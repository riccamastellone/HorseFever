package View;

import javax.swing.JLabel;
import javax.swing.ImageIcon;

import controller.ControllerInterface;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewLoadingScommessa1 extends JPanelFont {

	/**
	 * Create the panel.
	 */
	public ViewLoadingScommessa1(final ControllerInterface controller) {
		setLayout(null);
		JLabel lblFaseTruccaCorsa = new JLabel("Fase Scommessa");
		lblFaseTruccaCorsa.setFont(myFont);
		lblFaseTruccaCorsa.setBounds(165, 169, 373, 80);
		add(lblFaseTruccaCorsa);
		
		JButton btnInizia = new JButton("Continua");
		btnInizia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.faseScommessa1();
			}
		});
		btnInizia.setBounds(264, 260, 117, 29);
		add(btnInizia);
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ViewChiediNumeroGiocatore.class.getResource("/Altro/bg.jpg")));
		label.setBounds(0, 0, 640, 480);
		add(label);
		
		

	}
}
