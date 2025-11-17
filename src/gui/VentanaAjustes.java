package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridLayout;
import java.awt.FlowLayout;
import java.awt.BorderLayout;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class VentanaAjustes extends JFrame {
	//Atributos de la ventana ajustes 
	private static final long serialVersionUID = 1L;
	private VentanaPartida parent;

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
		gbl_panelDif.rowHeights = new int[]{0};
		gbl_panelDif.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		gbl_panelDif.rowWeights = new double[]{1.0};
		panelDif.setLayout(gbl_panelDif);

		JButton btnDif = new JButton("Dificultad");
		GridBagConstraints gbc_btnDif = new GridBagConstraints();
		gbc_btnDif.gridx = 3; 
		gbc_btnDif.gridy = 0; 
		gbc_btnDif.anchor = GridBagConstraints.CENTER;
		panelDif.add(btnDif, gbc_btnDif);

		
		JPanel panelSonido = new JPanel();
		contentPane.add(panelSonido);
		GridBagLayout gbl_panelSonido = new GridBagLayout();
		gbl_panelSonido.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0};
		gbl_panelSonido.rowHeights = new int[]{0};
		gbl_panelSonido.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		gbl_panelSonido.rowWeights = new double[]{1.0};
		panelSonido.setLayout(gbl_panelSonido);
		
		JButton btnSonido = new JButton("Sonido");
		btnSonido.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnSonido = new GridBagConstraints();
		gbc_btnSonido.gridx = 3;
		gbc_btnSonido.gridy = 0;
		gbc_btnSonido.anchor = GridBagConstraints.CENTER;
		panelSonido.add(btnSonido, gbc_btnSonido);
		
		
		
		JPanel panelColor = new JPanel();
		contentPane.add(panelColor);
		GridBagLayout gbl_panelColor = new GridBagLayout();
		gbl_panelColor.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0};
		gbl_panelColor.rowHeights = new int[]{0};
		gbl_panelColor.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		gbl_panelColor.rowWeights = new double[]{1.0};
		panelColor.setLayout(gbl_panelColor);
		
		JButton btnColor = new JButton("Color");
		GridBagConstraints gbc_btnColor = new GridBagConstraints();
		gbc_btnColor.insets = new Insets(0, 0, 0, 5);
		gbc_btnColor.gridx = 3;
		gbc_btnColor.gridy = 0;
		panelColor.add(btnColor, gbc_btnColor);

		
		
		JPanel panelAtras = new JPanel();
		contentPane.add(panelAtras);
		GridBagLayout gbl_panelAtras = new GridBagLayout();
		gbl_panelAtras.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0};
		gbl_panelAtras.rowHeights = new int[]{0, 0};
		gbl_panelAtras.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		gbl_panelAtras.rowWeights = new double[]{1.0, 0.0};
		panelAtras.setLayout(gbl_panelAtras);
		
		JButton btnNewButton = new JButton("New button");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.insets = new Insets(0, 0, 0, 5);
		gbc_btnNewButton.gridx = 3;
		gbc_btnNewButton.gridy = 1;
		panelAtras.add(btnNewButton, gbc_btnNewButton);
		
	}

}
