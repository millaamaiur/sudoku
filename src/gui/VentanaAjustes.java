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

		JButton btnNewButton = new JButton("New button");
		GridBagConstraints gbc_btnNewButton = new GridBagConstraints();
		gbc_btnNewButton.gridx = 3; 
		gbc_btnNewButton.gridy = 0; 
		gbc_btnNewButton.anchor = GridBagConstraints.CENTER;
		panelDif.add(btnNewButton, gbc_btnNewButton);

		
		JPanel panelSonido = new JPanel();
		contentPane.add(panelSonido);
		GridBagLayout gbl_panelSonido = new GridBagLayout();
		gbl_panelSonido.columnWidths = new int[] {0, 0, 0, 0, 0, 0, 0};
		gbl_panelSonido.rowHeights = new int[]{0};
		gbl_panelSonido.columnWeights = new double[]{1.0, 1.0, 1.0, 1.0, 1.0, 1.0, 1.0};
		gbl_panelSonido.rowWeights = new double[]{1.0};
		panelSonido.setLayout(gbl_panelSonido);
		
		JButton btnNewButton_1 = new JButton("New button");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		GridBagConstraints gbc_btnNewButton_1 = new GridBagConstraints();
		gbc_btnNewButton_1.gridx = 3;
		gbc_btnNewButton_1.gridy = 0;
		gbc_btnNewButton_1.anchor = GridBagConstraints.CENTER;
		panelSonido.add(btnNewButton_1, gbc_btnNewButton_1);
		
		JPanel panelColor = new JPanel();
		contentPane.add(panelColor);
		panelColor.setLayout(new BorderLayout(0, 0));
		
		JPanel panelAtras = new JPanel();
		contentPane.add(panelAtras);
	}

}
