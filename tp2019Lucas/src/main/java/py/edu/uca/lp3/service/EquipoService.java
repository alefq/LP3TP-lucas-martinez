package py.edu.uca.lp3.service;

import java.time.Year;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.edu.uca.lp3.constants.Contacto;
import py.edu.uca.lp3.domain.Equipo;
import py.edu.uca.lp3.domain.Ong;
import py.edu.uca.lp3.repository.EquipoRepository;
import py.edu.uca.lp3.repository.OngRepository;
import py.edu.uca.lp3exceptions.InscripcionException;

@Service
public class EquipoService {
	@Autowired
	private EquipoRepository equipoRepository;
	
	public Equipo findById(Long id) {
		Equipo equipo = equipoRepository.findOne(id);
		return equipo;
	}

	public List<Equipo> findAll() {
		List<Equipo> equipos = new ArrayList<>();
		Iterator<Equipo> iteratorEquipos = equipoRepository.findAll().iterator();
		while(iteratorEquipos.hasNext()) {
			equipos.add(iteratorEquipos.next());
		}
		return equipos;
	}

	public void save(Equipo equipo) {
		equipoRepository.save(equipo);
	}

	public void delete(Long id) {
		equipoRepository.delete(id);
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
	


}
