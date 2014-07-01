package quixada.ufc.br.repository.jpa;

import java.math.BigInteger;

import javax.inject.Named;
import javax.persistence.Query;

import quixada.ufc.br.model.Projeto;
import quixada.ufc.br.repository.ProjetoRepository;

@Named
public class JpaProjetoRepositoryImpl extends JpaGenericRepositoryImpl<Projeto> implements ProjetoRepository {
	
}