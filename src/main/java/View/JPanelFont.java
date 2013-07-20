/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package View;

import java.awt.Font;
import java.io.InputStream;
import javax.swing.JPanel;

/**
 *
 * @author Riccardo
 */
public class JPanelFont extends JPanel {
    
    protected Font myFont;

    public JPanelFont() {

        InputStream istream = getClass().getResourceAsStream("/Altro/DenneShuffleEuroHollow.ttf");
        
        try {
            myFont = Font.createFont(Font.TRUETYPE_FONT, istream);
            myFont = myFont.deriveFont(60.0f);
        } catch (Exception e) {
            myFont = new Font("Arial", Font.PLAIN, 40);
        }
    }
}
