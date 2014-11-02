package server.Monitoreo;
import java.io.*;
import java.net.*;
import java.util.logging.*;

import server.Monitoreo.ServidorHilo;
import server.procesamiento.Servidor;

public class ServidorMonitoreo {
	
	private static String ipDns = "192.168.0.20";// TODO Harcodear ipDns
	private static DataOutputStream registroServidor;
	
	
	private static void registrarServidor(InetAddress ipMia){
		try {
			Socket sk = new Socket(ipDns, 10579);
			registroServidor = new DataOutputStream(sk.getOutputStream());
			registroServidor.writeUTF(ipMia.getHostAddress());
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

    public static void main(String args[]) throws IOException {

    	InetAddress ipMia=InetAddress.getLocalHost();
        ServerSocket ss;

        System.out.print("Inicializando servidor... " + ipMia.getHostAddress());
        registrarServidor(ipMia);
        try {
            ss = new ServerSocket(5001);
            // Inicializacion de heartbeat con la ip y el puerto
            // a donde se tienen que comunicar los scripts
            Heartbeat hear = new Heartbeat(ipDns,10580,ipMia.getHostAddress());
            hear.run();
            System.out.println("\t[OK]");
            int idSession = 0;
            while (true) {
                Socket socket;
                socket = ss.accept();
                System.out.println("Nueva conexi�n entrante: "+socket);
                // Se crea un nuevo hilo para manejar la conexion con el script
                ((ServidorHilo) new ServidorHilo(socket, idSession)).start();
                idSession++;
            }

        } catch (IOException ex) {
            Logger.getLogger(Servidor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
	
	
	
    
}
