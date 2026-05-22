package Modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexión {
     private static final String URL = "jdbc:mariadb://localhost:3306/";

    private static final String BD = "Tienda";
    private static final String USUARIO = "root";
    private static final String PASSWORD = "";

    public static Connection conectarServidor() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, PASSWORD);
    }

    public static Connection conectarBD() throws SQLException {
        return DriverManager.getConnection(URL + BD, USUARIO, PASSWORD);
    }

}
