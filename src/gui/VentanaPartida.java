package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class VentanaPartida extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VentanaLogin parent;

	// Componentes principales
	private JPanel panelTablero;
	private JPanel panelControles;
	private JLabel lblTiempo;
	private JLabel lblDificultad;

	public VentanaPartida(VentanaLogin parent) {
		this.parent = parent;
		setTitle("Sudoku - Partida");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		setLocationRelativeTo(null);
		setResizable(false);

		contentPane = new JPanel(new BorderLayout(10, 10));
		contentPane.setBorder(new EmptyBorder(10, 10, 10, 10));
		setContentPane(contentPane);

		// ----- PANEL SUPERIOR -----
		JPanel panelSuperior = new JPanel(new BorderLayout());
		lblDificultad = new JLabel("Dificultad: Normal");
		lblDificultad.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblTiempo = new JLabel("Tiempo: 00:00");
		lblTiempo.setFont(new Font("Segoe UI", Font.BOLD, 16));

		panelSuperior.add(lblDificultad, BorderLayout.WEST);
		panelSuperior.add(lblTiempo, BorderLayout.EAST);
		contentPane.add(panelSuperior, BorderLayout.NORTH);

		// ----- PANEL CENTRAL (TABLERO) -----
		panelTablero = new JPanel(new GridLayout(9, 9, 2, 2));
		panelTablero.setBackground(new Color(192, 192, 192));

		// Generar casillas vacías (ejemplo)
		for (int i = 0; i < 81; i++) {
			JTextField celda = new JTextField();
			celda.setHorizontalAlignment(JTextField.CENTER);
			celda.setFont(new Font("Segoe UI", Font.PLAIN, 18));
			panelTablero.add(celda);
		}

		contentPane.add(panelTablero, BorderLayout.CENTER);

		// ----- PANEL INFERIOR (BOTONES) -----
		panelControles = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

		JButton btnComprobar = new JButton("Comprobar");
		JButton btnReiniciar = new JButton("Reiniciar");
		JButton btnResolver = new JButton("Resolver");
		JButton btnAjustes = new JButton("Ajustes");
		//Pasar a los ajustes
		btnAjustes.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				VentanaAjustes ventana = new VentanaAjustes(VentanaPartida.this);
				ventana.setVisible(true);
				VentanaPartida.this.setVisible(false);
			}
		});
		JButton btnVolver = new JButton("Volver");
		

		panelControles.add(btnComprobar);
		panelControles.add(btnReiniciar);
		panelControles.add(btnResolver);
		panelControles.add(btnAjustes);
		panelControles.add(btnVolver);

		contentPane.add(panelControles, BorderLayout.SOUTH);

		// ----- EVENTO VOLVER -----
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setVisible(true);
				dispose();
			}
		});
		// Aquí podrías añadir los listeners de cada botón (temporizador, comprobar, etc.)
	}
}
