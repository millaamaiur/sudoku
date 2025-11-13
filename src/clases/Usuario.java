package clases;

public abstract class Usuario {
	private String usuario;
	private String contraseña;
	private int idUsuario;
	
	public Usuario(String usuario, String contraseña, int idUsuario) {
		super();
		this.usuario = usuario;
		this.contraseña = contraseña;
		this.idUsuario = idUsuario;
	}

	/*Metodos de la clase Usuario*/
	
	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getContraseña() {
		return contraseña;
	}

	public void setContraseña(String contraseña) {
		this.contraseña = contraseña;
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}
	
	public abstract String getRol();
	
}
