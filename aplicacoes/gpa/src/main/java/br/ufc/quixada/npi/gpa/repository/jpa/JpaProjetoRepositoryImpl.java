package br.ufc.quixada.npi.gpa.repository.jpa;

import javax.inject.Named;

import br.ufc.quixada.npi.gpa.model.Projeto;
import br.ufc.quixada.npi.gpa.repository.ProjetoRepository;

@Named
public class JpaProjetoRepositoryImpl extends JpaGenericRepositoryImpl<Projeto> implements ProjetoRepository {
	
}