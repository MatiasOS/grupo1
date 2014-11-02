package ServidorDNS;

import java.util.HashMap;
import java.util.Timer;
import java.util.Vector;

public class RegistradorServidorHilo implements Runnable{

	private String ip;
	private Vector<String> direcciones;
	private HashMap<String, Timer> contadores;
	
	public RegistradorServidorHilo(String ip,Vector<String> direcciones,HashMap<String, Timer> contadores) {
		this.direcciones = direcciones;
		this.ip = ip;
		this.contadores = contadores;
	}
	
	@Override
	public void run() {
		TimerTaskTemporizador tt = new TimerTaskTemporizador(ip, contadores, direcciones);
		Timer t = new Timer();
		t.schedule(tt, 0, 15000);
		synchronized (contadores) {
			synchronized (direcciones) {
				direcciones.add(ip);
				contadores.put(ip,t);
			}
		}
	}

}
