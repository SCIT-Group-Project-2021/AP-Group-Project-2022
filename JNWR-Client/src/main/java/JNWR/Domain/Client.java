package JNWR.Domain;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;

import Entity.*;

public class Client {

    private ObjectInputStream objIs;
    private static ObjectOutputStream objOs;
    private Socket connectionSocket;
    private String action = "";

    public Client () {
        this.createConnection();
        this.configureStreams();
        
    }

    public Client (String ip) {
        this.createConnection(ip);
        this.configureStreams();
        
    }

    private void configureStreams() {
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

    private void createConnection() {
        try {
            connectionSocket = new Socket("127.0.0.1",8888);
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

    public void sendEntity(DBEntity entity) {
        sendAction(entity.getAction());
        try {
            objOs.writeObject(entity);
        } catch (IOException e) {
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

    public void getList(String Table) {
        //Calls the get list function
        sendAction("getList");
        //Tells the functionwhat table to return
        sendAction(Table);

        try {
            System.out.println(Arrays.toString(((List) objIs.readObject()).toArray()));
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
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

    //endregion
    
}
