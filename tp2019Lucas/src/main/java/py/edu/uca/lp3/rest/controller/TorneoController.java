package py.edu.uca.lp3.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import py.edu.uca.lp3.constants.ApiPaths;
import py.edu.uca.lp3.domain.Torneo;
import py.edu.uca.lp3.service.TorneoService;
import py.edu.uca.lp3exceptions.InscripcionException;

@RestController
@RequestMapping(ApiPaths.TORNEOS)
public class TorneoController {

	// Simulamos el design pattern de Controller-Service-Data_Access
	// típico de aplicaciones basadas en el framework Spring
	@Autowired
	private TorneoService torneoService;// = TorneoService.buildInstance();
	

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Torneo greetings(@PathVariable("id") Long id) {
        Torneo torneo = torneoService.findById(id);
        return torneo;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Torneo> list() {
        return torneoService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestBody List<Torneo> torneos) {
    	try {
			torneoService.saveList(torneos);
		} catch (InscripcionException e) {
			String email = e.getContacto();
			System.out.println("Ocurrió un error al inscribir los entrenadores: " + e.getMessage() + ". Para mas informacion contacte a: " + email);
		}
    }
    
    @RequestMapping(value = "/editar", method = RequestMethod.PUT)
    public void edit(@RequestBody List<Torneo> torneos) {
    	try {
			torneoService.editList(torneos);
		} catch (InscripcionException e) {
			String email = e.getContacto();
			System.out.println("Ocurrió un error al inscribir los entrenadores: " + e.getMessage() + ". Para mas informacion contacte a: " + email);
		}
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
    	torneoService.delete(id);
    }
    
    @RequestMapping(value = "/{nombreTorneo}/salario-promedio", method = RequestMethod.GET)
    public String obtenerPromedioSalarioPorEquipo(@PathVariable("nombreTorneo") String nombreTorneo) {
        String respuesta = torneoService.findPromedioSalarioEquipos(nombreTorneo);
        return respuesta;
    }

}
