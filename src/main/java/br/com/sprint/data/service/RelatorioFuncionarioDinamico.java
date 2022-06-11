package br.com.sprint.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import br.com.sprint.data.model.Funcionario;
import br.com.sprint.data.repository.FuncionarioRepository;
import br.com.sprint.data.specification.SpecificationFuncionario;

@Service
public class RelatorioFuncionarioDinamico {

	private final FuncionarioRepository funcionarioRepository;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	
	public RelatorioFuncionarioDinamico(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		Specification<Funcionario> specification = Specification.where(null);
		
		System.out.println("Digite o nome");
		String nome = scanner.next();
		
		if(!nome.equalsIgnoreCase("null")) {
			specification = specification.and(SpecificationFuncionario.nome(nome));
		}
		
		System.out.println("Digite o cpf");
		String cpf = scanner.next();
		
		if(!cpf.equalsIgnoreCase("null")) {
			specification = specification.and(SpecificationFuncionario.cpf(cpf));
		}
		
		System.out.println("Digite o salario");
		Double salario = scanner.nextDouble();
		
		if(!(salario == 0)) {
			specification = specification.and(SpecificationFuncionario.salario(salario));
		}
		
		System.out.println("Digite a data de contratação");
		String data = scanner.next();
		
		LocalDate dataContratacao;
		if(data.equalsIgnoreCase("null")) {
			dataContratacao = null;
		} else {
			dataContratacao = LocalDate.parse(data, formatter);
			specification = specification.and(SpecificationFuncionario.dataContratacao(dataContratacao));
		}
		
		List<Funcionario> lista = funcionarioRepository.findAll(specification);
		
		lista.forEach(f -> System.out.println(f));
	}
}
