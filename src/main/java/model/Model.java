package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Random;

public class Model implements Serializable, ModelInterface{

	private ArrayList<Giocatore> elencoGiocatori;
	private ArrayList<Scuderia> elencoScuderie;
	private String scuderiaCarteAzionePositiveEscluse = null;
	private String scuderiaCarteAzioneNegativeEscluse = null;
	private Corsa corsa;
	private Mazzo mazzoPersonaggio;
	private Mazzo mazzoAzione;

	private int idGiocatoreCorrente;
    private int idPrimoGiocatore;
    private int numTurni;
    private static Model _instance;

    private Model(){
    	
    	this.elencoGiocatori = new ArrayList<Giocatore>();
		this.elencoScuderie = new ArrayList<Scuderia>();

    }
    
    public static Model getInstance() {

		if (_instance == null) {
			_instance = new Model();
		}

		return _instance;
	}

	
	
	
	/* (non-Javadoc)
	 * @see model.ModelInterface#getNumTurni()
	 */
	public int getNumTurni() {
		return numTurni;
	}
	
	/* (non-Javadoc)
	 * @see model.ModelInterface#getIdGiocatoreCorrente()
	 */
	public int getIdGiocatoreCorrente() {
		return idGiocatoreCorrente;
	}

	
	/* (non-Javadoc)
	 * @see model.ModelInterface#setNumTurniIniziali(int)
	 */
	public void setNumTurniIniziali(int numGiocatori) {
		this.numTurni = Config.numeroTurni[numGiocatori - 2];
	}
	
	/* (non-Javadoc)
	 * @see model.ModelInterface#setNumTurni(int)
	 */
	public void setNumTurni(int numturni) {
		this.numTurni = numturni;
	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#initMazzi()
	 */
	public void initMazzi(){
		   // Inizializzo il mazzo Carte Personaggio
        this.mazzoPersonaggio = generaMazzoPersonaggio();
        
        //Inizializzo il mazzo Carte Azione
        this.mazzoAzione = generaMazzoAzione();
	}
	
	 /**
     * Inizializzo il mazzo di carte Personaggio per l'assegnazione
     *
     * @return mazzo Mazzo di Carte Personaggio
     */
    private Mazzo generaMazzoPersonaggio() {
        CartaPersonaggio c = new CartaPersonaggio();
        Mazzo mazzo = new Mazzo();
        c.loadCarte(mazzo);
        mazzo.mescola();
        return mazzo;
    }
    
    /**
     * Inizializzo il mazzo di carte Azione per l'assegnazione
     *
     * @return mazzo Mazzo di Carte Azione
     */
    private Mazzo generaMazzoAzione() {
        CartaAzione c = new CartaAzione();
        Mazzo mazzo = new Mazzo();
        c.loadCarte(mazzo);
        mazzo.mescola();
        return mazzo;
    }
    
    private String correggiNome(String nome) {
    	if(getGiocatoreByNome(nome) != null) {
    		nome = nome + '1';
    		return correggiNome(nome);
    	}
    	// Non esiste quindi tutto ok
    	return nome;
    }
    
    /* (non-Javadoc)
	 * @see model.ModelInterface#initGiocatore(java.lang.String)
	 */
    public void initGiocatore(String nome) {
    	
    	// Controlliamo che non esista gia un giocatore con questo nome e, nel caso esista, appendiamo un numero;
    	nome = correggiNome(nome);
    	
	// Pesco le carte da assegnare
    CartaPersonaggio cp = (CartaPersonaggio) mazzoPersonaggio.pesca();
    CartaAzione ca1 = (CartaAzione) mazzoAzione.pesca();
    CartaAzione ca2 = (CartaAzione) mazzoAzione.pesca();
    
    elencoGiocatori.add(new Giocatore(nome, cp, ca1, ca2));
    
    }

	
    /* (non-Javadoc)
	 * @see model.ModelInterface#sorteggiaPrimoGiocatore()
	 */
    public void sorteggiaPrimoGiocatore() {

		int lunghezza = elencoGiocatori.size();
		Random rand = new Random();
		int id = rand.nextInt(lunghezza);
		this.idPrimoGiocatore = id;
		this.idGiocatoreCorrente = id;

	}
	
	/**
     * Serve per il setup iniziale delle quotazioni
     *
     */
    private void initQuotazioni(ArrayList<Integer> quotazioni) {
        // arrivo a cofig.colori.length +1 perche' parto da 2
        for (int i = 2; i <= Config.colori.length + 1; i++) {
            quotazioni.add(i);
        }
        Collections.shuffle(quotazioni);
        
    }
    
    /* (non-Javadoc)
	 * @see model.ModelInterface#initScuderie()
	 */
    public void initScuderie() {
        
        ArrayList<Integer> quotazioni = new ArrayList<Integer>();
        initQuotazioni(quotazioni);
        int j = 0;
        for (int i = 0; i < Config.colori.length; i++) {
            Scuderia scuderia = new Scuderia(Config.colori[i],quotazioni.get(j));
            elencoScuderie.add(scuderia);
            j++;
        }
        ordinaScuderieByQuotazione(elencoScuderie);
    }
    
    /* (non-Javadoc)
	 * @see model.ModelInterface#setNumeroSegnaliniScommessaIniziali(int)
	 */
    public void setNumeroSegnaliniScommessaIniziali(int numeroGiocarori) {
		
    	for (Scuderia scuderia : elencoScuderie) {
			scuderia.setNumSegnaliniScommessa(Config.numeroSegnaliniScommessa[numeroGiocarori - 2]);
		}
	}
    
    /* (non-Javadoc)
	 * @see model.ModelInterface#checkSegnaliniScommessaDisponibili(model.Scuderia)
	 */
    public boolean checkSegnaliniScommessaDisponibili(Scuderia scuderia) {
		if (scuderia.getNumSegnaliniScommessa() == 0) {
			return false;
		}
		return true;
	}
    
    /* (non-Javadoc)
	 * @see model.ModelInterface#togliSegnalinoScommessa(model.Scuderia)
	 */
    public void togliSegnalinoScommessa(Scuderia scuderia) {
		scuderia.setNumSegnaliniScommessa(scuderia.getNumSegnaliniScommessa() - 1);
	}
    
    /* (non-Javadoc)
	 * @see model.ModelInterface#ordinaScuderieByQuotazione(java.util.ArrayList)
	 */
	public void ordinaScuderieByQuotazione(ArrayList<Scuderia> scuderie) {

		Collections.sort(scuderie, new Comparator<Scuderia>() {
			public int compare(Scuderia scud1, Scuderia scud2) {
				return scud1.getQuotazione() - scud2.getQuotazione();
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see model.ModelInterface#ordinaScuderieByPosizione(java.util.ArrayList)
	 */
   public void ordinaScuderieByPosizione(ArrayList<Scuderia> scuderie) {

       Collections.sort(scuderie, new Comparator<Scuderia>() {
           public int compare(Scuderia scud1, Scuderia scud2) {
               return scud2.getPosizioneSegnalino() - scud1.getPosizioneSegnalino();

           }
       });
   }
	
	/* (non-Javadoc)
	 * @see model.ModelInterface#getScuderiabyColore(java.lang.String)
	 */
	public Scuderia getScuderiabyColore(String colore) {

		int i = 0;
		Scuderia scuderia = elencoScuderie.get(i);
		while (!scuderia.getColore().equals(colore)) {

			i++;
			scuderia = elencoScuderie.get(i);
		}

		return scuderia;

	}
	
	/* (non-Javadoc)
	 * @see model.ModelInterface#getGiocatoreByNome(java.lang.String)
	 */
	public Giocatore getGiocatoreByNome(String nome) {
		
		// Nessun giocatore 
		if(elencoGiocatori.size() == 0) {
			return null;
		}
		
		int i = 0;
		Giocatore giocatore = elencoGiocatori.get(i);
		
		while (!giocatore.getNome().equals(nome)) {
			i++;
			try {
				giocatore = elencoGiocatori.get(i);
			} catch (Exception e) {
				return null;
			}
			
		}

		return giocatore;

	}
	
	/* (non-Javadoc)
	 * @see model.ModelInterface#getScuderiaByQuotazione(int)
	 */
	public Scuderia getScuderiaByQuotazione(int quotazione) {
		int i = 0;
		Scuderia scuderia = elencoScuderie.get(i);
		while (scuderia.getQuotazione() != quotazione) {
			i++;
			scuderia = elencoScuderie.get(i);
			
		}
		return scuderia;
	}
	
	/* (non-Javadoc)
	 * @see model.ModelInterface#nextGiocatoreCorrente(java.lang.String)
	 */
   public Giocatore nextGiocatoreCorrente(String senso) {
       
       // Nel caso nessun parametro venga passato usiamo il senso orario
       if(senso == null) {
           senso = "orario";
       }
       
       
       if (senso.equals("orario")) {
           
           if(idGiocatoreCorrente == elencoGiocatori.size()-1) {  //ho messo giocatori.size-1 perche l indice parte da 0 e quindi sforava prima di ritornare a capo
               idGiocatoreCorrente = 0;
           } else {
               idGiocatoreCorrente++;
           }
           
           
       } else if (senso.equals("antiorario")) {
           
           if(idGiocatoreCorrente == 0) {
               idGiocatoreCorrente = elencoGiocatori.size()-1; //ho messo giocatori.size-1 perche l indice parte da 0 e quindi sforava prima di ritornare a capo
           } else {
               idGiocatoreCorrente--;
           }
           
           
       } else {
           throw new IllegalArgumentException(); //chi prende questa eccezione??
       }
       
       
       // Abbiamo aggiornato il l'idGiocatoreCorrente
       // Quindi ritorno il giocatore corrente
       return getGiocatoreCorrente();
       
   }

   /* (non-Javadoc)
 * @see model.ModelInterface#nextPrimoGiocatore()
 */
	public void nextPrimoGiocatore() {

		if (idPrimoGiocatore >= elencoGiocatori.size() - 1) { 												
			idPrimoGiocatore = 0;
		} else {
			idPrimoGiocatore++;
		}
		idGiocatoreCorrente = idPrimoGiocatore;

	}
    
    /* (non-Javadoc)
	 * @see model.ModelInterface#getGiocatori()
	 */
    public ArrayList<Giocatore> getGiocatori(){
    	return elencoGiocatori;
    }
    
    /* (non-Javadoc)
	 * @see model.ModelInterface#getScuderie()
	 */
    public ArrayList<Scuderia> getScuderie() {
		return elencoScuderie;
	}
    
    /* (non-Javadoc)
	 * @see model.ModelInterface#getPrimoGiocatore()
	 */
    public Giocatore getPrimoGiocatore(){
    	return elencoGiocatori.get(idPrimoGiocatore);
    }
    
    /* (non-Javadoc)
	 * @see model.ModelInterface#getGiocatoreCorrente()
	 */
    public Giocatore getGiocatoreCorrente() {
        return elencoGiocatori.get(idGiocatoreCorrente);
    }
    
    /* (non-Javadoc)
	 * @see model.ModelInterface#setGiocatoreCorrente(int)
	 */
    public void setGiocatoreCorrente(int id) {
        idGiocatoreCorrente = id;
    }
    
    /* (non-Javadoc)
	 * @see model.ModelInterface#getIdPrimoGiocatore()
	 */
    public int getIdPrimoGiocatore() {
		return idPrimoGiocatore;
	}
    
    
    
    /* (non-Javadoc)
	 * @see model.ModelInterface#applyCarteAzione(java.lang.String)
	 */
    public void applyCarteAzione(String when) {
        // Scorro tutti i giocatori
        for (Giocatore giocatore : elencoGiocatori) {
            CartaAzione carta1 = giocatore.getCartaAzioneGiocata();
            
            
            Scuderia scuderia1 = getScuderiabyColore(carta1.getScuderiaEffetto());
            
            
            //Controlliamo prima che non siano quelle che annullano gli effetti
            if (when.equals("start")) {
                if (carta1.getElement().equals("carte_azione")) {
                    if (carta1.getCondition().equals("rosse")) {
                        this.scuderiaCarteAzioneNegativeEscluse = scuderia1.getColore();
                    } else {
                        this.scuderiaCarteAzionePositiveEscluse = scuderia1.getColore();
                    }
                    
                }
                
            }
            
            // Guardiamo se la carta 1 va applicata adesso
            if (carta1.getWhen().equals(when)) {
                if (checkCartaAzione(carta1, scuderia1) == true) {
                    doApplyCartaAzione(carta1, scuderia1, giocatore);
                }
            }
            
            
        }
        
    }
    /**
     * Controllo se posso applicare la carta azione
     * @param carta
     * @param scuderia
     * @return
     */
    private boolean checkCartaAzione(CartaAzione carta, Scuderia scuderia) {
        
        if (carta.getTipo().equals("rossa")) {
            if (scuderia.getColore().equals(this.scuderiaCarteAzioneNegativeEscluse)) {
                return false;
            }
        } else if (carta.getTipo().equals("verde")) {
            if (scuderia.getColore().equals(this.scuderiaCarteAzionePositiveEscluse)) {
                return false;
            }
            
        }
        
        return true;
        
    }
    
    private void doApplyCartaAzione(CartaAzione carta, Scuderia scuderia, Giocatore giocatore) {
        
        // A cosa va applicata?
        String element = carta.getElement();
        
        if (element.equals("quotazione")) {
            int quotazione = scuderia.getQuotazione();
            int newQuotazione = quotazione + carta.getValue();
            
            // Prevediamo i casi in cui la quotazione e' gia' massima o gia' minima
            if(newQuotazione > Config.colori.length + 1) {
                newQuotazione = 7;
            } else if(newQuotazione < 2) {
                newQuotazione = 2;
            }
            // Setto la nuova quotazione al cavallo
            scuderia.setQuotazione(newQuotazione);
        }
        else if (element.equals("carta_movimento")) {
            
            // Nel caso ci sia la condizione di primo o ultimo
            // corsa.getClassifica()to, controllo la posizione effettiva
            // e nel caso non sia rispettata, esco.
            // Controllo anche che non sia la partenza (ovvero il primo round)
            if(carta.getCondition() != null &&  carta.getCondition().equals("first_position")) {
                if(!corsa.checkIfFirst(scuderia) || corsa.getRoundCounter() == 1) {
                    return;
                }
            }
            
            if(carta.getCondition() != null && carta.getCondition().equals("last_position")) {
                if(!corsa.checkIfLast(scuderia) || corsa.getRoundCounter() == 1) {
                    return;
                }
            }
            
            // Nel caso sia stato chiamato il momento del traguardo, se
            // il cavallo non e' veramente arrivato, esco.
            if(carta.getWhen().equals("traguardo") && scuderia.getPosizioneSegnalino() < Config.caselleBoard) {
                return;
            }
            // Si ferma al traguardo
            if(carta.getWhen().equals("traguardo") && carta.getTipo().equals("rossa")) {
                scuderia.setPosizioneSegnalino(Config.caselleBoard);
                return;
            }
            
            int newPosizione;
            
            // Prendo i dati della carta Movimento
            CartaMovimento cartaMovimento = corsa.getLastCartaMovimento();
            int spostamento = cartaMovimento.getSpostamenti().get(scuderia.getQuotazione() - 2);
            
            if(carta.getAction().equals("override")) {
                // Tolgo la carta Movimento e metto la carta azione
                newPosizione = scuderia.getPosizioneSegnalino() - spostamento + carta.getValue();
            }
            else {
                if(carta.getValue() > spostamento) {
                    // Annullo l'effetto della carta Movimento e impedisco che il cavallo
                    // retroceda
                    newPosizione = scuderia.getPosizioneSegnalino() - spostamento;
                }
                else {
                    
                    // Aggiungo il valore della carta Azione
                    newPosizione = scuderia.getPosizioneSegnalino() + carta.getValue();
                }
            }
            
            scuderia.setPosizioneSegnalino(newPosizione);
        }
        else if (element.equals("photofinish")) {
            if(carta.getTipo().equals("verde")) {
                corsa.setFotoFinishVince(scuderia);
            } else {
                corsa.setFotoFinishPerde(scuderia);
            }
        }
        else if (element.equals("sprint")) {
            
            if(carta.getTipo().equals("verde")) {
                if(carta.getAction().equals("override")) {
                    corsa.setSprintOvveride2(scuderia);
                } else {
                    corsa.setSprintAdd1(scuderia);
                }
            } else {
                if(carta.getAction().equals("override")) {
                    corsa.setSprintBlock(scuderia);
                } else {
                    corsa.setSprintSubtract1(scuderia);
                }
            }
            
        }
    }
    
    
    
    /* (non-Javadoc)
	 * @see model.ModelInterface#initCorsa()
	 */
	public void initCorsa() {
		
		this.corsa = new Corsa();
		applyCarteAzione("start");
		applyCarteAzione("photofinish");
		applyCarteAzione("sprint");

	}
    
    
    /* (non-Javadoc)
	 * @see model.ModelInterface#isCompleta()
	 */
    public boolean isCompleta(){
    	return corsa.corsaCompleta();
    }
    
    /* (non-Javadoc)
	 * @see model.ModelInterface#getCorsa()
	 */
    public Corsa getCorsa() {
    	return corsa;		
	}
    
    /* (non-Javadoc)
	 * @see model.ModelInterface#fasePagamenti()
	 */
    public void fasePagamenti(){
        
        for (Giocatore giocatore : elencoGiocatori) {
            //controllo la prima scommessa
            pagaScommessa(giocatore.getPrimaScommessa(), giocatore);
            //nel caso il giocatore abbia effettuato anche la seconda scommessa pago anche quella
            if (giocatore.getSecondaScommessa() != null){
                pagaScommessa(giocatore.getSecondaScommessa(), giocatore);
            }
            //controllo se il giocatore e un proprietario di scuderia piazzata
            pagaProprietarioScuderia(giocatore);
            
        }
        
        
    }
    
    /* (non-Javadoc)
	 * @see model.ModelInterface#rispettaScommessaMinima(model.Giocatore, int)
	 */
    public boolean rispettaScommessaMinima(Giocatore giocatore, int valore) {
    	
    	if ( giocatore.checkScomessaMinima(valore) ){
			return true;
		}
		return false;
	}
    
    
    /* (non-Javadoc)
	 * @see model.ModelInterface#denariDisponibiliPerScommessa(model.Giocatore, int)
	 */
    public boolean denariDisponibiliPerScommessa(Giocatore giocatore, int valore) {
    	
    	if ( !giocatore.checkDenariDisponibiliPerScommessa(valore) ){
    		return false;
    	}
    	return true;

	}
    
	/* (non-Javadoc)
	 * @see model.ModelInterface#piazzaPrimaScommessa(model.Giocatore, int, model.Scuderia, java.lang.String)
	 */
	public void piazzaPrimaScommessa(Giocatore giocatore, int valoreScommessa, Scuderia scuderiaScelta, String tipoScelto) {

		giocatore.setPrimaScommessa(valoreScommessa, scuderiaScelta,
				tipoScelto);
		scuderiaScelta.setNumSegnaliniScommessa(scuderiaScelta.getNumSegnaliniScommessa() - 1);

	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#piazzaSecondaScommessa(model.Giocatore, int, model.Scuderia, java.lang.String)
	 */
	public void piazzaSecondaScommessa(Giocatore giocatore, int valoreScommessa, Scuderia scuderiaScelta, String tipoScelto) {

		giocatore.setSecondaScommessa(valoreScommessa, scuderiaScelta,
				tipoScelto);
		scuderiaScelta.setNumSegnaliniScommessa(scuderiaScelta.getNumSegnaliniScommessa() - 1);
	}

	
	/**
	 * pago i giocatori che hanno vinto e setto l'attributo importoVincita da usare poi nella schermata di riepilogo
	 * @param scommessa
	 * @param giocatore
	 */
	private void pagaScommessa(Scommessa scommessa, Giocatore giocatore) {

		if (scommessa.getTipoScommessa().equals("vincente")) {
			// vinco la scommessa vincente
			if (corsa.getClassifica().get(0)
					.equals(scommessa.getScuderiaScommessa())) {
				int importoDaPagare = (corsa.getClassifica().get(0)
						.getQuotazione() * scommessa.getValore());
				giocatore.setImportoVinto(importoDaPagare);
				giocatore.setVincitaPv(3);
				giocatore.setDenari(giocatore.getDenari() + importoDaPagare);
				giocatore.setPv(giocatore.getPv() + 3);

			}
		} else  if (scommessa.getTipoScommessa().equals("piazzato")){
			// vnco la scommessa piazzato
			if ((corsa.getClassifica().get(0).equals(scommessa
					.getScuderiaScommessa()))
					|| (corsa.getClassifica().get(1).equals(scommessa
							.getScuderiaScommessa()))
					|| (corsa.getClassifica().get(2).equals(scommessa
							.getScuderiaScommessa()))) {
				int importoDaPagare = (2 * scommessa.getValore());
				giocatore.setImportoVinto(importoDaPagare);
				giocatore.setVincitaPv(1);
				giocatore.setDenari(giocatore.getDenari() + importoDaPagare);
				giocatore.setPv(giocatore.getPv() + 1);

			}

		}
	}

	/**
	 * paga i giocatori che hanno scuderie piazzate bene
	 * 
	 * @param giocatore
	 */
	private void pagaProprietarioScuderia(Giocatore giocatore) {

		if (corsa.getClassifica().get(0).getColore()
				.equals(giocatore.getColoreScuderiaProprietario())) {
			giocatore.setDenari(giocatore.getDenari() + 600);
			giocatore.setVincitaScuderia(600);
		} else if (corsa.getClassifica().get(1).getColore()
				.equals(giocatore.getColoreScuderiaProprietario())) {
			giocatore.setDenari(giocatore.getDenari() + 400);
			giocatore.setVincitaScuderia(400);
		} else if (corsa.getClassifica().get(2).getColore()
				.equals(giocatore.getColoreScuderiaProprietario())) {
			giocatore.setDenari(giocatore.getDenari() + 200);
			giocatore.setVincitaScuderia(200);
		}

	}

	/* (non-Javadoc)
	 * @see model.ModelInterface#nuovoOrdineScuderie()
	 */
	public void nuovoOrdineScuderie() {
		for (Scuderia scuderia : elencoScuderie) {
			int posizione = scuderia.getQuotazione();
			int trovato = -1;
			int nuovaQuotazione = 0;
			// cerco se la scuderia e presente nelle posizioni superiori o
			// uguali a quelle della sua quotazione
			for (int i = 0; i <= posizione - 2; i++) {
				// se trovo la scuderia ritorno l'indice di dove e stato trovato
				if (corsa.getClassifica().get(i).equals(scuderia)) {
					trovato = i;
					break;
				}
			}
			// se non ho trovato la scuderia vuol dire che sono arrivato piu in
			// basso e prendo un punto di quotazione (quotazione piu alta e peggio)
			if (trovato == -1) {
				nuovaQuotazione = scuderia.getQuotazione() + 1;
			}
			// se ho trovato e sono sulla stessa casella la quotazione rimane
			// uguale
			else if (trovato == posizione - 2) {
				nuovaQuotazione = scuderia.getQuotazione();

			}
			// altrimenti decremento di uno la quotazione
			else {
				nuovaQuotazione = scuderia.getQuotazione() - 1;
			}
			
			//controllo di non sforare le quotazioni permesse
			if (nuovaQuotazione < 2) {
				nuovaQuotazione = 2;
			}
			else if (nuovaQuotazione > Config.colori.length + 1) {
				nuovaQuotazione = Config.colori.length + 1;
			} 
				
			
			scuderia.setQuotazione(nuovaQuotazione);

		}
		
		//ordino le scuderie secondo le nuove quotazioni
		ordinaScuderieByQuotazione(elencoScuderie);
	}
	
	/* (non-Javadoc)
	 * @see model.ModelInterface#scuderiaEffettoCartaAzione(model.Giocatore, model.CartaAzione, java.lang.String)
	 */
	public void scuderiaEffettoCartaAzione(Giocatore giocatore, CartaAzione carta, String colore) {
		carta.setScuderiaEffetto(colore);
		giocatore.setCartaAzioneGiocata(carta);
		giocatore.setCartaAzione1(null);
		giocatore.setCartaAzione2(null);
		
	}
	
	/* (non-Javadoc)
	 * @see model.ModelInterface#reinitGiocatori()
	 */
	public void reinitGiocatori() {
		
		initMazzi();
		for (Giocatore giocatore : elencoGiocatori) {
			CartaAzione ca1 = (CartaAzione) mazzoAzione.pesca();
		    CartaAzione ca2 = (CartaAzione) mazzoAzione.pesca();
		    giocatore.setCartaAzione1(ca1);
		    giocatore.setCartaAzione2(ca2);
		    giocatore.setImportoVinto(0);
		    giocatore.setVincitaScuderia(0);
		    giocatore.setPerditaPv(false);
		    giocatore.setPerso(false);
		    giocatore.clearSecondaScommessa();
		    giocatore.setVincitaPv(0);
		}
		
	}
	
	
	
	
	/* (non-Javadoc)
	 * @see model.ModelInterface#decretoVincitore()
	 */
	public String decretoVincitore() {
		
		ordinaGiocatoreByPv(elencoGiocatori);
		int pvMassimi = elencoGiocatori.get(0).getPv();
		// tolgo tutti i giocatori che hanno Pv inferiori al massimo
		for (Giocatore giocatore : elencoGiocatori) {
			if ( giocatore.getPv() != pvMassimi ){
				elencoGiocatori.remove(giocatore);
			}
		}
		ordinaGiocatoreByDenari(elencoGiocatori);
		int denariMassimi = elencoGiocatori.get(0).getDenari();
		//tolgo tutti i giocatori che hanno i denari inferiori al massimo
		for (Giocatore giocatore : elencoGiocatori) {
			if (giocatore.getDenari() != denariMassimi){
				elencoGiocatori.remove(giocatore);
			}
		}
		// tra quelli rimasti scelgo a caso il vincitore
		Random rand = new Random();
        int risultato = rand.nextInt(elencoGiocatori.size());
        Giocatore vincitore = elencoGiocatori.get(risultato);
        
        return vincitore.getNome();
        
		
		
	}
	
	/* (non-Javadoc)
	 * @see model.ModelInterface#ordinaGiocatoreByPv(java.util.ArrayList)
	 */
	public void ordinaGiocatoreByPv(ArrayList<Giocatore> giocatori) {

        Collections.sort(giocatori, new Comparator<Giocatore>() {
            public int compare(Giocatore gioc1, Giocatore gioc2) {
                return gioc2.getPv() - gioc1.getPv();

            }
        });
    }
	
	/* (non-Javadoc)
	 * @see model.ModelInterface#ordinaGiocatoreByDenari(java.util.ArrayList)
	 */
	public void ordinaGiocatoreByDenari(ArrayList<Giocatore> giocatori) {

        Collections.sort(giocatori, new Comparator<Giocatore>() {
            public int compare(Giocatore gioc1, Giocatore gioc2) {
                return gioc2.getDenari() - gioc1.getDenari();

            }
        });
    }
	
	/* (non-Javadoc)
	 * @see model.ModelInterface#roundCorsa()
	 */
	public void roundCorsa() {
		
		corsa.round();
		applyCarteAzione("corsa");
		applyCarteAzione("traguardo");
		corsa.sprint();
		
		if (corsa.getRoundCounter() == 1) {
			applyCarteAzione("partenza");
		}
		ArrayList<Scuderia> scuderieArrivate = corsa.updateClassifica();
		corsa.fotoFinish(scuderieArrivate);
		
	}
	
	/* (non-Javadoc)
	 * @see model.ModelInterface#rimuoviGiocatoriPerdenti()
	 */
	public void rimuoviGiocatoriPerdenti() {
		Iterator<Giocatore> i = elencoGiocatori.iterator();
		while (i.hasNext()) {
			   Giocatore giocatore = i.next();
			   if (giocatore.isPerso())
			   i.remove();
			}
	}
	
	/* (non-Javadoc)
	 * @see model.ModelInterface#reinitScuderie()
	 */
	public void reinitScuderie() {
		for (Scuderia scuderia : elencoScuderie) {
			scuderia.setPosizioneSegnalino(0);
			scuderia.setArrivato(false);
		}
	}
	
	/* (non-Javadoc)
	 * @see model.ModelInterface#marcaGiocatoriPerdenti()
	 */
	public void marcaGiocatoriPerdenti() {
		for (Giocatore giocatore : elencoGiocatori) {
			if (giocatore.getPv() <=0) {
				giocatore.setPerso(true);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see model.ModelInterface#marcaGiocatoriPerditaPv()
	 */
	public void marcaGiocatoriPerditaPv() {

		for (Giocatore giocatore : elencoGiocatori) {
			if (!giocatore.checkScomessaMinima(giocatore.getDenari())) {
				giocatore.setPerditaPv(true);
				giocatore.setPv(giocatore.getPv() - 2);
			}

		}
	}
 
}
