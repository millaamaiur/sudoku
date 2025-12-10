package clases;

public class Sudoku {
	
	/*Los sudokus contienen arrays de casillas (tablero y solucion) que en la bd manejamos como un string de 81 caracteres*/ 
	
	private Casilla[][] tablero;
	private Casilla[][] solucion;
	private String dificultad;
	private int id;
	
	public Sudoku(Casilla[][] tablero, Casilla[][] solucion, String dificultad, int id) {
		super();
		this.tablero = tablero;
		this.solucion = solucion;
		this.dificultad = dificultad;
		this.id = id;
	}
	
	/*Metodos de la clase Sudoku*/
	public Casilla[][] getTablero() {
		return tablero;
	}

	public void setTablero(Casilla[][] tablero) {
		this.tablero = tablero;
	}

	public Casilla[][] getSolucion() {
		return solucion;
	}

	public void setSolucion(Casilla[][] solucion) {
		this.solucion = solucion;
	}

	public String getDificultad() {
		return dificultad;
	}

	public void setDificultad(String dificultad) {
		this.dificultad = dificultad;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}	
}