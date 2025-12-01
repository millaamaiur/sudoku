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
	
	public static boolean guardarSudoku(int[][] tablero, String dificultad, String creador) {


	if (tablero == null || tablero.length != 9 || tablero[0].length != 9) {
	    System.err.println("Error: Tablero inválido");
	    return false;
	}

	//Validar que el sudoku sea resoluble
	if (!esSudokuValido(tablero)) {
	    System.err.println("Error: Sudoku no válido para guardar");
	    return false;
	}

	StringBuilder sbInicial = new StringBuilder();
	StringBuilder sbSolucion = new StringBuilder();

	int[][] copiaTablero = new int[9][9];
	for (int i = 0; i < 9; i++) {
	    System.arraycopy(tablero[i], 0, copiaTablero[i], 0, 9);
	}

	// Intentar resolver el sudoku (necesitas un solucionador)
	if (!resolverSudoku(copiaTablero)) {
	    System.err.println("Error: Sudoku no resoluble");
	    return false;
	}

	for (int fila = 0; fila < 9; fila++) {
	    for (int col = 0; col < 9; col++) {
	        sbInicial.append(tablero[fila][col]);     // Tablero inicial (con ceros)
	        sbSolucion.append(copiaTablero[fila][col]); // Tablero resuelto
	    }
	}

	String inicialStr = sbInicial.toString();
	String solucionStr = sbSolucion.toString();

	//Obtener ID del creador
	int idCreador = obtenerIdUsuario(creador);
	if (idCreador == -1) {
	    System.err.println("Error: Usuario creador no encontrado");
	    return false;
	}

	//Insertar en la base de datos
	try (Connection conn = SQLConnect.getConnection()) {
	    String sql = "INSERT INTO SudokuNR (SudokuSinCompletar, SudokuCompletado, Dificultad, IdUsuario) " +
	                 "VALUES (?, ?, ?, ?)";
	    
	    java.sql.PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, inicialStr);
	    pstmt.setString(2, solucionStr);
	    pstmt.setString(3, dificultad);
	    pstmt.setInt(4, idCreador);
	    
	    int filasAfectadas = pstmt.executeUpdate();
	    
	    if (filasAfectadas > 0) {
	        System.out.println("✅ Sudoku guardado exitosamente en la BD");
	        return true;
	    } else {
	        System.err.println("Error: No se pudo guardar el sudoku");
	        return false;
	    }
	    
	} catch (SQLException e) {
	    System.err.println("Error SQL al guardar sudoku: " + e.getMessage());
	    return false;
	}
	}

	// Método para validar sudoku
	private static boolean esSudokuValido(int[][] tablero) {
	// Verificar que solo haya números 0-9
	for (int i = 0; i < 9; i++) {
	    for (int j = 0; j < 9; j++) {
	        int valor = tablero[i][j];
	        if (valor < 0 || valor > 9) {
	            return false;
	        }
	    }
	}

	// Verificar que no haya números repetidos en filas, columnas o regiones
	for (int i = 0; i < 9; i++) {
	    for (int j = 0; j < 9; j++) {
	        int valor = tablero[i][j];
	        if (valor != 0) {
	            // Verificar fila
	            for (int k = 0; k < 9; k++) {
	                if (k != j && tablero[i][k] == valor) {
	                    return false;
	                }
	            }
	            // Verificar columna
	            for (int k = 0; k < 9; k++) {
	                if (k != i && tablero[k][j] == valor) {
	                    return false;
	                }
	            }
	            // Verificar región 3x3
	            int regionFila = (i / 3) * 3;
	            int regionCol = (j / 3) * 3;
	            for (int k = regionFila; k < regionFila + 3; k++) {
	                for (int l = regionCol; l < regionCol + 3; l++) {
	                    if ((k != i || l != j) && tablero[k][l] == valor) {
	                        return false;
	                    }
	                }
	            }
	        }
	    }
	}
	return true;
	}

	// Método para resolver sudoku (necesario para obtener la solución)
	private static boolean resolverSudoku(int[][] tablero) {
	for (int fila = 0; fila < 9; fila++) {
	    for (int col = 0; col < 9; col++) {
	        if (tablero[fila][col] == 0) {
	            for (int num = 1; num <= 9; num++) {
	                if (esNumeroValido(tablero, fila, col, num)) {
	                    tablero[fila][col] = num;
	                    if (resolverSudoku(tablero)) {
	                        return true;
	                    }
	                    tablero[fila][col] = 0; // backtrack
	                }
	            }
	            return false;
	        }
	    }
	}
	return true;
	}

	private static boolean esNumeroValido(int[][] tablero, int fila, int col, int num) {
	// Verificar fila
	for (int j = 0; j < 9; j++) {
	    if (tablero[fila][j] == num) {
	        return false;
	    }
	}

	// Verificar columna
	for (int i = 0; i < 9; i++) {
	    if (tablero[i][col] == num) {
	        return false;
	    }
	}

	// Verificar región 3x3
	int regionFila = (fila / 3) * 3;
	int regionCol = (col / 3) * 3;
	for (int i = regionFila; i < regionFila + 3; i++) {
	    for (int j = regionCol; j < regionCol + 3; j++) {
	        if (tablero[i][j] == num) {
	            return false;
	        }
	    }
	}

	return true;
	}

	// Método para obtener ID de usuario
	private static int obtenerIdUsuario(String nombreUsuario) {
	try (Connection conn = SQLConnect.getConnection()) {
	    String sql = "SELECT IdUsuario FROM Usuarios WHERE NombreUsuario = ?";
	    java.sql.PreparedStatement pstmt = conn.prepareStatement(sql);
	    pstmt.setString(1, nombreUsuario);
	    
	    ResultSet rs = pstmt.executeQuery();
	    if (rs.next()) {
	        return rs.getInt("IdUsuario");
	    }
	} catch (SQLException e) {
	    System.err.println("Error obteniendo ID de usuario: " + e.getMessage());
	}
	return -1;
	}

	
}
	
