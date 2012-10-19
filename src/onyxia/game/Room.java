package onyxia.game;

import java.util.HashSet;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Class Room - a room in "Onyxia" game.
 *
 * This class is part of the "Onyxia" application. 
 * 
 * "Onyxia" is a very interesting adventure game. Users can enter rooms, 
 * interact with characters and items and have fun! The game is already filled
 * with a rich functionality and can be extended easily.
 *
 * A "Room" represents one location in the scenery of the game.  It is
 * connected to other rooms via exits.  For each existing exit, the room
 * stores a reference to the neighboring room.
 *
 * @author  Michail Vogiatzis
 * @version 1.0 (November 2011)
 */
public class Room {
    
    /**
     * A small description of the Room
     */
    private String description;
    
    /**
     * A HashMap that contains the possible exits from this Room
     */
    private HashMap <String, Room> exits;
    
    /**
     * Boolean value to indicate if the room is locked or not
     */
    private boolean isLocked;
    
    /**
     * The set of items inside this Room. There is a restriction here, a room 
     * cannot contain two same Item instances.
     */
    private HashSet<Item> items;
    /**
     * Each room contains maximum one character.
     */
    private Character roomChar;
    /**
     * Each room is associated with an Image URL for display.
     */
    private String imagePath;
    

    /**
     * Constructor of the Room class - Creates a Room object with the following
     * properties.
     * @param description The description of the room, i.e. "in the kitchen"
     * @param isLocked Sets the state of the room, true if locked, false if not.
     * @param imagePath Sets the relative path to the room's image.
     */
    public Room(String description, boolean isLocked, String imagePath)
    {
        this.description = description;
        exits = new HashMap <String, Room> (); //initially the room has no exits
        this.isLocked = isLocked;
        this.imagePath = imagePath;
        items = new HashSet<Item>(); //initialize the HashSet that contains all the room's items.
    }
    
    
    /**
     * Constructor of Room class - An additional constructor which is used to assign items and
     * the character at the time of room creation.
     * @param description The description of the room, i.e. "in the kitchen"
     * @param isLocked Sets the state of the room, true if locked, false if not.
     * @param items HashSet<Item> That contains the items that room is starting with.
     * @param charact The character contained in the room.
     */
    public Room(String description, boolean isLocked, HashSet<Item> items, Character charact)
    {
        this.description = description;
        exits = new HashMap <String, Room> (); //initially the room has no exits
        this.isLocked = isLocked;
        this.items = items;
        setRoomChar(charact);
    }

    /**
     * Set an exit to the room
     * @param direction String The direction of the exit, i.e. forward
     * @param neighbor Room The room object that is accessed if you exit to the above direction.
     */
    public void setExit(String direction, Room neighbor)
    {
        exits.put(direction, neighbor);
    }

    /**
     * Return the description of the room (the one that was defined in the
     * constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Get a long description of this room
     * @return String The long description of the room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n" + getExitString();
    }
    
    /**
     * Check if the room is locked or not.
     * 
     * @return boolean True if is locked, False if it's not.
     */
    public boolean isLocked() {
	return isLocked;
    }
    
    /**
     * Get the Room image path
     * @return String The Image path
     */
    public String getRoomImagePath()
    {
        return imagePath;
    }
    
	
    /**
     * The mutator method of isLocked field.
     * @param isLocked The field to be set true if the room should lock, false if not.
     */
    public void setLocked(boolean isLocked) {
        this.isLocked = isLocked;
    }

    /**
     * Add an item into the Set of items.
     * @param newitem The item to be added.
     */
    public void addItem(Item newitem)
    {
        items.add(newitem);
    }

    /**
     * Check if the name of the item is contained in the items list of the Room.
     * @return boolean True if the item exists in the room, false if not.
     */
    public boolean containsItem(String itemName)
    {
        Iterator<Item> it = items.iterator();
        Item currentItem;

        while (it.hasNext())
        {
            currentItem = it.next();
            //the comparison is made between item names. Two items in the same room,
            //cannot have the same name.
            if (currentItem.getName().equals(itemName))
                    return true;
        }

        return false;
    }

    /**
     * Get the item with name "itemName" if exists in the room items set.
     * 
     * @param itemName The parameter to indicate what item we are looking for.
     * @return Item Returns the item with the name "itemName", if exists. Otherwise, return null.
     */
    public Item getItemByName(String itemName)
    {
        Iterator<Item> it = items.iterator();
        Item currentItem;

        //Try to find the item by name comparison. Two items in the same room cannot have the
        //same name.
        while (it.hasNext())
        {
            currentItem = it.next();
            if (currentItem.getName().equals(itemName))
                return currentItem;
        }

        //If this point is reached, no item was found so null is returned.
        return null;

    }
    /**
     * Get the items stored in the room
     * @return HashSet<Item> Returns a HashSet of the items stored in the room.
     */
    public HashSet<Item> getItems()
    {
        return items;
    }
    
    /**
     * Get the locked exits of the room
     * @return A HashSet containing all the rooms that are locked near this room.
     */
    public HashSet<Room> getLockedExits()
    {
        HashSet<Room> lockedrooms = new HashSet<Room>();

        for (Room r : exits.values())
                if (r.isLocked())
                        lockedrooms.add(r);

        return lockedrooms;
    }


    /**
     * Get the character of the room. If no character exists, return null.
     * @return Character Returns the character of the room.
     */
    public Character getRoomChar() {
            return roomChar;
    }

    /**
     * Check if the Room contains a Character with a given name 'nameOfChar'
     * @param nameOfChar The given name to be checked
     * @return boolean Returns True if there is a character with the given name. False if not.
     */
    public boolean hasChar(String nameOfChar)
    {
        if (roomChar != null)
            return (roomChar.getName().equals(nameOfChar));
        else
            return false;
    }

    /**
     * Set the Character of the room. Each room has only one character.
     * @param roomChar The Character to be set inside the room.
     */
    public void setRoomChar(Character roomChar) {
        this.roomChar = roomChar;
    }

    /**
     * Removes the specified element from this set if it is present.
     * @param The item to be removed
     * @return boolean True if it was removed
     */
    public boolean removeItem(Item tempItem)
    {
        boolean removed = items.remove(tempItem);
        return (removed);
    }

    /**
     * Return a string describing the room's exits. For example, "Exits: forward left".
     * @return String The exits of the room
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        for (Object key : exits.keySet()) {
            returnString += " " + key;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param String The direction to which the room will be found.
     * @return Room The room that will be reached towards this direction.
     */
    public Room getExit(String direction)
    {
        return (Room)exits.get(direction);
    }


}
