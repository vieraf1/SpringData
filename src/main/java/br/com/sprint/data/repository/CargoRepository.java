package br.com.sprint.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.sprint.data.model.Cargo;

@Repository
public interface CargoRepository extends CrudRepository<Cargo, Integer> {
	
}
