package assignment2.model.clients;

import assignment2.model.messages.Message;
import assignment2.model.messages.MessageReceiver;

import java.io.*;
import java.net.Socket;

/**
 * Class for anything which communicates with a socket. The thread waits for objects to read, and calls
 * methods so that child classes may control functionality.
 * This class also has a simulated latency, because without this, the sockets tend to fail erratically.
 */
public abstract class Communicator extends Thread implements MessageReceiver {

    public static final int SIMULATED_LATENCY = 100;

    //The socket used to send and receive.
    private Socket socket;
    //Input and output streams for data transfer.
    private ObjectOutputStream outputStream;
    private ObjectInputStream inputStream;
    //Whether or not the thread should run.
    protected boolean shouldRun = true;
    //Flag to determine if connected.
    private boolean connected = false;

    public Communicator(String threadname){
        this.setName(threadname);
    }

    /**
     * Constructs a communicator with a name and creates the socket and streams instantly.
     * @param threadname The name of the thread.
     * @param socket The socket to bind with.
     */
    public Communicator(String threadname, Socket socket){
        super(threadname);
        this.socket = socket;
        try {
            this.outputStream = new ObjectOutputStream(new BufferedOutputStream(socket.getOutputStream()));
            this.outputStream.flush();
            this.inputStream = new ObjectInputStream(new BufferedInputStream(socket.getInputStream()));
            this.connected = true;
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public Socket getSocket(){
        return this.socket;
    }

    public boolean isConnected(){
        return this.connected;
    }

    /**
     * Attempts to connect to the given hostname and port.
     * @param hostname The hostname, or website.
     * @param port The port on the host's computer to connect to.
     */
    public synchronized void connect(String hostname, int port){
        try {
            this.socket = new Socket(hostname, port);
            this.outputStream = new ObjectOutputStream(new BufferedOutputStream(this.socket.getOutputStream()));
            this.outputStream.flush();
            this.inputStream = new ObjectInputStream(new BufferedInputStream(this.socket.getInputStream()));
            this.connected = true;
        } catch (IOException e) {
            System.err.println("Unable to create connection.");
            e.printStackTrace();
        }
    }

    /**
     * Disconnects from the current socket.
     */
    public void disconnect(){
        if (this.connected) {
            try {
                this.socket.shutdownOutput();
                this.socket.shutdownInput();
                this.socket.close();
                this.connected = false;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Quits the thread, and disconnects.
     */
    public void quit(){
        this.disconnect();
        this.shouldRun = false;
    }

    /**
     * Sends a message to the socket connection.
     * @param message The message to send.
     */
    public final void sendMessage(Message message){
        if (this.connected){
            try {
                this.outputStream.writeObject(message);
                this.outputStream.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method to define functionality when a message is received.
     * @param message The message which was received.
     */
    public abstract void onMessageReceived(Message message);

    /**
     * Receives messages from the input stream, and blocks the thread if there are none.
     */
    protected void receiveMessages() {
        if (this.connected) {
            try {
                Object obj = this.inputStream.readObject();
                Message message = (Message) obj;
                onMessageReceived(message);
            } catch (IOException | ClassNotFoundException e) {
                System.err.println(this.getName()+": Unable to read object from stream.");
            }
        }
    }

    @Override
    public void run() {
        while (shouldRun){
            try {
                Thread.sleep(SIMULATED_LATENCY);
            } catch (InterruptedException e){
                e.printStackTrace();
            }
            receiveMessages();
        }
    }
}
