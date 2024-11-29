package com.example.proyectoBodega.Repository;

import com.example.proyectoBodega.Model.Categoria;
import com.example.proyectoBodega.Model.Producto;
import com.example.proyectoBodega.Model.Proveedor;
import com.example.proyectoBodega.Patters.conexionBD;
import org.springframework.stereotype.Repository;


import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositoryProductoImpl implements IRepositoryProducto {
    @Override
    public List<Producto> Listar() {
        String SQL = "SELECT nIdProducto, nIdCategoria, nIdProveedor, bEstado, vNombre, vDescripcion, nPrecio, nCosto, nStock, dtFechaRegistro FROM Producto WHERE bEstado = 1";
        List<Producto> productos = new ArrayList<>();

        try (
                Connection con = conexionBD.getConexion();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);
        ) {
            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt(1));
                producto.setIdCategoria(rs.getInt(2));
                producto.setIdProveedor(rs.getInt(3));
                producto.setEstado(rs.getBoolean(4));
                producto.setNombre(rs.getString(5));
                producto.setDescripcion(rs.getString(6));
                producto.setPrecio(rs.getDouble(7));
                producto.setCosto(rs.getDouble(8));
                producto.setStock(rs.getInt(9));
                producto.setFechaRegistro(rs.getDate(10));

                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;
    }

    @Override
    public Producto ListarPorId(long id) {
        String SQL = "SELECT nIdProducto, nIdCategoria, nIdProveedor, bEstado, vNombre, vDescripcion, nPrecio, nCosto, nStock, dtFechaRegistro FROM Producto WHERE bEstado = 1 AND nIdProducto = ?";
        Producto producto = null;

        try (
                Connection con = conexionBD.getConexion();
                PreparedStatement pstmt = con.prepareStatement(SQL);
        ) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                producto = new Producto();
                producto.setIdProducto(rs.getInt(1));
                producto.setIdCategoria(rs.getInt(2));
                producto.setIdProveedor(rs.getInt(3));
                producto.setEstado(rs.getBoolean(4));
                producto.setNombre(rs.getString(5));
                producto.setDescripcion(rs.getString(6));
                producto.setPrecio(rs.getDouble(7));
                producto.setCosto(rs.getDouble(8));
                producto.setStock(rs.getInt(9));
                producto.setFechaRegistro(rs.getDate(10));

            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return producto;
    }

    @Override
    public int Crear(Producto objeto) {
        String SQL = "INSERT INTO Producto(nIdCategoria, nIdProveedor, vNombre, vDescripcion, nPrecio, nCosto, nStock, dtFechaRegistro) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        int resultado = -1;

        try (
                Connection con = conexionBD.getConexion();
                PreparedStatement pstmt = con.prepareStatement(SQL);
        ) {
            pstmt.setInt(1, objeto.getIdCategoria());
            pstmt.setInt(2, objeto.getIdProveedor());
            pstmt.setString(3, objeto.getNombre());
            pstmt.setString(4, objeto.getDescripcion());
            pstmt.setDouble(5, objeto.getPrecio());
            pstmt.setDouble(6, objeto.getCosto());
            pstmt.setInt(7, objeto.getStock());
            pstmt.setDate(8, new java.sql.Date(objeto.getFechaRegistro().getTime()));

            resultado = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            resultado = -1;
        }

        return resultado;
    }

    @Override
    public int Modificar(Producto producto) {
        String SQL = "UPDATE Producto SET nIdCategoria = ?, nIdProveedor = ?, vNombre = ?, vDescripcion = ?, nPrecio = ?, nCosto = ?, nStock = ?  WHERE nIdProducto = ? AND bEstado = 1";
        int resultado = -1;

        try (Connection con = conexionBD.getConexion();
             PreparedStatement pstmt = con.prepareStatement(SQL)) {

            pstmt.setInt(1, producto.getIdCategoria());
            pstmt.setInt(2, producto.getIdProveedor());
            pstmt.setString(3, producto.getNombre());
            pstmt.setString(4, producto.getDescripcion());
            pstmt.setDouble(5, producto.getPrecio());
            pstmt.setDouble(6, producto.getCosto());
            pstmt.setInt(7, producto.getStock());
            pstmt.setLong(8, producto.getIdProducto()); // Asumiendo que getId() devuelve el ID del producto

            resultado = pstmt.executeUpdate();

            if (resultado > 0) {
                System.out.println("La actualización se realizó con éxito para el producto con ID: " + producto.getIdProducto());
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
        String SQL = "UPDATE Producto SET bEstado = 0 WHERE nIdProducto = ? AND bEstado = 1";
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
    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> productos = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Producto WHERE bEstado = 1 ")) {

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setIdProducto(rs.getInt(1));
                producto.setIdCategoria(rs.getInt(2));
                producto.setIdProveedor(rs.getInt(3));
                producto.setEstado(rs.getBoolean(4));
                producto.setNombre(rs.getString(5));
                producto.setDescripcion(rs.getString(6));
                producto.setPrecio(rs.getInt(7));
                producto.setCosto(rs.getInt(8));
                producto.setStock(rs.getInt(9));
                producto.setFechaRegistro(rs.getDate(10));


                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return productos;

    }

    @Override
    public List<Categoria> obtenerTodasLasCategorias() {
        List<Categoria> categorias = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Categoria")) {

            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt(1));
                categoria.setNombre(rs.getString(2));
                categoria.setEstado(rs.getBoolean(3));


                // Otros campos según tu modelo

                categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categorias;

    }

}

