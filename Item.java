
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

    /**
     * Constructor for Item. 
     * 
     * @param nameOf name of room
     * @param descriptionOf description of room
     */
    public Item(String nameOf, String descriptionOf)
    {
        name = nameOf;
        description = descriptionOf;
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
     * An example of a method - replace this comment with your own
     *
     * @param  y  a sample parameter for a method
     * @return    the sum of x and y
     */
    public int sampleMethod(int y)
    {
        // put your code here
        return y;
    }
}