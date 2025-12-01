package gui;

import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import clases.Casilla;
import clases.ControladorTimer;
import clases.Sudoku;
import sudokuBDFunciones.FuncionesSudoku;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import sudokuBDFunciones.FuncionesSudoku;

public class VentanaPartida extends JFrame {

	//Atributos de la ventana partida 
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VentanaLogin parent;
	private JPanel panelTablero;
	private JPanel panelControles;
	private JLabel lblTiempo;
	private JLabel lblDificultad;
	private JButton btnReiniciarTemp;
	private JButton btnPararTemp;
	private JButton btnIniciarTemp;
	private JPanel panelSuperCentral;
	private JPanel panelSuperior;
	
	
	public VentanaPartida(VentanaLogin parent) {
		this.parent = parent;
		setTitle("Sudoku - Partida");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setBounds(100, 100, 900, 700);
		setLocationRelativeTo(null);
		setResizable(false);
		
		Sudoku sudoku = FuncionesSudoku.generarSudokuInicial();
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/gui/logo.png"));
		Image img = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		setIconImage(img);

		contentPane = new JPanel(new BorderLayout(10, 10));
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);

		//Panel de arriba (Dificultad y tiempo)
		//Aqui se crea el panel y los labels de dificultad y tiempo
		panelSuperior = new JPanel(new BorderLayout());
		lblDificultad = new JLabel("Dificultad: " + sudoku.getDificultad());
		lblDificultad.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblTiempo = new JLabel("00:00"); 
		lblTiempo.setFont(new Font("Segoe UI", Font.BOLD, 16));
		panelSuperCentral = new JPanel(new FlowLayout());

		//Aqui se añaden los labels al panel y el panel al borderLayout (NORTE)
		panelSuperior.add(lblDificultad, BorderLayout.WEST);
		panelSuperior.add(lblTiempo, BorderLayout.EAST);
		panelSuperior.add(panelSuperCentral, BorderLayout.CENTER);
		
		btnIniciarTemp = new JButton("Iniciar");
		panelSuperCentral.add(btnIniciarTemp);
		
		btnPararTemp = new JButton("Parar");
		panelSuperCentral.add(btnPararTemp);
		
		btnReiniciarTemp = new JButton("Reiniciar");
		panelSuperCentral.add(btnReiniciarTemp);
		contentPane.add(panelSuperior, BorderLayout.NORTH);	
		
		// ----- PANEL CENTRAL (TABLERO) ----- con las letras bloqueadas
		panelTablero = new JPanel(new GridLayout(9, 9, 2, 2));
		panelTablero.setBackground(new Color(192, 192, 192));

		//Con este bucle for generamos las casillas. SOLO NÚMEROS 1-9
		for (int i = 0; i < 81; i++) {
		    JTextField celda = new JTextField();
		    celda.setHorizontalAlignment(JTextField.CENTER);
		    celda.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		    
		    celda.setCaret(new javax.swing.text.DefaultCaret() {
				private static final long serialVersionUID = 1L;

				@Override
		        public void setVisible(boolean v) {
		            super.setVisible(false); // Siempre lo mantiene invisible
		        }
		    });
		    
		    celda.addKeyListener(new KeyAdapter() {
		        @Override
		        public void keyTyped(KeyEvent e) {
		            char c = e.getKeyChar();
		            JTextField source = (JTextField) e.getSource();
		            // Solo permitir numeros del 1 al 9
		            if (!(c >= '1' && c <= '9') || source.getText().length() >= 1) {
		                e.consume(); // Si no es un numero o ya hay un numero en la casilla el consume lo que hace es no escribir en la casilla
		            }
		        }
		    });
		    
		    /*celda.addMouseListener(new MouseAdapter() {
		    	public void mouseClicked(MouseEvent e) {
		    		celda.setBackground(Color.blue);
		    	}
		    });*/
		    
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

		//---------------------------------------------------------------
		// Cargar el sudoku de prueba EN LAS CELDAS YA CREADAS
		
		//---------------------------------------------------------------
		crearSudoku(sudoku);
		
		//Panel de abajo (Botones)
		//Aqui se crea el panel y todos los botones
		panelControles = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

		JButton btnComprobar = new JButton("Comprobar");
		btnComprobar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
			}
		});
		JButton btnReiniciar = new JButton("Reiniciar");
		btnReiniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				   
		        
		        Casilla[][] casillas = sudoku.getTablero();
		        Component[] celdas = panelTablero.getComponents();

		        for (int fila = 0; fila < 9; fila++) {
		            for (int col = 0; col < 9; col++) {

		                JTextField tx = (JTextField) celdas[fila * 9 + col];
		                Casilla casilla = casillas[fila][col];
		                int valor = casilla.getValor();

		                if (valor != 0) {
		                    tx.setText(String.valueOf(valor));
		                    tx.setEditable(false);
		                    tx.setBackground(new Color(220, 220, 220)); 
		                    tx.setFont(new Font("Segoe UI", Font.BOLD, 18));
		                } else {
		                    tx.setText("");
		                    tx.setEditable(true);
		                    tx.setBackground(Color.WHITE); 
		                    tx.setFont(new Font("Segoe UI", Font.PLAIN, 18));
		                }
		            }
		        }

		        JOptionPane.showMessageDialog(null, "¡El tablero ha sido reiniciado!");
			}
		});
		JButton btnResolver = new JButton("Resolver");
		btnResolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Casilla[][] casillas = sudoku.getSolucion();
				Component[] celdas = panelTablero.getComponents(); //esta linea lo que hace es coger cada uno de los textFields que hemos creado arriba y meterlo a un array

				for (int fila = 0; fila < 9; fila++) {
				    for (int col = 0; col < 9; col++) {
				        
				        JTextField tf = (JTextField) celdas[fila * 9 + col];
				        Casilla casilla = casillas[fila][col];

				        int valor = casilla.getValor();

				        if (valor != 0) {
				            tf.setText(String.valueOf(valor));
				            tf.setEditable(false);
				            tf.setBackground(new Color(220, 220, 220));
				            tf.setFont(new Font("Segoe UI", Font.BOLD, 18));
				        } else {
				            tf.setText("");
				            tf.setEditable(true);
				        }
				    }
				}
			}
		});
		JButton btnAjustes = new JButton("Ajustes");
		JButton btnVolver = new JButton("Volver");
		

		//Aqui se añaden los botones al panel y el panel al borderLayout(SUR)
		panelControles.add(btnComprobar);
		panelControles.add(btnReiniciar);
		panelControles.add(btnResolver);
		panelControles.add(btnAjustes);
		panelControles.add(btnVolver);
		contentPane.add(panelControles, BorderLayout.SOUTH);

		
		ControladorTimer timer = new ControladorTimer(0, false, lblTiempo);

		//ACCIONES DE CADA BOTÓN
		// ----- BOTON VOLVER -----
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setVisible(true);
				dispose();
			}
		});
		// ----- BOTON AJUSTES -----
		btnAjustes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaAjustes ventana = new VentanaAjustes(VentanaPartida.this);
				ventana.setVisible(true);
				VentanaPartida.this.setVisible(false);
			}
		});
		// ----- BOTON INICIAR TEMP -----
		btnIniciarTemp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.start();
			}
		});
		// ----- BOTON PARAR TEMP -----
		btnPararTemp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.stop();
			}
		});
		// ----- BOTON REINICIAR TEMP -----
		btnReiniciarTemp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.reset();
			}
		});
	}

	public void guardarAjustes(String dificultad, int volumen, Color colorFondo) {
		// TODO Auto-generated method stub
		lblDificultad.setText("Dificultad: " + dificultad);
		 this.getContentPane().setBackground(colorFondo);
         this.getPanelTablero().setBackground(colorFondo);
         this.getPanelControles().setBackground(colorFondo);
         this.getPanelSuperCentral().setBackground(colorFondo);
         this.getPanelSuperior().setBackground(colorFondo);
	}
	
	public JPanel getPanelTablero() {
	    return panelTablero;
	}

	public JPanel getPanelControles() {
	    return panelControles;
	}

	public JPanel getPanelSuperCentral() {
		return panelSuperCentral;
	}

	public void setPanelSuperCentral(JPanel panelSuperCentral) {
		this.panelSuperCentral = panelSuperCentral;
	}

	public JPanel getPanelSuperior() {
		return panelSuperior;
	}

	public void setPanelSuperior(JPanel panelSuperior) {
		this.panelSuperior = panelSuperior;
	}
	public void crearSudoku(Sudoku sudoku) {
		Casilla[][] casillas = sudoku.getTablero();
		Component[] celdas = panelTablero.getComponents(); //esta linea lo que hace es coger cada uno de los textFields que hemos creado arriba y meterlo a un array

		for (int fila = 0; fila < 9; fila++) {
		    for (int col = 0; col < 9; col++) {
		        
		        JTextField tf = (JTextField) celdas[fila * 9 + col];
		        Casilla casilla = casillas[fila][col];

		        int valor = casilla.getValor();

		        if (valor != 0) {
		            tf.setText(String.valueOf(valor));
		            tf.setEditable(false);
		            tf.setBackground(new Color(220, 220, 220));
		            tf.setFont(new Font("Segoe UI", Font.BOLD, 18));
		        } else {
		            tf.setText("");
		            tf.setEditable(true);
		        }
		    }
		}
	}
	
}
/*



*/
