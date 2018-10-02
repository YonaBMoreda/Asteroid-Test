package assignment2.model.clients;

import assignment2.model.messages.*;
import assignment2.model.messages.meta.ConnectAcknowledgeMessage;
import assignment2.model.messages.meta.ConnectMessage;
import assignment2.model.messages.meta.DisconnectAcknowledgeMessage;
import assignment2.model.messages.meta.DisconnectMessage;

/**
 * Main client class for anything that connects to the server.
 */
public abstract class ChatClient extends Communicator {

    private boolean clientConnected = false;

    public ChatClient(String id){
        super(id);
    }

    public String getUserId(){
        return this.getName();
    }

    /**
     * Quits the thread, shutting down the bot.
     * Attempts to disconnect from any servers, too.
     */
    public void quit(){
        disconnect();
        super.quit();
    }

    @Override
    public boolean isConnected() {
        return this.clientConnected;
    }

    /**
     * Attempts to connect to a certain chat room on a port, and open streams for communication.
     * @param port The port to connect to.
     */
    public void connect(int port){
        super.connect("localhost", port);
        this.sendMessage(new ConnectMessage(this.getName()));
    }

    /**
     * Attempts to disconnect the client from its currently connected server.
     */
    public void disconnect(){
        this.sendMessage(new DisconnectMessage(this.getUserId()));
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
        super.disconnect();
        this.clientConnected = false;
    }

    /**
     * Sends a string message to the server.
     * @param chatMessage The string to send to the server.
     */
    public void sendChat(String chatMessage){
        this.sendMessage(new Message(this.getName(), chatMessage));
    }

    /**
     * Any child class may define what to do when a message is received.
     * Messages are filtered out which were sent by this client.
     * @param message The message which was received.
     */
    public void onMessageReceived(Message message){
        if (message.getUserId().equals(this.getName())){
            return;
        }
        if (message instanceof ConnectMessage && !this.clientConnected) {
            sendMessage(new ConnectAcknowledgeMessage(this.getUserId()));
            onConnect(message.getUserId());
            this.clientConnected = true;
        } else {
            onChatMessageReceived(message);
        }
    }

    /**
     * What to do when the client connects to a room for the first time.
     * @param roomId The name of the room.
     */
    public abstract void onConnect(String roomId);

    /**
     * What to do when the client receives a chat message sent over the chat room.
     * @param message The message to send.
     */
    public abstract void onChatMessageReceived(Message message);

}
