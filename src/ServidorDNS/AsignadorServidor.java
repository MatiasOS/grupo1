package ServidorDNS;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class AsignadorServidor implements Runnable {
	
	private ServerSocket ss;
	private Vector<String> direcciones;
	
	public AsignadorServidor (Vector<String> direcciones, int puerto)
	{
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
			Socket sk;
			try {
				sk = ss.accept();
				((AsignadorServidorHilo) new AsignadorServidorHilo((Socket)ss.accept(),direcciones)).run();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
