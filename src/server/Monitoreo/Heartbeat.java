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
	
	public Heartbeat(String ip,int puerto,String ipMia) {
		this.ip = ip;
		this.mensaje = ipMia;
		this.puerto = puerto;
	}
	
	@Override
	public void run() {
		
		try {
			sk = new Socket(ip, puerto);
			dos = new DataOutputStream(sk.getOutputStream());
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		while (true){
			try {
				dos.writeUTF(mensaje);
				System.out.println("Le envio al servidor "+this.ip+" "+mensaje +"se va a dormir 10 segs");
				Thread.sleep(5000);
			} catch (InterruptedException | IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
