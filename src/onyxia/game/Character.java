package onyxia.game;

/**
 * Class Character - A character in Onyxia game.
 * 
 * This class is part of the "Onyxia" application. 
 * 
 * "Onyxia" is a very interesting adventure game. Users can enter rooms, 
 * interact with characters and items and have fun! The game is already filled
 * with a rich functionality and can be extended easily.
 * 
 * A Character represents one character in the scenery of the game.
 * Users can interact with the characters of the game.
 * 
 * @author  Michail Vogiatzis
 * @version 1.0 (November 2011)
 */
public class Character {
	
    /**
     * The name of the character
     */
    private String name;
    /**
     * A description of the character
     */
    private String description;
    /**
     * Indicates if character is dead or alive. Every character starts alive.
     */
    private boolean isAlive;
    
    /**
     * The image path that represents the character
     */
    private String characterImagePath;

    /**
     * Constructor of Character class.
     * @param name The string that sets the name of the character
     * @param description The String that sets the description of the character
     * @param characterImagePath The String that sets the relative path of the character image
     */
    public Character(String name, String description, String characterImagePath)
    {
            this.name = name;
            this.description = description;
            this.characterImagePath = characterImagePath;
            //every character starts alive
            isAlive = true;
    }

    /**
     * Get the name of the character.
     * 
     * @return String The string to be returned as the character name.
     */
    public String getName()
    {
        return name;
    }

    /**
     * Get the description of the character.
     * 
     * @return String The string to be returned as the description of the character.
     */
    public String getDescription()
    {
        return description;
    }
    
    /**
     * Get the image path of character
     * @return String The image path of the character
     */
    public String getCharImagePath()
    {
        return characterImagePath;
    }
    /**
     * Set the character status. True if he is alive, False if not.
     * @param isAlive The boolean variable that is used to set the status.
     */
    public void setAlive(boolean isAlive) {
            this.isAlive = isAlive;
    }

    /**
     * Get the character status. True if is alive, false if not.
     * @return boolean The character status to be returned.
     */
    public boolean isAlive()
    {
        return (isAlive);
    }
	

}
