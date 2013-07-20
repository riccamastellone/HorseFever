package controller_event;


import view_event.HorseFeverEvent;

public class VincitoreEvent implements HorseFeverEvent{


	private String vincitore;
	private String nome = "";
	
	public VincitoreEvent(String vincitore){
		
		this.vincitore = vincitore;
	}

	public String getNome() {
		return nome;
	}

	public String getVincitore() {
		return vincitore;
	}


}
