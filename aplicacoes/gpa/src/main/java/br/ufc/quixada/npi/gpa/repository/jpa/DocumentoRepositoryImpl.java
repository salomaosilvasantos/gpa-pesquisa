package br.ufc.quixada.npi.gpa.repository.jpa;

import javax.inject.Named;

import br.ufc.quixada.npi.gpa.model.Documento;
import br.ufc.quixada.npi.gpa.repository.DocumentoRepository;

@Named
public class DocumentoRepositoryImpl extends JpaGenericRepositoryImpl<Documento> implements DocumentoRepository{

}
