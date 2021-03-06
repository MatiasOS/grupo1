package ServidorDNS.asignadorDNS;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class AsignadorServidor implements Runnable {
	
	private ServerSocket ss;
	private Vector<String> direcciones;
	private int dir;
	
	public AsignadorServidor (Vector<String> direcciones, int puerto)
	{
		dir = 0;
		this.direcciones = direcciones;
		try {
			ss = new ServerSocket(puerto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void run() {
		while (true){
			try {
				Socket sk = ss.accept();
				dir++;
				Thread t = new Thread(new AsignadorServidorHilo(sk,direcciones,dir));
				t.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
