package it.uniroma3.siw.Silph.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.Silph.model.Project;
import it.uniroma3.siw.Silph.model.User;
import it.uniroma3.siw.Silph.service.AlbumService;
import it.uniroma3.siw.Silph.service.FotografoService;
import it.uniroma3.siw.Silph.service.FotografoValidator;

@Controller
public class ControllerFotografo {

	//Deve vedere i suoi Projects e i suoi Tasks
	//Rifarsi quindi agli html di lista progeti e aggiungerne uno simile per lista task
	@Autowired
	private FotografoService serviceFotografo;
	@Autowired
	private FotografoValidator fotografoValidator;
	
	@Autowired
	private AlbumService albumService;
	

	
	@RequestMapping(value = "/formFotografo", method = RequestMethod.POST )
	public String add(@Valid @ModelAttribute ("fotografo") User f, Model m, BindingResult bindindResult) {
		this.fotografoValidator.validate(f, bindindResult);
		if(!bindindResult.hasErrors()) {
		
		this.serviceFotografo.inserisciFotografo(f);	
		m.addAttribute("fotografo", f);
		return "okFotografo.html";
		}
		else {
			return "registraFotografo.html";
		}

	}
	

	
	@RequestMapping("/linkListaFotografi")
	public String listaFotografi(Model model) {
		model.addAttribute("fotografi", this.serviceFotografo.tuttiFotografi());
		return "listaFotografi";
	}
	
	@RequestMapping(value = "/fotografo/{id}", method = RequestMethod.GET)
	public String getFotografo(@PathVariable ("id") Long id, Model model) {
			model.addAttribute("fotografo", this.serviceFotografo.fotografoPerID(id));
			model.addAttribute("listaAlbum", this.albumService.albumPerAuthorId(id));
	return "Fotografo";
	}
	
	@RequestMapping("/linkCreaAlbum/{id}")
	public String addAlbum(@PathVariable("id") Long id, Model model) {
		User f = this.serviceFotografo.fotografoPerID(id);
		model.addAttribute("fotografo", f);
		model.addAttribute("album", new Project());
	return "creaAlbum";
	}
	
	
}
