package ServidorDNS;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;
import java.util.Vector;

public class EscuchadorServidorHilo implements Runnable {
	
	private String ip;
	private HashMap<String,Timer> contadores;
	private Vector<String> direcciones;

	public EscuchadorServidorHilo(String ipServidorEntrante,
			HashMap<String, Timer> contadores2,Vector<String> direcciones) {
		this.ip = ip;
		this.contadores =  contadores2;
		this.direcciones=direcciones;
	}
	public void run() {
		// TODO ACTUALIZAR ALGUN TEMPORIZADOR
		(this.contadores.get(this.ip)).cancel();
		TimerTaskTemporizador tt = new TimerTaskTemporizador(ip, contadores, direcciones);
		Timer t = new Timer();
		t.schedule(tt, 0, 12000);
		this.contadores.put(ip, t);
		System.out.println(" Heartbeat de " + this.ip);
	}

}
