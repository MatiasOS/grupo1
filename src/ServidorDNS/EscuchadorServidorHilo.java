package ServidorDNS;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Timer;
//import java.util.TimerTask;
import java.util.Vector;

public class EscuchadorServidorHilo implements Runnable {
	
	private String ip;
	private HashMap<String,Timer> contadores;
	private Vector<String> direcciones;
	private Socket sk;

	public EscuchadorServidorHilo(HashMap<String, Timer> contadores2,Vector<String> direcciones,Socket sk) {
		//this.ip = ipServidorEntrante;
		this.contadores =  contadores2;
		this.direcciones=direcciones;
		this.sk=sk;
	}
	public void run() {
		//System.out.println(this.contadores.get(this.ip)+"mostrar");
		//System.out.println(this.ip);
		//System.out.println(this.contadores);
		while (true){
		try {
			DataInputStream dis = new DataInputStream(sk.getInputStream());
			//String ipServidorEntrante = dis.readUTF();
			this.ip =dis.readUTF();
			//System.out.println(ipServidorEntrante);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TimerTaskTemporizador tt = new TimerTaskTemporizador(ip, contadores, direcciones);
		Timer t = new Timer();
		t.schedule(tt, 12000, 12000);
		//System.out.println("Abro sync hilo escuchadorHilo");
		synchronized (contadores) {
			(this.contadores.get(this.ip)).cancel();
			//System.out.println("se cancelo el contador de ip "+this.ip);
			this.contadores.put(ip, t);
		}
		
		//System.out.println("Cierro sync hilo escuchadorHilo");
		
		//System.out.println(" Heartbeat de " + this.ip);
		}
	}
}
