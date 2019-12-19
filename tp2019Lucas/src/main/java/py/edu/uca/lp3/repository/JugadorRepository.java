package py.edu.uca.lp3.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import py.edu.uca.lp3.domain.Jugador;

//interface para almacenar y recuperar los datos sobre los jugadores registrados
public interface JugadorRepository extends PagingAndSortingRepository<Jugador, Long> {

}
