package clases;

public class Sudoku {
	/*Los sudokus creo que lo mejor sería hacerlo con arrays porque es lo mas sencillo.
	 * Lo de solucion es directamente el sudoku resuelto*/ 
	
	private int[][] tablero;
	private int[][] solucion;
	private String dificultad;
	private int id;
	
	public Sudoku(int[][] tablero, int[][] solucion, String dificultad, int id) {
		super();
		this.tablero = tablero;
		this.solucion = solucion;
		this.dificultad = dificultad;
		this.id = id;
	}
	
	/*Metodos de la clase Sudoku*/
	
	public int[][] getTablero() {
		return tablero;
	}

	public void setTablero(int[][] tablero) {
		this.tablero = tablero;
	}

	public int[][] getSolucion() {
		return solucion;
	}

	public void setSolucion(int[][] solucion) {
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
