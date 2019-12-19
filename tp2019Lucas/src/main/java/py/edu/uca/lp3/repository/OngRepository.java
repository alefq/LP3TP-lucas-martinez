package py.edu.uca.lp3.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import py.edu.uca.lp3.domain.Ong;

//interface para almacenar y recuperar los datos sobre las instituciones aprobadas por la ong
public interface OngRepository extends PagingAndSortingRepository<Ong, Long> {

}
