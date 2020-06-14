package it.uniroma3.siw.Silph;

import java.io.File;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import it.uniroma3.siw.Silph.controller.ControllerFoto;



@SpringBootApplication
@EntityScan(basePackages = {"it.uniroma3.siw.Silph.model"})
public class SilphApplication {

	public static void main(String[] args) {
		//new File(ControllerFoto.uploadingDir).mkdir();
		SpringApplication.run(SilphApplication.class, args);
	}

}
