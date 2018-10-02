package assignment2.model.messages.meta;

import assignment2.model.messages.Message;

/**
 * Message designated for users when they first connect to a server.
 */
public class ConnectMessage extends Message {

    public static final String CONNECT_MESSAGE = "__CONNECT__";

    public ConnectMessage(String userId) {
        super(userId, CONNECT_MESSAGE);
    }
}
