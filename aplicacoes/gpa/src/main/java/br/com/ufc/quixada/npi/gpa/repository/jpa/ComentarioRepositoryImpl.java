package br.com.ufc.quixada.npi.gpa.repository.jpa;

import javax.inject.Named;

import br.com.ufc.quixada.npi.gpa.model.Comentario;
import br.com.ufc.quixada.npi.gpa.repository.ComentarioRepository;

@Named
public class ComentarioRepositoryImpl extends JpaGenericRepositoryImpl<Comentario> implements ComentarioRepository{

}
