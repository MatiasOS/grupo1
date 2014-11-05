package server.Monitoreo;

import java.util.Vector;



public class Parser {
	
	private Click click;
	
	public Parser() {
		click = new Click();
	}
 
	public Click parsear( String p ) {
		String palabra = p;
		Vector<String> srtg = new Vector<String>();
		String delims = "[$]+";
		String[] tokens = palabra.split(delims);
		
		/////Muestra los tokens
		for(String e: tokens){
			e. trim();
			srtg.add(e);
		}
		/////
		click.setId(srtg.elementAt(0));
		click.setFecha(srtg.elementAt(1));
		click.setHora(srtg.elementAt(2));
		click.setBrowser(srtg.elementAt(3));
		click.setSo(srtg.elementAt(4));
		click.setLink(srtg.elementAt(5));
		click.setIp(srtg.elementAt(6));
		
		return click;
	}
	
}
