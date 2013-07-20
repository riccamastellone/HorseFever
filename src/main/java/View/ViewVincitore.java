package View;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controller_event.VincitoreEvent;

public class ViewVincitore extends JPanelFont {

	/**
	 * Create the panel.
	 */
	public ViewVincitore(VincitoreEvent e) {
		setLayout(null);
		setBounds(0, 0, 641, 482);
		
		JLabel label = new JLabel("ha vinto questa partita!");
		label.setFont(myFont);
		label.setBounds(123, 252, 476, 80);
		add(label);
		
		JLabel label2 = new JLabel(e.getVincitore().toUpperCase());
		label2.setFont(myFont);
		label2.setBounds(113, 169, 521, 80);
		add(label2);
		
		JLabel labelbg = new JLabel("");
		labelbg.setIcon(new ImageIcon(ViewChiediNumeroGiocatore.class.getResource("/Altro/bg.jpg")));
		labelbg.setBounds(0, 0, 640, 480);
		add(labelbg);
	}

}
