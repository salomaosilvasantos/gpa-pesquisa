package quixada.ufc.br.controller;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import quixada.ufc.br.model.Documentos;
import quixada.ufc.br.model.Projeto;
import quixada.ufc.br.service.GenericService;

@Controller
public class ProjetoController {

	@Inject
	private GenericService<Projeto> serviceProjeto;
	
	private static int contador = 0;

	private Logger log = LoggerFactory.getLogger(this.getClass());
	
	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		log.info("controller: projeto - action: index");
		return "index";
	}

	@RequestMapping(value = "/cadastro", method = RequestMethod.GET)
	public String cadastro(Model model) {
		model.addAttribute("projeto", new Projeto());
		return "cadastro";
	}

	@RequestMapping(value = "/novoProjeto", method = RequestMethod.POST)
	public String adicionarProjeto(
			@Valid @ModelAttribute("projeto") Projeto projeto,
			BindingResult result) {
		contador = contador + 1;
		String id = stringFormatada(contador);
		projeto.setId(id);
		String resultado = projeto.getNome().trim();
		if (result.hasErrors() || resultado.isEmpty()) {
			return ("cadastro");
		}
		projeto.setStatus("NOVO");
		this.serviceProjeto.save(projeto);
		return "redirect:/listar";

	}

	@RequestMapping(value = "/{id}/informacoesProjeto")
	public String informacoes(Projeto p, @PathVariable("id") String id,
			Model model) {
		Projeto projeto = serviceProjeto.find(Projeto.class, id);
		model.addAttribute("projeto", projeto);
		return "informacoes";
	}

	@RequestMapping(value = "/{id}/editarProjeto")
	public String editar(Projeto p, @PathVariable("id") String id, Model model) {
		Projeto projeto = serviceProjeto.find(Projeto.class, id);
		System.out.println("Projeto do Banco antes de atualizar: "
				+ projeto.toString());
		model.addAttribute("projeto", projeto);
		return "editar";
	}

	@RequestMapping(value = "/{id}/editarProjetoForm", method = RequestMethod.POST)
	public String atualizarProjeto(@PathVariable("id") String id,
			@ModelAttribute(value = "projeto") Projeto projetoAtualizado,
			BindingResult result) {
		projetoAtualizado.setStatus("NOVO");
		this.serviceProjeto.update(projetoAtualizado);
		System.out.println("Projeto do Banco DEPOIS de atualizar: "
				+ projetoAtualizado.toString());
		return "redirect:/listar";
	}

	@RequestMapping(value = "/{id}/excluirProjeto")
	public String excluirProjeto(Projeto p, @PathVariable("id") String id,
			Model model) {
		Projeto projeto = serviceProjeto.find(Projeto.class, id);
		if (projeto == null) {
			return "redirect:/listar";
		} else {
			this.serviceProjeto.delete(projeto);
			return "redirect:/listar";
		}
	}

	@RequestMapping(value = "{id}/submeterProjeto")
	public String submeterProjeto(@PathVariable("id") String id) {
		Projeto projeto = serviceProjeto.find(Projeto.class, id);
		if (validaSubmissao(projeto)) {
			projeto.setStatus("SUBMETIDO");
			this.serviceProjeto.update(projeto);
			System.out.println(projeto);
			return "redirect:/listar";
		} else {
			return "redirect:/" + id + "/editarProjeto";
		}

	}

	@RequestMapping(value = "/listar")
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("listar");
		List<Projeto> projeto = serviceProjeto.find(Projeto.class);
		modelAndView.addObject("projetos", projeto);
		return modelAndView;
	}

	@RequestMapping(value = "upload-doc", method = RequestMethod.POST)
	public String handleFileUpload(@RequestParam("idNo") String idNo,
			@RequestParam("file") MultipartFile file, Model model) {
		
		if (!file.isEmpty()) {
			try {
			
				
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
		
		return idNo;

	}

	private boolean validaSubmissao(Projeto projeto) {

		System.out.println("PROJETO:");
		if (!projeto.getNome().isEmpty() && !projeto.getLocal().isEmpty()
				&& !projeto.getParticipantes().isEmpty()
				&& projeto.getNumero_bolsas().intValue() > 0
				&& !projeto.getAtividades().isEmpty()
				&& !projeto.getDescricao().isEmpty()) {
			return true;
		} else {
			return false;
		}
	}
	

	private String stringFormatada(int contador){
		if(contador < 10){
			String id = "PESQ0"+ contador;
			return id;
		}else{
			String id = "PESQ"+ contador;
			return id;
		}
		
	}

}
