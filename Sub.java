/**
 * A Sub "is-a" Ship. A Sub has one
 * constructor that takes no parameters and calls
 * its super class constructor, setting it's size
 * to 3, sunk status to false, and hits to 0.
 * This is the ultimate "sub-class".
 */
public class Sub extends Ship
{
    /**
     * Subs are all of size 3. 
     * This constructor assigns 3 to the size field while
     * initializing sunk to false and hits to 0.
     */
    public Sub()
    {
        super(3);
    }
}
