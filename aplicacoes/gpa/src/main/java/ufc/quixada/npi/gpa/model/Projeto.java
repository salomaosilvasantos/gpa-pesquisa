package ufc.quixada.npi.gpa.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.codehaus.jackson.annotate.JsonManagedReference;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
public class Projeto implements Serializable {
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

	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date avaliacao;

	@Column(columnDefinition="TEXT")
	private Date submissao;

	@Column(columnDefinition = "TEXT")
	@Size(min = 5, message = "Mínimo 5 caracteres")
	private String descricao;

	@ManyToOne
	private Pessoa autor;

	private String atividades;

	//@NotNull(message="Campo obrigatório")
	private Integer cargaHoraria;

	private Integer valorDaBolsa;

	@Min(value = 1, message = "Número de bolsas deve ser maior que 1")
	private Integer quantidadeBolsa;

	private String local;

	@Enumerated(EnumType.STRING)
	private StatusProjeto status;

	@ManyToMany
    @JoinTable(joinColumns = {@JoinColumn(name="projeto_id",referencedColumnName="id")}, inverseJoinColumns = {@JoinColumn(name="pessoa_id", referencedColumnName="id")})
    private List<Pessoa> participantes;
	
	@OneToMany(mappedBy = "projeto", cascade = CascadeType.REMOVE)
	private List<Documento> documentos;

	@OneToMany(mappedBy = "projeto", cascade = CascadeType.REMOVE)
	@JsonManagedReference
	private List<Comentario> comentarios;

	@OneToMany(mappedBy = "projeto")
	private List<Parecer> pareceres;

	public Projeto() {
		super();
	}

	public Projeto(Long id, String codigo, String nome, Date inicio,
			Date termino, Date submissao, String descricao, Pessoa autor,
			String atividades, Integer cargaHoraria, Integer valorDaBolsa,
			Integer quantidadeBolsa, String local, StatusProjeto status,
			List<Pessoa> participantes, List<Documento> documentos,
			List<Comentario> comentarios, List<Parecer> pareceres) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.inicio = inicio;
		this.termino = termino;
		this.submissao = submissao;
		this.descricao = descricao;
		this.autor = autor;
		this.atividades = atividades;
		this.cargaHoraria = cargaHoraria;
		this.valorDaBolsa = valorDaBolsa;
		this.quantidadeBolsa = quantidadeBolsa;
		this.local = local;
		this.status = status;
		this.participantes = participantes;
		this.documentos = documentos;
		this.comentarios = comentarios;
		this.pareceres = pareceres;
	}

	public Date getAvaliacao() {
		return avaliacao;
	}

	public void setAvaliacao(Date avaliacao) {
		this.avaliacao = avaliacao;
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

	public Integer getCargaHoraria() {
		return cargaHoraria;
	}

	public void setCargaHoraria(Integer cargaHoraria) {
		this.cargaHoraria = cargaHoraria;
	}

	public Integer getValorDaBolsa() {
		return valorDaBolsa;
	}

	public void setValorDaBolsa(Integer valorDaBolsa) {
		this.valorDaBolsa = valorDaBolsa;
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

	public List<Pessoa> getParticipantes() {
		return participantes;
	}

	public void setParticipantes(List<Pessoa> participantes) {
		this.participantes = participantes;
	}

	public List<Documento> getDocumentos() {
		return documentos;
	}

	public void setDocumentos(List<Documento> documentos) {
		this.documentos = documentos;
	}

	public Pessoa getAutor() {
		return autor;
	}

	public void setAutor(Pessoa autor) {
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

	public Date getSubmissao() {
		return submissao;
	}

	public void setSubmissao(Date submissao) {
		this.submissao = submissao;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Projeto) {
			Projeto other = (Projeto) obj;
			if (other != null && other.getId() != null && this.id != null
					&& other.getId().equals(this.id)) {
				return true;
			}
		}
		return false;
	}

	public List<Comentario> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<Comentario> comentarios) {
		this.comentarios = comentarios;
	}

	@Override
	public String toString() {
		return "Projeto [id=" + id + ", codigo=" + codigo + ", nome=" + nome
				+ ", inicio=" + inicio + ", termino=" + termino
				+ ", submissao=" + submissao + ", descricao=" + descricao
				+ ", autor=" + autor + ", atividades=" + atividades
				+ ", cargaHoraria=" + cargaHoraria + ", valorDaBolsa="
				+ valorDaBolsa + ", quantidadeBolsa=" + quantidadeBolsa
				+ ", local=" + local + ", status=" + status
				+ ", participantes=" + participantes + ", documentos="
				+ documentos + ", comentarios=" + comentarios + ", pareceres="
				+ pareceres + "]";
	}

	public enum StatusProjeto {

		NOVO("NOVO"), SUBMETIDO("SUBMETIDO"), AGUARDANDO_PARECER("AGUARDANDO PARECER"), 
		AGUARDANDO_AVALIACAO("AGUARDANDO AVALIAÇÃO"), APROVADO("APROVADO"), REPROVADO("REPROVADO"),
		APROVADO_COM_RESTRICAO("APROVADO COM RESTRIÇÃO");
		
		private String descricao;

		private StatusProjeto(String descricao) {
			this.descricao = descricao;
		}

		public String getDescricao() {
			return this.descricao;
		}
	}

	public enum Evento {
		SUBMISSAO, ATRIBUICAO_PARECERISTA, EMISSAO_PARECER, AVALIACAO
	}

}
