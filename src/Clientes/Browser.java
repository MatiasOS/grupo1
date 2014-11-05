package Clientes;

import java.util.ArrayList;

public class Browser {

    public static void main(String[] args) {
    	
        ArrayList<Thread> clients = new ArrayList<Thread>();
        
        for (int i = 0; i < 50; i++)
            clients.add(new Script(i)); //AGREGO 5 PETICIONES

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