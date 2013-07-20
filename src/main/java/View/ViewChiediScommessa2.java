package View;

import javax.swing.JLabel;
import javax.swing.JButton;

import controller.ControllerInterface;
import controller_event.DomandaScommessa2Event;

import view_event.RispostaDomandaScommessaEvent;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.ImageIcon;


public class ViewChiediScommessa2 extends JPanelFont {

	/**
	 * Create the panel.
	 * @param e 
	 */
	public ViewChiediScommessa2(final DomandaScommessa2Event e, final ControllerInterface controller) {
		setLayout(null);
		
		JLabel lblNewLabel2 = new JLabel("TURNO DI : "+ e.getGiocatore().getNome().toUpperCase() );
		lblNewLabel2.setFont(myFont);
		lblNewLabel2.setBounds(91, 11, 602, 88);
		add(lblNewLabel2);
		
		
		JLabel lblNewLabel = new JLabel("Vuoi fare la seconda scommessa?");
		lblNewLabel.setFont(myFont);
		lblNewLabel.setBounds(20, 159, 614, 74);
		add(lblNewLabel);
		
		JButton btnSi = new JButton("SI");
		btnSi.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.valutaRispostaScommessa2(new RispostaDomandaScommessaEvent(0, e.getGiocatore()));
			}
		});
		btnSi.setBounds(244, 280, 89, 23);
		add(btnSi);
		
		JButton btnNo = new JButton("NO");
		btnNo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent event) {
				controller.valutaRispostaScommessa2(new RispostaDomandaScommessaEvent(1, e.getGiocatore()));

			}
		});
		btnNo.setBounds(343, 280, 89, 23);
		add(btnNo);
		
		
		
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ViewChiediScommessa2.class.getResource("/Altro/bg.jpg")));
		label.setBounds(0, 0, 640, 480);
		add(label);
		setVisible(true);

	}
}
