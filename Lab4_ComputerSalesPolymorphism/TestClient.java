
/**
 * Write a description of class TestClient here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class TestClient
{
    
    //this is a modified insertion method that will use comparable
    public  static void insert(Comparable x[], int i)
    {
        Comparable temp = x[i];
        int j = i - 1;
        
        while (j >= 0 && temp.compareTo(x[j]) < 0)
        {
            x[j + 1] = x[j];
            j--;
        }
        x[j + 1] = temp;
        
        
    }
    
    //a modified version of insertion sort that takes comparable objects
    public static void insertionSort(Comparable x[])
    {
        for (int i = 0; i < x.length; i++)
        {
            insert(x, i);
        }
        
    }
    
    //main method for testing
    public static void main(String args[])
    {
        
        //make a new array of computer objects
        Computer comp[] = new Computer[5];
        
        comp[0] = new Laptop();
        comp[1] = new Desktop();
        comp[2] = new Laptop(3, 750, 17);
        comp[3] = new Desktop(6, 1000, 4);
        comp[4] = new Desktop(2, 100, 1);
        
        //print the unsorted list of computers( 2 laptops and 3 desktops)
        for (int i = 0; i < comp.length; i++)
        {
            
            System.out.print(comp[i] + "\n\n");
        }
        
        //do the sorting (based on the price of each computer)
        insertionSort(comp);
        System.out.println("----------------[Sorted]---------------------");
        
        //print the sorted list of computers
        for (int j = 0; j < comp.length; j++)
        {
            System.out.print(comp[j] + "\n\n");
            
        }
        
        
    }
}
