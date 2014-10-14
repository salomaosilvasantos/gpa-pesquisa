package ufc.quixada.npi.gpa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import ufc.quixada.npi.gpa.model.Pessoa;
import ufc.quixada.npi.gpa.model.Projeto;
import ufc.quixada.npi.gpa.repository.ProjetoRepository;
import ufc.quixada.npi.gpa.repository.QueryType;
import ufc.quixada.npi.gpa.service.ProjetoService;

@Named
public class ProjetoServiceImpl extends GenericServiceImpl<Projeto> implements
		ProjetoService {

	@Inject
	private ProjetoRepository projetoRepository;

	@Override
	public List<Projeto> getProjetosSubmetidos() {
		return projetoRepository
				.find(QueryType.JPQL,
						"from Projeto as p where p.status = 'SUBMETIDO' or p.status = 'AGUARDANDO_PARECER' or p.status = 'AGUARDANDO_AVALIACAO'",
						null);
	}

	@Override
	public List<Projeto> getProjetosAtribuidos() {
		return projetoRepository.find(QueryType.JPQL,
				"from Projeto as p where p.status = 'AGUARDANDO_PARECER' ",
				null);
	}

	@Override
	public List<Projeto> getProjetosByUsuario(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return projetoRepository.find(QueryType.JPQL,
				"from Projeto where autor.id = :id", params);
	}

	@Override
	public List<Projeto> getProjetosAvaliadosDoUsuario(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return projetoRepository
				.find(QueryType.JPQL,
						"from Projeto as p where ((p.status = 'APROVADO') OR (p.status = 'REPROVADO') OR (p.status = 'APROVADO_COM_RESTRICAO')) AND (autor.id = :id)",
						params);
	}

	@Override
	public List<Projeto> getProjetosAvaliados() {
		return projetoRepository
				.find(QueryType.JPQL,
						"from Projeto as p where (p.status = 'APROVADO') OR (p.status = 'REPROVADO') OR (p.status = 'APROVADO_COM_RESTRICAO')",
						null);
	}

	@Override
	public List<Projeto> getProjetosAguardandoParecer(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return projetoRepository
				.find(QueryType.JPQL,
						"Select p from Projeto as p, Parecer as pa where p.id = pa.projeto.id and pa.usuario.id = :id and p.status = 'AGUARDANDO_PARECER'",
						params);
	}
	
	

}
