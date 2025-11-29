package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnect {
    
    private static final String URL = "jdbc:sqlite:bd/base_de_datos_sudoku.db";

   
    public static Connection getConnection() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
            System.out.println("Conexión establecida con éxito.");

        } catch (SQLException e) {
            System.err.println("Error de conexión (Ruta o archivo DB): " + e.getMessage());
        }
        return conn;
    }
}