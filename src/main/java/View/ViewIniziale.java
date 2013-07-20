package View;

import it.polimi.ingegneriaDelSoftware2013.horseFever_sebastiano.mariani_riccardo.mastellone.Main;

//import com.apple.eawt.Application;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;

import java.awt.GraphicsEnvironment;
import java.awt.Point;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JRadioButton;

public class ViewIniziale extends JFrame {

	private JPanel contentPane;
	// Settiamo come modalita' di default Grafica in Locale
	private boolean grafica = true;
	private int modalita = 3;


	/**
	 * Create the frame.
	 */
	public ViewIniziale() {
		setResizable(false);
		setIconImage(new ImageIcon(this.getClass().getResource("/Altro/Icona.png")).getImage());
		
		//Application.getApplication().setDockIconImage(new ImageIcon(this.getClass().getResource("/Altro/Icona.png")).getImage());
		
		
		setTitle( "HorseFever launcher" ); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Point center = GraphicsEnvironment.getLocalGraphicsEnvironment().getCenterPoint();
	    int windowWidth = 640;
	    int windowHeight = 480;
	    setBounds(center.x - windowWidth / 2, center.y - windowHeight / 2, windowWidth,
	        windowHeight);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JButton btnNewButton_2 = new JButton("Avvia Server");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Main.schermataServer();
			}
		});
		btnNewButton_2.setBounds(123, 391, 117, 29);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_4 = new JButton("Avvia Gioco");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				setVisible(false);
				Main.inizio(grafica, modalita);
				
			}
		});
		btnNewButton_4.setBounds(265, 391, 135, 29);
		contentPane.add(btnNewButton_4);
		
		JRadioButton rdbtnTestuale = new JRadioButton("Testuale");
		rdbtnTestuale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				grafica = false;
			}
		});
		rdbtnTestuale.setBounds(206, 217, 141, 23);
		contentPane.add(rdbtnTestuale);
		
		JRadioButton rdbtnGrafica = new JRadioButton("Grafica");
		rdbtnGrafica.setSelected(true);
		rdbtnGrafica.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				grafica = true;
			}
		});
		
		rdbtnGrafica.setBounds(360, 217, 141, 23);
		contentPane.add(rdbtnGrafica);
		
		ButtonGroup graficaTesto = new ButtonGroup();
		graficaTesto.add(rdbtnGrafica);
		graficaTesto.add(rdbtnTestuale);
		
		JRadioButton rdbtnLocale = new JRadioButton("Locale");
		rdbtnLocale.setSelected(true);
		rdbtnLocale.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modalita = 3;
			}
		});
		rdbtnLocale.setBounds(206, 305, 141, 23);
		contentPane.add(rdbtnLocale);
		
		JRadioButton rdbtnRete = new JRadioButton("Rete");
		rdbtnRete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				modalita = 2;
			}
		});
		rdbtnRete.setBounds(359, 305, 141, 23);
		contentPane.add(rdbtnRete);
		
		ButtonGroup localeRete = new ButtonGroup();
		localeRete.add(rdbtnLocale);
		localeRete.add(rdbtnRete);
		
		JButton btnNewButton = new JButton("Impostazioni");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Main.schermataImpostazioni();
			}
		});
		btnNewButton.setBounds(430, 391, 117, 29);
		contentPane.add(btnNewButton);
		
		JLabel lblNewLabel = new JLabel("");
		lblNewLabel.setIcon(new ImageIcon(ViewIniziale.class.getResource("/Altro/Home.jpg")));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(0, 0, 634, 451);
		contentPane.add(lblNewLabel);
	}
}
