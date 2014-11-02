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
	
	public EscuchadorServidor(Vector<String> direcciones, int puerto) {
		this.direcciones=direcciones;
		try {
			// Ponemos a escuchar las conexiones entrantes de los scripts 
			ss = new ServerSocket(puerto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (true){
			Socket sk;
			try {
				System.out.println("Todavia no entra el heartbeat");
				sk = ss.accept();
				DataInputStream dis = new DataInputStream(sk.getInputStream());
				String ipServidorEntrante = dis.readUTF();
				System.out.println("heartbeat de "+ipServidorEntrante);
				Thread t = new Thread(new EscuchadorServidorHilo(ipServidorEntrante,contadores,direcciones));
				t.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
