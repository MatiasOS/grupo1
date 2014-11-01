package ServidorDNS;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

public class AsignadorServidorHilo implements Runnable {
	private Vector<String> direcciones;
	private int dir;
	private Socket socket;
	private DataOutputStream dos;
	
	public AsignadorServidorHilo(Socket socket,Vector<String> direcciones){
		this.direcciones = direcciones;
		this.dir=0;
		this.socket = socket;
		try {
			dos = new DataOutputStream(socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			dos.writeUTF(direcciones.elementAt(dir%direcciones.size()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
