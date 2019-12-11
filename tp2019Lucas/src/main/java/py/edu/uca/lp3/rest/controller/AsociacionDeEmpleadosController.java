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
import py.edu.uca.lp3.domain.AsociacionDeEmpleados;
import py.edu.uca.lp3.domain.AsociacionDeEmpleados;
import py.edu.uca.lp3.service.AsociacionDeEmpleadosService;
import py.edu.uca.lp3exceptions.InscripcionException;

@RestController
@RequestMapping(ApiPaths.ASOCIACIONDEEMPLEADOS)
public class AsociacionDeEmpleadosController {

	// Simulamos el design pattern de Controller-Service-Data_Access
	// típico de aplicaciones basadas en el framework Spring
	@Autowired
	private AsociacionDeEmpleadosService AsociacionDeEmpleadoService;// = AsociacionDeEmpleadosService.buildInstance();
	

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public AsociacionDeEmpleados greetings(@PathVariable("id") Long id) {
        AsociacionDeEmpleados AsociacionDeEmpleado = AsociacionDeEmpleadoService.findById(id);
        return AsociacionDeEmpleado;
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<AsociacionDeEmpleados> list() {
        return AsociacionDeEmpleadoService.findAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestBody List<Integer> miembros) {
    	try {
			AsociacionDeEmpleadoService.saveList(miembros);
		} catch (InscripcionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("id") Long id) {
    	AsociacionDeEmpleadoService.delete(id);
    }

}
