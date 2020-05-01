import java.util.HashMap;

/**
 * 
 * 
 * 
 * This class holds an enumeration of all command words known to the game.
 * It is used to recognise commands as they are typed in.
 *
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class CommandWords
{
    // A mapping between a command word and the CommandWord
    // associated with it.
    private HashMap<String, CommandWord> validCommands;

    /**
     * Constructor - initialise the command words.
     */
    public CommandWords()
    {
        validCommands = new HashMap<>();
        for(CommandWord command : CommandWord.values()) {
            if(command != CommandWord.UNKNOWN) {
                validCommands.put(command.toString(), command);
            }
        }
    }

    /**
     * Find the CommandWord associated with a command word.
     * @param commandWord The word to look up.
     * @return The CommandWord correspondng to commandWord, or UNKNOWN
     *         if it is not a valid command word.
     */
    public CommandWord getCommandWord(String commandWord)
    {
        CommandWord command = validCommands.get(commandWord);
        if(command != null) {
            return command;
        }
        else {
            return CommandWord.UNKNOWN;
        }
    }
    
    /**
     * Check whether a given String is a valid command word. 
     * @return true if it is, false if it isn't.
     */
    public boolean isCommand(String aString)
    {
        return validCommands.containsKey(aString);
    }

    /**
     * Print all valid commands to System.out.
     */
    public void showAll() 
    {
        System.out.println("");
        for(String command : validCommands.keySet()) {
            switch (command) {
                case "help":
                    System.out.println("Help: ");
                    System.out.println("Help allows you to see availible commands and \n" + 
                                       "tells you the synopsis of the game. You used \n" +
                                       "this command to get here.\n" +
                                       "Example- 'help'\n");
                    break;
    
                case "go":
                    System.out.println("Go: ");
                    System.out.println("Go allows you to move between rooms.\n" +
                                       "Example- 'go north' or 'go south' \n");
                    break;
    
                case "quit":
                    System.out.println("Quit: ");
                    System.out.println("Quit allows you to quit the game.\n" +
                                       "But like, only if you *really* need to.\n" +
                                       "Example- 'quit'\n");
                    break;
                    
                case "look":
                    System.out.println("Look: ");
                    System.out.println("Look allows you to inspect the room that\n" +
                                       "you are in. It also allows you to inspect\n" +
                                       "an item that is either in the room or in \n" +
                                       "your inventory.\n" +
                                       "Example- 'look' or 'look inventory' or 'look dagger'");
                    break;
                    
                case "take":
                    System.out.println("Take: ");
                    System.out.println("Take allows you to take an item that is\n" +
                                       "in the same room as your character.\n" +
                                       "Example- 'take beans' or 'take key'\n");
                    break;
                    
                case "use":
                    System.out.println("Use: ");
                    System.out.println("Use allows you to use an item that's in your \n" +
                                       "inventory inside of the room that you're in \n" +
                                       "Example- 'use cheese' or 'use mug'\n");
                    break;
                
            } 
        }
        System.out.println();
    }
}
