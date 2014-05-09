package ar.com.gmn.android.service;

public class Message {
	public static final int RESPUESTA_INTENTO = 0;
	private int type;
	private String content;
	private String oponente;
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public void setOponente(String oponente) {
		this.oponente = oponente;
		
	}
	public String getOponente() {
		return oponente;
	}
	
	
	

}
