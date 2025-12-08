package gui;
/*<<<<<<< HEAD
=======

>>>>>>> branch 'master' of git@github.com:millaamaiur/sudoku.git*/

import java.awt.Color;
import java.awt.Component;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import gui.VentanaAjustes;
import gui.VentanaLogin;
import javax.swing.JLabel;

public class VentanaAdmin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JButton botonAjustes, botonCrearSudoku, botonSalir;
	private VentanaLogin parent;
	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VentanaAdmin(VentanaLogin parent) {
		this.parent = parent;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(255, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setResizable(false);

		ImageIcon icon = new ImageIcon(getClass().getResource("/gui/logo.png"));
		Image img = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		setIconImage(img);
		
		setContentPane(contentPane);
		contentPane.setLayout(null);
	
	//botones de ventanadmin
		botonAjustes = new JButton("Ajustes");
		botonAjustes.setBackground(new Color(192, 192, 192));
		botonAjustes.setBounds(331, 187, 126, 66);
		contentPane.add(botonAjustes);
		
		
		botonCrearSudoku = new JButton("Crear Sudoku");
		botonCrearSudoku.setBackground(new Color(192, 192, 192));
		botonCrearSudoku.setBounds(331, 302, 126, 74);
		contentPane.add(botonCrearSudoku);
        
        botonSalir = new JButton("Salir");
        botonSalir.setBackground(new Color(192, 192, 192));
        botonSalir.setBounds(331, 413, 126, 79);
        contentPane.add(botonSalir);
       
        
        contentPane.add(botonAjustes);
        contentPane.add(botonCrearSudoku);
        contentPane.add(botonSalir);
        
        JLabel titulo = new JLabel("SUDOKU - ADMIN");
        titulo.setFont(new Font("Tahoma", Font.PLAIN, 17));
        titulo.setBounds(321, 97, 196, 80);
        contentPane.add(titulo);
        
        configurarAcciones();
	}
	
	private void configurarAcciones() {
		botonAjustes.addActionListener(e -> abrirAjustes());      
        botonCrearSudoku.addActionListener(e -> abrirCrearSudoku()); 
        botonSalir.addActionListener(e -> salir());               
    }
	
	
	private void abrirAjustes() {
        // Cierra esta ventana y abre ajustes
        VentanaPartida parti = new VentanaPartida(parent);
        VentanaAjustes ajustes = new VentanaAjustes(parti);
        ajustes.setVisible(true);
        dispose();
    }
	
	
	
	private void abrirCrearSudoku() {
		//necesitemao crear la ventanacrearsudoku para esto
	}
	
	private void salir() {
		dispose();
		VentanaLogin login = new VentanaLogin();
		login.setVisible(true);
	}
}
