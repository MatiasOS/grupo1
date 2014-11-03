package Clientes;

import java.io.*;
import java.net.Socket;
import java.util.logging.*;

class Script extends Thread {
	private static String ipDns = "192.168.1.16";// TODO Harcodear ipDns
    protected Socket sk;
    protected DataOutputStream dos;
    protected DataInputStream dis;
    private int id;

    public Script(int id) {
        this.id = id;
    }

    public void run() {
        try {
            sk = new Socket(ipDns, 10578); // TODO setear ip del servidor DNS
            dos = new DataOutputStream(sk.getOutputStream()); //LE DOY LAS ENTRADAS Y SALIDAS DEL SOCKET
            dis = new DataInputStream(sk.getInputStream());
            
            System.out.println( "Script [" +id + "] Pide Servidor ");
            dos.writeUTF("getServidor"); //SALIDA
            String ipServ="";
            ipServ = dis.readUTF(); //ENTRADA
            //ipServ = "127.0.0.1";
            System.out.println(id + " Servidor devuelve: " + ipServ);
            
            //enviarClicks(ipServ); // Levanta los clicks desde el txt. y los envia a respuesta
 
        } catch (IOException ex) {
            Logger.getLogger(Script.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void enviarClicks(String ipServ) {
        

		File f = new File( "./clicks.txt" );
	    BufferedReader entrada;
	    try {
	         entrada = new BufferedReader( new FileReader( f ) );
	         String linea;
	         while(entrada.ready()){
	        	 sk = new Socket(ipServ, 5001); // Creo el socket al servidorMonitoreo al que va a enviar el click
		         dos = new DataOutputStream(sk.getOutputStream()); // cada vez que va a enviar crea el socket
		         dis = new DataInputStream(sk.getInputStream());
	        	 linea = entrada.readLine();
	        	 System.out.println(linea);
	        	 System.out.println( "Script ["+id + "] envia click" ); 
	        	 dos.writeUTF(linea);
	         }
	    }catch (IOException e) {
	      e.printStackTrace();
       }
	}
}