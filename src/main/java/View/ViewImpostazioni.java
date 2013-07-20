package View;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;

import model.Config;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class ViewImpostazioni extends JFrame {

	private JPanel contentPane;
	private JTextField textField;
	private JTextField textField_1;


	/**
	 * Create the frame.
	 */
	public ViewImpostazioni() {
		// Evitiamo che chiudere le impostazioni chiuda l'intero gioco
		addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                dispose();
            }
        });
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblIndirizzoServer = new JLabel("Indirizzo Server:");
		lblIndirizzoServer.setBounds(72, 74, 124, 16);
		contentPane.add(lblIndirizzoServer);
		
		JLabel lblPortaServer = new JLabel("Porta server:");
		lblPortaServer.setBounds(72, 136, 100, 16);
		contentPane.add(lblPortaServer);
		
		textField = new JTextField();
		textField.setText("127.0.0.1");
		textField.setBounds(208, 68, 134, 28);
		contentPane.add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setText("4000");
		textField_1.setBounds(208, 130, 134, 28);
		contentPane.add(textField_1);
		textField_1.setColumns(10);
		
		JButton btnImposta = new JButton("Imposta");
		btnImposta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String indirizzo = textField.getText();
				Integer porta = Integer.parseInt(textField_1.getText());
				if ( (!indirizzo.equals("")) && (porta != null) ) {
					setVisible(false);
					Config.indirizzoServer = indirizzo;
					Config.portaServer = porta;
				}
				else {
					JOptionPane.showMessageDialog(null, "Inserisci tutti i campi!");
				}
			}
		});
		btnImposta.setBounds(164, 217, 117, 29);
		contentPane.add(btnImposta);
	}
}
