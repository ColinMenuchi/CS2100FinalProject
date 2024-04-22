/**
 * A Battleship "is-a" Ship. A Battleship has one
 * constructor that takes no parameters and calls
 * its super class constructor, setting it's size
 * to 4, sunk status to false, and hits to 0.
 */
public class Battleship extends Ship
{
    /**
     * Battleships are all of size 4. 
     * This constructor assigns 4 to the size field while
     * initializing sunk to false and hits to 0.
     */
    public Battleship()
    {
        super(4);
    }
}