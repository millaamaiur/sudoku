package conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class SQLConnect {
    private static Connection conexion = null;
    
    private static final String URL = "jdbc:sqlite:bd/base_de_datos_sudoku.db";

    private SQLConnect() {
        
    }

    
    public static Connection getConnection() {
        // Solo conecta si la conexión aún no existe
        if (conexion == null) { 
            try {
                // Establece la conexión
                conexion = DriverManager.getConnection(URL);
                System.out.println("Conexión establecida con éxito.");

            } catch (SQLException e) {
                System.err.println("Error de conexión (Ruta o archivo DB): " + e.getMessage());
            }
        }
        // Devuelve la conexión existente o la nueva
        return conexion;
    }
    
    public static void closeConnection() {
        if (conexion != null) {
            try {
                conexion.close();
                conexion = null; // Reseteamos la instancia estática
                System.out.println("Conexión a la DB cerrada.");
            } catch (SQLException e) {
                System.err.println("Error al cerrar la conexión: " + e.getMessage());
            }
        }
    }
}