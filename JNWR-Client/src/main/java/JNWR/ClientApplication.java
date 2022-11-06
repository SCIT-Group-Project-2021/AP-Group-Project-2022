package JNWR;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import Entity.*;
import JNWR.Domain.*;
import JNWR.application.posPage;

@SpringBootApplication
public class ClientApplication {

	private static final Logger logger = LogManager.getLogger(ClientApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
        System.setProperty("java.awt.headless", "false");

        //TODO: Create client socket and have a way for it to connect to the server
        //TODO: Create UI for connecting client to server, after connecton s made make client login to access the rest of the database

		/*logger.info("Test Info message");
        logger.debug("Test Debug Message");
        logger.error("Test Error message");
        logger.trace("Test Trace message");
        logger.fatal("Test Fatal message");
        logger.warn("Test Warning message");*/

        Client client = new Client();

        Customer cust = new Customer("chase","Doe","2001-07-16","18765553606","gabe@gmail.com", "2022-11-03", "2023-11-03");
        
        Staff staff = new Staff(289219,"Gabriel","Tickle","18765993666","Admin","2555");

        cust.setAction("addEntity");
        staff.setAction("addEntity");
        client.sendEntity(cust);

        //new posPage();
        
	}

}
