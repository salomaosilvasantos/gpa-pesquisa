package ufc.quixada.npi.gpa.service;

import ufc.quixada.npi.gpa.model.Projeto;
import ufc.quixada.npi.gpa.model.Projeto.Evento;

public interface ProjetoObserver {
	
	void notificar(Projeto projeto, Evento evento);

}
