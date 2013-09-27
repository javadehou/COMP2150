
/**
 * Write a description of class ClientClass here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ClientClass
{
    public static void main(String[] args)
    {
         RationalNumber r1 = new RationalNumber( 1, 2 );
         RationalNumber r2 = new RationalNumber( 7, 6 );
         
         System.out.println(r1.subtract(r2));
         System.out.println(r1.subtract(r2).asDecimal());
         System.out.println(r1.add(r2));
         System.out.println(r1.addInverse());
         System.out.println(r1.multInverse());
         System.out.println(r1.multiply(r2));
         System.out.println(r1.divide(r2));
         System.out.println(r1);
         
        //Should produce the following outputs
        //  -2/3
        //  -0.6666666666666666
        //  5/3
        // -1/2
        //  2/1
        //  7/12
        //  3/7
        //  1/2

         
         
    }
}
