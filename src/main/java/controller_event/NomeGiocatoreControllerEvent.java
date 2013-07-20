package controller_event;

import view_event.HorseFeverEvent;



public class NomeGiocatoreControllerEvent implements HorseFeverEvent {
	
	private String nome = "";
	private Integer numero;
	
	public NomeGiocatoreControllerEvent(Integer numero) {
		
		
		this.numero = numero;
	}
	

	public String getNome() {
		return nome;
	}


	public Integer getNumero() {
		return numero;
	}



	

}
