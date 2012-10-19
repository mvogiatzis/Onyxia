package onyxia.game;

import java.util.ArrayList;
import java.util.HashSet;

/**
 * Class Player - A player in Onyxia game.
 * 
 * This class is part of the "Onyxia" application. 
 * 
 * "Onyxia" is a very interesting adventure game. Users can enter rooms, 
 * interact with characters and items and have fun! The game is already filled
 * with a rich functionality and can be extended easily.
 * 
 * A Player represents the person that plays the game. A Player becomes stronger
 * if his gear is updated and can carry items in his inventory.
 * 
 * @author  Michail Vogiatzis
 * @version 1.0 (November 2011)
 */
public class Player {
    
    /**
     * The Players inventory
     */
    private HashSet<Item> inventory;
    
    /**
     * The player's gear. A player can wear armor sets to protect himself.
     * It's an ArrayList because a user can wear two gloves for example..
     */
    private ArrayList<String> gear;
    
    /**
     * Player's hitpoints. It will be used in the future version of the game
     */
    private int hitpoints;
    
    /**
     * The player's resistance to fire.
     */
    private int fireResistance;
    
    /**
     * Constructor of the Player class.
     */
    public Player()
    {
        inventory = new HashSet<Item>();
        hitpoints = 100;    //hitpoints starting value is 100
        gear = new ArrayList<String>();
        fireResistance = 0; //player starts with zero resistance to fire
    }
    
    /**
     * Adds an item to the player's inventory
     * @param itemToAdd The Item object to be added in the inventory
     */
    public void addItemtoInventory(Item itemToAdd)
    {
        inventory.add(itemToAdd);
    }
    
    /**
     * Wear part of an armor.
     * @param armorPart String  
     */
    public void wearArmorPart(String armorPart)
    {
        gear.add(armorPart);
    }
    
    /**
     * Increase player's fire resistance by an exact value.
     * @param value The int value to be added in fire resistance stat.
     */
    public void increaseFireResistance(int value)
    {
        fireResistance += value;
    }
    
    /**
     * Get the player's fire resistance
     * @return int The value of fire resistance
     */
    public int getFireResistance()
    {
        return fireResistance;
    }
    
    /**
     * Get the players inventory
     * @return HashSet<Item> The player's inventory to be returned
     */
    public HashSet<Item> getInventory()
    {
        return inventory;
    }
    
}
