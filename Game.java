public class Game {
    // Instance variables
    private ComputerBoard computer;
    private UserBoard player;

    /**
     * Constructor for a Game object. Initializes
     * computer to a ComputerBoard and player to
     * a UserBoard.
     */
    public Game() {
        computer = new ComputerBoard("compFleet.txt");
        player = new UserBoard("userFleet.txt");
    }

    /**
     * Calls the makeComputerMove() method on the UserBoard
     * to make a move against that board.
     * 
     * @return - An an array of two Strings. The first is the move the computer made
     *         in user
     *         readable form. The second is either null, or, if the move resulted in
     *         a ship being sunk, a
     *         string along the lines of "You sunk my Battleship!"
     */
    public String[] makeComputerMove() {
        return player.makeComputerMove();
    }

    /**
     * Calls the makePlayerMove() method on the ComputerBoard
     * to make a move against that board.
     * 
     * @param playersMove - The player's Move (a String)
     * @return - If the move resulted in a ship being sunk,
     *         a String along the lines of "You sunk my Battleship!",
     *         null otherwise.
     */
    public String makePlayerMove(String playersMove) {
        return computer.makePlayerMove(new Move(playersMove));
    }

    /**
     * Checks to see if the player has been defeated.
     * @return - Returns true if all player ships have 
     * been sunk, false otherwise
     */
    public boolean userDefeated()
    {
        return player.getFleet().gameOver();
    }

    /**
     * Checks to see if the computer has been defeated.
     * @return - Returns true if all computer ships have 
     * been sunk, false otherwise
     */
    public boolean computerDefeated()
    {
        return computer.getFleet().gameOver();
    }

    /**
     * Returns a String representation of a Game object
     * @return - The String representation of this Game
     */
    @Override
    public String toString()
    {
        return String.format("COMPUTER\n%s\n\nUSER\n%s",
        computer.toString(), player.toString());
    }

}
