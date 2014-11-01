package ServidorDNS;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

public class AsignadorServidor implements Runnable {
	private ServerSocket ss;
	private Vector<String> direcciones;
	
	public AsignadorServidor (Vector<String> direcciones)
	{
		this.direcciones = direcciones;
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			ss = new ServerSocket(10580);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (true){
			Socket sk;
			try {
				sk = ss.accept();
				((AsignadorServidorHilo) new AsignadorServidorHilo((Socket)ss.accept(),direcciones)).run();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
