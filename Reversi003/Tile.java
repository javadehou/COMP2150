
/**
 * Write a description of class TIle here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Tile
{
    public int tileState;        //1 = black, 0 = blank, -1 = white
   
   
    //default pieces are empty
    Tile()
    {
       tileState = 0;
              
    }
    
    //specified pieces are not empty
    Tile(int i)
    {
      tileState = i;
    }
    
    
    public void flip()
    {
       tileState = tileState * -1;
       
    }
    
    public void setTile(int tile)
    {
       this.tileState = tile;
    }
    
    public int getTile()
    {
       return tileState;
       
    }
    public String toString()
    {
        if (tileState == 0)
        {
           return "|___|";
        }       
        else if(tileState == 1)
        {
            return "| B |";
        }
        else
        {
           return "| W |";
        }
    }
}
