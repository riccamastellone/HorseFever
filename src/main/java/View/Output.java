
package View;

import java.io.Serializable;

import controller.ControllerInterface;
import controller_event.CorsaEvent;
import controller_event.DomandaScommessa2Event;
import controller_event.EmptyEvent;
import controller_event.ErroreScommessa1Event;
import controller_event.ErroreScommessa2Event;
import controller_event.NomeGiocatoreControllerEvent;
import controller_event.RiepilogoTurnoEvent;
import controller_event.Scommessa2Event;
import controller_event.ScommessaEvent;
import controller_event.TruccaCorsaEvent;
import controller_event.VincitoreEvent;


/**
 *
 * @author Sebastiano Mariani
 */
public interface Output extends Serializable {
	
	
	public void setController(ControllerInterface controller);
    
    public void chiediNumeroGiocatori(EmptyEvent e);
    
    public void chiediNomeGiocatore(NomeGiocatoreControllerEvent e);
    
    public void schermataScommessa1(ScommessaEvent e);
    
    public void schermataScommessa2(Scommessa2Event e);
    
    public void warningScommessaMinima1(EmptyEvent e);
    
    public void warningDenariNonDisponibili1(EmptyEvent e);
    
    public void warningSegnaliniNonDisponibili1(EmptyEvent e);
    
    public void warningScommessaMinima2(EmptyEvent e);
    
    public void warningDenariNonDisponibili2(EmptyEvent e);
    
    public void warningSegnaliniNonDisponibili2(EmptyEvent e);
    
    public void schermataTruccaCorsa(TruccaCorsaEvent e);
    
    public void domandaScommessa2(DomandaScommessa2Event e);
    
    public void mostraCorsa(CorsaEvent e);
    
    public void schermataRiepilogoTurno(RiepilogoTurnoEvent e);
		
    public void schermataNessunVincitore(EmptyEvent e);
    
    public void schermataVincitore(VincitoreEvent e);
    
    public void schermataNonPuoiFareScommessa2(ErroreScommessa2Event e);
    
    public void loadingScommessa1(EmptyEvent e);
    
    public void loadingScommessa2(EmptyEvent e);
	
    public void loadingTruccaCorsa(EmptyEvent e);
    
    public void loadingCorsa(EmptyEvent e);
    
    public void loadingChiusuraTurno(EmptyEvent e);
    
    public void schermataAttendi();
    
    public void schermataSaltaScommessa1(ErroreScommessa1Event e);

    
}