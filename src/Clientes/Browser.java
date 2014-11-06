package Clientes;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;

public class Browser {
	
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

    public static void main(String[] args) {
    	int cant = 0;
    	if (args.length == 0) 
    		cant = 10;
    	else 
    		cant = Integer.valueOf( args[0]);
    	
        ArrayList<Thread> clients = new ArrayList<Thread>();
        for (int i = 0; i < cant; i++)
            clients.add(new Script(i, getIpDns())); //AGREGO 5 PETICIONES

        for (Thread thread : clients) { //RECORRO LAS 5 PERSONAS
            thread.start();
            try {
				Thread.sleep(1000); 
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }
}