import java.util.Set;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ArrayList;

/**
 * Class Room - a room in an adventure game.
 *
 * 
 * 
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  For each existing exit, the room 
 * stores a reference to the neighboring room.
 * 
 * @author  
 * @version 2016.02.29
 */

public class Room 
{
    private String name;
    private String description;
    private HashMap<String, Room> exits;        // stores exits of this room.
    private ItemStorage items;
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = new ItemStorage();
    }
    
    
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard". Also populates room with items.
     * 
     * @param nameOf name of room.
     * @param description The room's description.
     * @param itemsToAdd items contained in room.
     */
    public Room(String nameOf, String description, ItemStorage itemsToAdd) 
    {
        this.description = description;
        exits = new HashMap<>();
        items = itemsToAdd;
        name = nameOf;
    }
    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard". Can't directly add item in this overload.
     * 
     * @param nameOf name of room.
     * @param description The room's description.
     */
    public Room(String nameOf, String description) 
    {
        this.description = description;
        exits = new HashMap<>();
        name = nameOf;
        items = new ItemStorage();
    }
    // /**
     // * This will add an item to the array list of the 
     // * 
     // */
    // public void addItem(Item item)
    // {
        // items.addItem(item);
    // }
    
    
    
    
    /**
     * Returns name of room.
     * @return name String name of item
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns description of room.
     * @return description string description of item
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Returns items in the room.
     * @return items of type itemStorage
     */
    public ItemStorage getItemStorage() {
        return items;
    }
    
    /**
     * Define an exit from this room.
     * @param direction The direction of the exit.
     * @param neighbor  The room to which the exit leads.
     */
    public void setExit(String direction, Room neighbor) 
    {
        exits.put(direction, neighbor);
    }

    /**
     * @return The short description of the room
     * (the one that was defined in the constructor).
     */
    public String getShortDescription()
    {
        return description;
    }

    /**
     * Return a description of the room in the form:
     *     You are in the kitchen.
     *     Exits: north west
     * @return A long description of this room
     */
    public String getLongDescription()
    {
        return "You are " + description + ".\n"
               + itemList() + ".\n" 
               + getExitString();
    }

    
    /**
     * Returns items in items as a printable string.
     * @return itemsInside string names of items inside
     */
    public String itemList() {
        String itemsInside = "Items here:";
        for(Item item : items.getItems()) {
            itemsInside = itemsInside + " " + item.getName() ;
        }
        return itemsInside;
    }
    
    /**
     * Return a string describing the room's exits, for example
     * "Exits: north west".
     * @return Details of the room's exits.
     */
    private String getExitString()
    {
        String returnString = "Exits:";
        Set<String> keys = exits.keySet();
        for(String exit : keys) {
            returnString += " " + exit;
        }
        return returnString;
    }

    /**
     * Return the room that is reached if we go from this room in direction
     * "direction". If there is no room in that direction, return null.
     * @param direction The exit's direction.
     * @return The room in the given direction.
     */
    public Room getExit(String direction) 
    {
        return exits.get(direction);
    }
}

