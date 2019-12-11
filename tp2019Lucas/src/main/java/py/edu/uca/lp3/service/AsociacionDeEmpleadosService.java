package py.edu.uca.lp3.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.edu.uca.lp3.constants.Contacto;
import py.edu.uca.lp3.domain.AsociacionDeEmpleados;
import py.edu.uca.lp3.domain.Empleado;
import py.edu.uca.lp3.domain.Equipo;
import py.edu.uca.lp3.repository.AsociacionDeEmpleadosRepository;
import py.edu.uca.lp3.repository.EmpleadoRepository;
import py.edu.uca.lp3.repository.EquipoRepository;
import py.edu.uca.lp3exceptions.InscripcionException;

@Service
public class AsociacionDeEmpleadosService {

	@Autowired
	private AsociacionDeEmpleadosRepository miembroRepository;
	
	public AsociacionDeEmpleados findById(Long id) {
		AsociacionDeEmpleados miembro = miembroRepository.findOne(id);
		return miembro;
	}

	public List<AsociacionDeEmpleados> findAll() {
		List<AsociacionDeEmpleados> miembros = new ArrayList<>();
		Iterator<AsociacionDeEmpleados> iteratorMiembros = miembroRepository.findAll().iterator();
		while(iteratorMiembros.hasNext()) {
			miembros.add(iteratorMiembros.next());
		}
		return miembros;
	}

	public void save(AsociacionDeEmpleados miembro) {
		miembroRepository.save(miembro);
	}

	public void delete(Long id) {
		miembroRepository.delete(id);
	}
	
	
	/*
     * Funcion para guardar una lista de empleados con persistencia
     * Parametros:
     * 				List<Empleado> empleados : la lista de los empleados que guardaremos como miembros de la asociacion con persistencia
     * Retorno:
     * 				ninguno
     * */
	public void saveList(List<Integer> empleados) throws InscripcionException {
		for (int aGuardar : empleados) {
			if(aGuardar<0) {
				InscripcionException inscripcionException = new InscripcionException(//System.out.println(
						"Numero de cedula invalido: "+aGuardar);
				inscripcionException.setContacto(Contacto.INSCRIPCION);
				throw inscripcionException;
			}else if(numeroDeCedulaDisponible(aGuardar)) {
				InscripcionException inscripcionException = new InscripcionException(//System.out.println(
						"No existe una persona con el numero de cedula: " + aGuardar);
				inscripcionException.setContacto(Contacto.INSCRIPCION);
				throw inscripcionException;
			}
			
			Empleado empleado = findByCedula(aGuardar);
			AsociacionDeEmpleados empleadoMiembro = new AsociacionDeEmpleados();
			empleadoMiembro.setApellidoDelMiembro(empleado.getApellido());
			empleadoMiembro.setNombreDelMiembro(empleado.getNombre());
			empleadoMiembro.setNumeroCedulaMiembro(empleado.getNumeroCedula());
			empleadoMiembro.setClubDelMiembro(empleado.getClub());
			save(empleadoMiembro);
			
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
	
	@Resource
	private EmpleadoRepository empleadoRepository;
	
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

}
