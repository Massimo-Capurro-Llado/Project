package it.uniroma3.siw.Silph.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import it.uniroma3.siw.Silph.model.Project;
import it.uniroma3.siw.Silph.model.Task;
import it.uniroma3.siw.Silph.model.User;
import it.uniroma3.siw.Silph.service.AlbumService;
import it.uniroma3.siw.Silph.service.FotoService;
import it.uniroma3.siw.Silph.service.FotografoService;

@Controller
public class ControllerAlbum {

	@Autowired
	private FotografoService fotografoService;

	@Autowired
	private AlbumService albumService;

	@Autowired
	private FotoService fotoService;

	@RequestMapping(value = "/fotografo/{id}/album", method = RequestMethod.POST)
	public String add(@PathVariable("id") Long id, Model model, Project project) {
		User f = this.fotografoService.fotografoPerID(id);
		project.setFotografo(f);
		this.albumService.inserisci(project);
		return "okAlbum";
	}

	@RequestMapping(value = "/album/{id}", method = RequestMethod.GET)
	public String getFotografo(@PathVariable("id") Long id, Model model) {
		Project a = this.albumService.albumPerID(id);
		model.addAttribute("album", a);
		model.addAttribute("fotos", a.getFoto());
		return "album";
	}

	@RequestMapping(value = "/album/{id}/carica", method = RequestMethod.GET)
	public String formCarica(@PathVariable("id") Long id, Model model) {
		model.addAttribute("album", this.albumService.albumPerID(id));
		return "carica";
	}

	@RequestMapping(value = "/eliminaAlbum/{id}", method = RequestMethod.POST)
	public String eliminaAlbum(Model model, @PathVariable("id") Long id) {

		Project a = this.albumService.albumPerID(id);
		List<Task> fotoAlbum = a.getFoto();
		this.fotoService.eliminaTutteFoto(fotoAlbum);
		this.albumService.eliminaAlbum(a);
		return "listaAlbum";

	}
	
	@RequestMapping("/listaAlbum")
	public String listaAlbum(Model model) {
			model.addAttribute("album", this.albumService.tuttiAlbum());
			return "listaAlbum";
	}
	
	@RequestMapping(value = "/deleteAlbum/{id}", method = RequestMethod.POST)
	public String eliminaAlbum(@PathVariable("id") Long id, Model model) {
		if (id != null) {
			this.albumService.eliminaAlbumPerID(id);
			model.addAttribute("fotografi", this.fotografoService.tuttiFotografi());
			return "listaFotografi";
		} else {
			return "okAlbum";
		}
	}
}
	
