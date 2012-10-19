package onyxia.game;

import java.util.Scanner;

/**
 * This class is the main class of the "Onyxia" application.
 * 
 * "Onyxia" is a very interesting adventure game. Users can enter rooms, 
 * interact with characters and items and have fun! The game is already filled
 * with a rich functionality and can be extended easily.
 *
 * To play this game, create an instance of this class.
 *
 * This main class creates the necessary implementation objects and starts the game off.
 *
 * @author  Michail Vogiatzis
 * @version 1.0 (November 2011)
 */
public class Game {
    
    /**
     * The user interface of the game
     */
    private UserInterface gui;
    /**
     * The GameEngine class that contains the logic of the game
     */
    private GameEngine engine;

    /**
     * Create the game and initialize its internal map.
     */
    public Game()
    {
        engine = new GameEngine();       //first create the game engine
        gui = new UserInterface(engine); //then set the interface
        engine.setGUI(gui);
    }
    
    /**
     * Method to play the game from command line
     */
    private void start()
    {
         Scanner reader = new Scanner(System.in);
         String inputLine;
         // while loop that 'plays' the game
         while (true){
            inputLine = reader.nextLine(); //reads a line from the input
            engine.interpretCommand(inputLine);
         }
    }

     
    public static void main(String[] argv) {
    	// create the game
    	Game game = new Game();
    	
    	// invoke a method to play the game until the end - only for command line
    	//game.start();
    	
    	// no need to clean up, the game closes normally from UserInterface quit() method
        
    }
}
