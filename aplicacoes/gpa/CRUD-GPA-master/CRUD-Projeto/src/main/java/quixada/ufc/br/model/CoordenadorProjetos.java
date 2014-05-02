package quixada.ufc.br.model;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class CoordenadorProjetos {

	@Id
	private Integer siap;
	@OneToOne
	private Usuario usuario;
	
	public CoordenadorProjetos() {
		super();
	}
	public CoordenadorProjetos(Integer siap, Usuario usuario) {
		super();
		this.siap = siap;
		this.usuario = usuario;
	}
	public Integer getSiap() {
		return siap;
	}
	public void setSiap(Integer siap) {
		this.siap = siap;
	}
	public Usuario getUsuario() {
		return usuario;
	}
	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}
	
}
