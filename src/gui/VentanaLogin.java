package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaLogin extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField passwordField;
	private String usuarioTests = "usuario";
	private String passTests = "password";
	private JButton btnLogin;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					VentanaLogin frame = new VentanaLogin();
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
	public VentanaLogin() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(176, 131, 45, 13);
		contentPane.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String user = txtUsuario.getText();
				String pass= String.copyValueOf(passwordField.getPassword());
			    if(user.isEmpty() || pass.isEmpty()) {
			    	btnLogin.setEnabled(false);
			    }else {
			    	btnLogin.setEnabled(true);
			    }
		    }
			});
		txtUsuario.setBounds(231, 127, 96, 19);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		JLabel lblContrasenya = new JLabel("Contraseña");
		lblContrasenya.setBounds(155, 162, 66, 13);
		contentPane.add(lblContrasenya);
		
		passwordField = new JPasswordField();
		passwordField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyReleased(KeyEvent e) {
				String user = txtUsuario.getText();
				String pass= String.copyValueOf(passwordField.getPassword());
			    if(user.isEmpty() || pass.isEmpty()) {
			    	btnLogin.setEnabled(false);
			    }else {
			    	btnLogin.setEnabled(true);
			    }
			}
		});
		passwordField.setBounds(231, 158, 96, 19);
		contentPane.add(passwordField);
		
		btnLogin = new JButton("Iniciar sesion");
		btnLogin.setEnabled(false);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String usuarioInput = txtUsuario.getText();
				String contrasenyaInput = String.copyValueOf(passwordField.getPassword());
				
				if (comprobarContrasenya(usuarioInput, contrasenyaInput)) {
					
					VentanaPartida ventana = new VentanaPartida(VentanaLogin.this);
					ventana.setVisible(true);
					VentanaLogin.this.setVisible(false);
				}else {
					JOptionPane.showMessageDialog(VentanaLogin.this, "Error, usuario o contraseña incorrectos");
				}
				
				//Los usuarios donde los vamos a crear??
				
			}
		});
		btnLogin.setBounds(190, 203, 115, 21);
		contentPane.add(btnLogin);
	}
	
	public Boolean comprobarContrasenya(String usuario, String contraseña) {
		return (usuario.equals(usuarioTests) && contraseña.equals(passTests));
	}
}
