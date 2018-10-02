package assignment2.model.userInterface;

import javax.swing.*;
import java.awt.*;

/**
 * Window for a user.
 */
public class UserWindow extends ChatWindow {

    private JTextField chatEntryField;

    public UserWindow() {
        super("User window");
        this.chatEntryField = new JTextField();
        this.getContentPane().add(this.chatEntryField, BorderLayout.SOUTH);
        this.pack();
    }

}
