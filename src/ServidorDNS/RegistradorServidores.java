package ServidorDNS;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;

import server.Monitoreo.ServidorHilo;

public class RegistradorServidores implements Runnable{

	private Vector<String> direcciones;
	private ServerSocket ss;
	
	public RegistradorServidores(Vector<String> direcciones, int puerto) {
		this.direcciones=direcciones;
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
		while (true){
			Socket sk;
			try {
				// Cada conexion entrante es un servidor que se quiere registrar.
				// Entonces por cada conexion se crea un registrador hilo que es
				// quien agregara la ip entrante a la lista de direcciones del dns
				sk = ss.accept();
				DataInputStream dis = new DataInputStream(sk.getInputStream());
				String ipServidorEntrante = dis.readUTF();
				((RegistradorServidorHilo) new RegistradorServidorHilo(ipServidorEntrante,direcciones)).run();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
}
