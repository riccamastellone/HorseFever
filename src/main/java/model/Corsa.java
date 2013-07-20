package model;


import java.io.Serializable;
import java.util.ArrayList;

import java.util.Random;


/**
 * @author Sebastiano Mariani
 * @author Riccardo Mastellone
 */
public class Corsa implements Serializable{

    private Mazzo mazzoCarteMovimento;
    private Model model;
    private ArrayList<Scuderia> classifica;
    private int roundCounter = 0;
    
    // ATTRIBUTI PER GESTIRE LE CARTE AZIONE
    // FOTOFINISH E SPRINT
    private Scuderia fotofinishPerde;
    private Scuderia fotofinishVince;
    
    private Scuderia sprintAdd1;
    private Scuderia sprintOvveride2;
    private Scuderia sprintBlock;
    private Scuderia sprintSubtract1;
    private String scuderiaSprint1;
    private String scuderiaSprint2;
    
    // Utilizzato per tenere traccia dell'ultima carta movimento per 
    // gestire gli effetti delle carte Azione
    private CartaMovimento lastCartaMovimento;

    /**
     * Nel costruttore creo il mazzo di Carte Movimento e prendo l'istanza di
     * Elenco Scuderie
     */
    public Corsa() {
       
        this.classifica = new ArrayList<Scuderia>();
        CartaMovimento c = new CartaMovimento();
		this.mazzoCarteMovimento = new Mazzo();
		c.loadCarte(mazzoCarteMovimento);
		mazzoCarteMovimento.mescola();
		this.model = model.getInstance();
        
    }
    

    
    public CartaMovimento getLastCartaMovimento() {
        return this.lastCartaMovimento;
    }
    
    public void setLastCartaMovimento(CartaMovimento cartaMovimento) {
		this.lastCartaMovimento = cartaMovimento;
	}
    
    public int getRoundCounter() {
        return roundCounter;
    }
    
    public void setFotoFinishPerde(Scuderia scuderia) {
        this.fotofinishPerde = scuderia;
    }
    
    public void setFotoFinishVince(Scuderia scuderia) {
        this.fotofinishVince = scuderia;
    }
    

    /**
     * Metodo per i dado Sprint (da chiamare quindi due volte)
     *
     * @return String colore del dado
     */
    private String tiraDadi() {
        Random rand = new Random();
        int risultato = rand.nextInt(6);
        return Config.colori[risultato];

    }

    /**
     * Vengono tirati i dadi Sprint che determinano 2 colori di scuderie i quali
     * cavalli di appartenenza si muoveranno in avanti di una casella aggiuntiva
     * (Nel caso sia lo stesso colore, comunque una sola casella)
     */
    public void sprint() {
    	
    	scuderiaSprint1 = "";
    	scuderiaSprint2 = "";
      
        String color1 = tiraDadi();
        String color2 = tiraDadi();


        Scuderia s = model.getScuderiabyColore(color1);
        int valoreSprint = doApplyAzioneSprint(s);
        int nuovaPosizione = s.getPosizioneSegnalino() + valoreSprint;
        s.setPosizioneSegnalino(nuovaPosizione);
        scuderiaSprint1 = color1;
        if (!color1.equals(color2)) { // Nel caso siano uguali il cavallo si muove
            // comunque solo di una casella
            s = model.getScuderiabyColore(color2);
            valoreSprint = doApplyAzioneSprint(s);
            nuovaPosizione = s.getPosizioneSegnalino() + valoreSprint;
            s.setPosizioneSegnalino(nuovaPosizione);
            scuderiaSprint2 = color2;
        }

    }
    /**
     * Gestiamo gli effetti su Sprint delle carte Azione
     * @param scuderia 
     */
    private int doApplyAzioneSprint(Scuderia scuderia) {
        int valore = 1;
        if(scuderia == sprintAdd1) {
            valore = valore + 1;
        } else if(scuderia == sprintBlock) {
            valore = 0;
        } else if(scuderia == sprintOvveride2) {
            valore = 2;
        } else if(scuderia == sprintSubtract1) {
            valore = valore - 1;
        }
        return valore;
    }

    /**
     *
     * per ogni scuderia valuto che quotazione ha e applico l'effetto
     *
     */
    public void round() {
    	
    	//@todo mettere in controller
        lastCartaMovimento = (CartaMovimento) mazzoCarteMovimento.pesca();
        // Sposto i segnalini di tutte le scuderie
        for (Scuderia scuderia : model.getScuderie()) {
            // tolgo 2 alla quotazione della scuderia perche gli indici partono
            // da 0 e le quotazioni da 2 quindi quotazione 2 = indice 0
            int nuovaPosizione = scuderia.getPosizioneSegnalino()
                    + lastCartaMovimento.getSpostamenti().get(scuderia.getQuotazione() - 2);
            scuderia.setPosizioneSegnalino(nuovaPosizione);
        }
      
       roundCounter++;
    }

    /**
     * Controllo se tutti i cavalli hanno raggiunto il traguardo
     *
     * @return Bool vero nel caso la corsa sia completa, false altrimenti;
     */
    public boolean corsaCompleta() {
        for (int i = 0; i < model.getScuderie().size(); i++) {
            Scuderia s = model.getScuderie().get(i);
            if (s.getPosizioneSegnalino() < Config.caselleBoard) {
                return false;
            }
        }

        return true;
    }

    /**
     * aggiunge alla lista scuderieArrivate tutte quelle arrivate in questo
     * turno le mette in ordine decrescente a seconda della posizione del
     * segnalino le aggiunge alla classifica rispettando il fotofinish e le marca come arrivate
     * 
     *
     */
    public ArrayList<Scuderia> updateClassifica() {
    	
        ArrayList<Scuderia> scuderieArrivate = new ArrayList<Scuderia>();
        for (Scuderia s : model.getScuderie()) {

            if ((s.getPosizioneSegnalino() >= Config.caselleBoard)
                    && (s.isArrivato() == false)) {
                //aggiungo tutte le scuderie che sono arrivate in questo turno e le segno come arrivate
                scuderieArrivate.add(s);
                s.setArrivato(true);
            }

        }
       return scuderieArrivate;

    }
    
    /**
     * Controlla se la scuderia passata come parametro e' prima in classifica
     * @return 
     */
    public boolean checkIfFirst(Scuderia scuderia) {
        
        int posizione = scuderia.getPosizioneSegnalino();
        for (Scuderia s : model.getScuderie()) {

            if (s.getPosizioneSegnalino() > posizione) {
                return false;
            }

        }
        return true;
        
    } 
    
    /**
     * Controlla se la scuderia passata come parametro e' ultima in classifica
     * @return 
     */
    public boolean checkIfLast(Scuderia scuderia) {
        
        int posizione = scuderia.getPosizioneSegnalino();
        for (Scuderia s : model.getScuderie()) {

            if (s.getPosizioneSegnalino() < posizione) {
                return false;
            }

        }
        return true;
        
    } 

    /**
     * 
     * Nel caso uno o piu cavalli superino il traguardo dello stesso numero di
     * caselle vince quello con la quotazione piu alta. Nel caso sia la stessa,
     * decide il Primo Giocatore (-> scegliCavalloVincente())
     * @param scuderieArrivate
     */
    public void fotoFinish(ArrayList<Scuderia> scuderieArrivate){
        
       //le ordino dalla posizione piu alta aquella piu bassa
       model.ordinaScuderieByPosizione(scuderieArrivate);
    	
    	boolean inizioCheckFotofinish = true;
    	int posizioneFotofinish = 0;
    	ArrayList<Scuderia> scuderieFotofinish = new ArrayList<Scuderia>();
    	
    	for (Scuderia scuderia : scuderieArrivate) {
            //aggiungo a scuderieFotofinish tutte quelle che hanno segnalino uguale
        	if ( (inizioCheckFotofinish == true) || (posizioneFotofinish == scuderia.getPosizioneSegnalino()) ){
        		inizioCheckFotofinish = false;
        		posizioneFotofinish = scuderia.getPosizioneSegnalino();
        		scuderieFotofinish.add(scuderia);
        	}
        	else {
        		//appena trovo una scuderia che e piu indietro rispeto alle altre le ordino per quotazione e le aggiungo alla classifica
			if (scuderieFotofinish.size() >= 1) {
        
                                doApplyFotoFinish(scuderieFotofinish);
                                
				classifica.addAll(scuderieFotofinish);
				scuderieFotofinish.clear();
				scuderieFotofinish.add(scuderia);
				posizioneFotofinish= scuderia.getPosizioneSegnalino();
			}
		}
        }
        //se ne rimane solo una la aggiungo al di fuori del for
        doApplyFotoFinish(scuderieFotofinish);
        classifica.addAll(scuderieFotofinish);
        scuderieFotofinish.clear();

    	
    }
    
    private void doApplyFotoFinish(ArrayList<Scuderia> scuderieFotofinish) {
        
        //** EFFETTO CARTE AZIONE **//
        // Cambio temporaneamente le quotazioni dell scuderie
        // su cui sono applicate le carte azione per farle
        // vincere o perdere sempre.
        int oldQuotazione1 = 0;
        int oldQuotazione2 = 0;
                
        if(fotofinishPerde != null) {
            oldQuotazione1 = fotofinishPerde.getQuotazione();
            fotofinishPerde.setQuotazione(1000);
        }
        
        if(fotofinishVince != null) {
            oldQuotazione2 = fotofinishVince.getQuotazione();
            fotofinishVince.setQuotazione(0);
        }
        
        
        model.ordinaScuderieByQuotazione(scuderieFotofinish);
        
        
        if(fotofinishPerde != null) {
            fotofinishPerde.setQuotazione(oldQuotazione1);
        }
        if(fotofinishVince != null) {
            fotofinishVince.setQuotazione(oldQuotazione2);
        }
        
        
    }
 
    /**
     *
     * viene usata per vsualizzare la cllassifica finale in core
     *
     * @return
     */
    public ArrayList<Scuderia> getClassifica() {
        return classifica;

    }

	public Scuderia getSprintAdd1() {
		return sprintAdd1;
	}

	public void setSprintAdd1(Scuderia sprintAdd1) {
		this.sprintAdd1 = sprintAdd1;
	}

	public Scuderia getSprintOvveride2() {
		return sprintOvveride2;
	}

	public void setSprintOvveride2(Scuderia sprintOvveride2) {
		this.sprintOvveride2 = sprintOvveride2;
	}

	public Scuderia getSprintBlock() {
		return sprintBlock;
	}

	public void setSprintBlock(Scuderia sprintBlock) {
		this.sprintBlock = sprintBlock;
	}

	public Scuderia getSprintSubtract1() {
		return sprintSubtract1;
	}

	public void setSprintSubtract1(Scuderia sprintSubtract1) {
		this.sprintSubtract1 = sprintSubtract1;
	}

	public String getScuderiaSprint1() {
		return scuderiaSprint1;
	}


	public String getScuderiaSprint2() {
		return scuderiaSprint2;
	}


}
