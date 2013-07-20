package model;

import java.io.Serializable;
import java.sql.*;

import org.sqlite.SQLiteConfig;
import org.sqlite.SQLiteDataSource;



/**
 * Classe astratta Carta
 * 
 * Implementa i metodi comuni a tutti i tipi di carta
 * 
 * @author Riccardo Mastellone
 */
abstract public class Carta implements Serializable {

	private int id;
	private String nome;
	private String descrizione;
	private Connection conn;
	private Statement stmt;
	protected ResultSet rs;
	protected String tabella;

	protected void setId(int id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	protected void setNome(String n) {
		this.nome = n;
	}

	public String getDescrizione() {
		return descrizione;
	}

	protected void setDescrizione(String s) {
		this.descrizione = s;
	}

	/* Gestione Database */

	/**
	 * Carica il driver per la connessione a SQLlite e si connette
	 * 
	 * @throws ClassNotFoundException
	 * @throws SQLException
	 */
	private void startDatabase() throws SQLException {

		try {
			Class.forName("org.sqlite.JDBC");
		    SQLiteConfig config = new SQLiteConfig();
		    config.enableFullSync(true);
		    config.setReadOnly(false);
		    SQLiteDataSource ds = new SQLiteDataSource(config);
		    ds.setUrl("jdbc:sqlite::resource:"+
		              this.getClass().getResource("/Altro/database_carte.db").toString());
		    conn = ds.getConnection();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	/**
	 * loadCarte Esegue la query per le caricare le carte e passa il risultato a
	 * estraiMazzo che le inserisce in Mazzo m
	 * 
	 * @param m Mazzo in cui inserire le carte
	 * @throws SQLException
	 */
	public void loadCarte(Mazzo m) {
		try {
			startDatabase();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(String.format("SELECT * from %s", tabella));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		estraiMazzo(m);
	}

	/**
	 * Printa i campi comuni a tutte le carte
	 */
	protected void showCommonCarta() {
		System.out.println("id: " + id);
		System.out.println("Nome: " + nome);
		System.out.println("Descrizione: " + descrizione);
	}

	/* Metodi astratti da implementare nelle sotto classi */

	abstract public void estraiMazzo(Mazzo m);

}
