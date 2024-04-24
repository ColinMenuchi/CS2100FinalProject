import java.util.Random;
import java.util.Scanner;
public class BattleshipDriver 
{
    public static void main(String[] args)
    {
        // Create Game
        Game game = new Game();

        // Decide who goes first
        boolean playersTurn, computersTurn;
        Random rand = new Random();
        int movesFirst = rand.nextInt(1);
        if (movesFirst == 0)
        {
            playersTurn = true;
            computersTurn = false;
        }
        else
        {
            playersTurn = false;
            computersTurn = true;
        }

        // Game Loop
        Scanner keyboard = new Scanner(System.in);
        String playersMove;
        while (!game.userDefeated() && !game.computerDefeated())
        {
            
            /*
             * If player turn, then get player's move
             * (parse it) and apply the move to the
             * computer's board.
             */
            if (playersTurn)
            {
                System.out.print("Enter a Move: ");
                playersMove = keyboard.nextLine();
                game.makePlayerMove(playersMove);
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
                game.makeComputerMove();
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
}
