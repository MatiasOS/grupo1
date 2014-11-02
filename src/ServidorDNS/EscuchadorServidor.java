package ServidorDNS;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class EscuchadorServidor implements Runnable {
	
	private ServerSocket ss;
	private Vector<String> direcciones;
	
	public EscuchadorServidor(Vector<String> direcciones, int puerto) {
		this.direcciones=direcciones;
		try {
			ss = new ServerSocket(puerto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		try {
			ss = new ServerSocket(10580);
		} catch (IOException e) {
			e.printStackTrace();
		}
		while (true){
			Socket sk;
			try {
				sk = ss.accept();
				DataInputStream dis = new DataInputStream(sk.getInputStream());
				String ipServidorEntrante = dis.readUTF();
				((EscuchadorServidorHilo) new EscuchadorServidorHilo(ipServidorEntrante)).run();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
