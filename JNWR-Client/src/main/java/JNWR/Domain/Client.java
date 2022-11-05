package JNWR.Domain;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

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

    public void closeConnection() {
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
        this.action = action;
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
    }
    public void addEntity(DBEntity entity) {
        sendAction(entity.getAction());
        sendEntity();
    }

    //endregion
    
}
