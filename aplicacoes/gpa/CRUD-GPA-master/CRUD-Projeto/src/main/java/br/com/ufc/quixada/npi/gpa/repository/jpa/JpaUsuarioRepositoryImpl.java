package br.com.ufc.quixada.npi.gpa.repository.jpa;

import javax.inject.Named;

import br.com.ufc.quixada.npi.gpa.model.Usuario;
import br.com.ufc.quixada.npi.gpa.repository.UsuarioRepository;

@Named
public class JpaUsuarioRepositoryImpl extends JpaGenericRepositoryImpl<Usuario> implements UsuarioRepository{

}
