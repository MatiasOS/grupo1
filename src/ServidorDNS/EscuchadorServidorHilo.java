package ServidorDNS;

public class EscuchadorServidorHilo implements Runnable {
	private String ip;
	
	public EscuchadorServidorHilo(String ip)	{
		this.ip = ip;
	}
	public void run() {
		// TODO ACTUALIZAR ALGUN TEMPORIZADOR
		System.out.println(" Heartbeat de " + this.ip);
	}

}
