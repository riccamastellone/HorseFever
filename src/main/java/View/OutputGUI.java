package View;

import java.awt.GraphicsEnvironment;
import java.awt.Point;


import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;


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


public class OutputGUI extends JFrame implements Output{

	private JPanel contentPane;
	private JPanel viewNuova;
	private ControllerInterface controller;


	/**
	 * Create the frame.
	 */
	public OutputGUI() {
		setTitle("HorseFever GUI"); 
		setResizable(false);
		setIconImage(new ImageIcon(this.getClass().getResource("/Altro/Icona.png")).getImage());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
	    int windowWidth = 640;
	    int windowHeight = 480;
	    setBounds(center.x - windowWidth / 2, center.y - windowHeight / 2, windowWidth,
	        windowHeight);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setVisible(true);
		
	}
	
	public void setController(ControllerInterface controller) {
		this.controller = controller;
	}



	/**
	 * creo il nuovo jpanel per chiedere il numero giocatori e lo rendo visibile in questo frame
	 * passo al frame da creare me stesso(serve per chiamare il metodo del controller) e l evento ricevuto
	 */
	public void chiediNumeroGiocatori(EmptyEvent e) {
		
		contentPane.setVisible(false);
		contentPane.removeAll();
		viewNuova = new ViewChiediNumeroGiocatore(this, controller);
		viewNuova.setBounds(0, 0, 640, 480);
		contentPane.add(viewNuova);
		contentPane.validate();
		contentPane.repaint();
		contentPane.setVisible(true);
		
	}

	/**
	 * creo il nuovo jpanel per chiedere il nome e lo rendo visibile in questo frame
	 * passo al frame da creare me stesso(serve per chiamare il metodo del controller) e l evento ricevuto
	 */

	public void chiediNomeGiocatore(NomeGiocatoreControllerEvent e) {
		
		contentPane.setVisible(false);
		contentPane.removeAll();
		viewNuova = new ViewChiediNomeGiocatore(controller, e); 
		viewNuova.setBounds(0, 0, 640, 480);
		contentPane.add(viewNuova);
		contentPane.validate();
		contentPane.repaint();
		contentPane.setVisible(true);
	}

	/**
	 * creo il nuovo jpanel per chiedere la scommessa1 lo rendo visibile in questo frame
	 * creo la lavagna quotazioni all interno del jpanel nuovo
	 * passo al frame da creare me stesso(serve per chiamare il metodo del controller) e l evento ricevuto
	 */

	public void schermataScommessa1(ScommessaEvent e) {
		contentPane.setVisible(false);
		contentPane.removeAll();
		viewNuova = new ViewScommessa1(e ,controller);
		viewNuova.setBounds(0, 0, 640, 480);
		contentPane.add(viewNuova);
		contentPane.validate();
		contentPane.repaint();
		contentPane.setVisible(true);
	}


	public void schermataScommessa2(Scommessa2Event e) {
		contentPane.setVisible(false);
		contentPane.removeAll();
		viewNuova = new ViewScommessa2(e ,controller);
		viewNuova.setBounds(0, 0, 640, 480);
		contentPane.add(viewNuova);
		contentPane.validate();
		contentPane.repaint();
		contentPane.setVisible(true);
		
	}


	public void warningScommessaMinima1(EmptyEvent e) {
		JOptionPane.showMessageDialog(null, "Non hai rispettato la scommessa minima");
		controller.faseScommessa1();
			
	}


	public void warningDenariNonDisponibili1(EmptyEvent e) {
		JOptionPane.showMessageDialog(null, "Hai scommesso piu denari di quelli in tuo possesso");
		controller.faseScommessa1();
		
	}


	public void warningSegnaliniNonDisponibili1(EmptyEvent e) {
		JOptionPane.showMessageDialog(null, "La scuderia scelta non ha piu segnalini scommessa disponibili");
		controller.faseScommessa1();
		
	}
	

	public void warningScommessaMinima2(EmptyEvent e) {
		JOptionPane.showMessageDialog(null, "Non hai rispettato la scommessa minima");
		controller.faseScommessa2();
		
		
		
	}


	public void warningDenariNonDisponibili2(EmptyEvent e) {
		JOptionPane.showMessageDialog(null, "Hai scommesso piu denari di quelli in tuo possesso");
		controller.faseScommessa2();
		
	}


	public void warningSegnaliniNonDisponibili2(EmptyEvent e) {
		JOptionPane.showMessageDialog(null, "La scuderia scelta non ha piu segnalini scommessa disponibili");
		controller.faseScommessa2();
		
	}



	public void schermataTruccaCorsa(TruccaCorsaEvent e) {
		contentPane.setVisible(false);
		contentPane.removeAll();
		viewNuova = new ViewTruccaCorsa(e, controller);
		viewNuova.setBounds(0, 0, 1280, 720);
		contentPane.add(viewNuova);
		contentPane.validate();
		contentPane.repaint();
		contentPane.setVisible(true);
		
		
	}
	
	


	public void domandaScommessa2(DomandaScommessa2Event e) {
		
		contentPane.setVisible(false);
		contentPane.removeAll();
		viewNuova = new ViewChiediScommessa2(e, controller);
		viewNuova.setBounds(0, 0, 640, 480);
		contentPane.add(viewNuova);
		contentPane.validate();
		contentPane.repaint();
		contentPane.setVisible(true);
		
	}


	public void mostraCorsa(CorsaEvent e) {
		contentPane.setVisible(false);
		contentPane.removeAll();
		viewNuova = new ViewCorsa(e, controller);
		viewNuova.setBounds(0, 0, 1280, 720);
		Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
	    int windowWidth = 1280;
	    int windowHeight = 720;
	    setBounds(center.x - windowWidth / 2, center.y - windowHeight / 2, windowWidth,
	        windowHeight);
		contentPane.add(viewNuova);
		contentPane.validate();
		contentPane.repaint();
		contentPane.setVisible(true);		
	}



	public void schermataNessunVincitore(EmptyEvent e) {
		contentPane.setVisible(false);
		contentPane.removeAll();
		viewNuova = new ViewNessunVincitore();
		viewNuova.setBounds(0, 0, 640, 480);
		contentPane.add(viewNuova);
		contentPane.validate();
		contentPane.repaint();
		contentPane.setVisible(true);
		
	}


	public void schermataVincitore(VincitoreEvent e) {
		contentPane.setVisible(false);
		contentPane.removeAll();
		viewNuova = new ViewVincitore(e);
		viewNuova.setBounds(0, 0, 640, 480);
		contentPane.add(viewNuova);
		contentPane.validate();
		contentPane.repaint();
		contentPane.setVisible(true);
		
	}




	public void schermataRiepilogoTurno(RiepilogoTurnoEvent e) {
	
		contentPane.setVisible(false);
		contentPane.removeAll();
		viewNuova = new ViewRiepilogoCorsa(e, controller);
		viewNuova.setBounds(0, 0, 640, 480);
		Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
	    int windowWidth = 640;
	    int windowHeight = 480;
	    setBounds(center.x - windowWidth / 2, center.y - windowHeight / 2, windowWidth,
	        windowHeight);
		contentPane.add(viewNuova);
		contentPane.validate();
		contentPane.repaint();
		contentPane.setVisible(true);
		
	}




	public void schermataNonPuoiFareScommessa2(ErroreScommessa2Event e) {
		JOptionPane.showMessageDialog(null, "Non hai denari a sufficienza per fare la seconda scommessa!");
		controller.valutaProssimaDomanda();
		
	}




	public void loadingScommessa1(EmptyEvent e) {
		contentPane.setVisible(false);
		contentPane.removeAll();
		viewNuova = new ViewLoadingScommessa1(controller);
		viewNuova.setBounds(0, 0, 640, 480);
		contentPane.add(viewNuova);
		contentPane.validate();
		contentPane.repaint();
		contentPane.setVisible(true);
		
	}




	public void loadingScommessa2(EmptyEvent e) {
		contentPane.setVisible(false);
		contentPane.removeAll();
		viewNuova = new ViewLoadingScommessa2(controller);
		viewNuova.setBounds(0, 0, 640, 480);
		Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
	    int windowWidth = 640;
	    int windowHeight = 480;
	    setBounds(center.x - windowWidth / 2, center.y - windowHeight / 2, windowWidth,
	        windowHeight);
		contentPane.add(viewNuova);
		contentPane.validate();
		contentPane.repaint();
		contentPane.setVisible(true);		
	}




	public void loadingTruccaCorsa(EmptyEvent e) {
		contentPane.setVisible(false);
		contentPane.removeAll();
		viewNuova = new ViewLoadingTruccaCorsa(controller);
		viewNuova.setBounds(0, 0, 640, 480);
		contentPane.add(viewNuova);
		contentPane.validate();
		contentPane.repaint();
		contentPane.setVisible(true);
		
	}




	public void loadingCorsa(EmptyEvent e) {
		contentPane.setVisible(false);
		contentPane.removeAll();
		viewNuova = new ViewLoadingCorsa(controller);
		viewNuova.setBounds(0, 0, 640, 480);
		contentPane.add(viewNuova);
		contentPane.validate();
		contentPane.repaint();
		contentPane.setVisible(true);
		
		
	}


	public void loadingChiusuraTurno(EmptyEvent e) {
		contentPane.setVisible(false);
		contentPane.removeAll();
		viewNuova = new ViewLoadingChiusuraTurno(controller);
		Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
	    int windowWidth = 640;
	    int windowHeight = 480;
	    setBounds(center.x - windowWidth / 2, center.y - windowHeight / 2, windowWidth,
	        windowHeight);
		viewNuova.setBounds(0, 0, 640, 480);
		contentPane.add(viewNuova);
		contentPane.validate();
		contentPane.repaint();
		contentPane.setVisible(true);		
	}
	
	

	public void schermataAttendi() {
		contentPane.setVisible(false);
		contentPane.removeAll();
		viewNuova = new ViewAttendi();
		viewNuova.setBounds(0, 0, 640, 480);
		contentPane.add(viewNuova);
		contentPane.validate();
		contentPane.repaint();
		contentPane.setVisible(true);
		
	}

	
	public void schermataSaltaScommessa1(ErroreScommessa1Event e) {
		JOptionPane.showMessageDialog(null, "Non hai denari a sufficienza per fare la prima scommessa!");
		controller.valutaProssimaScommessa1();
		
	}
}
