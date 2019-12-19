package py.edu.uca.lp3.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import py.edu.uca.lp3.domain.Equipo;

//interface para almacenar y recuperar los datos sobre los diferentes equipos registrados
public interface EquipoRepository extends PagingAndSortingRepository<Equipo, Long> {

}
