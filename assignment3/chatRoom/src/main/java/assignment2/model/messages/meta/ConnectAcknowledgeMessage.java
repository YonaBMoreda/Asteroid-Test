package assignment2.model.messages.meta;

/**
 * Class for a client to send in response to a server connect message.
 */
public class ConnectAcknowledgeMessage extends ConnectMessage {

    public ConnectAcknowledgeMessage(String userId) {
        super(userId);
    }
}
