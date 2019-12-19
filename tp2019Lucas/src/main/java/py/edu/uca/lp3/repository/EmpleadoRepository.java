package py.edu.uca.lp3.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import py.edu.uca.lp3.domain.Empleado;

//interface para almacenar y recuperar los datos sobre  los empleados de los clubes
public interface EmpleadoRepository extends PagingAndSortingRepository<Empleado, Long> {

}
