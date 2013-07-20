package controller_event;

import java.util.ArrayList;

import view_event.HorseFeverEvent;

import model.Giocatore;
import model.Scuderia;

public class Scommessa2Event implements HorseFeverEvent{
	
	private ArrayList<Scuderia> elencoScuderie;
	private Giocatore giocatore;
	private Integer idGiocatore;
	private String nome = "";
	
	public Scommessa2Event( ArrayList<Scuderia> elencoScuderie, Giocatore giocatore, Integer idGiocatore) {
		
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
