import java.util.Random;
import java.util.Scanner;
public class BattleshipDriver 
{
    public static void main(String[] args)
    {
        // Create Game
        Game game = new Game();

        // Display a welcome message and game boards
        System.out.println("Welcome to Battleship!\n\n");
        System.out.println(game);

        // Decide who goes first
        boolean playersTurn, computersTurn;
        Random rand = new Random();
        int movesFirst = rand.nextInt(2);
        if (movesFirst == 0)
        {
            playersTurn = true;
            computersTurn = false;
            System.out.println("You won the coin toss and get to go first.");
        }
        else
        {
            playersTurn = false;
            computersTurn = true;
            System.out.println("The computer won the coin toss and gets to go first.");
        }

        // Game Loop
        Scanner keyboard = new Scanner(System.in);
        String playersMove;
        String[] computersMove;
        while (!game.userDefeated() && !game.computerDefeated())
        {
            
            /*
             * If player turn, then get player's move
             * (parse it) and apply the move to the
             * computer's board.
             */
            if (playersTurn)
            {
                System.out.print("Your Turn: ");
                // Check to see if the user entered a valid move
                do
                {
                    playersMove = keyboard.nextLine();
                } while (invalidUserInput(playersMove));

                // Display a message if a ship sank.
                String moveResult = game.makePlayerMove(playersMove);
                if (moveResult != null)
                {
                    System.out.println(moveResult + "\n");
                }
                playersTurn = false;
                computersTurn = true;
            }
            /*
             * Otherwise, it's the computer's turn.
             * generate a move and apply it to the
             * player's board.
             */
            else if (computersTurn)
            {
                System.out.println("Computer's turn. Press any key to continue. ");
                keyboard.nextLine();
                computersMove = game.makeComputerMove();
                System.out.println("Computer Chose : " + computersMove[0] + "\n");
                if (computersMove[1] != null)
                {
                    System.out.println(computersMove[1]);
                }
                computersTurn = false;
                playersTurn = true;
            }
            // Update the boards and display them
            System.out.println(game);

        }
        keyboard.close();

        // Display "game over" and a message
        // indicating who won.
        System.out.println("Game Over");
        if (game.computerDefeated())
        {
            System.out.println("USER WINS!");
        }
        else
        {
            System.out.println("COMPUTER WINS!");
        }
    }
    /**
     * Takes in a String that the user inputed and checks if
     * it is a valid move for the game Battleship.
     * @param input - A string entered by the user
     * @return - True if the input is an invalid Move,
     * false otherwise.
     */
    public static boolean invalidUserInput(String input)
    {
        if (input.length() > 0 && input.length() <= 3)
            {
                char row = input.charAt(0);
                int col = 0;
                try {
                    col = Integer.parseInt(input.substring(1));
                    // If the specified row or column is invalid
                    if ((row != 'A' && row != 'B' && row != 'C' &&
                    row != 'D' && row != 'E' && row != 'F' &&
                    row != 'G' && row != 'H' && row != 'I' &&
                    row != 'J') || col < 1  || col > 10)
                    {
                        System.out.print("Invalid move, try again: ");
                    }
                    else
                    {
                        return false;
                    }
                }
                // If the column could not be parsed
                catch (NumberFormatException e)
                {
                    System.out.print("Invalid move, try again: ");
                }
            }
        else
        {
            System.out.print("Invalid move, try again: ");
        }
        return true;
    }
}
