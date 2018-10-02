package assignment2.model.messages;

import java.util.ArrayList;
import java.util.List;

/**
 * Class to hold many messages in a particular stream.
 */
public class MessageHistory {

    private List<Message> history;

    public MessageHistory(){
        this.history = new ArrayList<>();
    }

    /**
     * Adds a message to the history.
     * @param newMessage The message to add.
     */
    public void addMessage(Message newMessage){
        this.history.add(newMessage);
    }

    /**
     * Returns a list of the messages in this history.
     * @return A list of messages contained in this history.
     */
    public Message[] getMessages(){
        Message[] messages = new Message[this.history.size()];
        this.history.toArray(messages);
        return messages;
    }

    public List<String> getMessageContents(){
        List<String> strings = new ArrayList<>();
        for (Message msg : this.history){
            strings.add(msg.getContent());
        }
        return strings;
    }

    /**
     * Returns the <code>count</code> most recent messages.
     * @param count The number of messages to retrieve.
     * @return An array of the most recent messages, ordered from most recent, to oldest.
     */
    public Message[] getRecentMessages(int count){
        //If the user inputs a count greater than the number of messages in the history, all messages will be retrieved.
        if (count > this.history.size()){
            count = this.history.size();
        }
        Message[] messages = new Message[count];
        for (int i = 0; i < count; i++){
            messages[i] = this.history.get(i);
        }
        return messages;
    }

}
