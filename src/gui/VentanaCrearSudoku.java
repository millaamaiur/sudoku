package gui;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import conexion.SQLConnect;
import sudokuBDFunciones.FuncionesSudoku;

import javax.swing.ImageIcon;
import javax.swing.JButton;

public class VentanaCrearSudoku extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VentanaAdmin parent;
	private JPanel panelTablero;
	private JPanel panelControles;
	private JLabel lblDificultad;
	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public VentanaCrearSudoku(VentanaAdmin parent) {
		this.parent = parent;
		setTitle("Sudoku - Crear Sudoku");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/gui/logo.png"));
		Image img = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		setIconImage(img);

		contentPane = new JPanel(new BorderLayout(10, 10));
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);

		// ----- PANEL SUPERIOR -----
		JPanel panelSuperior = new JPanel(new BorderLayout());
		lblDificultad = new JLabel("Dificultad: Normal");
		lblDificultad.setFont(new Font("Segoe UI", Font.BOLD, 16));
		
		panelSuperior.add(lblDificultad, BorderLayout.WEST);
		contentPane.add(panelSuperior, BorderLayout.NORTH);

		// ----- PANEL CENTRAL (TABLERO) -----
		panelTablero = new JPanel(new GridLayout(9, 9, 2, 2));
		panelTablero.setBackground(new Color(192, 192, 192));

		// Generar casillas vacías - SOLO NÚMEROS 1-9 pero sin dejar q pongan caracteres
		for (int i = 0; i < 81; i++) {
		    JTextField celda = new JTextField();
		    celda.setHorizontalAlignment(JTextField.CENTER);
		    celda.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		    
		    celda.addKeyListener(new KeyAdapter() {
		        @Override
		        public void keyTyped(KeyEvent e) {
		            char c = e.getKeyChar();
		            JTextField source = (JTextField) e.getSource();
		            // Solo permitir los numeros del 1 al 9
		            if (!(c >= '1' && c <= '9') || source.getText().length() >= 1) {
		                e.consume(); // Bloquear el caracteres 
		            }
		        }
		    });
		    
		    panelTablero.add(celda);
		}

		contentPane.add(panelTablero, BorderLayout.CENTER);

		// ----- PANEL INFERIOR (BOTONES) -----
		panelControles = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

		JButton btnGuardar= new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				guardarSudoku();
			}
			
		});
		JButton btnVolver = new JButton("Volver");
	
		panelControles.add(btnGuardar);
		panelControles.add(btnVolver);

		contentPane.add(panelControles, BorderLayout.SOUTH);

		// ----- EVENTO VOLVER -----
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setVisible(true);
				dispose();
			}
		});
		
		
		
	}
	private void guardarSudoku() {
	    int[][] tablero = new int[9][9];
	    Component[] celdas = panelTablero.getComponents();
	    
	    for (int fila = 0; fila < 9; fila++) {
	        for (int col = 0; col < 9; col++) {
	            JTextField tf = (JTextField) celdas[fila * 9 + col];
	            String texto = tf.getText();
	            if (texto.isEmpty()) {
	                tablero[fila][col] = 0;
	            } else {
	                try {
	                    tablero[fila][col] = Integer.parseInt(texto);
	                } catch (NumberFormatException ex) {
	                    tablero[fila][col] = 0;
	                }
	            }
	        }
	    }
	    
	    if (!esSudokuValido(tablero)) {
	        JOptionPane.showMessageDialog(this,
	            "El sudoku no es válido.\n" +
	            "Por favor, revisa que no haya números repetidos en filas, columnas o regiones.",
	            "Error",
	            JOptionPane.ERROR_MESSAGE);
	        return;
	    }
	    
	    try {
	        // Usar FuncionesSudoku para guardar
	        FuncionesSudoku.guardarSudoku(tablero, "Normal", "admin");
	        JOptionPane.showMessageDialog(this,
	            "✅ Sudoku guardado exitosamente",
	            "Guardado",
	            JOptionPane.INFORMATION_MESSAGE);
	        dispose();
	        parent.setVisible(true);
	    } catch (Exception ex) {
	        JOptionPane.showMessageDialog(this,
	            "❌ Error al guardar: " + ex.getMessage(),
	            "Error",
	            JOptionPane.ERROR_MESSAGE);
	    }
	}

	private boolean esSudokuValido(int[][] tablero) {
	    // Validación básica - implementar lógica real
	    for (int i = 0; i < 9; i++) {
	        for (int j = 0; j < 9; j++) {
	            int valor = tablero[i][j];
	            if (valor != 0 && (valor < 1 || valor > 9)) {
	                return false;
	            }
	        }
	    }
	    return true;
	}
	    
}
/*ublic static boolean guardarSudoku(int[][] tablero, String dificultad, String creador) {


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
}*/