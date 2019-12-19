package py.edu.uca.lp3.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.lang.Long;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.edu.uca.lp3.domain.Jugador;
import py.edu.uca.lp3.domain.Empleado;
import py.edu.uca.lp3.domain.Equipo;
import py.edu.uca.lp3.repository.JugadorRepository;
import py.edu.uca.lp3.repository.EmpleadoRepository;
import py.edu.uca.lp3.repository.EquipoRepository;
import py.edu.uca.lp3exceptions.InscripcionException;

import py.edu.uca.lp3.constants.Contacto;

@Service
public class JugadorService {
	@Autowired
	private JugadorRepository jugadorRepository;
	
	
	/*
     * Funcion para obtener un jugador en especifico
     * Parametros:
     * 				Long id : id del jugador que qeremos
     * Retorno:
     * 				Jugador jugador : el jugador con id = id
     * */
	public Jugador findById(Long id) {
		Jugador jugador = jugadorRepository.findOne(id);
		return jugador;
	}

	/*
     * Funcion para obtener una lista de todos los jugadores
     * Parametros:
     * 				ninguno
     * Retorno:
     * 				List<Jugador> jugadores : la lista de todos los jugadores
     * */
	public List<Jugador> findAll() {
		List<Jugador> jugadores = new ArrayList<>();
		Iterator<Jugador> iteratorJugadors = jugadorRepository.findAll().iterator();
		while(iteratorJugadors.hasNext()) {
			jugadores.add(iteratorJugadors.next());
		}
		return jugadores;
	}

	/*
     * Funcion para guardar un jugador con persistencia
     * Parametros:
     * 				Jugador jugador : el jugador que guardaremos con persistencia
     * Retorno:
     * 				ninguno
     * */
	public void save(Jugador jugador) {
		jugador.setCargo("Jugador");
		jugadorRepository.save(jugador);
	}

	/*
     * Funcion para eliminar un jugador con un id especifico de la persistencia
     * Parametros:
     * 				Long id : el ide del jugador que queremos eliminar
     * Retorno:
     * 				ninguno
     * */
	public void delete(Long id) {
		jugadorRepository.delete(id);
	}
	
	
	/*
     * Funcion para obtener un arraylist con todos los jugadores de un club determinado
     * Parametros:
     * 				String club : el club del cual queremos obtener los jugadores
     * Retorno:
     * 				ArrayList<Jugador> listaJugadors: un arraylist con los jugadores del club
     * */
	public ArrayList<Jugador> findJugadoresByClub(String club) {
		ArrayList<Jugador> listaJugadores = new ArrayList<Jugador>();
		Iterator<Jugador> iteratorJugadores = jugadorRepository.findAll().iterator();
		while(iteratorJugadores.hasNext()) {
			Jugador actual = iteratorJugadores.next();
			if (club != null && club.equals(actual.getClub())) {
				listaJugadores.add(actual);
			}

		}
		return listaJugadores;
	}
	
	/*
     * Funcion para guardar una lista de jugadores con persistencia
     * Parametros:
     * 				List<Jugador> jugadores : la lista de losjugadores que guardaremos con persistencia
     * Retorno:
     * 				ninguno
     * */
	public void saveList(List<Jugador> jugadores) throws InscripcionException {
		//EquipoService equipoServ = new EquipoService();
		for (Jugador aGuardar : jugadores) {
			//System.out.println(aGuardar.getClub());
			Equipo equipo = objEquipo(aGuardar.getClub());
			if (equipo == null) {
				InscripcionException inscripcionException = new InscripcionException(//System.out.println(
						"No se ha encontrado un equipo con el nombre: " + aGuardar.getClub());
				inscripcionException.setContacto(Contacto.INSCRIPCION);
				throw inscripcionException;
				//continue; 
			}
			long sumaSalarios= Long.sum(equipo.getSalarioClub() , aGuardar.getSalario());
			if ( sumaSalarios > equipo.getTopeSalarial() ) {
				InscripcionException inscripcionException = new InscripcionException(//System.out.println(
						"No se permite la inscripcion de: " + aGuardar.getNombre() +" "+ aGuardar.getApellido() + ", se supera el tope salarial con un salario total de: " + sumaSalarios);
				inscripcionException.setContacto(Contacto.INSCRIPCION);
				throw inscripcionException;
			}
			if(aGuardar.getNumeroCedula()<0) {
				InscripcionException inscripcionException = new InscripcionException(//System.out.println(
						"Numero de cedula invalido: "+aGuardar.getNumeroCedula());
				inscripcionException.setContacto(Contacto.INSCRIPCION);
				throw inscripcionException;
			}else if(!numeroDeCedulaDisponible(aGuardar.getNumeroCedula())) {
				InscripcionException inscripcionException = new InscripcionException(//System.out.println(
						"Ya existe una persona con el numero de cedula: " + aGuardar.getNumeroCedula());
				inscripcionException.setContacto(Contacto.INSCRIPCION);
				throw inscripcionException;
			}
			actualizarSalarioSumar(equipo, aGuardar.getSalario());
			save(aGuardar);
			
		}
	}
	
	/*
     * Funcion para guardar una lista de jugadores con persistencia al inicio (no evalua el promedio ni el tope salarial)
     * Se utiliza para asi, luego de cargar tus equipos, se pueda calcular el promedio y el tope salarial
     * Parametros:
     * 				List<Jugador> jugadores : la lista de losjugadores que guardaremos con persistencia
     * Retorno:
     * 				ninguno
     * */
	public void saveListInicio(List<Jugador> jugadores) throws InscripcionException {
		for (Jugador aGuardar : jugadores) {
			Equipo equipo = objEquipo(aGuardar.getClub());
			if (equipo == null) {
				InscripcionException inscripcionException = new InscripcionException(
						"No se ha encontrado un equipo con el nombre: " + aGuardar.getClub());
				inscripcionException.setContacto(Contacto.INSCRIPCION);
				throw inscripcionException;
			}
			if(aGuardar.getNumeroCedula()<0) {
				InscripcionException inscripcionException = new InscripcionException(
						"Numero de cedula invalido: "+aGuardar.getNumeroCedula());
				inscripcionException.setContacto(Contacto.INSCRIPCION);
				throw inscripcionException;
			}else if(!numeroDeCedulaDisponible(aGuardar.getNumeroCedula())) {
				InscripcionException inscripcionException = new InscripcionException(
						"Ya existe una persona con el numero de cedula: " + aGuardar.getNumeroCedula());
				inscripcionException.setContacto(Contacto.INSCRIPCION);
				throw inscripcionException;
			}
			actualizarSalarioSumar(equipo, aGuardar.getSalario());
			save(aGuardar);
			
		}
	}
	
	
	
	/*
     * Funcion para verificar si un numero de cedula especifico se encuentra disponible (si no hay nadie ya utilizando ese numero de cedula)
     * Parametros:
     * 				int cedula : el numero de cedula que queremos verificar
     * Retorno:
     * 				boolean : true si el numero de cedula se encuentra disponible, false si no
     * */
	public boolean numeroDeCedulaDisponible(int cedula) {
		if(findByCedulaEmp(cedula) == null) {
			return true;
		}
		return false;
	}
	
	/*
     * Funcion para obtener un jugador por numerio de cedula
     * Parametros:
     * 				int cedula : el numero de cedula del jugador que queremos
     * Retorno:
     * 				Jugador actual : el jugador que coincide con el numero de cedula
     * 				null : si no se encontro ningun jugador con dicho numero de cedula
     * */
	public Jugador findByCedula(int cedula) {
		Iterator<Jugador> iteratorJugadors = jugadorRepository.findAll().iterator();
		while(iteratorJugadors.hasNext()) {
			Jugador actual = iteratorJugadors.next();
			if (cedula== actual.getNumeroCedula()) {
				return actual;
			}
		}
		return null;
	}
	
	@Resource
	EmpleadoRepository empleadoRepository;
	/*
     * Funcion para obtener un empleado por numerio de cedula
     * Parametros:
     * 				int cedula : el numero de cedula del empleado que queremos
     * Retorno:
     * 				Empleado actual : el empleado que coincide con el numero de cedula
     * 				null : si no se encontro ningun empleado con dicho numero de cedula
     * */
	public Empleado findByCedulaEmp(int cedula) {
		Iterator<Empleado> iteratorEmpleados = empleadoRepository.findAll().iterator();
		while(iteratorEmpleados.hasNext()) {
			Empleado actual = iteratorEmpleados.next();
			if (cedula== actual.getNumeroCedula()) {
				return actual;
			}
		}
		return null;
	}
	
	/*
     * Funcion para eliminar un jugador de la persistencia utilizando su numero de cedula
     * Parametros:
     * 				int cedula : el numero de cedula del jugador que queremos eliminar
     * Retorno:
     * 				ninguno
     * */
	public void deleteByCedula(int cedula) throws InscripcionException {
		Empleado empleado = findByCedula(cedula);
		if(empleado == null) {
			InscripcionException inscripcionException = new InscripcionException(//System.out.println(
					"No existe un jugador con numero de cedula: "+cedula);
			inscripcionException.setContacto(Contacto.INSCRIPCION);
			throw inscripcionException;
		}
		Equipo equipo = objEquipo(empleado.getClub());
		long id = empleado.getId();
		jugadorRepository.delete(id);
		actualizarSalarioRestar(equipo, empleado.getSalario());
	}
	
	@Resource
	private EquipoRepository equipoRepository;
	
	/*
     * Funcion para obtener el objeto de un equipo por su nombre
     * Parametros:
     * 				String club : el nombre del equipo que queremos
     * Retorno:
     * 				Equipo actual: el equipo que coincide con el nombre que le pasamos
     * 				null : si no encuentra ningun equipo con dicho nombre
     * */
	public Equipo objEquipo(String club) {
		Iterator<Equipo> iteratorEquipo = equipoRepository.findAll().iterator();
		while(iteratorEquipo.hasNext()) {
			Equipo actual = iteratorEquipo.next();
			//System.out.println("NOMBRE: "+actual.getNombre());
			if (club.equals(actual.getNombre())) {
				return actual;
			}
		}
		return null;
	}

	/*
     * Funcion para transferir/cambiar el club del jugador
     * Parametros:
     * 				int cedula : el numero de cedula del jugador que queremos modificar
     * 				String nuevoClub : el nombre del club al cual sera transferido
     * Retorno:
     * 				ninguno
     * */
	public void transferir(int cedula, String nuevoClub) throws InscripcionException {
		Jugador jugador = findByCedula(cedula);
		if(jugador != null) {
			if(objEquipo(nuevoClub)!=null) {
				jugador.setClub(nuevoClub);
				save(jugador);
			}else {
				InscripcionException inscripcionException = new InscripcionException(//System.out.println(
						"No existe el club: "+nuevoClub);
				inscripcionException.setContacto(Contacto.INSCRIPCION);
				throw inscripcionException;
			}
		}else {
			InscripcionException inscripcionException = new InscripcionException(//System.out.println(
					"No existe el jugador con numero de cedula: "+cedula);
			inscripcionException.setContacto(Contacto.INSCRIPCION);
			throw inscripcionException;
		}
		
	}
	
	/*
     * Funcion para cambiar el cargo del jugador
     * Parametros:
     * 				int cedula : el numero de cedula del jugador que queremos modificar
     * 				String nuevoCargob : el nuevo cargo del jugador
     * Retorno:
     * 				ninguno
     * */
	public void cambiarCargo(int cedula, String nuevoCargo) throws InscripcionException {
		Jugador jugador = findByCedula(cedula);
		if(jugador != null) {
			jugador.setCargo(nuevoCargo);
			save(jugador);
		}else {
			InscripcionException inscripcionException = new InscripcionException(//System.out.println(
					"No existe el jugador con numero de cedula: "+cedula);
			inscripcionException.setContacto(Contacto.INSCRIPCION);
			throw inscripcionException;
		}
		
	}
	
	/*
     * Funcion para cambiar el salario del jugador
     * Parametros:
     * 				int cedula : el numero de cedula del jugador que queremos modificar
     * 				int nuevoSalario : el nuevo salario del jugador
     * Retorno:
     * 				ninguno
     * */
	public void cambiarSalario(int cedula, int nuevoSalario) throws InscripcionException {
		Jugador jugador = findByCedula(cedula);
		if(jugador != null) {
			jugador.setSalario(nuevoSalario);
			save(jugador);
		}else {
			InscripcionException inscripcionException = new InscripcionException(//System.out.println(
					"No existe el jugador con numero de cedula: "+cedula);
			inscripcionException.setContacto(Contacto.INSCRIPCION);
			throw inscripcionException;
		}
		
	}
	
	
	/*
     * Funcion para cambiar el salario del jugador
     * Parametros:
     * 				int cedula : el numero de cedula del jugador que queremos modificar
     * 				int nuevoSalario : el nuevo salario del jugador
     * Retorno:
     * 				ninguno
     * */
	public void cambiarPosicion(int cedula, String nuevaPosicion) throws InscripcionException {
		Jugador jugador = findByCedula(cedula);
		if(jugador != null) {
			jugador.setPosicion(nuevaPosicion);
			save(jugador);
		}else {
			InscripcionException inscripcionException = new InscripcionException(//System.out.println(
					"No existe el jugador con numero de cedula: "+cedula);
			inscripcionException.setContacto(Contacto.INSCRIPCION);
			throw inscripcionException;
		}
		
	}
	
	/*
     * Funcion para actualizar los atributos del jugador
     * Parametros:
     * 				int cedula : el numero de cedula del jugador que queremos modificar
     * Retorno:
     * 				ninguno
     * */
	public void editarAtributos(List<Jugador> jugadores) throws InscripcionException {
		for(Jugador jugadorEdit:jugadores) {
			int cedula = jugadorEdit.getNumeroCedula();
			Jugador jugadorExistente = findByCedula(cedula);
			if(jugadorExistente != null) {
				jugadorExistente.setHabilidad(jugadorEdit.getHabilidad());
				jugadorExistente.setPiernaHabil(jugadorEdit.getPiernaHabil());
				jugadorExistente.setPotencia(jugadorEdit.getPotencia());
				jugadorExistente.setVelocidad(jugadorEdit.getVelocidad());
				jugadorExistente.setResistencia(jugadorEdit.getResistencia());
				save(jugadorExistente);
			}else {
				InscripcionException inscripcionException = new InscripcionException(//System.out.println(
						"No existe el jugador con numero de cedula: "+cedula);
				inscripcionException.setContacto(Contacto.INSCRIPCION);
				throw inscripcionException;
			}
		}
		
		
	}
	
	
	/*
     * Funcion para actualizar el salario de un equipo especifico, y el promedio y tope de salario para todos los equipos
     * Parametros:
     * 				Equipo equipo : el equipo que esta modificando su salario
     * 				in salarioParaSumar : el salario que se agrega al equipo
     * Retorno:
     * 				ninguno
     * */
	public void actualizarSalarioSumar(Equipo equipo, int salarioParaSumar) {
		equipo.setSalarioClub(equipo.getSalarioClub() + salarioParaSumar);
		equipo.setImpuestoPromocion();
		equipo.setImpuestoTecnologia();
		equipoRepository.save(equipo);
		
		Long prom = actualizarPromedio();
		Long tope = actualizarTope(prom);
		
		Iterator<Equipo> iteratorEquipo = equipoRepository.findAll().iterator();
		while(iteratorEquipo.hasNext()) {
			Equipo actual = iteratorEquipo.next();
			actual.setPromedioSalarial(prom);
			actual.setTopeSalarial(tope);
			actual.setImpuestoLujo();
			actual.setImpuestoPromocion();
			actual.setImpuestoTecnologia();
			actual.setAumentoPorLujo();
			equipoRepository.save(actual);
		}
	}
	
	/*
     * Funcion para actualizar el salario de un equipo especifico, y el promedio y tope de salario para todos los equipos
     * Parametros:
     * 				Equipo equipo : el equipo que esta modificando su salario
     * 				in salarioParaRestar : el salario que se resta al equipo
     * Retorno:
     * 				ninguno
     * */
	public void actualizarSalarioRestar(Equipo equipo, int salarioParaRestar) {
		equipo.setSalarioClub(equipo.getSalarioClub() - salarioParaRestar);
		equipoRepository.save(equipo);
		
		Long prom = actualizarPromedio();
		Long tope = actualizarTope(prom);
		
		Iterator<Equipo> iteratorEquipo = equipoRepository.findAll().iterator();
		while(iteratorEquipo.hasNext()) {
			Equipo actual = iteratorEquipo.next();
			actual.setPromedioSalarial(prom);
			actual.setTopeSalarial(tope);
			actual.setImpuestoLujo();
			actual.setImpuestoPromocion();
			actual.setImpuestoTecnologia();
			actual.setAumentoPorLujo();
			equipoRepository.save(actual);
		}
		
	}
	
	/*
	 * Funcion para actualizar el salario promedio de todos los equipos
	 * Parametors:
	 * 				ninguno
	 * Retorno:
	 * 				Long : el promedio de todos los salarios
	 * */
	public Long actualizarPromedio() {
		Iterator<Equipo> iteratorEquipos2 = equipoRepository.findAll().iterator();
		int cantidadDeEquipos = 0;
		long sumaDeSalarios = 0;
		while(iteratorEquipos2.hasNext()) {
			cantidadDeEquipos ++;
			Equipo actual = iteratorEquipos2.next();
			sumaDeSalarios = Long.sum(actual.getSalarioClub(),sumaDeSalarios);
		}
		return Long.divideUnsigned(sumaDeSalarios, cantidadDeEquipos);
	}
	
	/*
	 * Funcion para actualizar el salario tope de todos los equipos
	 * Parametors:
	 * 				Long promedioSalarial : el promedio salarial de todos los clubes, se usa enla ecuacion para obtener el tope
	 * Retorno:
	 * 				Long tope : el tope salarial
	 * */
	public Long actualizarTope(Long promedioSalarial) {
		long x = promedioSalarial;
		long tope = (long) (x + 0.2*x + 0.3*1.2*x);
		return tope;
	}
	

	/*
	 * Funcion para obtener el promedio del salario de todos los jugadores de un equipo/club
	 * Parametros:
	 * 				String club : el nombre del club
	 * Retorno:
	 * 				String respuesta : un string en formato ("El promedio de salario por equipo es: "{PROMEDIO})
	 * */
	public String promedioSalarioJugadorEnClub(String club) {
		long sumaSalarios = 0;
		int cantidadDeJugadores;
		Equipo actual = objEquipo(club);
		if(actual == null) {
			return ("El equipo "+club+" no existe.");
		}
		ArrayList<Jugador> jugadoresList = findJugadoresByClub(club);
		cantidadDeJugadores = jugadoresList.size();
		if(cantidadDeJugadores < 1) {
			return ("No hay jugadores en el equipo: "+club);
		}
		for(Jugador jugador:jugadoresList) {
			sumaSalarios += jugador.getSalario();
		}
		
		return ("El promedio de salario por equipo es: " +Long.divideUnsigned(sumaSalarios, cantidadDeJugadores));
	}

	
	/*
	 * Funcion para obtener el promedio del salario de todos los jugadores de un equipo/club
	 * Parametros:
	 * 				String club : el nombre del club
	 * Retorno:
	 * 				String respuesta : un string en formato ("El promedio de salario por equipo es: "{PROMEDIO})
	 * */
	public String promedioSalarioJugador() {
		long sumaSalarios = 0;
		int cantidadDeJugadores;
		List<Jugador> jugadoresList = findAll();
		cantidadDeJugadores = jugadoresList.size();
		if(cantidadDeJugadores < 1) {
			return ("No hay jugadores registrados");
		}
		for(Jugador jugador:jugadoresList) {
			sumaSalarios += jugador.getSalario();
		}
		
		return ("El promedio de salario por equipo es: " +Long.divideUnsigned(sumaSalarios, cantidadDeJugadores));
	}
}
