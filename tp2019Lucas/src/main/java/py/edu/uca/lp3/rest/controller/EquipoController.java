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
import py.edu.uca.lp3.domain.Equipo;
import py.edu.uca.lp3.repository.EquipoRepository;
import py.edu.uca.lp3.domain.Equipo;
import py.edu.uca.lp3.service.EquipoService;
import py.edu.uca.lp3.service.EquipoService;

@RestController
@RequestMapping(ApiPaths.EQUIPOS)
public class EquipoController {

	// Simulamos el design pattern de Controller-Service-Data_Access
	// típico de aplicaciones basadas en el framework Spring
	@Autowired
	private EquipoService equipoService;// = EquipoService.buildInstance();
	


    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Equipo> list() {
        return equipoService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestBody Equipo equipo) {
    	equipoService.save(equipo);
    }

    
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/{nombreClub}", method = RequestMethod.GET)
    public List<Equipo> verEquipoClass(@PathVariable("nombreClub") String nombreClub) {
    	return (List<Equipo>) equipoService.classEquipo(nombreClub);
    }

}
