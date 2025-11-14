package clases;

public class Casilla {
	
	//Atributos
	private int valor;
	private final int fila; //Para que no se pueda modificar
	private final int columna; //Para que no se pueda modificar
	private boolean editable;
	
	//Constructor
	public Casilla(int valor, int fila, int columna, boolean editable) {
		super();
		this.valor = valor;
		this.fila = fila;
		this.columna = columna;
		this.editable = editable;
	}

	public int getValor() {
		return valor;
	}

	public void setValor(int valor) {
		this.valor = valor;
	}

	public boolean isEditable() {
		return editable;
	}

	public void setEditable(boolean editable) {
		this.editable = editable;
	}

	public int getFila() {
		return fila;
	}

	public int getColumna() {
		return columna;
	}

	//Metodos
	public boolean esValido() {
		return 0 <= getValor() && getValor() <=9;
	}

}
