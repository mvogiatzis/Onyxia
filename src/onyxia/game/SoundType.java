package onyxia.game;

/**
 * Enumerator SoundType - An Enumerator of different kinds of sounds in Onyxia
 * application.
 * 
 * This enumerator is part of the "Onyxia" application. 
 * 
 * "Onyxia" is a very interesting adventure game. Users can enter rooms, 
 * interact with characters and items and have fun! The game is already filled
 * with a rich functionality and can be extended easily.
 * 
 * This class holds an enumeration of sound types. i.e. GAMEOVER Sound
 * 
 * @author  Michail Vogiatzis
 * @version 1.0 (November 2011)
 */
public enum SoundType {
    GAMEOVER(1), VICTORY(2), ONYXIA(3), BACKGROUNDMUSIC(4), SCREAM(5);
    
    /**
     * The MessageType message type
     */
    private int soundType;
    
    /**
     * Initialize with the corresponding type of thing.
     * @param thingType The command string.
     */
    SoundType(int soundType)
    {
        this.soundType = soundType;
    }
    
    /**
     * Get the soundType
     * @return int Return the TypeOfThing int value
     */
    public int getSoundType()
    {
        return soundType;
    }
}
