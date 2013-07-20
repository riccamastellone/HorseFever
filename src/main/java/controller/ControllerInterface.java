package controller;

import network.Client;
import view_event.*;


public interface ControllerInterface {
	

	public void setClient(Client client);
	
	public void initScuderie();
	
	public void setImpostazioniIniziali(Integer numGiocatori)  throws Exception;
	
	public void initGiocatore(NomeGiocatoreEvent e);
	
	public void faseScommessa1();
	
	public void faseScommessa2();
	
	public void piazzaScommessa1(PiazzaScommessaEvent e);
	
	public void piazzaScommessa2(PiazzaScommessa2Event e);
	
	public void truccaCorsa();
	
	public void piazzaTruccaCorsa(PiazzaTruccaCorsaEvent e);
	
	public void domandaScommessa2();
	
	public void valutaRispostaScommessa2(RispostaDomandaScommessaEvent e);
	
	public void faseCorsa();
	
	public void initCorsa();
	
	public void chiusuraTurno();
	
	public void controllaFine();
	
	public void fineGioco();
	
	public void valutaProssimaDomanda();
	
	public void valutaProssimaScommessa1();
		
	
		
}
