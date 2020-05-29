package fr.formation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class NcdzApplication {

	public static void main(String[] args) {
	//	BCryptPasswordEncoder b = new BCryptPasswordEncoder();
	//	System.out.println(":"+b.encode(""));
		SpringApplication.run(NcdzApplication.class, args);
	}

}
