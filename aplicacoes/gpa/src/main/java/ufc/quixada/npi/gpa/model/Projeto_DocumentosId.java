package ufc.quixada.npi.gpa.model;

import java.io.Serializable;

import javax.persistence.Embeddable;


@Embeddable
public class Projeto_DocumentosId implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int projeto;
	private int documento;

	public int getProjeto() {
		return projeto;
	}

	public void setProjeto(int projeto) {
		this.projeto = projeto;
	}

	public int getPessoa() {
		return documento;
	}

	public void setDocumento(int documento) {
		this.documento = documento;
	}

	@Override
	public boolean equals(Object obj) {
		return super.equals(obj); // To change body of generated methods, choose
		// Tools | Templates.
	}

	@Override
	public int hashCode() {
		return super.hashCode(); // To change body of generated methods, choose
		// Tools | Templates.
	}

	public Projeto_DocumentosId() {
		super();
	}
}