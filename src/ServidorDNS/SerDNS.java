package ServidorDNS;

import java.io.*;
import java.net.*;
import java.util.HashMap;
import java.util.Timer;
import java.util.Vector;
import java.util.logging.*;

public class SerDNS {

	private static ServerSocket ss;
	private static Vector<String> direcciones;
	private static int idSer;
	private static HashMap<String, Timer> contadores;
	
	// Puerto por el cual se registran los servidores
	//private int puertoServidores = 10579;
	
    public static void main(String args[]) throws IOException {
    	
    	direcciones = new Vector<String>();
    	contadores = new HashMap<String, Timer>();
    	//idSer=0;
    	System.out.print("server starup... ");
    	Thread t1 = new Thread(new RegistradorServidores(direcciones,10579,contadores));
    	t1.start();
    	System.out.print("server starup1... ");
    	Thread t2 = new Thread(new EscuchadorServidor(direcciones,10580));
    	t2.start();
    	System.out.print("server starup2... ");
    	//Thread t3 = new Thread(new AsignadorServidor(direcciones,10578));
    	//t3.start();
        System.out.print("server ready... ");
/*        try {
            ss = new ServerSocket(10578); //SE CREA UNA CONEXI�N del SERVER
            System.out.println("\t[OK]");
            
            int idSession = 0;
            while (true) { //ESPERA CLIENTES
                //System.out.println("Nueva conexi�n entrante: "+ (Socket)ss.accept());
            	System.out.println("idServidor"+idSer);
                ((SerDNSHilo) new SerDNSHilo((Socket)ss.accept(), idSession, direcciones.elementAt(idSer%direcciones.size())) ).start(); //LE PASO EL SOCKET AL SERDNSHILO, LO CREO Y LO RUNNEO
                idSession++;
                idSer++;
            }

        } catch (IOException ex) {
            Logger.getLogger(SerDNS.class.getName()).log(Level.SEVERE, null, ex);
        }*/
    }
}
