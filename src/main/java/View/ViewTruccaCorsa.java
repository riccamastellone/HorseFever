package View;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JTextArea;

import model.Config;

import controller.ControllerInterface;
import controller_event.TruccaCorsaEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.BoxLayout;

import view_event.PiazzaTruccaCorsaEvent;
import javax.swing.ImageIcon;
import java.awt.Font;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.SwingConstants;

public class ViewTruccaCorsa extends JPanelFont {
	
	private boolean carta1Scelta = false;
	private boolean carta2Scelta = false;

	/**
	 * Create the panel.
	 * @param e 
	 * @param output 
	 */
	public ViewTruccaCorsa(final TruccaCorsaEvent e, final ControllerInterface controller) {
		setLayout(null);
		
		JLabel nome1 = new JLabel(e.getCarta1().getNome());
		nome1.setBounds(115, 84, 209, 16);
		add(nome1);
		
		
		
		final JLabel cartaScelta = new JLabel("");
		cartaScelta.setBounds(24, 426, 165, 16);
		add(cartaScelta);
		
		//creo un nuovo panel con dentro i bottoni per la scelta della scuderia
		final JLabel scuderiaScelta = new JLabel("");
		scuderiaScelta.setBounds(199, 426, 135, 16);
		add(scuderiaScelta);
		
		
		
		final JLabel labelCarta1 = new JLabel("");
		labelCarta1.setHorizontalAlignment(SwingConstants.CENTER);
		final Image img1 = new ImageIcon(ViewCorsa.class.getResource(e.getCarta1().getImmagine())).getImage(); 
		labelCarta1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				labelCarta1.setIcon(new ImageIcon(img1));
				labelCarta1.setBounds(53, 21,  537, 417);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				labelCarta1.setIcon(new ImageIcon(img1.getScaledInstance(77, 120,  java.awt.Image.SCALE_SMOOTH)));
				labelCarta1.setBounds(27, 84, 77, 120);
			}
		});
		 
		labelCarta1.setIcon(new ImageIcon(img1.getScaledInstance(77, 120,  java.awt.Image.SCALE_SMOOTH)));
		
		
		labelCarta1.setBounds(27, 84, 77, 120);
		add(labelCarta1);
		
		
		
		
		final JLabel labelCarta2 = new JLabel("");
		labelCarta2.setHorizontalAlignment(SwingConstants.CENTER);
		final Image img2 = new ImageIcon(ViewCorsa.class.getResource(e.getCarta2().getImmagine())).getImage();  
		labelCarta2.setIcon(new ImageIcon(img2.getScaledInstance(77, 120,  java.awt.Image.SCALE_SMOOTH)));
		
		labelCarta2.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				labelCarta2.setIcon(new ImageIcon(img2));
				labelCarta2.setBounds(53, 21,  537, 417);
			}
			@Override
			public void mouseReleased(MouseEvent e) {
				labelCarta2.setIcon(new ImageIcon(img2.getScaledInstance(77, 120,  java.awt.Image.SCALE_SMOOTH)));
				labelCarta2.setBounds(27, 215, 77, 120);
			}
		});
		
		
		
		JButton btnCarta2 = new JButton("Seleziona");
		btnCarta2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				carta2Scelta = true;
				if (carta1Scelta) {
					carta2Scelta = false;
				}
				cartaScelta.setText(e.getCarta2().getNome());
			}
		});
		labelCarta2.setBounds(27, 215, 77, 120);
		add(labelCarta2);
		
		btnCarta2.setBounds(115, 300, 108, 29);
		add(btnCarta2);
		
		
		JButton btnCarta1 = new JButton("Seleziona");
		btnCarta1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				carta1Scelta = true;
				if (carta2Scelta) {
					carta2Scelta = false;
				}
				cartaScelta.setText(e.getCarta1().getNome());
			}
		});
		btnCarta1.setBounds(115, 168, 108, 29);
		add(btnCarta1);
		
		
		
		
		
		JTextArea descrizione1 = new JTextArea();
		descrizione1.setFont(new Font("Tahoma", Font.PLAIN, 11));
		descrizione1.setEditable(false);
		descrizione1.setLineWrap(true);
		descrizione1.setBackground(new Color(0,0,0,0));
		descrizione1.setText(e.getCarta1().getDescrizione());
		descrizione1.setBounds(115, 111, 498, 59);
		add(descrizione1);
		
		JLabel nome2 = new JLabel(e.getCarta2().getNome());
		nome2.setBounds(115, 215, 249, 22);
		add(nome2);
		
		JTextArea descrizione2 = new JTextArea();
		descrizione2.setFont(new Font("Tahoma", Font.PLAIN, 11));
		descrizione2.setEditable(false);
		descrizione2.setLineWrap(true);
		descrizione2.setBackground(new Color(0,0,0,0));
		descrizione2.setText(e.getCarta2().getDescrizione());
		descrizione2.setBounds(115, 248, 498, 59);
		add(descrizione2);
		
		JPanel sceltaScuderia = new JPanel();
		sceltaScuderia.setBackground(new Color(0,0,0,0));
		sceltaScuderia.setBounds(27, 356, 586, 40);
		add(sceltaScuderia);
		sceltaScuderia.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		for (String colore : Config.colori) {
			final JButton btnScuderia = new JButton(colore);
			btnScuderia.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					
					scuderiaScelta.setText(btnScuderia.getText());
						
				}
			});
			sceltaScuderia.add(btnScuderia);
		}
		sceltaScuderia.setVisible(true);


		JLabel labelScuderia = new JLabel("Scuderia scelta:");
		labelScuderia.setBounds(199, 411, 108, 16);
		add(labelScuderia);
		
		JLabel lblCartaScelta = new JLabel("Carta scelta:");
		lblCartaScelta.setBounds(27, 411, 90, 16);
		add(lblCartaScelta);
		
		JButton btnTruccaCorsa = new JButton("Trucca corsa");
		btnTruccaCorsa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if ( ((carta1Scelta == false) && (carta2Scelta == false)) || (scuderiaScelta.getText().equals("")) ){
					JOptionPane.showMessageDialog(null, "non hai scelto tutti i campi!");
				}
				else if (carta1Scelta) {
					controller.piazzaTruccaCorsa(new PiazzaTruccaCorsaEvent(1, scuderiaScelta.getText(), e.getGiocatore()));
				}
				else {
					controller.piazzaTruccaCorsa(new PiazzaTruccaCorsaEvent(2, scuderiaScelta.getText(), e.getGiocatore()));
				}
			}
		});
		btnTruccaCorsa.setBounds(495, 411, 117, 29);
		add(btnTruccaCorsa);
		
		JLabel lblNewLabel = new JLabel("Turno di " + e.getGiocatore().getNome());
		lblNewLabel.setFont(myFont);
		lblNewLabel.setBounds(164, 0, 573, 80);
		add(lblNewLabel);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ViewTruccaCorsa.class.getResource("/Altro/bg_1280.jpg")));
		label.setBounds(0, 0, 640, 480);
		add(label);
		
		
	}
}
