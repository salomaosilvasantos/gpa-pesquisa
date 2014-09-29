package ufc.quixada.npi.gpa.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.codehaus.jackson.annotate.JsonBackReference;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Comentario implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(columnDefinition="TEXT")	
	private String texto;
	
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date data;
	
	@ManyToOne
	@JoinColumn(name = "pessoa_id")
	@JsonBackReference
	private Pessoa pessoa;
	
	@ManyToOne
	@JoinColumn(name = "projeto_id")
	@JsonBackReference
	private Projeto projeto;

	public Comentario() {
		super();
	}

	public Comentario(Long id, String texto, Date data, Pessoa pessoa,
			Projeto projeto) {
		super();
		this.id = id;
		this.texto = texto;
		this.data = data;
		this.pessoa = pessoa;
		this.projeto = projeto;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
		this.data = data;
	}

	public Pessoa getPessoa() {
		return pessoa;
	}

	public void setPessoa(Pessoa pessoa) {
		this.pessoa = pessoa;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	@Override
	public String toString() {
		return "Comentario [id=" + id + ", texto=" + texto + ", data=" + data
				+ ", pessoa=" + pessoa + ", projeto=" + projeto + "]";
	}
}