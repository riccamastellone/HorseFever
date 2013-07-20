package network;

import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.sql.Connection;

import model.Config;

import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;

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

import view_event.HorseFeverEvent;



import View.Output;


public class Client {
	

	private ControllerInterface controller;
	private Output output;
	private Socket socket;
	private Connection conn;
	
	/**
	 * 
	 * crea un socket verso il server e dice che e stata creata
	 * @param output
	 * @param controller
	 */
	public Client(Output output, ControllerInterface controller) {
		try {
			
			
			try {
				Class.forName("org.sqlite.JDBC");
			    SQLiteConfig config = new SQLiteConfig();
			    config.enableFullSync(true);
			    config.setReadOnly(false);
			    SQLiteDataSource ds = new SQLiteDataSource(config);
			    ds.setUrl("jdbc:sqlite::resource:"+
			              this.getClass().getResource("/Altro/database_carte.db").toString());
			    conn = ds.getConnection();
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			this.controller = controller;
			this.output = output;
			// open a socket connection
			socket = new Socket(Config.indirizzoServer, Config.portaServer);
			System.out.println("Socket creata: " + socket);
			controller.setClient(this);

			
			// Apre i canali I/O
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	public Socket getSocket(){
		return socket;
	}
	
	/**
	 * si mette in ascolto dell'evento spedito dal server
	 * una volta ricevuto lo analizza e chiama la funzione della view appropriata
	 */
	public void run() {
	
			
			HorseFeverEvent event = null;
			try {
				InputStream inStream = socket.getInputStream();
				ObjectInputStream inServer = new ObjectInputStream(inStream);
				event = (HorseFeverEvent) inServer.readObject();
				if (event.getClass() == NomeGiocatoreControllerEvent.class) {
					NomeGiocatoreControllerEvent newEvent = (NomeGiocatoreControllerEvent)event;
					output.chiediNomeGiocatore(newEvent);
				}
				else if (event.getNome().equals("loadingScommessa1")) {
					EmptyEvent newEvent = (EmptyEvent)event;
					output.loadingScommessa1(newEvent);
				}
				else if (event.getClass() == ScommessaEvent.class) {
					ScommessaEvent newEvent = (ScommessaEvent)event;
					output.schermataScommessa1(newEvent);
				}
				else if (event.getNome().equals("warningScommessaMinima1")) {
					EmptyEvent newEvent = (EmptyEvent)event;
					output.warningScommessaMinima1(newEvent);
				}
				else if (event.getNome().equals("warningDenariNonDisponibili1")) {
					EmptyEvent newEvent = (EmptyEvent)event;
					output.warningDenariNonDisponibili1(newEvent);
				}
				else if (event.getNome().equals("warningSegnaliniNonDisponibili1")) {
					EmptyEvent newEvent = (EmptyEvent)event;
					output.warningSegnaliniNonDisponibili1(newEvent);
				}
				else if (event.getNome().equals("loadingTruccaCorsa")) {
					EmptyEvent newEvent = (EmptyEvent)event;
					output.loadingTruccaCorsa(newEvent);
				}
				else if (event.getClass() == TruccaCorsaEvent.class) {
					TruccaCorsaEvent newEvent = (TruccaCorsaEvent)event;
					output.schermataTruccaCorsa(newEvent);
				}
				else if (event.getNome().equals("loadingScommessa2")) {
					EmptyEvent newEvent = (EmptyEvent)event;
					output.loadingScommessa2(newEvent);
				}
				else if (event.getClass() == DomandaScommessa2Event.class) {
					DomandaScommessa2Event newEvent = (DomandaScommessa2Event)event;
					output.domandaScommessa2(newEvent);
				}
				else if (event.getClass() == ErroreScommessa2Event.class) {
					ErroreScommessa2Event newEvent = (ErroreScommessa2Event)event;
					output.schermataNonPuoiFareScommessa2(newEvent);
				}
				else if (event.getClass() == Scommessa2Event.class) {
					Scommessa2Event newEvent = (Scommessa2Event)event;
					output.schermataScommessa2(newEvent);
				}
				else if (event.getNome().equals("warningScommessaMinima2")) {
					EmptyEvent newEvent = (EmptyEvent)event;
					output.warningScommessaMinima2(newEvent);
				}
				else if (event.getNome().equals("warningDenariNonDisponibili2")) {
					EmptyEvent newEvent = (EmptyEvent)event;
					output.warningDenariNonDisponibili2(newEvent);
				}
				else if (event.getNome().equals("warningSegnaliniNonDisponibili2")) {
					EmptyEvent newEvent = (EmptyEvent)event;
					output.warningSegnaliniNonDisponibili2(newEvent);
				}
				else if (event.getClass() == CorsaEvent.class) {
					CorsaEvent newEvent = (CorsaEvent)event;
					output.mostraCorsa(newEvent);
				}
				else if (event.getClass() == RiepilogoTurnoEvent.class) {
					RiepilogoTurnoEvent newEvent = (RiepilogoTurnoEvent)event;
					output.schermataRiepilogoTurno(newEvent);
				}
				else if (event.getClass() == ErroreScommessa1Event.class) {
					ErroreScommessa1Event newEvent = (ErroreScommessa1Event)event;
					output.schermataSaltaScommessa1(newEvent);
				}
				else if (event.getClass() == VincitoreEvent.class) {
					VincitoreEvent newEvent = (VincitoreEvent)event;
					output.schermataVincitore(newEvent);
				} 
				else if (event.getNome().equals("nessunVincitore")) {
					EmptyEvent newEvent = (EmptyEvent)event;
					output.schermataNessunVincitore(newEvent);
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