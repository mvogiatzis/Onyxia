package onyxia.game;

import javax.swing.JOptionPane;

/**
 * Enumerator MessageType - An Enumerator in "Onyxia" application.
 * 
 * This enumerator is part of the "Onyxia" application. 
 * 
 * "Onyxia" is a very interesting adventure game. Users can enter rooms, 
 * interact with characters and items and have fun! The game is already filled
 * with a rich functionality and can be extended easily.
 * 
 * This class holds an enumeration of all JOption message types of the game.
 * It matches meaningful (semantic) words with int values of JOption message types.
 * 
 * @author  Michail Vogiatzis
 * @version 1.0 (November 2011)
 */
public enum MessageType {
    
    //A value for each message type along with its corresponding int JOptionPane property
    GAMEOVER(JOptionPane.ERROR_MESSAGE), WIN(JOptionPane.DEFAULT_OPTION);
    
    /**
     * Integer that contains the type of the message
     */
    private int messageType;
    
    /**
     * Initialize with the corresponding message type.
     * @param messageType The command string.
     */
    MessageType(int messageType)
    {
        this.messageType = messageType;
    }
    
    /**
     * Get the messageType
     * @return int Return the MessageType int value
     */
    public int getMsg()
    {
        return messageType;
    }
    
}
