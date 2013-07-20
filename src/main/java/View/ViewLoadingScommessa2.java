package View;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controller.ControllerInterface;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewLoadingScommessa2 extends JPanelFont {

	/**
	 * Create the panel.
	 */
	public ViewLoadingScommessa2(final ControllerInterface controller) {

		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setBounds(188, 134, 178, 16);
		add(lblNewLabel);
		
		JLabel lblFaseTruccaCorsa = new JLabel("Fase Scommessa 2");
		lblFaseTruccaCorsa.setFont(myFont);
		lblFaseTruccaCorsa.setBounds(165, 169, 373, 80);
		add(lblFaseTruccaCorsa);
		
		JButton btnInizia = new JButton("Continua");
		btnInizia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.domandaScommessa2();
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