package py.edu.uca.lp3.repository;

//interface para almacenar y recuperar los datos sobre la asociacin de empleados
import org.springframework.data.repository.PagingAndSortingRepository;

import py.edu.uca.lp3.domain.AsociacionDeEmpleados;

public interface AsociacionDeEmpleadosRepository extends PagingAndSortingRepository<AsociacionDeEmpleados, Long> {

}
