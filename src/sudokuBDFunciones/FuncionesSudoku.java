package sudokuBDFunciones;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;

import clases.Casilla;
import clases.Sudoku;
import conexion.SQLConnect;

public class FuncionesSudoku {
	
	//Esta funcion selecciona un sudoku RANDOM de la base de datos
	public static Sudoku generarSudokuInicial() {
		
		
		//Inicializamos las variables que vamos a usar
		int id = -1;
	    String inicialStr = null;
	    String solucionStr = null;
	    String dificultad = null;
	    
	    //Abrimos la conexion
		try (Connection conn = SQLConnect.getConnection()) {
			
			Statement stmnt = conn.createStatement();
			
			//Sentencia SQL que se usa para seleccionar un sudoku random de la BD
			String sql = "SELECT CodSudoku, SudokuSinCompletar, SudokuCompletado, Dificultad\r\n"
					+ "FROM SudokuNR\r\n"
					+ "ORDER BY RANDOM()\r\n"
					+ "LIMIT 1;";
			
			ResultSet rs = stmnt.executeQuery(sql);
			
			if (rs.next()) {
	            //Se guardan los valores del sudoku en variables
	            id = rs.getInt("CodSudoku");
	            inicialStr = rs.getString("SudokuSinCompletar");
	            solucionStr = rs.getString("SudokuCompletado");
	            dificultad = rs.getString("Dificultad");
	            
			}
			
			//Se crean los arrays bidimensionales (tableros)
			Casilla[][] tableroInicial = new Casilla[9][9];
			Casilla[][] tableroSolucion = new Casilla[9][9];
			
			//Se pasan de string a matriz de ints usando la funcion StringAIntMatriz
			int[][] matrizInicial = stringAIntMatriz(inicialStr);
			int[][] matrizSolucion = stringAIntMatriz(solucionStr);
			
			//Se rellena el tablero inicial
			for (int fila = 0; fila < 9; fila++) {
				for (int col = 0; col < 9; col++) {

					int valor = matrizInicial[fila][col];
					boolean editable = (valor == 0);

					tableroInicial[fila][col] = new Casilla(valor, fila, col, editable);
				}
			}
			
			//Se rellena el tablero con la solucion
			for (int fila = 0; fila < 9; fila++) {
				for (int col = 0; col < 9; col++) {

					int valor = matrizSolucion[fila][col];
					boolean editable = false;

					tableroSolucion[fila][col] = new Casilla(valor, fila, col, editable);
				}
			}

			//Hace return del sudoku(id, los dos tableros y la dificultad)
			return new Sudoku(tableroInicial, tableroSolucion, dificultad, id);
			} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			return null;
		} 
		
	}
	
	//Funcion para pasar de un string de length 81 a una matriz de ints 
	public static int[][] stringAIntMatriz(String string) {
	    
		//Comprobar que no sea null o que no tenga 81 de largo
	    if (string == null || string.length() != 81) {
	        throw new IllegalArgumentException("La cadena de Sudoku debe tener exactamente 81 caracteres.");
	    }
	    
	    int[][] matriz = new int[9][9];
	    
	    //Se rellena la matriz
	    for (int i = 0; i < 81; i++) {
	        int valor = Character.getNumericValue(string.charAt(i));
	        int fila = i / 9;
	        int columna = i % 9;
	        matriz[fila][columna] = valor;
	    }
	    
	    return matriz;
	}
	
	//Funcion para comprobar que una tupla usuario, contraseña existe en la BD
	public static String comprobarCredenciales(String usuario, String contraseña) {
		//Inicialización variables
		int idBD = -1;
		String usuarioBD = "";
		String contraseñaBD = "";
		String rolBD = "ERROR";
		
		//Abrir conexion con la BD usando la clase SQLConnect
		try (Connection conn = SQLConnect.getConnection()) {
			
			Statement stmnt = conn.createStatement();
			
			//Sentencia SQL que utilizamos
			String sql = "SELECT IdUsuario, NombreUsuario, Password, Rol FROM Usuarios WHERE NombreUsuario = '" + usuario + "' AND Password = '" + contraseña + "'";
			
			ResultSet rs = stmnt.executeQuery(sql);
			
			//Devuelve el rol (Jugador o Admin)
			if (rs.next()) {
	            rolBD = rs.getString("Rol");
			}
			
			//Si no existe devuelve ERROR
			} catch (SQLException e) {
			System.out.println("Error: " + e.getMessage());
			return rolBD;
		}
		return rolBD;
	}
	
	
	//Funcion para seleccionar un sudoku de la base de datos a partir de la dificultad elegida por el usuario
	//Funciona exactamente igual que generarSudokuInicial() pero cambia la sentencia SQL (añadimos where)
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

	//Funcion para comprobar que un usuario existe en la BD
	public static boolean existeUsuario(String usuario) {
		//Por defecto ponemos que no existe
		boolean existe=false;
		
		//Abrimos conexion
		try (Connection conn = SQLConnect.getConnection()) {
			
			Statement stmnt = conn.createStatement();
			
			//Sentencia SQL que deberia devolver 0 o 1 si es que hay un usuario
			String sql = "SELECT Count(*) Usuario FROM Usuarios WHERE NombreUsuario = ?";
			
			ResultSet rs = stmnt.executeQuery(sql);
			
			if (rs.next()) {
	            int NumUsuarios = rs.getInt(1);
	           
	            //Si existe el usuario devolvemos true
	            if (NumUsuarios > 0) {
	            	existe = true;
	            }
			}
			
			} catch (SQLException e) {
				System.err.println("Error al comprobar usuario: " + e.getMessage());
		        return false;
		}
		return existe;
	}

	
	//Funcion para añadir sudokus a la base de datos
	public static boolean añadirSudoku(String sudokuInicial, String sudokuFinal, String dificultad) {
		boolean añadido = false;
		
		//Abrimos conexion
		try (Connection conn = SQLConnect.getConnection()) {
	        
			//Sentencia SQL para insertar
	        String sql = "INSERT INTO SudokuNR (SudokuSinCompletar, SudokuCompletado, Dificultad) VALUES (?, ?, ?)";
	        
	        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
	        
	        pst.setString(1, sudokuInicial);
	        pst.setString(2, sudokuFinal);
	        pst.setString(3, dificultad); 
	        
	        int filasAfectadas = pst.executeUpdate();
	        
	        //Si se han cambiado filas ponemos que ha habiado cambios
	        if (filasAfectadas > 0) {
	            añadido = true;
	        }
	        
	    } catch (SQLException e) {
	        System.err.println("Error al registrar usuario: " + e.getMessage());
	        return false;
	    }
	    
	    return añadido;
	}
	
	//Funcion para registrar un usuario a la BD
	public static boolean registrarUsuario(String usuario, String password) {
		boolean registrado = false;
		
			//Se abre la conexion
		    try (Connection conn = SQLConnect.getConnection()) {
		        
		    	//Sentencia para insertar el usuario
		        String sql = "INSERT INTO Usuarios (NombreUsuario, Password, Rol) VALUES (?, ?, ?)";
		        
		        java.sql.PreparedStatement pst = conn.prepareStatement(sql);
		        
		        pst.setString(1, usuario);
		        pst.setString(2, password);
		        pst.setString(3, "JUGADOR"); 
		        
		        int filasAfectadas = pst.executeUpdate();
		        
		        //Si ha habido filas afectadas significa que se ha registrado al usuario
		        if (filasAfectadas > 0) {
		            registrado = true;
		        }
		        
		    } catch (SQLException e) {
		        System.err.println("Error al registrar usuario: " + e.getMessage());
		        return false;
		    }
		    
		    return registrado;
	}
}
