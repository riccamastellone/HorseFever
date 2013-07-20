package controller_event;

import java.util.ArrayList;

import controller.ControllerInterface;

import model.CartaAzione;
import model.Giocatore;
import model.Scuderia;


import view_event.HorseFeverEvent;

public class TruccaCorsaEvent implements HorseFeverEvent {

	private CartaAzione carta1;
	private CartaAzione carta2;
	private Giocatore giocatore;
	private ArrayList<Scuderia> scuderie;
	private Integer idGiocatore;
	private String nome = "";

	
	public TruccaCorsaEvent(Giocatore giocatore, CartaAzione carta1, CartaAzione carta2, ArrayList<Scuderia> scuderie, Integer idGiocatore) {
		
		this.carta1 = carta1;
		this.carta2 = carta2;
		this.giocatore = giocatore;
		this.scuderie = scuderie;
		this.idGiocatore = idGiocatore;
	}

	public CartaAzione getCarta1() {
		return carta1;
	}


	public CartaAzione getCarta2() {
		return carta2;
	}

	public Giocatore getGiocatore() {
		return giocatore;
	}

	public ArrayList<Scuderia> getScuderie() {
		return scuderie;
	}


	public String getNome() {
		return nome;
	}

	public Integer getIdGiocatore() {
		return idGiocatore;
	}




}
