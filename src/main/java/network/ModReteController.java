package network;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import model.Config;

import View.Output;

import view_event.NomeGiocatoreEvent;
import view_event.PiazzaScommessa2Event;
import view_event.PiazzaScommessaEvent;
import view_event.PiazzaTruccaCorsaEvent;
import view_event.RispostaDomandaScommessaEvent;
import controller.ControllerInterface;
import controller_event.EmptyEvent;

public class ModReteController implements ControllerInterface {

	private Client client;
	private Output output;
	
	public ModReteController(Output output) {
		this.output = output;
	}

	
	public void setClient(Client client) {
		this.client = client;
	}
	
	public void initScuderie() {
		// TODO Auto-generated method stub
		
	}

	
	public void setImpostazioniIniziali(Integer numGiocatore) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * apre la comunicazione con il server e gli spedisce l'evento ricevuto dalla view
	 */
	public void initGiocatore(NomeGiocatoreEvent e) {
		output.schermataAttendi();
		try {
			Socket socket = new Socket(Config.indirizzoServer, Config.portaServer);
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.writeObject(e);
			outputStream.flush();
			//outputStream.close();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		client.run();
		
		
	}

	/**
	 * apre la comunicazione con il server e crea un evento 
	 * con dentro una stringa che il server usera per riconoscere l evento e chiamare la funzione approprata
	 */
	public void faseScommessa1() {
		try {
			Socket socket = new Socket(Config.indirizzoServer, Config.portaServer);
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.writeObject(new EmptyEvent("iniziaScommessa1"));
			outputStream.flush();
			//outputStream.close();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		client.run();
		
	}

	/**
	 * apre la comunicazione con il server e crea un evento 
	 * con dentro una stringa che il server usera per riconoscere l evento e chiamare la funzione approprata
	 */
	public void faseScommessa2() {
		try {
			Socket socket = new Socket(Config.indirizzoServer, Config.portaServer);
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.writeObject(new EmptyEvent("iniziaScommessa2"));
			outputStream.flush();
			//outputStream.close();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		client.run();
		
	}

	/**
	 * apre la comunicazione con il server e gli spedisce l'evento ricevuto dalla view
	 */
	public void piazzaScommessa1(PiazzaScommessaEvent e) {
		try {
			Socket socket = new Socket(Config.indirizzoServer, Config.portaServer);
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.writeObject(e);
			outputStream.flush();
			//outputStream.close();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		client.run();
		
	}

	/**
	 * apre la comunicazione con il server e gli spedisce l'evento ricevuto dalla view
	 */
	public void piazzaScommessa2(PiazzaScommessa2Event e) {
		try {
			Socket socket = new Socket(Config.indirizzoServer, Config.portaServer);
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.writeObject(e);
			outputStream.flush();
			//outputStream.close();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		client.run();
		
		
	}

	/**
	 * apre la comunicazione con il server e crea un evento 
	 * con dentro una stringa che il server usera per riconoscere l evento e chiamare la funzione approprata
	 */
	public void truccaCorsa() {
		try {
			Socket socket = new Socket(Config.indirizzoServer, Config.portaServer);
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.writeObject(new EmptyEvent("iniziaTruccaCorsa"));
			outputStream.flush();
			//outputStream.close();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		client.run();
		
		
	}

	/**
	 * apre la comunicazione con il server e gli spedisce l'evento ricevuto dalla view
	 */
	public void piazzaTruccaCorsa(PiazzaTruccaCorsaEvent e) {
		try {
			Socket socket = new Socket(Config.indirizzoServer, Config.portaServer);
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.writeObject(e);
			outputStream.flush();
			//outputStream.close();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		client.run();
		
	}

	/**
	 * apre la comunicazione con il server e crea un evento 
	 * con dentro una stringa che il server usera per riconoscere l evento e chiamare la funzione approprata
	 */
	public void domandaScommessa2() {
		try {
			Socket socket = new Socket(Config.indirizzoServer, Config.portaServer);
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.writeObject(new EmptyEvent("domandaScommessa2"));
			outputStream.flush();
			//outputStream.close();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		client.run();
		
	}

	/**
	 * apre la comunicazione con il server e gli spedisce l'evento ricevuto dalla view
	 */
	public void valutaRispostaScommessa2(RispostaDomandaScommessaEvent e) {
		try {
			Socket socket = new Socket(Config.indirizzoServer, Config.portaServer);
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.writeObject(e);
			outputStream.flush();
			//outputStream.close();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		client.run();
		
	}

	/**
	 * apre la comunicazione con il server e crea un evento 
	 * con dentro una stringa che il server usera per riconoscere l evento e chiamare la funzione approprata
	 */
	public void faseCorsa() {
		try {
			Socket socket = new Socket(Config.indirizzoServer, Config.portaServer);
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.writeObject(new EmptyEvent("faseCorsa"));
			outputStream.flush();
			//outputStream.close();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		client.run();
		
	}

	
	public void initCorsa() {
		// TODO Auto-generated method stub
		
	}

	
	public void chiusuraTurno() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * apre la comunicazione con il server e crea un evento 
	 * con dentro una stringa che il server usera per riconoscere l evento e chiamare la funzione approprata
	 */
	public void controllaFine() {
		try {
			Socket socket = new Socket(Config.indirizzoServer, Config.portaServer);
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.writeObject(new EmptyEvent("controllaFine"));
			outputStream.flush();
			//outputStream.close();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		client.run();
		
	}

	
	public void fineGioco() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * apre la comunicazione con il server e crea un evento 
	 * con dentro una stringa che il server usera per riconoscere l evento e chiamare la funzione approprata
	 */
	public void valutaProssimaDomanda() {
		try {
			Socket socket = new Socket(Config.indirizzoServer, Config.portaServer);
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.writeObject(new EmptyEvent("valutaProssimaDomanda"));
			outputStream.flush();
			//outputStream.close();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		client.run();
		
	}


	/**
	 * apre la comunicazione con il server e crea un evento 
	 * con dentro una stringa che il server usera per riconoscere l evento e chiamare la funzione approprata
	 */
	public void valutaProssimaScommessa1() {
		try {
			Socket socket = new Socket(Config.indirizzoServer, Config.portaServer);
			ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
			outputStream.writeObject(new EmptyEvent("valutaProssimaScommessa1"));
			outputStream.flush();
			
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		client.run();
	}

}
