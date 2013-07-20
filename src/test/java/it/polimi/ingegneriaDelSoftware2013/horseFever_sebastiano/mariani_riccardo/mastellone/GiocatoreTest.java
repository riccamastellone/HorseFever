package it.polimi.ingegneriaDelSoftware2013.horseFever_sebastiano.mariani_riccardo.mastellone;

import static org.junit.Assert.*;


import model.CartaAzione;
import model.CartaPersonaggio;
import model.Giocatore;
import model.Mazzo;
import model.Model;

import org.junit.Before;
import org.junit.Test;

public class GiocatoreTest {
	Mazzo mazzo;
	Mazzo mazzo2;
	Model model;
	
	@Before
	public void setUp() throws Exception {
		// Istanzio i due mazzi Carta Personaggio e Carta Azione, senza mescolare e istanzio un giocatore.
		// Dato che non mescolo so in anticipo quali carte verranno assegnate.

		CartaPersonaggio c = new CartaPersonaggio();
        mazzo = new Mazzo();
        c.loadCarte(mazzo);
        
        CartaAzione ca = new CartaAzione();
        mazzo2 = new Mazzo();
        ca.loadCarte(mazzo2);
        
        // Devo inizializzare le scuderie per istanziare un giocatore
        model = Model.getInstance();
        model.initScuderie();
	}

	@Test
	public void testAssegnamentoCarte() {
		
        CartaPersonaggio cp = (CartaPersonaggio) mazzo.pesca();
        CartaAzione ca2 = (CartaAzione) mazzo2.pesca();
        CartaAzione ca3 = (CartaAzione) mazzo2.pesca();
        
		Giocatore giocatore = new Giocatore("Giovanni", cp, ca2, ca3);
		assertTrue("Carta Personaggio estratta errata",cp.getDenari()==4000);
		assertTrue("Carta Personaggio assegnata errata", giocatore.getCartaPersonaggio()==cp);
		assertTrue("Carta Azione 1 assegnata errata",giocatore.getCartaAzione1()==ca2);
		assertTrue("Carta Azione 1 assegnata errata",giocatore.getCartaAzione2()==ca3);
		assertTrue("Denari iniziali assegnati errati",giocatore.getDenari()==cp.getDenari());
	}
	
	
	@Test
	public void testImpostazioniGiocatore() {
		Giocatore giocatore = new Giocatore("Giovanni", (CartaPersonaggio) mazzo.pesca(), (CartaAzione) mazzo2.pesca(), (CartaAzione) mazzo2.pesca());
		assertTrue("PV iniziali errati",giocatore.getPv()==1);
	}
	
	
	@Test
	public void testCheckScommessaMinima() {
		Giocatore giocatore = new Giocatore("Giovanni", (CartaPersonaggio) mazzo.pesca(), (CartaAzione) mazzo2.pesca(), (CartaAzione) mazzo2.pesca());
		assertTrue(giocatore.checkScomessaMinima(100));
	}

	

}
