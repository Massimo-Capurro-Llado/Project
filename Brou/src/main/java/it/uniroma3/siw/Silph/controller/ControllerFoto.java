package it.uniroma3.siw.Silph.controller;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.google.common.io.Files;

import it.uniroma3.siw.Silph.model.Project;
import it.uniroma3.siw.Silph.model.Task;
import it.uniroma3.siw.Silph.service.AlbumService;
import it.uniroma3.siw.Silph.service.FotoService;

@Controller
public class ControllerFoto {

	@Autowired
	private AlbumService albumService; 
	@Autowired
	private FotoService fotoService;
	
	public static final String rootUploadingDir = "uploads";
	public static final String uploadingDir = "" + System.getProperty("user.dir") 
	+ File.separator 
 + "src/main/resources/static" + File.separator + ControllerFoto.rootUploadingDir + File.separator;

	@RequestMapping(value = "/album/{id}/carica", method = RequestMethod.POST)
	public String uploadingPost(@PathVariable("id") Long id,
			@RequestParam("caricamentoFoto") MultipartFile[] uploadingFiles) throws IOException {

		Project project = this.albumService.albumPerID(id);
		for (MultipartFile uploadedFile : uploadingFiles) {
			Timestamp timestamp = new Timestamp(System.currentTimeMillis());
			String extension = Files.getFileExtension(uploadedFile.getOriginalFilename());
			String filename = "album_" + project.getId() + "_" + timestamp.getTime() + "." + extension;
			File file = new File(uploadingDir + filename);

			Task f = new Task();
			f.setNome(File.separator + ControllerFoto.rootUploadingDir + "/" + filename);
			f.setAlbum(project);
			this.fotoService.salva(f);
			uploadedFile.transferTo(file);
		}

		return "redirect:/album/" + project.getId() + "";
	}
	
	

	
}
