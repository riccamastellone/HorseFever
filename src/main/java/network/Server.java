package network;

import it.polimi.ingegneriaDelSoftware2013.horseFever_sebastiano.mariani_riccardo.mastellone.Main;


import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;


import model.Config;
import model.Giocatore;
import model.Model;
import model.ModelInterface;

import controller.ControllerInterface;

import view_event.HorseFeverEvent;
import view_event.NomeGiocatoreEvent;
import view_event.PiazzaScommessa2Event;
import view_event.PiazzaScommessaEvent;
import view_event.PiazzaTruccaCorsaEvent;
import view_event.RispostaDomandaScommessaEvent;

public class Server extends Thread {
	
	
	private ServerSocket Server;
	private ArrayList<Socket> elencoConnessioni;
	private int numGiocatori;
	private int i = 0;
	private ControllerInterface controller;
	private int eventCounter = 0;

	


	/**
	 * crea il server aprendo la porta specificata
	 * @param numGiocatori
	 * @throws Exception
	 */
	public Server(int numGiocatori) throws Exception {
		Server = new ServerSocket(Config.portaServer);
		this.elencoConnessioni = new ArrayList<Socket>();
		this.numGiocatori = numGiocatori;
		System.out.println("Il Server e in attesa sulla porta " + Config.portaServer);
		
		
	}
	
	public void setController(ControllerInterface controller) {
		this.controller = controller;
	}

	/**
	 * aspetta tante connessioni tante quanti sono i giocatore
	 * 
	 * una volta ricevute tutte le connssioni fa il setupIniziale
	 * e si mette in ascolto degli eventi ricevuti dalle view
	 * 
	 * quando riceve un evento lo riconosce tramite ua stringa e chiama la fuzione del controller appropriata
	 * 
	 * 
	 */
	public void run() {
		
		while (i<numGiocatori) {
			try {
				
				System.out.println("In attesa di Connessione.");
				Socket client = Server.accept();
				System.out.println("Connessione accettata da: "
						+ client.getInetAddress());
				
				elencoConnessioni.add(client);
				i++;
						
			} catch (Exception e) {
			}
		}
		try {
			Main.inizioGiocoServer(numGiocatori, elencoConnessioni,this);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	while (true) {
	
		try {
			System.out.println("In attesa di evento.");
			Socket client2 = Server.accept();
			InputStream inStream = client2.getInputStream();
			ObjectInputStream inputStream = new ObjectInputStream(inStream);
			HorseFeverEvent event = (HorseFeverEvent) inputStream.readObject();
			if (event.getClass() == NomeGiocatoreEvent.class) {
				NomeGiocatoreEvent newEvent = (NomeGiocatoreEvent) event;
				controller.initGiocatore(newEvent);
			}
			else if (event.getNome().equals("iniziaScommessa1")) {
				controller.faseScommessa1();
			}
			else if (event.getClass() == PiazzaScommessaEvent.class) {
				PiazzaScommessaEvent newEvent = (PiazzaScommessaEvent)event;
				controller.piazzaScommessa1(newEvent);
			}
			else if (event.getNome().equals("valutaProssimaScommessa1")) {
				controller.valutaProssimaDomanda();
			}
			else if (event.getNome().equals("iniziaTruccaCorsa")) {
				controller.truccaCorsa();
			}
			else if (event.getClass() == PiazzaTruccaCorsaEvent.class) {
				PiazzaTruccaCorsaEvent newEvent = (PiazzaTruccaCorsaEvent)event;
				controller.piazzaTruccaCorsa(newEvent);
			}
			else if (event.getNome().equals("domandaScommessa2")) {
				controller.domandaScommessa2();
			}
			else if (event.getClass() == RispostaDomandaScommessaEvent.class) {
				RispostaDomandaScommessaEvent newEvent = (RispostaDomandaScommessaEvent)event;
				controller.valutaRispostaScommessa2(newEvent);
			}
			else if (event.getNome().equals("valutaProssimaDomanda")) {
				controller.valutaProssimaDomanda();
			}
			else if (event.getClass() == PiazzaScommessa2Event.class) {
				PiazzaScommessa2Event newEvent = (PiazzaScommessa2Event)event;
				controller.piazzaScommessa2(newEvent);
			}
			else if (event.getNome().equals("iniziaScommessa2")) {
				controller.faseScommessa2();
			}
			else if (event.getNome().equals("faseCorsa")) {
				eventCounter++;
				if (eventCounter == elencoConnessioni.size()) {
					eventCounter = 0;
					controller.faseCorsa();
				}
				else {
					continue;
				}
			}
			else if (event.getNome().equals("controllaFine")) {
				eventCounter++;
				if (eventCounter == elencoConnessioni.size()) {
					eventCounter = 0;
					ModelInterface model = Model.getInstance();
					ArrayList<Integer> indiciDaRimuovere = new ArrayList<Integer>();
					
					//scorro tutti i giocatori e mi segno l'indce dei perdnti
					for (Giocatore giocatore : model.getGiocatori()) {
						if (giocatore.isPerso()){
							indiciDaRimuovere.add(model.getGiocatori().indexOf(giocatore));
						}
					}
					
					//ordino gli indici in ordine decrescente cosi evito il problema del compattamento automatico dell arraylist
					Collections.sort(indiciDaRimuovere, Collections.reverseOrder());
					
					//rimuovo i socket dei giocatori perdenti
					for (Integer i : indiciDaRimuovere) {
						int postoDaRimuovere = i;
						elencoConnessioni.remove(postoDaRimuovere);
					}
					controller.controllaFine();
				}
				else {
					continue;
				}
			}
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	}
	
	
}

