package gui;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
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

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaAdmin frame = new VentanaAdmin();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public VentanaAdmin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 900, 700);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(128, 255, 255));
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
	
	//botones de ventanadmin
		JButton botonAjustes = new JButton("Ajustes");
		botonAjustes.setBounds(321, 213, 126, 66);
		contentPane.add(botonAjustes);
		
		
		JButton botonCrearSudoku = new JButton("Crear Sudoku");
		botonCrearSudoku.setBounds(321, 315, 126, 74);
		contentPane.add(botonCrearSudoku);
        
        JButton botonSalir = new JButton("Salir");
        botonSalir.setBounds(321, 428, 126, 79);
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
        AbstractButton botonAjustes = null;
		botonAjustes.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirAjustes();
            }
        });
        
        AbstractButton botonCrearSudoku = null;
		botonCrearSudoku.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                abrirCrearSudoku();
            }
        });
        AbstractButton botonSalir = null;
		botonSalir.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                salir();
            }
        });
     
} 
	
	
	private void abrirAjustes() {
        // Cierra esta ventana y abre ajustes
       /* dispose();
        VentanaAjustes ajustes = new VentanaAjustes();
        ajustes.setVisible(true);*/
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
