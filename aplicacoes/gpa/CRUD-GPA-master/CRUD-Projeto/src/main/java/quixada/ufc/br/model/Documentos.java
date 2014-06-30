package quixada.ufc.br.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.hibernate.annotations.Type;

@Entity
public class Documentos {

	@Id
	private String id;
	
	private String nomeOriginal;
	
	private String prefixo;
	
	private String tipo;
	
	@Type(type="org.hibernate.type.BinaryType") 
	private byte[] arquivo;

	@ManyToOne
	@JoinColumn(name = "projeto_id")
	private Projeto projeto;
	
	
	
	public Documentos(){
		super();
	}	
	
	public Documentos(String id, String nomeOriginal, String prefixo,
			String tipo, byte[] arquivo) {
		super();
		this.id = id;
		this.nomeOriginal = nomeOriginal;
		this.prefixo = prefixo;
		this.tipo = tipo;
		this.arquivo = arquivo;	
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
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
