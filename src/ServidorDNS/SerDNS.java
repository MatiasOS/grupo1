package ServidorDNS;

import java.io.*;
import java.util.HashMap;
import java.util.Timer;
import java.util.Vector;

public class SerDNS {

	private static Vector<String> direcciones;
	private static HashMap<String, Timer> contadores;
	
	// Puerto por el cual se registran los servidores
	//private int puertoServidores = 10579;
	
    public static void main(String args[]) throws IOException {
    	
    	direcciones = new Vector<String>();
    	contadores = new HashMap<String, Timer>();
    	System.out.print("server starup... ");
    	
    	Thread t1 = new Thread(new RegistradorServidores(direcciones,10579,contadores));
    	t1.start();
    	System.out.print("server starup1... ");
    	
    	Thread t2 = new Thread(new EscuchadorServidor(direcciones,10580,contadores));
    	t2.start();
    	System.out.print("server starup2... ");
    	
    	Thread t3 = new Thread(new AsignadorServidor(direcciones,10578));
    	t3.start();
        System.out.print("server ready... ");
    }
}
