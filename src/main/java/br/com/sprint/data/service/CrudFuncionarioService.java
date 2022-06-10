package br.com.sprint.data.service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import br.com.sprint.data.model.Cargo;
import br.com.sprint.data.model.Funcionario;
import br.com.sprint.data.model.Unidade;
import br.com.sprint.data.repository.CargoRepository;
import br.com.sprint.data.repository.FuncionarioRepository;
import br.com.sprint.data.repository.UnidadeRepository;

@Service
public class CrudFuncionarioService {
	

	private Boolean system = true;
	private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
	
	private final FuncionarioRepository funcionarioRepository;
	private final CargoRepository cargoRepository;
	private final UnidadeRepository unidadeRepository;
	
	public CrudFuncionarioService(FuncionarioRepository funcionarioRepository, CargoRepository cargoRepository,
			UnidadeRepository unidadeRepository) {
		this.funcionarioRepository = funcionarioRepository;
		this.cargoRepository = cargoRepository;
		this.unidadeRepository = unidadeRepository;
	}
	
	public void inicial(Scanner scanner) {
		while(system) {
			System.out.println("Qual ação de Funcionario deseja executar?");
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
					visualizar(scanner);
					break;
				}
				case 4: {
					deletar(scanner);
					break;
				}
			}
		}
	}
	
	private void salvar(Scanner scanner) {
		System.out.println("Digite o nome");
        String nome = scanner.next();

        System.out.println("Digite o cpf");
        String cpf = scanner.next();

        System.out.println("Digite o salario");
        Double salario = scanner.nextDouble();

        System.out.println("Digite a data de contracao");
        String dataContratacao = scanner.next();

        System.out.println("Digite o cargoId");
        Integer cargoId = scanner.nextInt();

        List<Unidade> unidades = unidade(scanner);

        Funcionario funcionario = new Funcionario();
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
        Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        funcionario.setCargo(cargo.get());
        funcionario.setUnidades(unidades);

        funcionarioRepository.save(funcionario);
        System.out.println("Salvo");
	}
	
	private List<Unidade> unidade(Scanner scanner) {
        Boolean isTrue = true;
        List<Unidade> unidades = new ArrayList<>();

        while (isTrue) {
            System.out.println("Digite o unidadeId (Para sair digite 0)");
            Integer unidadeId = scanner.nextInt();

            if(unidadeId != 0) {
                Optional<Unidade> unidade = unidadeRepository.findById(unidadeId);
                unidades.add(unidade.get());
            } else {
                isTrue = false;
            }
        }

        return unidades;
    }
	
	private void atualizar(Scanner scanner) {
		System.out.println("Digite o id");
        Integer id = scanner.nextInt();

        System.out.println("Digite o nome");
        String nome = scanner.next();

        System.out.println("Digite o cpf");
        String cpf = scanner.next();

        System.out.println("Digite o salario");
        Double salario = scanner.nextDouble();

        System.out.println("Digite a data de contracao");
        String dataContratacao = scanner.next();

        System.out.println("Digite o cargoId");
        Integer cargoId = scanner.nextInt();

        Funcionario funcionario = new Funcionario();
        funcionario.setId(id);
        funcionario.setNome(nome);
        funcionario.setCpf(cpf);
        funcionario.setSalario(salario);
        funcionario.setDataContratacao(LocalDate.parse(dataContratacao, formatter));
        Optional<Cargo> cargo = cargoRepository.findById(cargoId);
        funcionario.setCargo(cargo.get());

        funcionarioRepository.save(funcionario);
        System.out.println("Alterado");
	}
	
	private void visualizar(Scanner scanner) {
		System.out.println("Qual página deseja visualizar?");
		Integer page = scanner.nextInt();
		
		Pageable pageable = PageRequest.of(page, 5, Sort.by(Sort.Direction.ASC, "nome"));
		Page<Funcionario> funcionarios = funcionarioRepository.findAll(pageable);
		
		System.out.println(funcionarios);
		System.out.println("Pagina atual " + funcionarios.getNumber());
		System.out.println("Total de elementos: " + funcionarios.getTotalElements());
		
		System.out.println();
		funcionarios.forEach(funcionario -> System.out.println(funcionario));
		System.out.println();
	}
	
	private void deletar(Scanner scanner) {
		System.out.println("Id: ");
		Integer id = scanner.nextInt();
		
		funcionarioRepository.deleteById(id);
		System.out.println("Funcionario deletado com sucesso!");
	}
}
