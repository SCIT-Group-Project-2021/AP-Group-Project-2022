package JNWR;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import JNWR.application.posPage;

@SpringBootApplication
public class ClientApplication {

	private static final Logger logger = LogManager.getLogger(ClientApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
        System.setProperty("java.awt.headless", "false");

        //TODO: Create client socket and have a way for it to connect to the server
        //TODO: Create UI for connecting client to server, after connecton s made make client login to access the rest of the database

		logger.info("Test Info message");
        logger.debug("Test Debug Message");
        logger.error("Test Error message");
        logger.trace("Test Trace message");
        logger.fatal("Test Fatal message");
        logger.warn("Test Warning message");

        new posPage();
        
	}

}
