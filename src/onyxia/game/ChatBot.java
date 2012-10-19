package onyxia.game;

import langen.CannedLanguageGenerator;

/**
 * Class ChatBot - A ChatBot in Onyxia game, that is also a character.
 * 
 * This class is part of the "Onyxia" application. 
 * 
 * "Onyxia" is a very interesting adventure game. Users can enter rooms, 
 * interact with characters and items and have fun! The game is already filled
 * with a rich functionality and can be extended easily.
 * 
 * A class representing a digital persona known as a chat bot. 
 * The chat bot can reply to remarks. 
 * <p> Used in IJP assignments 1 and 2.</p>
 * 
 * @author  Michail Vogiatzis
 * @version 1.0 (November 2011)
 * 
 */
public class ChatBot extends Character {
	/**
     * The TemplateLanguageGenerator object
     */
    private CannedLanguageGenerator langen = new CannedLanguageGenerator();
    
    
    
    /**
     * Constructor of ChatBot class. Creates a ChatBot that extends Character class
     * @param name String Sets the name of the ChatBot
     * @param description String Sets the description of the ChatBot
     * @param imagePath String Sets the relative path of the ChatBot image
     */
    public ChatBot(String name, String description, String imagePath) {
		super(name, description, imagePath); //call character constructor
		
	}


    /**
     * Generate a response to the specified remark
     * 
     * @param remark The comment which must be responded to
     * 
     * @return A sentence which represents a reply to the specified remark
     */
    public String reply(String remark) {
        
        String reply;
        //generate a reply for the given remark
        reply = langen.generateReply(remark);
        //return the reply
        return reply;
    }

}
