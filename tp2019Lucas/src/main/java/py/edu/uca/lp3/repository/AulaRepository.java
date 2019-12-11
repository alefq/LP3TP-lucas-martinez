package py.edu.uca.lp3.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import py.edu.uca.lp3.domain.Aula;

@RepositoryRestResource(collectionResourceRel = "aula2", path = "aula2")
public interface AulaRepository extends PagingAndSortingRepository<Aula, Long> {

	List<Aula> findByDescripcion(String descipcion);

}
