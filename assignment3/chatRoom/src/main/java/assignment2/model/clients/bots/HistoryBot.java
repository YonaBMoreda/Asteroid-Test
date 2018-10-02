package assignment2.model.clients.bots;

import assignment2.Utilities.Utils;
import assignment2.model.clients.ChatClient;
import assignment2.model.messages.Message;
import assignment2.model.messages.MessageHistory;

import java.util.Random;

/**
 * This is a bot which remembers messages which have been sent, so that it may use them in its messages.
 */
public class HistoryBot extends ChatClient {

    //Chance for the bot to send a message.
    private static final float messageChance = 0.20f;
    //The message history.
    protected MessageHistory history;

    public HistoryBot(String id) {
        super(id);
        this.history = new MessageHistory();
    }

    @Override
    public void onConnect(String roomId) {
        sendChat("I am a history bot, and I will construct messages from other's messages.");
    }

    @Override
    public void onChatMessageReceived(Message message) {
        this.history.addMessage(message);
        if (new Random().nextFloat() < messageChance){
            this.sendChat(Utils.getRandomMessage(this.history.getMessageContents()));
        }
    }
}
