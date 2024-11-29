package com.example.proyectoBodega.Repository;

import com.example.proyectoBodega.Model.Categoria;
import com.example.proyectoBodega.Model.Cliente;
import com.example.proyectoBodega.Patters.conexionBD;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositoryClienteImpl implements IRepositoryCliente {
    @Override
    public List<Cliente> Listar() {
        String SQL = "SELECT nIdCliente, bEstado, vNombre, vDireccion, vTelefono, dtFechaRegistro FROM Cliente WHERE bEstado = 1";
        List<Cliente> clientes = new ArrayList<>();

        try (
                Connection con = conexionBD.getConexion();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);
        ) {
            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt(1));
                cliente.setEstado(rs.getBoolean(2));
                cliente.setNombre(rs.getString(3));
                cliente.setDireccion(rs.getString(4));
                cliente.setTelefono(rs.getString(5));
                cliente.setFechaRegistro(rs.getDate(6));

                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;
    }

    @Override
    public Cliente ListarPorId(long id) {
        String SQL = "SELECT nIdCliente, bEstado, vNombre, vDireccion, vTelefono, dtFechaRegistro FROM Cliente WHERE bEstado = 1 AND nIdCliente = ?";
        Cliente cliente = null;

        try (
                Connection con = conexionBD.getConexion();
                PreparedStatement pstmt = con.prepareStatement(SQL);
        ) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                cliente = new Cliente();
                cliente.setIdCliente(rs.getInt(1));
                cliente.setEstado(rs.getBoolean(2));
                cliente.setNombre(rs.getString(3));
                cliente.setDireccion(rs.getString(4));
                cliente.setTelefono(rs.getString(5));
                cliente.setFechaRegistro(rs.getDate(6));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return cliente;
    }

    @Override
    public int Crear(Cliente objeto) {
        String SQL = "INSERT INTO Cliente(vNombre, vDireccion, vTelefono, dtFechaRegistro) VALUES (?, ?, ?, ?)";
        int resultado = -1;

        try (
                Connection con = conexionBD.getConexion();
                PreparedStatement pstmt = con.prepareStatement(SQL);
        ) {
            pstmt.setString(1, objeto.getNombre());
            pstmt.setString(2, objeto.getDireccion());
            pstmt.setString(3, objeto.getTelefono());
            pstmt.setDate(4, new java.sql.Date(objeto.getFechaRegistro().getTime()));

            resultado = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            resultado = -1;
        }

        return resultado;
    }

    @Override
    public int Modificar(Cliente cliente) {
        String SQL = "UPDATE Cliente SET vNombre = ?, vDireccion = ?, vTelefono = ? WHERE nIdCliente = ? AND bEstado = 1";
        int resultado = -1;

        try (
                Connection con = conexionBD.getConexion();
                PreparedStatement pstmt = con.prepareStatement(SQL);
        ) {
            pstmt.setString(1, cliente.getNombre());
            pstmt.setString(2, cliente.getDireccion());
            pstmt.setString(3, cliente.getTelefono());
            pstmt.setLong(4, cliente.getIdCliente());

            resultado = pstmt.executeUpdate();

            if (resultado > 0) {
                System.out.println("La actualización se realizó con éxito para el producto con ID: " + cliente.getIdCliente());
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
        String SQL = "UPDATE Cliente SET bEstado = 0 WHERE nIdCliente = ? AND bEstado = 1";
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
    public List<Cliente> obtenerTodosLosClientes() {
        List<Cliente> clientes = new ArrayList<>();

        try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
             Statement stmt = con.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM Cliente  WHERE bEstado = 1")) {

            while (rs.next()) {
                Cliente cliente = new Cliente();
                cliente.setIdCliente(rs.getInt(1));
                cliente.setEstado(rs.getBoolean(2));
                cliente.setNombre(rs.getString(3));
                cliente.setDireccion(rs.getString(4));
                cliente.setTelefono(rs.getString(5));
                cliente.setFechaRegistro(rs.getDate(6));
                cliente.setFechaEliminacion(rs.getDate(7));
                // Otros campos según tu modelo

                clientes.add(cliente);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return clientes;

    }
}
