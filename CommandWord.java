/**
 * Representations for all the valid command words for the game
 * along with a string in a particular language.
 * 
 * @author  Michael Kölling and David J. Barnes, edited by Ryan Canuel and Tom O'Rourke
 * @version 2020
 */
public enum CommandWord
{
    // A value for each command word along with its
    // corresponding user interface string.

    GO("go"), QUIT("quit"), HELP("help"), UNKNOWN("?"), LOOK("look"), TAKE("take"), USE("use") ;

    
    // The command string.
    private String commandString;
    
    /**
     * Initialise with the corresponding command string.
     * @param commandString The command string.
     */
    CommandWord(String commandString)
    {
        this.commandString = commandString;
    }
    
    /**
     * @return The command word as a string.
     */
    public String toString()
    {
        return commandString;
    }
}
