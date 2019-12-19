package py.edu.uca.lp3.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import py.edu.uca.lp3.domain.Entrenador;

//interface para almacenar y recuperar los datos sobre los entrenadores de los clubes
public interface EntrenadorRepository extends PagingAndSortingRepository<Entrenador, Long> {

}
