package br.ufc.quixada.npi.gpa.repository.jpa;

import javax.inject.Named;

import br.ufc.quixada.npi.gpa.model.Comentario;
import br.ufc.quixada.npi.gpa.repository.ComentarioRepository;

@Named
public class ComentarioRepositoryImpl extends JpaGenericRepositoryImpl<Comentario> implements ComentarioRepository{

}
