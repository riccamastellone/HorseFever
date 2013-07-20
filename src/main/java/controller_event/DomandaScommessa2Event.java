package controller_event;

import controller.ControllerInterface;
import model.Giocatore;
import view_event.HorseFeverEvent;

public class DomandaScommessa2Event implements HorseFeverEvent {

	private Giocatore giocatore;
	private Integer idGiocatore;
	private String nome = "";
		
	public DomandaScommessa2Event(Giocatore giocatore, Integer idGiocatore){

		this.giocatore = giocatore;
		this.idGiocatore = idGiocatore;
	}

	public Giocatore getGiocatore() {
		return giocatore;
	}

	public String getNome() {
		return nome;
	}

	public Integer getIdGiocatore() {
		return idGiocatore;
	}




}
