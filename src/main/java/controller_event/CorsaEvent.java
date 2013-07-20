package controller_event;

import java.util.ArrayList;


import model.Corsa;
import model.Giocatore;
import model.Scuderia;

import view_event.HorseFeverEvent;

public class CorsaEvent implements HorseFeverEvent {
	

	private Corsa corsa;
	private ArrayList<Giocatore> elencoGiocatori;
	private ArrayList<Scuderia> elencoScuderie;
	private String nome = "";
	
	public CorsaEvent(Corsa corsa, ArrayList<Scuderia> elencoScuderie, ArrayList<Giocatore> elencoGiocatori){
		
		this.elencoGiocatori = elencoGiocatori;
		this.corsa = corsa;
		this.elencoScuderie = elencoScuderie;
	}

	public Corsa getCorsa() {
		return corsa;
	}
	
	public ArrayList<Scuderia> getElencoScuderie(){
		return elencoScuderie;
	}


	public String getNome() {
		return nome;
	}

	public ArrayList<Giocatore> getElencoGiocatori() {
		return elencoGiocatori;
	}




}
