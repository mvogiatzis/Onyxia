package onyxia.game;

/**
 * Class Enchantable - An Enchantable Item in Onyxia game.
 * 
 * This class is part of the "Onyxia" application. 
 * 
 * "Onyxia" is a very interesting adventure game. Users can enter rooms, 
 * interact with characters and items and have fun! The game is already filled
 * with a rich functionality and can be extended easily.
 * 
 * An Enchantable item is an Item object that can be enchanted with magical abilities. For example,
 * a sword can be enchanted in order to become stronger.
 * 
 * @author  Michail Vogiatzis
 * @version 1.0 (November 2011)
 */
public class Enchantable extends Item{
	
	/**
	 * a boolean variable that indicates if the item is enchanted with magical abilities.
	 */
	private boolean isEnchanted;
	
	/**
         * Constructor of Enchantable Class
         * @param itemName String The name of the Enchantable item
         * @param itemDescription The description of the Enchantable item
         * @param itemImagePath String The relative path to the item image.
         */
	public Enchantable(String itemName, String itemDescription, String itemImagePath) {
		
		super(itemName, itemDescription, itemImagePath); //constructor of Item class
		
		isEnchanted = false; //enchantable items are not enchanted when created.
	}
	
	/**
	 * The mutator method to enchant an item
	 * @param status True if the item will be enchanted. False if not.
	 */
	public void enchant(boolean status)
	{
            isEnchanted = status;
	}
	
	/**
	 * Get the status of the item.
	 * @return boolean The status that will be returned. True if the item is
         * enchanted, false if not.
	 */
	public boolean isEnchanted()
	{
            return (isEnchanted);
	}

}
