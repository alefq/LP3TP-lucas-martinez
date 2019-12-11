package py.edu.uca.lp3.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.edu.uca.lp3.constants.Contacto;
import py.edu.uca.lp3.domain.Director;
import py.edu.uca.lp3.domain.Empleado;
import py.edu.uca.lp3.domain.Director;
import py.edu.uca.lp3.domain.Equipo;
import py.edu.uca.lp3.domain.Director;
import py.edu.uca.lp3.domain.Director;
import py.edu.uca.lp3.repository.DirectorRepository;
import py.edu.uca.lp3.repository.EquipoRepository;
import py.edu.uca.lp3exceptions.InscripcionException;

@Service
public class DirectorService {
	@Autowired
	private DirectorRepository directorRepository;
	

	/*
     * Funcion para obtener un director en especifico
     * Parametros:
     * 				Long id : id del director que qeremos
     * Retorno:
     * 				Director director : el director con id = id
     * */
	public Director findById(Long id) {
		Director director = directorRepository.findOne(id);
		return director;
	}

	/*
     * Funcion para obtener una lista de todos los directores
     * Parametros:
     * 				ninguno
     * Retorno:
     * 				List<Director> directores : la lista de todos los directores
     * */
	public List<Director> findAll() {
		List<Director> directores = new ArrayList<>();
		Iterator<Director> iteratorDirectores = directorRepository.findAll().iterator();
		while(iteratorDirectores.hasNext()) {
			directores.add(iteratorDirectores.next());
		}
		return directores;
	}

	/*
     * Funcion para guardar un director con persistencia
     * Parametros:
     * 				Director director : el director que guardaremos con persistencia
     * Retorno:
     * 				ninguno
     * */
	public void save(Director director) {
		director.setCargo("Director");
		directorRepository.save(director);
	}

	/*
     * Funcion para eliminar un director con un id especifico de la persistencia
     * Parametros:
     * 				Long id : el ide del director que queremos eliminar
     * Retorno:
     * 				ninguno
     * */
	public void delete(Long id) {
		directorRepository.delete(id);
	}
	
	
	/*
     * Funcion para obtener un arraylist con todos los directores de un club determinado
     * Parametros:
     * 				String club : el club del cual queremos obtener los directores
     * Retorno:
     * 				ArrayList<Director> listaDirectores: un arraylist con los directores del club
     * */
	public ArrayList<Director> findDirectoresByClub(String club) {
		ArrayList<Director> listaDirectores = new ArrayList<Director>();
		/*for (Director directorIteracion : directoresExampleList) {
			if (club != null && club.equals(directorIteracion.getClub())) {
				listaDirectores.add(directorIteracion);
			}
		}*/
		Iterator<Director> iteratorDirectores = directorRepository.findAll().iterator();
		while(iteratorDirectores.hasNext()) {
			if (club != null && club.equals(iteratorDirectores.next().getClub())) {
				listaDirectores.add(iteratorDirectores.next());
			}

		}
		return listaDirectores;
	}
	
	/*
     * Funcion para guardar una lista de directores con persistencia
     * Parametros:
     * 				List<Director> directores : la lista de losdirectores que guardaremos con persistencia
     * Retorno:
     * 				ninguno
     * */
	public void saveList(List<Director> directores) throws InscripcionException {
		//EquipoService equipoServ = new EquipoService();
		for (Director aGuardar : directores) {
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
     * Funcion para obtener un director por numerio de cedula
     * Parametros:
     * 				int cedula : el numero de cedula del director que queremos
     * Retorno:
     * 				Director actual : el director que coincide con el numero de cedula
     * 				null : si no se encontro ningun director con dicho numero de cedula
     * */
	public Director findByCedula(int cedula) {
		Iterator<Director> iteratorDirectores = directorRepository.findAll().iterator();
		while(iteratorDirectores.hasNext()) {
			Director actual = iteratorDirectores.next();
			if (cedula== actual.getNumeroCedula()) {
				return actual;
			}
		}
		return null;
	}
	
	/*
     * Funcion para eliminar un director de la persistencia utilizando su numero de cedula
     * Parametros:
     * 				int cedula : el numero de cedula del director que queremos eliminar
     * Retorno:
     * 				ninguno
     * */
	public void deleteByCedula(int cedula) throws InscripcionException {
		Empleado empleado = findByCedula(cedula);
		if(empleado == null) {
			InscripcionException inscripcionException = new InscripcionException(//System.out.println(
					"No existe un director con numero de cedula: "+cedula);
			inscripcionException.setContacto(Contacto.INSCRIPCION);
			throw inscripcionException;
		}
		Equipo equipo = objEquipo(empleado.getClub());
		long id = empleado.getId();
		directorRepository.delete(id);
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
     * Funcion para transferir/cambiar el club del director
     * Parametros:
     * 				int cedula : el numero de cedula del director que queremos modificar
     * 				String nuevoClub : el nombre del club al cual sera transferido
     * Retorno:
     * 				ninguno
     * */
	public void transferir(int cedula, String nuevoClub) throws InscripcionException {
		Director director = findByCedula(cedula);
		if(director != null) {
			if(objEquipo(nuevoClub)!=null) {
				director.setClub(nuevoClub);
				save(director);
			}else {
				InscripcionException inscripcionException = new InscripcionException(//System.out.println(
						"No existe el club: "+nuevoClub);
				inscripcionException.setContacto(Contacto.INSCRIPCION);
				throw inscripcionException;
			}
		}else {
			InscripcionException inscripcionException = new InscripcionException(//System.out.println(
					"No existe el director con numero de cedula: "+cedula);
			inscripcionException.setContacto(Contacto.INSCRIPCION);
			throw inscripcionException;
		}
		
	}
	
	/*
     * Funcion para cambiar el cargo del director
     * Parametros:
     * 				int cedula : el numero de cedula del director que queremos modificar
     * 				String nuevoCargob : el nuevo cargo del director
     * Retorno:
     * 				ninguno
     * */
	public void cambiarCargo(int cedula, String nuevoCargo) throws InscripcionException {
		Director director = findByCedula(cedula);
		if(director != null) {
			director.setCargo(nuevoCargo);
			save(director);
		}else {
			InscripcionException inscripcionException = new InscripcionException(//System.out.println(
					"No existe el director con numero de cedula: "+cedula);
			inscripcionException.setContacto(Contacto.INSCRIPCION);
			throw inscripcionException;
		}
		
	}
	
	/*
     * Funcion para cambiar el salario del director
     * Parametros:
     * 				int cedula : el numero de cedula del director que queremos modificar
     * 				int nuevoSalario : el nuevo salario del director
     * Retorno:
     * 				ninguno
     * */
	public void cambiarSalario(int cedula, int nuevoSalario) throws InscripcionException {
		Director director = findByCedula(cedula);
		if(director != null) {
			director.setSalario(nuevoSalario);
			save(director);
		}else {
			InscripcionException inscripcionException = new InscripcionException(//System.out.println(
					"No existe el director con numero de cedula: "+cedula);
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
	

	public ArrayList<String> getListaEquipos() {
		ArrayList<String> listaEquipos = new ArrayList<String>();
		/*for (Director directorIteracion : directoresExampleList) {
			 listaEquipos.add(directorIteracion.getClub());
		}*/
		
		Iterator<Director> iteratorDirectores = directorRepository.findAll().iterator();
		while(iteratorDirectores.hasNext()) {
			listaEquipos.add(iteratorDirectores.next().getClub());
		}
		return listaEquipos;
	}

}
