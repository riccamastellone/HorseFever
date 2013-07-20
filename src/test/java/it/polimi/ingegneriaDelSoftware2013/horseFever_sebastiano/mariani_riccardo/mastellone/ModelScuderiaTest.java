package it.polimi.ingegneriaDelSoftware2013.horseFever_sebastiano.mariani_riccardo.mastellone;

import static org.junit.Assert.*;

import java.util.Collections;

import model.Model;
import model.ModelInterface;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class ModelScuderiaTest {
	
	ModelInterface model;
	
	@Before
	public void setUp() throws Exception {
		model = Model.getInstance();
		model.initScuderie();
	}

	@After
	public void tearDown() throws Exception {
	}


	@Test
	public void testsetNumeroSegnaliniScommessaIniziali() {
		model.setNumeroSegnaliniScommessaIniziali(2);
		assertTrue(model.getScuderiabyColore("Nero").getNumSegnaliniScommessa()==1);
	}
	
	@Test
	public void testTogliSegnalinoScommessa() {
		model.setNumeroSegnaliniScommessaIniziali(2);
		model.togliSegnalinoScommessa(model.getScuderiabyColore("Nero"));
		assertTrue(model.getScuderiabyColore("Nero").getNumSegnaliniScommessa()==0);
	}

	@Test
	public void testOrdinaScuderieByQuotazione() {
		Collections.shuffle(model.getScuderie());
		model.ordinaScuderieByQuotazione(model.getScuderie());
		assertTrue(model.getScuderiaByQuotazione(2)==model.getScuderie().get(0));
	}

	@Test
	public void testOrdinaScuderieByPosizione() {
		Collections.shuffle(model.getScuderie());
		model.getScuderiabyColore("Nero").setPosizioneSegnalino(1000);
		model.ordinaScuderieByPosizione(model.getScuderie());
		assertTrue(model.getScuderiabyColore("Nero")==model.getScuderie().get(0));
	}


}
