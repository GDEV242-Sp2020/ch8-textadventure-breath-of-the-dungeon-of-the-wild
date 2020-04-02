
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
        //Room theater, pub, lab, office;
        
        
        Room outside, atrium, southStair, floorLanding, cubeRoom, grandHall, heavyButton;
        Room pillowRoom, spikeRoom, pillowKeyRoom, northStair,lockedDoor, armory;
        Room dragoFight, treasureRoom;
        
        ArrayList<Item> itemsToAdd = new ArrayList<Item>();
        
        //itemsToAdd.add(new Item("beans", "can of beans yum yum", 5));
        
        outside = new Room("Outside", "outside the main entrance of the" + 
                           " dungeon.");
        //itemsToAdd = new ArrayList<Item>();
        
        atrium = new Room("Atrium", "Inside the dungeon you see a stairwell and a grand hall.");
        
        southStair = new Room("Southern Stair Well", "There isn't anything too " 
                + "special here, maybe some stairs i guess");
        floorLanding = new Room("Second floor landing", "Not really much in here");
        cubeRoom = new Room("Cube Room", "This room had some sort of trap door to the left, but "
        + " in the center there is pedistool with something on it");
        grandHall = new Room("Grand Hall", "This room is quite grand, it appears that there are doors to the left and right");
        
        itemsToAdd.add(new Item("Cube", "This cube is heavier than expected", 25));
        heavyButton = new Room("Button Room", "This room appears to have a button in the center of it."
            + " I wonder what would happen if you put something on it", new ItemStorage(itemsToAdd));
        itemsToAdd = new ArrayList<Item>();
        
        pillowRoom = new Room("Pillow Room", "This room is is filled with large soft pillows");
        spikeRoom = new Room("Spike Room", "This room has a spikes in the center of it." +
            " Wouldn't want to fall on them.");
       
        itemsToAdd.add(new Item("pillow","soft pillow perfect for landing on", 3));
        itemsToAdd.add(new Item("key","this key is used for a door", 1));
        pillowKeyRoom = new Room("Pillow Room", "This room has a pedistool with a pillow on it",
            new ItemStorage(itemsToAdd));
        itemsToAdd = new ArrayList<Item>();
        
        northStair = new Room("Northern Stair Well", "This doesn't really have much in it, " +
             "best to move forward");
        lockedDoor = new Room("Locked Door Room","This room has a locked door in it, " +
            "I probably need a key to get through");
        
        itemsToAdd.add(new Item("Sword","this new sword is expertly crafted. "
            + "Appears sharp enough to slay a dragon or something...",10));
        armory = new Room("Armory","This room appears to be an armory." + 
            "There would appear to be a sword finished on the table", new ItemStorage(itemsToAdd));
        itemsToAdd = new ArrayList<Item>();
        
        dragoFight = new Room("Dragon nest", "This room has a large dragon sleeping in the middle" 
            + "I bed I could use a sword to defeat him");
        treasureRoom = new Room("Treasure Room", "This room is filled with treasures beyond my wildest delight");
        
        
        
        
        
        
        
        // initialise room exits
        outside.setExit("east", atrium);
        
        atrium.setExit("west", outside);
        atrium.setExit("north", grandHall);
        atrium.setExit("east", southStair);
        
        floorLanding.setExit("west", southStair);
        floorLanding.setExit("north", cubeRoom);
        
        grandHall.setExit("south", atrium);
        grandHall.setExit("west", heavyButton);
        grandHall.setExit("east", pillowRoom);
        
        cubeRoom.setExit("south", floorLanding);
        cubeRoom.setExit("west", pillowRoom);
        
        pillowRoom.setExit("west", grandHall);
        pillowRoom.setExit("north", spikeRoom);
        
        spikeRoom.setExit("south", pillowRoom);
        spikeRoom.setExit("west", pillowKeyRoom);
        spikeRoom.setExit("north", northStair);
        
        pillowKeyRoom.setExit("east", spikeRoom);
        
        northStair.setExit("south", spikeRoom);
        northStair.setExit("east", lockedDoor);
        
        lockedDoor.setExit("west", northStair);
        lockedDoor.setExit("south", armory);
        
        armory.setExit("north", lockedDoor);
        armory.setExit("west", pillowKeyRoom);
        
        heavyButton.setExit("east", grandHall);
        heavyButton.setExit("north", dragoFight);
        
        dragoFight.setExit("south", heavyButton);
        dragoFight.setExit("north", treasureRoom);
        
        treasureRoom.setExit("south", dragoFight);
        
        

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
        System.out.println("Welcome to The Dungeon of Diners, Dragons, Drive Ins, and Dives");
        System.out.println();
        System.out.println("You are an adventurer, hoping to find treasure in the famed dungeon,");
        System.out.println("which people around here usually shorten to \"The DoDDDIaD\" or just ");
        System.out.println("\"the Dungeon.\"");
        System.out.println();
        System.out.println("You can always type \"help\" if you need help. Oh, and you also only ");
        System.out.println("have ten minutes to complete this or else the Dungeon's cursed halls ");
        System.out.println("wil devour your soul eternally. Have fun!");
        System.out.println();
        System.out.println();
        System.out.println(currentRoom.getLongDescription());
    }

    /**
     * Given a command, process (that is: execute) the command.
     * Also checks to see if the time has run out and the game is over.
     * @param command The command to be processed.
     * @return true If the game is now over, false otherwise.
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
