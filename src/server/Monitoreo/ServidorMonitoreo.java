package server.Monitoreo;
import java.io.*;
import java.net.*;
import java.util.logging.*;

import server.Monitoreo.ServidorHilo;
public class ServidorMonitoreo {
	
	private static DataOutputStream registroServidor;
	
	private static String getIpDns() { // Lee del archivo de configuracion la ip del Servidor Dns
	      String linea = null;
	      try {
	    	 File archivo = new File( "./config.txt" );
	    	 BufferedReader br = new BufferedReader(new FileReader (archivo));
	         linea = br.readLine();
	      }
	      catch(Exception e){
	         e.printStackTrace();
	      }
	      return linea;
	}
	
	private static void registrarServidor(InetAddress ipMia){
		try {
			Socket sk = new Socket(getIpDns(), 10579);
			registroServidor = new DataOutputStream(sk.getOutputStream());
			registroServidor.writeUTF(ipMia.getHostAddress());
			
		} catch (IOException e) {
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
            Thread hear = new Thread(new Heartbeat(getIpDns(),10580,ipMia.getHostAddress()));
            hear.start();
            System.out.println("\t[OK]");
            while (true) {
                Socket socket;
                System.out.println("esperando conexion...");
                socket = ss.accept();
                System.out.println("Nueva conexi�n entrante: "+socket+"\n");
                // Se crea un nuevo hilo para manejar la conexion con el script
                ((ServidorHilo) new ServidorHilo(socket)).start();
            }

        } catch (IOException ex) {
            Logger.getLogger(ServidorMonitoreo.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 
}
