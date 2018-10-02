package assignment2.model.server;

import assignment2.model.clients.Communicator;
import assignment2.model.messages.Message;
import assignment2.model.messages.MessageHistory;
import assignment2.model.userInterface.ChatWindow;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ChatRoom extends Thread {

    //The port this chat room will operate on.
    private final int port;
    //A server socket to handle incoming connections.
    private ServerSocket serverSocket;
    //Window to display the message history of this chat room.
    private ChatWindow display;
    //Message history.
    private MessageHistory history;
    //List of all socket managers, for every client currently connected to the chat room.
    private List<ClientSocketManager> socketManagers;
    //Boolean value to determine if the server should run.
    private boolean shouldRun = true;

    public ChatRoom(String name, int port){
        super.setName(name);
        this.port = port;
        this.history = new MessageHistory();
        this.socketManagers = new ArrayList<>();
        this.display = new ChatWindow(name);
        try {
            this.serverSocket = new ServerSocket(port);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<ClientSocketManager> getManagers(){
        return this.socketManagers;
    }

    public int getPort(){
        return this.port;
    }

    public ChatWindow getDisplay() {
        return display;
    }

    /**
     * Shuts down each socket manager for the chat room, and the thread stops.
     */
    public void shutdown(){
        this.socketManagers.forEach(Communicator::quit);
        this.display.logStatus("Shutting down the chat room.");
        this.shouldRun = false;
    }

    /**
     * Broadcasts a message to all currently connected clients.
     * @param message The message to send.
     */
    public synchronized void sendMessage(Message message){
        this.history.addMessage(message);
        this.display.logMessage(message);
        socketManagers.forEach(manager -> {
            manager.sendMessage(message);
        });
    }

    /**
     * Logs some line of text to the output window, separate from usual chat.
     * This text will not be broadcast to bots, and is only used for clarity.
     * @param message The message to display.
     */
    public synchronized void logMessage(String message){
        this.display.logStatus(message);
    }

    /**
     * Updates the client list when a client connects or disconnects
     * @param addOrRemoveClient Controls whether to add or remove client from list
     * @param userID The identification of the client
     */
    public synchronized void updateClientList(int addOrRemoveClient ,String userID) {
        if(addOrRemoveClient == 1) {
            display.addClientToList(userID);
        } else if(addOrRemoveClient == 2) {
            display.removeClientFromList(userID);
        }
    }

    /**
     * Removes a manager from the list of active managers.
     * This will be called by the manager that wishes to remove itself when their client chooses to disconnect.
     * @param manager The manager which will be removed.
     */

    public synchronized void removeManager(ClientSocketManager manager){
        socketManagers.remove(manager);
    }

    /**
     * Adds a manager to the list of active managers.
     * @param manager The manager to add.
     */
    public synchronized void addManager(ClientSocketManager manager) {
        socketManagers.add(manager);
    }

    /**
     * Method to wait for a client to connect to this chat room.
     * Once a client connects, a manager is created to listen for messages, and to control sending messages back to
     * the client.
     */
    private void acceptNewClient(){
        try {
            Socket socket = this.serverSocket.accept();
            ClientSocketManager manager = new ClientSocketManager(socket, this);
            manager.start();
            addManager(manager);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run(){
        while (shouldRun) {
            acceptNewClient();
        }
    }

}
