package br.com.sprint.data.service;

import java.util.Scanner;

import org.springframework.stereotype.Service;

import br.com.sprint.data.model.Cargo;
import br.com.sprint.data.repository.CargoRepository;

@Service
public class CrudCargoService {
	
	private boolean system = true;
	private final CargoRepository cargoRepository;
	
	public CrudCargoService(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Qual ação de cargo deseja executar?");
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
		System.out.println("Descrição do cargo: ");
		String descricao = scanner.next();
		
		Cargo cargo = new Cargo();
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("Cargo salvado com sucesso!");
	}
	
	private void atualizar(Scanner scanner) {
		System.out.println("Id: ");
		Integer id = scanner.nextInt();
		
		System.out.println("Descrição do cargo: ");
		String descricao = scanner.next();
		
		Cargo cargo = new Cargo();
		cargo.setId(id);
		cargo.setDescricao(descricao);
		cargoRepository.save(cargo);
		System.out.println("Cargo salvado com sucesso!");
	}
	
	private void visualizar() {
		Iterable<Cargo> lista = cargoRepository.findAll();
		lista.forEach(cargo -> System.out.println(cargo));
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Id: ");
		Integer id = scanner.nextInt();
		
		cargoRepository.deleteById(id);
		System.out.println("Cargo deletado com sucesso!");
	}
}
