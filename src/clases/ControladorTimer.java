package clases;

import javax.swing.JLabel;
import javax.swing.SwingUtilities;

public class ControladorTimer implements Runnable{
	private int segundos = 0; 
	private boolean corriendo = false;
	private boolean reiniciar = false;
	private Thread hilo;
	private JLabel label;
    private Object lock = new Object();
    private boolean pausado = false;

	
	
	public ControladorTimer(int segundos, boolean correr, JLabel label) {
		super();
		this.segundos = segundos;
		this.corriendo = correr;
		this.label = label;
	}
	
	public void pause() {
        synchronized (lock) {
            pausado = true;
        }
    }
	public void resume() {
        synchronized (lock) {
            pausado = false;
            lock.notify(); //segir con el hilo si esta esperando
        }
    }

	public void start() {
		synchronized (lock) {
            if (!corriendo) {
                corriendo = true;
                if (hilo == null || !hilo.isAlive()) {
                    hilo = new Thread(this);
                    hilo.start();
                }
            }
        }
	}
	
	public void stop() {
		synchronized (lock) {
            corriendo = false;
        }
        if (hilo != null) {
            hilo.interrupt();
        }
	}
	
	public void reset() {
		synchronized (lock) {
            segundos = 0;
        }
        
        if (hilo != null) {
            hilo.interrupt();
        }
        
        // Actualizar el label en el EDT (Event Dispatch Thread)
        SwingUtilities.invokeLater(() -> {
            label.setText("00:00");
        });
	}
	
	 @Override
	    public void run() {
	        while (true) {
	            synchronized (lock) {
	                if (!corriendo) {
	                    break;
	                }
	                
	                // Si está pausado, esperar
	                while (pausado) {
	                    try {
	                        lock.wait();
	                    } catch (InterruptedException e) {
	                        Thread.currentThread().interrupt();
	                        return;
	                    }
	                }
	            }
	            
	            try {
	                Thread.sleep(1000);
	            } catch (InterruptedException e) {
	                Thread.currentThread().interrupt();
	                break;
	            }
	            
	            synchronized (lock) {
	                if (corriendo && !pausado) {
	                    segundos++;
	                    
	                    final int mins = segundos / 60;
	                    final int secs = segundos % 60;
	                    
	                    SwingUtilities.invokeLater(() -> {
	                        label.setText(String.format("%02d:%02d", mins, secs));
	                    });
	                }
	            }
	        }
	    }
	 
	    public boolean isPaused() {
	        synchronized (lock) {
	            return pausado;
	        }
	    }
	
    
	 //metodo para saber si esta corriendo
    public boolean isRunning() {
        synchronized (lock) {
            return corriendo;
        }
    }

}
