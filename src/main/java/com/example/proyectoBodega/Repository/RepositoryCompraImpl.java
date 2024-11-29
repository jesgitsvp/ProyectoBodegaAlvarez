package com.example.proyectoBodega.Repository;

import com.example.proyectoBodega.Model.*;
import com.example.proyectoBodega.Patters.conexionBD;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositoryCompraImpl implements IRepositoryCompra {
    @Override
    public List<Compra> Listar() {
        String SQL = "SELECT nIdCompra, nIdProducto, nIdProveedor, nIdEmpleado, bEstado, nStock, nCosto, dtFechaCompra, dtFechaRegistro FROM Compra WHERE bEstado = 1";
        List<Compra> compras = new ArrayList<>();

        try (
                Connection con = conexionBD.getConexion();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);
        ) {
            while (rs.next()) {
                Compra compra = new Compra();
                compra.setIdCompra(rs.getInt(1));
                compra.setIdProducto(rs.getInt(2));
                compra.setIdProveedor(rs.getInt(3));
                compra.setIdEmpleado(rs.getInt(4));
                compra.setEstado(rs.getBoolean(5));
                compra.setStock(rs.getInt(6));
                compra.setCosto(rs.getDouble(7));
                compra.setFechaCompra(rs.getDate(8));
                compra.setFechaRegistro(rs.getDate(9));

                compras.add(compra);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return compras;
    }

    @Override
    public Compra ListarPorId(long id) {
        String SQL = "SELECT nIdCompra, nIdProducto, nIdProveedor, nIdEmpleado, bEstado, nStock, nCosto, dtFechaCompra, dtFechaRegistro FROM Compra WHERE bEstado = 1 AND nIdCompra = ?";
        Compra compra = null;

        try (
                Connection con = conexionBD.getConexion();
                PreparedStatement pstmt = con.prepareStatement(SQL);
        ) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                compra = new Compra();
                compra.setIdCompra(rs.getInt(1));
                compra.setIdProducto(rs.getInt(2));
                compra.setIdProveedor(rs.getInt(3));
                compra.setIdEmpleado(rs.getInt(4));
                compra.setEstado(rs.getBoolean(5));
                compra.setStock(rs.getInt(6));
                compra.setCosto(rs.getDouble(7));
                compra.setFechaCompra(rs.getDate(8));
                compra.setFechaRegistro(rs.getDate(9));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return compra;
    }

    @Override
    public int Crear(Compra objeto) {
        String SQL = "INSERT INTO Compra(nIdProducto, nIdProveedor, nIdEmpleado, nStock, nCosto, dtFechaCompra, dtFechaRegistro) VALUES (?, ?, ?, ?, ?, ?, ?)";
        int resultado = -1;

        try (
                Connection con = conexionBD.getConexion();
                PreparedStatement pstmt = con.prepareStatement(SQL);
        ) {
            pstmt.setInt(1, objeto.getIdProducto());
            pstmt.setInt(2, objeto.getIdProveedor());
            pstmt.setInt(3, objeto.getIdEmpleado());
            pstmt.setInt(4, objeto.getStock());
            pstmt.setDouble(5, objeto.getCosto());
            pstmt.setDate(6, (Date) objeto.getFechaCompra());
            pstmt.setDate(7, new java.sql.Date(objeto.getFechaRegistro().getTime()));

            resultado = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            resultado = -1;
        }

        return resultado;
    }

    @Override
    public int Modificar(Compra objeto) {
        String SQL = "UPDATE Compra SET nIdProducto = ?, nIdProveedor = ?, nIdEmpleado = ?, nStock = ?, nCosto = ?, dtFechaCompra = ?, dtFechaRegistro = ? WHERE nIdCompra = ? AND bEstado = 1";
        int resultado = -1;

        try (
                Connection con = conexionBD.getConexion();
                PreparedStatement pstmt = con.prepareStatement(SQL);
        ) {
            pstmt.setInt(1, objeto.getIdProducto());
            pstmt.setInt(2, objeto.getIdProveedor());
            pstmt.setInt(3, objeto.getIdEmpleado());
            pstmt.setInt(4, objeto.getStock());
            pstmt.setDouble(5, objeto.getCosto());
            pstmt.setDate(6, (Date) objeto.getFechaCompra());
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
        String SQL = "UPDATE Compra SET bEstado = 0 WHERE nIdCompra = ? AND bEstado = 1";
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
    public List<Compra> obtenerTodasLasCompras() {
        List<Compra> compras = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Compra")) {

            while (rs.next()) {
                Compra compra = new Compra();
                compra.setIdCompra(rs.getInt(1));
                compra.setIdProducto(rs.getInt(2));
                compra.setIdProveedor(rs.getInt(3));
                compra.setIdEmpleado(rs.getInt(4));
                compra.setEstado(rs.getBoolean(5));
                compra.setStock(rs.getInt(6));
                compra.setCosto(rs.getInt(7));
                compra.setFechaCompra(rs.getDate(8));
                compra.setFechaRegistro(rs.getDate(9));
                compra.setFechaEliminacion(rs.getDate(10));
                // Otros campos según tu modelo

                compras.add(compra);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return compras;

    }

    @Override
    public List<Producto> obtenerTodosLosProductos() {
        List<Producto> productos = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Producto")) {

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
    @Override
    public List<Proveedor> obtenerTodosLosProveedores() {
        List<Proveedor> proveedores = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Proveedor")) {

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
