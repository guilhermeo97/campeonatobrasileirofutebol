package br.com.campeonatobrasileirofutebol.campeonatobrasileirofutebol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@EntityScan
@SpringBootApplication
public class CampeonatobrasileirofutebolApplication {

	public static void main(String[] args) {
		SpringApplication.run(CampeonatobrasileirofutebolApplication.class, args);
	}

}
