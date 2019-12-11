package py.edu.uca.lp3.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import py.edu.uca.lp3.domain.Empresa;
import py.edu.uca.lp3.service.EmpresaService;
import py.edu.uca.lp3exceptions.InscripcionException;

@RestController
@RequestMapping("/empresa")
public class EmpresaController {

	@Autowired
	private EmpresaService empresaService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public Empresa greetings(@PathVariable("id") Long id) {
		Empresa empresa = empresaService.findById(id);
		return empresa;
	}

	@RequestMapping(value = "/save-batch", method = RequestMethod.POST)
	public void saveBatch(@RequestBody List<Empresa> empresa) {
		try {
			empresaService.saveBatch(empresa);
		} catch (InscripcionException e) {
			String email = e.getContacto();
			System.out.println("Ocurrió un error al inscribir las empresas: " + e.getMessage());
		}
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public List<Empresa> list() {
		return empresaService.findAll();
	}

	@RequestMapping(method = RequestMethod.POST)
	public void add(@RequestBody Empresa empresa) {
		empresaService.save(empresa);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public void delete(@PathVariable("id") Long id) {
		empresaService.delete(id);
	}

}