package assignment2;

import assignment2.model.clients.ChatClient;
import assignment2.model.clients.bots.BasicBot;
import assignment2.model.clients.bots.HistoryBot;
import assignment2.model.clients.bots.LocalSpamBot;
import assignment2.model.clients.bots.MigratoryBot;
import assignment2.model.server.ChatRoom;
import assignment2.model.userInterface.UserWindow;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Main {

    private static List<ChatRoom> chatRooms = new ArrayList<>();
    private static List<ChatClient> clients = new ArrayList<>();

    public static void main(String[] args) {
        createRooms();
        createClients();
        connectClients();
        UserWindow userWindow = new UserWindow();

//        sleep(3000);
//        System.out.println(room1.getManagers().size()+" / "+bots.size()+" bots connected.");
//        for (ChatClient bot : bots){
//            if (!bot.isConnected()){
//                System.out.println(bot.getUserId()+" is not connected.");
//                //bot.connect(6960);
//            }
//        }
//        room1.sendMessage(new ServerMessage("I am your overlord. Please accept me."));
//        sleep(3000);
//        bots.get(0).quit();

    }

    private static void shutdown(){
        chatRooms.forEach(ChatRoom::shutdown);
    }

    /**
     * Generate some chat rooms and start them.
     */
    private static void createRooms(){
        chatRooms.add(new ChatRoom("Room1", 6960));
        chatRooms.add(new ChatRoom("Room2", 6961));

        for (ChatRoom room : chatRooms){
            room.start();
        }
    }

    /**
     * Generate some clients and start them.
     */
    private static void createClients(){
        //clients.add(new BasicBot("test1"));
        clients.add(new LocalSpamBot("SpamBot1"));
        clients.add(new LocalSpamBot("SpamBot2"));
        clients.add(new MigratoryBot("Traveller", chatRooms));
        clients.add(new MigratoryBot("Adventurer", chatRooms));
        clients.add(new HistoryBot("Gilgamesh"));

        for (ChatClient client : clients){
            client.start();
        }
    }

    private static void connectClients(){
        Random rand = new Random();
        for (ChatClient client : clients){
            client.connect(chatRooms.get(rand.nextInt(chatRooms.size())).getPort());
            sleep(100);
        }
    }

    private static void sleep(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException e){
            e.printStackTrace();
        }
    }

}
