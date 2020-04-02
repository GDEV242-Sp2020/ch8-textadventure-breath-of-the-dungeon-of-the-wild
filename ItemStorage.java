import java.util.ArrayList;


/**
 *  Universal inventory of items for rooms and players.
 *
 * @author Ryan and tom
 * @version 1
 */
public class ItemStorage
{
    private ArrayList<Item> items;
    
    /**
     * Constructor for objects of class ItemStorage
     */
    public ItemStorage()
    {
        items = new ArrayList<Item>();
    }
    
    /**
     * Constructor for objects of class ItemStorage that initializes with a parameter arraylist
     * @param list which is an ArrayList of type Item
     */
    public ItemStorage( ArrayList<Item> list)
    {
        items = list;
    }
    
    /**
     * Returns the arraylist items.
     * @return items An array list of type item
     */
    public ArrayList<Item> getItems() {
        return items;
    }
    
    /**
     * Adds items to the itemStorage
     * @param item
     */
    public void addItem(Item item)
    {   
        items.add(item);
    }
}
