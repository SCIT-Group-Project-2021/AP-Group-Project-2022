package JNWR.Domain;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.hibernate.boot.MappingNotFoundException;

import Entity.*;

public class Server {

	private static final Logger logger = LogManager.getLogger(Server.class);

    //Creates Entity Manager Factor using the entityManager(Hibernate) Set in the Persistence.xm
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("default");

    private ServerSocket serverSocket;
    private Socket connectionSocket;

    //Open Server on Port Specified and then waits for request
    public Server() {
        this.createConnection();
        this.waitForRequests();
    }

    public void createConnection() {
        try {
            serverSocket = new ServerSocket(8888);
            serverSocket.setReuseAddress(true);
        } catch (IOException ex) {
            logger.error(ex.toString());    
        }
    }

    private void waitForRequests() {
        Integer client = 0;
        try {

            while (true) {
                logger.info("Waiting For Request");

                connectionSocket = serverSocket.accept();

                ClientHandler clientSock = new ClientHandler(connectionSocket);
                
                new Thread(clientSock).start();
                client++;
                logger.info("Client number " + client);
                
                
            }
        } catch (EOFException ex) {
            logger.error("Client has Terminated connection with server");
            logger.error(ex.toString());            
        } catch (IOException ex) {
            logger.error(ex.toString());    
        }
        finally {

            ENTITY_MANAGER_FACTORY.close();

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


    //#endregion

    private static class ClientHandler implements Runnable {
        private final Socket clientSocket;

        private ObjectOutputStream objOs;
        private ObjectInputStream objIs;
  
        // Constructor
        public ClientHandler(Socket socket)
        {
            this.clientSocket = socket;
        }
  
        public void run()
        {
            String action = "";
            this.configureStreams();
            while (true) {

                try {
                    logger.info("Waiting For Action");
                    
                    action = (String) objIs.readObject();
                    logger.info(action);

                    DBEntity dbEntity = null;

                    switch (action) {
                        case "addEntity":

                            logger.info("Adding Entity");

                            try {
                                
                                dbEntity = (DBEntity)objIs.readObject();

                                addEntity(dbEntity);
                                sendAction("Task Completed");

                            } catch (ConnectException e) {
                                logger.error(e.toString());    
                            }
                            

                            break;
                        case "checkOutEntity":

                            logger.info("Checking Entity");

                            try {
                                
                                dbEntity = (DBEntity)objIs.readObject();

                                addEntity(dbEntity);
                                InvoiceItem checkoutItem = (InvoiceItem)dbEntity;
                                Inventory itemStock = (Inventory)findEntity("Inventory","productCode",checkoutItem.getProductCode());
                                itemStock.setStock(itemStock.getStock() - checkoutItem.getItemQuantity());
                                dbEntity = itemStock;
                                logger.info(itemStock.getProductCode());
                                alterEntity(Integer.parseInt(itemStock.getProductCode()), dbEntity);
                                sendAction("Task Completed");

                            } catch (ConnectException e) {
                                logger.error(e.toString());    
                            }
                            
                            break;
                        case "findLastEntity":

                            logger.info("Finding Last Entity");

                            try {
                                
                                sendEntity(findLastEntity((String)objIs.readObject()));
                                sendAction("Task Completed");

                            } catch (ConnectException e) {
                                logger.error(e.toString());    
                            }
                            
                            
                            break;
                        case "findEntity":

                           logger.info("Finding Entity");

                            try {
                                sendEntity(findEntity((String) objIs.readObject(),(String) objIs.readObject(),(String) objIs.readObject()));
                                sendAction("Task Completed");

                            } catch (EntityNotFoundException e) {
                                logger.error(e.toString());    
                            }catch (ConnectException e) {
                                logger.error(e.toString());    
                            }
                        
                            break;
                        case "findEntitySimple":
                            logger.info("Finding Entity by Class");
                            try {

                                sendEntity(findEntity((Integer) objIs.readObject(), (DBEntity) objIs.readObject()));
                                sendAction("Task Completed");

                            } catch (EntityNotFoundException e) {
                                logger.error(e.toString());  
                            }catch (ConnectException e) {
                                logger.error(e.toString());  
                            }
                        
                            break;
                        
                            
                        
                        case "alterEntity":

                            logger.info("Altering Entity");

                            try {

                                alterEntity((Integer) objIs.readObject(), (DBEntity) objIs.readObject());                      
                                sendAction("Task Completed");
                                
                            } catch (ConnectException e) {
                                // TODO: handle exception
                                logger.error(e.toString());    
                            }
                        
                            break;
                        case "removeEntity":

                           logger.info("Removing Entity");

                            try {

                                removeEntity((Integer)objIs.readObject(),(DBEntity)objIs.readObject());
                                sendAction("Task Completed");
                                
                            } catch (ConnectException e) {
                                // TODO: handle exception
                                logger.error(e.toString());  
                            }
                            
                            
                            break;
                        case "getList":

                            logger.info("Getting full list");

                            try {
                                sendList(listEntity((String) objIs.readObject()));
                                sendAction("Task Completed");
                            } catch (MappingNotFoundException e) {
                                logger.error(e.toString());  
                            }
                            catch (ConnectException e) {
                                logger.error(e.toString());  
                            }

                            break;

                        case "getSpecificList":

                            logger.info("Getting Specific List");

                            try {
                                sendList(listSpecificEntity((String) objIs.readObject(),(String) objIs.readObject(),(String) objIs.readObject()));
                                sendAction("Task Completed");
                            } catch (MappingNotFoundException e) {
                                logger.error(e.toString());  
                            }
                            catch (ConnectException e) {
                                logger.error(e.toString());  
                            }
                            break;

                        case "getExactList":

                            logger.info("Getting Exact List");

                            try {
                                sendList(listSpecificEntity((String) objIs.readObject(),(String) objIs.readObject(),(String) objIs.readObject()));
                                sendAction("Task Completed");
                            } catch (MappingNotFoundException e) {
                                logger.error(e.toString());  
                            }
                            catch (ConnectException e) {
                                logger.error(e.toString());  
                            }

                        break;

                        case "shutDown":
                            logger.info("Client Disconnected");
                            try {
                                if (objOs != null) {
                                    objOs.close();
                                }
                                if (objIs != null) {
                                    objIs.close();
                                    clientSocket.close();
                                }
                            }
                            catch (IOException e) {
                                e.printStackTrace();
                            }
                            return;
                        default:
                            logger.info("No Task Completed");
                            break;
                    }

                    logger.info("Task Completed");

                } catch (ClassNotFoundException ex) {
                    logger.error(ex.toString());  
                    
                } catch (ClassCastException ex) {
                    logger.error(ex.toString());  
                
                } catch (EOFException ex) {
                    logger.error("Client has Terminated connection with server");
                    logger.error(ex.toString());            
                } catch (IOException ex) {
                    logger.error(ex.toString());  
                } 
                
            }
               
        }
    
        private DBEntity findLastEntity(String table) {
            EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
    
            DBEntity dbEntity = null;
    
            try {
                dbEntity = em.createQuery("SELECT a FROM " + table + " a WHERE invoiceNum=(SELECT max(invoiceNum) FROM " + table+")",DBEntity.class).getSingleResult(); 
                
            } catch (EntityNotFoundException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            catch (NoResultException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
    
            return dbEntity;
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
    
        private void configureStreams() {
            try {
                objOs = new ObjectOutputStream(clientSocket.getOutputStream());
    
                objIs = new ObjectInputStream(clientSocket.getInputStream());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        private void removeEntity(Integer ID, DBEntity Entitiy) {
    
            EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
    
            EntityTransaction transaction = em.getTransaction();
    
            try {
    
                transaction.begin();
    
                DBEntity dbEntity = em.find(Entitiy.getClass(), ID);
    
                em.remove(dbEntity);
            
                transaction.commit();
                
            } catch (EntityNotFoundException e) {
                // TODO: handle exception
                e.printStackTrace();
            }catch (IllegalArgumentException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            
        }
    
        private DBEntity findEntity(String Table,String IDType, String ID) throws EntityNotFoundException{
            EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
    
            DBEntity dbEntity = null;
    
            try {
                dbEntity = em.createQuery("SELECT a FROM " + Table + " a WHERE " + IDType + " LIKE '" + ID + "'",DBEntity.class).getSingleResult(); 
                
            } catch (EntityNotFoundException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            catch (NoResultException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
    
            return dbEntity;
    
        }
    
        private DBEntity findEntity(Integer ID, DBEntity Entitiy) throws EntityNotFoundException{
    
            EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
    
            DBEntity dbEntity = null;
    
            try {
    
                dbEntity = em.find(Entitiy.getClass(), String.valueOf(ID));
    
            } catch (EntityNotFoundException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            
            return dbEntity;
    
        }
    
        private void alterEntity(Integer ID, DBEntity Entitiy) {
    
            EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
    
            EntityTransaction transaction = em.getTransaction();
    
            try {
    
                transaction.begin();
    
                DBEntity dbEntity = em.find(Entitiy.getClass(), String.valueOf(ID));
    
                em.merge(Entitiy);
            
                transaction.commit();
                
            } catch (EntityNotFoundException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
    
            
            
    
        }
    
        public List<DBEntity> listEntity(String Table) throws MappingNotFoundException{
            EntityManager em2 = ENTITY_MANAGER_FACTORY.createEntityManager();
    
            List<DBEntity> entityList = null;
    
            try {
                entityList = em2.createQuery("SELECT a FROM " + Table + " a",DBEntity.class).getResultList();
            } catch (IllegalArgumentException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
            
    
            return entityList;
            
        }
    
        public List<DBEntity> listSpecificEntity(String Table,String IDType, String ID) throws MappingNotFoundException{
            EntityManager em2 = ENTITY_MANAGER_FACTORY.createEntityManager();
    
            List<DBEntity> entityList = null;
    
            try {
                entityList = em2.createQuery("SELECT a FROM " + Table + " a WHERE " + IDType + " LIKE '%" + ID + "%'",DBEntity.class).getResultList();
            } catch (IllegalArgumentException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
    
    
            return entityList;
    
        }

        public List<DBEntity> listExactEntity(String Table,String IDType, String ID) throws MappingNotFoundException{
            EntityManager em2 = ENTITY_MANAGER_FACTORY.createEntityManager();
    
            List<DBEntity> entityList = null;
    
            try {
                entityList = em2.createQuery("SELECT a FROM " + Table + " a WHERE " + IDType + " LIKE '" + ID + "'",DBEntity.class).getResultList();
            } catch (IllegalArgumentException e) {
                // TODO: handle exception
                e.printStackTrace();
            }
    
    
            return entityList;
    
        }
    
        public void sendList(List<DBEntity> entityList) {
            try {
                objOs.writeObject(entityList);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
        public static void addEntity(DBEntity entity) {
            EntityManager em = ENTITY_MANAGER_FACTORY.createEntityManager();
            EntityTransaction et = null;
            try{
                et = em.getTransaction();
                et.begin();
                em.persist(entity);
                et.commit();
                
            }
            catch(Exception ex){
                if(et !=null){
                    et.rollback();
                }
                ex.printStackTrace();
            }
            finally{
                em.close();
            }
        }
    
        public void sendAction(String action) {
            try {
                objOs.writeObject(action);
            } catch (IOException e) {
                e.printStackTrace();
    
            }
        }
    
        public void sendEntity(DBEntity entity) {
            try {
                objOs.writeObject(entity);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    
    
    }

    
}

