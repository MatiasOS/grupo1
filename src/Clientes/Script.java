package Clientes;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.logging.*;

class Script extends Thread {
	private  String ipDns ;
    protected Socket sk;
    protected DataOutputStream dos;
    protected DataInputStream dis;
    private int id;
    private String ipServ;
    
    private void pedirServidor() throws UnknownHostException, IOException {
    	 sk = new Socket(ipDns, 10578); // TODO setear ip del servidor DNS.
         dos = new DataOutputStream(sk.getOutputStream()); //LE DOY LAS ENTRADAS Y SALIDAS DEL SOCKET
         dis = new DataInputStream(sk.getInputStream());
         System.out.println( "Script [" +id + "] Pide Servidor ");
         ipServ = dis.readUTF(); //ENTRADA
         System.out.println( " DNS asigna Servidor: " + ipServ);
         sk.close();
    }

    public Script(int id, String ipdns) {
        this.id = id;
        this.ipDns = ipdns;
    }

    public void run() {
        try {       
            pedirServidor();
           
            enviarClicks(); // Levanta los clicks desde el txt. y los envia a respuesta
 
        } catch (IOException ex) {
            Logger.getLogger(Script.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void enviarClicks() {

		File f = new File( "./clicks.txt" );
	    BufferedReader entrada;
		String linea;
		try {
			entrada = new BufferedReader(new FileReader(f));
			while ((linea = entrada.readLine()) != null) {
				try {

					sk = new Socket(ipServ, 5001); // Creo el socket al
													// servidorMonitoreo al que
													// va a enviar el click
					dos = new DataOutputStream(sk.getOutputStream()); // cada vez que va a enviar un click crea el socket con el serv
					dis = new DataInputStream(sk.getInputStream());
					System.out.println("Script [" + id + "] envia click"+"\n" +String.valueOf(id) + "$" + linea);
					dos.writeUTF(String.valueOf(id) + "$" + linea);
					sk.close();
					this.sleep(1000);
				} catch (IOException e) {
					System.out.println("No se pudo establecer conexion con servidor" + ipServ);
					pedirServidor();
				}

			}
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}