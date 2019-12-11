package py.edu.uca.lp3.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import py.edu.uca.lp3.domain.Empleado;

public interface EmpleadoRepository extends PagingAndSortingRepository<Empleado, Long> {

}
