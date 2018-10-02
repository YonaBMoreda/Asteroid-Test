package assignment2.model.server;

import assignment2.model.clients.Communicator;
import assignment2.model.messages.*;
import assignment2.model.messages.meta.ConnectAcknowledgeMessage;
import assignment2.model.messages.meta.ConnectMessage;
import assignment2.model.messages.meta.DisconnectAcknowledgeMessage;
import assignment2.model.messages.meta.DisconnectMessage;

import java.net.Socket;

/**
 * Class for a thread-per-socket way to manage each socket in a given chat room.
 * A manager is created once a socket is gained from a call to <code>accept</code>, and this manger will try to read
 * any messages coming from the socket, and also provides a way to send data to the client.
 */
public class ClientSocketManager extends Communicator {

    private boolean clientConnected = false;
    private String clientName;

    //The time limit for a client to acknowledge it is connected.
    public static final int CONNECT_TIMEOUT = 1000;

    //The room which created this manager.
    private ChatRoom parentRoom;

    public ClientSocketManager(Socket socket, ChatRoom parentRoom){
        super("ClientSocketManager", socket);
        this.parentRoom = parentRoom;
    }

    @Override
    public void quit(){
        super.quit();
        this.parentRoom.removeManager(this);
        this.clientConnected = false;
    }

    @Override
    public void onMessageReceived(Message message){
        if (message instanceof DisconnectMessage){
            //What to do if the user disconnects.
            this.parentRoom.updateClientList(2, message.getUserId());
            this.parentRoom.logMessage("Client disconnected: " + message.getUserId());
            //this.quit();
            this.parentRoom.removeManager(this);
            this.clientConnected = false;
            this.shouldRun = false;
        } else if (message instanceof ConnectMessage){
            //What to do if a client recently connected.
            if (message instanceof ConnectAcknowledgeMessage){
                this.parentRoom.updateClientList(1,message.getUserId());
                this.parentRoom.logMessage("Client connected: "+message.getUserId());
                this.clientName = message.getUserId();
                this.clientConnected = true;
            } else {
                this.sendMessage(new ConnectMessage(this.parentRoom.getName()));
                new Thread(() -> {
                    try {
                        Thread.sleep(CONNECT_TIMEOUT);
                    } catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    if (!clientConnected){
                        System.err.println("Client connection timed out, removing manager.");
                        quit();
                    }
                }).start();
            }
        } else {
            //This is a normal message.
            this.parentRoom.sendMessage(message);
        }
    }

}
