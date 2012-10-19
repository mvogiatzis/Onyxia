package onyxia.game;

/**
 * Class Item - An item in Onyxia game.
 * 
 * This class is part of the "Onyxia" application. 
 * 
 * "Onyxia" is a very interesting adventure game. Users can enter rooms, 
 * interact with characters and items and have fun! The game is already filled
 * with a rich functionality and can be extended easily.
 * 
 * An "Item" represents an item in the scenery of the game. A Room can have many items
 * and a user can pick an item and store it in his/her inventory.
 * 
 * @author  Michail Vogiatzis
 * @version 1.0 (November 2011)
 * 
 */
public class Item {
    /**
     * The name of the item
     */
    private String name;
    
    /**
     * A small description of the item
     */
    private String description;
    
    /**
     * Every item has an associated image that represents it.
     * The image path of the item.
     */
    private String itemImagePath;

    /**
     * Constructor of the Item class
     * @param itemName String The name of the item
     * @param itemDescription String A small description of the item
     * @param itemImagePath String The relative path to the item's image.
     */
    public Item(String itemName, String itemDescription, String itemImagePath)
    {
        setName(itemName);
        this.description = itemDescription;
        this.itemImagePath = itemImagePath;
    }

    /**
     * Get the name of the item.
     * 
     * @return String Returns the name of the item.
     */
    public String getName() {
        return name;
    }

    /**
     * Set the name of the item.
     * 
     * @param name The name to be set as item name.
     */
    public void setName(String name) {
        this.name = name;
    }
    /**
     * Get the image path of the item
     * @return String The path of the item's image
     */
    public String getItemURL()
    {
        return itemImagePath;
    }
	
}
