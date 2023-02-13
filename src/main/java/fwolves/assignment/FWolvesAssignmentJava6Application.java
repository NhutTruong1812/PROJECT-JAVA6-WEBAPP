package fwolves.assignment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@EnableWebSecurity
public class FWolvesAssignmentJava6Application {

	public static void main(String[] args) {
		SpringApplication.run(FWolvesAssignmentJava6Application.class, args);
	}

}
