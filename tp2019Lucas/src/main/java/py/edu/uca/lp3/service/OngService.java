package py.edu.uca.lp3.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import py.edu.uca.lp3.domain.Ong;
import py.edu.uca.lp3.repository.OngRepository;
import py.edu.uca.lp3exceptions.InscripcionException;

@Service
public class OngService {

	@Autowired
	private OngRepository ongRepository;

	public Ong findById(Long id) {
		Ong ong = ongRepository.findOne(id);
		return ong;
	}

	public void saveList(List<Ong> ongs) throws InscripcionException {
		for (Ong aGuardar : ongs) {
			save(aGuardar);
		}
	}

	public List<Ong> findAll() {
		List<Ong> ongs = new ArrayList<>();
		Iterator<Ong> iteratorOngs = ongRepository.findAll().iterator();
		while (iteratorOngs.hasNext()) {
			ongs.add(iteratorOngs.next());
		}
		return ongs;
	}

	public void save(Ong ong) {
		ongRepository.save(ong);
	}

	public void delete(Long id) {
		ongRepository.delete(id);
	}

}
