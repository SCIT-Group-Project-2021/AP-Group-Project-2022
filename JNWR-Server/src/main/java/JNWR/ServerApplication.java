package JNWR;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import JNWR.Domain.*;

@SpringBootApplication
public class ServerApplication {
	private static final Logger logger = LogManager.getLogger(ServerApplication.class);

	public static void main(String[] args) throws ClassNotFoundException {
		SpringApplication.run(ServerApplication.class, args);

        
        //TODO: Connect server to Database
        //TODO: Create Task For server to do when client Requests

		/*logger.info("Test Info message");
        logger.debug("Test Debug Message");
        logger.error("Test Error message");
        logger.trace("Test Trace message");
        logger.fatal("Test Fatal message");
        logger.warn("Test Warning message");*/

        //Create's Server instance
        Server dB_Server = new Server();

	}

}
