package ServidorDNS;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class EscuchadorServidor implements Runnable {
	
	private ServerSocket ss;
	private Vector<String> direcciones;
	private HashMap<String, Timer> contadores;
	
	public EscuchadorServidor(Vector<String> direcciones, int puerto,HashMap<String, Timer> contadores) {
		this.direcciones=direcciones;
		this.contadores = contadores;
		try {
			// Ponemos a escuchar las conexiones entrantes de los scripts 
			ss = new ServerSocket(puerto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		Socket sk = new Socket();
		try {
			sk = ss.accept();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		while (true){
			try {
				//System.out.println("Todavia no entra el heartbeat (entra)");
				DataInputStream dis = new DataInputStream(sk.getInputStream());
				String ipServidorEntrante = dis.readUTF();
				//System.out.println("heartbeat de "+ipServidorEntrante);
				Thread t = new Thread(new EscuchadorServidorHilo(ipServidorEntrante,contadores,direcciones));
				t.start();
				
				//System.out.println("sale");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
