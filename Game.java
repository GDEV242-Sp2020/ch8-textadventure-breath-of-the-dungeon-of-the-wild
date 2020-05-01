
import java.util.ArrayList;

/**
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes, edited by Ryan Canuel and Tom O'Rourke
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private Player player;
    private long start;
    private long finish;
    private boolean wantToQuit;
    
    private Room outside, atrium, southStair, floorLanding, cubeRoom, grandHall, heavyButton;
    private Room pillowRoom, spikeRoom, pillowKeyRoom, northStair, lockedDoor, armory;
    private Room dragonFight, treasureRoom;
        
        
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
        ArrayList<Item> itemsToAdd = new ArrayList<Item>();
        
        outside = new Room("Outside", "outside the main entrance of the dungeon.\n" +
                            "To the east is a stone archway leading into an atrium.");
        
        atrium = new Room("Atrium", "inside the atrium.\n"+
                            "You see a stairwell to the east and the Grand Hall to the north. \n"+
                            "To your west is the stone archway leading outside.\n");
        
        southStair = new Room("Southern Stair Well", "in the Southern Stairwell. \n"+
                            "East will lead you up the stairs, and west will bring you to the \n"+
                            "atrium.\n");
                            
        floorLanding = new Room("Second floor landing", "at the top of the south stairs. \n"+
                            "There is a room to the north, and west will bring you down the stairs. \n");
        
        itemsToAdd.add(new Item("cube", "This is a large, stone cube. It's very heavy. \n", 25));
        cubeRoom = new Room("Cube Room", "in a room.\n"+
                            "The floor has a hole in the west, leading down to a room filled with \n"+
                            "pillows, and an exit in the south. There is a large pedestal in the \n"+
                            "center. \n", new ItemStorage(itemsToAdd));
        
        itemsToAdd = new ArrayList<Item>();
        grandHall = new Room("Grand Hall", "in the Grand Hall. \n" + 
                            "A door to the west leads to a room with a large button, and a door \n"+
                            "to the east goes to a room filled with pillows. The Atrium is south \n"+
                            "of you. \n");
        
        heavyButton = new Room("Button Room", "in a room with a large button in the center of it. \n"+
                                "The Grand Hall is to the east, and there is a heavy door to the \n"+
                                "north. You wonder what would happen if you weighed the button down. \n");
        
        
        pillowRoom = new Room("Pillow Room", "in a room filled with large soft pillows, and a hole \n"+
                            "in the ceiling. The Grand Hall is to your west, and there is a room \n"+
                            "with some spikes on the floor to the north. \n");
        
        spikeRoom = new Room("Spike Room", "in a room with spikes on the floor, underneath a hole \n"+
                            "in the ceiling. There is a room filled with pillows to the south, a \n"+
                            "room with a pedestal to the west, and a stairwell to the north. \n");
       
        itemsToAdd.add(new Item("pillow","A giant, soft, pillow. Perfect for cushioning a fall. \n", 3));
        itemsToAdd.add(new Item("key","This hefty key is used on a similar-sized lock. \n", 1));
        pillowKeyRoom = new Room("Pillow Room", "in a room with a pedestal in the center. \n"+
                            "The spiked room is to your east. \n",new ItemStorage(itemsToAdd));
                            
        itemsToAdd = new ArrayList<Item>();
        northStair = new Room("Northern Stair Well", "in the Northern Stairwell. \n"+
                            "East will bring you up the stairs, and the spiked room is to the \n"+
                            "south. \n");
                            
        lockedDoor = new Room("Locked Room","at the top of the Northern Stairwell. \n"+
                            "West will bring you down the stairs, and the door to the south has \n"+
                            "a hefty lock on it. \n");
        
        itemsToAdd.add(new Item("sword","The hilt of this sword fits perfectly into your hand, and \n"+
                            "the blade glows blue as you hold it. \n",3));
        armory = new Room("Armory","in an armory, complete with anvil and hammers. \n" + 
                            "In the center of the room sits an elegantly crafted sword stand. \n"+
                            "The floor has a hole in the west, leading down to a room filled with \n"+
                            "spikes. The top of the stairs is to the north. \n", new ItemStorage(itemsToAdd));
                            
        itemsToAdd = new ArrayList<Item>();
        dragonFight = new Room("Dragon Room", "in a Dragon's Lair. \n"+
                            "A great crimson beast slumbers atop a pile of bones. There is an ornate, \n"+
                            "golden door on the north wall. You need be careful, so you don't get \n"+
                            "killed! \n");
            
        treasureRoom = new Room("Treasure Room", "\nYou are in a room, surrounded with treasures beyond your wildest delight! \n"+
                            "Surrounding you are piles of gold, chests filled with gems and \n"+
                            "jewelery, and ornate artifacts. You step in, to admire it all, and- \n"+
                            "wait, what? Did the door just close?\n"+
                            "\n"+
                            "~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"+
                            "\n"+
                            "Congratulations! You have completed the game.\n"+
                            "Remember to go outside in real life, and drink some water. \n"+
                            "\n"+
                            "Thanks for playing!\n"+
                            "\n"+
                            "-Ryan Canuel and Tom O'Rourke");
        
        // initialise room exits
        outside.setExit("east", atrium);
        //outside.setExit("south", treasureRoom); //only for playtesting purposes
        
        atrium.setExit("west", outside);
        atrium.setExit("north", grandHall);
        atrium.setExit("east", southStair);
        
        southStair.setExit("west", atrium);
        southStair.setExit("east", floorLanding);
        
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
        
        armory.setExit("north", lockedDoor);
        armory.setExit("west", spikeRoom);
        
        heavyButton.setExit("east", grandHall);
        
        dragonFight.setExit("south", heavyButton);
        
        treasureRoom.setExit("south", dragonFight);
        
        currentRoom = outside;  // start game outside
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();
     
        boolean finished = false;
        while (!finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
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
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(currentRoom.getLongDescription());
        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * Also checks to see if the time has run out and the game is over.
     * @param command The command to be processed.
     * @return true If the game is now over, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
        wantToQuit = false;
        
        finish = System.currentTimeMillis();
        if(start + 600000 <= finish) {
            System.out.println("\n" + "You begin to collapse. The Dungeon's Curse is getting to you.\n"+
                                "Your time is up.\n"+
                                "Thanks for playing! Goodbye.\n");
            return true;
        }
        
        CommandWord commandWord = command.getCommandWord();

        switch (commandWord) {
            case UNKNOWN:
                System.out.println("\n" + "I don't know what you mean...");
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
        
        System.out.println();
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println();
        
        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some silly, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
        System.out.println("\n" + "You are lost. You are alone. You wander ");
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
        if(currentRoom.getName().equals("Spike Room") && player.searchFor("pillow") != null) {
            armory.setExit("west", spikeRoom); //first, connect rooms
            System.out.println("\n" + "You place the pillow over the spikes. It should be safe to land on, now.");//then, print flavor text
        } else if(currentRoom.getName().equals("Locked Room") && player.searchFor("key") != null) {
            lockedDoor.setExit("south", armory);
            System.out.println("\n" + "You unlock the lock with the matching key. \n"+
                                "Now, you can enter through the door.");
        } else if(currentRoom.getName().equals("Button Room") && player.searchFor("cube") != null) {
            heavyButton.setExit("north", dragonFight);
            System.out.println("\n" + "You place the cube on the button, and the north door opens.");
        } else if(currentRoom.getName().equals("Dragon Room") && player.searchFor("sword") != null) {
            dragonFight.setExit("north", treasureRoom);
            System.out.println("\n" + "You silently creep towards the sleeping dragon. Your heart \n"+
                                "pounds in your chest, and a nervous sweat rolls down your brow.\n\n"+
                                "As you approach, you can feel the warmth of each of the dragon's \n"+
                                "exhalations, and smell the musty stench of it's breath.\n\n"+
                                "Slowly, you come closer to the beast, and you raise the gleaming \n"+
                                "blade above your head, grasping it with both hands- and with one \n"+
                                "mighty swing, you plunge it into the dragon's skull. The sword's \n"+
                                "light flickers and slowly fades, as the dragon ceases moving.\n\n"+
                                "You have slain a dragon.\n"+
                                "You can now go through the golden door in the north.");
        } else {
            System.out.println("\n" + "You cannot use this here.");
        }
        
    }
    

    /**
     * Look command. Gives player information about an room, item, or their inventory.
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
            System.out.println("\nI can't seem to find the item you want to take.");
        } else {
            currentRoom.getItemStorage().getItems().remove(item);
            player.getItemStorage().getItems().add(item);
            System.out.println("\nThe " + item.getName() + " has been added to your inventory.");
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
            System.out.println("\n" + "Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = currentRoom.getExit(direction);

        if (nextRoom == null) {
            System.out.println("\n" + "You can't go that way.");
        } else if(nextRoom.getName().equals("Treasure Room")) {
            currentRoom = nextRoom;
            System.out.println(currentRoom.getDescription());
            wantToQuit = true;
        }else {
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
            System.out.println("\n" + "If you want to exit the game, just type \"quit.\"");
            return false;
        }
        else {
            System.out.println("Thanks for playing! \n"+
                                "Hope you come back to finish the game later. \n"+
                                "Bye!");
            return true;  // signal that we want to quit
        }
    }
}
