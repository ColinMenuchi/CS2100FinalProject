/**
 * A Cruiser "is-a" Ship. A Cruiser has one
 * constructor that takes no parameters and calls
 * its super class constructor, setting it's size
 * to 3, sunk status to false, and hits to 0.
 */
public class Cruiser extends Ship
{
    /**
     * Cruisers are all of size 3. 
     * This constructor assigns 3 to the size field while
     * initializing sunk to false and hits to 0.
     */
    public Cruiser()
    {
        super(3);
    }
}
