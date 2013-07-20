package View;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewAttendi extends JPanel {

	/**
	 * Create the panel.
	 */
	public ViewAttendi() {
		
		
		setLayout(null);
		
		JLabel lblAttendi = new JLabel("Attendi...");
		lblAttendi.setBounds(321, 205, 160, 16);
		add(lblAttendi);
		
		JButton btnNewButton = new JButton("New button");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.out.println("ciao");
			}
		});
		btnNewButton.setBounds(345, 282, 117, 29);
		add(btnNewButton);

	}
}
