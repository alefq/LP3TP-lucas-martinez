package py.edu.uca.lp3.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.edu.uca.lp3.constants.ApiPaths;
import py.edu.uca.lp3.constants.Contacto;
import py.edu.uca.lp3.domain.Empleado;
import py.edu.uca.lp3.domain.Empresa;
import py.edu.uca.lp3.domain.Entrenador;
import py.edu.uca.lp3.domain.Equipo;
import py.edu.uca.lp3.domain.Entrenador;
import py.edu.uca.lp3.repository.EntrenadorRepository;
import py.edu.uca.lp3.repository.EquipoRepository;
import py.edu.uca.lp3exceptions.InscripcionException;

@Service
public class EntrenadorService {
	@Autowired
	private EntrenadorRepository entrenadorRepository;
	

	/*
     * Funcion para obtener un entrenador en especifico
     * Parametros:
     * 				Long id : id del entrenador que qeremos
     * Retorno:
     * 				Entrenador entrenador : el entrenador con id = id
     * */
	public Entrenador findById(Long id) {
		Entrenador entrenador = entrenadorRepository.findOne(id);
		return entrenador;
	}

	/*
     * Funcion para obtener una lista de todos los entrenadores
     * Parametros:
     * 				ninguno
     * Retorno:
     * 				List<Entrenador> entrenadores : la lista de todos los entrenadores
     * */
	public List<Entrenador> findAll() {
		List<Entrenador> entrenadores = new ArrayList<>();
		Iterator<Entrenador> iteratorEntrenadors = entrenadorRepository.findAll().iterator();
		while(iteratorEntrenadors.hasNext()) {
			entrenadores.add(iteratorEntrenadors.next());
		}
		return entrenadores;
	}

	/*
     * Funcion para guardar un entrenador con persistencia
     * Parametros:
     * 				Entrenador entrenador : el entrenador que guardaremos con persistencia
     * Retorno:
     * 				ninguno
     * */
	public void save(Entrenador entrenador) {
		entrenador.setCargo("Entrenador");
		entrenadorRepository.save(entrenador);
	}

	/*
     * Funcion para eliminar un entrenador con un id especifico de la persistencia
     * Parametros:
     * 				Long id : el ide del entrenador que queremos eliminar
     * Retorno:
     * 				ninguno
     * */
	public void delete(Long id) {
		entrenadorRepository.delete(id);
	}
	
	
	/*
     * Funcion para obtener un arraylist con todos los entrenadores de un club determinado
     * Parametros:
     * 				String club : el club del cual queremos obtener los entrenadores
     * Retorno:
     * 				ArrayList<Entrenador> listaEntrenadors: un arraylist con los entrenadores del club
     * */
	public ArrayList<Entrenador> findEntrenadoresByClub(String club) {
		ArrayList<Entrenador> listaEntrenadors = new ArrayList<Entrenador>();
		Iterator<Entrenador> iteratorEntrenadors = entrenadorRepository.findAll().iterator();
		while(iteratorEntrenadors.hasNext()) {
			Entrenador actual = iteratorEntrenadors.next();
			if (club != null && club.equals(actual.getClub())) {
				listaEntrenadors.add(actual);
			}

		}
		return listaEntrenadors;
	}
	
	/*
     * Funcion para guardar una lista de entrenadores con persistencia
     * Parametros:
     * 				List<Entrenador> entrenadores : la lista de los entrenadores que guardaremos con persistencia
     * Retorno:
     * 				ninguno
     * */
	public void saveList(List<Entrenador> entrenadores) throws InscripcionException {
		//EquipoService equipoServ = new EquipoService();
		for (Entrenador aGuardar : entrenadores) {
			//System.out.println(aGuardar.getClub());
			Equipo equipo = objEquipo(aGuardar.getClub());
			if (equipo == null) {
				InscripcionException inscripcionException = new InscripcionException(//System.out.println(
						"No se ha encontrado un equipo con el nombre: " + aGuardar.getClub());
				inscripcionException.setContacto(Contacto.INSCRIPCION);
				throw inscripcionException;
				//continue; 
			}
			if(findEntrenadoresByClub(equipo.getNombre()).size()!=0){
				InscripcionException inscripcionException = new InscripcionException(
						"No se pudo agregar el entrenador, el club ya cuenta con un entrenador: "+equipo.getNombre());
				inscripcionException.setContacto(Contacto.INSCRIPCION);
				throw inscripcionException;
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
			if (!aGuardar.isExJugador()) {
				InscripcionException inscripcionException =  new InscripcionException(
						"No se permite la inscripción a entrenadores que no hayan sido jugadores");
				inscripcionException.setContacto(Contacto.INSCRIPCION);
				throw inscripcionException;
			}
			if (aGuardar.getTitulosGanados() < 1) {
				InscripcionException inscripcionException =  new InscripcionException(
						"No se permite la inscripción a entrenadores con titulos ganados: " + aGuardar.getTitulosGanados());
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
		if(findByCedula(cedula) == null) {
			return true;
		}
		return false;
	}
	
	/*
     * Funcion para obtener un entrenador por numerio de cedula
     * Parametros:
     * 				int cedula : el numero de cedula del entrenador que queremos
     * Retorno:
     * 				Entrenador actual : el entrenador que coincide con el numero de cedula
     * 				null : si no se encontro ningun entrenador con dicho numero de cedula
     * */
	public Entrenador findByCedula(int cedula) {
		Iterator<Entrenador> iteratorEntrenadors = entrenadorRepository.findAll().iterator();
		while(iteratorEntrenadors.hasNext()) {
			Entrenador actual = iteratorEntrenadors.next();
			if (cedula== actual.getNumeroCedula()) {
				return actual;
			}
		}
		return null;
	}
	
	/*
     * Funcion para eliminar un entrenador de la persistencia utilizando su numero de cedula
     * Parametros:
     * 				int cedula : el numero de cedula del entrenador que queremos eliminar
     * Retorno:
     * 				ninguno
     * */
	public void deleteByCedula(int cedula) throws InscripcionException {
		Empleado empleado = findByCedula(cedula);
		if(empleado == null) {
			InscripcionException inscripcionException = new InscripcionException(//System.out.println(
					"No existe un entrenador con numero de cedula: "+cedula);
			inscripcionException.setContacto(Contacto.INSCRIPCION);
			throw inscripcionException;
		}
		Equipo equipo = objEquipo(empleado.getClub());
		long id = empleado.getId();
		entrenadorRepository.delete(id);
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
     * Funcion para transferir/cambiar el club del entrenador
     * Parametros:
     * 				int cedula : el numero de cedula del entrenador que queremos modificar
     * 				String nuevoClub : el nombre del club al cual sera transferido
     * Retorno:
     * 				ninguno
     * */
	public void transferir(int cedula, String nuevoClub) throws InscripcionException {
		Entrenador entrenador = findByCedula(cedula);
		if(entrenador != null) {
			if(objEquipo(nuevoClub)!=null) {
				entrenador.setClub(nuevoClub);
				save(entrenador);
			}else {
				InscripcionException inscripcionException = new InscripcionException(//System.out.println(
						"No existe el club: "+nuevoClub);
				inscripcionException.setContacto(Contacto.INSCRIPCION);
				throw inscripcionException;
			}
		}else {
			InscripcionException inscripcionException = new InscripcionException(//System.out.println(
					"No existe el entrenador con numero de cedula: "+cedula);
			inscripcionException.setContacto(Contacto.INSCRIPCION);
			throw inscripcionException;
		}
		
	}
	
	/*
     * Funcion para cambiar el cargo del entrenador
     * Parametros:
     * 				int cedula : el numero de cedula del entrenador que queremos modificar
     * 				String nuevoCargob : el nuevo cargo del entrenador
     * Retorno:
     * 				ninguno
     * */
	public void cambiarCargo(int cedula, String nuevoCargo) throws InscripcionException {
		Entrenador entrenador = findByCedula(cedula);
		if(entrenador != null) {
			entrenador.setCargo(nuevoCargo);
			save(entrenador);
		}else {
			InscripcionException inscripcionException = new InscripcionException(//System.out.println(
					"No existe el entrenador con numero de cedula: "+cedula);
			inscripcionException.setContacto(Contacto.INSCRIPCION);
			throw inscripcionException;
		}
		
	}
	
	/*
     * Funcion para cambiar el salario del entrenador
     * Parametros:
     * 				int cedula : el numero de cedula del entrenador que queremos modificar
     * 				int nuevoSalario : el nuevo salario del entrenador
     * Retorno:
     * 				ninguno
     * */
	public void cambiarSalario(int cedula, int nuevoSalario) throws InscripcionException {
		Entrenador entrenador = findByCedula(cedula);
		if(entrenador != null) {
			entrenador.setSalario(nuevoSalario);
			save(entrenador);
		}else {
			InscripcionException inscripcionException = new InscripcionException(//System.out.println(
					"No existe el entrenador con numero de cedula: "+cedula);
			inscripcionException.setContacto(Contacto.INSCRIPCION);
			throw inscripcionException;
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
	
	
	

	
	public void saveBatch(List<Entrenador> entrenadores) throws InscripcionException {
		for (Entrenador aGuardar : entrenadores) {
			if (!aGuardar.isExJugador()) {
				InscripcionException inscripcionException =  new InscripcionException(
						"No se permite la inscripción a entrenadores que no hayan sido entrenadores");
				inscripcionException.setContacto(Contacto.INSCRIPCION);
				throw inscripcionException;
			}
			if (aGuardar.getTitulosGanados() < 1) {
				InscripcionException inscripcionException =  new InscripcionException(
						"No se permite la inscripción a entrenadores con titulos ganados: " + aGuardar.getTitulosGanados());
				inscripcionException.setContacto(Contacto.INSCRIPCION);
				throw inscripcionException;
			}
			if(objEquipo(aGuardar.getClub()) == null) {
				InscripcionException inscripcionException =  new InscripcionException(
						"No existe un equipo con el nombre: " + aGuardar.getClub());
				inscripcionException.setContacto(Contacto.INSCRIPCION);
				throw inscripcionException;
			}
		
			save(aGuardar);
		}
	}
	

}
