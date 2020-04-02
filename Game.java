import java.util.ArrayList;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Player player;
    private long start;
    private long finish;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
        start = System.currentTimeMillis();
        createRooms();
        parser = new Parser();
        player = new Player(currentRoom);
    }
    
    /**
     * Main method for the Game object.
     */
    public static void main(String[] cheese) {
        Game start = new Game();
        start.play();
    }
    
    
    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office;
        
        ArrayList<Item> itemsToAdd = new ArrayList<Item>();
        itemsToAdd.add(new Item("beans", "can of beans yum yum", 5));
        outside = new Room("Outside", "outside the main entrance of the" + 
                           " university", new ItemStorage(itemsToAdd));
        
        itemsToAdd = new ArrayList<Item>();
        itemsToAdd.add(new Item("key","opens a door", 2));
        theater = new Room("theater", "in a lecture theater",new ItemStorage(itemsToAdd));
        
        itemsToAdd = new ArrayList<Item>();
        
        pub = new Room("in the campus pub");
        
        itemsToAdd = new ArrayList<Item>();
        
        lab = new Room("in a computing lab");
        
        itemsToAdd = new ArrayList<Item>();
        
        office = new Room("in the computing admin office");
        
        // initialise room exits
        outside.setExit("east", theater);
        outside.setExit("south", lab);
        outside.setExit("west", pub);

        theater.setExit("west", outside);

        pub.setExit("east", outside);

        lab.setExit("north", outside);
        lab.setExit("east", office);

        office.setExit("west", lab);

        currentRoom = outside;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing!"); 
        System.out.println("Either you wanted to quit, or your time is up.");
        System.out.println("Good bye.");
    }
    
    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type '" + CommandWord.HELP + "' if you need help.");
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        boolean wantToQuit = false;
        
        finish = System.currentTimeMillis();
        if(start + 600000 <= finish) {
            return true;
        }
        
        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("I don't know what you mean...");
                break;

            case HELP:
                printHelp();
                break;

            case GO:
                goRoom(command);
                break;

            case QUIT:
                wantToQuit = quit(command);
                break;
                
            case LOOK:
                lookAt(command.getSecondWord());
                break;
                
            case TAKE:
                takeItem(command.getSecondWord());
                break;
                
            case USE:
                useItem(command.getSecondWord());
                break;

        }
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("You are lost. You are alone. You wander");
        System.out.println("around the dungeon.");
        System.out.println();
        System.out.println("Your command words are:");
        parser.showCommands();
    }
    
    /**
     * Use command. If the player is able to use an item from their inventory, do so.
     * There are only 4 use cases: 
     * Pillow in Spike Room
     * Key in Locked Door Room
     * Cube in Button Room
     * Sword in Dragon Room
     */
    public void useItem(String item) {
        if(currentRoom.equals("spike room") && player.searchFor("pillow") != null) {
            //yadda yadda yadda
        } else if(currentRoom.equals("locked room") && player.searchFor("key") != null) {
            //yadda yadda yadda
        } else if(currentRoom.equals("button room") && player.searchFor("cube") != null) {
            //yadda yadda yadda
        } else if(currentRoom.equals("dragon lair") && player.searchFor("sword") != null) {
            //yadda yadda yadda
        } else {
            System.out.println("nope, cant use this here, buckaroo");
        }
        
    }
    

    /**
     * Look command. Gives player information about an room, item, or enemy.
     */
    public void lookAt(String target) {
        if(target == null) {
            target = "room";
            //System.out.println(player.lookAt("room"));
        }
        System.out.println(player.lookAt(target));
    }
    
    /**
     * Takes an item out of a room and adds it into the player's inventory.
     */
    public void takeItem(String target)
    {
        Item item = player.searchFor(target);
        if(item == null) {
            System.out.println("I can't seem to find the item you want to take.");
        } else {
            currentRoom.getItemStorage().getItems().remove(item);
            player.getItemStorage().getItems().add(item);
            System.out.println(item.getName() + " has been added to your inventory.");
        }
    }
    
    /** 
     * Try to go in one direction. If there is an exit, enter the new
     * room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            player.setCurrentRoom(currentRoom);
            System.out.println(currentRoom.getLongDescription());
        }
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
}
