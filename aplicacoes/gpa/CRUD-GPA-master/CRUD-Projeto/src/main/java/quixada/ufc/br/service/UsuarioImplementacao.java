package quixada.ufc.br.service;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;

import org.springframework.transaction.annotation.Transactional;

import quixada.ufc.br.model.Usuario;
import quixada.ufc.br.repository.UsuarioRepository;

@Named
public class UsuarioImplementacao implements UsuarioService{

	@Inject
	private UsuarioRepository usuarioRepository;

	public UsuarioImplementacao() {
	}

	@Transactional
	public void salvar(Usuario u) {
		usuarioRepository.save(u);

	}
	
	@Transactional
	public void  atualizar(Usuario u) {
		usuarioRepository.update(u);

	}

	
	@Transactional
	public Usuario findById(int id) {
		return usuarioRepository.find(id);
		 
	}
	@Transactional
	public List<Usuario> findAll() {
		List<Usuario> l = usuarioRepository.find();
		return l;
	}
	@Transactional
	public void delete(Usuario u) {
		usuarioRepository.delete(u);
		 
	}

	
}
