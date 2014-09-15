package br.ufc.quixada.npi.gpa.model;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Parecer {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Enumerated(EnumType.STRING)
	private StatusPosicionamento status;
	
	@Lob
	private String comentario;
	
	private String posicionamento;
	
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date dataAtribuicao;
	
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date dataRealizacao;
	
	@DateTimeFormat(pattern = "dd/mm/yyyy")
	private Date prazo;
	
	@ManyToOne
	@JoinColumn(name="documento_id")
	private Documento documento;
	
	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Pessoa usuario;
	
	@ManyToOne
	@JoinColumn(name = "projeto_id")
	private Projeto projeto;

	public Parecer(){
		super();
	}

	public Parecer(Long id, StatusPosicionamento status, String comentario,
			String posicionamento, Date dataAtribuicao, Date dataRealizacao,
			Date prazo, Documento documento, Pessoa usuario, Projeto projeto) {
		super();
		this.id = id;
		this.status = status;
		this.comentario = comentario;
		this.posicionamento = posicionamento;
		this.dataAtribuicao = dataAtribuicao;
		this.dataRealizacao = dataRealizacao;
		this.prazo = prazo;
		this.documento = documento;
		this.usuario = usuario;
		this.projeto = projeto;
	}


	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public StatusPosicionamento getStatus() {
		return status;
	}

	public void setStatus(StatusPosicionamento status) {
		this.status = status;
	}

	public String getComentario() {
		return comentario;
	}

	public Documento getDocumento() {
		return documento;
	}

	public void setDocumento(Documento documento) {
		this.documento = documento;
	}

	public void setComentario(String comentario) {
		this.comentario = comentario;
	}

	public Date getDataAtribuicao() {
		return dataAtribuicao;
	}

	public void setDataAtribuicao(Date dataAtribuicao) {
		this.dataAtribuicao = dataAtribuicao;
	}

	public Date getDataRealizacao() {
		return dataRealizacao;
	}

	public void setDataRealizacao(Date dataRealizacao) {
		this.dataRealizacao = dataRealizacao;
	}

	public Date getPrazo() {
		return prazo;
	}

	public void setPrazo(Date prazo) {
		this.prazo = prazo;
	}

	public Pessoa getUsuario() {
		return usuario;
	}

	public void setUsuario(Pessoa usuario) {
		this.usuario = usuario;
	}

	public Projeto getProjeto() {
		return projeto;
	}

	public void setProjeto(Projeto projeto) {
		this.projeto = projeto;
	}

	public String getPosicionamento() {
		return posicionamento;
	}

	public void setPosicionamento(String posicionamento) {
		this.posicionamento = posicionamento;
	}


	@Override
	public String toString() {
		return "Parecer [id=" + id + ", status=" + status + ", comentario="
				+ comentario + ", posicionamento=" + posicionamento
				+ ", dataAtribuicao=" + dataAtribuicao + ", dataRealizacao="
				+ dataRealizacao + ", prazo=" + prazo + ", documento="
				+ documento + ", usuario=" + usuario + ", projeto=" + projeto
				+ "]";
	}


	public enum StatusPosicionamento {
		FAVORAVEL , NAO_FAVORAVEL;
	}
}


