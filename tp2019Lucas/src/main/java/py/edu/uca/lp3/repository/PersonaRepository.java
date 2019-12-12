package py.edu.uca.lp3.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import py.edu.uca.lp3.domain.Persona;

public interface PersonaRepository extends PagingAndSortingRepository<Persona, Long> {

}
