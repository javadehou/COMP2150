
/**
 * Write a description of class testClient here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Laptop extends Computer
{
   //constructor that allows arguments to be passed upon instantiation
    Laptop(int ramGB, int hdGB, int otherSpec)
    {
        super(ramGB, hdGB, otherSpec);
        
    }
    
    //default values when instantiated
    Laptop()
    {
        this(4, 200, 15);
    }
    
    //my own implementation of getCost()...note otherSpec is used in Desktop as well
    public double getCost()
    {
        return 300 + (8.00 * ramGB) + (0.19 * hdGB) + (13.83 * otherSpec);
    }
    
    //Laptop's own implementation of toString()
    public String toString()
    {
        return "Laptop Computer \n" + 
               "Ram in GB: \t" + this.ramGB + "\n" +
               "Hard drive: \t" + this.hdGB + "\n" +
               "Screen Size:\t" + this.otherSpec + "\n" +
               "Total Price:\t" + this.getCost();
    }
}
