package View;

import javax.swing.JOptionPane;
import javax.swing.JLabel;
import javax.swing.JTextField;

import controller.ControllerInterface;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import javax.swing.ImageIcon;


public class ViewChiediNumeroGiocatore extends JPanelFont {
	private JTextField textField;

	/**
	 * Create the panel.
	 */
	public ViewChiediNumeroGiocatore(final OutputGUI output, final ControllerInterface controller) {
		setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Quanti giocatori siete?");
		lblNewLabel.setFont(myFont);
		lblNewLabel.setBounds(96, 105, 468, 74);
		add(lblNewLabel);
		
		textField = new JTextField();
		textField.addKeyListener(new KeyAdapter() {
			@Override
			public void keyPressed(KeyEvent event) {
				if(event.getKeyCode() == KeyEvent.VK_ENTER) {
					try {
						Integer numGiocatori = Integer.parseInt(textField.getText());
						controller.setImpostazioniIniziali(numGiocatori);
						
					} catch (NumberFormatException e2) {
						JOptionPane.showMessageDialog(null, "Inserisci un numero!");
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(null, "Possono giocare a HorseFever dai 2 ai 6 giocatori!");
					}
				}
			}
		});
		textField.setFont(myFont);
		textField.setBounds(259, 191, 101, 88);
		add(textField);
		textField.setColumns(10);
		
		
		JLabel label = new JLabel("");
		label.setIcon(new ImageIcon(ViewChiediNumeroGiocatore.class.getResource("/Altro/bg.jpg")));
		label.setBounds(0, 0, 640, 480);
		add(label);
		setVisible(true);

	}

}
