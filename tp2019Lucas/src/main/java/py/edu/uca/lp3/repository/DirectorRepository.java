package py.edu.uca.lp3.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import py.edu.uca.lp3.domain.Director;

public interface DirectorRepository extends PagingAndSortingRepository<Director, Long> {

}
