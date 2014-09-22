package ufc.quixada.npi.gpa.service;

import java.util.List;

import ufc.quixada.npi.gpa.model.Projeto;
import ufc.quixada.npi.gpa.model.Projeto.Evento;

public class NotificationService {
	
	private List<ProjetoObserver> observadores;

	public void notificar(Projeto projeto, Evento evento) {
		for(ProjetoObserver observer : observadores) {
			observer.notificar(projeto, evento);
		}
	}

	public void setObservadores(List<ProjetoObserver> observadores) {
		this.observadores = observadores;
	}

}
