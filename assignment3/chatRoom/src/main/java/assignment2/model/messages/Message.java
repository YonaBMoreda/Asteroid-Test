package assignment2.model.messages;

import java.io.Serializable;

/**
 * Class for any message sent by a client or server.
 */
public class Message implements Serializable {

    //The main content of the message.
    protected String content;
    //A way to identify a user who sent the message.
    protected String userId;
    //Required serial id for serialization.
    private static final long serialVersionUID = 1L;

    public Message(String userId, String content){
        this.content = content;
        this.userId = userId;
    }

    public String getContent() {
        return content;
    }

    public String getUserId(){
        return this.userId;
    }

    @Override
    public String toString(){
        return this.userId + ": " + this.content;
    }

}
