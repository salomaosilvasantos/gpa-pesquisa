package ufc.quixada.npi.gpa.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Properties;
import java.util.Vector;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import ufc.quixada.npi.gpa.model.Comentario;
import ufc.quixada.npi.gpa.model.Documento;
import ufc.quixada.npi.gpa.model.Parecer;
import ufc.quixada.npi.gpa.model.Parecer.StatusPosicionamento;
import ufc.quixada.npi.gpa.model.Pessoa;
import ufc.quixada.npi.gpa.model.Projeto;
import ufc.quixada.npi.gpa.model.Projeto.Evento;
import ufc.quixada.npi.gpa.model.Projeto.StatusProjeto;
import ufc.quixada.npi.gpa.service.ComentarioService;
import ufc.quixada.npi.gpa.service.DocumentoService;
import ufc.quixada.npi.gpa.service.ParecerService;
import ufc.quixada.npi.gpa.service.ProjetoService;
import ufc.quixada.npi.gpa.service.UsuarioService;
import ufc.quixada.npi.gpa.service.impl.NotificationService;
import ufc.quixada.npi.gpa.utils.Constants;

@Component
@Controller
@RequestMapping("projeto")
public class ProjetoController {

	@Inject
	private ProjetoService serviceProjeto;

	@Inject
	private UsuarioService serviceUsuario;

	@Autowired
	private ComentarioService comentarioService;
	
	@Inject
	private DocumentoService serviceDocumento;

	@Inject
	private ParecerService serviceParecer;

	@Inject
	private NotificationService notificationService;

	public static Properties getProp() throws IOException {

		Resource resource = new ClassPathResource("/notification.properties");
		return PropertiesLoaderUtils.loadProperties(resource);
	}

	@RequestMapping(value = "/index", method = RequestMethod.GET)
	public String index() {
		return "redirect:/projeto/listar";
	}

	@RequestMapping(value = "/cadastrar", method = RequestMethod.GET)
	public String cadastro(Model model) {
		model.addAttribute("projeto", new Projeto());
		return "projeto/cadastrar";
	}

	@RequestMapping(value = "/cadastrar", method = RequestMethod.POST)
	public String adicionarProjeto(
			@Valid @ModelAttribute("projeto") Projeto projeto,
			BindingResult result, HttpSession session,
			RedirectAttributes redirect) {
		if (result.hasErrors()) {
			return ("projeto/cadastrar");
		}
		
		if (projeto.getTermino() != null
				&& comparaDatas(new Date(), projeto.getTermino()) > 0) {
			result.rejectValue("termino", "error.projeto",
					"Somente data futura");
			return "projeto/cadastrar";
		}
		if (projeto.getTermino() != null && projeto.getInicio() != null
				&& comparaDatas(projeto.getInicio(), projeto.getTermino()) > 0) {
			result.rejectValue("inicio", "error.projeto",
					"A data de início deve ser antes da data de término.");
			return "projeto/cadastrar";
		}

		projeto.setAutor(getUsuarioLogado(session));
		projeto.setStatus(StatusProjeto.NOVO);
		this.serviceProjeto.save(projeto);

		String codigo = geraCodigoProjeto(projeto.getId());
		projeto.setCodigo(codigo);
		this.serviceProjeto.update(projeto);
		redirect.addFlashAttribute("info", "Projeto cadastrado com sucesso.");

		return "redirect:/projeto/listar";

	}

	@RequestMapping(value = "/{id}/detalhes")
	public String getDetalhes(Projeto p, @PathVariable("id") Long id,
			Model model, HttpSession session,
			RedirectAttributes redirectAttributes) {
		Projeto projeto = serviceProjeto.find(Projeto.class, id);
		Pessoa usuario = getUsuarioLogado(session);
		// Verifica se o projeto existe
		if (projeto == null) {
			redirectAttributes
					.addFlashAttribute("erro", "Projeto inexistente.");
			return "redirect:/projeto/listar";
		}
		// Verifica se o usuário logado é o autor do projeto ou se é o diretor e
		// o projeto já foi submetido
		if (usuario.getId() == projeto.getAutor().getId()
				|| (serviceUsuario.isDiretor(usuario) && !projeto.getStatus()
						.equals(StatusProjeto.NOVO))) {
			model.addAttribute("projeto", projeto);
			model.addAttribute("comentario", comentarioService.find(Comentario.class));
			return "projeto/detalhes";
		} else {
			redirectAttributes.addFlashAttribute("erro", "Permissão negada.");
			return "redirect:/projeto/listar";
		}
	}

	@RequestMapping(value = "/{id}/editar", method = RequestMethod.GET)
	public String editar(@PathVariable("id") long id, Model model,
			HttpSession session, RedirectAttributes redirectAttributes) {
		Projeto projeto = serviceProjeto.find(Projeto.class, id);
		Pessoa usuario = getUsuarioLogado(session);
		if (projeto == null) {
			redirectAttributes
					.addFlashAttribute("erro", "Projeto inexistente.");
			return "redirect:/projeto/listar";
		}
		if (usuario.getId() == projeto.getAutor().getId()
				&& !projeto.getStatus()
						.equals(StatusProjeto.AGUARDANDO_PARECER)) {
			model.addAttribute("projeto", projeto);
			model.addAttribute("participantes",
					serviceUsuario.getParticipantes());
			model.addAttribute("action", "editar");

			return "projeto/editar";
		}

		redirectAttributes.addFlashAttribute("erro", "Permissão negada.");
		return "redirect:/projeto/listar";
	}

	@RequestMapping(value = "/{id}/emitirParecer/{parecerId}", method = RequestMethod.GET)
	public String getEmitirParecerPage(@PathVariable("id") long id,
			@PathVariable("parecerId") long parecerId, Model model,
			HttpSession session, RedirectAttributes redirectAttributes) {
		Projeto projeto = serviceProjeto.find(Projeto.class, id);
		Parecer parecer = serviceParecer.find(Parecer.class, parecerId);
		if (projeto == null) {
			redirectAttributes
					.addFlashAttribute("erro", "Projeto Inexistente.");
			return "redirect:/projeto/listar";
		}
		if (!projeto.getStatus().equals(StatusProjeto.AGUARDANDO_PARECER)) {
			redirectAttributes.addFlashAttribute("erro",
					"Projeto não está aguardando parecer");
			return "redirect:/projeto/listar";
		}
		if (getUsuarioLogado(session).getId() != parecer.getUsuario().getId()) {
			redirectAttributes.addFlashAttribute("erro",
					"Parecer não atribuído a você");
			return "redirect:/projeto/listar";
		}
		model.addAttribute("projeto", projeto);
		return "projeto/emitirParecer";
	}

	@RequestMapping(value = "/{id}/emitirParecer/{parecerId}", method = RequestMethod.POST)
	public String emitirParecer(HttpServletRequest request,
			@PathVariable("id") long id,
			@PathVariable("parecerId") long parecerId,
			@RequestParam("file") MultipartFile[] files,
			@RequestParam("parecer") String comentario,
			@RequestParam("statusParecer") String status,
			@ModelAttribute(value = "parecer") Parecer parecer,
			BindingResult result, HttpSession session,
			RedirectAttributes redirectAttributes) throws IOException {

		Projeto projeto = serviceProjeto.find(Projeto.class, id);
		parecer = serviceParecer.find(Parecer.class, parecerId);

		if (result.hasErrors()) {
			return "redirect:/projeto/listar";
		}

		if (comentario.isEmpty()) {
			redirectAttributes.addAttribute("erro",
					"Comentário não pode estar vazio");
			return "redirect:/projeto/" + id + "/emitirParecer" + parecerId;
		}

		for (MultipartFile mpf : files) {
			if (mpf.getBytes().length > 0) {
				Documento documento = new Documento();
				documento.setNomeOriginal(mpf.getOriginalFilename());
				documento.setTipo(mpf.getContentType());
				documento.setProjeto(projeto);
				documento.setArquivo(mpf.getBytes());
				serviceDocumento.save(documento);
				parecer.setDocumento(documento);
			}

		}

		if (status.equals("favorável")) {
			parecer.setStatus(StatusPosicionamento.FAVORAVEL);
		} else {
			parecer.setStatus(StatusPosicionamento.NAO_FAVORAVEL);
		}

		parecer.setComentario(comentario);
		serviceParecer.update(parecer);
		projeto.setStatus(StatusProjeto.AGUARDANDO_AVALIACAO);
		serviceProjeto.update(projeto);

		notificationService.notificar(projeto, Evento.EMISSAO_PARECER);
		
		return "redirect:/projeto/listar";

	}
	
	@RequestMapping(value = "/{id}/verParecer", method = RequestMethod.GET)
	public String verParecer(@PathVariable("id") long id,
			Model model,
			HttpSession session, RedirectAttributes redirectAttributes) {
		
		Projeto projeto = serviceProjeto.find(Projeto.class, id);
		Pessoa usuario = getUsuarioLogado(session);
		// Verifica se o projeto existe
		if (projeto == null) {
			redirectAttributes
					.addFlashAttribute("erro", "Projeto inexistente.");
			return "redirect:/projeto/listar";
		}
		// Verifica se o usuário logado é o autor do projeto ou se é o diretor e
		// o projeto já foi submetido
		if (usuario.getId() == projeto.getAutor().getId()
				|| (serviceUsuario.isDiretor(usuario))){
			model.addAttribute("projeto", projeto);
			return "diretor/verParecer";
		} else {
			redirectAttributes.addFlashAttribute("erro", "Permissão negada.");
			return "redirect:/projeto/listar";
		}
		
	}
	
	@RequestMapping(value = "/{id}/avaliarProjeto", method = RequestMethod.GET)
	public String avaliarProjeto(@PathVariable("id") long id,
			 Model model,HttpSession session,
			RedirectAttributes redirect) {
		
		Projeto projeto = serviceProjeto.find(Projeto.class, id);
		if (projeto == null) {
			redirect
					.addFlashAttribute("erro", "Projeto Inexistente.");
			return "redirect:/projeto/listar";
		}
		
		
		
		if (!projeto.getStatus().equals(StatusProjeto.AGUARDANDO_AVALIACAO)) {
			redirect.addFlashAttribute("erro",
					"Projeto não está aguardando avaliação");
			return "redirect:/projeto/listar";
		}
		if (!(serviceUsuario.isDiretor(getUsuarioLogado(session)))) {
			redirect.addFlashAttribute("erro",
					"Permissão para avaliar projeto negada");
			return "redirect:/projeto/listar";
		}
		model.addAttribute("projeto", projeto);
		return "diretor/avaliarProjeto";
	}
	
	@RequestMapping(value = "/{id}/avaliarProjeto", method = RequestMethod.POST)
	public String avaliarProjeto(HttpServletRequest request,
			@PathVariable("id") long id,
			@RequestParam("file") MultipartFile[] files,
			@RequestParam("observacao") String observacao,
			@RequestParam("opcoesAvaliacao") String status,
			 HttpSession session,
			RedirectAttributes redirect) throws IOException  {
		
		Projeto projeto = serviceProjeto.find(Projeto.class, id);
		
		if (observacao.isEmpty()) {
			redirect.addAttribute("erro",
					"Comentário não pode estar vazio");
			return "redirect:/projeto/" + id + "/avaliarProjeto";
		}
		
		for (MultipartFile mpf : files) {
			if (mpf.getBytes().length > 0) {
				Documento documento = new Documento();
				documento.setNomeOriginal(mpf.getOriginalFilename());
				documento.setTipo(mpf.getContentType());
				documento.setProjeto(projeto);
				documento.setArquivo(mpf.getBytes());
				serviceDocumento.save(documento);
			}

		}
		if (status.equals("Aprovado")) {
			projeto.setStatus(StatusProjeto.APROVADO);
		} else if(status.equals("Aprovado com restrição")) {
			projeto.setStatus(StatusProjeto.APROVADO_COM_RESTRICAO);		
		}else{
			projeto.setStatus(StatusProjeto.REPROVADO);
		}
		
		this.serviceProjeto.save(projeto);

		this.serviceProjeto.update(projeto);
		redirect.addFlashAttribute("info", "Projeto avaliado.");

		return "redirect:/projeto/listar";

	}
	
	
	@RequestMapping(value = "/{id}/editar", method = RequestMethod.POST)
	public String atualizarProjeto(
			@PathVariable("id") Long id,
			@RequestParam("file") MultipartFile[] files,
			@RequestParam(value = "participanteSelecionado", required = false) List<String> listaParticipantes,
			@Valid @ModelAttribute(value = "projeto") Projeto projetoAtualizado,
			BindingResult result, Model model, HttpSession session,
			RedirectAttributes redirect) throws IOException {

		if (result.hasErrors()) {
			model.addAttribute("action", "editar");
			return "projeto/editar";
		}
		if (projetoAtualizado.getTermino() != null
				&& comparaDatas(new Date(), projetoAtualizado.getTermino()) > 0) {
			result.rejectValue("termino", "error.projeto",
					"Somente data futura");
			model.addAttribute("action", "editar");
			return "projeto/editar";
		}
		if (projetoAtualizado.getTermino() != null
				&& projetoAtualizado.getInicio() != null
				&& comparaDatas(projetoAtualizado.getInicio(),
						projetoAtualizado.getTermino()) > 0) {
			result.rejectValue("inicio", "error.projeto",
					"A data de início deve ser antes da data de término.");
			model.addAttribute("action", "editar");
			return "projeto/editar";
		}
		if(listaParticipantes == null){
			redirect.addFlashAttribute("error_participantes",
					"Por favor, selecione ao menos um participante.");
			model.addAttribute("action", "editar");
			return "redirect:/projeto/" + id + "/editar";
		}

		Projeto projeto = serviceProjeto.find(Projeto.class, id);
		List<Pessoa> participantes = new ArrayList<Pessoa>();
		Boolean pessoaJaCadastrada = false;

		// verificar se todas as pessoas que vem do formulario estao no BD
		for (String identificador : listaParticipantes) {

			Pessoa pessoa = serviceUsuario.getPessoaByNome(identificador);

			if (pessoa == null) {
				redirect.addFlashAttribute("error_participantes",
						"A pessoa '"+identificador +"' não se encontra na base de dados");
				model.addAttribute("action", "editar");
				return "redirect:/projeto/" + id + "/editar";

			} else {

				for (Pessoa participante : participantes) {
					if(pessoa.equals(participante)){
						System.out.println("A pessoa "+participante.getNome()+" ja se encontra cadastrada no projeto");
						pessoaJaCadastrada = true;
					}
				}
				if(!pessoaJaCadastrada) {
					
					participantes.add(pessoa);
				}
			}

		}

		for (MultipartFile mpf : files) {
			if (mpf.getBytes().length > 0) {
				Documento documento = new Documento();
				documento.setNomeOriginal(mpf.getOriginalFilename());
				documento.setTipo(mpf.getContentType());
				documento.setProjeto(projeto);
				documento.setArquivo(mpf.getBytes());

				serviceDocumento.save(documento);
			}
		}

		projeto.setNome(projetoAtualizado.getNome());
		projeto.setDescricao(projetoAtualizado.getDescricao());
		projeto.setInicio(projetoAtualizado.getInicio());
		projeto.setTermino(projetoAtualizado.getTermino());
		projeto.setAtividades(projetoAtualizado.getAtividades());
		projeto.setQuantidadeBolsa(projetoAtualizado.getQuantidadeBolsa());
		projeto.setLocal(projetoAtualizado.getLocal());
		if (participantes.size() > 0){
			projeto.setParticipantes(participantes);
		}

		this.serviceProjeto.update(projeto);
		redirect.addFlashAttribute("info", "Projeto atualizado com sucesso.");
		return "redirect:/projeto/listar";
	}

	@RequestMapping(value = "/{id}/excluir")
	public String excluirProjeto(Projeto p, @PathVariable("id") Long id,
			HttpSession session, RedirectAttributes redirectAttributes,
			Model model) {
		Projeto projeto = serviceProjeto.find(Projeto.class, id);
		if (projeto == null) {
			redirectAttributes
					.addFlashAttribute("erro", "Projeto inexistente.");
			return "redirect:/projeto/listar";
		}
		Pessoa usuario = getUsuarioLogado(session);
		if (usuario.getId() == projeto.getAutor().getId()
				&& projeto.getStatus().equals(StatusProjeto.NOVO)) {
			this.serviceProjeto.delete(projeto);
			redirectAttributes.addFlashAttribute("info",
					"Projeto excluído com sucesso.");
		} else {
			redirectAttributes.addFlashAttribute("erro", "Permissão negada.");
		}
		return "redirect:/projeto/listar";

	}

	@RequestMapping(value = "{id}/submeter", method = RequestMethod.GET)
	public String submeterProjetoForm(@PathVariable("id") Long id, Model model,
			HttpSession session, RedirectAttributes redirectAttributes)
			throws IOException {
		Projeto projeto = serviceProjeto.find(Projeto.class, id);
		Pessoa usuario = getUsuarioLogado(session);

		if (projeto == null) {
			redirectAttributes
					.addFlashAttribute("erro", "Projeto inexistente.");
			return "redirect:/projeto/listar";
		}

		if (usuario.getId() == projeto.getAutor().getId()
				&& projeto.getStatus().equals(StatusProjeto.NOVO)) {
			if (validaSubmissao(projeto, model)
					&& validaSubmissaoAnexo(projeto, model)) {

				projeto.setStatus(StatusProjeto.SUBMETIDO);
				
				this.serviceProjeto.update(projeto);
				notificationService.notificar(projeto, Evento.SUBMISSAO);
				redirectAttributes.addFlashAttribute("info",
						"Projeto submetido com sucesso.");
				return "redirect:/projeto/listar";
			} else {
				model.addAttribute("projeto", projeto);
				model.addAttribute("action", "submeter");
				return "projeto/editar";
			}

		} else {
			redirectAttributes.addFlashAttribute("erro", "Permissão negada.");
			return "redirect:/projeto/listar";
		}

	}

	@RequestMapping(value = "submeter", method = RequestMethod.POST)
	public String submeterProjeto(
			@ModelAttribute(value = "projeto") Projeto proj,
			@RequestParam("file") MultipartFile[] files, BindingResult result,
			Model model, HttpSession session,
			RedirectAttributes redirectAttributes) {
		Projeto projeto = serviceProjeto.find(Projeto.class, proj.getId());
		Pessoa usuario = getUsuarioLogado(session);
		if (projeto == null) {
			redirectAttributes
					.addFlashAttribute("erro", "Projeto inexistente.");
			return "redirect:/projeto/listar";
		}

		if (usuario.getId() == projeto.getAutor().getId()
				&& projeto.getStatus().equals(StatusProjeto.NOVO)) {

				projeto.setNome(proj.getNome());
				projeto.setDescricao(proj.getDescricao());
				projeto.setInicio(proj.getInicio());
				projeto.setTermino(proj.getTermino());
				projeto.setAtividades(proj.getAtividades());
				projeto.setQuantidadeBolsa(proj.getQuantidadeBolsa());
				projeto.setLocal(proj.getLocal());
				projeto.setParticipantes(proj.getParticipantes());

				projeto.setStatus(StatusProjeto.SUBMETIDO);
				Date data = new Date(System.currentTimeMillis());
				projeto.setSubmissao(data);

				this.serviceProjeto.update(projeto);

				
				if (validaSubmissao(projeto, model)) {
					
					try {
						for (MultipartFile mpf : files) {
							if (mpf.getBytes().length > 0) {
								Documento documento = new Documento();
								documento.setNomeOriginal(mpf.getOriginalFilename());
								documento.setTipo(mpf.getContentType());
								documento.setProjeto(projeto);
								documento.setArquivo(mpf.getBytes());
								serviceDocumento.save(documento);
							

							} else {
								redirectAttributes.addFlashAttribute(
										"error_documento", "Arquivo obrigatório");
								return "redirect:/projeto/" + projeto.getId()
										+ "/submeter";
							}

						}
					} catch (IOException e) {
						redirectAttributes
								.addFlashAttribute("error_documento",
										"Ocorreu um erro ao processar o arquivo, tente novamente.");
						return "redirect:/projeto/" + projeto.getId() + "/submeter";
					}
						if(validaSubmissaoAnexo(projeto, model)){
							projeto.setStatus(StatusProjeto.SUBMETIDO);
							serviceProjeto.update(projeto);
							notificationService.notificar(projeto, Evento.SUBMISSAO);

							redirectAttributes.addFlashAttribute("info",
									"Projeto submetido com sucesso.");
							return "redirect:/projeto/listar";	
						}else{
							model.addAttribute("action", "submeter");
							return "projeto/editar";
						}
					
						
					} else {
				model.addAttribute("action", "submeter");
				return "projeto/editar";
				}
				
		} else {
			redirectAttributes.addFlashAttribute("erro", "Permissão negada.");
			return "redirect:/projeto/listar";
		}

	}

	@RequestMapping(value = "/listar")
	public String listar(ModelMap modelMap, HttpSession session) {
		modelMap.addAttribute("projetos", serviceProjeto
				.getProjetosByUsuario(getUsuarioLogado(session).getId()));

		modelMap.addAttribute("projetosAguardandoParecer",
				serviceProjeto.getProjetosAguardandoParecer(getUsuarioLogado(
						session).getId()));
		modelMap.addAttribute("projetosAvaliados",
				serviceProjeto.getProjetosAvaliadosDoUsuario(getUsuarioLogado(
						session).getId()));

		if (serviceUsuario.isDiretor(getUsuarioLogado(session))) {
			modelMap.addAttribute("projetosSubmetidos",
					serviceProjeto.getProjetosSubmetidos());

			modelMap.addAttribute("projetosAvaliados",
					serviceProjeto.getProjetosAvaliados());

			return "diretor/listarProjetos";
		}
		return "projeto/listar";

	}

	@RequestMapping(value = "diretor/{projetoId}/atribuirParecerista", method = RequestMethod.GET)
	public String atribuirParecerista(
			@PathVariable("projetoId") Long projetoId, Model model,
			RedirectAttributes redirectAttributes) {

		Projeto projeto = serviceProjeto.find(Projeto.class, projetoId);

		if (projeto == null) {
			redirectAttributes.addFlashAttribute("erro",
					"O projeto não existe!");
			return "redirect:/projeto/listar";
		}
		if (projeto.getStatus() != StatusProjeto.SUBMETIDO) {
			redirectAttributes.addFlashAttribute("erro",
					"Não é possível atribuir um parecerista a este projeto.");
			return "redirect:/projeto/listar";
		}

		model.addAttribute("projetoId", projetoId);
		model.addAttribute("usuarios",
				serviceUsuario.getPareceristas(projeto.getAutor().getId()));
		return "diretor/atribuirParecerista";
	}

	@RequestMapping(value = "diretor/atribuirParecerista", method = RequestMethod.POST)
	public String atribuirPareceristaNoProjeto(HttpServletRequest request,
			Model model, RedirectAttributes redirect) throws IOException {

		Long projetoId = Long.parseLong(request.getParameter("projetoId"));
		Long parecerista = Long.parseLong(request.getParameter("parecerista"));
		String observacao = request.getParameter("observacao");

		Projeto projeto = serviceProjeto.find(Projeto.class, projetoId);
		redirect.addFlashAttribute("parecerista", parecerista);
		redirect.addFlashAttribute("observacao", observacao);
		redirect.addFlashAttribute("projetoId", projetoId);
		redirect.addFlashAttribute("usuarios",
				serviceUsuario.getPareceristas(projeto.getAutor().getId()));

		if (request.getParameter("prazo") == null
				|| request.getParameter("prazo").isEmpty()) {
			redirect.addFlashAttribute("error_prazo", "Campo obrigatório");
			return "redirect:/projeto/diretor/" + projetoId
					+ "/atribuirParecerista";
		}

		SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
		Date prazo = null;
		try {
			prazo = format.parse(request.getParameter("prazo"));
		} catch (ParseException e) {
			redirect.addFlashAttribute("error_prazo", "Data inválida");
			return "redirect:/projeto/diretor/" + projetoId
					+ "/atribuirParecerista";
		}

		if (comparaDatas(prazo, new Date()) < 0) {
			redirect.addFlashAttribute("error_prazo",
					"O prazo não pode ser uma data passada");
			redirect.addFlashAttribute("prazo", request.getParameter("prazo"));
			return "redirect:/projeto/diretor/" + projetoId
					+ "/atribuirParecerista";
		}

		Pessoa usuario = serviceUsuario.find(Pessoa.class, parecerista);
		Parecer parecer = new Parecer();
		parecer.setProjeto(projeto);
		parecer.setUsuario(usuario);
		parecer.setDataAtribuicao(new Date());
		parecer.setComentario(observacao);
		parecer.setPrazo(prazo);

		serviceParecer.save(parecer);
		projeto.setStatus(StatusProjeto.AGUARDANDO_PARECER);
		serviceProjeto.update(projeto);

		notificationService.notificar(projeto, Evento.ATRIBUICAO_PARECERISTA);

		redirect.addFlashAttribute("info",
				"O parecerista foi atribuído ao projeto com sucesso.");

		return "redirect:/projeto/listar";
	}

	private Pessoa getUsuarioLogado(HttpSession session) {
		if (session.getAttribute(Constants.USUARIO_LOGADO) == null) {
			Pessoa usuario = serviceUsuario
					.getUsuarioByLogin(SecurityContextHolder.getContext()
							.getAuthentication().getName());
			session.setAttribute(Constants.USUARIO_LOGADO, usuario);
		}
		return (Pessoa) session.getAttribute(Constants.USUARIO_LOGADO);
	}

	private String geraCodigoProjeto(Long id) {
		if (id < 10) {
			return "PESQ0" + id;
		} else {
			return "PESQ" + id;
		}
	}

	private boolean validaSubmissao(Projeto projeto, Model model) {
		boolean valid = true;

		if (projeto.getNome().isEmpty()) {
			model.addAttribute("error_nome", "Campo obrigatório");
			valid = false;
		}
		if (projeto.getInicio() == null) {
			model.addAttribute("error_inicio", "Campo obrigatório");
			valid = false;
		}
		if (projeto.getTermino() == null) {
			model.addAttribute("error_termino", "Campo obrigatório");
			valid = false;
		} else if (comparaDatas(new Date(), projeto.getTermino()) > 0) {
			model.addAttribute("error_termino", "Somente data futura");
			valid = false;
		}
		if (projeto.getInicio() != null && projeto.getTermino() != null
				&& comparaDatas(projeto.getInicio(), projeto.getTermino()) > 0) {
			model.addAttribute("error_inicio",
					"A data de início deve ser antes da data de término");
			valid = false;
		}
		
		if (projeto.getDescricao().isEmpty()) {
			model.addAttribute("error_descricao", "Campo obrigatório");
			valid = false;
		}
		if (projeto.getAtividades().isEmpty()) {
			model.addAttribute("error_atividades", "Campo obrigatório");
			valid = false;
		}
		if (projeto.getCargaHoraria() == null) {
			model.addAttribute("error_cargaHoraria", "Campo obrigatório");
			valid = false;
		}		
		if (projeto.getQuantidadeBolsa() == null) {
			model.addAttribute("error_quantidadeBolsa", "Campo obrigatório");
			valid = false;
		}
		if (projeto.getLocal().isEmpty()) {
			model.addAttribute("error_local", "Campo obrigatório");
			valid = false;
		}
		/*if (projeto.getParticipantes().isEmpty()) {
			model.addAttribute("error_participantes", "Campo obrigatório");
			valid = false;
		}*/

		return valid;
	}

	private boolean validaSubmissaoAnexo(Projeto projeto, Model model) {
		boolean valid = true;
		if (projeto.getDocumentos().size() == 0) {
			model.addAttribute("error_documento", "Arquivo obrigatório");
			valid = false;
		}

		return valid;
	}

	private int comparaDatas(Date date1, Date date2) {
		Calendar calendar1 = Calendar.getInstance();
		calendar1.setTime(date1);
		Calendar calendar2 = Calendar.getInstance();
		calendar2.setTime(date2);
		if (calendar1.get(Calendar.YEAR) > calendar2.get(Calendar.YEAR)) {
			return 1;
		} else if (calendar1.get(Calendar.YEAR) < calendar2.get(Calendar.YEAR)) {
			return -1;
		} else {
			if (calendar1.get(Calendar.MONTH) > calendar2.get(Calendar.MONTH)) {
				return 1;
			} else if (calendar1.get(Calendar.MONTH) < calendar2
					.get(Calendar.MONTH)) {
				return -1;
			} else {
				if (calendar1.get(Calendar.DAY_OF_MONTH) > calendar2
						.get(Calendar.DAY_OF_MONTH)) {
					return 1;
				} else if (calendar1.get(Calendar.DAY_OF_MONTH) < calendar2
						.get(Calendar.DAY_OF_MONTH)) {
					return -1;
				} else {
					return 0;
				}
			}
		}
	}
}
