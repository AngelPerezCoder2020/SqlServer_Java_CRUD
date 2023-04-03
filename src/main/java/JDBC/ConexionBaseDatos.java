package JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Objects;

public class ConexionBaseDatos {
    private static String url = "jdbc:sqlserver://DESKTOP-DARIO19;databaseName=Curso_Udemy;" +
            "TrustServerCertificate=True";
    private static Connection conn;

    public static Connection getConnection() throws SQLException {
        if(Objects.isNull(conn)){
            return DriverManager.getConnection(url,"sa","123");
        }
        return conn;
    }
}