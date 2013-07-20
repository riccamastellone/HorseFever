package it.polimi.ingegneriaDelSoftware2013.horseFever_sebastiano.mariani_riccardo.mastellone;

import static org.junit.Assert.*;

import model.Giocatore;
import model.Model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ModelGiocatoreTest {
	Model model;

	@Before
	public void setUp() throws Exception {
		model = Model.getInstance();
		model.initScuderie();
		model.initMazzi();
		// In quanto Model e' un singleton
		if(model.getGiocatori().size() != 3) {
			model.initGiocatore("Giocatore1");
			model.initGiocatore("Giocatore2");
			model.initGiocatore("Giocatore3");
		}
		
		
	}

	@After
	public void tearDown() throws Exception {
		
	}
	
	@Test
	public void testInitGiocatore() {
		assertTrue(model.getGiocatori().size()==3);
	}
	
	@Test
	public void testGetGiocatoreByNome() {
		assertTrue(model.getGiocatoreByNome("Giocatore1")==model.getGiocatori().get(0));
		
	}

	@Test
	public void testNextGiocatoreCorrente() {
		assertTrue(model.getGiocatoreCorrente()==model.getGiocatori().get(0));
	}


	@Test
	public void testRispettaScommessaMinima() {
		model.getGiocatoreByNome("Giocatore1").setPv(1);
		assertTrue(model.rispettaScommessaMinima(model.getGiocatoreByNome("Giocatore1"), 100));
		assertFalse(model.rispettaScommessaMinima(model.getGiocatoreByNome("Giocatore1"), 99));
	}

	@Test
	public void testDenariDisponibiliPerScommessa() {
		model.getGiocatoreByNome("Giocatore1").setDenari(2000);
		assertTrue(model.denariDisponibiliPerScommessa(model.getGiocatoreByNome("Giocatore1"), 2000));
		assertFalse(model.denariDisponibiliPerScommessa(model.getGiocatoreByNome("Giocatore1"), 2001));
	}

	@Test
	public void testPiazzaPrimaScommessa() {
		model.piazzaPrimaScommessa(model.getGiocatoreByNome("Giocatore1"), 1000, model.getScuderiabyColore("Nero"), "vincente");
		assertTrue(model.getGiocatoreByNome("Giocatore1").getPrimaScommessa().getScuderiaScommessa() == model.getScuderiabyColore("Nero"));
		assertTrue(model.getGiocatoreByNome("Giocatore1").getPrimaScommessa().getTipoScommessa() == "vincente");
		assertTrue(model.getGiocatoreByNome("Giocatore1").getPrimaScommessa().getValore() == 1000);
	}

	@Test
	public void testPiazzaSecondaScommessa() {
		model.piazzaSecondaScommessa(model.getGiocatoreByNome("Giocatore1"), 1000, model.getScuderiabyColore("Bianco"), "piazzato");
		assertTrue(model.getGiocatoreByNome("Giocatore1").getSecondaScommessa().getScuderiaScommessa() == model.getScuderiabyColore("Bianco"));
		assertTrue(model.getGiocatoreByNome("Giocatore1").getSecondaScommessa().getTipoScommessa() == "piazzato");
		assertTrue(model.getGiocatoreByNome("Giocatore1").getSecondaScommessa().getValore() == 1000);
	}

	@Test
	public void testOrdinaGiocatoreByPv() {
		model.getGiocatoreByNome("Giocatore1").setPv(4);
		model.getGiocatoreByNome("Giocatore2").setPv(2);
		model.getGiocatoreByNome("Giocatore3").setPv(5);
		model.ordinaGiocatoreByPv(model.getGiocatori());
		assertTrue(model.getGiocatori().get(0) == model.getGiocatoreByNome("Giocatore3"));
		assertTrue(model.getGiocatori().get(1) == model.getGiocatoreByNome("Giocatore1"));
		assertTrue(model.getGiocatori().get(2) == model.getGiocatoreByNome("Giocatore2"));
	}

	@Test
	public void testOrdinaGiocatoreByDenari() {
		model.getGiocatoreByNome("Giocatore1").setDenari(2000);
		model.getGiocatoreByNome("Giocatore2").setDenari(1999);
		model.getGiocatoreByNome("Giocatore3").setDenari(2001);
		model.ordinaGiocatoreByDenari(model.getGiocatori());
		assertTrue(model.getGiocatori().get(0) == model.getGiocatoreByNome("Giocatore3"));
		assertTrue(model.getGiocatori().get(1) == model.getGiocatoreByNome("Giocatore1"));
		assertTrue(model.getGiocatori().get(2) == model.getGiocatoreByNome("Giocatore2"));
	}

	@Test
	public void testRimuoviGiocatoriPerdenti() {
		model.initGiocatore("Giocatore4");
		model.getGiocatoreByNome("Giocatore4").setPerso(true);
		model.rimuoviGiocatoriPerdenti();
		assertTrue(model.getGiocatori().size()==3);
		
	}

	@Test
	public void testMarcaGiocatoriPerdenti() {
		model.getGiocatoreByNome("Giocatore3").setPv(0);
		model.marcaGiocatoriPerdenti();
		assertTrue(model.getGiocatoreByNome("Giocatore3").isPerso());
		model.getGiocatoreByNome("Giocatore3").setPerso(false);
	}


}
