package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

public class VentanaAdmin extends JFrame {

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
		
		JButton btnAjustes = new JButton("Ajustes");
		btnAjustes.setBounds(179, 117, 85, 21);
		contentPane.add(btnAjustes);
		
		JButton btnCrearSudoku = new JButton("Crear Sudoku");
		btnCrearSudoku.setBounds(170, 223, 110, 21);
		contentPane.add(btnCrearSudoku);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.setBounds(179, 336, 85, 21);
		contentPane.add(btnSalir);
	}

}
