package view_event;



public class NomeGiocatoreEvent implements HorseFeverEvent{


	
	private String nome;

	
	public NomeGiocatoreEvent(String nome){
		
		
		this.nome = nome;

		
	}

	public String getNome() {
		return nome;
	}



 }
