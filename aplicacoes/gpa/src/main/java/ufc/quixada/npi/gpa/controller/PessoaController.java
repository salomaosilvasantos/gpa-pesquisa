package ufc.quixada.npi.gpa.controller;

import javax.inject.Inject;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ufc.quixada.npi.gpa.model.Pessoa;
import ufc.quixada.npi.gpa.service.UsuarioService;

@Controller("usuario")
public class PessoaController {
	
	@Inject
	private UsuarioService usuarioService;

	@RequestMapping(value = "/{id}/detalhes")
	public String getDetalhes(@PathVariable("id") Long id, Model model, RedirectAttributes redirectAttributes) {
		Pessoa usuario = usuarioService.getUsuarioById(id);
		if (usuario == null) {
			redirectAttributes.addFlashAttribute("erro", "Pessoa inexistente.");
			return "redirect:/projeto/listar";

		} else {
			model.addAttribute("usuario", usuario);
			return "diretor/detalhesParticipante";
		}

	}

}
