package quixada.ufc.br.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import quixada.ufc.br.model.CoordenadorProjetos;
import quixada.ufc.br.repository.CoordenadorProjetosRepository;

@Named
public class CoordenadorImplementacao implements CoordenadorProjetosService{

	@Inject
	private CoordenadorProjetosRepository cRepository;

	public CoordenadorImplementacao() {
	}

	@Transactional
	public void salvar(CoordenadorProjetos c) {
		cRepository.save(c);

	}
	
	@Transactional
	public void  atualizar(CoordenadorProjetos c) {
		cRepository.update(c);

	}

	
	@Transactional
	public CoordenadorProjetos findById(int siap) {
		return cRepository.find(siap);
		 
	}
	@Transactional
	public List<CoordenadorProjetos> findAll() {
		List<CoordenadorProjetos> l = cRepository.find();
		return l;
	}
	@Transactional
	public void delete(CoordenadorProjetos coordenador) {
		cRepository.delete(coordenador);
		 
	}

	
}
