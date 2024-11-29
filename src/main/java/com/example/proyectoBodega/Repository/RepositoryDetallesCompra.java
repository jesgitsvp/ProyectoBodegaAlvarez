package com.example.proyectoBodega.Repository;

import com.example.proyectoBodega.Model.DetallesCompra;
import com.example.proyectoBodega.Patters.conexionBD;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositoryDetallesCompra implements IRepositoryDetallesCompra{
    @Override
    public List<DetallesCompra> Listar() {
        String SQL = "SELECT nIdDetallesCompra, nIdProducto, nIdCompra, bEstado, nCantidad, nPrecio, dtFechaRegistro FROM DetallesCompra WHERE bEstado = 1";
        List<DetallesCompra> detallesCompra = new ArrayList<>();

        try (
                Connection con = conexionBD.getConexion();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);
        ) {
            while (rs.next()) {
                DetallesCompra detalleCompra = new DetallesCompra();
                detalleCompra.setIdDetallesCompra(rs.getInt(1));
                detalleCompra.setIdProducto(rs.getInt(2));
                detalleCompra.setIdCompra(rs.getInt(3));
                detalleCompra.setEstado(rs.getBoolean(4));
                detalleCompra.setCantidad(rs.getInt(5));
                detalleCompra.setPrecio(rs.getDouble(6));
                detalleCompra.setFechaRegistro(rs.getDate(7));

                detallesCompra.add(detalleCompra);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return detallesCompra;
    }

    @Override
    public DetallesCompra ListarPorId(long id) {
        String SQL = "SELECT nIdDetallesCompra, nIdProducto, nIdCompra, bEstado, nCantidad, nPrecio, dtFechaRegistro FROM DetallesCompra WHERE bEstado = 1 AND nIdDetallesCompra = ?";
        DetallesCompra detalleCompra = null;

        try (
                Connection con = conexionBD.getConexion();
                PreparedStatement pstmt = con.prepareStatement(SQL);
        ) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                detalleCompra = new DetallesCompra();
                detalleCompra.setIdDetallesCompra(rs.getInt(1));
                detalleCompra.setIdProducto(rs.getInt(2));
                detalleCompra.setIdCompra(rs.getInt(3));
                detalleCompra.setEstado(rs.getBoolean(4));
                detalleCompra.setCantidad(rs.getInt(5));
                detalleCompra.setPrecio(rs.getDouble(6));
                detalleCompra.setFechaRegistro(rs.getDate(7));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return detalleCompra;
    }

    @Override
    public int Crear(DetallesCompra objeto) {
        String SQL = "INSERT INTO DetallesCompra(nIdProducto, nIdCompra, nCantidad, nPrecio, dtFechaRegistro) VALUES (?, ?, ?, ?, ?)";
        int resultado = -1;

        try (
                Connection con = conexionBD.getConexion();
                PreparedStatement pstmt = con.prepareStatement(SQL);
        ) {
            pstmt.setInt(1, objeto.getIdProducto());
            pstmt.setInt(2, objeto.getIdCompra());
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
    public int Modificar(DetallesCompra objeto) {
        String SQL = "UPDATE DetallesCompra SET nIdProducto = ?, nIdCompra = ?, nCantidad = ?, nPrecio = ?, dtFechaRegistro = ? WHERE nIdDetallesCompra = ? AND bEstado = 1";
        int resultado = -1;

        try (
                Connection con = conexionBD.getConexion();
                PreparedStatement pstmt = con.prepareStatement(SQL);
        ) {
            pstmt.setInt(1, objeto.getIdProducto());
            pstmt.setInt(2, objeto.getIdCompra());
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
        String SQL = "UPDATE DetallesCompra SET bEstado = 0 WHERE nIdDetallesCompra = ? AND bEstado = 1";
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
