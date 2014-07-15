package br.com.ufc.quixada.npi.gpa.model;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import br.com.ufc.quixada.npi.gpa.enumerator.StatusProjeto;

@Entity
public class Projeto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String codigo;

	@Size(min = 2, message = "Mínimo 2 caracteres")
	private String nome;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date inicio;

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date termino;

	@Size(min = 5, message = "Mínimo 5 caracteres")
	private String descricao;

	@ManyToOne
	@JoinColumn(name = "usuario_id")
	private Usuario autor;

	private String atividades;

	@Min(value = 1, message = "Número de bolsas deve ser maior que 1")
	private Integer quantidadeBolsa;

	private String local;

	@Enumerated(EnumType.STRING)
	private StatusProjeto status;

	private String participantes;

	@OneToMany(mappedBy = "projeto", cascade = CascadeType.REMOVE, fetch = FetchType.EAGER)
	private List<Documento> documentos;

	@OneToMany(mappedBy = "projeto", fetch = FetchType.EAGER)
	private List<Parecer> pareceres;

	public Projeto() {
		super();
	}

	public Projeto(Long id, String codigo, String nome, Date inicio,
			Date termino, String descricao, String atividades,
			Integer quantidadeBolsa, String local, StatusProjeto status,
			String participantes, Usuario usuarioCriador) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.inicio = inicio;
		this.termino = termino;
		this.descricao = descricao;
		this.atividades = atividades;
		this.quantidadeBolsa = quantidadeBolsa;
		this.local = local;
		this.status = status;
		this.participantes = participantes;
		this.autor = usuarioCriador;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Date getInicio() {
		return inicio;
	}

	public void setInicio(Date inicio) {
		this.inicio = inicio;
	}

	public Date getTermino() {
		return termino;
	}

	public void setTermino(Date termino) {
		this.termino = termino;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public String getAtividades() {
		return atividades;
	}

	public void setAtividades(String atividades) {
		this.atividades = atividades;
	}

	public Integer getQuantidadeBolsa() {
		return quantidadeBolsa;
	}

	public void setQuantidadeBolsa(Integer quantidadeBolsa) {
		this.quantidadeBolsa = quantidadeBolsa;
	}

	public String getLocal() {
		return local;
	}

	public void setLocal(String local) {
		this.local = local;
	}

	public StatusProjeto getStatus() {
		return status;
	}

	public void setStatus(StatusProjeto status) {
		this.status = status;
	}

	public String getParticipantes() {
		return participantes;
	}

	public void setParticipantes(String participantes) {
		this.participantes = participantes;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	public Usuario getAutor() {
		return autor;
	}

	public void setAutor(Usuario autor) {
		this.autor = autor;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getId() {
		return id;
	}

	public List<Parecer> getPareceres() {
		return pareceres;
	}

	public void setPareceres(List<Parecer> pareceres) {
		this.pareceres = pareceres;
	}
	
	@Override
	public boolean equals(Object obj) {
		if(obj instanceof Projeto) {
			Projeto other = (Projeto) obj;
			if (other != null && other.getId() != null && this.id != null && other.getId().equals(this.id)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public String toString() {
		return "Projeto [id=" + id + ", codigo=" + codigo + ", nome=" + nome
				+ ", inicio=" + inicio + ", termino=" + termino
				+ ", descricao=" + descricao + ", atividades=" + atividades
				+ ", quantidadeBolsa=" + quantidadeBolsa + ", local=" + local
				+ ", status=" + status + ", participantes=" + participantes
				+ ", documentos=" + documentos + "]";
	}

}
