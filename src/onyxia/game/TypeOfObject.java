package onyxia.game;

/**
 * Enumerator TypeOfObject - An enumerator in Onyxia application
 * 
 * This enumerator is part of the "Onyxia" application. 
 * 
 * "Onyxia" is a very interesting adventure game. Users can enter rooms, 
 * interact with characters and items and have fun! The game is already filled
 * with a rich functionality and can be extended easily.
 * 
 * This class holds an enumeration of two things. Items and Characters.
 * It matches meaningful (semantic) things with int values of ITEMs and CHARACTERs
 * 
 * @author  Michail Vogiatzis
 * @version 1.0 (November 2011)
 */
public enum TypeOfObject {
    ITEM(1), CHARACTER(2);
    
    /**
     * Integer that is used to show if the object is Item or Character
     */
    private int thingType;
    
    /**
     * Initialize with the corresponding type of thing.
     * @param thingType The command string.
     */
    TypeOfObject(int thingType)
    {
        this.thingType = thingType;
    }
    
    /**
     * Get the thingType
     * @return int Return the TypeOfObject int value
     */
    public int getTypeOfObject()
    {
        return thingType;
    }
}
