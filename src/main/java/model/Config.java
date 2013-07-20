package model;



/**
 * 
 * Classe statica con i parametri di configurazione del gioco
 * 
 * @author Sebastiano Mariani
 * @author Riccardo Mastellone
 * 
 *
 */


public final class Config {
	
	public static final String[] colori = { "Verde", "Blu", "Rosso", "Nero", "Bianco", "Giallo" };
	public static final int[] tipoDenari = { 100, 500, 1000 };
	public static final String[] tipoScommessa = { "vincente", "piazzato" };
	public static final int[] numeroTurni = { 6, 6, 4, 5, 6 };
	public static final int[] numeroSegnaliniScommessa = { 1, 2, 3, 4, 4 };
	public static String indirizzoServer = "127.0.0.1";
	public static int portaServer = 4000;

	// Caselle prima del traguardo (compreso)
	public static final int caselleBoard = 11;
	
	// Non vogliamo che la classe venga istanziata
	private Config() {
	}

}
