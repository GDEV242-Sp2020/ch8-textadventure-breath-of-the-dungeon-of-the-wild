import java.util.ArrayList;


/**
 * The player of the game. Has an inventory.
 *
 * @author Ryan Canuel, Tom o'Rourke
 * @version 1.0.0
 */
public class Player
{
    private ItemStorage inventory;
    private int health;
    private Room currentRoom;

    /**
     * Constructor for objects of class Player
     * @peram room takes in a room of type Room
     */
    public Player(Room room)
    {
        inventory = new ItemStorage();
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
     * Returns the ItemStorage property of the player.
     */
    public ItemStorage getItemStorage() {
        return inventory;
    }
    
    /**
     * search for an item that either the player has or it is in the room
     * @param itemName The name of the item
     */
    public Item searchFor(String itemName) {
        for(Item item : inventory.getItems()) {
            if(item.getName().equalsIgnoreCase(itemName.trim())) {
                return item;
            }
        }
        for(Item item : currentRoom.getItemStorage().getItems()) {
            if(item.getName().equalsIgnoreCase(itemName.trim())) {
                return item;
            }
        }
        return null;
    }
    
    
    /**
     * Searches through player's inventory, room's items, and room itself,
     * to try to look at something.
     * @param target this is the target of what the player is tryin to look at
     */
    public String lookAt(String target)
    {
        if(currentRoom.getName().equalsIgnoreCase(target.trim()) || target.trim().equalsIgnoreCase("room")) {
            return currentRoom.getLongDescription();
        }
        if(target.trim().equalsIgnoreCase("inventory")) {
            return itemList();
        }
        for(Item item : inventory.getItems()) {
            if(item.getName().equalsIgnoreCase(target.trim())) {
                return item.getDescription();
            }
        }
        for(Item item : currentRoom.getItemStorage().getItems()) {
            if(item.getName().equalsIgnoreCase(target.trim())) {
                return item.getDescription();
            }
        }
        return "I don't know what you're trying to look at...";
    }
    
    /**
     * Returns items in iinventory as a printable string.
     * @return itemsInside string names of items inside
     */
    public String itemList() {
        String itemsInside = "Your inventory:";
        for(Item item : inventory.getItems()) {
            itemsInside = itemsInside + " " + item.getName();
        }
        return itemsInside;
    }
    
    /**
     * SetCurrentRoom method changes the room that the player is in
     * @param room room to change player's current room to
     */
    public void setCurrentRoom(Room room) {
        currentRoom = room;
    }
}
