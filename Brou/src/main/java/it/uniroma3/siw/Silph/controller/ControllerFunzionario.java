package it.uniroma3.siw.Silph.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.Silph.model.Task;
import it.uniroma3.siw.Silph.model.User;
import it.uniroma3.siw.Silph.service.FotoService;
import it.uniroma3.siw.Silph.service.FotografoService;
import it.uniroma3.siw.Silph.service.CredentialsService;

@Controller
public class ControllerFunzionario {
	@Autowired
	private FotoService fotoService;
	
	@Autowired
	private CredentialsService credentialsService;

	@Autowired
	private FotografoService fotografoService;
	
	@RequestMapping("/linkRichieste")
	public String linkRichieste(Model model) {
		model.addAttribute("richieste", this.credentialsService.tutteRichieste());
		return "listaRichieste";
	}

	@RequestMapping("/funzionario")
	public String indexFunzionario() {
		return "indexFunzionario";
	}

	@RequestMapping("/linkinserisciFotografo")
	public String UploadPage(Model model) {
		model.addAttribute("fotografo", new User());
		return "registraFotografo";
	}

	@RequestMapping("/logout")
	public String esci(Model model) {
		List<Task> task = this.fotoService.ultimi15();
		model.addAttribute("fotos", task);
		return "home";
	}

	@RequestMapping("/Home")
	public String home(Model m) {
		return "indexFunzionario";
	}

	@RequestMapping(value = "/deleteFotografo/{id}", method = RequestMethod.POST)
	public String eliminaFotografo(@PathVariable("id") Long id, Model model) {
		if (id != null) {
			this.fotografoService.eliminaFotografoPerID(id);
			model.addAttribute("fotografi", this.fotografoService.tuttiFotografi());
			return "listaFotografi";
		} else {
			return "okAlbum";
		}
	}

}
