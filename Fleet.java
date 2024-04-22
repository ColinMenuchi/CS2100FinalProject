/**
 * Represents a Fleet of ships for the game Battleship.
 * A fleet has five ships.
 */

public class Fleet 
{
    // Instance Variables
    private Ship battleShip;
    private Ship aircraftCarrier;
    private Ship cruiser;
    private Ship sub;
    private Ship destroyer;

    /**
     * Initializes the Ship fields of a fleet.
     */
    public Fleet()
    {
        battleShip = new Battleship();
        aircraftCarrier = new AircraftCarrier();
        cruiser = new Cruiser();
        sub = new Sub();
        destroyer = new Destroyer();
    }

    /**
     * Informs the appropriate ship that is has been hit, and
     * returns true if this sank the ship, and false if
     * it did not.
     * @param ship - The ship to update
     * @return - A boolean. True if the ship was sunk,
     *           false otherwise.
     */
    public boolean updateFleet(ShipType ship)
    {
        return switch (ship)
        {
            case ST_AIRCRAFT_CARRIER -> aircraftCarrier.hit();
            case ST_BATTLESHIP -> battleShip.hit();
            case ST_CRUISER -> cruiser.hit();
            case ST_DESTROYER -> destroyer.hit();
            case ST_SUB -> sub.hit();
            default -> false;
        };
    }

    /**
     * Returns true if all ships have been sunk, false
     * otherwise.
     * @return - True if the game is over, false otherwise.
     */
    public boolean gameOver()
    {
        return battleShip.getSunk() &&
        aircraftCarrier.getSunk() &&
        cruiser.getSunk() &&
        destroyer.getSunk() &&
        sub.getSunk();
    }
}
