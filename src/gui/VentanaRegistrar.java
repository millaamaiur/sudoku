package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import sudokuBDFunciones.FuncionesSudoku;

import javax.swing.JTextField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class VentanaRegistrar extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private VentanaLogin parent;
	private JTextField txtUsuario;
	private JPasswordField passwordField;


	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 */
	public VentanaRegistrar(VentanaLogin parent) {
		this.parent = parent;
		
		ImageIcon icon = new ImageIcon(getClass().getResource("/gui/logo.png"));
		Image img = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		setIconImage(img);
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		//Titulo Registrar
		JLabel lblTitulo = new JLabel("REGISTRATE");
		lblTitulo.setBounds(180, 50, 150, 30);
		lblTitulo.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
		contentPane.add(lblTitulo);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(220, 117, 120, 25);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		//Label Usuario
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(150, 120, 60, 13);
		contentPane.add(lblUsuario);
		
		//Label contraseña
		JLabel lblContrasenya = new JLabel("Contraseña");
		lblContrasenya.setBounds(150, 160, 70, 13);
		contentPane.add(lblContrasenya);
		
		//Boton para volver a ventana login
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setVisible(true);
				dispose();
			}
		});
		btnVolver.setBounds(125, 228, 85, 21);
		contentPane.add(btnVolver);
		
		//Boton para registrase
		JButton btnRegistrarse = new JButton("Registrarse");
		btnRegistrarse.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
								
				String usuario = txtUsuario.getText().trim(); 
		        String password = String.valueOf(passwordField.getPassword());
				
		        
		        //CONDICIONES PARA QUE LA CONTRASEÑA Y EL USUARIO SEAN VALIDOS
				if (usuario.isEmpty() || password.isEmpty()) {
					
					JOptionPane.showMessageDialog(null, "Por favor rellena los datos", "Campos vacios", JOptionPane.WARNING_MESSAGE);
					return;
				}
				
				if (Character.isDigit(usuario.charAt(0))) {
					JOptionPane.showMessageDialog(null, 
			                "El nombre de usuario no puede empezar por un número.", 
			                "Formato incorrecto", 
			                JOptionPane.WARNING_MESSAGE);
			            return;
				}
				
				if (password.length() < 8) {
					JOptionPane.showMessageDialog(null, 
			                "La contraseña debe tener al menos 8 caracteres.", 
			                "Contraseña insegura", 
			                JOptionPane.WARNING_MESSAGE);
			            return;
				}
				
				if (FuncionesSudoku.existeUsuario(usuario)) {
		            JOptionPane.showMessageDialog(null, 
		                "El usuario '" + usuario + "' ya esta en uso", 
		                "Usuario Duplicado", 
		                JOptionPane.ERROR_MESSAGE);
		            return;
		        }
				
				//Funcion para registrar el usuario en la BD que devuelve un booleano 
				//para confirmar si el registro se ha efectuado correctamente
				boolean registroExitoso = FuncionesSudoku.registrarUsuario(usuario, password);	
				
				
				if (registroExitoso) {
		            JOptionPane.showMessageDialog(null, 
		                "¡Usuario registrado con éxito!", 
		                "Bienvenido", 
		                JOptionPane.INFORMATION_MESSAGE);
		            
		            txtUsuario.setText("");
		            passwordField.setText("");
		        } else {
		            JOptionPane.showMessageDialog(null, 
		                "Hubo un error técnico al registrarse.", 
		                "Error BBDD", 
		                JOptionPane.ERROR_MESSAGE);
		        }
			}
		});
		btnRegistrarse.setBounds(244, 228, 85, 21);
		contentPane.add(btnRegistrarse);
		
		JLabel lblInformacion = new JLabel("Introduce tus datos");
		lblInformacion.setBounds(190, 90, 110, 13);
		contentPane.add(lblInformacion);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(220, 157, 120, 25);
		contentPane.add(passwordField);
		
		
	}
}
