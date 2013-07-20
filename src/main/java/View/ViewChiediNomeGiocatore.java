package View;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextField;


import controller.ControllerInterface;
import controller_event.NomeGiocatoreControllerEvent;

import view_event.NomeGiocatoreEvent;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class ViewChiediNomeGiocatore extends JPanelFont {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public ViewChiediNomeGiocatore(final ControllerInterface controller, NomeGiocatoreControllerEvent e) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Come ti chiami Giocatore " + e.getNumero() + " ?");
		lblNewLabel.setFont(myFont);
		lblNewLabel.setBounds(29, 106, 605, 74);
		add(lblNewLabel);
		
		
		textField = new JTextField();
		textField.setFont(myFont);
		textField.setBounds(189, 191, 279, 88);
		add(textField);
		textField.setColumns(10);
		
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent event) {
				if(event.getKeyCode() == KeyEvent.VK_ENTER) {
					String nome = textField.getText();
			        controller.initGiocatore(new NomeGiocatoreEvent(nome));
			    
					
				}
			}
		});
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ViewChiediNumeroGiocatore.class.getResource("/Altro/bg.jpg")));
		label.setBounds(0, 0, 640, 480);
		add(label);
	}
}
