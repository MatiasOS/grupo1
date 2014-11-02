package ServidorDNS;

import java.io.*;
import java.net.*;
import java.util.Vector;
import java.util.logging.*;

public class SerDNS {

	private static ServerSocket ss;
	private static Vector<String> direcciones;
	private static int idSer;
	
	// Puerto por el cual se registran los servidores
//	private int puertoServidores = 10579;
	
    public static void main(String args[]) throws IOException {
    	
    	direcciones = new Vector<String>();
    	//idSer=0;
    	System.out.print("server starup... ");
    	((RegistradorServidores)new RegistradorServidores(direcciones,10579)).run();
    	((EscuchadorServidor) new EscuchadorServidor(direcciones,10580)).run();
    	((AsignadorServidor) new AsignadorServidor(direcciones,10578)).run();
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
