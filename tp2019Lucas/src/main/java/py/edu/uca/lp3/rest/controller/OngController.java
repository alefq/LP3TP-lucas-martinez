package py.edu.uca.lp3.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import py.edu.uca.lp3.domain.Ong;
import py.edu.uca.lp3.service.OngService;
import py.edu.uca.lp3exceptions.InscripcionException;
import py.edu.uca.lp3.constants.ApiPaths;

@RestController
@RequestMapping(ApiPaths.ONG)
public class OngController {

	@Autowired
	private OngService ongService;


	@RequestMapping(method = RequestMethod.POST)
	public void saveList(@RequestBody List<Ong> ong) {
		try {
			ongService.saveList(ong);
		} catch (InscripcionException e) {
			String email = e.getContacto();
			System.out.println("Ocurrió un error al inscribir las instituciones ongs: " + e.getMessage() + ". Para mas informacion contacte a: " + email);
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Ong> list() {
		return ongService.findAll();
	}

	
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		ongService.delete(id);
	}

}