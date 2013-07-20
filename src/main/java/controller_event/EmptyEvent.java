package controller_event;

import java.io.Serializable;

import view_event.HorseFeverEvent;

public class EmptyEvent implements HorseFeverEvent, Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9219516503034445593L;
	private String nome;
	private Integer idGiocatore;
	
	public EmptyEvent(String nome) {
		
		this.nome = nome;
		
	}
	
	public EmptyEvent(String nome, Integer idGiocatore) {

		this.nome = nome;
		this.idGiocatore = idGiocatore;

	}

	public String getNome() {
		return nome;
	}

	public Integer getIdGiocatore() {
		return idGiocatore;
	}




	
}
