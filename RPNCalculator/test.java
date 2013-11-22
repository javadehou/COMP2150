
/**
 * Write a description of class test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class test
{
    public static void main(String args[]) throws Exception
    {
        InfixPostfixConverter c1 = new InfixPostfixConverter();
        String infix = "8 * ( 9 - 2 )";
        
        
        System.out.println(c1.convert(infix));
        
        
    }
}
