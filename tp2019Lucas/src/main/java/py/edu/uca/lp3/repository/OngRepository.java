package py.edu.uca.lp3.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import py.edu.uca.lp3.domain.Ong;

public interface OngRepository extends PagingAndSortingRepository<Ong, Long> {

}
