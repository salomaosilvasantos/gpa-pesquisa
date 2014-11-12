package ufc.quixada.npi.gpa.model;

import java.io.Serializable;

import javax.persistence.Embeddable;


@Embeddable
public class ProjetoDocumentosId implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private long projeto;
	private long documento;

	public long getProjeto() {
		return projeto;
	}

	public void setProjeto(long projeto) {
		this.projeto = projeto;
	}

	public long getPessoa() {
		return documento;
	}

	public void setDocumento(long documento) {
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

	public ProjetoDocumentosId() {
		super();
	}
}