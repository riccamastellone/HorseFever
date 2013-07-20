package model;

import java.io.Serializable;

public class Scuderia implements Serializable {
	
	  																					
	private String colore;   															
	private int posizioneSegnalino = 0;
	private int quotazione;
	private boolean arrivato = false;
	private int numSegnaliniScommessa;

	public Scuderia(String colore, int quotazione) {
		
		this.setColore(colore);
		this.setQuotazione(quotazione);


	}

	public int getPosizioneSegnalino() {
		return posizioneSegnalino;
	}

	public void setPosizioneSegnalino(int posizioneSegnalino) {
		this.posizioneSegnalino = posizioneSegnalino;
	}


	public int getQuotazione() {
		return quotazione;
	}

	public void setQuotazione(int quotazione) {
		this.quotazione = quotazione;
	}

	public String getColore() {
		return colore;
	}

	public void setColore(String colore) {
		this.colore = colore;
	}
	

	public boolean isArrivato() {
		return arrivato;
	}

	public void setArrivato(boolean arrivato) {
		this.arrivato = arrivato;
	}

	public int getNumSegnaliniScommessa() {
		return numSegnaliniScommessa;
	}

	public void setNumSegnaliniScommessa(int numSegnaliniScommessa) {
		this.numSegnaliniScommessa = numSegnaliniScommessa;
	}
	
	



}
