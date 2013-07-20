package model;

import java.io.Serializable;


public class Scommessa implements Serializable {
	
	private int valore;
	private Scuderia scuderiaScommessa;
	private String tipoScommessa;
	
	
	public Scommessa(int valore, Scuderia scuderiaScommessa, String tipoScommessa){
		
		this.valore = valore;
		this.scuderiaScommessa = scuderiaScommessa;
		this.tipoScommessa= tipoScommessa;
		
	}


	public String getTipoScommessa() {
		return tipoScommessa;
	}


	public int getValore() {
		return valore;
	}


	public Scuderia getScuderiaScommessa() {
		return scuderiaScommessa;
	}
	
	


	

}
