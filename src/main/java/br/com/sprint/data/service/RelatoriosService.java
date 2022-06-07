package br.com.sprint.data.service;

import java.util.List;
import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.sprint.data.model.Funcionario;
import br.com.sprint.data.repository.FuncionarioRepository;

@Service
public class RelatoriosService {

	private boolean system = true;
	
	private final FuncionarioRepository funcionarioRepository;
	
	public RelatoriosService(FuncionarioRepository funcionarioRepository) {
		this.funcionarioRepository = funcionarioRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Qual ação você deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Buscar funcionario por nome");
			
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
			}
		}
	}

	private void buscarFuncionarioPorNome(Scanner scanner) {
		System.out.println("Digite o nome que deseja pesquisar: ");
		String nome = scanner.next();
		
		List<Funcionario> lista = funcionarioRepository.findByNome(nome);
		lista.forEach(f -> System.out.println(f));
	}
}
