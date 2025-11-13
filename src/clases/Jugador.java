package clases;

public class Jugador extends Usuario {
	private int partidasJugadas;
	private int mejorTiempo;
	
	public Jugador(String usuario, String contraseña, int idUsuario) {
		super(usuario, contraseña, idUsuario);
		this.partidasJugadas = 0;
		this.mejorTiempo = 0;
	}

	
	/*Metodos de la clase Jugador*/
	
	public String getRol() {
		return "Jugador";
	}
	
	public void sumarPartida() {
		partidasJugadas++;
	}

	public int getPartidasJugadas() {
		return partidasJugadas;
	}

	public void setPartidasJugadas(int partidasJugadas) {
		this.partidasJugadas = partidasJugadas;
	}

	public int getMejorTiempo() {
		return mejorTiempo;
	}

	public void setMejorTiempo(int mejorTiempo) {
		this.mejorTiempo = mejorTiempo;
	}
	
	
}
