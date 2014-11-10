package ufc.quixada.npi.gpa.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@IdClass(Projeto_DocumentosId.class)
public class Projeto_Documentos implements Serializable {


	@Id
	@ManyToOne
	@JoinColumn(name = "projeto_id")
	private Projeto projeto;

	@Id
	@ManyToOne
	@JoinColumn(name = "documento_id")
	private Documento documento;

	public Projeto_Documentos() {
		// TODO Auto-generated constructor stub
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	@Override
	public int hashCode() {
		int hash = 3;
		hash = 59 * hash + Objects.hashCode(this.projeto);
		hash = 59 * hash + Objects.hashCode(this.documento);
		return hash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (getClass() != obj.getClass()) {
			return false;
		}
		final Projeto_Documentos other = (Projeto_Documentos) obj;
		if (!Objects.equals(this.projeto, other.projeto)) {
			return false;
		}
		if (!Objects.equals(this.documento, other.documento)) {
			return false;
		}
		return true;
	}
}