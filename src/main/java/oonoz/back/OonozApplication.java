package oonoz.back;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import oonoz.back.domain.Player;
import oonoz.back.repositories.PlayerRepository;

@Configuration
@EnableAutoConfiguration
@ComponentScan
public class OonozApplication {

	private static final Logger log = LoggerFactory.getLogger(OonozApplication.class);
	
	public static void main(String[] args) {
		SpringApplication.run(OonozApplication.class, args);
	}
	
	 @Bean
	 public CommandLineRunner demo(PlayerRepository repository) {
	 	return (args) -> {
	 		log.info("Player found with findAll():");
	 		log.info("-------------------------------");
	 		for (Player player : repository.findAll()) {
	 			log.info(player.getFirstName());
	 		}
	 	};
	 }
}
