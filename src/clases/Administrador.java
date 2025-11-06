package clases;

public class Administrador extends Usuario {

    public Administrador(String usuario, String contraseña, int idUsuario) {
        super(usuario, contraseña, idUsuario);
    }

	/*Metodos de la clase Administrador*/

    public String getRol() {
        return "admin";
    }

    public void crearSudoku(Sudoku s) {
        System.out.println("Sudoku creado: " + s.getId());
        // Aquí iría la lógica para añadirlo a la BD
    }

    public void eliminarSudoku(int id) {
        System.out.println("Sudoku eliminado: " + id);
        // Aquí iría la lógica para eliminarlo de la BD
    }
}
