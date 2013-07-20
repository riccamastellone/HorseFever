package View;


import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

import model.Carta;
import model.CartaAzione;
import model.CartaMovimento;
import model.Config;
import model.Giocatore;
import model.Scuderia;
import controller.ControllerInterface;
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
import view_event.NomeGiocatoreEvent;
import view_event.PiazzaScommessa2Event;
import view_event.PiazzaScommessaEvent;
import view_event.PiazzaTruccaCorsaEvent;
import view_event.RispostaDomandaScommessaEvent;

public class OutputText implements Output {

	private Scanner tastiera;
	private PrintStream ps;
	private ControllerInterface controller;

	
	public OutputText() {
		
		this.ps = new PrintStream(System.out);
        this.tastiera = new Scanner(System.in);
        
	}
	

	
	public void setController(ControllerInterface controller) {
		this.controller = controller;
	}
	
	
	/**
	 * chiedo quanti giocatori sono e attraverso l'evento vuoto chiamo il metodo del controller giusto
	 */
	public void chiediNumeroGiocatori(EmptyEvent e) {
		
		out("Quanti giocatori siete?");
		Integer numGiocatori = controllaInput( 2, 6, "Inserisci un numero");
		try {
			controller.setImpostazioniIniziali(numGiocatori);
		} catch (Exception e2) {
			ps.println("Possono giocare a HorseFever dai 2 ai 6 giocatori!");
		}
 		
		
		
	}
	
	 /**
     * controllo che l'input inserito sia un numero e che sia compreso tra i
     * valori massimo e minimo
     *
     * questa funzione puo' essere usata per molti range cambiando il messaggio
     * d'errore
     *
     * @param minimo
     * @param massimo
     * @param messagioErrore
     * @return
     */
    private Integer controllaInput(int minimo, int massimo, String messagioErrore) {
        Integer numero = minimo - 1;
        
        while ((numero < minimo) || (numero > massimo)) {
        	

            try {

                numero = Integer.parseInt(tastiera.nextLine());
                if ((numero < minimo) || (numero > massimo)) {
                    ps.printf(
                            "inserisci un numero compreso tra %d e %d ",
                            minimo, massimo);

                }
            } catch (NumberFormatException e) {

                out(messagioErrore);
            }
        }

        return numero;
    }
    
    private void out() {
        out(" ");
    }

    private void out(String messaggio) {
        ps.println(messaggio);
    }
    
    /**
	 * chiedo il nome del giocatore e attraverso l'evento vuoto chiamo il metodo del controller giusto
	 */
    public void chiediNomeGiocatore(NomeGiocatoreControllerEvent e) {
        out("Come ti chiami Giocatore " + e.getNumero() + " ?");
        String nome = tastiera.nextLine();
        controller.initGiocatore(new NomeGiocatoreEvent(nome));
     

    }
    
    /**
     * serve per creare la lavagna delle quotazioni con colore,quotazione, numero segnalin scommess disponibili
     * @param elencoScuderie
     */
    private void mostraLavagna(ArrayList<Scuderia> elencoScuderie) {
        int lunghezzaLavagna = 69;
        
        for (int i = 0; i < lunghezzaLavagna; i++) {
            ps.print("-");
        }
        out();
        out("|       SCUDERIA      |      QUOTAZIONE       |      SEGNALINI      |");
        for (int i = 0; i < lunghezzaLavagna; i++) {
            ps.print("-");
        }
        out();
        for (int i = 0; i < elencoScuderie.size(); i++) {
            Scuderia scuderia = elencoScuderie.get(i);
            showScuderia(scuderia);
        }
        for (int i = 0; i < lunghezzaLavagna; i++) {
            ps.print("-");
        }
        out();
    }
    
    /**
     * visualizza le proprieta della scuderia formattate bene per la lavagna quotazioni
     * @param scuderia
     */
	private void showScuderia(Scuderia scuderia) {

		String testo = "|        " + scuderia.getColore() + "                                                         ";
		String testoGiusto = testo.substring(0, 22) + "|"
				+ testo.substring(22, 32) + "1:" + scuderia.getQuotazione()
				+ testo.substring(29, 39) + "|" + testo.substring(39, 49) + scuderia.getNumSegnaliniScommessa() + testo.substring(49, 59) + "|";
		out(testoGiusto);

	}
   
	/**
	 * fa scegliere la scuderia, il valore 
	 * della scommessa(si puo scommettere piu di una banconota e si puo decidere di ri iniziare il valore)
	 * e il tipo(vincente/piazzato)
	 */
    public void schermataScommessa1(ScommessaEvent e) {
    	
    	out("TURNO DI " + e.getGiocatore().getNome()
				.toUpperCase());
    	mostraLavagna(e.getElencoScuderie());
    	out("HAI A DISPOSIZIONE " + e.getGiocatore().getDenari() + "DENARI");
    	out();
    	String scuderia = chiediScuderia();
    	Integer valore = decisionePuntata();
    	String tipo = chiediTipoScommessa();
    	controller.piazzaScommessa1(new PiazzaScommessaEvent(e.getGiocatore(), scuderia, valore, tipo));
		
	}
    
    /**
     * fa scegliere la scuderia e il valore della seconda scommessa
     * (il tipo no perche viene scelto in automatico a seconda della prima scommessa)
     */
	public void schermataScommessa2(Scommessa2Event e) {

		out("TURNO DI " + e.getGiocatore().getNome().toUpperCase());
		mostraLavagna(e.getElencoScuderie());
		out("HAI A DISPOSIZIONE " + e.getGiocatore().getDenari() + "DENARI");
    	out();
		String scuderia = chiediScuderia();
		Integer valore = decisionePuntata();
		controller.piazzaScommessa2(new PiazzaScommessa2Event(e
				.getGiocatore(), scuderia, valore, null));

	}
    
	/**
	 * restituisce il colore della scuderia scelta
	 * @return
	 */
    private String chiediScuderia() {
        int i = 0;
        out();
        out("Premi:");

        for (String colore : Config.colori) {
            ps.printf("[%d] per %s \n", i, colore);
            i++;
        }
        int scelta = controllaInput( 0, Config.colori.length - 1, "Scegli il colore in base al numero scritto a fianco");
        String coloreScelto = Config.colori[scelta];
        ps.printf("Hai scelto la scuderia %s \n", coloreScelto);
        return coloreScelto;
    }
    
    /**
     * restituisce il valore della puntata scommessa
     * 
     * @return
     */
	private Integer decisionePuntata() {

		int valoreScommessa = 0;
		Integer banconota = 0;
		int conferma = 0;
		do {

			valoreScommessa = 0;

			do {
				// @todo implementare bene questi 2 out.out
				out("Quanto vuoi scommettere");
				out("Premi:");

				banconota = chiediBanconotaPuntata();
				valoreScommessa += banconota;
				out("Fino ad ora hai puntato "+ valoreScommessa);

			} while (banconota != 0);
			
			conferma = confermaPuntata();
			
		} while ((conferma == 1));
		
		return valoreScommessa;

	}

	/**
	 * permette di giocare piu di una banconota alla volta
	 * @return
	 */
	private Integer chiediBanconotaPuntata() {

		Integer i = 0;
		Integer valoreScelta = 0;
		for (int valore : Config.tipoDenari) {
			ps.printf("[%d] per %d \n", i, valore);
			i++;
		}

		ps.printf("[%d] per finire la puntata \n",
				Config.tipoScommessa.length + 1);
		Integer scelta = controllaInput(0, Config.tipoDenari.length,
				"Scegli che banconota puntare in base al numero scritto a fianco");
		// controllo che la scelta non sia fine scommessa perche se no
		// genererebbe un errore indexOutOfBound
		if (scelta != Config.tipoScommessa.length + 1) {
			valoreScelta = Config.tipoDenari[scelta];
			return valoreScelta;
		}

		return 0;
	}
	
	/**
	 * permette di riiniziare la puntata
	 * @return
	 */
	private int confermaPuntata() {

		ps.println("Sei sicuro di voler puntare?");
		ps.println("[0] per si");
		ps.println("[1] per no");
		int scelta = controllaInput(0, 1,
				"Inserisci 0 per dire si e 1 per dire no");
		return scelta;

	}
	
	/**
	 * restituisce vincente o piazzato
	 * @return
	 */
    private String chiediTipoScommessa() {
    	  
        out("Che tipo di scommessa vuoi fare?");        
        int i = 0;
        for (String tipo : Config.tipoScommessa) {
            ps.printf("[%d] per %s \n", i, tipo);
            i++;
        }

         int scelta = controllaInput( 0, Config.tipoScommessa.length -1,
                "Scegli il tipo di scommessa in base al numero scritto a fianco");
        return Config.tipoScommessa[scelta];
       
    }
    
    /**
     * viene visualizzato in caso di scommessa con importo minore di pv*100
     * richiede la scommessa 1
     */
    public void warningScommessaMinima1(EmptyEvent e) {
		out("non hai rispettato la scommessa minima");
		controller.faseScommessa1();
	}
    
    /**
     * viene visualizzato quando scommetto piu denari di quelli dsponibili
     * richiede la scommessa 1
     */
    public void warningDenariNonDisponibili1(EmptyEvent e) {
    	out("Hai scommesso piu denari di quanti ne possiedi!");
    	controller.faseScommessa1();
    	
	}
    
    /**
     * viene visualizzato quando scommetto su una scuderia che non ha piu segnalini
     * scommessa disponibili
     * richiede la scommessa 1
     */
    public void warningSegnaliniNonDisponibili1(EmptyEvent e) {
		out("Questa scuderia non ha piu segnalini scommessa disponibili, scegline un altra");
		controller.faseScommessa1();
	}
    
    /**
     * viene visualizzato in caso di scommessa con importo minore di pv*100
     * richiede la scommessa 2
     */
    public void warningScommessaMinima2(EmptyEvent e) {
		out("non hai rispettato la scommessa minima");
		controller.faseScommessa2();
	}
    
    /**
     * viene visualizzato quando scommetto piu denari di quelli dsponibili
     * richiede la scommessa 2
     */
    public void warningDenariNonDisponibili2(EmptyEvent e) {
    	out("Hai scommesso piu denari di quanti ne possiedi!");
    	controller.faseScommessa2();
    	
	}
    
    /**
     * viene visualizzato quando scommetto su una scuderia che non ha piu segnalini
     * scommessa disponibili
     * richiede la scommessa 2
     */
    public void warningSegnaliniNonDisponibili2(EmptyEvent e) {
		out("Questa scuderia non ha piu segnalini scommessa disponibili, scegline un altra");
		controller.faseScommessa2();
    }
    
    /**
     * permette di decidere quale carta azione giocare e su che scuderia giocarla
     * visualizza anche di che scuderia il giocatore e proprietario eanche le carte azione in suo possesso
     */
    public void schermataTruccaCorsa(TruccaCorsaEvent e) {
		
    	out("TURNO DI " + e.getGiocatore().getNome()
				.toUpperCase());
    	mostraLavagna(e.getScuderie());
    	showCommonCarta(e.getCarta1());
    	out();
    	showCommonCarta(e.getCarta2());
    	out();
    	out("Sei proprietario della scuderia " + e.getGiocatore().getColoreScuderiaProprietario());
    	out();
    	out("Quale carta azione vuoi giocare?");
    	out("[0] per " + e.getCarta1().getNome().toUpperCase());
        out("[1] per " + e.getCarta2().getNome().toUpperCase());
    	int cartaScelta = controllaInput(0, 1, "Premi 0 per la prima e 1 per la seconda");
    	out();
    	out("su quale scuderia vuoi giocarla");
    	String scuderiaScelta = chiediScuderia();
    	if (cartaScelta == 0) {
			controller.piazzaTruccaCorsa(new PiazzaTruccaCorsaEvent(1, scuderiaScelta, e.getGiocatore()));
		}
    	else {
    		controller.piazzaTruccaCorsa(new PiazzaTruccaCorsaEvent(2, scuderiaScelta, e.getGiocatore()));
		}
    	
	}
    
    /**
     * visualizza gli attributi comuni a tutte le carte
     * @param carta
     */
    private void showCommonCarta(Carta carta) {

		out("Nome: " + carta.getNome().toUpperCase());
		out("Descrizione: " + carta.getDescrizione());
		
	}
    
    
    /**
     * chiede se si vuole fare la scommessa 2
     */
	public void domandaScommessa2(DomandaScommessa2Event e) {
		out("TURNO DI " + e.getGiocatore().getNome().toUpperCase());
		out("Vuoi fare la seconda scommessa?");
		out("[0] per si");
		out("[1] per no");
		Integer scelta = controllaInput(0, 1,
				"inserisci 0 per dire si e 1 per dire no");
		controller.valutaRispostaScommessa2(new RispostaDomandaScommessaEvent(scelta,e.getGiocatore()));

	}
	
	/**
	 * visualizza lo stato della corsa e la classifica
	 */
	public void mostraCorsa(CorsaEvent e) {

		showCartaMovimento(e.getCorsa().getLastCartaMovimento());

		out("Sprint sulla scuderia " + e.getCorsa().getScuderiaSprint1());
		if (e.getCorsa().getScuderiaSprint2() != null) {
			out("Sprint sulla scuderia " + e.getCorsa().getScuderiaSprint2());
		}

		for (int i = 0; i < Config.caselleBoard; i++) {
			ps.print("-");
		}
		
		ps.println();
		for (int i = 0; i < e.getElencoScuderie().size(); i++) {
			Scuderia scuderia = e.getElencoScuderie().get(i);
			ps.print(scuderia.getColore() + ":  ");
			for (int j = 0; j < scuderia.getPosizioneSegnalino(); j++) {
				ps.print(" _ ");
			}
			ps.print(" * ");
			for (int j = scuderia.getPosizioneSegnalino() + 1; j < Config.caselleBoard; j++) {
				ps.print(" _ ");

			}
			ps.println();

		}
		ps.println();
		for (int i = 0; i < Config.caselleBoard; i++) {
			ps.print("-");
		}
		ps.println();
	
		int i = 1;
		for (Scuderia scuderia : e.getCorsa().getClassifica()) {
			System.out.println(i + " posto: " + scuderia.getColore());
			i++;
		}
		
		//aspetto ad andare avanti
		tastiera.nextLine();
		
		controller.faseCorsa();

	}

	/**
	 * visualizza le informazioni della carta movimento
	 */
	private void showCartaMovimento(CartaMovimento carta) {

		int i = 2;
		for (Integer spostamento : carta.getSpostamenti()) {

			ps.println("Spostamento 1:" + i + " " + spostamento);
			i++;
		}

	}

	/**
	 * viene visualizzato alla fine del gioco quando non si hanno vincitori
	 */
	public void schermataNessunVincitore(EmptyEvent e) {
		out("Non ha vinto nessuno!");
		out("FINE GIOCO");
		
	}
	
	/**
	 * mostra chi e il vincitore
	 */
	public void schermataVincitore(VincitoreEvent e) {
		out("HA VINTO " + e.getNome().toUpperCase());
		out();
		out("FINE GIOCO");
		
	}


	/**
	 * mostra chi ha vinto le scommesse, chi e stato pagato perche proprietario di 
	 * scuderia vincente, chi ha perso pv e chi ha perso la partita
	 */
	public void schermataRiepilogoTurno(RiepilogoTurnoEvent e) {
		//mostro i giocatori che hanno vinto la scommessa e dico quanto
		out("I SEGUENTI GIOCATORI HANNO VINTO LE SCOMMESSE EFFETTUATE:");
		for (Giocatore giocatore : e.getElencoGiocatori()) {
			if (giocatore.getImportoVinto() !=0 ){
			ps.println(giocatore.getNome()+ " ha vinto " + giocatore.getImportoVinto() + " e ha guadagnato " + giocatore.getVincitaPv() + " pv");
			}
		}
		out();
		
		//mostro i giocatori che sono stati pagati perche proprietari di scuderia
		out("I SEGUENTI GIOCATORI SONO STATI PAGATI:");
		for (Giocatore giocatore : e.getElencoGiocatori()){
			if (giocatore.getVincitaScuderia() != 0) {
				ps.println(giocatore.getNome() + " ha vinto " + giocatore.getVincitaScuderia() +" perche' prorietario della scuderia " + giocatore.getColoreScuderiaProprietario());
				
			}
		}
		out();
		//metto in un arraylist tutti i giocatori che hanno perso pv e se e diversa da 0 li visualizzo
		ArrayList<Giocatore> giocatoriPerdentiArrayList = new ArrayList<Giocatore>();
		for (Giocatore giocatore : e.getElencoGiocatori()) {
			if (giocatore.isPerditaPv()){
				giocatoriPerdentiArrayList.add(giocatore);
			}
		}
		if (giocatoriPerdentiArrayList.size() == 0) {
			out("IN QUESTO TURNO NESSUN GIOCATORE HA PERSO PV");
		}
		else {
			for (Giocatore giocatore : giocatoriPerdentiArrayList) {
				ps.println(giocatore.getNome() + "ha perso 2 Pv");
			}
		}
		
		giocatoriPerdentiArrayList.clear();
		
		//metto in un arraylist tutti i giocatori che hanno perso e se e diversa da 0 li visualizzo
		for (Giocatore giocatore : e.getElencoGiocatori()) {
			if (giocatore.isPerso()){
				giocatoriPerdentiArrayList.add(giocatore);
			}
		}
		if (giocatoriPerdentiArrayList.size() == 0) {
			out("IN QUESTO TURNO NESSUN GIOCATORE HA PERSO LA PARTITA");
		}
		else {
			for (Giocatore giocatore : giocatoriPerdentiArrayList) {
				ps.println(giocatore.getNome() + " ha perso");
			}
		}
		
		tastiera.nextLine();
		
		controller.controllaFine();
		
	}


	/**
	 * viene visualizzata solo se il giocatore vuole fare la seconda domanda ma non puo rispettare la scommessa minima
	 * chiamo valuta prossima domanda per sapere se mancano giocatori a cui chiedere se vogliono fare la seconda scommessa
	 */
	public void schermataNonPuoiFareScommessa2(ErroreScommessa2Event e) {
		
		out(e.getGiocatore().getNome().toUpperCase() + " NON PUOI FARE LA SECONDA SCOMMESSA PERCHE NON HAI ABBASTANZA DENARI PER COPRIRE LA SCOMMESSA MINIMA");
		controller.valutaProssimaDomanda();
		
	}


	public void loadingScommessa1(EmptyEvent e) {
		out("****************** FASE SCOMMESSA 1 *******************");
		controller.faseScommessa1();
		
	}


	public void loadingTruccaCorsa(EmptyEvent e) {
		out("****************** FASE TRUCCA CORSA *******************");
		controller.truccaCorsa();
		
	}


	public void loadingCorsa(EmptyEvent e) {
		out("****************** FASE CORSA *******************");
		controller.initCorsa();
		
	}


	public void loadingScommessa2(EmptyEvent e) {
		out("****************** FASE SCOMMESSA 2 *******************");
		controller.domandaScommessa2();
		
	}


	public void loadingChiusuraTurno(EmptyEvent e) {
		out("****************** SCHERMATA RIEPILOGO *******************");
		controller.chiusuraTurno();
		
	}


	public void schermataAttendi() {
		System.out.println("Attendi...");
		
	}

	public void schermataSaltaScommessa1(ErroreScommessa1Event e) {
		out(e.getGiocatore().getNome().toUpperCase() + " NON PUOI FARE LA PRIMA SCOMMESSA PERCHE NON HAI ABBASTANZA DENARI PER COPRIRE LA SCOMMESSA MINIMA");
		controller.valutaProssimaScommessa1();
		
	}
		
}
