package py.edu.uca.lp3.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.edu.uca.lp3.domain.Aula;
import py.edu.uca.lp3.repository.AulaRepository;

@Service
public class AulaServiceImpl implements py.edu.uca.lp3.service.AulaService {

	@Autowired
	private AulaRepository aulaRepository;
	
	
	public Aula findById(Long id) {
		return aulaRepository.findOne(id);
	}

	
	public List<Aula> findAll() {
		List<Aula> aulas = new ArrayList<>();
		Iterator<Aula> aulasIterator = aulaRepository.findAll().iterator();
		while(aulasIterator.hasNext()) {
			aulas.add(aulasIterator.next());
		}
		return aulas ;
	}

	
	public void save(Aula aula) {
		aulaRepository.save(aula);

	}

	public void delete(Long id) {
		// TODO Auto-generated method stub

	}

}
