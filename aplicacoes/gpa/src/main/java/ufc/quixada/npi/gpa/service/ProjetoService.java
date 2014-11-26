package ufc.quixada.npi.gpa.service;

import java.util.List;
import java.util.Map;

import ufc.quixada.npi.gpa.model.Projeto;

public interface ProjetoService {
	
	Map<String, String> cadastrar(Projeto projeto);
	
	Projeto getProjetoById(Long id);

	List<Projeto> getProjetosSubmetidos();

	List<Projeto> getProjetosAtribuidos();

	List<Projeto> getProjetosByUsuario(Long id);

	List<Projeto> getProjetosAvaliadosDoUsuario(Long id);

	List<Projeto> getProjetosAvaliados();

	List<Projeto> getProjetosAguardandoParecer(Long id);

}
