package View;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import controller.ControllerInterface;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewLoadingCorsa extends JPanelFont {

	/**
	 * Create the panel.
	 */
	public ViewLoadingCorsa(final ControllerInterface controller) {
		setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("Fase Corsa");
		lblNewLabel.setFont(myFont);

		lblNewLabel.setBounds(221, 110, 229, 130);
		add(lblNewLabel);
		
		
		JButton btnInizia = new JButton("Continua");
		btnInizia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.initCorsa();
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
