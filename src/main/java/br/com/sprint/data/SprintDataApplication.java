package br.com.sprint.data;

import java.util.Scanner;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.sprint.data.service.CrudCargoService;

@SpringBootApplication
public class SprintDataApplication implements CommandLineRunner {
	
	private final CrudCargoService crudCargoService;
	private boolean system = true;
	
	public SprintDataApplication(CrudCargoService crudCargoService) {
		this.crudCargoService = crudCargoService;
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
			
			int action = scanner.nextInt();
			if(action == 1) {
				crudCargoService.inicial(scanner);
			} else {
				system = false;
			}
		}
	}

}
