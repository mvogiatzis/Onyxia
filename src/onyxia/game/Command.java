package onyxia.game;

/**
 * Class Command - A Command in Onyxia game.
 * 
 * This class is part of the "Onyxia" application. 
 * 
 * "Onyxia" is a very interesting adventure game. Users can enter rooms, 
 * interact with characters and items and have fun! The game is already filled
 * with a rich functionality and can be extended easily.
 *
 * This class holds information about a command that was issued by the user.
 * A command currently consists of three strings: a command word, a second
 * word and a third word.
 * For example, if the command was "use bat woman", then the three strings
 * obviously are "use" , "bat" and "woman".
 *
 * The way this is used is: Commands are already checked for being valid
 * command words. If the user entered an invalid command (a word that is not
 * known) then the command word is <null>.
 *
 * If the command had only one word (i.e. help), then the second and third words are <null>.
 * Also, commands with two words are also applicable. For example, "pick keys" is a command where
 * only two words are used, thus, the third word is <null>.
 * 
 * @author  Michail Vogiatzis
 * @version 1.0 (November 2011)
 */
public class Command {
    
    /**
     * The CommandWord
     */
    private CommandWord commandWord;
    private String secondWord;
    private String thirdWord;

    /**
     * Create a command object. First, second and third words must be supplied, but
     * either one, two or three can be null. The third word is only for specific commands.
     * The command word should be null to indicate that this was a command that is not 
     * recognized by this game.
     */
    public Command(CommandWord firstWord, String secondWord, String thirdWord)
    {
        commandWord = firstWord;
        this.secondWord = secondWord;
        this.thirdWord = thirdWord;
    }

    /**
     * Return the command word (the first word) of this command. If the
     * command was not understood, the result is null.
     * @return CommandWord The CommandWord object to be returned
     */
    public CommandWord getCommandWord()
    {
        return commandWord;
    }

    /**
     * Return the second word of this command. Returns null if there was no
     * second word.
     * 
     * @return String The second word of command
     */
    public String getSecondWord()
    {
        return secondWord;
    }
    
    /**
     * Return the third word of this command. Returns null if there was no
     * third word.
     * 
     * @return String The third word of command
     */
    public String getThirdWord()
    {
        return thirdWord;
    }
    

    /**
     * Return true if this command was not understood.
     * @return boolean True if the command was understood, false otherwise.
     */
    public boolean isUnknown()
    {
        return (commandWord == null);
    }

    /**
     * Return true if the command has a second word.
     * @return boolean True if the command has second word, false otherwise.
     */
    public boolean hasSecondWord()
    {
        return (secondWord != null);
    }
    
    /**
     * Return true if the command has a third word.
     * @return boolean True if the command has third word, false otherwise.
     */
    public boolean hasThirdWord()
    {
        return (thirdWord != null);
    }
    
}

