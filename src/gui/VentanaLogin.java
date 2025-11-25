package gui;

import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class VentanaLogin extends JFrame {
	//Atributos de la ventana login. Es la ventana padre (Por eso tiene main) 
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsuario;
	private JPasswordField passwordField;
	private String usuarioTests = "usuario";
	private String passTests = "password";
	private JButton btnLogin;
	
	//Main de la ventanaLogin
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
	
	public VentanaLogin() {
		//Crear content pane (Por defecto)
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		ImageIcon icon = new ImageIcon(getClass().getResource("/gui/logo.png"));
		Image img = icon.getImage().getScaledInstance(32, 32, Image.SCALE_SMOOTH);
		setIconImage(img);
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);
		
		
		//titulo de ventana
		JLabel lblTitulo = new JLabel("SUDOKU GAME");
		lblTitulo.setBounds(180, 50, 150, 30);
		lblTitulo.setFont(new java.awt.Font("Arial", Font.BOLD, 18));
		contentPane.add(lblTitulo);
		
		
		//Label y JTextField USUARIO
		JLabel lblUsuario = new JLabel("Usuario");
		lblUsuario.setBounds(150, 120, 60, 13);
		contentPane.add(lblUsuario);
		
		txtUsuario = new JTextField();
		txtUsuario.setBounds(220, 117, 120, 25);
		contentPane.add(txtUsuario);
		txtUsuario.setColumns(10);
		
		//Label y JTextField CONTRASEÑA
		JLabel lblContrasenya = new JLabel("Contraseña");
		lblContrasenya.setBounds(150, 160, 70, 13);
		contentPane.add(lblContrasenya);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(220, 157, 120, 25);
		contentPane.add(passwordField);
		
		
		//KeyListeners de los campos usuario y contraseña
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
		
		
		//ACCIONES DE CADA BOTÓN
		// ----- BOTON LOGIN -----
		btnLogin = new JButton("Iniciar sesion");
		btnLogin.setEnabled(false);
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				String usuarioInput = txtUsuario.getText();
				String contrasenyaInput = String.copyValueOf(passwordField.getPassword());
				
				/*	---- ESTA PARTE NO ES UTIL SI USAMOS EL DIFERENCIADOR DE ADMIN Y USUARIO DEL TIRON (lo que hay abajo)
				if (comprobarContrasenya(usuarioInput, contrasenyaInput)) {
					
					VentanaPartida ventana = new VentanaPartida(VentanaLogin.this);
					ventana.setVisible(true);
					VentanaLogin.this.setVisible(false);
				}else {
					JOptionPane.showMessageDialog(VentanaLogin.this, "Error, usuario o contraseña incorrectos");
				}
				
				//Los usuarios hay que crearlos en la base de datos pero de manera provisional:
				//usuario: usuario
				//contraseña: password
				*/
				String rol = comprobarCredenciales(usuarioInput, contrasenyaInput);//Los usuarios hay que crearlos en la base de datos pero de manera provisional:
				if (!rol.equals("ERROR")) {
				    if (rol.equals("ADMIN")) {
				        VentanaAdmin admin = new VentanaAdmin(VentanaLogin.this);//aqui depende de cual sea te abre una ventana o otra
				        txtUsuario.setText("");
						passwordField.setText("");
				        admin.setVisible(true);
				    } else {
				        VentanaPartida ventana = new VentanaPartida(VentanaLogin.this);
				        txtUsuario.setText("");
						passwordField.setText("");
				        ventana.setVisible(true);
				    }
				    VentanaLogin.this.setVisible(false);
				    //la nueva contraseña es: para usuario, password. Y para el administrador: admin, admin123-------------
				}else {
					JOptionPane.showMessageDialog(null, "Los datos estan mal puestos", "Error", JOptionPane.INFORMATION_MESSAGE);
					txtUsuario.setText("");
					passwordField.setText("");
				}
				
			}
		});
		btnLogin.setBounds(170, 206, 132, 25);
		contentPane.add(btnLogin);
		
		getRootPane().setDefaultButton(btnLogin);

		
		
		//boton para salir de ventanalogin
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				confirmarSalida();
			}
		});
		btnSalir.setBounds(10, 332, 98, 21);
		contentPane.add(btnSalir);
		
	}
	
	/*
	//Función para comprobar que la contraseña y el usuario son correctos
	public Boolean comprobarContrasenya(String usuario, String contraseña) {
		return (usuario.equals(usuarioTests) && contraseña.equals(passTests));
	}// en lugar de esto mejor añadir que se puedan diferenciar usuario y adminin del tiron
	*/
	
	public String comprobarCredenciales(String usuario, String contraseña) {//Los usuarios hay que crearlos en la base de datos pero de manera provisional:
	    if (usuario.equals("admin") && contraseña.equals("admin123")) {//asi que por ahora se quedan estos dos solo
	        return "ADMIN"; //returneo admin y error, para que arriba al hacer el .equial sepa diferenciarlos bien
	    } else if (usuario.equals("usuario") && contraseña.equals("password")) {
	        return "JUGADOR";
	    }
	    return "ERROR";
	}
	
	
	
	private void confirmarSalida() { // para confirmar la salida
		int confirm = JOptionPane.showConfirmDialog(null, "¿Seguro que quieres cerrar el juego?","Salir",JOptionPane.YES_NO_OPTION,JOptionPane.QUESTION_MESSAGE);
		if (confirm == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}
	

}
