package com.example.proyectoBodega.Repository;

import com.example.proyectoBodega.Model.DetallesVenta;
import com.example.proyectoBodega.Patters.conexionBD;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositoryDetallesVenta implements IRepositoryDetallesVenta {
    @Override
    public List<DetallesVenta> Listar() {
        String SQL = "SELECT nIdDetallesVenta, nIdProducto, nIdVenta, bEstado, nCantidad, nPrecio, dtFechaRegistro FROM nIdDetallesVenta WHERE bEstado = 1";
        List<DetallesVenta> detallesVentas = new ArrayList<>();

        try (
                Connection con = conexionBD.getConexion();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);
        ) {
            while (rs.next()) {
                DetallesVenta detalleVenta = new DetallesVenta();
                detalleVenta.setIdDetallesVenta(rs.getInt(1));
                detalleVenta.setIdProducto(rs.getInt(2));
                detalleVenta.setIdVenta(rs.getInt(3));
                detalleVenta.setEstado(rs.getBoolean(4));
                detalleVenta.setCantidad(rs.getInt(5));
                detalleVenta.setPrecio(rs.getDouble(6));
                detalleVenta.setFechaRegistro(rs.getDate(7));

                detallesVentas.add(detalleVenta);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return detallesVentas;
    }

    @Override
    public DetallesVenta ListarPorId(long id) {
        String SQL = "SELECT nIdDetallesVenta, nIdProducto, nIdVenta, bEstado, nCantidad, nPrecio, dtFechaRegistro FROM DetallesVenta WHERE bEstado = 1 AND nIdDetallesVenta = ?";
        DetallesVenta detalleVenta = null;

        try (
                Connection con = conexionBD.getConexion();
                PreparedStatement pstmt = con.prepareStatement(SQL);
        ) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                detalleVenta = new DetallesVenta();
                detalleVenta.setIdDetallesVenta(rs.getInt(1));
                detalleVenta.setIdProducto(rs.getInt(2));
                detalleVenta.setIdVenta(rs.getInt(3));
                detalleVenta.setEstado(rs.getBoolean(4));
                detalleVenta.setCantidad(rs.getInt(5));
                detalleVenta.setPrecio(rs.getDouble(6));
                detalleVenta.setFechaRegistro(rs.getDate(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return detalleVenta;
    }

    @Override
    public int Crear(DetallesVenta objeto) {
        String SQL = "INSERT INTO DetallesVenta(nIdProducto, nIdVenta, nCantidad, nPrecio, dtFechaRegistro) VALUES (?, ?, ?, ?, ?)";
        int resultado = -1;

        try (
                Connection con = conexionBD.getConexion();
                PreparedStatement pstmt = con.prepareStatement(SQL);
        ) {
            pstmt.setInt(1, objeto.getIdProducto());
            pstmt.setInt(2, objeto.getIdVenta());
            pstmt.setInt(3, objeto.getCantidad());
            pstmt.setDouble(4, objeto.getPrecio());
            pstmt.setDate(5, new java.sql.Date(objeto.getFechaRegistro().getTime()));

            resultado = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            resultado = -1;
        }

        return resultado;
    }

    @Override
    public int Modificar(DetallesVenta objeto) {
        String SQL = "UPDATE DetallesVenta SET nIdProducto = ?, nIdVenta = ?, nCantidad = ?, nPrecio = ?, dtFechaRegistro = ? WHERE nIdDetallesVenta = ? AND bEstado = 1";
        int resultado = -1;

        try (
                Connection con = conexionBD.getConexion();
                PreparedStatement pstmt = con.prepareStatement(SQL);
        ) {
            pstmt.setInt(1, objeto.getIdProducto());
            pstmt.setInt(2, objeto.getIdVenta());
            pstmt.setInt(3, objeto.getCantidad());
            pstmt.setDouble(4, objeto.getPrecio());
            pstmt.setDate(5, new java.sql.Date(objeto.getFechaRegistro().getTime()));

            resultado = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            resultado = -1;
        }

        return resultado;
    }

    @Override
    public int Eliminar(long id) {
        String SQL = "UPDATE DetallesVenta SET bEstado = 0 WHERE nIdDetallesVenta = ? AND bEstado = 1";
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
}
