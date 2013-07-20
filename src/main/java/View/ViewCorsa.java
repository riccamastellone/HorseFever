package View;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

import model.Giocatore;
import model.Scuderia;

import controller.ControllerInterface;
import controller_event.CorsaEvent;

import java.awt.Color;
import java.awt.Image;


import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;



public class ViewCorsa extends JPanel {

	
	/**
	 * Pannello della corsa
	 * @param e 
	 * @param output 
	 */
	public ViewCorsa(final CorsaEvent e, final ControllerInterface controller) {
		int i = 0;

		setLayout(null);
		setBounds(0, 0, 1280, 720);
		
		JPanel carteAzioneNero = new JPanel();
		carteAzioneNero.setBounds(10, 93, 141, 67);
		carteAzioneNero.setBackground(new Color(0,0,0,0));
		add(carteAzioneNero);
		
		JPanel carteAzioneBlu = new JPanel();
		carteAzioneBlu.setBounds(10, 93+(65), 141, 67);
		carteAzioneBlu.setBackground(new Color(0,0,0,0));
		add(carteAzioneBlu);
		
		JPanel carteAzioneVerde = new JPanel();
		carteAzioneVerde.setBounds(10, 93+(65*2), 141, 67);
		carteAzioneVerde.setBackground(new Color(0,0,0,0));
		add(carteAzioneVerde);
		
		JPanel carteAzioneRosso = new JPanel();
		carteAzioneRosso.setBounds(10, 93+(65*3), 141, 67);
		carteAzioneRosso.setBackground(new Color(0,0,0,0));
		add(carteAzioneRosso);
		
		JPanel carteAzioneGiallo = new JPanel();
		carteAzioneGiallo.setBounds(10, 93+(65*4), 141, 67);
		carteAzioneGiallo.setBackground(new Color(0,0,0,0));
		add(carteAzioneGiallo);
		
		JPanel carteAzioneBianco = new JPanel();
		carteAzioneBianco.setBounds(10, 93+(65*5), 141, 67);
		carteAzioneBianco.setBackground(new Color(0,0,0,0));
		add(carteAzioneBianco);
		
		
		
		// Metto i segnalini sul tabellone calcolando i pixel in base al colore
		for(Scuderia s : e.getElencoScuderie()) {
				JLabel lblNewLabel = new JLabel("");
				lblNewLabel.setIcon(new ImageIcon(ViewCorsa.class.getResource("/SegnaliniQuotazioniScuderie/SegnalinoQuotazioniScuderia" + s.getColore() + ".jpg")));
				
				if(s.getColore().equals("Nero")) {
					i = 0;
				} else if(s.getColore().equals("Blu")) {
					i = 1;
				} else if(s.getColore().equals("Verde")) {
					i = 2;
				} else if(s.getColore().equals("Rosso")) {
					i = 3;
				} else if(s.getColore().equals("Giallo")) {
					i = 4;
				} else if(s.getColore().equals("Bianco")) {
					i = 5;
				}  
					
				lblNewLabel.setBounds(154 + (65*s.getPosizioneSegnalino()), 101 + (64*i), 61, 55);
				add(lblNewLabel);
		}
		
		for(Giocatore giocatore: e.getElencoGiocatori()) {
			Image carta = new ImageIcon(ViewCorsa.class.getResource(giocatore.getCartaAzioneGiocata().getImmagine())).getImage(); 
			JLabel cartaAzione = new JLabel("");
			cartaAzione.setVerticalAlignment(SwingConstants.CENTER);
			cartaAzione.setToolTipText(giocatore.getCartaAzioneGiocata().getNome() + " - " + giocatore.getCartaAzioneGiocata().getDescrizione());
			cartaAzione.setIcon(new ImageIcon(carta.getScaledInstance(42,65, java.awt.Image.SCALE_SMOOTH)));
			
			if(giocatore.getCartaAzioneGiocata().getScuderiaEffetto().equals("Nero")) {
				carteAzioneNero.add(cartaAzione);
			} else if(giocatore.getCartaAzioneGiocata().getScuderiaEffetto().equals("Blu")) {
				carteAzioneBlu.add(cartaAzione);
			} else if(giocatore.getCartaAzioneGiocata().getScuderiaEffetto().equals("Verde")) {
				carteAzioneVerde.add(cartaAzione);
			} else if(giocatore.getCartaAzioneGiocata().getScuderiaEffetto().equals("Rosso")) {
				carteAzioneRosso.add(cartaAzione);
			} else if(giocatore.getCartaAzioneGiocata().getScuderiaEffetto().equals("Giallo")) {
				carteAzioneGiallo.add(cartaAzione);
			} else if(giocatore.getCartaAzioneGiocata().getScuderiaEffetto().equals("Bianco")) {
				carteAzioneBianco.add(cartaAzione);
			}  
			
		}
		
		JButton btnNewButton = new JButton("");
		Image img = new ImageIcon(ViewCorsa.class.getResource("/Movimento/retro.jpg")).getImage();  
		btnNewButton.setIcon(new ImageIcon(img.getScaledInstance(129, 200,  java.awt.Image.SCALE_SMOOTH)));
        btnNewButton.setBounds(348, 498, 124, 197);
        btnNewButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                controller.faseCorsa();
            }
        });
        add(btnNewButton);
        
        if(ViewCorsa.class.getResource(e.getCorsa().getLastCartaMovimento().getImmagine()) != null) {
        	JLabel btnNewButton1 = new JLabel("");
    		Image img1 = new ImageIcon(ViewCorsa.class.getResource(e.getCorsa().getLastCartaMovimento().getImmagine())).getImage();  
    		btnNewButton1.setIcon(new ImageIcon(img1.getScaledInstance(129, 200,  java.awt.Image.SCALE_SMOOTH)));
            
            btnNewButton1.setBounds(189, 498, 124, 197);
            add(btnNewButton1);
        }
		String temp = "Classifica Corsa: \r\n ";
        for ( Scuderia s: e.getCorsa().getClassifica()) {
        	temp = temp + s.getColore() + " \r\n ";
		}
		
		JLabel lblPrimaScuderiaSprint = new JLabel("Prima scuderia sprint:");
		lblPrimaScuderiaSprint.setBounds(563, 619, 146, 16);
		add(lblPrimaScuderiaSprint);
		
		JLabel lblNewLabel_1 = new JLabel("Seconda scuderia sprint:");
		lblNewLabel_1.setBounds(563, 647, 168, 16);
		add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("");
		lblNewLabel_2.setBounds(770, 619, 115, 16);
		add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("");
		lblNewLabel_3.setBounds(770, 647, 115, 16);
		add(lblNewLabel_3);
		JLabel classifica = new JLabel(temp);
		classifica.setBounds(563, 498, 636, 197);
		add(classifica);
		
		lblNewLabel_2.setText(e.getCorsa().getScuderiaSprint1());
		if (e.getCorsa().getScuderiaSprint2() != null) {
			lblNewLabel_3.setText(e.getCorsa().getScuderiaSprint2());
		}
		
		
		
		
		JLabel sfondo = new JLabel("");
		sfondo.setIcon(new ImageIcon(ViewCorsa.class.getResource("/ComponentiTabellone/TabelloneNew2.jpg")));
		sfondo.setBounds(0, 0, 1280, 720);
		add(sfondo);
		
		
		
		
			

	}
}
