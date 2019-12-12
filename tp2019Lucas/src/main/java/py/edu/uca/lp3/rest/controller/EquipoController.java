package py.edu.uca.lp3.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import py.edu.uca.lp3.constants.ApiPaths;
import py.edu.uca.lp3.domain.Equipo;
import py.edu.uca.lp3.service.EquipoService;
import py.edu.uca.lp3exceptions.InscripcionException;

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
    public void add(@RequestBody List<Equipo> equipo) {
    	try {
			equipoService.saveList(equipo);
		} catch (InscripcionException e) {
			String email = e.getContacto();
			System.out.println("Ocurrió un error al inscribir los equipos: " + e.getMessage() + ". Para mas informacion contacte a: " + email);
		}
    }

    
    @RequestMapping(method = RequestMethod.PUT)
    public void edit(@RequestBody List<Equipo> equipo) {
    	try {
			equipoService.editList(equipo);
		} catch (InscripcionException e) {
			String email = e.getContacto();
			System.out.println("Ocurrió un error al inscribir los equipos: " + e.getMessage() + ". Para mas informacion contacte a: " + email);
		}
    }
    
    @RequestMapping(value ="/impuesto-lujo", method = RequestMethod.PUT)
    public void pagoImpuestoLujo(@RequestBody List<Equipo> equipo) {
    	try {
			equipoService.pagoImpuestoLujoList(equipo);
		} catch (InscripcionException e) {
			String email = e.getContacto();
			System.out.println("Ocurrió un error al inscribir los equipos: " + e.getMessage() + ". Para mas informacion contacte a: " + email);
		}
    }
    
    
    @RequestMapping(value = "/clasifica-internacional", method = RequestMethod.PUT)
    public void clacificaInternacional(@RequestBody List<Equipo> equipo) {
    	try {
			equipoService.clasificaInternacionalList(equipo);
		} catch (InscripcionException e) {
			String email = e.getContacto();
			System.out.println("Ocurrió un error al inscribir los equipos: " + e.getMessage() + ". Para mas informacion contacte a: " + email);
		}
    }
    
    
    @SuppressWarnings("unchecked")
	@RequestMapping(value = "/ver/{nombreClub}", method = RequestMethod.GET)
    public List<Equipo> verEquipoObj(@PathVariable("nombreClub") String nombreClub) {
    	return (List<Equipo>) equipoService.objEquipo(nombreClub);
    }

}
