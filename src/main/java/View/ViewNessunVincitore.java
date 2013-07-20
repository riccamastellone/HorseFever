package View;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

public class ViewNessunVincitore extends JPanelFont {

	/**
	 * Create the panel.
	 */
	public ViewNessunVincitore() {
		setLayout(null);
		setBounds(0, 0, 641, 482);
		
		JLabel label = new JLabel("Nessuno ha vinto questa partita");
		label.setFont(myFont);
		label.setBounds(79, 169, 555, 80);
		add(label);
		
		JLabel labelbg = new JLabel("");
		labelbg.setIcon(new ImageIcon(ViewChiediNumeroGiocatore.class.getResource("/Altro/bg.jpg")));
		labelbg.setBounds(0, 0, 640, 480);
		add(labelbg);
	}

}
