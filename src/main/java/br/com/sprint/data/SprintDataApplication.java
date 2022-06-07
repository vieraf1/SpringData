package br.com.sprint.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.sprint.data.service.CrudCargoService;
import br.com.sprint.data.service.CrudFuncionarioService;
import br.com.sprint.data.service.CrudUnidadeService;
import br.com.sprint.data.service.RelatoriosService;

@SpringBootApplication
public class SprintDataApplication implements CommandLineRunner {
	
	private final CrudCargoService crudCargoService;
	private final CrudUnidadeService crudUnidadeService;
	private final CrudFuncionarioService crudFuncionarioService;
	private final RelatoriosService relatoriosService;
	private boolean system = true;
	
	public SprintDataApplication(CrudCargoService crudCargoService, CrudUnidadeService crudUnidadeService,
			CrudFuncionarioService crudFuncionarioService, RelatoriosService relatoriosService) {
		this.crudCargoService = crudCargoService;
		this.crudUnidadeService = crudUnidadeService;
		this.crudFuncionarioService = crudFuncionarioService;
		this.relatoriosService = relatoriosService;
	}

	public static void main(String[] args) {
		SpringApplication.run(SprintDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		
		while(system) {
			System.out.println("Qual ação deseja executar?");
			System.out.println("0 - Sair");
			System.out.println("1 - Cargo");
			System.out.println("2 - Unidade");
			System.out.println("3 - Funcionario");
			System.out.println("4 - Relatórios");
			
			int action = scanner.nextInt();
			switch(action) {
				case 1:
					crudCargoService.inicial(scanner);
					break;
				case 2:
					crudUnidadeService.inicial(scanner);
					break;
				case 3:
					crudFuncionarioService.inicial(scanner);
					break;
				case 4:
					relatoriosService.inicial(scanner);
					break;
				case 0:
					system = false;
			}
		}
	}

}
