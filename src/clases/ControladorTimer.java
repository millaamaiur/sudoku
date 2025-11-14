package clases;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class ControladorTimer implements Runnable{
	private int segundos = 0;
	private boolean corriendo = false;
	private boolean reiniciar = false;
	private Thread hilo;
	private JLabel label;
	
	
	
	public ControladorTimer(int segundos, boolean correr, JLabel label) {
		super();
		this.segundos = segundos;
		this.corriendo = correr;
		this.label = label;
	}
	
	public void actualizarSegundos() {
		this.segundos += 1;
	}

	public void start() {
		if (hilo == null || !hilo.isAlive()) {
			corriendo = true;
			hilo = new Thread(this);
			hilo.start();
		}
	}
	
	public void stop() {
		corriendo = false;
		if (hilo != null) {
			hilo.interrupt();
		}
	}
	
	public void reset() {
		reiniciar = true;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (corriendo) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				System.out.println("Temporizador detenido");
			}
			
			if (reiniciar) {
				segundos = 0;
				reiniciar = false;
				label.setText("00:00");
				corriendo = false;
				if (hilo != null) {
					hilo.interrupt();
				}
			} else {
				actualizarSegundos();
			}
			
			SwingUtilities.invokeLater(() -> {
                int mins = segundos / 60;
                int secs = segundos % 60;
                label.setText(String.format("%02d:%02d", mins, secs));
            });
		}
	}

}
