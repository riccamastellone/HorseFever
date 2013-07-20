package model;


import java.sql.SQLException;
import java.util.ArrayList;


/**
 * 
 * @author Riccardo Mastellone
 */
public class CartaMovimento extends Carta {

	private ArrayList<Integer> spostamenti;
	private String immagine; 
	private CartaMovimento temp;

	public CartaMovimento() {
		this.tabella = "carte_movimento";
		this.spostamenti = new ArrayList<Integer>();
	}
	
	// ===== SETTER ====== //
	
	public void setImmagine(String imm) {
        this.immagine = imm;
    }
	
	
	// ===== GETTER ====== //
	
	
	public String getImmagine() {
        return this.immagine;
    }
	
	public ArrayList<Integer> getSpostamenti() {
		return spostamenti;
	}




	public void estraiMazzo(Mazzo m) {
		try {
			while (rs.next()) {
				temp = new CartaMovimento();
				temp.setId(rs.getInt("id"));
				temp.setNome(rs.getString("nome"));
				temp.setDescrizione(rs.getString("descrizione"));
				temp.setImmagine(rs.getString("immagine"));
				for(int i=2 ; i <= Config.colori.length + 1 ; i++) {
					temp.addSpostamenti((Integer)rs.getInt("spostamento_" + i));
				}
				m.addCarta(temp);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}




	





	public void addSpostamenti(Integer spostamento) {
		spostamenti.add(spostamento);
	}


}
