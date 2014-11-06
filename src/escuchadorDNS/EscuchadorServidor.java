package escuchadorDNS;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

public class EscuchadorServidor implements Runnable {
	
	private ServerSocket ss;
	private Vector<String> direcciones;
	
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
			try {
				Socket sk = ss.accept();
				Thread t = new Thread(new EscuchadorServidorHilo(direcciones,sk));
				t.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
