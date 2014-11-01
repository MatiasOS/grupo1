package server.Monitoreo;
import java.io.*;
import java.net.*;
import java.util.logging.*;


public class ServidorHilo extends Thread {

    private Socket socket;
    private DataOutputStream dos;
    private DataInputStream dis;
    
    public Socket getSocket() {
		return socket;
	}

	public void setSocket(Socket socket) {
		this.socket = socket;
	}

	private int idSessio;

    public ServidorHilo(Socket socket, int id) {
        this.socket = socket;
        this.idSessio = id;
        try {
            dos = new DataOutputStream(socket.getOutputStream());
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }


	public void desconnectar() {
        try {
            socket.close();
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void run() {
        String click = "";
        try {
            click = dis.readUTF(); 
            System.out.println("El cliente con idSesion "+this.idSessio+" envi� un click");
            dos.writeUTF("me llego el click...");
   
            Parser p = new Parser();
            Click ck;
            ck = p.parsear(click);
            ck.print();
            //TODO agregar que le llega el chorizo lo pasa por el parser, y lo devuelve en forma de Click (Ya implementados)
            
        } catch (IOException ex) {
            Logger.getLogger(ServidorHilo.class.getName()).log(Level.SEVERE, null, ex);
        }



        desconnectar();
    }
}
