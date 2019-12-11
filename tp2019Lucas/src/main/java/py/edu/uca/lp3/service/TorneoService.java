package py.edu.uca.lp3.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.edu.uca.lp3.domain.Equipo;
import py.edu.uca.lp3.domain.Torneo;
import py.edu.uca.lp3.repository.EquipoRepository;
import py.edu.uca.lp3.repository.TorneoRepository;

@Service
public class TorneoService {
	@Autowired
	private TorneoRepository torneoRepository;
	
	public Torneo findById(Long id) {
		Torneo torneo = torneoRepository.findOne(id);
		return torneo;
	}

	public List<Torneo> findAll() {
		List<Torneo> torneos = new ArrayList<>();
		Iterator<Torneo> iteratorTorneos = torneoRepository.findAll().iterator();
		while(iteratorTorneos.hasNext()) {
			torneos.add(iteratorTorneos.next());
		}
		return torneos;
	}

	public void save(Torneo torneo) {
		torneoRepository.save(torneo);
	}

	public void delete(Long id) {
		torneoRepository.delete(id);
	}

	public void saveList(List<Torneo> torneos) {
		for (Torneo aGuardar : torneos) {
			boolean participanteNoExiste = false;
			if (isNombreDisponible(aGuardar.getNombreDelTorneo())) {
				for(String participante:aGuardar.getParticipantes()) {
					if(objEquipo(participante) == null) {
						System.out.println("Para el torneo: "+aGuardar.getNombreDelTorneo()+", el equipo: "+participante+" no pudo ser inscripto porque no existe. Corrija la lista de particioantes y vuelva a intentarlo.Pasando al siguiente torneo (si existe)");
						participanteNoExiste = true;
						break;
					}
				}
				if(participanteNoExiste) {
					continue;
				}
				save(aGuardar);
			}else {
				System.out.println("No se puede crear el torneo, el torneo: "+aGuardar.getNombreDelTorneo()+" ya existe");
			}
			
		}
	}
	
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

	private boolean isNombreDisponible(String nombreDelTorneo) {
		if(findByName(nombreDelTorneo) == null) {
			return true;
		}
		return false;
	}
	
	public void editTorneoList(List <Torneo> torneos) {
		for (Torneo aGuardar : torneos) {
			Torneo elTorneo = findByName(aGuardar.getNacionalInternacional());
			if(elTorneo == null) {
				System.out.println("No puede editar el torneo: "+aGuardar.getNombreDelTorneo()+" porque ese torneo no existe");
			}else {
				boolean participanteNoExiste = false;
				for(String participante:aGuardar.getParticipantes()) {
					if(objEquipo(participante) == null) {
						System.out.println("Para el torneo: "+aGuardar.getNombreDelTorneo()+", el equipo: "+participante+" no pudo ser inscripto porque no existe. Corrija la lista de particioantes y vuelva a intentarlo.Pasando al siguiente torneo (si existe)");
						participanteNoExiste = true;
						break;
					}
				}
				if (!participanteNoExiste) {
					save(aGuardar);
					delete(elTorneo.getId());
				}
				
			}
		}
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
	


}
