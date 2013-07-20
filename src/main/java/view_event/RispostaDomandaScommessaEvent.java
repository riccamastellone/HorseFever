package view_event;

import model.Giocatore;

public class RispostaDomandaScommessaEvent implements HorseFeverEvent {

	private Integer scelta; 
	private Giocatore giocatore;
	private String nome = "";
		
	public RispostaDomandaScommessaEvent(Integer scelta, Giocatore giocatore){
		
		this.scelta = scelta;
		this.giocatore = giocatore;
	}

	public Integer getScelta() {
		return scelta;
	}

	public Giocatore getGiocatore() {
		return giocatore;
	}

	public String getNome() {
		return nome;
	}

	
}
