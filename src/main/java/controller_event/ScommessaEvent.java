package controller_event;

import java.util.ArrayList;

import model.Giocatore;
import model.Scuderia;


import view_event.HorseFeverEvent;

public class ScommessaEvent implements HorseFeverEvent {
	
	private ArrayList<Scuderia> elencoScuderie;
	private Giocatore giocatore;
	private Integer idGiocatore;
	private String nome = "";
	
	public ScommessaEvent( ArrayList<Scuderia> elencoScuderie, Giocatore giocatore, Integer idGiocatore) {
		
		this.elencoScuderie = elencoScuderie;
		this.giocatore = giocatore;
		this.idGiocatore = idGiocatore;
		
	}

	public ArrayList<Scuderia> getElencoScuderie() {
		return elencoScuderie;
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
