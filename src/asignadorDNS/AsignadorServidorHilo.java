package asignadorDNS;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

public class AsignadorServidorHilo implements Runnable {
	private Vector<String> direcciones;
	
	private Socket socket;
	private DataOutputStream dos;
	private int dir;
	
	public AsignadorServidorHilo(Socket socket,Vector<String> direcciones,int dir){
		this.direcciones = direcciones;
		this.dir=dir;
		this.socket = socket;
		try {
			dos = new DataOutputStream(this.socket.getOutputStream());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void run() {
		try {
			if(direcciones.size()!=0)
				dos.writeUTF(direcciones.elementAt(dir%direcciones.size()));
			else System.out.println("no hay servidores :-(");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
