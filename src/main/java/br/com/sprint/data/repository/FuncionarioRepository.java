package br.com.sprint.data.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import br.com.sprint.data.model.Funcionario;

@Repository
public interface FuncionarioRepository extends PagingAndSortingRepository<Funcionario, Integer> {
	
	public List<Funcionario> findByNome(String nome);
	
	public List<Funcionario> findByNome(String nome, Pageable pageable);
	
	@Query(value = "SELECT f FROM Funcionario f "
			+ " WHERE f.nome = :nome "
			+ " AND f.salario >= :salario"
			+ " AND f.dataContratacao = :data")
	public List<Funcionario> findNomeDataContratacaoSalarioMaior(String nome, Double salario, LocalDate data);
	
	@Query(value = "SELECT * FROM FUNCIONARIOS f "
			+ " WHERE f.data_contratacao >= :data", nativeQuery = true)
	public List<Funcionario> findDataContratacaoMaior(LocalDate data);
	
}
