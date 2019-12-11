package py.edu.uca.lp3.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import py.edu.uca.lp3.domain.Torneo;

public interface TorneoRepository extends PagingAndSortingRepository<Torneo, Long> {

}
