import java.util.ArrayList;


/**
 * The player of the game. Has an inventory.
 *
 * @author Ryan Canuel, Tom o'Rourke
 * @version 1.0.0
 */
public class Player
{
    private ArrayList<Player> inventory;
    private int health;
    private Room currentRoom;

    /**
     * Constructor for objects of class Player
     */
    public Player(Room room)
    {
        inventory = new ArrayList<>();
        health = 5;
        currentRoom = room;
    }

    /**
     * 
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return y;
    }
}
