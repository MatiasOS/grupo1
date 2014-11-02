package server.Monitoreo;

public class Click {
	private String fecha;
	private String hora;
	private String browser;
	private String so;
	private String link;
	private String ip;
	
	//return fecha
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	public String getHora() {
		return hora;
	}
	public void setHora(String hora) {
		this.hora = hora;
	}
	public String getBrowser() {
		return browser;
	}
	public void setBrowser(String browser) {
		this.browser = browser;
	}
	public String getSo() {
		return so;
	}
	public void setSo(String so) {
		this.so = so;
	}
	public String getLink() {
		return link;
	}
	public void setLink(String link) {
		this.link = link;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public Click() {
	
	}
	
	public void print () {
		System.out.println("..........................el click contiene estos datos que van a ser almacenados en la bd "  );
		System.out.println("ip:["  + ip+"] link:["  + link +"] browser:["  + browser+"] so:["  + so +"] hora:["  + hora +"] fecha:["  + fecha+"]");
	}
}
