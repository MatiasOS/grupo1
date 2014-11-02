package ServidorDNS;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class TimerTaskTemporizador extends TimerTask {

	private String ip;
	private HashMap<String,Timer> contadores;
	private Vector<String> direcciones;
	
	public TimerTaskTemporizador(String ip, HashMap<String,Timer> contadores,Vector<String> direcciones) {
		this.ip = ip;
		this.contadores = contadores;
		this.direcciones = direcciones;
	}
	
	@Override
	public void run() {
		synchronized (contadores) {
			synchronized (direcciones) {
				direcciones.remove(ip);
				contadores.remove(ip);
			}
		}
	}

}
