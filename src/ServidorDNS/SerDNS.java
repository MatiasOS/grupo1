package ServidorDNS;

import java.io.*;
import java.util.Vector;

import escuchadorDNS.EscuchadorServidor;

import registradorDNS.RegistradorServidores;

import asignadorDNS.AsignadorServidor;

public class SerDNS {

	private static Vector<String> direcciones;
	
	// Puerto por el cual se registran los servidores
    public static void main(String args[]) throws IOException {
    	
    	direcciones = new Vector<String>();
    	System.out.print("server starup... ");
    	
    	Thread t1 = new Thread(new RegistradorServidores(direcciones,10579));
    	t1.start();
    	System.out.print("server starup1... ");
    	
    	Thread t2 = new Thread(new EscuchadorServidor(direcciones,10580));
    	t2.start();
    	System.out.print("server starup2... ");
    	
    	Thread t3 = new Thread(new AsignadorServidor(direcciones,10578));
    	t3.start();
        System.out.print("server ready... ");
    }
}
