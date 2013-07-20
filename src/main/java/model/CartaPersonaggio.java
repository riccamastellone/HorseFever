package model;

import java.sql.*;


/**
 * Carta Personaggio
 * @author Riccardo Mastellone
 */
public class CartaPersonaggio extends Carta {
    
    private int scuderia;
    protected int denari;
    private String immagine = "";
    
    private CartaPersonaggio temp;
    
    public CartaPersonaggio() {
        
        // Definiamo il nome della tabella
        this.tabella = "carte_personaggio";
    }
    
    // ===== GETTER ====== //
    
    public int getScuderia() {
        return scuderia;
    }
    
    public String getImmagine() {
        return this.immagine;
    }
    
    public int getDenari() {
        return this.denari;
    }
    
	// ===== SETTER ====== //
	
	public void setImmagine(String imm) {
        this.immagine = imm;
    }
	
	
    public void setScuderia(int s) {
        this.scuderia = s;
    }
    
    public void setDenari(int d) {
        this.denari = d;
    }
    

    /**
     * Implementazione dell'estrazione vera e propria del mazzo dal risultato SQL
     * @throws SQLException 
     */
    public void estraiMazzo(Mazzo m){
        try {
            while (rs.next()) {
                temp = new CartaPersonaggio();
                temp.setId(rs.getInt("id"));
                temp.setNome(rs.getString("nome"));
                temp.setDescrizione(rs.getString("descrizione"));
                temp.setDenari(rs.getInt("denari"));
                temp.setScuderia(rs.getInt("quotazione"));
				temp.setImmagine(rs.getString("immagine"));
                m.addCarta(temp);
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
   }

    
    
}
