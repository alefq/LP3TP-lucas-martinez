package py.edu.uca.lp3.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import py.edu.uca.lp3.constants.ApiPaths;
import py.edu.uca.lp3.domain.Torneo;
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
    	torneoService.saveList(torneos);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
    	torneoService.delete(id);
    }

}
