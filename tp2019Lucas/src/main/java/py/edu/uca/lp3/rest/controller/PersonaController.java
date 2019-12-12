package py.edu.uca.lp3.rest.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import py.edu.uca.lp3.constants.ApiPaths;
import py.edu.uca.lp3.domain.Persona;
import py.edu.uca.lp3.service.PersonaService;
import py.edu.uca.lp3exceptions.InscripcionException;

@RestController
@RequestMapping(ApiPaths.PERSONA)
public class PersonaController {

	// Simulamos el design pattern de Controller-Service-Data_Access
	// típico de aplicaciones basadas en el framework Spring
	@Autowired
	private PersonaService personaService;// = PersonaService.buildInstance();

	

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Persona greetings(@PathVariable("id") Long id) {
        Persona persona = personaService.findById(id);
        return persona;
    }
    
    /*
     * Llama a la funcion para ver a todos los personas
     * */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Persona> list() {
        return personaService.findAll();
    }

    /*
     * Llama a la funcion para agregar una lista de personas
     * por metodo POST
     * */
    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestBody List<Persona> personas) {
    	try {
			personaService.saveList(personas);
		} catch (InscripcionException e) {
			String email = e.getContacto();
			System.out.println("Ocurrió un error al inscribir las personas: " + e.getMessage() + ". Para mas informacion contacte a: " + email);
		}
    }

    /*
     * Llama a la funcion para eliminar un persona por su numero de cedula por DELETE
     * por metodo DELETE
     */
    @RequestMapping(value = "/{cedula}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("cedula") int cedula) {
    	try {
			personaService.deleteByCedula(cedula);
		} catch (InscripcionException e) {
			// TODO Auto-generated catch block
			String email = e.getContacto();
			System.out.println("Ocurrió un error al tratar de elimniar la persona: " + e.getMessage() + ". Para mas informacion contacte a: " + email);
		}
    }

}
