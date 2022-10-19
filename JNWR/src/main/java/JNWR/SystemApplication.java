package JNWR;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import JNWR.application.posPage;

@SpringBootApplication
public class SystemApplication {

	private static final Logger logger = LogManager.getLogger(SystemApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(SystemApplication.class, args);
        System.setProperty("java.awt.headless", "false");

		/*logger.info("Test Info message");
        logger.debug("Test Debug Message");
        logger.error("Test Error message");
        logger.trace("Test Trace message");
        logger.fatal("Test Fatal message");
        logger.warn("Test Warning message");*/

        new posPage();
        
	}

}
