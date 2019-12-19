package py.edu.uca.lp3.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import py.edu.uca.lp3.domain.Persona;

//interface para almacenar y recuperar los datos sobre las personas
public interface PersonaRepository extends PagingAndSortingRepository<Persona, Long> {

}
