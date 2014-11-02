package ServidorDNS;

import java.util.Vector;

public class RegistradorServidorHilo implements Runnable{

	private String ip;
	private Vector<String> direcciones;
	
	public RegistradorServidorHilo(String ip,Vector<String> direcciones) {
		this.direcciones = direcciones;
		this.ip = ip;
	}
	
	@Override
	public void run() {
		synchronized (this.direcciones) {
			this.direcciones.add(ip);
			System.out.println("Agrego direccion "+ip);
		}
	}

}
