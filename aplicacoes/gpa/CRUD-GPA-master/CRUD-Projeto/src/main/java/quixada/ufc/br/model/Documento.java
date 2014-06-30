package quixada.ufc.br.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Documento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	private String nomeOriginal;
	
	private String prefixo;
	
	private String tipo;
	
	@Type(type="org.hibernate.type.BinaryType") 
	private byte[] arquivo;

	@ManyToOne
	@JoinColumn(name = "projeto_id")
	private Projeto projeto;
	
	public Documento(){
		super();
	}	
	
	public Documento(String nomeOriginal, String tipo, byte[] arquivo, Projeto projeto){
		super();
		this.nomeOriginal = nomeOriginal;
		this.tipo = tipo;
		this.arquivo = arquivo;
		this.projeto = projeto;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNomeOriginal() {
		return nomeOriginal;
	}

	public void setNomeOriginal(String nomeOriginal) {
		this.nomeOriginal = nomeOriginal;
	}

	public String getPrefixo() {
		return prefixo;
	}

	public void setPrefixo(String prefixo) {
		this.prefixo = prefixo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public byte[] getArquivo() {
		return arquivo;
	}

	public void setArquivo(byte[] arquivo) {
		this.arquivo = arquivo;
	}
	
	
	
}

