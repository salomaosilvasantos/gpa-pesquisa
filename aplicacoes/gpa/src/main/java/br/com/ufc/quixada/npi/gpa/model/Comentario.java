package br.com.ufc.quixada.npi.gpa.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Comentario {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String texto;
	
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date dataComentario;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name = "projeto_id")
	private Projeto projeto;

	public Comentario(){
		super();
	}
	
	public Comentario(Long id, String texto, Date dataComentario,
			Usuario usuario, Projeto projeto) {
		super();
		this.id = id;
		this.texto = texto;
		this.dataComentario = dataComentario;
		this.usuario = usuario;
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

	public Date getDataComentario() {
		return dataComentario;
	}

	public void setDataComentario(Date dataComentario) {
		this.dataComentario = dataComentario;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	@Override
	public String toString() {
		return "Comentario [id=" + id + ", texto=" + texto
				+ ", dataComentario=" + dataComentario + ", usuario=" + usuario
				+ ", projeto=" + projeto + "]";
	}
}
