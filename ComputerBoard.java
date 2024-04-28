/*
 * This class represents the Computer's board in a
 * game of Battleship. It inherits from the Board class.
 * It can apply player moves to itself and create a
 * String representation of itself.
 */

public class ComputerBoard extends Board {
    /**
     * Constructs a ComputerBoard object. Passes
     * filename to the super constructor.
     * 
     * @param filename - The name of the file to retrieve
     *                 board data from
     */
    public ComputerBoard(String filename) {
        super(filename);
    }

    /**
     * Takes a move and makes it AGAINST this board. Takes in move to be applied.
     * Returns either null, or, if the move sank a ship, a String along the lines of
     * "You sank
     * My Battleship!"
     * 
     * @param playersMove - The Move made by the player
     * @return - A String if they [the player] sank a battleship,
     *         null otherwise.
     */
    public String makePlayerMove(Move playersMove) {
        // Get status of the chosen cell
        // Use col - 1 to account for the 0 indexed ArrayList
        CellStatus cellStatus = applyMoveToLayout(playersMove);
        // Determine the ship at that cell if any
        ShipType shipAtCell = switch (cellStatus) {
            case NOTHING -> null;
            case DESTROYER -> ShipType.ST_DESTROYER;
            case AIRCRAFT_CARRIER -> ShipType.ST_AIRCRAFT_CARRIER;
            case BATTLESHIP -> ShipType.ST_BATTLESHIP;
            case CRUISER -> ShipType.ST_CRUISER;
            case SUB -> ShipType.ST_SUB;
            default -> null;
        };

        // Update the Fleet if a ship was hit
        if (shipAtCell != null) {
            // If a ship was sunk, switch its cell status
            // from hit to sunk
            if (getFleet().updateFleet(shipAtCell)) {
                CellStatus statusToLookFor = switch (shipAtCell)
                {
                    case ST_DESTROYER -> CellStatus.DESTROYER_HIT;
                    case ST_AIRCRAFT_CARRIER -> CellStatus.AIRCRAFT_CARRIER_HIT;
                    case ST_BATTLESHIP -> CellStatus.BATTLESHIP_HIT;
                    case ST_CRUISER -> CellStatus.CRUISER_HIT;
                    case ST_SUB -> CellStatus.SUB_HIT;
                };
                CellStatus newStatus;
                String returnMessage;
                switch (statusToLookFor)
                {
                    case DESTROYER_HIT:
                    {
                        newStatus = CellStatus.DESTROYER_SUNK;
                        returnMessage = "You sunk my Destroyer!";
                        break;
                    }
                    case AIRCRAFT_CARRIER_HIT:
                    {
                        newStatus = CellStatus.AIRCRAFT_CARRIER_SUNK;
                        returnMessage = "You sunk my Aircraft Carrier!";
                        break;
                    }
                    case BATTLESHIP_HIT:
                    {
                        newStatus = CellStatus.BATTLESHIP_SUNK;
                        returnMessage = "You sunk my Battleship!";
                        break;
                    }
                    case CRUISER_HIT:
                    {
                        newStatus = CellStatus.CRUISER_SUNK;
                        returnMessage = "You sunk my Cruiser!";
                        break;
                    }
                    case SUB_HIT: 
                    {
                        newStatus = CellStatus.SUB_SUNK;
                        returnMessage = "You sunk my Sub!";
                        break;
                    }
                    default:
                    {
                        newStatus = null;
                        returnMessage = null;
                    }
                }

                for (int row = 0; row < 10; row++)
                {
                    for (int col = 0; col < 10; col++)
                    {
                        if (getLayout().get(row).get(col) == statusToLookFor)
                        {
                            getLayout().get(row).set(col, newStatus);
                        }
                    }
                }
                // Returns iff a ship sank
                return returnMessage;
            }
        }
        // Returns if no ships sank
        return null;
    }

    /**
     * Returns a String representation of the ComputerBoard,
     * displaying the first character of the String
     * returned by the toString method overridden in
     * CellStatus
     * 
     * @return - A String representation of the Computer's Board.
     */
    @Override
    public String toString() {
        CellStatus cellStatus;
        String boardAsString = "  1 2 3 4 5 6 7 8 9 10\n";
        char rowLetter = 'A';

        // Iterate through each cell on the board,
        // concatenating each to boardAsString
        for (int row = 0; row < 10; row++) {
            boardAsString += rowLetter + " ";
            for (int col = 0; col < 10; col++) {
                cellStatus = getLayout().get(row).get(col);
                boardAsString += cellStatus.toString().substring(0, 1) + " ";
            }
            boardAsString += "\n";
            rowLetter = (char)(rowLetter + 1);
        }
        return boardAsString;
    }

}
