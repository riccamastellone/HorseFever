package View;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controller.ControllerInterface;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewLoadingTruccaCorsa extends JPanelFont {

	/**
	 * Create the panel.
	 */
	public ViewLoadingTruccaCorsa(final ControllerInterface controller) {

		setLayout(null);
		JButton btnInizia = new JButton("Continua");
		btnInizia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.truccaCorsa();
			}
		});
		btnInizia.setBounds(264, 260, 117, 29);
		add(btnInizia);
		
		JLabel lblFaseTruccaCorsa = new JLabel("Fase Trucca Corsa");
		lblFaseTruccaCorsa.setFont(myFont);
		lblFaseTruccaCorsa.setBounds(165, 169, 373, 80);
		add(lblFaseTruccaCorsa);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ViewChiediNumeroGiocatore.class.getResource("/Altro/bg.jpg")));
		label.setBounds(0, 0, 640, 480);
		add(label);
		
		

	}
	
	
}
