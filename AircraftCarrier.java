/**
 * An Aircraft Carrier "is-a" Ship. An Airctaft Carrier has one
 * constructor that takes no parameters and calls
 * its super class constructor, setting it's size
 * to 5, sunk status to false, and hits to 0.
 */
public class AircraftCarrier extends Ship
{
    /**
     * Aircraft Carriers are all of size 5. 
     * This constructor assigns 5 to the size field while
     * initializing sunk to false and hits to 0.
     */
    public AircraftCarrier()
    {
        super(5);
    }
}