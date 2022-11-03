package JNWR;

import JNWR.Entity.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

@SpringBootApplication
public class ServerApplication {
	private static final Logger logger = LogManager.getLogger(ServerApplication.class);
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY;
	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);

        //TODO: Create Server and connections
        //TODO: Connect server to Database
        //TODO: Create Task For server to do when client Requests

		/*logger.info("Test Info message");
        logger.debug("Test Debug Message");
        logger.error("Test Error message");
        logger.trace("Test Trace message");
        logger.fatal("Test Fatal message");
        logger.warn("Test Warning message");*/

        addCustomer("Ashley","Deans","2002-12-06","18764523606","ashs4657@gmail.com", "2022-11-03", "2023-11-03");

        ENTITY_MANAGER_FACTORY.close();
	}

    public static void addCustomer(String fName, String lName, String dob, String telephoneNum, String email, String dateOfMem, String expiryDate){
        ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("default");
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try{
            et = em.getTransaction();
            et.begin();
            Customer cust = new Customer(fName, lName, dob, telephoneNum, email, dateOfMem, expiryDate);
            em.persist(cust);
            et.commit();
            logger.info("New customer record added to the database");
        }
        catch(Exception ex){
            if(et !=null){
                et.rollback();
                logger.error("Entity was not added to the database. Rolled back!");
            }
            ex.printStackTrace();
        }
        finally{
            em.close();
        }
    }

}
