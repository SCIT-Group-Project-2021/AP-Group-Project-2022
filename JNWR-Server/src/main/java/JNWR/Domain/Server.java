package JNWR.Domain;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.sql.ResultSet;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import Entity.*;

public class Server {

    //Create's Entity Manager Factor using the entityManager(Hibernate) Set in the Persistence.xm
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("default");

    private ServerSocket serverSocket;
    private Socket connectionSocket;
    private ObjectOutputStream objOs;
    private ObjectInputStream objIs;
    private ResultSet result;  

    //Open Server on Port Specified and then waits for request
    public Server() throws ClassNotFoundException {
        this.createConnection();
        this.waitForRequests();
    }

    public Server(ServerSocket serverSocket, Socket connectionSocket, ObjectOutputStream objOs, ObjectInputStream objIs, ResultSet result) {
        this.serverSocket = serverSocket;
        this.connectionSocket = connectionSocket;
        this.objOs = objOs;
        this.objIs = objIs;
        this.result = result;
    }

    private void waitForRequests() throws ClassNotFoundException {
        String action = "";
        try {
            while (true) {
                connectionSocket = serverSocket.accept();
                this.configureStreams();
                try {
                    action = (String) objIs.readObject();
                    /*
                    if (action.equals("shutDown")) {
                        System.out.println("Shutting Down Server");
                        break;
                    }*/

                    switch (action) {
                        case "addCustomer":

                            Customer customer = null;

                            customer = (Customer)objIs.readObject();

                            addCustomer(customer);
                            
                            break;
                        case "findCustomer":

                            Customer customer = null;

                            customer = (Customer)objIs.readObject();

                            addCustomer(customer);

                            break;
                        case "deleteCustomer":

                            Customer customer = null;

                            customer = (Customer)objIs.readObject();

                            addCustomer(customer);

                            break;
                        case "shutDown":
                            System.out.println("Server shutdown");
                            return;
                        default:
                            break;
                    }

                } catch (ClassNotFoundException ex) {
                    ex.printStackTrace();
                    
                } catch (ClassCastException ex) {
                    ex.printStackTrace();
                }
                this.closeConnection();
            }
        } catch (EOFException ex) {
            System.out.println("Client has Terminated connection with server");
            ex.printStackTrace();            
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        finally {

            ENTITY_MANAGER_FACTORY.close();

        }
        
    }

    private void configureStreams() {
        try {
            objOs = new ObjectOutputStream(connectionSocket.getOutputStream());

            objIs = new ObjectInputStream(connectionSocket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createConnection() {
        try {
            serverSocket = new ServerSocket(8888);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }

    public void closeConnection() {
        try {
            objIs.close();
            objOs.close();
            connectionSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void addCustomer(Customer cust) {
        EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
        EntityTransaction et = null;
        try{
            et = em.getTransaction();
            et.begin();
            em.persist(cust);
            et.commit();
            //logger.info("New customer record added to the database");
        }
        catch(Exception ex){
            if(et !=null){
                et.rollback();
                //logger.error("Entity was not added to the database. Rolled back!");
            }
            ex.printStackTrace();
        }
        finally{
            em.close();
        }
    }

    //#region

    public ServerSocket getServerSocket() {
        return this.serverSocket;
    }

    public void setServerSocket(ServerSocket serverSocket) {
        this.serverSocket = serverSocket;
    }

    public Socket getConnectionSocket() {
        return this.connectionSocket;
    }

    public void setConnectionSocket(Socket connectionSocket) {
        this.connectionSocket = connectionSocket;
    }

    public ObjectOutputStream getObjOs() {
        return this.objOs;
    }

    public void setObjOs(ObjectOutputStream objOs) {
        this.objOs = objOs;
    }

    public ObjectInputStream getObjIs() {
        return this.objIs;
    }

    public void setObjIs(ObjectInputStream objIs) {
        this.objIs = objIs;
    }

    

    public ResultSet getResult() {
        return this.result;
    }

    public void setResult(ResultSet result) {
        this.result = result;
    }


    //#endregion

}
