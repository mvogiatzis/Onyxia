package onyxia.game;

/**
 * Enumerator CommandWord - A CommandWord enumerator in Onyxia game.
 * 
 * This class is part of the "Onyxia" application. 
 * 
 * "Onyxia" is a very interesting adventure game. Users can enter rooms, 
 * interact with characters and items and have fun! The game is already filled
 * with a rich functionality and can be extended easily.
 * 
 * Representations for all the valid command words for the game
 * along with a string in a particular language.
 * 
 * @author Michail Vogiatzis
 * @version 1.0 (November 2011)
 */
public enum CommandWord {
	
    // A value for each command word along with its
    // corresponding string.
    GO("go"), QUIT("quit"), HELP("help"), USE("use"), PICK("pick"), TALK("talk"), ITEMS("items"), UNKNOWN("?");
    
    /*
     * String that contains the CommandWord
     */
    private String commandString;
    
    /**
     * Initialize with the corresponding command word.
     * @param commandString The command string.
     */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    /**
     * Get the command word as a string.
     * @return String The command word as a string.
     */
    @Override
    public String toString()
    {
        return commandString;
    }
}
