package com.example.proyectoBodega.Repository;

import com.example.proyectoBodega.Model.Empleado;
import com.example.proyectoBodega.Model.Proveedor;
import com.example.proyectoBodega.Patters.conexionBD;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositoryProveedorImpl implements IRepositoryProveedor {
    @Override
    public List<Proveedor> Listar() {
        String SQL = "SELECT nIdProveedor, bEstado, vNombre, vApellido, vDireccion, vTelefono, vRuc, dtFechaRegistro, dtFechaEliminacion FROM Proveedor WHERE bEstado = 1";
        List<Proveedor> proveedores = new ArrayList<>();

        try (
                Connection con = conexionBD.getConexion();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);
        ) {
            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt(1));
                proveedor.setEstado(rs.getBoolean(2));
                proveedor.setNombre(rs.getString(3));
                proveedor.setApellido(rs.getString(4));
                proveedor.setDireccion(rs.getString(5));
                proveedor.setTelefono(rs.getString(6));
                proveedor.setRuc(rs.getString(7));
                proveedor.setFechaRegistro(rs.getDate(8));
                proveedor.setFechaEliminacion(rs.getDate(9));

                proveedores.add(proveedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return proveedores;
    }

    @Override
    public Proveedor ListarPorId(long id) {
        String SQL = "SELECT nIdProveedor, bEstado, vNombre, vApellido, vDireccion, vTelefono, vRuc, dtFechaRegistro, dtFechaEliminacion FROM Proveedor WHERE bEstado = 1 AND nIdProveedor = ?";
        Proveedor proveedor = null;

        try (
                Connection con = conexionBD.getConexion();
                PreparedStatement pstmt = con.prepareStatement(SQL);
        ) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt(1));
                proveedor.setEstado(rs.getBoolean(2));
                proveedor.setNombre(rs.getString(3));
                proveedor.setApellido(rs.getString(4));
                proveedor.setDireccion(rs.getString(5));
                proveedor.setTelefono(rs.getString(6));
                proveedor.setRuc(rs.getString(7));
                proveedor.setFechaRegistro(rs.getDate(8));
                proveedor.setFechaEliminacion(rs.getDate(9));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return proveedor;
    }

    @Override
    public int Crear(Proveedor objeto) {
        String SQL = "INSERT INTO Proveedor(vNombre, vApellido, vDireccion, vTelefono, vRuc, dtFechaRegistro, dtFechaEliminacion) VALUES (?, ?, ?,?, ?, ?, ?)";
        int resultado = -1;

        try (
                Connection con = conexionBD.getConexion();
                PreparedStatement pstmt = con.prepareStatement(SQL);
        ) {
            pstmt.setString(1, objeto.getNombre());
            pstmt.setString(2, objeto.getApellido());
            pstmt.setString(3, objeto.getDireccion());
            pstmt.setString(4, objeto.getTelefono());
            pstmt.setString(5, objeto.getRuc());
            pstmt.setDate(6, new java.sql.Date(objeto.getFechaRegistro().getTime()));
            pstmt.setDate(7, new java.sql.Date(objeto.getFechaEliminacion().getTime()));

            resultado = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            resultado = -1;
        }

        return resultado;
    }

    @Override
    public int Modificar(Proveedor proveedor) {
        String SQL = "UPDATE Proveedor SET vNombre = ?, vApellido = ?, vDireccion = ?, vTelefono = ?, vRuc = ? WHERE nIdProveedor = ? AND bEstado = 1";
        int resultado = -1;

        try (
                Connection con = conexionBD.getConexion();
                PreparedStatement pstmt = con.prepareStatement(SQL);
        ) {
            pstmt.setString(1, proveedor.getNombre());
            pstmt.setString(2, proveedor.getApellido());
            pstmt.setString(3, proveedor.getDireccion());
            pstmt.setString(4, proveedor.getTelefono());
            pstmt.setString(5, proveedor.getRuc());
            pstmt.setInt(6,proveedor.getIdProveedor());



            resultado = pstmt.executeUpdate();
            if (resultado > 0) {
                System.out.println("La actualización se realizó con éxito para el producto con ID: " + proveedor.getIdProveedor());
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
        String SQL = "UPDATE Proveedor SET bEstado = 0 WHERE nIdProveedor = ? AND bEstado = 1";
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
    public List<Proveedor> obtenerTodosLosProveedores() {
        List<Proveedor> proveedores = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Proveedor  WHERE bEstado = 1")) {

            while (rs.next()) {
                Proveedor proveedor = new Proveedor();
                proveedor.setIdProveedor(rs.getInt(1));
                proveedor.setEstado(rs.getBoolean(2));
                proveedor.setNombre(rs.getString(3));
                proveedor.setApellido(rs.getString(4));
                proveedor.setDireccion(rs.getString(5));
                proveedor.setTelefono(rs.getString(6));
                proveedor.setRuc(rs.getString(7));
                proveedor.setFechaRegistro(rs.getDate(8));



                // Otros campos según tu modelo

                proveedores.add(proveedor);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return proveedores;

    }
}
