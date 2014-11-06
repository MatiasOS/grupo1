package escuchadorDNS;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Vector;

public class EscuchadorServidorHilo implements Runnable {
	
	private String ip;
	private Vector<String> direcciones;
	private Socket sk;

	public EscuchadorServidorHilo(Vector<String> direcciones,Socket sk) {
		this.direcciones=direcciones;
		this.sk=sk;
	}
	public void run() {
		boolean sigue = true;
		while (sigue){
				try {
					DataInputStream dis = new DataInputStream(sk.getInputStream());
					String ipAux =dis.readUTF();
					this.ip =ipAux;
					System.out.println("Llego un heartbeat de "+ip);
				} catch (IOException e) {
					sigue = false;
					direcciones.remove(ip);
					System.out.println("se elimino la ip "+ip);
					System.out.println(direcciones);
				}
		}
	}
}
