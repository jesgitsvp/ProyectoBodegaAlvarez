package com.example.proyectoBodega.Patters;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class conexionBD {
    private static String url = "jdbc:sqlserver://localhost:61150;databaseName=BDBodega;TrustServerCertificate=true";
    private static String userName = "giovanni";
    private static String password = "giovanni";
    private static Connection con;

    public static Connection getConexion() throws SQLException {

        con = DriverManager.getConnection(url, userName, password);
        System.out.println("CONEXION EXISTOSA");
        return con;
    }
}
