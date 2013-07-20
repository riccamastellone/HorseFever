package view_event;

import model.Giocatore;

public class PiazzaTruccaCorsaEvent implements HorseFeverEvent {

	private Integer scelta;
	private String colore;
	private Giocatore giocatore;
	private String nome = "";
	
	public PiazzaTruccaCorsaEvent(Integer scelta, String colore, Giocatore giocatore) {
		
		this.scelta = scelta;
		this.colore = colore;
		this.giocatore = giocatore;
		
	}



	public String getColore() {
		return colore;
	}

	public Giocatore getGiocatore() {
		return giocatore;
	}

	public String getNome() {
		return nome;
	}



	public Integer getScelta() {
		return scelta;
	}




}
