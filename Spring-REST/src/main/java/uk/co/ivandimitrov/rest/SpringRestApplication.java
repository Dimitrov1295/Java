package uk.co.ivandimitrov.rest;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class SpringRestApplication {

	@SuppressWarnings("unused") // Repository bean not used locally.
	public static void main(String[] args) {
		ConfigurableApplicationContext context = SpringApplication.run(SpringRestApplication.class, args);

		RfamRepository repo = context.getBean(RfamRepository.class);// A bean for the repository
	}

}
