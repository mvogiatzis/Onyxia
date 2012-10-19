package onyxia.game;

import java.util.StringTokenizer;

/**
 * Parser Class - A class used to parse the text from the input, imported from
 * IJP Assignment 1.
 * 
 * This class is part of the "Onyxia" application. 
 * 
 * "Onyxia" is a very interesting adventure game. Users can enter rooms, 
 * interact with characters and items and have fun! The game is already filled
 * with a rich functionality and can be extended easily.
 *
 * This parser takes user input and tries to interpret it as a "Onyxia"
 * command. Every time it is called it takes a line as a String and
 * tries to interpret the line as a three word command. It returns the command
 * as an object of class Command.
 *
 * The parser has a set of known command words. It checks user input against
 * the known commands, and if the input is not one of the known commands, it
 * returns a command object that is marked as an unknown command.
 *
 * @author  Michail Vogiatzis
 * @version 1.0 (November 2011)
 */
public class Parser {
    
    /**
     * The CommandWords object that holds all valid command words
     */
    private CommandWords commands;
    
    /**
     * Constructor of Parser class
     */
    public Parser()
    {
        commands = new CommandWords();
    }

    /**
     * Get a new command from the user. The command is read by
     * parsing the 'inputLine'.
     * @param inputLine String The text from the input line to be parsed
     * @return Command Returns the Command object after parsing.
     */
    public Command getCommand(String inputLine)
    {
        String word1;
        String word2;
        String word3;
        
        StringTokenizer tokenizer = new StringTokenizer(inputLine);

        if(tokenizer.hasMoreTokens())
            word1 = tokenizer.nextToken();      // get first word
        else
            word1 = null;
        if(tokenizer.hasMoreTokens())
            word2 = tokenizer.nextToken();      // get second word
        else
            word2 = null;
        if(tokenizer.hasMoreTokens())
        	word3 = tokenizer.nextToken();		// get third word
        else
        	word3 = null;

        // note: we just ignore the rest of the input line.

        // Now check whether this word is known. If so, create a command
        // with it. If not, create a "null" command (for unknown command).

        if(commands.isCommand(word1))
            return new Command(commands.getCommandWord(word1), word2, word3);
        else
            return new Command(null, word2, word3);
    }

    /**
     * Print out a list of valid command words.
     * @return String The valid commands to be returned
     */
    public String showCommands()
    {
        return commands.showAll();
    }
}
