package model;


import java.sql.SQLException;


    /**
     * Carte Azione
     * 
     * 3 Categorie
     * 
     * Verdi -> Positive
     *
     * - Override carta Movimento [alla partenza] [nel caso sia ultimo quando viene girata una carta Movimento]
     * - Add to carta Movimento [alla partenza] [in caso di Sprint]x2 [al traguardo]
     * 
     * - PhotoFinish vince anche se carta piu bassa
     * 
     * Rosse -> Negative
     *  
     * - Override carta Movimento [alla partenza] [in caso sia primo quando viene girata una carta Movimento]
     * - Subtract from carta Movimento [alla partenza] [in caso di Sprint]
     * 
     * - Blocco Sprint
     * - PhotoFinish perde anche se carta piu alta
     * - Blocco al traguardo tranne per Sprint
     * 
     * Grigie -> Speciali
     * 
     * - [Add to] [Subtract from] quotazione
     * - Elimina carte [negative] [positive]
     * 
     * 
     * Logica di funzionamento
     * 
     * Quando vengono assegnate le due carte Azione a tutti i giocatori vengon aggiunti degli Hooks in tanti arraylist 
     * quanti sono i 'momenti' in cui si applicano gli effetti.
     * Questi poi vengono parsati in coincidenza di questi momenti applicandone gli effetti
     *
     * 
     * 
     * Tabella DB
     * 
     * Tipo String - Positiva, Negativa, Grigia
     * When String - Quando aggiungere l'hook
     * Element String - Elemento influenzato
     * Action String - Azione che l'elemento subisce
     * Value int - Valore dell'action
     * Condition String - Nel caso alcune condizioni debbano essere rispettate
     * 
     * @author Riccardo Mastellone
     *
     */

public class CartaAzione extends Carta{
    
    private CartaAzione temp;
    
    private String tipo; 
    private String when; 
    private String element; 
    private String action; 
    private int value; 
    private String condition; 
    private String immagine; 
    // Colore della scudera sul quale la carta Azione viene giocata
    private String coloreScuderiaEffetto;
    
    public CartaAzione() {
        this.tabella = "carte_azione";
    }
    
    // ===== SETTER ====== //
    
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    public void setWhen(String when) {
        this.when = when;
    }
    
    public void setElement(String element) {
        this.element = element;
    }
    
    public void setAction(String action) {
        this.action = action;
    }
    
    public void setValue(int value) {
        this.value = value;
    }
    
    public void setCondition(String condition) {
        this.condition = condition;
    }
    
    public void setImmagine(String imm) {
        this.immagine = imm;
    }
    
    public void setScuderiaEffetto(String colore) {
        this.coloreScuderiaEffetto = colore;
    }
    
    // ===== GETTER ====== //
    
    public String getTipo() {
        return this.tipo;
    }
    
    public String getWhen() {
        return this.when;
    }
    
    public String getElement() {
        return this.element;
    }
    
    public int getValue() {
        return this.value;
    }
    
    public String getAction() {
        return this.action;
    }
    
    public String getCondition() {
        return this.condition;
    }
    
    public String getImmagine() {
        return this.immagine;
    }
    
    public String getScuderiaEffetto() {
        return this.coloreScuderiaEffetto;
    }
    
    
    
    
    public void estraiMazzo(Mazzo m){
         try {
            while (rs.next()) {
                temp = new CartaAzione();
                temp.setId(rs.getInt("id"));
                temp.setNome(rs.getString("nome"));
                temp.setDescrizione(rs.getString("descrizione"));
                temp.setTipo(rs.getString("tipo"));
                temp.setWhen(rs.getString("when"));
                temp.setElement(rs.getString("element"));
                temp.setAction(rs.getString("action"));
                temp.setValue(rs.getInt("value"));
                temp.setCondition(rs.getString("condition"));
                temp.setImmagine(rs.getString("immagine"));
                m.addCarta(temp);
            } 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        
    }
    
    
    
}
