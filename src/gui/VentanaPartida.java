package gui;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;

import clases.ControladorTimer;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

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

		//Panel de arriba (Dificultad y tiempo)
		//Aqui se crea el panel y los labels de dificultad y tiempo
		JPanel panelSuperior = new JPanel(new BorderLayout());
		lblDificultad = new JLabel("Dificultad: Normal");
		lblDificultad.setFont(new Font("Segoe UI", Font.BOLD, 16));
		lblTiempo = new JLabel("00:00");
		lblTiempo.setFont(new Font("Segoe UI", Font.BOLD, 16));
		JPanel panelSuperCentral = new JPanel(new FlowLayout());

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

		// ----- PANEL CENTRAL (TABLERO) -----
		panelTablero = new JPanel(new GridLayout(9, 9, 2, 2));
		panelTablero.setBackground(new Color(192, 192, 192));

		//Con este bucle for generamos las casillas. ES SOLO VISUAL
		for (int i = 0; i < 81; i++) {
			JTextField celda = new JTextField();
			celda.setHorizontalAlignment(JTextField.CENTER);
			celda.setFont(new Font("Segoe UI", Font.PLAIN, 18));
			panelTablero.add(celda);
		}

		contentPane.add(panelTablero, BorderLayout.CENTER);

		//Panel de abajo (Botones)
		//Aqui se crea el panel y todos los botones
		panelControles = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));

		JButton btnComprobar = new JButton("Comprobar");
		JButton btnReiniciar = new JButton("Reiniciar");
		JButton btnResolver = new JButton("Resolver");
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
		
		// ----- BOTON RESET TEMP -----
		btnReiniciarTemp.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				timer.reset();
			}
		});
	}
}
