package assignment2.model.messages.meta;

import assignment2.model.messages.Message;

/**
 * This is a custom type of message sent from clients to a server, in order to signal that they wish to disconnect.
 */
public class DisconnectMessage extends Message {

    public static final String DISCONNECT_MESSAGE = "__DISCONNECT__";

    public DisconnectMessage(String userId) {
        super(userId, DISCONNECT_MESSAGE);
    }

}
