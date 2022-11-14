package JNWR;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import JNWR.application.*;


@SpringBootApplication
public class ClientApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
        System.setProperty("java.awt.headless", "false");

        new LoginPage();

        
	}

}
