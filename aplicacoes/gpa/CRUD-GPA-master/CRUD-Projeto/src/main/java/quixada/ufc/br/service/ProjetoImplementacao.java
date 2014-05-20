package quixada.ufc.br.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import quixada.ufc.br.model.Projeto;
import quixada.ufc.br.repository.ProjetoRepository;

@Named
public class ProjetoImplementacao implements ProjetoService{

	@Inject
	private ProjetoRepository pRepository;

	public ProjetoImplementacao() {
	}

	@Transactional
	public void salvar(Projeto p) {
		pRepository.save(p);

	}
	
	@Transactional
	public void  atualizar(Projeto p) {
		pRepository.update(p);

	}

	
	@Transactional
	public Projeto findById(String id) {
		return pRepository.find(id);
		 
	}
	@Transactional
	public List<Projeto> findAll() {
		List<Projeto> l = pRepository.find();
		return l;
	}
	@Transactional
	public void delete(Projeto p) {
		pRepository.delete(p);
		 
	}

	
}
