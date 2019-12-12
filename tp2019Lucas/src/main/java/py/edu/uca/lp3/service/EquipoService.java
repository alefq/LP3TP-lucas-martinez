package py.edu.uca.lp3.service;

import java.time.Year;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.hibernate.annotations.Source;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.edu.uca.lp3.constants.Contacto;
import py.edu.uca.lp3.domain.Empleado;
import py.edu.uca.lp3.domain.Equipo;
import py.edu.uca.lp3.domain.Ong;
import py.edu.uca.lp3.repository.AsociacionDeEmpleadosRepository;
import py.edu.uca.lp3.repository.EmpleadoRepository;
import py.edu.uca.lp3.repository.EquipoRepository;
import py.edu.uca.lp3.repository.OngRepository;
import py.edu.uca.lp3exceptions.InscripcionException;

@Service
public class EquipoService {
	@Autowired
	private EquipoRepository equipoRepository;
	
	/*
     * Funcion para obtener un equipo en especifico
     * Parametros:
     * 				Long id : id del equipo que queremos
     * Retorno:
     * 				Equipo equipo : el equipo con id = id
     * */
	public Equipo findById(Long id) {
		Equipo equipo = equipoRepository.findOne(id);
		return equipo;
	}

	/*
     * Funcion para obtener una lista de todos los equipos
     * Parametros:
     * 				ninguno
     * Retorno:
     * 				List<Equipo> equipos : la lista de todos los equipos
     * */
	public List<Equipo> findAll() {
		List<Equipo> equipos = new ArrayList<>();
		Iterator<Equipo> iteratorEquipos = equipoRepository.findAll().iterator();
		while(iteratorEquipos.hasNext()) {
			equipos.add(iteratorEquipos.next());
		}
		return equipos;
	}

	/*
     * Funcion para guardar un equipo con persistencia
     * Parametros:
     * 				Equipo equipo : el equipo que guardaremos con persistencia
     * Retorno:
     * 				ninguno
     * */
	public void save(Equipo equipo) {
		equipoRepository.save(equipo);
	}

	/*
     * Funcion para eliminar un equipo con un id especifico y todos sus empleados de la persistencia
     * Parametros:
     * 				Long id : el id del equipo que queremos eliminar
     * Retorno:
     * 				ninguno
     * */
	public void delete(Long id) {
		Equipo equipoBorrar = equipoRepository.findOne(id);
		ArrayList<Empleado> empleadosDelEquipoBorrar = findEmpleadosByClub(equipoBorrar.getNombre());
		for(Empleado empleado:empleadosDelEquipoBorrar) {
			empleadoRepository.delete(empleado.getId());
			miembroRepository.delete(empleado.getId());
		}
		equipoRepository.delete(id);
	}
	
	
	@Source
	EmpleadoRepository empleadoRepository;
	
	@Source
	AsociacionDeEmpleadosRepository miembroRepository;
	/*
     * Funcion para obtener un arraylist con todos los empleados de un club determinado
     * Parametros:
     * 				String club : el club del cual queremos obtener los empleados
     * Retorno:
     * 				ArrayList<Empleado> listaEmpleados: un arraylist con los empleados del club
     * */
	public ArrayList<Empleado> findEmpleadosByClub(String club) {
		ArrayList<Empleado> listaEmpleados = new ArrayList<Empleado>();
		Iterator<Empleado> iteratorEmpleados = empleadoRepository.findAll().iterator();
		while(iteratorEmpleados.hasNext()) {
			Empleado actual = iteratorEmpleados.next();
			if (club != null && club.equals(actual.getClub())) {
				listaEmpleados.add(actual);
			}

		}
		return listaEmpleados;
	}

	
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
     * Funcion para guardar una lista de equipos con persistencia
     * Parametros:
     * 				List<Equipo> equipos : la lista de los equipos que guardaremos con persistencia
     * Retorno:
     * 				ninguno
     * */
	public void saveList(List<Equipo> equipos) throws InscripcionException {
		for (Equipo aGuardar : equipos) {
			if(objEquipo(aGuardar.getNombre())!=null) {
				InscripcionException inscripcionException = new InscripcionException(//System.out.println(
						"No se pudo crear el equipo, el equipo ya existe: " + aGuardar.getNombre());
				inscripcionException.setContacto(Contacto.INSCRIPCION);
				throw inscripcionException;
			}
			if(!verificarONG(aGuardar.getOng())) {
				InscripcionException inscripcionException = new InscripcionException(//System.out.println(
						"La institucion no existe o lleva menos de 5 anhos en el mercado: " + aGuardar.getNombre());
				inscripcionException.setContacto(Contacto.INSCRIPCION);
				throw inscripcionException;
			}
			save(aGuardar);
		}
	}
	
	/*
     * Funcion para editar una lista de equipos con persistencia
     * Parametros:
     * 				List<Equipo> equipos : la lista de los equipos que editaremos
     * Retorno:
     * 				ninguno
     * */
	public void editList(List<Equipo> equipos) throws InscripcionException {
		for (Equipo aGuardar : equipos) {
			if(objEquipo(aGuardar.getNombre())==null) {
				InscripcionException inscripcionException = new InscripcionException(//System.out.println(
						"No se pudo editar el equipo, el equipo no existe: " + aGuardar.getNombre());
				inscripcionException.setContacto(Contacto.INSCRIPCION);
				throw inscripcionException;
			}
			if(!verificarONG(aGuardar.getOng())) {
				InscripcionException inscripcionException = new InscripcionException(//System.out.println(
						"La institucion no existe o lleva menos de 5 anhos en el mercado: " + aGuardar.getNombre());
				inscripcionException.setContacto(Contacto.INSCRIPCION);
				throw inscripcionException;
			}
			Equipo equipoExistente = objEquipo(aGuardar.getNombre());
			equipoExistente.setElEquipoPagoImpuestoDeLujo(aGuardar.isCalificaParaInternacional());
			equipoExistente.setCalificaParaInternacional(aGuardar.isCalificaParaInternacional());
			equipoExistente.setTecnologias(aGuardar.getTecnologias());
			equipoExistente.setViajes(aGuardar.getViajes());
			save(equipoExistente);
		}
	}
	
	/*
	 * Funcion para verificar si las institucioones nombradas pertenecen a la ONG
	 * Parametros:
	 * 				List<String> onglist : lista con los nombres de ;as instituciones que queremos verrificar
	 * Retorno:
	 * 				boolean : true si todas pertenecen, false si no
	 * */
	public boolean verificarONG(List<String> ongList) {
		for(String ongNombre:ongList) {System.out.println(Year.now().getValue());
			if(objOng(ongNombre) == null) {
				return false;
			}
			if((int)(Year.now().getValue()) - objOng(ongNombre).getAnhoFundacion() < 5) {
				return false;
			}
		}
		return true;
	}
	
	@Resource
	private OngRepository ongRepository;
	
	/*
     * Funcion para obtener el objeto de una organizacion ONG por su nombre
     * Parametros:
     * 				String nombre : el nombre la organizacion que queremos
     * Retorno:
     * 				Ong actual: la organizacion que coincide con el nombre que le pasamos
     * 				null : si no encuentra ninguna organizacion con dicho nombre
     * */
	public Ong objOng(String nombre) {
		Iterator<Ong> iteratorOng = ongRepository.findAll().iterator();
		while(iteratorOng.hasNext()) {
			Ong actual = iteratorOng.next();
			if (nombre.equals(actual.getNombre())) {
				return actual;
			}
		}
		return null;
	}
	
	/*
	 * Funcion para poner en True o False si el equipo pago el impuesto de lujo
	 * Parametros:
	 * 				List<Equipo> equipos : lista de los equipo squevamos a modificar
	 * Retorno:
	 * 				ninguno
	 * */
	public void pagoImpuestoLujoList(List<Equipo> equipos) throws InscripcionException {
		for (Equipo equipoEdit : equipos) {
			Equipo equipoExistente = objEquipo(equipoEdit.getNombre());
			if(equipoExistente==null) {
				InscripcionException inscripcionException = new InscripcionException(
						"No puede modificar el equipo porque este no existe " + equipoEdit.getNombre());
				inscripcionException.setContacto(Contacto.INSCRIPCION);
				throw inscripcionException;
			}
			equipoExistente.setElEquipoPagoImpuestoDeLujo(equipoEdit.isElEquipoPagoImpuestoDeLujo());
		}
	}
	
	
	/*
	 * Funcion para poner en True o False si el equipo pago el impuesto de lujo
	 * Parametros:
	 * 				List<Equipo> equipos : lista de los equipo squevamos a modificar
	 * Retorno:
	 * 				ninguno
	 * */
	public void clasificaInternacionalList(List<Equipo> equipos) throws InscripcionException {
		for (Equipo equipoEdit : equipos) {
			Equipo equipoExistente = objEquipo(equipoEdit.getNombre());
			if(equipoExistente==null) {
				InscripcionException inscripcionException = new InscripcionException(
						"No puede modificar el equipo porque este no existe " + equipoEdit.getNombre());
				inscripcionException.setContacto(Contacto.INSCRIPCION);
				throw inscripcionException;
			}
			equipoExistente.setElEquipoPagoImpuestoDeLujo(equipoEdit.isElEquipoPagoImpuestoDeLujo());
		}
	}
	


}
