package ufc.quixada.npi.gpa.service;

import java.util.Date;
import java.util.List;

import ufc.quixada.npi.gpa.model.Projeto;

public interface ProjetoService extends GenericService<Projeto> {

	  List<Projeto> getProjetosSubmetidos();
	
	  List<Projeto> getProjetosAtribuidos();

	  List<Projeto> getProjetosByUsuario(Long id);
	
	  List<Projeto> getProjetosAvaliadosDoUsuario(Long id);
	  
	  List<Projeto> getProjetosAvaliados();
	  
	  List<Projeto> getProjetosAguardandoParecer(Long id);
	  
	  List<Projeto> getProjetosByDates(Date inicio, Date termino);
	  
	  
}
