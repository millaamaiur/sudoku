import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		
	}
	
	private void initComponents() {
	//botones de ventanadmin
		botonAjustes = new JButton("Ajustes");
		botonAjustes.setFont(new Font("Arial",Font.BOLD,16));
		botonAjustes.setBackground(new Color(70, 130, 180));
		botonAjustes.setForeground(Color.WHITE);
		
		
		botonCrearSudoku = new JButton("Crear Sudoku");
		botonCrearSudoku.setFont(new Font("Arial", Font.BOLD, 16));
        botonCrearSudoku.setBackground(new Color(60, 179, 113));
        botonCrearSudoku.setForeground(Color.WHITE);
        
        botonSalir = new JButton("Salir");
        botonSalir.setFont(new Font("Arial", Font.BOLD, 16));
        botonSalir.setBackground(new Color(205, 92, 92));
        botonCrearSudoku.setForeground(Color.WHITE);
        
        contentPane.add(btnAjustes);
        contentPane.add(btnCrearSudoku);
        contentPane.add(btnSalir);
        
        configurarAcciones();
	}

}
