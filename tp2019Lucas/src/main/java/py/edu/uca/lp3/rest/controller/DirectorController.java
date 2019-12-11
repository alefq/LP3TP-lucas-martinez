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
import py.edu.uca.lp3.domain.Director;
import py.edu.uca.lp3.domain.Director;
import py.edu.uca.lp3.service.DirectorService;
import py.edu.uca.lp3exceptions.InscripcionException;

@RestController
@RequestMapping(ApiPaths.DIRECTORES)
public class DirectorController {

	// Simulamos el design pattern de Controller-Service-Data_Access
	// típico de aplicaciones basadas en el framework Spring
	@Autowired
	private DirectorService directorService;// = DirectorService.buildInstance();

	/*
     * Llama a la funcion para ver a todos los directores de un club
     * {club} el club que queremos ver
     * */
	@RequestMapping(value = "/{club}", method = RequestMethod.GET)
	public ArrayList<Director> obtenerEquipos(@PathVariable("club") String club) {
		return directorService.findDirectoresByClub(club);
	}
	

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Director greetings(@PathVariable("id") Long id) {
        Director director = directorService.findById(id);
        return director;
    }
    
    /*
     * Llama a la funcion para ver a todos los directores
     * */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Director> list() {
        return directorService.findAll();
    }

    /*
     * Llama a la funcion para agregar una lista de directores
     * por metodo POST
     * */
    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestBody List<Director> directores) {
    	try {
			directorService.saveList(directores);
		} catch (InscripcionException e) {
			String email = e.getContacto();
			System.out.println("Ocurrió un error al inscribir los directores: " + e.getMessage());
		}
    }

    /*
     * Llama a la funcion para eliminar un empleado por su numero de cedula por DELETE
     * por metodo DELETE
     */
    @RequestMapping(value = "/{cedula}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("cedula") int cedula) {
    	try {
			directorService.deleteByCedula(cedula);
		} catch (InscripcionException e) {
			// TODO Auto-generated catch block
			String email = e.getContacto();
			System.out.println("Ocurrió un error al tratar de elimniar al director: " + e.getMessage());
		}
    }
    
    /*
     * Llama a la funcion para transferir/cambiar el club de un director
     * {cedula} es el numero de cedula
     * {club} el nuevo club
     * */
    @RequestMapping(value = "/{cedula}/transferir-a/{club}", method = RequestMethod.PUT)
    public void transferir(@PathVariable("cedula") int cedula, @PathVariable("club") String nuevoClub) {
    	try {
			directorService.transferir(cedula, nuevoClub);
		} catch (InscripcionException e) {
			// TODO Auto-generated catch block
			String email = e.getContacto();
			System.out.println("Ocurrió un error al inscribir los directores: " + e.getMessage());
		}
    }
    
    /*
     * Llama a la funcion para cambiar el cargo de un director
     * {cedula} es el numero de cedula
     * {cargo} el nuevo cargo
     * */
    @RequestMapping(value = "/{cedula}/cambiar-cargo/{cargo}", method = RequestMethod.PUT)
    public void cargo(@PathVariable("cedula") int cedula, @PathVariable("cargo") String nuevoCargo) {
    	try {
			directorService.cambiarCargo(cedula,nuevoCargo);
		} catch (InscripcionException e) {
			// TODO Auto-generated catch block
			String email = e.getContacto();
			System.out.println("Ocurrió un error al inscribir los directores: " + e.getMessage());
		}
    }
    
    /*
     * Llama a la funcion para cambiar el salario de un director
     * {cedula} es el numero de cedula
     * {salario} el nuevo salario
     * */
    @RequestMapping(value = "/{cedula}/cambiar-salario/{salario}", method = RequestMethod.PUT)
    public void salario(@PathVariable("cedula") int cedula, @PathVariable("salario") int nuevoSalario) {
    	try {
			directorService.cambiarSalario(cedula,nuevoSalario);
		} catch (InscripcionException e) {
			// TODO Auto-generated catch block
			String email = e.getContacto();
			System.out.println("Ocurrió un error al inscribir los directores: " + e.getMessage());
		}
    }

}
