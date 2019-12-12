package py.edu.uca.lp3.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.edu.uca.lp3.constants.Contacto;
import py.edu.uca.lp3.domain.Equipo;
import py.edu.uca.lp3.domain.Torneo;
import py.edu.uca.lp3.repository.EquipoRepository;
import py.edu.uca.lp3.repository.TorneoRepository;
import py.edu.uca.lp3exceptions.InscripcionException;

@Service
public class TorneoService {
	@Autowired
	private TorneoRepository torneoRepository;
	
	/*
     * Funcion para obtener un torneo en especifico
     * Parametros:
     * 				Long id : id del torneo que queremos
     * Retorno:
     * 				Torneo torneo : el torneo con id = id
     * */
	public Torneo findById(Long id) {
		Torneo torneo = torneoRepository.findOne(id);
		return torneo;
	}

	/*
     * Funcion para obtener una lista de todos los torneos
     * Parametros:
     * 				ninguno
     * Retorno:
     * 				List<Torneo> torneos : la lista de todos los torneos
     * */
	public List<Torneo> findAll() {
		List<Torneo> torneos = new ArrayList<>();
		Iterator<Torneo> iteratorTorneos = torneoRepository.findAll().iterator();
		while(iteratorTorneos.hasNext()) {
			torneos.add(iteratorTorneos.next());
		}
		return torneos;
	}

	/*
     * Funcion para guardar un torneo con persistencia
     * Parametros:
     * 				Torneo torneo : el torneo que guardaremos con persistencia
     * Retorno:
     * 				ninguno
     * */
	public void save(Torneo torneo) {
		torneoRepository.save(torneo);
	}

	/*
     * Funcion para eliminar un torneo con un id especifico de la persistencia
     * Parametros:
     * 				Long id : el ide del torneo que queremos eliminar
     * Retorno:
     * 				ninguno
     * */
	public void delete(Long id) {
		torneoRepository.delete(id);
	}

	/*
     * Funcion para guardar una lista de torneos con persistencia
     * Parametros:
     * 				List<Torneo> torneos : la lista de lostorneos que guardaremos con persistencia
     * Retorno:
     * 				ninguno
     * */
	public void saveList(List<Torneo> torneos) throws InscripcionException {
		for (Torneo aGuardar : torneos) {
			if (isNombreDisponible(aGuardar.getNombreDelTorneo())) {
				for(String participante:aGuardar.getParticipantes()) {
					if(objEquipo(participante) == null) {
						InscripcionException inscripcionException = new InscripcionException(
								"Para el torneo: "+aGuardar.getNombreDelTorneo()+", el equipo: "+participante+" no pudo ser inscripto porque no existe. Corrija la lista de particioantes y vuelva a intentarlo.");
						inscripcionException.setContacto(Contacto.TORNEO);
						throw inscripcionException;
					}
					if(aGuardar.getTipo().equals("Internacional") && !objEquipo(participante).isCalificaParaInternacional()) {
						InscripcionException inscripcionException = new InscripcionException(
								"El equipo: "+ participante +" no ha calificado para el torneo internacional: "+aGuardar.getNombreDelTorneo());
						inscripcionException.setContacto(Contacto.TORNEOINTERNACIONAL);
						throw inscripcionException;
					}
				}
				save(aGuardar);
			}else {
				InscripcionException inscripcionException = new InscripcionException(
						"No se puede crear el torneo, el torneo: "+aGuardar.getNombreDelTorneo()+" ya existe");
				inscripcionException.setContacto(Contacto.TORNEO);
				throw inscripcionException;
			}
			
		}
	}
	
	/*
     * Funcion para editar una lista de torneos con persistencia
     * Parametros:
     * 				List<Torneo> torneos : la lista de lo storneos que queremos editar con persistencia
     * Retorno:
     * 				ninguno
     * */
	public void editList(List<Torneo> torneos) throws InscripcionException {
		for (Torneo aGuardar : torneos) {
			if (!isNombreDisponible(aGuardar.getNombreDelTorneo())) {
				for(String participante:aGuardar.getParticipantes()) {
					if(objEquipo(participante) == null) {
						InscripcionException inscripcionException = new InscripcionException(
								"Para el torneo: "+aGuardar.getNombreDelTorneo()+", el equipo: "+participante+" no pudo ser inscripto porque no existe. Corrija la lista de particioantes y vuelva a intentarlo.");
						inscripcionException.setContacto(Contacto.TORNEO);
						throw inscripcionException;
					}
					if(aGuardar.getTipo().equals("Internacional") && !objEquipo(participante).isCalificaParaInternacional()) {
						InscripcionException inscripcionException = new InscripcionException(
								"El equipo: "+ participante +" no ha calificado para el torneo internacional: "+aGuardar.getNombreDelTorneo());
						inscripcionException.setContacto(Contacto.TORNEOINTERNACIONAL);
						throw inscripcionException;
					}
				}
				Torneo torneoExistente = findByName(aGuardar.getNombreDelTorneo());
				torneoExistente.setParticipantes(aGuardar.getParticipantes());
				save(torneoExistente);
			}else {
				InscripcionException inscripcionException = new InscripcionException(
						"No se puede modificar el torneo: "+aGuardar.getNombreDelTorneo()+" porque no existe");
				inscripcionException.setContacto(Contacto.TORNEO);
				throw inscripcionException;
			}
			
		}
	}
	
	/*
     * Funcion para obtener un torneo por su nombre
     * Parametros:
     * 				String nombre : el nombre del torneo que queremos
     * Retorno:
     * 				Torneo actual : el torneo que coincide con el numero de cedula
     * 				null : si no se encontro ningun torneo con dicho numero de cedula
     * */
	public Torneo findByName(String nombre) {
		Iterator<Torneo> iteratorTorneos = torneoRepository.findAll().iterator();
		while(iteratorTorneos.hasNext()) {
			Torneo actual = iteratorTorneos.next();
			if(nombre.equals(actual.getNombreDelTorneo())) {
				return actual;
			}
		}
		return null;
	}

	/*
     * Funcion para verificar si un nombre de torneo especifico se encuentra disponible
     * Parametros:
     * 				String nombreDelTorneo : el nombre del torneo que queremos verificar
     * Retorno:
     * 				boolean : true si el numero de cedula se encuentra disponible, false si no
     * */
	private boolean isNombreDisponible(String nombreDelTorneo) {
		if(findByName(nombreDelTorneo) == null) {
			return true;
		}
		return false;
	}
	
	
	@Resource
	private EquipoRepository equipoRepository;
	
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
	
	
	public String findPromedioSalarioEquipos(String nombreTorneo) {
		long sumaSalarios = 0;
		int cantidadDeEquipos;
		Torneo actual = findByName(nombreTorneo);
		if(actual == null) {
			return ("El torneo "+nombreTorneo+" no existe.");
		}
		ArrayList<String> participantes = actual.getParticipantes();
		cantidadDeEquipos = participantes.size();
		if(cantidadDeEquipos < 1) {
			return ("No hay participantes en el torneo: "+nombreTorneo);
		}
		Equipo aEvaluar;
		for(String equipo:participantes) {
			aEvaluar = objEquipo(equipo);
			sumaSalarios += aEvaluar.getSalarioClub();
		}
		
		return ("El promedio de salario por equipo es: " +Long.divideUnsigned(sumaSalarios, cantidadDeEquipos));
	}
	


}
