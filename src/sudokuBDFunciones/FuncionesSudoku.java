package sudokuBDFunciones;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import clases.Casilla;
import clases.Sudoku;
import conexion.SQLConnect;

public class FuncionesSudoku {
	public static Sudoku generarSudokuInicial() {
		
		int id = -1;
	    String inicialStr = null;
	    String solucionStr = null;
	    String dificultad = null;
	    
		try (Connection conn = SQLConnect.getConnection()) {
			
			Statement stmnt = conn.createStatement();
			
			String sql = "SELECT CodSudoku, SudokuSinCompletar, SudokuCompletado, Dificultad\r\n"
					+ "FROM SudokuNR\r\n"
					+ "ORDER BY RANDOM()\r\n"
					+ "LIMIT 1;";
			
			ResultSet rs = stmnt.executeQuery(sql);
			
			if (rs.next()) {
	            
	            id = rs.getInt("CodSudoku");
	            inicialStr = rs.getString("SudokuSinCompletar");
	            solucionStr = rs.getString("SudokuCompletado");
	            dificultad = rs.getString("Dificultad");
	            
			}
			
			Casilla[][] tableroInicial = new Casilla[9][9];
			Casilla[][] tableroSolucion = new Casilla[9][9];
			
			int[][] matrizInicial = stringAIntMatriz(inicialStr);
			int[][] matrizSolucion = stringAIntMatriz(solucionStr);
			
			for (int fila = 0; fila < 9; fila++) {
				for (int col = 0; col < 9; col++) {

					int valor = matrizInicial[fila][col];
					boolean editable = (valor == 0);

					tableroInicial[fila][col] = new Casilla(valor, fila, col, editable);
				}
			}
			
			for (int fila = 0; fila < 9; fila++) {
				for (int col = 0; col < 9; col++) {

					int valor = matrizSolucion[fila][col];
					boolean editable = false;

					tableroSolucion[fila][col] = new Casilla(valor, fila, col, editable);
				}
			}

			return new Sudoku(tableroInicial, tableroSolucion, dificultad, id);
			} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			return null;
		} 
		
	}
	
	public static int[][] stringAIntMatriz(String string) {
	    
	    if (string == null || string.length() != 81) {
	        throw new IllegalArgumentException("La cadena de Sudoku debe tener exactamente 81 caracteres.");
	    }
	    
	    int[][] matriz = new int[9][9];
	    
	    for (int i = 0; i < 81; i++) {
	        int valor = Character.getNumericValue(string.charAt(i));
	        int fila = i / 9;
	        int columna = i % 9;
	        matriz[fila][columna] = valor;
	    }
	    
	    return matriz;
	}
	
	public static String comprobarCredenciales(String usuario, String contraseña) {
		int idBD = -1;
		String usuarioBD = "";
		String contraseñaBD = "";
		String rolBD = "ERROR";
		
		try (Connection conn = SQLConnect.getConnection()) {
			
			Statement stmnt = conn.createStatement();
			
			String sql = "SELECT IdUsuario, NombreUsuario, Password, Rol FROM Usuarios WHERE NombreUsuario = '" + usuario + "' AND Password = '" + contraseña + "'";
			
			ResultSet rs = stmnt.executeQuery(sql);
			
			if (rs.next()) {
	            rolBD = rs.getString("Rol");
			}
			
			} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			return rolBD;
		}
		return rolBD;
	}
	
public static Sudoku generarSudokuNuevo(String dificultad) {
		
		int id = -1;
	    String inicialStr = null;
	    String solucionStr = null;
	    
		try (Connection conn = SQLConnect.getConnection()) {
			
			Statement stmnt = conn.createStatement();
			
			String sql = "SELECT CodSudoku, SudokuSinCompletar, SudokuCompletado, Dificultad\r\n"
					+ "FROM SudokuNR\r\n"
					+ "WHERE Dificultad LIKE " + "'" + dificultad+ "'" +"\r\n"
					+ "ORDER BY RANDOM()\r\n"
					+ "LIMIT 1 \r\n;";
			
			ResultSet rs = stmnt.executeQuery(sql);
			
			if (rs.next()) {
	            
	            id = rs.getInt("CodSudoku");
	            inicialStr = rs.getString("SudokuSinCompletar");
	            solucionStr = rs.getString("SudokuCompletado");
	            
			}
			
			Casilla[][] tableroInicial = new Casilla[9][9];
			Casilla[][] tableroSolucion = new Casilla[9][9];
			
			int[][] matrizInicial = stringAIntMatriz(inicialStr);
			int[][] matrizSolucion = stringAIntMatriz(solucionStr);
			
			for (int fila = 0; fila < 9; fila++) {
				for (int col = 0; col < 9; col++) {

					int valor = matrizInicial[fila][col];
					boolean editable = (valor == 0);

					tableroInicial[fila][col] = new Casilla(valor, fila, col, editable);
				}
			}
			
			for (int fila = 0; fila < 9; fila++) {
				for (int col = 0; col < 9; col++) {

					int valor = matrizSolucion[fila][col];
					boolean editable = false;

					tableroSolucion[fila][col] = new Casilla(valor, fila, col, editable);
				}
			}

			return new Sudoku(tableroInicial, tableroSolucion, dificultad, id);
			} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			return null;
		} 
		
	}

	
}
	
