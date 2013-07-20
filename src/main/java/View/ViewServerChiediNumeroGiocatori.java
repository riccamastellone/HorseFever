package View;

import it.polimi.ingegneriaDelSoftware2013.horseFever_sebastiano.mariani_riccardo.mastellone.Main;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ViewServerChiediNumeroGiocatori extends JFrame {

	private JPanel contentPane;
	private JTextField textField;

	/**
	 * Create the frame.
	 */
	public ViewServerChiediNumeroGiocatori() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblInserisciIlNumero = new JLabel("Inserisci il numero giocatori");
		lblInserisciIlNumero.setBounds(129, 69, 190, 16);
		contentPane.add(lblInserisciIlNumero);
		
		textField = new JTextField();
		textField.setBounds(158, 112, 134, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		JButton btnAvviaServer = new JButton("Avvia Server");
		btnAvviaServer.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Integer numGiocatori = Integer.parseInt(textField.getText());
				try {
					setVisible(false);
					Main.initServer(numGiocatori);
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnAvviaServer.setBounds(169, 193, 117, 29);
		contentPane.add(btnAvviaServer);
	}
}
