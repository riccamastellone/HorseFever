package View;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;

import model.Giocatore;

import controller.ControllerInterface;
import controller_event.RiepilogoTurnoEvent;

import java.util.ArrayList;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Color;



public class ViewRiepilogoCorsa extends JPanelFont {

	
	/**
	 * Pannello della corsa
	 * @param e 
	 * @param output 
	 */
	public ViewRiepilogoCorsa(final RiepilogoTurnoEvent e, final ControllerInterface controller) {

		setLayout(null);
		setBounds(0, 0, 641, 482);
		
		JButton btnNewButton = new JButton("Continua");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				controller.controllaFine();
			}
		});
		btnNewButton.setBounds(268, 414, 117, 29);
		add(btnNewButton);
		
		JLabel lblRiepilgo = new JLabel("Riepilogo");
		lblRiepilgo.setFont(myFont);
		lblRiepilgo.setBounds(232, 6, 191, 78);
		add(lblRiepilgo);
		
		JPanel panel = new JPanel();
		panel.setBackground(new Color(0,0,0,0));
		panel.setBounds(40, 96, 550, 307);
		add(panel);
		
		for (Giocatore giocatore : e.getElencoGiocatori()) {
			if (giocatore.getImportoVinto() !=0 ){
				JLabel nomiGiocatori = new JLabel(giocatore.getNome().toUpperCase()+ " ha vinto " + giocatore.getImportoVinto() + " denari e ha guadagnato " + giocatore.getVincitaPv() + " Punti Vita\n");
				nomiGiocatori.setBounds(43, 93, 200, 78);
				panel.add(nomiGiocatori);
			}
		}
	
		
		for (Giocatore giocatore : e.getElencoGiocatori()) {
			if (giocatore.getVincitaScuderia() !=0 ){
				JLabel nomiGiocatori = new JLabel(giocatore.getNome().toUpperCase() + " ha vinto " + giocatore.getVincitaScuderia() +" perche' prorietario della scuderia " + giocatore.getColoreScuderiaProprietario());
				nomiGiocatori.setBounds(43, 93, 200, 78);
				panel.add(nomiGiocatori);
			}
		}
		
		ArrayList<Giocatore> giocatoriPerdentiArrayList = new ArrayList<Giocatore>();
		for (Giocatore giocatore : e.getElencoGiocatori()) {
			if (giocatore.isPerditaPv()){
				giocatoriPerdentiArrayList.add(giocatore);
			}
		}
		if (giocatoriPerdentiArrayList.size() == 0) {
			JLabel messaggio = new JLabel("IN QUESTO TURNO NESSUN GIOCATORE HA PERSO PV");
			messaggio.setBounds(43, 93, 200, 78);
			panel.add(messaggio);
		}
		else {
			for (Giocatore giocatore : giocatoriPerdentiArrayList) {
				JLabel messaggio = new JLabel(giocatore.getNome().toUpperCase() + " ha perso 2 Punti Vita \n");
				messaggio.setBounds(43, 110, 200, 78);
				panel.add(messaggio);
			}
		}
		
		giocatoriPerdentiArrayList.clear();
		
		//metto in un arraylist tutti i giocatori che hanno perso e se e diversa da 0 li visualizzo
		for (Giocatore giocatore : e.getElencoGiocatori()) {
			if (giocatore.isPerso()){
				giocatoriPerdentiArrayList.add(giocatore);
			}
		}
		if (giocatoriPerdentiArrayList.size() == 0) {
			JLabel messaggio = new JLabel("IN QUESTO TURNO NESSUN GIOCATORE HA PERSO LA PARTITA");
			messaggio.setBounds(43, 93, 200, 78);
			panel.add(messaggio);
		}
		else {
			for (Giocatore giocatore : giocatoriPerdentiArrayList) {
				JLabel messaggio = new JLabel(giocatore.getNome().toUpperCase() + " ha perso la partita \n");
				messaggio.setBounds(43, 110, 200, 78);
				panel.add(messaggio);
			}
		}
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ViewRiepilogoCorsa.class.getResource("/Altro/bg.jpg")));
		label.setBounds(0, 0, 640, 480);
		add(label);
		
		
			

	}
}
