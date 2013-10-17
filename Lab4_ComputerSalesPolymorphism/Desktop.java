
/**
 * Write a description of class Desktop here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Desktop extends Computer
{
    // constructor with arguments
    Desktop(int ramGB, int hdGB, int otherSpec)
    {
        super(ramGB, hdGB, otherSpec);
        
    }
    
    //default constructor with no arguments
    Desktop()
    {
        this(8, 500, 4);
    }
    
    //Desktop's own implementation of getCost()
    public double getCost()
    {
        return 150 + (6.50 * ramGB) + (0.15 * hdGB) + (0.48 * otherSpec);
    }
    
    //Desktop's own implementation of the toString()
    public String toString()
    {
        return "Desktop Computer \n" + 
               "Ram in GB: \t" + this.ramGB + "\n" +
               "Hard drive: \t" + this.hdGB + "\n" +
               "Vram:\t\t" + this.otherSpec + "\n" +
               "Total Price: \t" + this.getCost();
    }
    
}
