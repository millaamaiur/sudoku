package gui;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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

import sudokuBDFunciones.FuncionesSudoku;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;

public class VentanaCrearSudoku extends JFrame {
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VentanaLogin parent;
	private JPanel panelTablero;
	private JPanel panelControles;
	private JLabel lblDificultad;
	private JComboBox comboBox;
	private JButton btnGuardar;
	private Boolean faseUno;
	private String sudokuInicial;

	public VentanaCrearSudoku(VentanaLogin parent) {
		this.parent = parent;
		setTitle("Sudoku - Crear Sudoku");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		
		faseUno = true;
		ImageIcon icon = new ImageIcon(getClass().getResource("/gui/logo.png"));
		Image img = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		setIconImage(img);

		contentPane = new JPanel(new BorderLayout(10, 10));
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);

		// ----- PANEL SUPERIOR -----
		JPanel panelSuperior = new JPanel();
		panelSuperior.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		lblDificultad = new JLabel("Dificultad:");
		lblDificultad.setFont(new Font("Segoe UI", Font.BOLD, 16));
		
		
		panelSuperior.add(lblDificultad);
		contentPane.add(panelSuperior, BorderLayout.NORTH);
		
		comboBox = new JComboBox();
		comboBox.setModel(new DefaultComboBoxModel(new String[] {"Facil", "Normal", "Dificil"}));
		panelSuperior.add(comboBox);

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
		            
		            if (c == KeyEvent.VK_BACK_SPACE || c == KeyEvent.VK_DELETE) {
		                return; 
		            }
		            // Solo permitir numeros del 1 al 9
		            if (!(c >= '1' && c <= '9') || source.getText().length() >= 1) {
		                e.consume(); // Si no es un numero o ya hay un numero en la casilla el consume lo que hace es no escribir en la casilla
		            }
		        }
		    });
		    
		    int top = 1, left = 1, bottom = 1, right = 1; // por defecto todos los bordes a 1 de grosor

		    // Líneas horizontales gruesas. Si cumple alguna condición de las de abajo se cambia el grosor a 5
		    if (i < 9) top = 5;
		    if (i >= 27 && i < 36) top = 5;
		    if (i >= 54 && i < 63) top = 5;
		    if (i >= 72) bottom = 5;

		    // Líneas verticales gruesas. Si cumple alguna condición de las de abajo se cambia el grosor a 5
		    if (i % 9 == 0) left = 5;
		    if (i % 9 == 3) left = 5;
		    if (i % 9 == 6) left = 5;
		    if (i % 9 == 8) right = 5;

		    celda.setBorder(BorderFactory.createMatteBorder(top, left, bottom, right, Color.BLACK));
		    panelTablero.add(celda);
		}

		contentPane.add(panelTablero, BorderLayout.CENTER);

		// ----- PANEL INFERIOR (BOTONES) -----
		panelControles = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

		
		//El boton guardar hace cosas distintas dependiendo si estamos en la fase UNO o DOS de la creacion del sudoku
		btnGuardar= new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (faseUno) {
					sudokuInicial = guardarEstadoTablero();
					faseUno = false;
					
					transicionAFaseDos();
					JOptionPane.showMessageDialog(null, "Pistas guardadas. Ahora, rellena la solución completa para este Sudoku.");
				} else {
					String sudokuFinal = guardarEstadoTablero();
					String dificultad = (String) comboBox.getSelectedItem();
					
					boolean exito = FuncionesSudoku.añadirSudoku(sudokuInicial, sudokuFinal, dificultad);
					
					if (exito) {
						JOptionPane.showMessageDialog(null, "Sudoku añadido con exito");
					} else {
						JOptionPane.showMessageDialog(null, "No se ha podido añadir el sudoku");
					}
				}
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
	
	//Funcion que pasa el estado actual del tablero a una secuencia de digitios que podemos introducir a la base de datos
	public String guardarEstadoTablero() {
		String secuenciaDigitos = "";
		
		Component[] componentes = panelTablero.getComponents();
		
		for (int i = 0; i < componentes.length; i++) {
			if (componentes[i] instanceof JTextField) {
				JTextField celda = (JTextField) componentes[i];
				String texto = celda.getText();
				
				if (texto.isEmpty()) {
					secuenciaDigitos = secuenciaDigitos + "0";
				} else {
					secuenciaDigitos = secuenciaDigitos + texto;
				}
			}
		}
		return secuenciaDigitos;
	}
	
	//Funcion para pasar de la primera parte de la creacion del sudoku a la segunda
	//En la primera se piden solo las casillas iniciales y en la segunda fase se piden todas las casillas (Solucion)
	public void transicionAFaseDos() {
		Component[] componentes = panelTablero.getComponents();
		
		for (int i = 0; i < componentes.length; i++) {
	        if (componentes[i] instanceof JTextField) {
	            JTextField celda = (JTextField) componentes[i];
	            celda.setText(""); 
	            celda.setEditable(true); 
	            celda.setBackground(Color.WHITE); 
	            celda.setForeground(Color.BLACK);
	        }
	    }
		btnGuardar.setText("Finalizar Sudoku");
	}
}

