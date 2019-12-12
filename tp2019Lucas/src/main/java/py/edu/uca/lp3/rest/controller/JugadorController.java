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
import py.edu.uca.lp3.domain.Jugador;
import py.edu.uca.lp3.service.JugadorService;
import py.edu.uca.lp3exceptions.InscripcionException;

@RestController
@RequestMapping(ApiPaths.JUGADORES)
public class JugadorController {

	// Simulamos el design pattern de Controller-Service-Data_Access
	// típico de aplicaciones basadas en el framework Spring
	@Autowired
	private JugadorService jugadorService;// = JugadorService.buildInstance();

	/*
     * Llama a la funcion para ver a todos los jugadores de un club por GET
     * {club} el club que queremos ver
     * */
	@RequestMapping(value = "/equipo/{club}", method = RequestMethod.GET)
	public ArrayList<Jugador> obtenerEquipos(@PathVariable("club") String club) {
		return jugadorService.findJugadoresByClub(club);
	}
	

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public Jugador greetings(@PathVariable("id") Long id) {
        Jugador jugador = jugadorService.findById(id);
        return jugador;
    }
    
    /*
     * Llama a la funcion para ver a todos los jugadores por GET
     * */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<Jugador> list() {
        return jugadorService.findAll();
    }

    /*
     * Llama a la funcion para agregar una lista de jugadores por POST
     * por metodo POST
     * */
    @RequestMapping(method = RequestMethod.POST)
    public void add(@RequestBody List<Jugador> jugadores) {
    	try {
			jugadorService.saveList(jugadores);
		} catch (InscripcionException e) {
			String email = e.getContacto();
			System.out.println("Ocurrió un error al inscribir los jugadores: " + e.getMessage() + ". Para mas informacion contacte a: " + email);
		}
    }
    
    /*
     * Llama a la funcion para agregar una lista de jugadores al inicio por POST
     * asi no se evalua el tope salarial ya que al comienzo este es cero
     * */
    @RequestMapping(value = "/inicializar", method = RequestMethod.POST)
    public void addInit(@RequestBody List<Jugador> jugadores) {
    	try {
			jugadorService.saveListInicio(jugadores);
		} catch (InscripcionException e) {
			String email = e.getContacto();
			System.out.println("Ocurrió un error al inscribir los jugadores: " + e.getMessage() + ". Para mas informacion contacte a: " + email);
		}
    }

    /*
     * Llama a la funcion para eliminar un empleado por su numero de cedula por DELETE
     * por metodo DELETE
     */
    @RequestMapping(value = "/{cedula}", method = RequestMethod.DELETE)
    public void delete(@PathVariable("cedula") int cedula) {
    	try {
			jugadorService.deleteByCedula(cedula);
		} catch (InscripcionException e) {
			// TODO Auto-generated catch block
			String email = e.getContacto();
			System.out.println("Ocurrió un error al tratar de elimniar al jugador: " + e.getMessage() + ". Para mas informacion contacte a: " + email);
		}
    }
    
    /*
     * Llama a la funcion para transferir/cambiar el club de un jugador por PUT
     * {cedula} es el numero de cedula
     * {club} el nuevo club
     * */
    @RequestMapping(value = "/{cedula}/transferir-a/{club}", method = RequestMethod.PUT)
    public void transferir(@PathVariable("cedula") int cedula, @PathVariable("club") String nuevoClub) {
    	try {
			jugadorService.transferir(cedula, nuevoClub);
		} catch (InscripcionException e) {
			// TODO Auto-generated catch block
			String email = e.getContacto();
			System.out.println("Ocurrió un error al inscribir los jugadores: " + e.getMessage() + ". Para mas informacion contacte a: " + email);
		}
    }
    
    /*
     * Llama a la funcion para cambiar el cargo de un jugador por PUT
     * {cedula} es el numero de cedula
     * {cargo} el nuevo cargo
     * */
    @RequestMapping(value = "/{cedula}/cambiar-cargo/{cargo}", method = RequestMethod.PUT)
    public void cargo(@PathVariable("cedula") int cedula, @PathVariable("cargo") String nuevoCargo) {
    	try {
			jugadorService.cambiarCargo(cedula,nuevoCargo);
		} catch (InscripcionException e) {
			// TODO Auto-generated catch block
			String email = e.getContacto();
			System.out.println("Ocurrió un error al inscribir los jugadores: " + e.getMessage() + ". Para mas informacion contacte a: " + email);
		}
    }
    
    /*
     * Llama a la funcion para cambiar el salario de un jugador por PUT
     * {cedula} es el numero de cedula
     * {salario} el nuevo salario
     * */
    @RequestMapping(value = "/{cedula}/cambiar-salario/{salario}", method = RequestMethod.PUT)
    public void salario(@PathVariable("cedula") int cedula, @PathVariable("salario") int nuevoSalario) {
    	try {
			jugadorService.cambiarSalario(cedula,nuevoSalario);
		} catch (InscripcionException e) {
			// TODO Auto-generated catch block
			String email = e.getContacto();
			System.out.println("Ocurrió un error al inscribir los jugadores: " + e.getMessage() + ". Para mas informacion contacte a: " + email);
		}
    }
    
    /*
     * Llama a la funcion para cambiar la posicion de un jugador por PUT
     * {cedula} es el numero de cedula
     * {posicion} la nueva posicion del jugador
     * */
    @RequestMapping(value = "/{cedula}/cambiar-posicion/{posicion}", method = RequestMethod.PUT)
    public void posicion(@PathVariable("cedula") int cedula, @PathVariable("salario") String nuevaPosicion) {
    	try {
			jugadorService.cambiarPosicion(cedula,nuevaPosicion);
		} catch (InscripcionException e) {
			// TODO Auto-generated catch block
			String email = e.getContacto();
			System.out.println("Ocurrió un error al inscribir los jugadores: " + e.getMessage() + ". Para mas informacion contacte a: " + email);
		}
    }
    
    @RequestMapping(value = "/equipo/{club}/promedio-salario", method = RequestMethod.GET)
    public String obtenerPromedioSalarioJugadorPorClub(@PathVariable("club") String club) {
    	String respuesta = jugadorService.promedioSalarioJugadorEnClub(club);
        return respuesta;
    }
    
    @RequestMapping(value = "/promedio-salario", method = RequestMethod.GET)
    public String obtenerPromedioSalarioJugador() {
    	String respuesta = jugadorService.promedioSalarioJugador();
        return respuesta;
    }

}
