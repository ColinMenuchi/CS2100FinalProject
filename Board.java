/**
 * Abstract class that represents a basic Board for
 * the popular game Battle Ship. Serves as the superclass
 * for a player board and a computer board. 
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;

public abstract class Board {
    // Instance Variables
    private ArrayList<ArrayList<CellStatus>> layout;
    private Fleet fleet;
    public static final int SIZE = 10;

    /**
     * Takes filename as a parameter. Initializes layout,
     * initially setting all cells to CellStatus.NOTHING.
     * Gets information from file and adds ships to the layout.
     * Initializes Fleet.
     * 
     * @param filename - The filename holding the desired information.
     */
    public Board(String filename) {
        // Initialize layout
        layout = new ArrayList<ArrayList<CellStatus>>(SIZE);

        // Set all cells to CellStatus.NOTHING.
        for (int row = 0; row < 10; row++) {
            for (int col = 0; col < 10; col++) {
                layout.get(row).add(CellStatus.NOTHING);
            }
        }

        // Get information from the file and add ships
        // to the layout.
        Scanner inputFile = null;
        try {
            inputFile = new Scanner(new File(filename));
        } catch (FileNotFoundException e) {
            System.out.println("Invalid file, terminating program.");
        }
        String[] shipArray;
        CellStatus shipType;
        String shipSymbol;
        int startRow;
        int endRow;
        int startCol;
        int endCol;
        while (inputFile.hasNext()) {
            shipArray = inputFile.nextLine().split(" ");
            shipSymbol = shipArray[0];
            startRow = shipArray[1].charAt(0) - 'A';
            endRow = shipArray[2].charAt(0) - 'A';
            startCol = Integer.parseInt(shipArray[1].substring(0, 1));
            endCol = Integer.parseInt(shipArray[2].substring(0, 1));

            // Determine the Type of Ship
            shipType = switch (shipSymbol) {
                case "D" -> CellStatus.DESTROYER;
                case "A" -> CellStatus.AIRCRAFT_CARRIER;
                case "B" -> CellStatus.BATTLESHIP;
                case "C" -> CellStatus.CRUISER;
                case "S" -> CellStatus.SUB;
                default -> CellStatus.NOTHING;
            };

            // If start row matches end row, ship is horizontal
            if (startRow == endRow) {
                for (int col = startCol; col < endCol; col++) {
                    layout.get(startRow).add(col, shipType);
                }
            }
            // Otherwise, ship is vertical.
            else {
                for (int row = startRow; row < endRow; row++) {
                    layout.get(row).add(startCol, shipType);
                }
            }
        }
        inputFile.close();

        // Initialize the Fleet
        this.fleet = new Fleet();
    }

    /**
     * Applies a move to layout. If the targeted cell does not contain a ship, it is set to
     * CellStatus.NOTHING_HIT. If it contains a ship, the cell is changed from, for instance,
     * CellStatus.SUB to CellStatus.SUB_HIT. It returns the original CellStatus of the
     * targeted cell.
     * @param move - The move that the player/computer made
     * @return - The status of the chosen cell before the move
     *           was made.
     */
    public CellStatus applyMoveToLayout(Move move) {
        // Get the chosen Cell
        CellStatus originalStatus = layout.get(move.row()).get(move.col());
        CellStatus newStatus = null;

        // Determine what is at the chosen Cell
        newStatus = switch(originalStatus)
        {
            case NOTHING -> CellStatus.NOTHING_HIT;
            case DESTROYER -> CellStatus.DESTROYER_HIT;
            case AIRCRAFT_CARRIER -> CellStatus.AIRCRAFT_CARRIER_HIT;
            case BATTLESHIP -> CellStatus.BATTLESHIP_HIT;
            case CRUISER -> CellStatus.CRUISER_HIT;
            case SUB -> CellStatus.SUB_HIT;
            default -> CellStatus.NOTHING_HIT;
        };

        // Update the chosen Cell
        layout.get(move.row()).set(move.col(), newStatus);

        // Return the original status of the Cell
        return originalStatus;
    }

    /**
     * Takes a move as a parameter and determines if the spot is available. (any CellStatus
     * that isn’t hit or sunk).
     * @param move - The move to check.
     * @return - A boolean: true if the move is available,
     *           false otherwise.
     */
    public boolean moveAvailable(Move move)
    {
        CellStatus selectedCell;
        selectedCell = layout.get(move.row()).get(move.col());
        return selectedCell == CellStatus.NOTHING;
    }

    /**
     * Returns a shallow copy of this Board's layout.
     * @return - The layout of this Board.
     */
    public ArrayList<ArrayList<CellStatus>> getLayout()
    {
        return this.layout;
    }

    /**
     * Returns a shllow copy of this Board's Fleet.
     * @return - The Fleet belonging to this Board
     */
    public Fleet getFleet()
    {
        return this.fleet;
    }

    /**
     * Determines whether or not the game is over.
     * @return - A boolean: true if all ships have
     *           been sunk, false otherwise.
     */
    public boolean gameOver()
    {
        return this.fleet.gameOver();
    }
}
