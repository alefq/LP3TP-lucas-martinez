package py.edu.uca.lp3.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import py.edu.uca.lp3.domain.Director;

//interface para almacenar y recuperar los datos sobre los directores de los clubes
public interface DirectorRepository extends PagingAndSortingRepository<Director, Long> {

}
