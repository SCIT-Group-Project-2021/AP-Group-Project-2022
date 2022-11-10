package JNWR.Domain;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.net.ConnectException;

import Entity.*;

public class Client {

    private ObjectInputStream objIs;
    private static ObjectOutputStream objOs;
    private Socket connectionSocket;
    private String action = "";

    public Client () {
        try {
            this.createConnection();
            this.configureStreams();
        }catch (ConnectException e) {
        // TODO: handle exception
            e.printStackTrace();
        } catch (NullPointerException e) {
            e.printStackTrace();
        }catch (IOException e) {
            e.printStackTrace();
        }
        
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
            e.printStackTrace();
        }
    }

    private void closeConnection() {
        try {
            objIs.close();
            objOs.close();
            connectionSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createConnection() throws ConnectException {
        try {
            connectionSocket = new Socket("127.0.0.1",8888);
        }catch (ConnectException e) {
            // TODO: handle exception
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createConnection(String ip) {
        try {
            connectionSocket = new Socket(ip,8888);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //region Utility Methods

    public void sendAction(String action) {
        try {
            objOs.writeObject(action);
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void sendInteger(Integer value) {
        try {
            objOs.writeObject(value);
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

    public void sendClass(Class classObject) {
        try {
            objOs.writeObject(classObject);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public ArrayList<DBEntity> getList(String Table) {

        ArrayList<DBEntity> list = new ArrayList<DBEntity>();
        //Calls the get list function
        sendAction("getList");
        //Tells the functionwhat table to return
        sendAction(Table);

        try {
            list =(ArrayList<DBEntity>) objIs.readObject();
            System.out.println(Arrays.toString(list.toArray()));
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NullPointerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        try {
            System.out.println((String) objIs.readObject());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return list;
  
    }

    public void addEntity(DBEntity entity) {

        sendAction("addEntity");

        sendEntity(entity);

        try {
            System.out.println((String) objIs.readObject());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        
    }

    public void findEntity(String Table,String IDType, String ID) {
        //Calls the get list function
        sendAction("findEntity");
        //Tells the functionwhat table to return
        sendAction(Table);
        sendAction(IDType);
        sendAction(ID);

        try {
            DBEntity entity = (DBEntity)objIs.readObject();
            System.out.println(entity);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NullPointerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

        try {
            System.out.println((String) objIs.readObject());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void findEntity(DBEntity entity,  Integer ID) {
        //Calls the get list function
        sendAction("findEntitySimple");
        sendInteger(ID);
        sendEntity(entity);

        try {
            DBEntity entityOut = (DBEntity)objIs.readObject();
            System.out.println(entityOut);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NullPointerException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        

        try {
            System.out.println((String) objIs.readObject());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


    }

    public void alterEntity(DBEntity entity,  Integer ID) {
        //Calls the get list function
        sendAction("alterEntity");
        sendInteger(ID);
        sendEntity(entity);

        try {
            System.out.println((String) objIs.readObject());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    public void removeEntity(DBEntity entity,  Integer ID) {
        //Calls the get list function
        sendAction("removeEntity");
        sendInteger(ID);
        sendEntity(entity);

        try {
            System.out.println((String) objIs.readObject());
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    //endregion
    
}
