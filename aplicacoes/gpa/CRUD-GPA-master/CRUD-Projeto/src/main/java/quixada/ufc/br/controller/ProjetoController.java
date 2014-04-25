package quixada.ufc.br.controller;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;

import quixada.ufc.br.model.Projeto;
import quixada.ufc.br.service.ProjetoService;

@Controller
public class ProjetoController {

	@Inject
	private ProjetoService pc;

	private Logger log = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value = "/home", method = RequestMethod.GET)
	public String index() {
		log.info("controller: projeto - action: index");
		return "index";
	}

	@RequestMapping("/cadastro")
	public ModelAndView cadastro() {
		return new ModelAndView("cadastro");
	}

	@RequestMapping(value = "/projetos/new", method = RequestMethod.POST)
	public String adicionarProjeto(@Valid Projeto projeto,
			BindingResult result, SessionStatus status,
			HttpServletRequest request) {

		String stringData = request.getParameter("termino");
		System.out.println(stringData);
		projeto.setStatus("NOVO");

		System.out.println(projeto.toString());
		log.info("controller: projeto - action: AdicionarProjetos");
		if (result.hasErrors()) {
			return "home";
		} else {
			this.pc.salvar(projeto);
			status.setComplete();
			return "confirmacao";
		}
	}

	@RequestMapping(value = "/listar")
	public ModelAndView listar() {
		ModelAndView modelAndView = new ModelAndView("listar");

		List<Projeto> projeto = pc.findAll();
		modelAndView.addObject("projetos", projeto);

		return modelAndView;
	}

}