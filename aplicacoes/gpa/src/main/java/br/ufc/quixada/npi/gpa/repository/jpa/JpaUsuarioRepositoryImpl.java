package br.ufc.quixada.npi.gpa.repository.jpa;

import javax.inject.Named;

import br.ufc.quixada.npi.gpa.model.Pessoa;
import br.ufc.quixada.npi.gpa.repository.UsuarioRepository;

@Named
public class JpaUsuarioRepositoryImpl extends JpaGenericRepositoryImpl<Pessoa> implements UsuarioRepository{

}
