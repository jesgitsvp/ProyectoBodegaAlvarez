package com.example.proyectoBodega.Repository;

import com.example.proyectoBodega.Model.Categoria;
import com.example.proyectoBodega.Patters.conexionBD;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.jdbc.JdbcTemplateAutoConfiguration;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class RepositoryCategoriaImpl implements IRepositoryCategoria {


    @Override
    public List<Categoria> Listar() {
        String SQL = "SELECT nIdCategoria, bEstado, vNombre, dtFechaRegistro FROM Categoria WHERE bEstado = 1";
        List<Categoria> categorias = new ArrayList<>();

        try (
                Connection con = conexionBD.getConexion();
                Statement stmt = con.createStatement();
                ResultSet rs = stmt.executeQuery(SQL);
        ) {
            while (rs.next()) {
                Categoria categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt(1));
                categoria.setEstado(rs.getBoolean(2));
                categoria.setNombre(rs.getString(3));
                categoria.setFechaRegistro(rs.getDate(4));

                categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categorias;
    }

    @Override
    public Categoria ListarPorId(long id) {
        String SQL = "SELECT nIdCategoria, bEstado, vNombre, dtFechaRegistro FROM Categoria WHERE bEstado = 1 AND nIdCategoria = ?";
        Categoria categoria = null;

        try (
                Connection con = conexionBD.getConexion();
                PreparedStatement pstmt = con.prepareStatement(SQL);
        ) {
            pstmt.setLong(1, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                categoria = new Categoria();
                categoria.setIdCategoria(rs.getInt(1));
                categoria.setEstado(rs.getBoolean(2));
                categoria.setNombre(rs.getString(3));
                categoria.setFechaRegistro(rs.getDate(4));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categoria;
    }

    @Override
    public int Crear(Categoria objeto) {
        String SQL = "INSERT INTO Categoria(vNombre, dtFechaRegistro) VALUES (?, ?)";
        int resultado = -1;

        try (
                Connection con = conexionBD.getConexion();
                PreparedStatement pstmt = con.prepareStatement(SQL);
        ) {
            pstmt.setString(1, objeto.getNombre());
            pstmt.setDate(2, new java.sql.Date(objeto.getFechaRegistro().getTime()));

            resultado = pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            resultado = -1;
        }

        return resultado;
    }

    @Override
    public int Modificar(Categoria categoria) {
        String SQL = "UPDATE Categoria SET vNombre = ? WHERE nIdCategoria = ? AND bEstado = 1";
        int resultado = -1;

        try (
                Connection con = conexionBD.getConexion();
                PreparedStatement pstmt = con.prepareStatement(SQL);
        ) {
            pstmt.setString(1, categoria.getNombre());
            pstmt.setInt(2, categoria.getIdCategoria() );

            resultado = pstmt.executeUpdate();

            if (resultado > 0) {
                System.out.println("La actualización se realizó con éxito para el producto con ID: " + categoria.getIdCategoria());
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
        String SQL = "UPDATE Categoria SET bEstado = 0 WHERE nIdCategoria = ? AND bEstado = 1";
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



        // Asegúrate de configurar la URL, el usuario y la contraseña correctamente
        private static final String URL = "jdbc:sqlserver://localhost:61150;databaseName=BDBodega;TrustServerCertificate=true";
        private static final String USER = "giovanni";
        private static final String PASSWORD = "giovanni";




        @Override
        public List<Categoria> obtenerTodasLasCategorias() {
            List<Categoria> categorias = new ArrayList<>();

            try (Connection con = DriverManager.getConnection(URL, USER, PASSWORD);
                 Statement stmt = con.createStatement();
                 ResultSet rs = stmt.executeQuery("SELECT * FROM Categoria WHERE bEstado = 1")) {

                while (rs.next()) {
                    Categoria categoria = new Categoria();
                    categoria.setIdCategoria(rs.getInt(1));
                    categoria.setEstado(rs.getBoolean(2));
                    categoria.setNombre(rs.getString(3));



                    // Otros campos según tu modelo

                    categorias.add(categoria);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return categorias;

        }


}
