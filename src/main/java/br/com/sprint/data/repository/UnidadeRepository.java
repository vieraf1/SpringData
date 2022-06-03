package br.com.sprint.data.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.sprint.data.model.Unidade;

@Repository
public interface UnidadeRepository extends CrudRepository<Unidade, Integer> {
	
}
