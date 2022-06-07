package br.com.sprint.data.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import br.com.sprint.data.model.Funcionario;

@Repository
public interface FuncionarioRepository extends CrudRepository<Funcionario, Integer> {
	
	public List<Funcionario> findByNome(String nome);
	
	@Query(value = "SELECT FROM Funcionario f "
			+ " WHERE f.nome = :nome "
			+ " AND f.salario >= :salario"
			+ " AND f.dataContratacao = :data")
	public List<Funcionario> findNomeDataContratacaoSalarioMaior(String nome, Double salario, LocalDate data);
	
}
