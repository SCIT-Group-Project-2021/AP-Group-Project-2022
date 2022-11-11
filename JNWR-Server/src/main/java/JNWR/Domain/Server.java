package JNWR.Domain;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ConnectException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

import org.hibernate.boot.MappingNotFoundException;

import Entity.*;

public class Server {

    //Creates Entity Manager Factor using the entityManager(Hibernate) Set in the Persistence.xm
    private static EntityManagerFactory ENTITY_MANAGER_FACTORY = Persistence.createEntityManagerFactory("default");

    private ServerSocket serverSocket;
    private Socket connectionSocket;
    private ObjectOutputStream objOs;
    private ObjectInputStream objIs;


    //Open Server on Port Specified and then waits for request
    public Server() {
        this.createConnection();
        this.waitForRequests();
    }

    public Server(ServerSocket serverSocket, Socket connectionSocket, ObjectOutputStream objOs, ObjectInputStream objIs) {
        this.serverSocket = serverSocket;
        this.connectionSocket = connectionSocket;
        this.objOs = objOs;
        this.objIs = objIs;
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

    private void waitForRequests() {
        String action = "";
        try {
            while (true) {
                System.out.println("Waiting For Request");
                connectionSocket = serverSocket.accept();
                this.configureStreams();
                try {
                    System.out.println("Waiting For Action");
                    action = (String) objIs.readObject();

                    DBEntity dbEntity = null;

                    switch (action) {
                        case "addEntity":

                            System.out.println("Adding Entity");

                            try {
                                
                                dbEntity = (DBEntity)objIs.readObject();

                                addEntity(dbEntity);
                                sendAction("Task Completed");

                            } catch (ConnectException e) {
                                // TODO: handle exception
                                e.printStackTrace();
                            }
                            
                            
                            break;
                        case "findEntity":

                            System.out.println("Finding Entity");

                            try {
                                sendEntity(findEntity((String) objIs.readObject(),(String) objIs.readObject(),(String) objIs.readObject()));
                                sendAction("Task Completed");

                            } catch (EntityNotFoundException e) {
                                // TODO: handle exception
                                e.printStackTrace();
                            }catch (ConnectException e) {
                                // TODO: handle exception
                                e.printStackTrace();
                            }
                        
                            break;
                        case "findEntitySimple":
                            System.out.println("Finding Entity");
                            try {

                                sendEntity(findEntity((Integer) objIs.readObject(), (DBEntity) objIs.readObject()));
                                sendAction("Task Completed");

                            } catch (EntityNotFoundException e) {
                                // TODO: handle exception
                                e.printStackTrace();
                            }catch (ConnectException e) {
                                // TODO: handle exception
                                e.printStackTrace();
                            }
                        
                            break;
                        
                            
                        
                        case "alterEntity":

                            System.out.println("Altering Entity");

                            try {

                                alterEntity((Integer) objIs.readObject(), (DBEntity) objIs.readObject());                      
                                sendAction("Task Completed");
                                
                            } catch (ConnectException e) {
                                // TODO: handle exception
                                e.printStackTrace();
                            }
                        
                            break;
                        case "removeEntity":

                            System.out.println("Removing Entity");

                            try {

                                removeEntity((Integer)objIs.readObject(),(DBEntity)objIs.readObject());
                                sendAction("Task Completed");
                                
                            } catch (ConnectException e) {
                                // TODO: handle exception
                                e.printStackTrace();
                            }
                            

                            
                            
                            break;
                        case "getList":

                            System.out.println("Getting List");

                            try {
                                sendList(listEntity((String) objIs.readObject()));
                                sendAction("Task Completed");
                            } catch (MappingNotFoundException e) {
                                // TODO: handle exception
                                e.printStackTrace();
                            }
                            catch (ConnectException e) {
                                // TODO: handle exception
                                e.printStackTrace();
                            }

                            break;

                            case "getSpecificList":

                            System.out.println("Getting List");

                            try {
                                sendList(listSpecificEntity((String) objIs.readObject(),(String) objIs.readObject(),(String) objIs.readObject()));
                                sendAction("Task Completed");
                            } catch (MappingNotFoundException e) {
                                // TODO: handle exception
                                e.printStackTrace();
                            }
                            catch (ConnectException e) {
                                // TODO: handle exception
                                e.printStackTrace();
                            }


                            break;
                        case "shutDown":
                            System.out.println("Server shutdown");
                            return;
                        default:
                            System.out.println("No Task Completed");
                            break;
                    }

                    System.out.println("Task Completed");

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

            dbEntity = em.find(Entitiy.getClass(), ID);

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

            DBEntity dbEntity = em.find(Entitiy.getClass(), ID);

            em.merge(Entitiy);
        
            transaction.commit();

            sendAction("Task Completed");
            
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
            System.out.println(Arrays.toString(entityList.toArray()));
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
            System.out.println(Arrays.toString(entityList.toArray()));
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

    private void configureStreams() {
        try {
            objOs = new ObjectOutputStream(connectionSocket.getOutputStream());

            objIs = new ObjectInputStream(connectionSocket.getInputStream());
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

    //#endregion

}
