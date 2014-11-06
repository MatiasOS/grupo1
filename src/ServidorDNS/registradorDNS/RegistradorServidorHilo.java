package ServidorDNS.registradorDNS;

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
		//System.out.println("Abro sync hilo registrador");
		direcciones.add(ip);
		System.out.println("se registro "+ip);
		System.out.println("direcciones registradas: "+direcciones);
		//System.out.println("Cierro sync hilo registrador");
	}

}
