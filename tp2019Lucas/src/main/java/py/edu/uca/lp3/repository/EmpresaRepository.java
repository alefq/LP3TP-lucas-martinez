package py.edu.uca.lp3.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import py.edu.uca.lp3.domain.Empresa;

public interface EmpresaRepository extends PagingAndSortingRepository<Empresa, Long> {

}
