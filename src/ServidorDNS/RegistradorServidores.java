package ServidorDNS;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Timer;
import java.util.Vector;

import server.Monitoreo.ServidorHilo;

public class RegistradorServidores implements Runnable{

	private Vector<String> direcciones;
	private ServerSocket ss;
	private HashMap<String, Timer> contadores;
	
	public RegistradorServidores(Vector<String> direcciones, int puerto,HashMap<String, Timer> contadores) {
		this.direcciones=direcciones;
		this.contadores = contadores;
		// Se pone al RegistradorServidores a escuchar
		// por el puerto puerto(10579)
		try {
			ss = new ServerSocket(puerto);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		Socket sk;
		while (true){
			try {
				// Cada conexion entrante es un servidor que se quiere registrar.
				// Entonces por cada conexion se crea un registrador hilo que es
				// quien agregara la ip entrante a la lista de direcciones del dns
				sk = ss.accept();
				//System.out.println(sk.toString());
				DataInputStream dis = new DataInputStream(sk.getInputStream());
				String ipServidorEntrante = dis.readUTF();
				//System.out.println(ipServidorEntrante+" se quiere registrar");
				(new Thread(new RegistradorServidorHilo(ipServidorEntrante,direcciones,contadores))).start();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
