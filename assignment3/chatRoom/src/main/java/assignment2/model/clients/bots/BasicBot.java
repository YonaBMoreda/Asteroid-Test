package assignment2.model.clients.bots;

import assignment2.model.clients.ChatClient;
import assignment2.model.messages.Message;
import assignment2.model.messages.ServerMessage;

import java.util.Random;

/**
 * This bot is the most basic of bots, used mostly for testing.
 */
public class BasicBot extends ChatClient {

    public BasicBot(String id) {
        super(id);
    }

    @Override
    public void onConnect(String roomId) {
        this.sendChat("Hello, I'm a basic bot, and my name is "+this.getUserId());
    }

    @Override
    public void onChatMessageReceived(Message message) {
        if (message instanceof ServerMessage){
            this.sendChat("Thank you for the information, server.");
        }
    }
}
