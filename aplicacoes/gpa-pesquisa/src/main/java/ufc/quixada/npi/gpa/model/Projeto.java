package ufc.quixada.npi.gpa.model;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "projeto" , uniqueConstraints = @UniqueConstraint(columnNames = { "id" }))
public class Projeto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private String codigo;
	
	@Size(min = 2, message = "MínimoW 2 caracteres")
	private String nome;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date inicio;
	
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private Date termino;
	
	@Size(min = 5, message = "Mínimo 5 caracteres")
	private String descricao;
	
	@ManyToOne
	private Pessoa autor;
	
	private String atividades;
	
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
	private List<Comentario> comentarios;
	
	@OneToMany(mappedBy = "projeto")
	private List<Parecer> pareceres;

	public Projeto() {
		super();
	}

	public Projeto(Long id, String codigo, String nome, Date inicio,
			Date termino, String descricao, Pessoa autor, String atividades,
			Integer quantidadeBolsa, String local, StatusProjeto status,
			List<Pessoa> participantes, List<Documento> documentos,
			List<Comentario> comentarios, List<Parecer> pareceres) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
		this.inicio = inicio;
		this.termino = termino;
		this.descricao = descricao;
		this.autor = autor;
		this.atividades = atividades;
		this.quantidadeBolsa = quantidadeBolsa;
		this.local = local;
		this.status = status;
		this.participantes = participantes;
		this.documentos = documentos;
		this.comentarios = comentarios;
		this.pareceres = pareceres;
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
				+ ", descricao=" + descricao + ", autor=" + autor
				+ ", atividades=" + atividades + ", quantidadeBolsa="
				+ quantidadeBolsa + ", local=" + local + ", status=" + status
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

}