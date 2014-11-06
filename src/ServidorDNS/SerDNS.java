package ServidorDNS;

import java.io.*;
import java.util.Vector;

import ServidorDNS.registradorDNS.RegistradorServidores;
import ServidorDNS.asignadorDNS.AsignadorServidor;
import ServidorDNS.escuchadorDNS.EscuchadorServidor;

public class SerDNS {

	private static Vector<String> direcciones;
	
	// Puerto por el cual se registran los servidores
    public static void main(String args[]) throws IOException {
    	
    	direcciones = new Vector<String>();
    	System.out.println("Iniciando servidor DNS... ");
    	
    	Thread t1 = new Thread(new RegistradorServidores(direcciones,10579));
    	t1.start();
    	System.out.println("Registrador ready... ");
    	
    	Thread t2 = new Thread(new EscuchadorServidor(direcciones,10580));
    	t2.start();
    	System.out.println("Escuchador ready... ");
    	
    	Thread t3 = new Thread(new AsignadorServidor(direcciones,10578));
    	t3.start();
        System.out.println("Asignador ready... ");
    }
}
