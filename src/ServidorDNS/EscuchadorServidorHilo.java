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
		this.contadores =  contadores2;
		this.direcciones=direcciones;
		this.sk=sk;
	}
	public void run() {
		while (true){
		try {
			DataInputStream dis = new DataInputStream(sk.getInputStream());
			this.ip =dis.readUTF();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		TimerTaskTemporizador tt = new TimerTaskTemporizador(ip, contadores, direcciones);
		Timer t = new Timer();
		t.schedule(tt, 12000, 12000);
		synchronized (contadores) {
			(this.contadores.get(this.ip)).cancel();
			//System.out.println("se cancelo el contador de ip "+this.ip);
			System.out.println("heartbeat2");
			//System.out.println("se cancelo el contador de ip "+this.ip);
			System.out.println("heartbeat2");
			this.contadores.put(ip, t);
		}
		}
	}
}
