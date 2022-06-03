package br.com.sprint.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.sprint.data.model.Unidade;
import br.com.sprint.data.repository.UnidadeRepository;

@Service
public class CrudUnidadeService {
	
	private boolean system = true;
	private final UnidadeRepository unidadeRepository;
	
	public CrudUnidadeService(UnidadeRepository unidadeRepository) {
		this.unidadeRepository = unidadeRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Qual ação de Unidade deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Criar");
			System.out.println("2 - Atualizar");
			System.out.println("3 - Visualizar");
			System.out.println("4 - Deletar");
			
			int action = scanner.nextInt();
			switch(action) {
				case 0: {
					system = false;
					break;
				}
				case 1: {
					salvar(scanner);
					break;
				}
				case 2: {
					atualizar(scanner);
					break;
				}
				case 3: {
					visualizar();
					break;
				}
				case 4: {
					deletar(scanner);
					break;
				}
			}
		}
	}
	
	public void salvar(Scanner scanner) {
		scanner.nextLine();
		System.out.println("Descrição da Unidade: ");
		String descricao = scanner.nextLine();
		
		Unidade unidade = new Unidade();
		unidade.setDescricao(descricao);
		unidadeRepository.save(unidade);
		System.out.println("Unidade salvado com sucesso!");
		scanner.nextLine();
	}
	
	private void atualizar(Scanner scanner) {
		System.out.println("Id: ");
		Integer id = scanner.nextInt();
		
		scanner.nextLine();
		System.out.println("Descrição da Unidade: ");
		String descricao = scanner.nextLine();
		
		Unidade unidade = new Unidade();
		unidade.setId(id);
		unidade.setDescricao(descricao);
		unidadeRepository.save(unidade);
		System.out.println("Unidade salvado com sucesso!");
	}
	
	private void visualizar() {
		Iterable<Unidade> lista = unidadeRepository.findAll();
		lista.forEach(Unidade -> System.out.println(Unidade));
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Id: ");
		Integer id = scanner.nextInt();
		
		unidadeRepository.deleteById(id);
		System.out.println("Unidade deletado com sucesso!");
	}
}
