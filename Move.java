public class Move
{
    // Instance Variables
    private int row;
    private int col;

    /**
     * Creates a Move object from two integers representing
     * the indicies in a two-dimensional array.
     * @param row - The row in the array
     * @param column - The column in the array
     */
    public Move(int row, int column)
    {
        this.row = row;
        this.col = column;
    }

    /**
     * Creates a move object from a String consisting
     * of a letter and a number
     * @param tile - A string containing a letter an a number
     */
    public Move(String tile)
    {
        char character = tile.charAt(0);
        this.row = character - 'A';
        this.col = Integer.parseInt(tile.substring(1));
    }

    /**
     * Accessor for this Move's row.
     * @return - The row of this Move
     */
    public int row()
    {
        return this.row;
    }

    /**
     * Accessor for this Move's column.
     * @return - This Move's column.
     */
    public int col()
    {
        return this.col;
    }

    /**
     * Returns a 2 to 3-character string consisting of
     * a letter in the range A-J followed by a number
     * in the range 1-10. Provides for ease of display
     * of Move values in an interface.
     * @return - The string representation of this Move.
     */
    @Override
    public String toString()
    {
        return "" + (char)(this.row + 'A') + this.col;
    }
}