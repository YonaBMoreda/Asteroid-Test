package assignment2.model.messages;

/**
 * Interface for any class which is a receiver of messages.
 */
public interface MessageReceiver {

    void onMessageReceived(Message message);

}
