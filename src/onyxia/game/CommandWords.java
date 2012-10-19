package onyxia.game;

import java.util.HashMap;

/**
 * Class CommandWords - A Class that contains all the CommandWord objects in order
 * to be available from other classes.
 * 
 * This class is part of the "Onyxia" application. 
 * 
 * "Onyxia" is a very interesting adventure game. Users can enter rooms, 
 * interact with characters and items and have fun! The game is already filled
 * with a rich functionality and can be extended easily.
 *
 * @author  Michail Vogiatzis
 * @version 1.0 (November 2011)
 */
public class CommandWords {

    /**
     * A HashMap between a command word and the CommandWord associated with it
     */
    private HashMap<String, CommandWord> validCommands;

    /**
     * Constructor - Initializes the HashMap that contains all the valid commands.
     */
    public CommandWords()
    {
    	validCommands = new HashMap<String, CommandWord>();
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
    }
    
    /**
     * Find the CommandWord associated with a command word.
     * @param commandWord The word to look up.
     * @return The CommandWord corresponding to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public CommandWord getCommandWord(String commandWord)
    {
        CommandWord command = validCommands.get(commandWord);
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }
    

    /**
     * Check whether a given String is a valid command word.
     * Return true if it is, false if it isn't.
     * @param aString The string to be checked if it is a valid command or not.
     * @return boolean True if it is a valid command, false otherwise.
     **/
    public boolean isCommand(String aString)
    {
    	return validCommands.containsKey(aString);
    }

    /**
     * Returns a String of all valid commands.
     * @return String The String that contains all the valid commands seperated by space
     */
    public String showAll()
    {
        StringBuilder commands = new StringBuilder();
        for(String command : validCommands.keySet()) {
            commands.append(command).append(" ");   //seperate them by space
        }
        return commands.toString();
        
    }
}
