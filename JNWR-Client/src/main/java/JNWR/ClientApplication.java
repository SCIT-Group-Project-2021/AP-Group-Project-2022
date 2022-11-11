package JNWR;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import Entity.*;
import JNWR.Domain.Client;
import JNWR.application.*;


@SpringBootApplication
public class ClientApplication {

	private static final Logger logger = LogManager.getLogger(ClientApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
        System.setProperty("java.awt.headless", "false");
         
            /*
            Client client = new Client();
            Customer cust = new Customer("chase","Doe","2001-07-16","18765553606","gabe@gmail.com", "2024-11-03");
        
            Department dep = new Department("Admin", "Admin");

            Staff staff = new Staff(349219,"graham","Tickle","18765993666","Admin","ACS");

            Staff staff2 = new Staff(389219,"graham","Tickle","18765993666","Admin","ACS");
    
            
            System.out.println("Task 1");
            client.addEntity(cust);

            System.out.println("Task 2");
            client.addEntity(staff2);
    
            System.out.println("Task 3");
            client.getList("Department");
    
            System.out.println("Task 4");
            client.findEntity("Staff","idNum", "399219");

            System.out.println("Task 5");
            client.findEntity(staff2, staff2.getIdNum());
    
            System.out.println("Task 6");
            client.alterEntity(staff, staff.getIdNum());     
            
            System.out.println("Task 7");
            new Client().removeEntity(new Staff(), 399219); 
             */

        
        
        new landingPage();

        
	}

}
