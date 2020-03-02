import java.util.ArrayList;


/**
 * The player of the game. Has an inventory.
 *
 * @author Ryan Canuel, Tom o'Rourke
 * @version 1.0.0
 */
public class Player
{
    private ArrayList<Item> inventory;
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
     * Returns the current room player is in.
     */
    public Room getRoom() {
        return currentRoom;
    }
    
    /**
     * Searches through player's inventory, room's items, and room itself,
     * to try to look at something.
     */
    public String lookAt(String target)
    {
        for(Item item : inventory) {
            if(item.getName().equalsIgnoreCase(target.trim())) {
                return item.getDescription();
            }
        }
        if(currentRoom.getName().equalsIgnoreCase(target.trim())) {
            return currentRoom.getDescription();
        }
        for(Item item : currentRoom.getItems()) {
            if(item.getName().equalsIgnoreCase(target.trim())) {
                return item.getDescription();
            }
        }
        return "I don't know what you're trying to look at...";
    }
}
