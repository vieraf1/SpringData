package br.com.sprint.data;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import br.com.sprint.data.model.Cargo;
import br.com.sprint.data.repository.CargoRepository;

@SpringBootApplication
public class SprintDataApplication implements CommandLineRunner {
	
	private final CargoRepository cargoRepository;
	
	public SprintDataApplication(CargoRepository cargoRepository) {
		this.cargoRepository = cargoRepository;
	}

	public static void main(String[] args) {
		SpringApplication.run(SprintDataApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Cargo cargo = new Cargo();
		cargo.setDescricao("Desenvolvedor de software");
		
		cargoRepository.save(cargo);
	}

}
