package server.Monitoreo;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Heartbeat implements Runnable {

	private String ip;
	private Socket sk;
    private DataOutputStream dos;
    private String mensaje;
    private int puerto;
	
	public Heartbeat(String ip,int puerto) {
		this.ip = ip;
		this.mensaje = ip;
		this.puerto = puerto;
		
	}
	
	@Override
	public void run() {
		
		try {
			sk = new Socket(ip, 10580);
			dos = new DataOutputStream(sk.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		while (true){
			try {
				//dos.writeBytes(mensaje);
				System.out.println("El servidor con ip"+this.ip+"se va a dormir 10 segs");
				Thread.sleep(10000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
