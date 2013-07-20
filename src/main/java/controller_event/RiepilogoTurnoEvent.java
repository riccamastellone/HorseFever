package controller_event;

import java.util.ArrayList;

import model.Giocatore;
import view_event.HorseFeverEvent;

public class RiepilogoTurnoEvent implements HorseFeverEvent {

		
		
		private ArrayList<Giocatore> elencoGiocatori;
		private String nome = "";
		
		public RiepilogoTurnoEvent(ArrayList<Giocatore> elencoGiocatori){
			
			this.elencoGiocatori = elencoGiocatori;
		}



		public ArrayList<Giocatore> getElencoGiocatori() {
			return elencoGiocatori;
		}

		
		public String getNome() {
			return nome;
		}



}
