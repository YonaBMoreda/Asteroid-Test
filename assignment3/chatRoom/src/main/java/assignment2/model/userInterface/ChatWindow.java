package assignment2.model.userInterface;

import assignment2.model.messages.Message;

import javax.swing.*;
import javax.swing.text.*;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Date;
import java.util.Locale;

/**
 * A generic window which can be used to display messages from a chat room.
 */
public class ChatWindow extends JFrame {

    //The starting position of windows, so that they can appear to not overlap.
    private static Point windowStartPosition = new Point(10, 10);
    //Main text area that displays messages.
    private JTextPane textPane;
    //Preferred size of the window.
    private static final Dimension preferredSize = new Dimension(800, 600);
    //List of online users.
    private JList<String> userList;
    private DefaultListModel<String> userListModel;
    //Attributes for displaying styled text.
    private static Style timeStampStyle;
    private static Style userIdStyle;
    private static Style errorStyle;
    private static Style statusStyle;


    public ChatWindow(String chatName){
        super(chatName);
        //Create the text pane.
        this.textPane = new JTextPane();
        this.textPane.setAutoscrolls(true);
        this.textPane.setEditable(false);
        this.textPane.setBackground(Color.white);
        JScrollPane scrollPane = new JScrollPane(this.textPane);
        //Create the list.
        this.userListModel = new DefaultListModel<>();
        this.userList = new JList<>(this.userListModel);
        this.getContentPane().setLayout(new BorderLayout());
        JScrollPane listPane = new JScrollPane(this.userList);
        listPane.setPreferredSize(new Dimension(150, -1));
        //Add components.
        this.getContentPane().add(scrollPane, BorderLayout.CENTER);
        this.getContentPane().add(listPane, BorderLayout.EAST);
        //Final preparations.
        this.setLocation(windowStartPosition);
        windowStartPosition.x += 50;
        windowStartPosition.y += 20;
        this.createStyles();
        this.setPreferredSize(preferredSize);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
        this.pack();
    }

    /**
     * Creates the styles used for fancy text.
     */
    private void createStyles(){
        timeStampStyle = this.textPane.addStyle("timeStampStyle", null);
        StyleConstants.setForeground(timeStampStyle, new Color(38, 38, 38));
        StyleConstants.setBackground(timeStampStyle, new Color(234, 234, 234));
        userIdStyle = this.textPane.addStyle("userIdStyle", null);
        StyleConstants.setForeground(userIdStyle, new Color(0, 9, 119));
        StyleConstants.setBold(userIdStyle, true);
        errorStyle = this.textPane.addStyle("errorStyle", null);
        StyleConstants.setForeground(errorStyle, Color.red);
        statusStyle = this.textPane.addStyle("statusStyle", null);
        StyleConstants.setForeground(statusStyle, Color.blue);
    }

    /**
     * Appends a string of text to the window, with a given style.
     * @param text The text to append.
     */
    private void appendText(String text, Style style){
        Document doc = this.textPane.getStyledDocument();
        try {
            doc.insertString(doc.getLength(), text, style);
        } catch (BadLocationException e){
            System.err.println("Unable to insert text into the text pane.");
        }
    }

    /**
     * Appends the current time to the document.
     */
    private void appendTime(){
        Instant timestamp = Instant.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).withLocale(Locale.GERMANY).withZone(ZoneId.systemDefault());
        SimpleDateFormat f = new SimpleDateFormat("HH:mm:ss");
        this.appendText('['+f.format(new Date())+"] ", timeStampStyle);
    }

    /**
     * Logs an error message, in red color!
     * @param errorMessage The error message to display.
     */
    public void logError(String errorMessage){
        appendTime();
        appendText(errorMessage+'\n', errorStyle);
    }

    /**
     * Appends a message to the window, with time, and the user id.
     * @param message The message to display.
     */
    public void logMessage(Message message){
        appendTime();
        appendText(message.getUserId()+": ", userIdStyle);
        appendText(message.getContent()+'\n', null);
    }

    /**
     * Logs a message about the status of the chat room.
     * @param message The string to write.
     */
    public void logStatus(String message){
        appendTime();
        appendText(message+'\n', statusStyle);
    }

    /**
     * Adds a user's name to the displayed list.
     * @param userID The name to add.
     */
    public void addClientToList(String userID) {
        this.userListModel.addElement(userID);
    }

    /**
     * Removes a user's name from the list.
     * @param userID The name to remove.
     */
    public void removeClientFromList(String userID) {
        this.userListModel.removeElement(userID);
    }
}
