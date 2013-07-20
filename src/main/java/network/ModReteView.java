package network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
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
import View.Output;

public class ModReteView implements Output {
	
	private ArrayList<Socket> connections;
	private ControllerInterface controller;
	private int socketCorrente;
	
	public ModReteView(ArrayList<Socket> sockets){
		this.connections = sockets;
	}
	
	/**
	 * setto il controller
	 * usato per saltare le schermate di loading in multiplayer
	 */
	public void setController(ControllerInterface controller) {
		this.controller = controller;
	}

	public void chiediNumeroGiocatori(EmptyEvent e) {
		// Non serve in quanto e' il server a decidere il numero dei giocatori
		
	}

	/**
	 * 
	 * prendo il socket del giocatore corrente e scrivo nel outputStream delsocket 
	 * l'evento che deve ricevere il client
	 */
	public void chiediNomeGiocatore(NomeGiocatoreControllerEvent e) {
		
		Socket connection = connections.get(socketCorrente);
		try {
		
			ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
			output.writeObject(e);
			output.flush();
			socketCorrente++;
			//output.close();
				
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	/**
	 * 
	 * prendo il socket del giocatore corrente e scrivo nel outputStream delsocket 
	 * l'evento che deve ricevere il client
	 */
	public void schermataScommessa1(ScommessaEvent e) {
		Socket connection = connections.get(e.getIdGiocatore());
		try {
		
			ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
			output.writeObject(e);
			output.flush();
			
				
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	/**
	 * 
	 * prendo il socket del giocatore corrente e scrivo nel outputStream delsocket 
	 * l'evento che deve ricevere il client
	 */
	public void schermataScommessa2(Scommessa2Event e) {
		Socket connection = connections.get(e.getIdGiocatore());
		try {
		
			ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
			output.writeObject(e);
			output.flush();
			
				
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	/**
	 * 
	 * prendo il socket del giocatore corrente e scrivo nel outputStream delsocket 
	 * l'evento che deve ricevere il client
	 */
	public void warningScommessaMinima1(EmptyEvent e) {
		Socket connection = connections.get(e.getIdGiocatore());
		try {
		
			ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
			output.writeObject(e);
			output.flush();
			
				
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	/**
	 * 
	 * prendo il socket del giocatore corrente e scrivo nel outputStream delsocket 
	 * l'evento che deve ricevere il client
	 */
	public void warningDenariNonDisponibili1(EmptyEvent e) {
		Socket connection = connections.get(e.getIdGiocatore());
		try {
		
			ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
			output.writeObject(e);
			output.flush();
			
				
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	/**
	 * 
	 * prendo il socket del giocatore corrente e scrivo nel outputStream delsocket 
	 * l'evento che deve ricevere il client
	 */
	public void warningSegnaliniNonDisponibili1(EmptyEvent e) {
		Socket connection = connections.get(e.getIdGiocatore());
		try {
		
			ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
			output.writeObject(e);
			output.flush();
			
				
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	/**
	 * 
	 * prendo il socket del giocatore corrente e scrivo nel outputStream delsocket 
	 * l'evento che deve ricevere il client
	 */
	public void warningScommessaMinima2(EmptyEvent e) {
		Socket connection = connections.get(e.getIdGiocatore());
		try {
		
			ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
			output.writeObject(e);
			output.flush();
			
				
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	/**
	 * 
	 * prendo il socket del giocatore corrente e scrivo nel outputStream delsocket 
	 * l'evento che deve ricevere il client
	 */
	public void warningDenariNonDisponibili2(EmptyEvent e) {
		Socket connection = connections.get(e.getIdGiocatore());
		try {
		
			ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
			output.writeObject(e);
			output.flush();
			
				
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	/**
	 * 
	 * prendo il socket del giocatore corrente e scrivo nel outputStream delsocket 
	 * l'evento che deve ricevere il client
	 */
	public void warningSegnaliniNonDisponibili2(EmptyEvent e) {
		Socket connection = connections.get(e.getIdGiocatore());
		try {
		
			ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
			output.writeObject(e);
			output.flush();
			
				
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	/**
	 * 
	 * prendo il socket del giocatore corrente e scrivo nel outputStream delsocket 
	 * l'evento che deve ricevere il client
	 */
	public void schermataTruccaCorsa(TruccaCorsaEvent e) {
		Socket connection = connections.get(e.getIdGiocatore());
		try {
		
			ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
			output.writeObject(e);
			output.flush();
			
				
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	/**
	 * 
	 * prendo il socket del giocatore corrente e scrivo nel outputStream delsocket 
	 * l'evento che deve ricevere il client
	 */
	public void domandaScommessa2(DomandaScommessa2Event e) {
		Socket connection = connections.get(e.getIdGiocatore());
		try {
		
			ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
			output.writeObject(e);
			output.flush();
			
				
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	/**
	 * 
	 * manda in broadcast la schermata corsa a tutti i giocatori
	 */
	public void mostraCorsa(CorsaEvent e) {
		for (Socket socket : connections) {
			
			try {
			
				ObjectOutputStream output = new ObjectOutputStream(socket.getOutputStream());
				output.writeObject(e);
				output.flush();
				
					
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	/**
	 * 
	 * prendo il socket del giocatore corrente e scrivo nel outputStream delsocket 
	 * l'evento che deve ricevere il client
	 */
	public void schermataRiepilogoTurno(RiepilogoTurnoEvent e) {
		for (Socket socket : connections) {

			try {

				ObjectOutputStream output = new ObjectOutputStream(
						socket.getOutputStream());
				output.writeObject(e);
				output.flush();

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}

	}
	
	public void schermataNessunVincitore(EmptyEvent e) {
		for (Socket socket : connections) {

			try {

				ObjectOutputStream output = new ObjectOutputStream(
						socket.getOutputStream());
				output.writeObject(e);
				output.flush();

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	
	public void schermataVincitore(VincitoreEvent e) {
		for (Socket socket : connections) {

			try {

				ObjectOutputStream output = new ObjectOutputStream(
						socket.getOutputStream());
				output.writeObject(e);
				output.flush();

			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	/**
	 * 
	 * prendo il socket del giocatore corrente e scrivo nel outputStream delsocket 
	 * l'evento che deve ricevere il client
	 */
	public void schermataNonPuoiFareScommessa2(ErroreScommessa2Event e) {
		Socket connection = connections.get(e.getIdGiocatore());
		try {
		
			ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
			output.writeObject(e);
			output.flush();
			
				
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		
	}

	/**
	 * 
	 * prendo il socket del giocatore corrente e scrivo nel outputStream delsocket 
	 * l'evento che deve ricevere il client
	 */
	public void loadingScommessa1(EmptyEvent e) {
		Socket connection = connections.get(e.getIdGiocatore());
		try {
		
			ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
			output.writeObject(e);
			output.flush();
			
				
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	/**
	 * 
	 * prendo il socket del giocatore corrente e scrivo nel outputStream delsocket 
	 * l'evento che deve ricevere il client
	 */
	public void loadingScommessa2(EmptyEvent e) {
		Socket connection = connections.get(e.getIdGiocatore());
		try {
		
			ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
			output.writeObject(e);
			output.flush();
			
				
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	/**
	 * 
	 * prendo il socket del giocatore corrente e scrivo nel outputStream delsocket 
	 * l'evento che deve ricevere il client
	 */
	public void loadingTruccaCorsa(EmptyEvent e) {
		Socket connection = connections.get(e.getIdGiocatore());
		try {
		
			ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
			output.writeObject(e);
			output.flush();
			
				
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}

	/**
	 * 
	 * chiamo la funzione del controller per avanzare
	 * salto la schermata di loading
	 */
	public void loadingCorsa(EmptyEvent e) {
		controller.initCorsa();		
	}

	/**
	 * 
	 * chiamo la funzione del controller per avanzare
	 * salto la schermata di loading
	 */
	public void loadingChiusuraTurno(EmptyEvent e) {
		controller.chiusuraTurno();		
	}

	
	public void schermataAttendi() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * 
	 * prendo il socket del giocatore corrente e scrivo nel outputStream delsocket 
	 * l'evento che deve ricevere il client
	 */
	public void schermataSaltaScommessa1(ErroreScommessa1Event e) {
		Socket connection = connections.get(e.getIdGiocatore());
		try {
		
			ObjectOutputStream output = new ObjectOutputStream(connection.getOutputStream());
			output.writeObject(e);
			output.flush();
			
				
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}


}
