package quixada.ufc.br.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import quixada.ufc.br.model.Cargo;
import quixada.ufc.br.repository.CargoRepository;

@Named
public class CargoImplementacao implements CargoService{

	@Inject
	private CargoRepository cargoRepository;

	public CargoImplementacao() {
	}

	@Transactional
	public void salvar(Cargo cargo) {
		cargoRepository.save(cargo);

	}
	
	@Transactional
	public void  atualizar(Cargo cargo) {
		cargoRepository.update(cargo);

	}

	
	@Transactional
	public Cargo findById(int id) {
		return cargoRepository.find(id);
		 
	}
	@Transactional
	public List<Cargo> findAll() {
		List<Cargo> l = cargoRepository.find();
		return l;
	}
	@Transactional
	public void delete(Cargo cargo) {
		cargoRepository.delete(cargo);
		 
	}

	
}
