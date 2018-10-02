package assignment2.model.clients;

import assignment2.model.messages.Message;

/**
 */
public class UserClient extends ChatClient {

    public UserClient(String id) {
        super(id);
    }

    @Override
    public void onConnect(String roomId) {

    }

    @Override
    public void onChatMessageReceived(Message message) {

    }
}
