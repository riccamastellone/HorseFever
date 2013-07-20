package View;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controller.ControllerInterface;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewLoadingChiusuraTurno extends JPanelFont {

	/**
	 * Create the panel.
	 */
	public ViewLoadingChiusuraTurno(final ControllerInterface controller) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("LA CORSA E' TERMINATA!");
		lblNewLabel.setFont(myFont);
		lblNewLabel.setBounds(77, 107, 533, 130);
		add(lblNewLabel);
		
		JButton btnInizia = new JButton("Continua");
		btnInizia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.chiusuraTurno();
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