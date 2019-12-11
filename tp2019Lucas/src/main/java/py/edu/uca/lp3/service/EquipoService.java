package py.edu.uca.lp3.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.edu.uca.lp3.domain.Equipo;
import py.edu.uca.lp3.domain.Equipo;
import py.edu.uca.lp3.domain.Equipo;
import py.edu.uca.lp3.repository.EquipoRepository;

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

	public Equipo classEquipo(String club) {
		Iterator<Equipo> iteratorEquipo = equipoRepository.findAll().iterator();
		while(iteratorEquipo.hasNext()) {
			Equipo actual = iteratorEquipo.next();
			System.out.println("NOMBRE: "+actual.getNombre());
			if (club == actual.getNombre()) {
				return actual;
			}
		}
		return null;
	}
	
	


}
