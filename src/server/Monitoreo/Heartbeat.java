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
		boolean caido = false;
		while (!caido){
			try {
				dos.writeUTF(mensaje);
				Thread.sleep(5000);
			} catch (InterruptedException | IOException e) {
				System.out.println("Server DNS caido");
				caido = true;
			}
		}

	}

}
