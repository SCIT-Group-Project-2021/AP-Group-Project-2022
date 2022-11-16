package JNWR.Domain;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.net.ConnectException;

import Entity.*;

public class Client {

    private static final Logger logger = LogManager.getLogger(Client.class);

    private ObjectInputStream objIs;
    private static ObjectOutputStream objOs;
    private Socket connectionSocket;

    public Client () throws ConnectException{
        this.createConnection();
        this.configureStreams();      
        
    }

    public Client (String ip) {
        this.createConnection(ip);
        this.configureStreams();
        
    }

    private void configureStreams() throws NullPointerException{
        try {

            objOs = new ObjectOutputStream(connectionSocket.getOutputStream());

            objIs = new ObjectInputStream(connectionSocket.getInputStream());
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

    private void createConnection() throws ConnectException {
        try {
            connectionSocket = new Socket("localHost",8888);
        }catch (ConnectException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

    private void createConnection(String ip) {
        try {
            connectionSocket = new Socket(ip,8888);
        }catch (ConnectException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

    //region Utility Methods

    //Sends the action to the Server
    public void sendAction(String action) {
        try {
            objOs.writeObject(action);
            objOs.flush();
            objOs.reset();
            logger.info("Sent Action: " + action);
        } catch (IOException e) {
            logger.error(e.toString());

        }
    }

    public void sendInteger(Integer value) {
        try {
            objOs.writeObject(value);
            objOs.flush();
            objOs.reset();
            logger.info("Sent Integer: " + value);
        } catch (IOException e) {
            logger.error(e.toString());

        }
    }

    public void sendEntity(DBEntity entity) {
        try {
            objOs.writeObject(entity);
            objOs.flush();
            objOs.reset();
            logger.info("Sent Entity: " + entity);
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

    public void sendClass(Class<?> classObject) {
        try {
            objOs.writeObject(classObject);
            objOs.flush();
            objOs.reset();
            logger.info("Sent Class: " + classObject);
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

    public ArrayList<DBEntity> getList(String Table) {

        ArrayList<DBEntity> list = new ArrayList<DBEntity>();
        //Calls the get list function
        sendAction("getList");
        //Tells the function what table to return
        sendAction(Table);

        try {
            list = (ArrayList<DBEntity>) objIs.readObject();
        } catch (ClassNotFoundException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        } catch (NullPointerException e) {
            logger.error(e.toString());
        }

        try {
            logger.info((String) objIs.readObject());
        } catch (ClassNotFoundException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        }

        return list;
  
    }

    public ArrayList<DBEntity> reportInvoiceList(String startDate, String endDate) {

        ArrayList<Entity.DBEntity> list = new ArrayList<Entity.DBEntity>();
        //Calls the specific list function
        sendAction("reportInvoiceList");
        //Tells the function what the start date is
        sendAction(startDate);
        //Tells the function what the end date is
        sendAction(endDate);

        try {
            list =(ArrayList<Entity.DBEntity>) objIs.readObject();
        } catch (ClassNotFoundException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        } catch (NullPointerException e) {
            logger.error(e.toString());
        }

        try {
            logger.info((String) objIs.readObject());
        } catch (ClassNotFoundException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        }

        return list;

    }

    public DBEntity getSpecificInvoiceReport(String Table,String IDType, String ID, String IDType2, String ID2) {
        //Calls the specific list function
        sendAction("getSpecificInvoiceReport");
        //Tells the function what table to return
        sendAction(Table);
        //Tells the function what Column to use to find the items
        sendAction(IDType);
        //Tells the function IDs to return
        sendAction(ID);
        //Tells the function what Column to use to find the items
        sendAction(IDType2);
        //Tells the function IDs to return
        sendAction(ID2);

        DBEntity entity = null;

        try {
            entity = (DBEntity)objIs.readObject();
        } catch (ClassNotFoundException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        } catch (NullPointerException e) {
            logger.error(e.toString());
        }


        try {
            logger.info((String) objIs.readObject());

        } catch (ClassNotFoundException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        }
        return entity;

    }



    public ArrayList<DBEntity> getSpecificList(String Table,String IDType, String ID) {

        ArrayList<Entity.DBEntity> list = new ArrayList<Entity.DBEntity>();
        //Calls the specific list function
        sendAction("getSpecificList");
        //Tells the function what table to return
        sendAction(Table);
        //Tells the function what Column to use to find the items
        sendAction(IDType);
        //Tells the function ID's to return
        sendAction(ID);

        try {
            list =(ArrayList<Entity.DBEntity>) objIs.readObject();
        } catch (ClassNotFoundException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        } catch (NullPointerException e) {
            logger.error(e.toString());
        }

        try {
            logger.info((String) objIs.readObject());
        } catch (ClassNotFoundException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        }

        return list;

    }

    public ArrayList<DBEntity> getExactList(String Table,String IDType, String ID) {

        ArrayList<Entity.DBEntity> list = new ArrayList<Entity.DBEntity>();
        //Calls the get exact list function
        sendAction("getExactList");
        //Tells the functionwhat table to return
        sendAction(Table);
        sendAction(IDType);
        sendAction(ID);

        try {
            list =(ArrayList<Entity.DBEntity>) objIs.readObject();
        } catch (ClassNotFoundException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        } catch (NullPointerException e) {
            logger.error(e.toString());
        }

        try {
            logger.info((String) objIs.readObject());
        } catch (ClassNotFoundException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        }

        return list;

    }


    public  DBEntity findLastEntity(String table, String columnName) {
        //Calls the get list function
        sendAction("findLastEntity");
        sendAction(table);
        sendAction(columnName);

        DBEntity entity = null;

        try {
            entity = (DBEntity)objIs.readObject();
            logger.info(entity);
        } catch (ClassNotFoundException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        } catch (NullPointerException e) {
            logger.error(e.toString());
        }
        

        try {
            System.out.println((String) objIs.readObject());
        } catch (ClassNotFoundException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        }

        return entity;

    }

    public void addEntity(DBEntity entity) {

        sendAction("addEntity");

        sendEntity(entity);

        try {
            logger.info((String) objIs.readObject());
        } catch (ClassNotFoundException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        }
        
    }
    public void checkOutEntity(DBEntity entity) {

        sendAction("checkOutEntity");

        sendEntity(entity);

        try {
            logger.info((String) objIs.readObject());
        } catch (ClassNotFoundException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        }
        
    }
    

    public DBEntity findEntity(String Table,String IDType, String ID) {
        
        //Calls the get list function
        sendAction("findEntity");
        //Tells the functionwhat table to return
        sendAction(Table);
        sendAction(IDType);
        sendAction(ID);
        DBEntity entity = null;

        try {
            entity = (DBEntity)objIs.readObject();
        } catch (ClassNotFoundException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        } catch (NullPointerException e) {
            logger.error(e.toString());
        }
        

        try {
            logger.info((String) objIs.readObject());

        } catch (ClassNotFoundException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        }
        return entity;
    }

    public DBEntity findEntity(DBEntity entity,  Integer ID) {
        //Calls the get list function
        sendAction("findEntitySimple");
        sendInteger(ID);
        sendEntity(entity);

        DBEntity entityOut = null;

        try {
            entityOut = (DBEntity)objIs.readObject();
        } catch (ClassNotFoundException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        } catch (NullPointerException e) {
            logger.error(e.toString());
        }
        

        try {
            logger.info((String) objIs.readObject());
        } catch (ClassNotFoundException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        }

        return entityOut;

    }

    public DBEntity findEntity(DBEntity entity, String ID) {
        //Calls the get list function
        sendAction("findEntitySimpleString");
        sendAction(ID);
        sendEntity(entity);

        DBEntity entityOut = null;

        try {
            entityOut = (DBEntity)objIs.readObject();
        } catch (ClassNotFoundException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        } catch (NullPointerException e) {
            logger.error(e.toString());
        }


        try {
            logger.info((String) objIs.readObject());
        } catch (ClassNotFoundException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        }

        return entityOut;

    }

    public void alterEntity(DBEntity entity, Integer ID) {
        //Calls the get list function
        sendAction("alterEntity");
        sendInteger(ID);
        sendEntity(entity);

        try {
            logger.info((String) objIs.readObject());
        } catch (ClassNotFoundException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

    public void alterEntity(DBEntity entity, String ID) {
        //Calls the get list function
        sendAction("alterEntityString");
        sendAction(ID);
        sendEntity(entity);

        try {
            logger.info((String) objIs.readObject());
        } catch (ClassNotFoundException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }

    public Integer removeEntity(DBEntity entity,  Integer ID) {
        //Sends the action to the Server
        sendAction("removeEntity");
        sendInteger(ID);
        sendEntity(entity);
        Integer result = -2;
        try {
            result = (Integer) objIs.readObject();
            logger.info((String) objIs.readObject());

        } catch (ClassNotFoundException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        }
        finally{
            return result;
        }

    }

    public Integer removeEntity(DBEntity entity, String ID) {
        //Calls the get list function
        sendAction("removeEntityString");
        sendAction(ID);
        sendEntity(entity);
        Integer result = -2;
        try {
            result = (Integer) objIs.readObject();
            logger.info((String) objIs.readObject());

        } catch (ClassNotFoundException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        }
        finally{
            return result;
        }

    }

    public void removeInvoiceItem(DBEntity entity, Integer invoiceNum, String productCode) {
        //Calls the get list function
        sendAction("removeInvoiceItem");
        sendEntity(entity);
        sendInteger(invoiceNum);
        sendAction(productCode);


        try {
            logger.info((String) objIs.readObject());
        } catch (ClassNotFoundException e) {
            logger.error(e.toString());
        } catch (IOException e) {
            logger.error(e.toString());
        }
    }



    //endregion
    
}
