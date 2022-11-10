package JNWR;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import Entity.*;
import JNWR.Domain.Client;
import JNWR.application.landingPage;


@SpringBootApplication
public class ClientApplication {

	private static final Logger logger = LogManager.getLogger(ClientApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
        System.setProperty("java.awt.headless", "false");

        //TODO: Create client socket and have a way for it to connect to the server
        //TODO: Create UI for connecting client to server, after connecton s made make client login to access the rest of the database
       
         
        Customer cust = new Customer("chase","Doe","2001-07-16","18765553606","gabe@gmail.com", "2022-11-03");
        
        Department dep = new Department("Admin", "Admin");

        Staff staff = new Staff(319219,"graham","Tickle","18765993666","Admin","ACS");
        
       
            System.out.println("Task 1");
            new Client().addEntity(cust);
    
            System.out.println("Task 2");
            new Client().getList("Department");
    
            System.out.println("Task 3");
            new Client().findEntity("Staff","idNum", "339219");

            System.out.println("Task 4");
            new Client().findEntity(staff, staff.getIdNum());
    
            System.out.println("Task 5");
            new Client().alterEntity(staff, staff.getIdNum());     
            
            System.out.println("Task 6");
            new Client().removeEntity(new Staff(), 339219); 
        
        new landingPage();

        
	}

}
