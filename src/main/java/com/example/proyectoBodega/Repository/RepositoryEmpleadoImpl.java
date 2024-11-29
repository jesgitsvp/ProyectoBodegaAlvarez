package com.example.proyectoBodega.Repository;

import com.example.proyectoBodega.Model.Categoria;
import com.example.proyectoBodega.Model.Empleado;
import com.example.proyectoBodega.Model.Producto;
import com.example.proyectoBodega.Patters.conexionBD;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositoryEmpleadoImpl implements IRepositoryEmpleado {
    @Override
    public List<Empleado> Listar() {
        String SQL = "SELECT nIdEmpleado, bEstado, vNombre, vApellido, nSalario, vTelefono, vDNI, nTiempoDeTrabajo, vCargo, dtFechaRegistro, dtFechaeliminacion FROM Empleado WHERE bEstado = 1";
        List<Empleado> empleados = new ArrayList<>();

        try (
                Connection con = conexionBD.getConexion();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);
        ) {
            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setIdEmpleado(rs.getInt(1));
                empleado.setEstado(rs.getBoolean(2));
                empleado.setNombre(rs.getString(3));
                empleado.setApellido(rs.getString(4));
                empleado.setSalario(rs.getDouble(5));
                empleado.setTelefono(rs.getString(6));
                empleado.setDNI(rs.getString(7));
                empleado.setTiempoDeTrabajo(rs.getInt(8));
                empleado.setCargo(rs.getString(9));
                empleado.setFechaRegistro(rs.getDate(10));
                empleado.setFechaEliminacion(rs.getDate(10));

                empleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return empleados;
    }

    @Override
    public Empleado ListarPorId(long id) {
        String SQL = "SELECT nIdEmpleado, bEstado, vNombre, vApellido, nSalario, vTelefono, vDNI, nTiempoDeTrabajo, vCargo, dtFechaRegistro, dtFechaeliminacion FROM Empleado WHERE bEstado = 1 AND nIdEmpleado = ?";
        Empleado empleado = null;

        try (
                Connection con = conexionBD.getConexion();
                PreparedStatement pstmt = con.prepareStatement(SQL);
        ) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                empleado = new Empleado();
                empleado.setIdEmpleado(rs.getInt(1));
                empleado.setEstado(rs.getBoolean(2));
                empleado.setNombre(rs.getString(3));
                empleado.setApellido(rs.getString(4));
                empleado.setSalario(rs.getDouble(5));
                empleado.setTelefono(rs.getString(6));
                empleado.setDNI(rs.getString(7));
                empleado.setTiempoDeTrabajo(rs.getInt(8));
                empleado.setCargo(rs.getString(9));
                empleado.setFechaRegistro(rs.getDate(10));
                empleado.setFechaEliminacion(rs.getDate(10));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return empleado;
    }


    @Override
    public int Crear(Empleado objeto) {
        String SQL = "INSERT INTO Empleado(vNombre, vApellido, nSalario, vTelefono, vDNI, nTiempoDeTrabajo, vCargo, dtFechaRegistro, dtFechaeliminacion) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int resultado = -1;
        try (
                Connection con = conexionBD.getConexion();
                PreparedStatement pstmt = con.prepareStatement(SQL);
        ) {
            pstmt.setString(1, objeto.getNombre());
            pstmt.setString(2, objeto.getApellido());
            pstmt.setDouble(3, objeto.getSalario());
            pstmt.setString(4, objeto.getTelefono());
            pstmt.setString(5, objeto.getDNI());
            pstmt.setInt(6, objeto.getTiempoDeTrabajo());
            pstmt.setString(7, objeto.getCargo());
            pstmt.setDate(8, new java.sql.Date(objeto.getFechaRegistro().getTime()));
            pstmt.setDate(9, new java.sql.Date(objeto.getFechaEliminacion().getTime()));

            resultado = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            resultado = -1;
        }

        return resultado;
    }

    @Override
    public int Modificar(Empleado empleado) {
        String SQL = "UPDATE Empleado SET  vNombre = ?, vApellido = ?, nSalario = ?, vTelefono = ?, vDNI = ? , nTiempoDeTrabajo = ?, vCargo = ?  WHERE nIdEmpleado = ? AND bEstado = 1";
        int resultado = -1;

        try (Connection con = conexionBD.getConexion();
             PreparedStatement pstmt = con.prepareStatement(SQL)) {

            pstmt.setString(1, empleado.getNombre());
            pstmt.setString(2, empleado.getApellido());
            pstmt.setDouble(3, empleado.getSalario());
            pstmt.setString(4, empleado.getTelefono());
            pstmt.setString(5, empleado.getDNI());
            pstmt.setDouble(6, empleado.getTiempoDeTrabajo());
            pstmt.setString(7, empleado.getCargo());
            pstmt.setLong(8, empleado.getIdEmpleado()); // Asumiendo que getId() devuelve el ID del producto

            resultado = pstmt.executeUpdate();

            if (resultado > 0) {
                System.out.println("La actualización se realizó con éxito para el producto con ID: " + empleado.getIdEmpleado());
            } else {
                System.out.println("La actualización no afectó ningún registro.");
            }

        } catch (SQLException e) {
            System.err.println("Error al intentar modificar el producto: " + e.getMessage());
            e.printStackTrace();
        }

        return resultado;
    }

    @Override
    public int Eliminar(long id) {
        String SQL = "UPDATE Empleado SET bEstado = 0 WHERE nIdEmpleado = ? AND bEstado = 1";
        int resultado = -1;

        try (
                Connection con = conexionBD.getConexion();
                PreparedStatement pstmt = con.prepareStatement(SQL);
        ) {
            pstmt.setLong(1, id);
            resultado = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            resultado = -1;
        }

        return resultado;
    }
    private static final String URL = "jdbc:sqlserver://localhost:61150;databaseName=BDBodega;TrustServerCertificate=true";
    private static final String USER = "giovanni";
    private static final String PASSWORD = "giovanni";
    @Override
    public List<Empleado> obtenerTodosLosEmpleados() {
        List<Empleado> empleados = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM  Empleado WHERE bEstado = 1")) {

            while (rs.next()) {
                Empleado empleado = new Empleado();
                empleado.setIdEmpleado(rs.getInt(1));
                empleado.setEstado(rs.getBoolean(2));
                empleado.setNombre(rs.getString(3));
                empleado.setApellido(rs.getString(4));
                empleado.setSalario(rs.getDouble(5));
                empleado.setTelefono(rs.getString(6));
                empleado.setDNI(rs.getString(7));
                empleado.setTiempoDeTrabajo(rs.getInt(8));
                empleado.setCargo(rs.getString(9));
                empleado.setFechaRegistro(rs.getDate(10));

                // Otros campos según tu modelo

                empleados.add(empleado);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return empleados;

    }
}
