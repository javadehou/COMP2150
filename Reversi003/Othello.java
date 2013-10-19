
/**
 * Write a description of class TestClient here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;
public class Othello
{
    static GameBoard g1 = new GameBoard(8, 8);      //default size is 8x8 but any size can be specified here
    static Scanner keyboard = new Scanner(System.in);
    static int rows;
    static int col;
    static int color = 1;       //by default black goes first
    static boolean gameMode;    //true = 2 player, false = 1 player
    
    
    
    //the game flow for 2-player mode
    public static void gameFlow()
    {
        System.out.println(g1);         //first print the game board
        
        //choose whose turn it is based on the color variable
        if (color == 1)
        {
            System.out.println("black's turn");
        }
        else if (color == -1)
        {
            System.out.println("white's turn");
        }
        System.out.println("Enter a Row number");
        rows = keyboard.nextInt() - 1;
        System.out.println("Enter a column number");
        col  = keyboard.nextInt() - 1;
        
        if (!g1.validMoveTestAll(color, rows, col))             //if the selected row/col isn't valid print the message
        {
           System.out.println("not a valid move");
           
        }
        else
        {
           System.out.println("valid move");
           g1.makeMove(color, rows, col);
           
           color = color * -1;                                  //if it's a valid move, then after making move, swap turns
           
        }
        
    }
    
    //the game flow for computer vs you mode
    public static void gameFlowComp()
    {
        System.out.println(g1);                     //print the gameboard
        
        if (color == 1)
        {
            System.out.println("black's turn");
            System.out.println("Enter a Row number");
            rows = keyboard.nextInt() - 1;
            System.out.println("Enter a column number");
            col  = keyboard.nextInt() - 1;
            
            if (!g1.validMoveTestAll(color, rows, col))             //similar to code above
            {
               System.out.println("not a valid move");
               
            }
            else
            {
               System.out.println("valid move");
               g1.makeMove(color, rows, col);
               
               color = color * -1;
               
            }
           
        }
        else if (color == -1)
        {
            System.out.println("white's turn");                     //the computer will be white by default and uses a different method for captures
            g1.makeMoveRand(-1);                                    //the computer isn't intelligent and simply selects randomly from available moves
            color = color * -1;
        }
           
        
        
    }
    
    
    //the main method holds the actual gameplay
    public static void main(String args[])
    {
             
        System.out.println("########  ######## ##     ## ######## ########   ######  #### \n" +
                           "##     ## ##       ##     ## ##       ##     ## ##    ##  ##  \n" +
                           "##     ## ##       ##     ## ##       ##     ## ##        ##  \n" +
                           "########  ######   ##     ## ######   ########   ######   ##  \n" +
                           "##   ##   ##        ##   ##  ##       ##   ##         ##  ##  \n" +
                           "##    ##  ##         ## ##   ##       ##    ##  ##    ##  ##  \n" +
                           "##     ## ########    ###    ######## ##     ##  ######  #### \n" );
                           
                           
        System.out.println("--------------------------------------------------------------");
        System.out.println("                    Press Enter to Play                       ");
        keyboard.nextLine();
        
        
        System.out.print('\f');
        
        
       
        System.out.println("For 2-Player type true, for single player type false");
          
        gameMode = keyboard.nextBoolean();          //this gives the user the option of playing against the computer or with a human
        
        
        do
        {
           
            if (!g1.openMoves(color) && g1.openMoves(color * -1))
            {
                switch (color)
                {
                    case 1:
                    {
                       System.out.println("Black forfeits a move");
                    }
                    break;
                    
                    case -1:
                    {
                       System.out.println("White forfeits a move");
                    }
                    break;
                }
                color *= -1;
                
            }
            
            //your gameflow (handling) will be different depending on which mode was selected
            if (gameMode)
            {
                gameFlow();                 //2-player mode 
            }
            else
            {
                gameFlowComp();             //single player mode
            }
           
        }
        while(g1.openMoves(-1) || g1.openMoves(1));                 //while at least one of the players has an open move left
        

        System.out.println(g1);                                     //at the game completion print the gameboard again
            
        if (g1.countPiece(1) > g1.countPiece(-1))                   //count pieces and compare
        {
            System.out.println("Black Wins");
        }
        else if (g1.countPiece(1) < g1.countPiece(-1))
        {
            System.out.println("White Wins");                       //print the appropriate message for whoever wins
        }
        else
        {
            System.out.println("Tie");                              //if its a tie print this msg
        }
        System.out.println("Black: " + g1.countPiece(1) + "\nWhite: " + g1.countPiece(-1));     //print the final score as well
        
    }
}
