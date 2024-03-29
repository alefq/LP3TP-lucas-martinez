package py.edu.uca.lp3.rest.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import py.edu.uca.lp3.constants.ApiPaths;
import py.edu.uca.lp3.domain.Empleado;
import py.edu.uca.lp3.service.EmpleadoService;
import py.edu.uca.lp3exceptions.InscripcionException;

@RestController
@RequestMapping(ApiPaths.EMPLEADOS)
public class EmpleadoController {

	// Simulamos el design pattern de Controller-Service-Data_Access
	// típico de aplicaciones basadas en el framework Spring
	@Autowired
	private EmpleadoService empleadoService;// = EmpleadoService.buildInstance();

	/*
     * Llama a la funcion para ver a todos los empleados de un club
     * {club} el club que queremos ver
     * */
	@RequestMapping(value = "/equipo/{club}", method = RequestMethod.GET)
	public ArrayList<Empleado> obtenerEquipos(@PathVariable("club") String club) {
		return empleadoService.findEmpleadosByClub(club);
	}
	

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Empleado greetings(@PathVariable("id") Long id) {
        Empleado empleado = empleadoService.findById(id);
        return empleado;
    }
    
    /*
     * Llama a la funcion para ver a todos los empleados
     * */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Empleado> list() {
        return empleadoService.findAll();
    }

    /*
     * Llama a la funcion para agregar una lista de empleados
     * por metodo POST
     * */
    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestBody List<Empleado> empleados) {
    	try {
			empleadoService.saveList(empleados);
		} catch (InscripcionException e) {
			String email = e.getContacto();
			System.out.println("Ocurrió un error al inscribir los empleados: " + e.getMessage() + ". Para mas informacion contacte a: " + email);
		}
    }

    /*
     * Llama a la funcion para eliminar un empleado por su numero de cedula por DELETE
     * por metodo DELETE
     */
    @RequestMapping(value = "/{cedula}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("cedula") int cedula) {
    	try {
			empleadoService.deleteByCedula(cedula);
		} catch (InscripcionException e) {
			// TODO Auto-generated catch block
			String email = e.getContacto();
			System.out.println("Ocurrió un error al tratar de elimniar al empleado: " + e.getMessage() + ". Para mas informacion contacte a: " + email);
		}
    }
    
    /*
     * Llama a la funcion para transferir/cambiar el club de un empleado
     * {cedula} es el numero de cedula
     * {club} el nuevo club
     * */
    @RequestMapping(value = "/{cedula}/transferir-a/{club}", method = RequestMethod.PUT)
    public void transferir(@PathVariable("cedula") int cedula, @PathVariable("club") String nuevoClub) {
    	try {
			empleadoService.transferir(cedula, nuevoClub);
		} catch (InscripcionException e) {
			String email = e.getContacto();
			System.out.println("Ocurrió un error al inscribir los empleados: " + e.getMessage() + ". Para mas informacion contacte a: " + email);
		}
    }
    
    /*
     * Llama a la funcion para cambiar el cargo de un empleado
     * {cedula} es el numero de cedula
     * {cargo} el nuevo cargo
     * */
    @RequestMapping(value = "/{cedula}/cambiar-cargo/{cargo}", method = RequestMethod.PUT)
    public void cargo(@PathVariable("cedula") int cedula, @PathVariable("cargo") String nuevoCargo) {
    	try {
			empleadoService.cambiarCargo(cedula,nuevoCargo);
		} catch (InscripcionException e) {
			// TODO Auto-generated catch block
			String email = e.getContacto();
			System.out.println("Ocurrió un error al inscribir los empleados: " + e.getMessage() + ". Para mas informacion contacte a: " + email);
		}
    }
    
    /*
     * Llama a la funcion para cambiar el salario de un empleado
     * {cedula} es el numero de cedula
     * {salario} el nuevo salario
     * */
    @RequestMapping(value = "/{cedula}/cambiar-salario/{salario}", method = RequestMethod.PUT)
    public void salario(@PathVariable("cedula") int cedula, @PathVariable("salario") int nuevoSalario) {
    	try {
			empleadoService.cambiarSalario(cedula,nuevoSalario);
		} catch (InscripcionException e) {
			// TODO Auto-generated catch block
			String email = e.getContacto();
			System.out.println("Ocurrió un error al inscribir los empleados: " + e.getMessage() + ". Para mas informacion contacte a: " + email);
		}
    }
    
    @RequestMapping(value = "/equipo/{club}/promedio-salario", method = RequestMethod.GET)
    public String obtenerPromedioSalarioEmpleadoPorClub(@PathVariable("club") String club) {
    	String respuesta = empleadoService.promedioSalarioEmpleadoEnClub(club);
        return respuesta;
    }

}
