package com.example.proyectoBodega.Repository;

import com.example.proyectoBodega.Model.Proveedor;
import com.example.proyectoBodega.Model.Venta;
import com.example.proyectoBodega.Patters.conexionBD;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositoryVentaImpl implements IRepositoryVenta {
    @Override
    public List<Venta> Listar() {
        String SQL = "SELECT nIdVenta, nIdProducto, nIdCliente, nIdEmpleado, bEstado, nCantidad, dtFechaVenta, vMetodoPago, dtFechaRegistro FROM Venta WHERE bEstado = 1";
        List<Venta> ventas = new ArrayList<>();

        try (
                Connection con = conexionBD.getConexion();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);
        ) {
            while (rs.next()) {
                Venta venta = new Venta();
                venta.setIdVenta(rs.getInt(1));
                venta.setIdProducto(rs.getInt(2));
                venta.setIdCliente(rs.getInt(3));
                venta.setIdEmpleado(rs.getInt(4));
                venta.setEstado(rs.getBoolean(5));
                venta.setCantidad(rs.getInt(6));
                venta.setFechaVenta(rs.getDate(7));
                venta.setMetodoPago(rs.getString(8));
                venta.setFechaRegistro(rs.getDate(9));

                ventas.add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ventas;
    }

    @Override
    public Venta ListarPorId(long id) {
        String SQL = "SELECT nIdVenta, nIdProducto, nIdCliente, nIdEmpleado, bEstado, nCantidad, dtFechaVenta, vMetodoPago, dtFechaRegistro FROM Venta WHERE bEstado = 1 AND nIdVenta = ?";
        Venta venta = null;

        try (
                Connection con = conexionBD.getConexion();
                PreparedStatement pstmt = con.prepareStatement(SQL);
        ) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                venta = new Venta();
                venta.setIdVenta(rs.getInt(1));
                venta.setIdProducto(rs.getInt(2));
                venta.setIdCliente(rs.getInt(3));
                venta.setIdEmpleado(rs.getInt(4));
                venta.setEstado(rs.getBoolean(5));
                venta.setCantidad(rs.getInt(6));
                venta.setFechaVenta(rs.getDate(7));
                venta.setMetodoPago(rs.getString(8));
                venta.setFechaRegistro(rs.getDate(9));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return venta;
    }

    @Override
    public int Crear(Venta objeto) {
        String SQL = "INSERT INTO Venta(nIdProducto, nIdCliente, nIdEmpleado, nCantidad, dtFechaVenta, vMetodoPago, dtFechaRegistro) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int resultado = -1;

        try (
                Connection con = conexionBD.getConexion();
                PreparedStatement pstmt = con.prepareStatement(SQL);
        ) {
            pstmt.setInt(1, objeto.getIdProducto());
            pstmt.setInt(2, objeto.getIdCliente());
            pstmt.setInt(3, objeto.getIdEmpleado());
            pstmt.setInt(4, objeto.getCantidad());
            pstmt.setDate(5, new java.sql.Date(objeto.getFechaVenta().getTime()));
            pstmt.setString(6, objeto.getMetodoPago());
            pstmt.setDate(7, new java.sql.Date(objeto.getFechaRegistro().getTime()));

            resultado = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            resultado = -1;
        }

        return resultado;
    }

    @Override
    public int Modificar(Venta objeto) {
        String SQL = "UPDATE Venta SET nIdProducto = ?, nIdCliente = ?, nIdEmpleado = ?, nCantidad = ?, dtFechaVenta = ?, vMetodoPago = ?, dtFechaRegistro = ? WHERE nIdVenta = ? AND bEstado = 1";
        int resultado = -1;

        try (
                Connection con = conexionBD.getConexion();
                PreparedStatement pstmt = con.prepareStatement(SQL);
        ) {
            pstmt.setInt(1, objeto.getIdProducto());
            pstmt.setInt(2, objeto.getIdCliente());
            pstmt.setInt(3, objeto.getIdEmpleado());
            pstmt.setInt(4, objeto.getCantidad());
            pstmt.setDate(5, new java.sql.Date(objeto.getFechaVenta().getTime()));
            pstmt.setString(6, objeto.getMetodoPago());
            pstmt.setDate(7, new java.sql.Date(objeto.getFechaRegistro().getTime()));

            resultado = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            resultado = -1;
        }

        return resultado;
    }

    @Override
    public int Eliminar(long id) {
        String SQL = "UPDATE Venta SET bEstado = 0 WHERE nIdVenta = ? AND bEstado = 1";
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
    public List<Venta> obtenerTodasLasVentas() {
        List<Venta> ventas = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Venta  WHERE bEstado = 1")) {

            while (rs.next()) {
                Venta venta = new Venta();

                venta.setIdVenta(rs.getInt(1));
                venta.setIdProducto(rs.getInt(2));
                venta.setIdCliente(rs.getInt(3));
                venta.setIdEmpleado(rs.getInt(4));
                venta.setCantidad(rs.getInt(5));
                venta.setFechaVenta(rs.getDate(6));
                venta.setMetodoPago(rs.getString(7));
                venta.setFechaRegistro(rs.getDate(8));
                venta.setFechaEliminacion(rs.getDate(9));





                // Otros campos según tu modelo

                ventas.add(venta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return ventas;

    }
}

