package ufc.quixada.npi.gpa.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.inject.Named;

import ufc.quixada.npi.gpa.model.Projeto;
import ufc.quixada.npi.gpa.model.Projeto.StatusProjeto;
import ufc.quixada.npi.gpa.service.ProjetoService;
import br.ufc.quixada.npi.enumeration.QueryType;
import br.ufc.quixada.npi.repository.GenericRepository;

@Named
public class ProjetoServiceImpl implements ProjetoService {

	@Inject
	private GenericRepository<Projeto> projetoRepository;
	
	@Override
	public Map<String, String> cadastrar(Projeto projeto) {
		Map<String, String> resultado = new HashMap<String, String>();
		
		if(!projeto.isDataTerminoFutura()) {
			resultado.put("termino", "Somente data futura");
		}
		
		if(!projeto.isPeriodoValido()) {
			resultado.put("inicio", "A data de início deve ser antes da data de término");
		}
		
		if (resultado.isEmpty()) {
			projeto.setStatus(StatusProjeto.NOVO);
			projetoRepository.save(projeto);

			String codigo = geraCodigoProjeto(projeto.getId());
			projeto.setCodigo(codigo);
			projetoRepository.update(projeto);
		}
		
		return resultado;
	}

	@Override
	public List<Projeto> getProjetosSubmetidos() {
		return projetoRepository.find(QueryType.JPQL, "from Projeto as p where p.status = 'SUBMETIDO' or p.status = 'AGUARDANDO_PARECER' or p.status = 'AGUARDANDO_AVALIACAO'", null);
	}
	
	@Override
	public List<Projeto> getProjetosAtribuidos() {
		return projetoRepository.find(QueryType.JPQL, "from Projeto as p where p.status = 'AGUARDANDO_PARECER' ", null);
	}

	@Override
	public List<Projeto> getProjetosByUsuario(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return projetoRepository.find(QueryType.JPQL, "from Projeto where autor.id = :id", params);
	}
	

	@Override
	public List<Projeto> getProjetosAvaliadosDoUsuario(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return projetoRepository.find(QueryType.JPQL, "from Projeto as p where ((p.status = 'APROVADO') OR (p.status = 'REPROVADO') OR (p.status = 'APROVADO_COM_RESTRICAO')) AND (autor.id = :id)" , params);
	}

	@Override
	public List<Projeto> getProjetosAvaliados() {
		return projetoRepository.find(QueryType.JPQL, "from Projeto as p where (p.status = 'APROVADO') OR (p.status = 'REPROVADO') OR (p.status = 'APROVADO_COM_RESTRICAO')" , null);
	}

	@Override
	public List<Projeto> getProjetosAguardandoParecer(Long id) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("id", id);
		return projetoRepository.find(QueryType.JPQL, "Select p from Projeto as p, Parecer as pa where p.id = pa.projeto.id and pa.usuario.id = :id and p.status = 'AGUARDANDO_PARECER'" , params);
	}

	@Override
	public Projeto getProjetoById(Long id) {
		return projetoRepository.find(Projeto.class, id);
	}
	
	private String geraCodigoProjeto(Long id) {
		if (id < 10) {
			return "PESQ0" + id;
		} else {
			return "PESQ" + id;
		}
	}

}