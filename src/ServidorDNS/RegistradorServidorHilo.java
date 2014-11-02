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
		t.schedule(tt, 12000, 12000);
		//System.out.println("Abro sync hilo registrador");
		synchronized (contadores) {
			synchronized (direcciones) {
				direcciones.add(ip);
				contadores.put(ip,t);
				//System.out.println(contadores);
			}
		}
		System.out.println("se registro "+ip);
		//System.out.println("Cierro sync hilo registrador");
	}

}
