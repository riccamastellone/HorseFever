package view_event;

import model.Giocatore;

public class PiazzaScommessa2Event implements HorseFeverEvent {
	
	private Giocatore giocatore;
	private String colore;
	private Integer valore;
	private String tipo;
	private String nome = "";

	
	public PiazzaScommessa2Event(Giocatore giocatore, String colore, Integer valore, String tipo){
		
		this.giocatore = giocatore;
		this.colore = colore;
		this.valore = valore;
		this.tipo = tipo;

	}



	public String getColore() {
		return colore;
	}


	public Integer getValore() {
		return valore;
	}


	public String getTipo() {
		return tipo;
	}


	public Giocatore getGiocatore() {
		return giocatore;
	}

	public String getNome() {
		return nome;
	}
}
