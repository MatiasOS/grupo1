package ServidorDNS;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

import com.sun.org.apache.xalan.internal.xsltc.runtime.Hashtable;

public class EscuchadorServidor implements Runnable {
	
	private ServerSocket ss;
	private Vector<String> direcciones;
	private HashMap<String, Timer> contadores;
	
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
			Socket sk;
			try {
				sk = ss.accept();
				DataInputStream dis = new DataInputStream(sk.getInputStream());
				String ipServidorEntrante = dis.readUTF();
				((EscuchadorServidorHilo) new EscuchadorServidorHilo(ipServidorEntrante,contadores,direcciones)).run();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

}
