
package model;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Classe Mazzo
 * @author Riccardo Mastellone
 */
public class Mazzo implements Serializable {
    
    private ArrayList<Carta> mazzo;
    private Carta temp;
    
    public Mazzo() {
        mazzo = new ArrayList<Carta>();
    }
    

    
    public void addCarta(Carta carta) {
        mazzo.add(carta);
    }
    
    
    /**
     * Mescola il mazzo
     */
    public void mescola() {
    	Collections.shuffle(mazzo);
    }
    
    /*
     * Pesca una carta e la elimina dal mazzo
     */
    public Carta pesca() {
    	try {
    		temp = mazzo.get(0);
            mazzo.remove(0);
        } catch (Exception e) {
     	   System.out.println("Non posso pescare una carta, il mazzo e' vuoto");
        }
        
        return temp;
    }
    
}
