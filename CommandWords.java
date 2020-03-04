import java.util.HashMap;

/**
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.
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
        System.out.println("Here are your availible commands. ");
        for(String command : validCommands.keySet()) {
            switch (command) {
                case "help":
                    System.out.println("Help: ");
                    System.out.println("Help allows you to yadda yadda whatever");
                    break;
    
                case "go":
                    System.out.println("Go: ");
                    System.out.println("Help allows you to yadda yadda whatever");
                    break;
    
                case "quit":
                    System.out.println("Quit: ");
                    System.out.println("Help allows you to yadda yadda whatever");
                    break;
                    
                case "look":
                    System.out.println("Look: ");
                    System.out.println("Help allows you to yadda yadda whatever");
                    break;
                    
                case "take":
                    System.out.println("Take: ");
                    System.out.println("allows you to take an item in a room");
                    break;
                }
                
        }
        System.out.println();
    }
}
