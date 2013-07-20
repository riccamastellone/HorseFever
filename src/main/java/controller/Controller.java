package controller;


import java.io.Serializable;

import network.Client;

import model.CartaAzione;
import model.Giocatore;
import model.ModelInterface;
import model.Scuderia;
import View.Output;
import controller_event.CorsaEvent;
import controller_event.DomandaScommessa2Event;
import controller_event.EmptyEvent;
import controller_event.ErroreScommessa1Event;
import controller_event.ErroreScommessa2Event;
import controller_event.NomeGiocatoreControllerEvent;
import controller_event.RiepilogoTurnoEvent;
import controller_event.Scommessa2Event;
import controller_event.ScommessaEvent;
import controller_event.TruccaCorsaEvent;
import controller_event.VincitoreEvent;
import view_event.*;

public class Controller implements ControllerInterface,Serializable
{


	private ModelInterface model;

	private Output out;
	private Integer numeroGiocatori;
	private Integer giocatoriRimanentiGiro;

	public Controller(ModelInterface model, Output output) {
		this.model = model;
		this.out = output;
		model.initMazzi();
	}
	
	public int getNumeroGiocatori() {
		return this.numeroGiocatori;
	}
	
	
	
	
	/**
	 * inizializzo le scuderie e chiedo quanti giocatori vogliono giocare
	 */
	public void initScuderie() {
		
		out.chiediNumeroGiocatori(new EmptyEvent("numeroGiocatore"));
	}

	/**
	 * ricevo il numero iniziale di giocatori dalla view e setto le impostazioni iniziali di conseguenza
	 * chiedo come si chiamo il primo giocatore
	 * @throws Exception 
	 */
	public void setImpostazioniIniziali(Integer numGiocatori) throws Exception {
		if (numGiocatori < 2 || numGiocatori > 7) {
			throw new Exception();
		}
		
		model.initScuderie();
		this.numeroGiocatori = numGiocatori;
		this.giocatoriRimanentiGiro = this.numeroGiocatori;
		model.setNumTurniIniziali(this.numeroGiocatori);
		model.setNumeroSegnaliniScommessaIniziali(this.numeroGiocatori);
		out.chiediNomeGiocatore(new NomeGiocatoreControllerEvent(1));
	}

	/**
	 * ricevo il nome del giocatoe dalla view e lo istanzio
	 * continuo a chiedere il nome del giocatore finche non ho finito i partecipanti
	 * se ho finito sorteggio il primo giocatore e passo alla prima fase della scommessa
	 */
	public void initGiocatore(NomeGiocatoreEvent e) {
		model.initGiocatore(e.getNome());
		giocatoriRimanentiGiro--;
		if(giocatoriRimanentiGiro!=0){
			out.chiediNomeGiocatore(new NomeGiocatoreControllerEvent( this.numeroGiocatori - giocatoriRimanentiGiro + 1 ));
		}
		else {
			model.sorteggiaPrimoGiocatore();
			model.setGiocatoreCorrente(model.getIdPrimoGiocatore());
			model.ordinaScuderieByQuotazione(model.getScuderie());
			this.giocatoriRimanentiGiro = this.numeroGiocatori;
			out.loadingScommessa1(new EmptyEvent("loadingScommessa1", model.getIdGiocatoreCorrente()));
		}
	}
	
	/**
	 * partendo dal primo giocatore chiedo quanto vuole scommettere
	 */
	public void faseScommessa1() {
		Giocatore giocatore = model.getGiocatoreCorrente();
		if (giocatore.checkScomessaMinima(giocatore.getDenari())) {
			out.schermataScommessa1(new ScommessaEvent(model.getScuderie(), giocatore, model.getIdGiocatoreCorrente()));
		}
		else {
			out.schermataSaltaScommessa1(new ErroreScommessa1Event(giocatore, model.getIdGiocatoreCorrente()));
		}
		
	}
	
	/**
	 * valuto se rimangono fiocatori che devono ancora fare la scommessa 1
	 * CI ARRIVO SOLTANTO SE UN GIOCATORE NON E IN GRADO DI COPRIRE LA SCOMMESSA MINIMA
	 */
	public void valutaProssimaScommessa1() {
		giocatoriRimanentiGiro --;
		model.nextGiocatoreCorrente("orario");	
		if (giocatoriRimanentiGiro == 0) {
			this.giocatoriRimanentiGiro = this.numeroGiocatori;
			out.loadingCorsa(new EmptyEvent("loadingCorsa"));
		}
		else {
			
			faseScommessa1();
		}
		
	}
	
	/**
	 * ricevo la scommessa dalla view
	 * se e tutto corretto piazo la scommessa e passo al prossimo giocatore
	 * se non e corretto(non ho rispettato la scommessa minima ecc) richiedo la scommessa allo stesso giocatore
	 * se non ho piu giocatori a cui far piazzare la scommessa(idPrimoGiocatore == idGIocatoreCorrente) passo a truccaCorsa
	 */
	public void piazzaScommessa1(PiazzaScommessaEvent e) {
		if( !model.rispettaScommessaMinima(e.getGiocatore(), e.getValore()) ){
			out.warningScommessaMinima1(new EmptyEvent("warningScommessaMinima1",model.getIdGiocatoreCorrente()));
			
		}
		else if (!model.denariDisponibiliPerScommessa(e.getGiocatore(), e.getValore())) {
			//DOMANDA creare un altro evento o posso passare il giocatore in questo modo??
			out.warningDenariNonDisponibili1(new EmptyEvent("warningDenariNonDisponibili1",model.getIdGiocatoreCorrente()));
		}
		else if (!model.checkSegnaliniScommessaDisponibili(model.getScuderiabyColore(e.getColore()))) {
			out.warningSegnaliniNonDisponibili1(new EmptyEvent("warningSegnaliniNonDisponibili1",model.getIdGiocatoreCorrente()));
			
		}
		else {
			giocatoriRimanentiGiro --;
			Scuderia scuderia = model.getScuderiabyColore(e.getColore());
			Giocatore giocatore = model.getGiocatoreByNome(e.getGiocatore().getNome());
			model.piazzaPrimaScommessa(giocatore, e.getValore(), scuderia, e.getTipo());
			model.nextGiocatoreCorrente("orario");
			if (giocatoriRimanentiGiro == 0) {
				this.giocatoriRimanentiGiro = this.numeroGiocatori;
				model.setGiocatoreCorrente(model.getIdGiocatoreCorrente());
				out.loadingTruccaCorsa(new EmptyEvent("loadingTruccaCorsa",model.getIdGiocatoreCorrente()));
			}
			else {			
				faseScommessa1();
			}
		}
		
	}
	
	/**
	 * partendo dal giocatore prima del primo giocatore se vuole fare la seconda scommessa
	 * 
	 */
	public void domandaScommessa2() {
		Giocatore giocatore = model.getGiocatoreCorrente();
		out.domandaScommessa2(new DomandaScommessa2Event(giocatore, model.getIdGiocatoreCorrente()));
		
	}
	
	/**
	 * valuto se ho altre domande da fare o se ho finito
	 * QUI ARRIVO SOLO SE UN GIOCATORE VUOLE FARE LA SECONDA SCOMMESSA E NON HA AV^BBASTANZA DENARI PER LA SCOMMESSA MINIMA
	 */
	public void valutaProssimaDomanda(){
		giocatoriRimanentiGiro --;
		model.nextGiocatoreCorrente("antiorario");	
		if (giocatoriRimanentiGiro == 0) {
			this.giocatoriRimanentiGiro = this.numeroGiocatori;
			out.loadingCorsa(new EmptyEvent("loadingCorsa"));
		}
		else {
			
			domandaScommessa2();
		}
	}
	
	/**
	 * valuto la risposta alla domandaScommesssa2
	 * se il giocatore la vuole fare passo a scommessa2 se no avanzo in modo antiorario chiedendo ancora la domandaScommessa2
	 * se ho finito i giocatori(ho fatto tutto il giro) passo a faseCorsa
	 */
	public void valutaRispostaScommessa2(RispostaDomandaScommessaEvent e) {
		if (e.getScelta() == 1) {
			giocatoriRimanentiGiro --;
			model.nextGiocatoreCorrente("antiorario");
			
			if (giocatoriRimanentiGiro == 0) {
				this.giocatoriRimanentiGiro = this.numeroGiocatori;
				out.loadingCorsa(new EmptyEvent("loadingCorsa"));
			}
			else {
				
				domandaScommessa2();
			}
		}
		else {
			if (!model.rispettaScommessaMinima(e.getGiocatore(), e.getGiocatore().getDenari())) {
				out.schermataNonPuoiFareScommessa2(new ErroreScommessa2Event(e.getGiocatore(), model.getIdGiocatoreCorrente()));
				
			}
			else{
				faseScommessa2();
			}
		}
		
	}
	
	/**
	 * faccio piazzare la seconda scommessa
	 */
	public void faseScommessa2() {
		Giocatore giocatore = model.getGiocatoreCorrente();
		out.schermataScommessa2(new Scommessa2Event(model.getScuderie(), giocatore, model.getIdGiocatoreCorrente()));
		
	}
	
	/**
	 * valuto la scommessa ricevuta dalla view e se e tutto a posto la piazzo
	 * se no rimostro la schermata
	 */
	public void piazzaScommessa2(PiazzaScommessa2Event e) {
		if( !model.rispettaScommessaMinima(e.getGiocatore(), e.getValore()) ){
			out.warningScommessaMinima2(new EmptyEvent("warningScommessaMinima2", model.getIdGiocatoreCorrente()));
		}
		else if (!model.denariDisponibiliPerScommessa(e.getGiocatore(), e.getValore())) {
			//DOMANDA creare un altro evento o posso passare il giocatore in questo modo??
			out.warningDenariNonDisponibili2(new EmptyEvent("warningDenariNonDisponibili2", model.getIdGiocatoreCorrente()));
		}
		else if (!model.checkSegnaliniScommessaDisponibili(model.getScuderiabyColore(e.getColore()))) {
			out.warningSegnaliniNonDisponibili2(new EmptyEvent("warningSegnaliniNonDisponibili2", model.getIdGiocatoreCorrente()));
		}
		else {
			giocatoriRimanentiGiro --;
			model.nextGiocatoreCorrente("antiorario");
			Scuderia scuderia = model.getScuderiabyColore(e.getColore());
			Giocatore giocatore = model.getGiocatoreByNome(e.getGiocatore().getNome());
			String tipo ="";
			//posso fare questi controlli o li devo fare nel model??
			if (giocatore.getPrimaScommessa().getTipoScommessa().equals("piazzato")) {
				tipo = "vincente";
			}
			else {
				tipo = "piazzato";
			}
			model.piazzaSecondaScommessa(giocatore, e.getValore(), scuderia, tipo);

			if (giocatoriRimanentiGiro == 0) {
				this.giocatoriRimanentiGiro = this.numeroGiocatori;
				out.loadingCorsa(new EmptyEvent("loadingCorsa"));
			}
			else {
				
				domandaScommessa2();
			}
			}		
		
	}
	
	/**
	 * a partire dal primo giocatore chiedo quale carta azione vogliono giocare
	 * e su quale scuderia la vogliono giocare
	 */
	public void truccaCorsa() {
		
		Giocatore giocatore = model.getGiocatoreCorrente();
		CartaAzione carta1 = giocatore.getCartaAzione1();
		CartaAzione carta2 = giocatore.getCartaAzione2();
		out.schermataTruccaCorsa(new TruccaCorsaEvent(giocatore, carta1, carta2, model.getScuderie(), model.getIdGiocatoreCorrente()));	
		
	}
	
	/**
	 * 
	 * ricevo dalla view la scelta trucca corsa del giocatore e la applico
	 * se ho finito i giocatori passo alla domandaScommessa 2 se no continuo
	 */
	public void piazzaTruccaCorsa(PiazzaTruccaCorsaEvent e) {
		Giocatore giocatore = model.getGiocatoreByNome(e.getGiocatore().getNome());
		CartaAzione cartaAzione = giocatore.getCartaAzione2();
		if(e.getScelta() == 1){
			cartaAzione = giocatore.getCartaAzione1();
		}

		model.scuderiaEffettoCartaAzione(giocatore, cartaAzione, e.getColore());
		giocatoriRimanentiGiro --;
		model.nextGiocatoreCorrente("orario");
		if (giocatoriRimanentiGiro == 0) {
			this.giocatoriRimanentiGiro = this.numeroGiocatori;
			if (model.getIdPrimoGiocatore() == 0) {
				model.setGiocatoreCorrente(model.getGiocatori().size() - 1);
			} else {
				model.setGiocatoreCorrente(model.getIdPrimoGiocatore() - 1);
			}
			out.loadingScommessa2(new EmptyEvent("loadingScommessa2",model.getIdGiocatoreCorrente()));

		} else {

			truccaCorsa();
		}

	}
	
	/**
	 * inizializzo l oggetto corsa 
	 */
	public void initCorsa() {
		model.initCorsa();
		faseCorsa();
	}
	
	/**
	 * 
	 * se la corsa non e finita(tutte le scuderie sono arrivate)
	 * faccio un round e lo faccio mostrare alla view passandogli l oggetto corsa
	 * quando e finita la corsa passo a chiusura turno
	 */
	public void faseCorsa() {

		if (model.isCompleta() == false) { 
			model.roundCorsa();
			out.mostraCorsa(new CorsaEvent(model.getCorsa(), model.getScuderie(), model.getGiocatori()));
		}
		else if (model.isCompleta() == true) {
			out.loadingChiusuraTurno(new EmptyEvent("loadingChiusuraTurno"));
		}
	}
	
	/**
	 * pago i giocatori e faccio un nuovo ordine scuderie
	 * marco i giocatori perdenti(MA NON GLI ELIMINO) e marco i giocatori che hanno perso i pv e sottraggo 2 pv
	 */
	public void chiusuraTurno() {
		model.fasePagamenti();
		model.nuovoOrdineScuderie();
		model.marcaGiocatoriPerditaPv();
		model.marcaGiocatoriPerdenti();
		out.schermataRiepilogoTurno(new RiepilogoTurnoEvent(model.getGiocatori()));
	}
	
	
	/**
	 * rimuovo i giocatori perdenti
	 * assegno le nuove carte ai personaggi
	 * reinizializzo le scommesse
	 * setto il nuovo primo gioocatore
	 * se non sono finit i turni o non ci sono piu giocatori decreto la fine del gioco
	 */
	public void controllaFine() {
		model.rimuoviGiocatoriPerdenti();
		this.numeroGiocatori = model.getGiocatori().size();
		this.giocatoriRimanentiGiro = this.numeroGiocatori;
		//decremento di uno il numero di turni disponibili
		model.setNumTurni(model.getNumTurni() - 1);
		if ((model.getGiocatori().size() == 1) || (model.getGiocatori().size() == 0) || (model.getNumTurni() == 0)) {
			fineGioco();
		}
		else {
			model.setNumeroSegnaliniScommessaIniziali(model.getGiocatori().size());
			model.reinitScuderie();
			model.reinitGiocatori();
			model.nextPrimoGiocatore();
			model.setGiocatoreCorrente(model.getIdGiocatoreCorrente());
			out.loadingScommessa1(new EmptyEvent("loadingScommessa1",model.getIdGiocatoreCorrente()));
		}
	}
	
	/**
	 * se non ci sono piu giocatori vuol dire che non ha vinto nessuno
	 * se no decreto il vincitore perche sono finiti i turni
	 */
	public void fineGioco() {
		
		if ( model.getGiocatori().size() == 0){
			out.schermataNessunVincitore(new EmptyEvent("nessunVincitore"));
		}
		else {
			String vincitore = model.decretoVincitore();
			out.schermataVincitore(new VincitoreEvent(vincitore));
		}
	}




	public void setClient(Client client) {
		// TODO Auto-generated method stub
	}


	
	

}
