package model;

import java.io.Serializable;



public class Giocatore implements Serializable {

	private String nome;
	private int pv;
	private int denari;
	private Scommessa primaScommessa;
	private Scommessa secondaScommessa;
	private CartaPersonaggio cartaPersonaggio;
	private CartaAzione cartaAzione1;
	private CartaAzione cartaAzione2;
	private CartaAzione cartaAzioneGiocata;
	private boolean perso = false;
	private int importoVinto = 0;
	private boolean perditaPv = false;
	private int vincitaScuderia = 0;
	private int vincitaPv = 0;

        // Una volta assegnata la carta Personaggio metto il colore della Scuderia corrispondente
        private String coloreScuderiaProprietario;
	
	/**
	 * Costruttore che istanzia un giocatore
	 * 
	 * @param nome Nome del giocatore
	 * @param cartaP Riferimento alla carta Personaggio assegnata al giocatore
	 * @param cartaA1 Riferimento alla prima carta Azione assegnata al giocatore
	 * @param cartaA2 Riferimento alla seconda carta Azione assegnata al giocatore
	 */
	public Giocatore(String nome, CartaPersonaggio cartaP, CartaAzione cartaA1, CartaAzione cartaA2){
		
		this.nome = nome;
		// Sempre 1 di default [vedi regole]
		this.pv = 1; 
		// Assegno i denari della carta Personaggio
		this.denari = cartaP.denari;

		// Mantengo il riferimento alla carta Personaggio
		this.cartaPersonaggio = cartaP;
                
        // Guardo quale scuderia ha quella quotazione e metto il colore nell'attributo
        ModelInterface model = Model.getInstance();
        this.coloreScuderiaProprietario = model.getScuderiaByQuotazione(cartaP.getScuderia()).getColore();
		
		this.cartaAzione1 = cartaA1;
		this.cartaAzione2 = cartaA2;
		
		
	}

	
	// ===== GETTER ====== //
	

	public int getVincitaPv() {
		return vincitaPv;
	}

	
	public String getNome() {
		return nome;
	}
	
	public int getPv() {
		return pv;
	}
	
	public int getDenari() {
		return denari;
	}
	
	public CartaPersonaggio getCartaPersonaggio() {
		return cartaPersonaggio;
	}
        
    public CartaAzione getCartaAzione1() {
		return cartaAzione1;
	}
        
    public CartaAzione getCartaAzione2() {
		return cartaAzione2;
	}
    
    public Scommessa getPrimaScommessa() {
		return primaScommessa;
	}


	public Scommessa getSecondaScommessa() {
		return secondaScommessa;
	}
	
	public int getVincitaScuderia() {
		return vincitaScuderia;
	}

        
        public String getColoreScuderiaProprietario() {
            return coloreScuderiaProprietario;
        }

	
    	public int getImportoVinto() {
    		return importoVinto;
    	}
    	

    	public CartaAzione getCartaAzioneGiocata() {
    		return cartaAzioneGiocata;
    	}

	
	// ===== SETTER ====== //


    	public void setPerso(boolean perso) {
    		this.perso = perso;
    	}

        
        public void setImportoVinto(int importoVinto) {
    		this.importoVinto = importoVinto;
    	}


    public void setVincitaScuderia(int vincitaScuderia) {
    	this.vincitaScuderia = vincitaScuderia;
    }

    public void setPerditaPv(boolean perditaPv) {
		this.perditaPv = perditaPv;
	}

    public void setVincitaPv(int vincitaPv) {
    	this.vincitaPv = vincitaPv;
    }

	
	public void setNome(String nome) {
		this.nome = nome;
	}

	public void setPv(int pv) {
		this.pv = pv;
	}


	public void setDenari(int denari) {
		this.denari = denari;
	}
	
	public void setCartaAzione1(CartaAzione c1) {
		this.cartaAzione1 = c1;
	}
	
	public void setCartaAzione2(CartaAzione c2) {
		this.cartaAzione2 = c2;
	}
        

	public void setCartaAzioneGiocata(CartaAzione cartaAzioneGiocata) {
		this.cartaAzioneGiocata = cartaAzioneGiocata;
	}

	
	/**
	 * 
	 * istanzio la prima scommessa chiedendo la scuderia il valore della scommessa e il tipo (vincente/piazzato)
	 * @param valore
	 * @param scuderiaScommessa
	 * @param tipoScommessa
	 */
	public void setPrimaScommessa(int valore, Scuderia scuderiaScommessa, String tipoScommessa){
		
		primaScommessa = new Scommessa(valore, scuderiaScommessa, tipoScommessa);
		setDenari(getDenari() - valore);
		
		
	}
	
	public void setSecondaScommessa(int valore, Scuderia scuderiaScommessa, String tipoScommessa){
		
		secondaScommessa = new Scommessa(valore, scuderiaScommessa, tipoScommessa);
		setDenari(getDenari() - valore);
		
		
	}
	
	
	/**
	 * 
	 * check scommessa minima 
	 * ritorna true se e' possibile fare la scommessa
	 * @return boolean
	 */
	public boolean checkScomessaMinima(int valore){
		
		if ( valore < (pv*100) ){
			return false;
		}
		return true;
	}
	/**
	 * 
	 * 
	 * controllo che il giocatore non abbia scommesso piu denari di quelli in suo possesso 
	 * ritorna true se e possibile scommettere
	 * @param valore
	 * @return
	 */
	public boolean checkDenariDisponibiliPerScommessa(int valore) {
		
		if ( valore > denari ){
			return false;
		}
		return true;
		
	}
	
	/**
	 * da usare quando si deve reinizializzare le scommesse a fine turno
	 */
	public void clearSecondaScommessa() {
		this.secondaScommessa = null;
	}





	public boolean isPerso() {
		return perso;
	}




	

	public boolean isPerditaPv() {
		return perditaPv;
	}


	


	


	
}
