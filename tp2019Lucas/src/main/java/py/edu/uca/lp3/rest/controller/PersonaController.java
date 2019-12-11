package py.edu.uca.lp3.rest.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import py.edu.uca.lp3.constants.ApiPaths;
import py.edu.uca.lp3.domain.Persona;
import py.edu.uca.lp3.service.PersonaService;

@RestController
@RequestMapping(ApiPaths.PERSONA)
public class PersonaController {

	// Simulamos el design pattern de Controller-Service-Data_Access
	// típico de aplicaciones basadas en el framework Spring
	@Autowired
	private PersonaService personaService;// = PersonaService.buildInstance();

	
	public Persona obtenerPersona(@RequestParam(value = "numeroCedula", required = true) Integer numeroCedula) {
		return personaService.findPersonaByNroCedula(numeroCedula);
	}

}
