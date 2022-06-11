package br.com.sprint.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.sprint.data.model.Funcionario;
import br.com.sprint.data.model.projecao.FuncionarioProjecao;
import br.com.sprint.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {

	private boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final FuncionarioRepository funcionarioRepository;
	
	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Qual ação você deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Buscar funcionario por nome");
			System.out.println("2 - Buscar funcionario por nome, data contratação e salário maior");
			System.out.println("3 - Buscar funcionario por data contratação");
			System.out.println("4 - Buscar funcionario por salario");
			
			int action = scanner.nextInt();
			switch(action) {
				case 0: {
					system = false;
					break;
				}
				case 1: {
					buscarFuncionarioPorNome(scanner);
					break;
				}
				case 2: {
					buscaFuncionarioNomeSalarioMaiorData(scanner);
					break;
				}
				case 3: {
					buscaFuncionarioDataContratacao(scanner);
					break;
				}
				case 4: {
					buscaFuncionarioSalario();
					break;
				}
			}
		}
	}

	private void buscarFuncionarioPorNome(Scanner scanner) {
		System.out.println("Digite o nome que deseja pesquisar: ");
		String nome = scanner.next();
		
		List<Funcionario> lista = funcionarioRepository.findByNome(nome);
		lista.forEach(f -> System.out.println(f));
	}
	
	private void buscaFuncionarioNomeSalarioMaiorData(Scanner scanner) {
		System.out.println("Digite o nome que deseja pesquisar: ");
		String nome = scanner.next();
		
		System.out.println("Digite a data de contratação que deseja pesquisar: ");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		System.out.println("Digite o salário que deseja pesquisar: ");
		Double salario = scanner.nextDouble();
		
		List<Funcionario> lista = funcionarioRepository.findNomeDataContratacaoSalarioMaior(nome, salario, localDate);
		lista.forEach(f -> System.out.println(f));
	}
	
	private void buscaFuncionarioDataContratacao(Scanner scanner) {
		System.out.println("Digite a data de contratação que deseja pesquisar: ");
		String data = scanner.next();
		LocalDate localDate = LocalDate.parse(data, formatter);
		
		List<Funcionario> lista = funcionarioRepository.findDataContratacaoMaior(localDate);
		lista.forEach(f -> System.out.println(f));
	}
	
	private void buscaFuncionarioSalario() {	
		List<FuncionarioProjecao> lista = funcionarioRepository.findFuncionarioSalario();
		lista.forEach(f -> System.out.println("Funcionario: " + f.getId() + " - " + f.getNome() + " - " + f.getSalario()));
	}
}
