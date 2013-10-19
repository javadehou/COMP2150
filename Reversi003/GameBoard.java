
/**
 * Write a description of class Grid here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.math.*;
public class GameBoard
{
    public Tile board[][];
    public int rows;
    public int col;
    
    public int xComb[] = {0, 0, -1, 1, 1, 1, -1, -1};   //an array that holds the traversal direction like a playstation d-pad
    public int yComb[] = {1, -1, 0, 0, -1, 1, -1, 1};
    
    public int xCompChoice[] = new int[10];             //an array of move combinations that the computer may choose
    public int yCompChoice[] = new int[10];             //an array of move combinations that the computer may choose
    
    //default if no size is specified
    GameBoard()
    {
        this(8, 8);
    }
    
    //overloaded constructor that allows user to specify number of col and rows
    GameBoard(int rows, int col)
    {
        this.rows = rows;
        this.col = col;
        
        board = new Tile[rows][col];
            
        for (int i = 0; i < board.length; i++)
        {
            
            for (int j = 0; j < board[rows - 1].length; j++)
            {
                board[i][j] = new Tile();
                
            }
            
        }
        
        // Generalization of starting pieces for a board of any 2n x 2n dimension tile size
        board[rows/2 - 1][col/2 - 1].setTile(-1);
        board[(rows/2)][col/2 - 1].setTile(1);
        
        board[rows/2 -  1][col/2].setTile(1);
        board[(rows/2)][col/2].setTile(-1);
        
    }
    
    //computer's moves available are stored in an array
    public int movesAvailable(int color)
    {
        int index = 0;
        for (int i = 0; i < board.length; i++)
        {
           
            for (int j = 0; j < board[i].length; j++)
            {
                if (validMoveTestAll(color, i, j) && index < 10)
                {
                    
                    xCompChoice[index] = j;
                    yCompChoice[index] = i;
                    index++;
                    
                }
                
            }
            
        }
        return index;
       
    }
    
    //the computer will make a random move based on the array 
    public void makeMoveRand(int color)
    {
        int count = movesAvailable(color);
        int sel;
        
        if (count > 0)          //the moves available needs to be > 0 for the conditional to execute
        {
            sel = (int)(Math.random() * count);
            makeMove(color, yCompChoice[sel], xCompChoice[sel]);
        }
        
    }
    
    //general method that returns true or false if any open moves exist
    public boolean openMoves(int color)
    {
        
        for (int i = 0; i < board.length; i++)
        {
           
            for (int j = 0; j < board[i].length; j++)
            {
                if (validMoveTestAll(color, i, j))
                {
                   return true;
                }
                
            }
            
        }
        return false;
    }
    
    //my overridden method of toString that will print the dimensions and grid labels
    public String toString()
    {
        //this section prints the top grid labels
        System.out.print(" " + "\t  ");
        for (int k = 0; k < col; k++)
        {
            System.out.print(k + 1 +"  \t  ");
        }
        System.out.println("\n\n");
        
        //this section prints the row grid numbers and the values at each coordinate
        for (int i = 0; i < board.length; i++)
        {
            System.out.print(i + 1 + "\t");
            for (int j = 0; j < board[rows - 1].length; j++)
            {
                System.out.print(board[i][j] +"\t");
            }
            
            System.out.println("\n\n");
        }
        return "";
        
    }
    
    //count total black if 1, count total white if -1
    public int countPiece(int tile)
    {
        
        int count = 0;
  
        for (int i = 0; i < board.length; i++)
        {
            
            for (int j = 0; j < board[i].length; j++)
            {
                if (board[i][j].getTile() == tile)
                {
                    count += 1;
                }
            }
            
        }
        
        return count;
             
    }
    
    public void setTile(int y, int x, int tile)
    {
        board[y][x].setTile(tile);
    }
    
    
    
    //the traversal takes a starting input y, and x,
    //and a direction to traverse which is b and a, along with the tile state
    public int traverse(int tile, int y, int x, int b, int a)
    {
        int captures = 0;
        //while you're not beyond the edge boundaries
        while ((y + b < board.length && x + a < board[rows - 1].length) && (y + b >= 0 && x + a >= 0))
        {
            
            if (captures > 0 && board[y + b][x + a].getTile() == tile)
            {
                return captures;
            }
            else if (board[y + b][x + a].getTile() == 0 || board[y + b][x + a].getTile() == tile)
            {
                return -1;
            }
            else
            {
                y += b;
                x += a;
                captures++;
            }
            
        }
        
        return -999;
        
    }
    
    //this method tests all valid move directions for a given coordinate
    public boolean validMoveTestAll(int color, int y, int x)
    {
        //can't be an out of bounds position
        if ((y  >= board.length || x  >= board[rows - 1].length) || (y < 0 || x  < 0))
        {
            return false;
        }
        //and can't be occupied by another piece
        else if (board[y][x].getTile() != 0)
        {
            return false;
        }
        
        int result;
        //traverse through the direction specified in the xComb/yComb arrays and return immediately upon the first discovery of a valid move
        
        for (int i = 0; i < 8; i++)
        {
            
            result = this.traverse(color, y, x, yComb[i],xComb[i]);
            if( result != -999 && result != -1)
            {
                
                return true;
            }
            
        }
        //if you traversed through all combinations and never got a valid move, then it's not a valid move, return false
        return false;
        
    }
    
    //try to make a valid move based on the x, y combination the user specifies
    public void makeMove(int color, int y, int x)
    {
        
        //first make sure it's a valid move
        if (validMoveTestAll(color, y, x))
        {
            int step;
            //traverse all directions, only using the ones that don't return -999 or -111
            for (int i = 0; i < 8; i++)
            {
                
                step = this.traverse(color, y, x, yComb[i],xComb[i]);
                
                if( step != -999 && step != -1)
                {
                   setTile(y, x, color);
                   doCapture(y, x, yComb[i], xComb[i], step);               //commit to a capture by calling the doCapture() method.
                }
                
            }
           
        }
        
    }
    
    //commit to a capture by calling the flip() method
    public void doCapture(int y, int x, int yComb, int xComb, int captures)
    {
        
        while (captures > 0)
        {
            this.board[y + yComb][x + xComb].flip();
            y += yComb;
            x += xComb;
            captures--;         //decrement the capture count after each flip until all captures are flipped
            
        }
    }
    
    
}
