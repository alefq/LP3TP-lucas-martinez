package py.edu.uca.lp3.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import java.lang.Integer;
import java.lang.Long;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.edu.uca.lp3.domain.Empleado;
import py.edu.uca.lp3.domain.Equipo;
import py.edu.uca.lp3.repository.EmpleadoRepository;
import py.edu.uca.lp3.repository.EquipoRepository;
import py.edu.uca.lp3exceptions.InscripcionException;

import py.edu.uca.lp3.constants.Contacto;

@Service
public class EmpleadoService {
	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	
	/*
     * Funcion para obtener un empleado en especifico
     * Parametros:
     * 				Long id : id del empleado que qeremos
     * Retorno:
     * 				Empleado empleado : el empleado con id = id
     * */
	public Empleado findById(Long id) {
		Empleado empleado = empleadoRepository.findOne(id);
		return empleado;
	}

	/*
     * Funcion para obtener una lista de todos los empleados
     * Parametros:
     * 				ninguno
     * Retorno:
     * 				List<Empleado> empleados : la lista de todos los empleados
     * */
	public List<Empleado> findAll() {
		List<Empleado> empleados = new ArrayList<>();
		Iterator<Empleado> iteratorEmpleados = empleadoRepository.findAll().iterator();
		while(iteratorEmpleados.hasNext()) {
			empleados.add(iteratorEmpleados.next());
		}
		return empleados;
	}

	/*
     * Funcion para guardar un empleado con persistencia
     * Parametros:
     * 				Empleado empleado : el empleado que guardaremos con persistencia
     * Retorno:
     * 				ninguno
     * */
	public void save(Empleado empleado) {
		empleadoRepository.save(empleado);
	}

	/*
     * Funcion para eliminar un empleado con un id especifico de la persistencia
     * Parametros:
     * 				Long id : el ide del empleado que queremos eliminar
     * Retorno:
     * 				ninguno
     * */
	public void delete(Long id) {
		empleadoRepository.delete(id);
	}
	
	
	/*
     * Funcion para obtener un arraylist con todos los empleados de un club determinado
     * Parametros:
     * 				String club : el club del cual queremos obtener los empleados
     * Retorno:
     * 				ArrayList<Empleado> listaEmpleados: un arraylist con los empleados del club
     * */
	public ArrayList<Empleado> findEmpleadosByClub(String club) {
		ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>();
		/*for (Empleado empleadoIteracion : empleadosExampleList) {
			if (club != null && club.equals(empleadoIteracion.getClub())) {
				listaEmpleados.add(empleadoIteracion);
			}
		}*/
		Iterator<Empleado> iteratorEmpleados = empleadoRepository.findAll().iterator();
		while(iteratorEmpleados.hasNext()) {
			Empleado actual = iteratorEmpleados.next();
			if (club != null && club.equals(actual.getClub())) {
				listaEmpleados.add(actual);
			}

		}
		return listaEmpleados;
	}
	
	/*
     * Funcion para guardar una lista de empleados con persistencia
     * Parametros:
     * 				List<Empleado> empleados : la lista de losempleados que guardaremos con persistencia
     * Retorno:
     * 				ninguno
     * */
	public void saveList(List<Empleado> empleados) throws InscripcionException {
		//EquipoService equipoServ = new EquipoService();
		for (Empleado aGuardar : empleados) {
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
     * Funcion para obtener un empleado por numerio de cedula
     * Parametros:
     * 				int cedula : el numero de cedula del empleado que queremos
     * Retorno:
     * 				Empleado actual : el empleado que coincide con el numero de cedula
     * 				null : si no se encontro ningun empleado con dicho numero de cedula
     * */
	public Empleado findByCedula(int cedula) {
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
     * Funcion para eliminar un empleado de la persistencia utilizando su numero de cedula
     * Parametros:
     * 				int cedula : el numero de cedula del empleado que queremos eliminar
     * Retorno:
     * 				ninguno
     * */
	public void deleteByCedula(int cedula) throws InscripcionException {
		Empleado empleado = findByCedula(cedula);
		if(empleado == null) {
			InscripcionException inscripcionException = new InscripcionException(//System.out.println(
					"No un empleado con numero de cedula: "+cedula);
			inscripcionException.setContacto(Contacto.INSCRIPCION);
			throw inscripcionException;
		}
		Equipo equipo = objEquipo(empleado.getClub());
		long id = empleado.getId();
		empleadoRepository.delete(id);
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
     * Funcion para transferir/cambiar el club del empleado
     * Parametros:
     * 				int cedula : el numero de cedula del empleado que queremos modificar
     * 				String nuevoClub : el nombre del club al cual sera transferido
     * Retorno:
     * 				ninguno
     * */
	public void transferir(int cedula, String nuevoClub) throws InscripcionException {
		Empleado empleado = findByCedula(cedula);
		if(empleado != null) {
			if(objEquipo(nuevoClub)!=null) {
				empleado.setClub(nuevoClub);
				save(empleado);
			}else {
				InscripcionException inscripcionException = new InscripcionException(//System.out.println(
						"No existe el club: "+nuevoClub);
				inscripcionException.setContacto(Contacto.INSCRIPCION);
				throw inscripcionException;
			}
		}else {
			InscripcionException inscripcionException = new InscripcionException(//System.out.println(
					"No existe el empleado con numero de cedula: "+cedula);
			inscripcionException.setContacto(Contacto.INSCRIPCION);
			throw inscripcionException;
		}
		
	}
	
	/*
     * Funcion para cambiar el cargo del empleado
     * Parametros:
     * 				int cedula : el numero de cedula del empleado que queremos modificar
     * 				String nuevoCargob : el nuevo cargo del empleado
     * Retorno:
     * 				ninguno
     * */
	public void cambiarCargo(int cedula, String nuevoCargo) throws InscripcionException {
		Empleado empleado = findByCedula(cedula);
		if(empleado != null) {
			empleado.setCargo(nuevoCargo);
			save(empleado);
		}else {
			InscripcionException inscripcionException = new InscripcionException(//System.out.println(
					"No existe el empleado con numero de cedula: "+cedula);
			inscripcionException.setContacto(Contacto.INSCRIPCION);
			throw inscripcionException;
		}
		
	}
	
	/*
     * Funcion para cambiar el salario del empleado
     * Parametros:
     * 				int cedula : el numero de cedula del empleado que queremos modificar
     * 				int nuevoSalario : el nuevo salario del empleado
     * Retorno:
     * 				ninguno
     * */
	public void cambiarSalario(int cedula, int nuevoSalario) throws InscripcionException {
		Empleado empleado = findByCedula(cedula);
		if(empleado != null) {
			empleado.setSalario(nuevoSalario);
			save(empleado);
		}else {
			InscripcionException inscripcionException = new InscripcionException(//System.out.println(
					"No existe el empleado con numero de cedula: "+cedula);
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
	
	

}
