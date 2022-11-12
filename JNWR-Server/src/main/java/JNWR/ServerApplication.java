package JNWR;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import JNWR.Domain.*;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) throws ClassNotFoundException {
		SpringApplication.run(ServerApplication.class, args);

        //Create's Server instance
        Server dB_Server = new Server();

	}

}
