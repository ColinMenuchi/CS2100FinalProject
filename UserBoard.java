/**
 * This class represents the User's board in a
 * game of Battleship. It inherits from the Board class.
 * It can apply computer moves to itself and create a
 * String representation of itself.
 */

import java.util.ArrayList;
import java.util.Random;

public class UserBoard extends Board {
    // Instance Variables
    private ArrayList<Move> moves;
    private Random rand;

    /**
     * Constructs a UserBoard object. Passes
     * filename to the super constructor.
     * Initializes a Random object and an
     * ArrayList of Move objects.
     * 
     * @param filename - The name of the file to retrieve
     *                 board data from. Then initializes a
     *                 Random object and an ArrayList of all
     *                 possible Moves.
     */
    public UserBoard(String filename) {
        super(filename);
        this.rand = new Random();
        this.moves = new ArrayList<Move>();
        // Fill the moves with all possible Moves
        for (int row = 1; row <= 10; row++) {
            for (int col = 1; col <= 10; col++) {
                moves.add(new Move(row, col));
            }
        }
    }

    /**
     * Selects and makes a move AGAINST this board.
     * Returns an array of two Strings. The first
     * is the move the computer made in user
     * readable form. The second is either null, or,
     * if the move resulted in a ship being sunk, a
     * string saying "You sunk my Battleship!"
     * toString. Returns a String representation of the
     * ComputerBoard, displaying the second character of
     * the String returned by the toString method
     * overridden in CellStatus
     * 
     * @return - A String array containing the move the
     *         computer made and the second character of the
     *         String returned by the toString method overridden
     *         in CellStatus
     */
    public String[] makeComputerMove() {
        // Select a random move until a valid move
        // is chosen
        boolean validMove = false;
        int[] moveChoice = new int[2];
        Move selectedMove = null;
        while (!validMove) {
            // Pick a random cell
            moveChoice[0] = rand.nextInt(10) + 1;
            moveChoice[1] = rand.nextInt(10) + 1;
            selectedMove = new Move(moveChoice[0], moveChoice[1]);

            // Check if this Move is still available
            validMove = moveAvailable(selectedMove);
            // Otherwise, repeat this process
        }
        // Make Move against the player board
        CellStatus cellHit = applyMoveToLayout(selectedMove);

        // Update the correct ship in the
        // Fleet if a ship was hit
        boolean shipSank = false;
        switch (cellHit) {
            case AIRCRAFT_CARRIER: {
                shipSank = getFleet().updateFleet(ShipType.ST_AIRCRAFT_CARRIER);
                break;
            }
            case BATTLESHIP: {
                shipSank = getFleet().updateFleet(ShipType.ST_BATTLESHIP);
                break;
            }
            case CRUISER: {
                shipSank = getFleet().updateFleet(ShipType.ST_CRUISER);
                break;
            }
            case DESTROYER: {
                shipSank = getFleet().updateFleet(ShipType.ST_DESTROYER);
                break;
            }
            case SUB: {
                shipSank = getFleet().updateFleet(ShipType.ST_SUB);
                break;
            }
        }
        // If a ship sank, indicate that in the
        // return message.
        String returnMessage = null;
        if (shipSank) {
            returnMessage = "You sunk my Battleship!";
        }

        // Return the array with the move and
        // information about whether a ship sank
        // or not.
        String[] returnArray = new String[2];
        returnArray[0] = selectedMove.toString();
        returnArray[1] = returnMessage;
        return returnArray;
    }

    /**
     * Returns a String representation of the UserBoard,
     * displaying the second character of the String
     * returned by the toString method overridden in
     * CellStatus
     * 
     * @return - A String representation of the User's Board.
     */
    @Override
    public String toString() {
        CellStatus cellStatus;
        String boardAsString = "";

        // Iterate through each cell on the board,
        // concatenating each to boardAsString
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                cellStatus = getLayout().get(row).get(col);
                boardAsString = boardAsString + cellStatus.toString().substring(1) + " ";
            }
            boardAsString = boardAsString + "\n";
        }
        return boardAsString;
    }
}
