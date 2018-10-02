package assignment2.model.clients.bots;

import assignment2.model.clients.ChatClient;
import assignment2.model.messages.Message;
import assignment2.model.server.ChatRoom;

import java.util.List;
import java.util.Random;

/**
 * A bot which can connect to and disconnect from chat rooms whenever it wants.
 */
public class MigratoryBot extends ChatClient {

    private List<ChatRoom> chatRooms;
    private String currentChatRoomName;

    private Thread switcherThread = new Thread(() -> {
        while (true) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            switchChatRoom();
        }
    });

    public MigratoryBot(String id, List<ChatRoom> rooms) {
        super(id);
        this.chatRooms = rooms;
    }

    /**
     * Switches to a random other chat room.
     */
    private void switchChatRoom(){
        ChatRoom room = chatRooms.get(new Random().nextInt(chatRooms.size()));
        if (!room.getName().equals(this.currentChatRoomName)) {
            this.disconnect();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            this.connect(room.getPort());
        }
    }

    @Override
    public void onConnect(String roomId) {
        this.currentChatRoomName = roomId;
        this.sendChat("Hello, I am a migrating bot, and my name is "+this.getUserId());
    }

    @Override
    public void onChatMessageReceived(Message message) {
        switchChatRoom();
    }
}
