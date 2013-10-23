
/**
 * Write a description of class Computer here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Computer implements Comparable
{
    //instance variables for ALL computer objects
    protected int ramGB;
    protected int hdGB;
    protected int otherSpec;
    protected double cost;
    
    protected String type;// (optional) the type of computer doesn't have to be specified
    
    //the default constructor for abstract computer types
    Computer(int ramGB, int hdGB, int otherSpec)
    {
        this.ramGB = ramGB;
        this.hdGB = hdGB;
        this.otherSpec = otherSpec;
        cost = getCost();
    }
    
    //abstract method must be implemented by extended objects
    public abstract double getCost();
    
    //compare the computer objects based on their cost
    public int compareTo(Comparable c)
    {
        Computer a = (Computer)c;
        
        if (cost < a.getCost())
        {
            return -1;
        }
        else if ( cost == a.getCost())
        {
            return 0;
        }
        else
        {
            return 1;
        }
    }
}
