package br.com.carro.repository;

import br.com.carro.domain.Carro;
import org.springframework.data.repository.CrudRepository;

public interface CarroRepository extends CrudRepository<Carro, Long> {

}
