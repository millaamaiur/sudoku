package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JSlider;

public class VentanaAjustes extends JFrame {
	//Atributos de la ventana ajustes 
	private static final long serialVersionUID = 1L;
	private VentanaPartida parent;
	private JPanel panelSonido;
	
	 public VentanaAjustes() {
	        this(null); 
	       }

	public VentanaAjustes(VentanaPartida parent) {
		this.parent = parent;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		JPanel contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);
		contentPane.setLayout(new GridLayout(4, 0, 0, 0));
		
		JPanel panelDif = new JPanel();
		contentPane.add(panelDif);
		GridBagLayout gbl_panelDif = new GridBagLayout();
		gbl_panelDif.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0};
		gbl_panelDif.rowHeights = new int[]{0, 0};
		gbl_panelDif.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		gbl_panelDif.rowWeights = new double[]{0.0, 1.0};
		panelDif.setLayout(gbl_panelDif);
		
		JLabel lblDificultad = new JLabel("Dificultad:");
		GridBagConstraints gbc_lblDificultad = new GridBagConstraints();
		gbc_lblDificultad.insets = new Insets(0, 0, 5, 5);
		gbc_lblDificultad.gridx = 3;
		gbc_lblDificultad.gridy = 0;
		panelDif.add(lblDificultad, gbc_lblDificultad);
		
		JComboBox combDificultad = new JComboBox();
		combDificultad.setModel(new DefaultComboBoxModel(new String[] {"Facil", "Medio", "Dificil"}));
		GridBagConstraints gbc_combDificultad = new GridBagConstraints();
		gbc_combDificultad.insets = new Insets(0, 0, 0, 5);
		gbc_combDificultad.fill = GridBagConstraints.HORIZONTAL;
		gbc_combDificultad.gridx = 3;
		gbc_combDificultad.gridy = 1;
		panelDif.add(combDificultad, gbc_combDificultad);

		
		panelSonido = new JPanel();
		contentPane.add(panelSonido);
		GridBagLayout gbl_panelSonido = new GridBagLayout();
		gbl_panelSonido.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0};
		gbl_panelSonido.rowHeights = new int[]{0, 0};
		gbl_panelSonido.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		gbl_panelSonido.rowWeights = new double[]{0.0, 1.0};
		panelSonido.setLayout(gbl_panelSonido);
		
		JLabel lblSonido = new JLabel("Sonido:");
		GridBagConstraints gbc_lblSonido = new GridBagConstraints();
		gbc_lblSonido.insets = new Insets(0, 0, 5, 5);
		gbc_lblSonido.gridx = 3;
		gbc_lblSonido.gridy = 0;
		panelSonido.add(lblSonido, gbc_lblSonido);
		
		JSlider slider = new JSlider();
		GridBagConstraints gbc_slider = new GridBagConstraints();
		gbc_slider.insets = new Insets(0, 0, 0, 5);
		gbc_slider.gridx = 3;
		gbc_slider.gridy = 1;
		panelSonido.add(slider, gbc_slider);
		
		
		
		JPanel panelColor = new JPanel();
		contentPane.add(panelColor);
		GridBagLayout gbl_panelColor = new GridBagLayout();
		gbl_panelColor.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0};
		gbl_panelColor.rowHeights = new int[]{0, 0};
		gbl_panelColor.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		gbl_panelColor.rowWeights = new double[]{0.0, 1.0};
		panelColor.setLayout(gbl_panelColor);
		
		JLabel lblColor = new JLabel("Color:");
		GridBagConstraints gbc_lblColor = new GridBagConstraints();
		gbc_lblColor.insets = new Insets(0, 0, 5, 5);
		gbc_lblColor.gridx = 3;
		gbc_lblColor.gridy = 0;
		panelColor.add(lblColor, gbc_lblColor);
		
		JButton btnColor = new JButton("Cambiar");
		btnColor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Color nuevoColor = JColorChooser.showDialog(
	                    null,
	                    "Elige un color",
	                    getContentPane().getBackground()
	            );
	            if (nuevoColor != null) {
	                getContentPane().setBackground(nuevoColor);
	                panelSonido.setBackground(nuevoColor);
	            }
			}
		});
		GridBagConstraints gbc_btnColor = new GridBagConstraints();
		gbc_btnColor.insets = new Insets(0, 0, 0, 5);
		gbc_btnColor.gridx = 3;
		gbc_btnColor.gridy = 1;
		panelColor.add(btnColor, gbc_btnColor);

		
		
		JPanel panelAtras = new JPanel();
		contentPane.add(panelAtras);
		GridBagLayout gbl_panelAtras = new GridBagLayout();
		gbl_panelAtras.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0, 0};
		gbl_panelAtras.rowHeights = new int[]{0, 0};
		gbl_panelAtras.columnWeights = new double[]{0.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		gbl_panelAtras.rowWeights = new double[]{1.0, 0.0};
		panelAtras.setLayout(gbl_panelAtras);
		
		JButton btnVolver = new JButton("Volver");
		btnVolver.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				parent.setVisible(true);
				VentanaAjustes.this.setVisible(false);
			}
		});
		GridBagConstraints gbc_btnVolver = new GridBagConstraints();
		gbc_btnVolver.insets = new Insets(0, 0, 0, 5);
		gbc_btnVolver.gridx = 0;
		gbc_btnVolver.gridy = 1;
		panelAtras.add(btnVolver, gbc_btnVolver);
		
		JButton btnGuardar = new JButton("Guardar");
		btnGuardar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
		        String dificultad = (String) combDificultad.getSelectedItem();
		        int volumen = slider.getValue();
		        Color colorFondo = getContentPane().getBackground();
		        
		        //esto es para aplicar lo configurado 
		        parent.guardarAjustes(dificultad, volumen, colorFondo); //este metodo se crea en el VENTANA PARTIDA---falta hacer que se queden los ajustes
		        
		        String mensaje = "Configuaracion guardada: \n"+ "Dificultad :"+ dificultad + "\n"+ "Volumen: "+volumen+"\n"+ "Color : "+ colorFondo ;			            
		        JOptionPane.showMessageDialog(null, mensaje,"Ajustes Guardados", JOptionPane.INFORMATION_MESSAGE);
		        
		        parent.setVisible(true);
		        dispose();
			}
		});
		GridBagConstraints gbc_btnGuardar = new GridBagConstraints();
		gbc_btnGuardar.insets = new Insets(0, 0, 0, 5);
		gbc_btnGuardar.gridx = 3;
		gbc_btnGuardar.gridy = 1;
		panelAtras.add(btnGuardar, gbc_btnGuardar);
		
	}

}
