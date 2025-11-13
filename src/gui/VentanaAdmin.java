package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAdmin extends JFrame {
	//Atributos de la ventana admin
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VentanaLogin parent;

	/**
	 * Create the frame.
	 */
	public VentanaAdmin(VentanaLogin parent) {
		this.parent = parent;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnCrearSudoku = new JButton("Crear Sudoku");
		btnCrearSudoku.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentanaCrearSudoku ventana = new VentanaCrearSudoku(VentanaAdmin.this);
				ventana.setVisible(true);
				dispose();
			}
		});
		btnCrearSudoku.setBounds(170, 223, 110, 21);
		contentPane.add(btnCrearSudoku);
		
		// ----- BOTON VOLVER -----
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				VentanaLogin ventana = new VentanaLogin();
				ventana.setVisible(true);
				dispose();
				
				
			}
		});
		btnSalir.setBounds(179, 336, 85, 21);
		contentPane.add(btnSalir);
	}

}
