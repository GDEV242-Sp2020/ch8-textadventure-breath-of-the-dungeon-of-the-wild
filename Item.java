
/**
 * Items or objects that are in the world. Can be manipulated by the player,
 * also used to ulock stuff.
 *
 * @author Ryan Canuel and Tom O'Rourke
 * @version 1.0.0
 */
public class Item
{
    private String name;
    private String description;
    private int weight;

    /**
     * Constructor for Item. 
     * 
     * @param nameOf name of room
     * @param descriptionOf description of room
     */
    public Item(String nameOf, String descriptionOf, int weightOf)
    {
        name = nameOf;
        description = descriptionOf;
        weight = weightOf;
    }

    /**
     * Returns name of item.
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns description of item.
     */
    public String getDescription() {
        return description;
    }
    
    /**
     * Returns weight of item.
     */
    public int getWeight() {
        return weight;
    }
}
