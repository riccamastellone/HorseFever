package it.polimi.ingegneriaDelSoftware2013.horseFever_sebastiano.mariani_riccardo.mastellone;

import java.net.Socket;
import java.util.ArrayList;
import javax.swing.JFrame;
import network.Client;
import network.ModReteController;
import network.ModReteView;
import network.Server;
import View.Output;
import View.OutputGUI;
import View.OutputText;
import View.ViewImpostazioni;
import View.ViewIniziale;
import View.ViewServerChiediNumeroGiocatori;
import controller.Controller;
import controller.ControllerInterface;
import model.Model;
import model.ModelInterface;

public class Main {

	public static void main(String[] args){
		
		ViewIniziale view = new ViewIniziale();
		view.setVisible(true);

	}
	
	
	
	/**
	 * 
	 * 
	 * modalita 2 = mi istanzio come client e cerco il server
	 * 
	 * modalita 3 = gioco in locale
	 * @param grafica
	 * @param modalita
	 */
	public static void inizio(Boolean grafica, int modalita) {

		//ho scelto la gui grafica(inutile nel server)
		if (grafica) {

		
			//mi istanzio come client e passo a ModreteController(si occupa di ritornare al server le informazioni)
			//il socket aperto e il rifrimento alla mia view(cosi posso essere sia testuale che grafica che il codice non cambia)
			if (modalita == 2) {
				
				Output output = new OutputGUI();
				ControllerInterface controller = new ModReteController(output);
				output.setController(controller);
				Client client = new Client(output, controller);
				client.run();
				

			} 
			
			//semplice partita in locale, non istanzio i moduli di rete
			else if (modalita == 3) {
				ModelInterface model = Model.getInstance();
				Output output = new OutputGUI();
				Controller controller2 = new Controller(model,output);
				output.setController(controller2);
				controller2.initScuderie();
			}
		} 
		
		//ho scelto la gui testuale(inutile nel server
		else {
			
			//mi istanzio come client e passo a ModreteController(si occupa di ritornare al server le informazioni)
			//il socket aperto e il rifrimento alla mia view(cosi posso essere sia testuale che grafica che il codice non cambia)
			if (modalita == 2) {
				Output output = new OutputText();
				ControllerInterface controller = new ModReteController(output);
				output.setController(controller);
				Client client = new Client(output, controller);
				client.run();
				
				

			}
			
			//semplice partita in locale, non istanzio i moduli di rete
			else if (modalita == 3) {
				ModelInterface model = Model.getInstance();
				Output output = new OutputText();
				Controller controller2 = new Controller(model,output);
				output.setController(controller2);
				controller2.initScuderie();
		}
		}

	}
	
	public static void schermataServer() {
		JFrame viewServer = new ViewServerChiediNumeroGiocatori();
		viewServer.setVisible(true);
	}
	
	public static void schermataImpostazioni() {
		JFrame viewImpostazioni = new ViewImpostazioni();
		viewImpostazioni.setVisible(true);
	}
	
	/**
	 * creo il server che inizia ad aspettare i giocatori ino al raggiungimento del numero inserito 
	 * e poi passo a inizoGiocoServer
	 * @param numGiocatori
	 * @throws Exception
	 */
	public static void initServer(int numGiocatori) throws Exception {
		
		Server server = new Server(numGiocatori);
		server.run();
		
	}
	
	/**
	 * 
	 * inizializzo il controller che si occupera di gestire i dati di tutti i giocatori
	 * inizializzo anche modReteView che si occupa di impacchettare i dati in socket e spedirli al giusto giocatore
	 * inizializzo anche il mosel(strutture dati condivise tra tutti i giocatori)
	 * @param numGiocatori
	 * @throws Exception
	 */
	public static void inizioGiocoServer(int numGiocatori, ArrayList<Socket> connections, Server server) throws Exception {
		ModelInterface model = Model.getInstance();
		Output output = new ModReteView(connections);
		Controller controller2 = new Controller(model,output);
		server.setController(controller2);
		output.setController(controller2);
		controller2.setImpostazioniIniziali(numGiocatori);
	}
}
