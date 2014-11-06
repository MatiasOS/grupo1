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
    	
        ArrayList<Thread> clients = new ArrayList<Thread>();
        for (int i = 0; i < 200; i++)
            clients.add(new Script(i, getIpDns())); //AGREGO 5 PETICIONES

        for (Thread thread : clients) { //RECORRO LAS 5 PERSONAS
            thread.start();
            try {
				Thread.sleep(1000); //DUERMO CADA PERSONA
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
        }
    }
}