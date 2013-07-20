package model;

import java.util.ArrayList;

public interface ModelInterface {

	public int getNumTurni();

	public int getIdGiocatoreCorrente();

	/**
	 * setto il numero di turni da disputare in base al numero di giocatori
	 * DA CHIAMARE SOLO ALL INIZIO
	 * @param numGiocatori
	 */
	public void setNumTurniIniziali(int numGiocatori);

	/**
	 * serve per decrementare di uno il numero di turni da disputare
	 * @param numturni
	 */
	public void setNumTurni(int numturni);

	public void initMazzi();

	public void initGiocatore(String nome);

	/**
	 *
	 * A seconda del numero dei giocatori tira a caso un numero e sceglie il
	 * primo giocatore
	 *
	 */
	public void sorteggiaPrimoGiocatore();

	/**
	 *
	 * per ogni colore istanzio una scuderia e per ogni scuderia assegno una
	 * quotazine a caso estendibile: basta aggiungere un colore al'attributo
	 * colori
	 *
	 *
	 */
	public void initScuderie();

	public void setNumeroSegnaliniScommessaIniziali(int numeroGiocarori);

	/**
	 * guardo se sono disponibili ancora segnalini scommessa
	 * ritorna true se ci sono
	 * @param scuderia
	 * @return
	 */
	public boolean checkSegnaliniScommessaDisponibili(Scuderia scuderia);

	public void togliSegnalinoScommessa(Scuderia scuderia);

	/**
	 * prova classe anonima per ordinare scuderie in base a quotazione
	 */
	public void ordinaScuderieByQuotazione(ArrayList<Scuderia> scuderie);

	/**
	 *
	 * ordina le scuderie in modo decrescente
	 *
	 * in alto c'e la scuderia con la posizioneSegnalino piu alta
	 *
	 *
	 * @param scuderie
	 */
	public void ordinaScuderieByPosizione(ArrayList<Scuderia> scuderie);

	public Scuderia getScuderiabyColore(String colore);

	public Giocatore getGiocatoreByNome(String nome);

	/**
	 * Ritorna la scuderia con la quotazione passata
	 * @param quotazione Int 
	 * @return
	 */
	public Scuderia getScuderiaByQuotazione(int quotazione);

	/**
	 * passo al prossimo giocatore corrente
	 * @param senso puo' essere 'orario' o 'antiorario'
	 * @return Giocatore
	 */
	public Giocatore nextGiocatoreCorrente(String senso);

	/**
	 * 
	 * a fine turno fa scorrere il Primo giocatore
	 */
	public void nextPrimoGiocatore();

	public ArrayList<Giocatore> getGiocatori();

	public ArrayList<Scuderia> getScuderie();

	public Giocatore getPrimoGiocatore();

	public Giocatore getGiocatoreCorrente();

	public void setGiocatoreCorrente(int id);

	public int getIdPrimoGiocatore();

	/**
	 * Metodo chiamato per applicare le carte Azione durante le varie fasi della corsa.
	 * Controlla che non siano escluse chiamando la checkCartaAzione e successivamente
	 * doApplyCartaAzione
	 * @param when String fase della corsa in cui parsare le carta azione possedute dai giocatori
	 */
	public void applyCarteAzione(String when);

	/**
	 * 
	 * inizio la corsa e applico le carte azione che hanno effetto sin dall inizio
	 */
	public void initCorsa();

	public boolean isCompleta();

	public Corsa getCorsa();

	/**
	 * controlla se i giocatori hanno vinto qualche scommessa e paga di conseguenza
	 * paga anche i proprietari delle scuderie piazzate bene
	 */
	public void fasePagamenti();

	/**
	 * 
	 * controllo che il giocatore abbia abbastanza denari per rispettare la scommessa minima
	 * @param giocatore
	 * @param valore
	 * @return
	 */
	public boolean rispettaScommessaMinima(Giocatore giocatore, int valore);

	/**
	 * controllo che il giocatore abbia abbastanza denari per piazzare la scommessa
	 * @param giocatore
	 * @param valore
	 * @return
	 */
	public boolean denariDisponibiliPerScommessa(Giocatore giocatore, int valore);

	public void piazzaPrimaScommessa(Giocatore giocatore, int valoreScommessa,
			Scuderia scuderiaScelta, String tipoScelto);

	public void piazzaSecondaScommessa(Giocatore giocatore,
			int valoreScommessa, Scuderia scuderiaScelta, String tipoScelto);

	/**
	 * 
	 * aggiorna le quotazioni in base alla corsa.getClassifica() finale
	 * 
	 * posso avere piu scuderie con la stessa quotazione
	 */
	public void nuovoOrdineScuderie();

	public void scuderiaEffettoCartaAzione(Giocatore giocatore,
			CartaAzione carta, String colore);

	/**
	 * 
	 * assegno ai giocatori le nuove carte azione
	 * e reinizializzo le impostazioni utili per la fine del turno
	 */
	public void reinitGiocatori();

	public String decretoVincitore();

	public void ordinaGiocatoreByPv(ArrayList<Giocatore> giocatori);

	public void ordinaGiocatoreByDenari(ArrayList<Giocatore> giocatori);

	public void roundCorsa();

	/**
	 * tolgo dalla lista elenco giocatori quelli che hanno perso
	 */
	public void rimuoviGiocatoriPerdenti();

	/**
	 * reinizializzo i segnalini posizione alla fine del turno
	 */
	public void reinitScuderie();

	/**
	 * marco i giocatori che hanno perso in attesa di rimuoverli
	 * 
	 */
	public void marcaGiocatoriPerdenti();

	/**
	 * marco i giocatori che non possono soddisfare la scommessa minima 
	 * nel prossimo turno e gli sottraggo 2 pv
	 * 
	 */
	public void marcaGiocatoriPerditaPv();

}