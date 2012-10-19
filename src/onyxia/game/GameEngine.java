package onyxia.game;

import java.util.HashSet;
import java.util.Iterator;


/**
 * GameEngine class - The engine of the game in "Onyxia" application. All logic
 * is contained here.
 * 
 * This class is part of the "Onyxia" application. 
 * 
 * "Onyxia" is a very interesting adventure game. Users can enter rooms, 
 * interact with characters and items and have fun! The game is already filled
 * with a rich functionality and can be extended easily.
 *
 * This class creates all rooms, all characters and items. It also creates the 
 * parser and starts the game. It evaluates and executes the commands that
 * the parser returns.
 *
 * @author  Michail Vogiatzis
 * @version 1.0 (November 2011)
 */
public class GameEngine{
    
    /**
     * The Parser of the commands
     */
    private Parser parser;
    
    /**
     * The current Room object that our player is in.
     */
    private Room currentRoom;
    
    /**
     * The graphical user interface 
     */
    private UserInterface gui;
    
    /**
     * The player of the game
     */
    private Player player;
    

    /**
     * Constructor of GameEngine class. It creates the parser, the player, the
     * rooms with their associated items and characters. Everything starts here.
     */
    public GameEngine()
    {
        parser = new Parser();
        
        player = new Player();
        
        createRooms();
        
        
    }
    
    /**
     * Set an associated GUI to the GameEngine and print a welcome message
     * @param userInterface The UserInterface to be set
     */
    public void setGUI(UserInterface userInterface)
    {
        gui = userInterface;
        printWelcome();
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
    	gui.println("Welcome to Onyxia");
    	gui.println("Try to reach immortality by finding the magical ring!");
    	gui.println("You can press 'help' for more info.");
    	gui.println("");
    	gui.println("Let the game begin...");
    }
    

    /**
     * This method creates all the rooms and link their exits together.
     * Also while creating rooms it creates the characters and the items of the 
     * game and inserts them into the correct room
     */
    private void createRooms()
    {
    	//creating the rooms, the items have not been created yet.
    	Room outside, hall, livingroom, kitchen, myroom, cave;
    	//define the Image Path of each room
        String outsideImagePath, hallImagePath, livingroomImagePath, kitchenImagePath;
        String myroomImagePath, caveImagePath;
        
                
        outsideImagePath = "/images/outdoors-600.png";
        hallImagePath = "/images/hall-600.png";
        livingroomImagePath = "/images/livingroom-600.png";
        kitchenImagePath = "/images/kitchen-600.png";
        myroomImagePath = "/images/myroom-600.png";
        caveImagePath = "/images/onyxia-back-600.png";
        
    	outside = new Room("outside of the house", false, outsideImagePath);
    	hall = new Room("in the hall of the house.. Beware !", false, hallImagePath);
    	livingroom = new Room("in the living room.", false, livingroomImagePath);
    	kitchen = new Room("in the kitchen", false, kitchenImagePath);
        //this room is locked
    	myroom = new Room("in the frightening room of the house", true, myroomImagePath);
    	cave = new Room("down in the cave. ONYXIA lives here !!!", false, caveImagePath);
    	
    	//set Exits for each Room and connect each other
    	outside.setExit("forward", hall);
    	outside.setExit("down", cave);
    	
    	hall.setExit("forward", kitchen);
    	hall.setExit("right", livingroom);
    	hall.setExit("left", myroom);
    	hall.setExit("backwards", outside);
    	
    	livingroom.setExit("left", hall);
    	
    	kitchen.setExit("backwards", hall);
    	
    	myroom.setExit("right", hall);
    	
    	cave.setExit("up", outside);
    	
        //define item image paths for each item
        String batURL, keysURL, bookURL, chestURL, swordURL;
        batURL = "/images/bat.png";
        keysURL = "/images/keys.png";
        bookURL = "/images/book.png";
        swordURL = "/images/sword.png";
        chestURL = "/images/chest.png";
        
    	//Now create the items and insert them in the correct room.
    	Item bat, keys, book, chest;
    	Enchantable sword; //extends Item
    	
    	//living room
    	bat = new Item("bat", "This is a strong baseball bat.", batURL);
    	keys = new Item("keys", "A pair of keys. Keys might be useful..", keysURL);
    	//kitchen
    	sword = new Enchantable("sword", "This is a legendary sword! The myth says that its " +
    			"blacksmith was killed by Onyxia !", swordURL);
    	//frightening room
    	book = new Item("book", "This is an ancient book.", bookURL);
    	//cave
    	chest = new Item("chest", "This is an old chest. The containment is so precious !!!", chestURL);
        
    	//living room
    	livingroom.addItem(bat);
    	livingroom.addItem(keys);
    	//kitchen
    	kitchen.addItem(sword);
    	//frightening room
    	myroom.addItem(book);
    	//cave
    	cave.addItem(chest);
    	
    	//Now create the characters and insert each one in a room.
    	
    	//create characters of the game
    	Character woman, smallDragon, onyxia;
    	ChatBot genie;
    	
        //define character image paths for each character
        String womanURL, smallDragonURL, onyxiaURL, genieURL;
        womanURL = "/images/woman.png";
        genieURL = "/images/genie.png";
        smallDragonURL = "/images/small_dragon.png";
        onyxiaURL = "/images/onyxia-600.png";
        
    	woman = new Character("woman", "Scared woman that lives in the house.", womanURL);
    	genie = new ChatBot("genie", "Helping Genie that can give some useful advices!", genieURL);
    	smallDragon = new Character("small_dragon", "A small dragon, also known as Son of Onyxia.", smallDragonURL);
    	onyxia = new Character("onyxia", "Powerful dragon Onyxia that lives in the cave. She " +
    			" keeps something valuable! Prepare to fight for it...", onyxiaURL);
    	
    	//Assign each character to the room he belongs
    	//hall
    	hall.setRoomChar(woman);
    	
    	//kitchen
    	kitchen.setRoomChar(genie);
    	
    	//frightening room - myroom
    	myroom.setRoomChar(smallDragon);
    	
    	//cave
    	cave.setRoomChar(onyxia);
    	
    	//The game begins in outside room.
    	currentRoom = outside;
    }
    
    /**
     * Given a command, process (that is: execute) the command.
     * If this command ends the game, true is returned, otherwise false is
     * returned.
     */
    public void interpretCommand(String commandLine)
    {
        gui.println("");
        gui.println(commandLine);
        Command command = parser.getCommand(commandLine);
        
        CommandWord commandWord = command.getCommandWord();

        //take cases according to the enumerator
        if (commandWord == CommandWord.HELP)
            printHelp();
        else if (commandWord == CommandWord.GO)
            goRoom(command);
        else if (commandWord == CommandWord.USE)
        	use(command);
        else if (commandWord == CommandWord.PICK)
        	pick(command);
        else if (commandWord == CommandWord.TALK)
        	talk(command);
        else if (commandWord == CommandWord.ITEMS)
        	showItems();
        else if (commandWord == CommandWord.QUIT) {
            if(command.hasSecondWord())
                gui.println("Quit what?");
            else
                endGame();
        }
        else
            gui.println("I don't know what you mean...");
    }

    // implementations of user commands:

    /**
     * Print out some help information about the house, the currentRoom.
     */
    private void printHelp()
    {
    	gui.println("You are in a old cold house, somewhere in 17th century.");
    	gui.println("Trusting your rusty compass, you are now "+currentRoom.getShortDescription()+".");
        gui.println("Remember: You are trying to reach immortality by finding the magical ring !!");
    	gui.println("But a lot of scary monsters live in this house! Muhahaha...\n");
    	gui.println("Your available commands are: " + parser.showCommands());
    }
    
    /**
     * Wrapper of printHelp to be used in GUI
     */
    public void printHelpWrapper()
    {
        printHelp();
    }

    /**
     * Try to go to one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     * @param command The Command object that is passed as a parameter.
     */
    private void goRoom(Command command)
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            gui.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null)
            gui.println("There is no door!");
        else if (nextRoom.isLocked())
        	gui.println("You can't go " + direction + ". The room is locked !");
        else{
            if (nextRoom.hasChar("onyxia"))
            {
               gui.playSoundEffect(SoundType.ONYXIA.getSoundType());
            }
            currentRoom = nextRoom;
            gui.changeRoom(nextRoom);
            gui.setChatModeEnabled(false);  //to ensure that he will not keep speaking
            gui.println(currentRoom.getLongDescription());
        }
    }
    
    /**
     * Use command is used to perform an action. The second word of the command must be 
     * and item from player's inventory because users can only use items stored in the inventory.
     * The third word represents the object that receives the
     * action. This can be a character or an item. For example, 'use sword dragon' will 
     * use the sword if this is contained in your inventory to kill the dragon.
     * 
     * Note: The third word is optional for some items such as book or keys.
     * 
     * @param command The command object.
     */
    private void use(Command command)
    {
    	String theItemName; //the secondWord
    	String actionReceiver; //the thirdWord
    	
    	if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to use where.
            gui.println("Use what?");
            return;
        }
    	
    	//At this point, there is at lright a second word.
    	//Check if the item is in player's inventory.
    	theItemName = command.getSecondWord();
    	if (!containsItem(player.getInventory(), theItemName))
    	{
    		gui.println("You do not have the " + theItemName);
    		return;
    	}
    	else
    	{
            //Now implement the functionality of each use command, depending 
            //on the second and third word
            actionReceiver = command.getThirdWord();

            //BAT
            if (theItemName.equals("bat"))
            {
                    //check if there is third word
                    if (!command.hasThirdWord())
                    {
                        gui.println("Use " +theItemName+ " where ?");
                        return;
                    }
                    else
                    {
                        //Now we have secondWord: bat and a thirdWord
                        //Call the method that includes the Bat functionality
                        callWeaponFunctionality(theItemName, actionReceiver);
                        return;
                    }
            }
            //SWORD
            else if (theItemName.equals("sword"))
            {
                    //check if there is third word
                    if (!command.hasThirdWord())
                    {
                        gui.println("Use " +theItemName+ " where ?");
                        return;
                    }
                    else
                    {
                        //Now we have secondWord: sword and a thirdWord
                        //Call the method that includes the Sword functionality
                        callWeaponFunctionality(theItemName, actionReceiver);
                        return;
                    }
            }
            //KEYS
            else if (theItemName.equals("keys"))
            {
                //Here the third word is ignored.
                //if the room we 'use keys' is connected to the locked one - myroom, then unlock it.
                callKeysFunctionality();
                return;
            }
            //BOOK
            else if (theItemName.equals("book"))
            {
                //Book functionality: Print out the contents of the book
                gui.println("\"He pulled the sword and tried to kill her...\" " +
                                "but he was not prepared...");
                gui.println("The powerful dragon, \"Onyxia\", smashed him with her tail"
                        + " and burned his corpse with her fire breath! The hero died in pain..");
                gui.println("His sword was not so strong.. It seems that it needs something more to" +
                                " kill this creature...");
                return;
            }
            else
            {
                //If user tries to use something else in his inventory that cannot be used
                gui.println("You cannot use the " + theItemName);
                return;
            }

    	}
    	
    }
    
    
    
    /**
     * Method that includes the functionality of items 'sword' and 'bat' for 'use' command.
     * 
     * @param itemName The name of the item to be used.
     * @param actionReceiver The receiver of the action, given by name.
     */
    private void callWeaponFunctionality(String itemName, String actionReceiver)
    {
        boolean killed;
        //for woman and genie the functionality is the same
        if (actionReceiver.equals("woman"))
        {
            //kill the character if found in the room.
            //killCharacter checks if char exists else returns false
            killed = killCharacter(actionReceiver);

            if (killed)
            {
                gui.println("You just killed the "+ actionReceiver);
                gui.playSoundEffect(SoundType.SCREAM.getSoundType()); //scream
                gui.showItemChar("woman",false);
                return;
            }
            else
            {
                gui.println("There is no " + actionReceiver + " in this room.");
                return;
            }
        }
        else if (actionReceiver.equals("genie"))
        {
            //If the receiver is the genie, check if he is in the room,
            //and if true, print out that he cannot die.
            if (currentRoom.hasChar(actionReceiver))
            {
                gui.println("Aaaaaaah.. I was trying to help you ! The Mighty Genie cannot die " +
                                "you fool !");
                return;
            }
            else
            {
                gui.println("There is no " + actionReceiver + " in this room.");
                return;
            }
        }
        else if (actionReceiver.equals("small_dragon"))
        {
            if (itemName.equals("bat"))
            {
                //The dragon can die from a baseball bat.
                //killCharacter checks if char exists else returns false
                killed = killCharacter(actionReceiver);
                if (killed)
                {
                    gui.println("Son of Onyxia tries to burn your legs !");
                    gui.println("You dodge the attack! You hit him back twice, with your "+itemName);
                    gui.println("Congratulations! You just killed the "+ actionReceiver + " !!");

                    gui.showItemChar("small_dragon", false);
                    return;
                }
                else
                {
                    gui.println("There is no " + actionReceiver + " in this room.");
                    return;
                }
            }
            else if (itemName.equals("sword"))
            {
                //The dragon can die from a sword and then sword is enchanted with magical abilities.
                killed = killCharacter(actionReceiver);
                if (killed)
                {
                    gui.println("The small dragon swings his tail and hits you in the right leg !");
                    gui.println("You pull your silver sword and cut his head off !!");
                    gui.println("Congratulations! You just killed the "+ actionReceiver + " !!");
                    gui.println("Your sword is now enchanted with magical abilities !");
                    enchantItem("sword");
                    gui.showItemChar("small_dragon", false); //hide him
                    return;
                }
                else
                {
                    gui.println("There is no " + actionReceiver + " in this room.");
                    return;
                }
            }
        }//end of smallDragon
        else if (actionReceiver.equals("onyxia"))
        {
            if (itemName.equals("bat"))
            {
                //Onyxia cannot die from a baseball bat, so our player dies !
                if (currentRoom.hasChar(actionReceiver))
                {
                    String displaymsg = 
                        "Onyxia is moving towards you !\n"+
                        "She breathes fire and burns everything around her !!\n"+
                        "and now a scream... HOW DARE YOU ? ? ?\n\n"+
                        "You die in pain...";
                    gui.playSoundEffect(SoundType.GAMEOVER.getSoundType()); //play gameover effect
                    gui.printMessageBox(displaymsg, "Game Over", MessageType.GAMEOVER.getMsg());
                    endGame();  //close the frame
                }
                else
                {
                        gui.println("There is no " + actionReceiver + " in this room.");
                        return;
                }
            }
            else if (itemName.equals("sword"))
            {
                //Onyxia can die from a sword, only if it is enchanted! 
                    if (currentRoom.hasChar(actionReceiver) && (!isEnchanted(itemName)))
                    {
                        String deadmsg = "Onyxia is moving towards you !\n"+
                        "She breathes fire and burns everything around her !!\n"+
                        "and now a scream... HOW DARE YOU ? ? ?\n"+
                        "You die in pain...";
                        gui.playSoundEffect(SoundType.GAMEOVER.getSoundType()); //play gameover effect
                        gui.printMessageBox(deadmsg, "Game Over", MessageType.GAMEOVER.getMsg());
                        endGame();
                    }
                    else if (currentRoom.hasChar(actionReceiver) && isEnchanted(itemName) )
                    {
                        //Here onyxia can die ! The sword is enchanted.
                        String dialogMsg = "Onyxia is moving towards you !!\n"+
                        "She performs a melee attack to you! You are bleeding..\n"+
                        "You find the strength to hit her back with a kidney shot !\n"+
                        "The big dragonkin SCREAMS and open her wings.\n"+
                        "She flies away in a desperate need to recover.\n"+
                        "She now casts \"Deep Breath\" on you,\nand a ball" +
                                        " of fire comes out of her mouth!";
                        //option dialog
                        boolean decision = gui.printDialog(dialogMsg);
                        //fire resistance needed
                        if ((decision) && (player.getFireResistance() > 0))
                        {
                            //continue fighting and win
                            dialogMsg = "Your fire resistance chest protects you from the spell !\n"+
                            "She attemps to crush you, as she comes down to the ground !\n"+
                            "Your agility helps you to stab the enchanted sword in her heart...\n"+
                            "The dragon is burning into her own flames and dies screaming...\n\n"+
                            "Congratulations! You killed Onyxia !!!\n"+
                            "The ring of immortality is forever yours !";

                            killed = killCharacter("onyxia");   //kill her
                            gui.showItemChar("onyxia", false); //hide her
                            gui.showItemChar("chest",true);    //show the chest with ring
                            gui.playSoundEffect(SoundType.VICTORY.getSoundType()); //play victory effect
                            gui.printMessageBox(dialogMsg, "YOU WIN !!!", MessageType.WIN.getMsg());
                            gui.freezeInterface();
                            //change room to show ring, and freeze.
                        }
                        else if ((decision) && (player.getFireResistance() == 0))
                        {
                            dialogMsg = "A ball of fire hits you in your heart! \n"
                                    + "You are burning into flames and you try to run away!!! \n"
                                    + "Onyxia flies upon you and unleashes an ultimate fire breath that"
                                    + " knocks you out...!!\n"
                                    + "You die...";
                            gui.playSoundEffect(SoundType.GAMEOVER.getSoundType()); //play gameover effect
                            gui.printMessageBox(dialogMsg, "Game Over", MessageType.GAMEOVER.getMsg());
                            endGame();
                        }
                        else
                        {
                            //he is a chicken. go up.
                            interpretCommand("go up");
                            return;
                        }
                    }
                    else
                    {
                        gui.println("There is no " + actionReceiver + " in this room.");
                        return;
                    }
            }
        }//end of onyxia actionReceiver
        else
        {
            //Now if the item is used on something different, print out a message.
            gui.println("You cannot use the " + itemName + " on a " + actionReceiver);
            return;
        }
        
        
        
    }
    
    /**
     * Method that includes the functionality of item 'keys' for 'use' command.
     * Note that keys can be used without a receiver of the action. For example
     * 'use keys' is a valid command and the rest of the string is ignored.
     */
    private void callKeysFunctionality()
    {
    	HashSet<Room> lockedRooms = currentRoom.getLockedExits();
        if (lockedRooms.isEmpty())
        {
            gui.println("There is nothing to unlock");
            return;
        }
        else
        {
            //unlock all rooms
            for (Room r : lockedRooms)
            {
                r.setLocked(false);
                gui.println("You can now walk " + r.getShortDescription());
            }
            return;
        }
    }
    
    /**
     * Function that checks if the character belongs in the room and kills him.
     * 
     * @param nameOfChar The name of the character to be killed
     * 
     * @return boolean True if he was killed, false if the character was not found in the room.
     */
    private boolean killCharacter(String nameOfChar)
    {
    	//check if character is contained in the room and kill her.
        if (currentRoom.hasChar(nameOfChar))
        {
            currentRoom.getRoomChar().setAlive(false); //kill the woman
            currentRoom.setRoomChar(null);
            return true;
        }
        else //if character is not in the room
        {
            return false;
        }
    }
    
    /**
     * Enchant an item of the inventory. Only items of class Enchantable can be enchanted.
     * 
     * @param itemName The name of the item that is going to be enchanted.
     * @return boolean True if the enchant was successful. False if not, or item name not found.
     */
    private boolean enchantItem(String itemName)
    {
    	Iterator<Item> it = player.getInventory().iterator();
        Item tempItem;

        while (it.hasNext())
        {
                tempItem = it.next();
                if (tempItem.getName().equals(itemName) && (tempItem instanceof Enchantable))
                {
                    ((Enchantable)tempItem).enchant(true);
                    return true;
                }
        }
        return false;
    }
    
    
    /**
     * Get if the given item is enchanted or not.
     * 
     * @param itemName The name of the item to be checked.
     * @return boolean True if the item is Enchanted, false if not or not found in the inventory.
     */
    private boolean isEnchanted(String itemName)
    {
    	Iterator<Item> it = player.getInventory().iterator();
    	Item tempItem;
    	
    	while (it.hasNext())
    	{
            tempItem = it.next();
            if (tempItem.getName().equals(itemName) && (tempItem instanceof Enchantable))
            {
                    return ((Enchantable)tempItem).isEnchanted();
            }
    	}
    	
    	return false;
    }
    
    /**
     * Adds an armor part in player's gear.
     * @param armorPart String the armor part to be added.
     */
    public void addToArmorSet(String armorPart)
    {
        player.wearArmorPart(armorPart);
    }
    
    /**
     * Increase the player's fire resistance by the int value.
     * @param value The int value to be added in fire resistance stat.
     */
    public void increaseFireResistance(int value)
    {
        player.increaseFireResistance(value);
    }
    
    /**
     * Check if the name of the item is contained in the given HashSet.
     * 
     * @param inventory The HashSet that will be used to check whether an item exists inside.
     * @param itemName The name of the item to be checked.
     * @return boolean True if the item exists in the inventory, false if not.
     */
    private boolean containsItem(HashSet<Item> inventory, String itemName)
    {
        Iterator<Item> it = inventory.iterator();
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
     * Try to pick an item from the Room and store it in player's inventory.
     * 
     * @param command The command that indicates what to pick.
     * @return boolean Return true if the item was picked - added to the inventory. False if not.
     */
    private boolean pick(Command command)
    {
    	Item tempItem;
    	String itemName;
    	
    	if(!command.hasSecondWord()) {
            // if there is no second word, we don't know what to pick 
            gui.println("Pick what ?");
            return false;
        }
    	
    	itemName = command.getSecondWord();
    	//check if the item is contained in the room and add it to player's inventory. Otherwise
    	//print out a not found message.
    	if (currentRoom.containsItem(itemName))
    	{
            boolean picked = false;
            tempItem = currentRoom.getItemByName(itemName);
            player.addItemtoInventory(tempItem);	//add it to the player's inventory.
            currentRoom.removeItem(tempItem);  //remove it from the room
            gui.refreshInventory(player);
            //hide the item
            if (tempItem.getName().equals("keys"))
            {
                picked = gui.showItemChar("keys",false);
            }
            if (tempItem.getName().equals("bat"))
            {
                picked = gui.showItemChar("bat",false);
            } 
            if (tempItem.getName().equals("sword"))
            {
                picked = gui.showItemChar("sword",false);
            }
            if (tempItem.getName().equals("book"))
            {
                picked = gui.showItemChar("book",false);
            }
            if (picked)
                gui.println("You have picked the " + itemName);
            return true;
    	}
    	else
    		gui.println("There is no such item here...");
    	
    	//if this point is reached, the item was not found.
    	return false;
    }
    
    /**
     * Talk command is used to talk with a Character.
     * Only the second word of the command is needed and the third is ignored.
     * The ChatBot from exercise 1 is used to implement a conversation with the genie.
     * 
     * @param command The given Command 'command' to start talking.
     */
    private void talk(Command command)
    {
    	String person;	//the person that receives the remark
    	
    	if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            gui.println("Talk to who?");
            return;
        }
    	
    	person = command.getSecondWord();
    	
    	//TALK WITH GENIE
    	if (person.equals("genie"))
    	{
            //if you are in the same room, enter chat mode.
            if (currentRoom.hasChar(person) && currentRoom.getRoomChar().isAlive())
            {

                //get the room char and if its a chatbot, start a conversation
                Character tempChar = currentRoom.getRoomChar();
                if (tempChar instanceof ChatBot)
                {
                    gui.println("You enter the chat mode. To quit, say \"bye\" .");
                    gui.setFocusInput();    //input textfield gets focus
                    gui.setChatModeEnabled(true);
                    return;
                }
            }
            //at this point there is no such person in this room
            gui.println("There is no "+person+" in this room.");
            return;
    	}
    	else if (person.equals("woman") && currentRoom.getRoomChar().isAlive())
    	{
            //The woman is so scared that will only warn the user...
            if (currentRoom.hasChar(person))
            {
                gui.println("I'm so scared !!!!! There are evil monsters in this house !!!");
                gui.println("It's still early.. You can QUIT !");
                gui.println("You better kill me before you leave.. I don't want to die from monsters!!");
                return;
            }
            //at this point there is no such person in this room
            gui.println("There is no "+person+" in this room.");
            return;
    	}
    	else
    	{
            //Person not included in the Characters that can talk.
            gui.println("You cannot talk to " + person);
            return;
    	}
    	
    	
    }
    
    
    
    /**
     * Get a reply from Chatbot if exists in the room. Otherwise return null.
     * @param humanRemark  The remark to be given as input to the Chatbot
     * @return String The chat bot's reply to the specified remark
     */
    public String getReplyfromChatbot(String humanRemark)
    {
        String reply;
        Character tempChar = currentRoom.getRoomChar();
        if (tempChar instanceof ChatBot)
        {
            reply = ((ChatBot)tempChar).reply(humanRemark);
            return reply;
        }
        return null;    //if character does not have a chatbot
    }
    
    
    /**
     * Show the available items in player's inventory.
     * Second and third word are being ignored.
     */
    private void showItems()
    {
	//Players inventory
        HashSet<Item> playersInventory = new HashSet<Item>();
        playersInventory = player.getInventory();
    	if (playersInventory.isEmpty())
    		gui.println("Your inventory is empty! Consider picking something.");
    	else
    	{
            StringBuilder str = new StringBuilder("Your inventory has the following items: ");
            for(Item itm : playersInventory)
            {
                str.append(itm.getName()).append(" ");
            }
            gui.println(str.toString());
    	}
    }
    
    
    
    /**
     * Call the UI to quit the frame, close it and dispose it.
     */
    private void endGame()
    {
        //Message box
        gui.quit();
    	//System.exit(0);
    }

}
