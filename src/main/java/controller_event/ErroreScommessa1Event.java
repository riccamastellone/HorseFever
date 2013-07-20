package controller_event;

import view_event.HorseFeverEvent;
import model.Giocatore;

public class ErroreScommessa1Event implements HorseFeverEvent{
	
	
	
	private Giocatore giocatore;
	private Integer idGiocatore;
	private String nome = "";
		
	public ErroreScommessa1Event(Giocatore giocatore, Integer idGiocatore){
		
		this.idGiocatore = idGiocatore;
		this.giocatore = giocatore;
	}



	public Giocatore getGiocatore() {
		return giocatore;
	}



	public Integer getIdGiocatore() {
		return idGiocatore;
	}


	public String getNome() {
		return nome;
	}
	
	

}
