package py.edu.uca.lp3.service;

import java.util.List;

import py.edu.uca.lp3.domain.Aula;

public interface AulaService {

	Aula findById(Long id);

	List<Aula> findAll();

	void save(Aula institute);

	void delete(Long id);

}
