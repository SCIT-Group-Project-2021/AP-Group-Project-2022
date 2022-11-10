package JNWR;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import JNWR.application.landingPage;

@SpringBootApplication
public class ClientApplication {

	private static final Logger logger = LogManager.getLogger(ClientApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
        System.setProperty("java.awt.headless", "false");

        //TODO: Create client socket and have a way for it to connect to the server
        //TODO: Create UI for connecting client to server, after connecton s made make client login to access the rest of the database
       
        /* 
        Customer cust = new Customer("chase","Doe","2001-07-16","18765553606","gabe@gmail.com", "2022-11-03", "2023-11-03");
        
        Department dep = new Department("Admin", "Admin");

        Staff staff = new Staff(339219,"Gabriel","Tickle","18765993666","Admin","Admin");
        cust.setAction("addEntity");
        dep.setAction("addEntity");
        staff.setAction("addEntity");
       
        
        System.out.println("Task 1");
        new Client().sendEntity(cust);

        System.out.println("Task 2");
        new Client().getList("Department");

        System.out.println("Task 3");
        new Client().findEntity("Staff","idNum", "339219");
        */
        
        new landingPage();

        
	}

}
