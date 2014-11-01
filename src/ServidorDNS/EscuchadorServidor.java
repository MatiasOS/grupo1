package ServidorDNS;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class EscuchadorServidor implements Runnable {
	private ServerSocket ss;
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			ss = new ServerSocket(10580);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		while (true){
			Socket sk;
			try {
				sk = ss.accept();
				DataInputStream dis = new DataInputStream(sk.getInputStream());
				String ipServidorEntrante = dis.readUTF();
				((EscuchadorServidorHilo) new EscuchadorServidorHilo(ipServidorEntrante)).run();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

}
