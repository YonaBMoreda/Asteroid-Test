package assignment2.model.messages;

/**
 * A message whose sender ID is always simply 'server'.
 */
public class ServerMessage extends Message {

    public ServerMessage(String content) {
        super("server", content);
    }

}
