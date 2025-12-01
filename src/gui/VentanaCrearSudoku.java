package gui;

import java.awt.BorderLayout;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
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
}

