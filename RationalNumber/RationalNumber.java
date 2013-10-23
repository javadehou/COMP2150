
/**
 * James Atwood
 * 
 *  
 * Rational Number class
 */
public class RationalNumber
{
    
    public int num;
    public int den;
    
    //the constructor takes the integer values and will reduce to lowest terms
    RationalNumber(int num, int den)
    {
        
        int divisor;                //divisor will hold the greatest common divisor
        
        //this loop will determine if the numerator of denominator is the larger term
        //we want the smaller value to do the dividing
        if (den > num)
        {
            divisor = num;
        }
        else
        {
            divisor = den;
        }
        
        //for negative values we need to use the divisor in a different way, by incrementing
        if (divisor < 0)
        {
           while (((num % divisor != 0) || (den % divisor != 0))) //if both num AND den are divisible by the divisor, stop incrementing
            {
                divisor++;
            }
        
            this.num = -1 * (num / divisor);
            this.den = -1 * (den / divisor); 
	
        }
        
        //for positive values we need to use the divisor by decrementing
        if (divisor > 0)
        {
            
            while (((num % divisor != 0) || (den % divisor != 0)))  //if both num AND den{ } are divisible by the divisor, stop incrementing
            {
                divisor--;
            }
        
            this.num = (num / divisor);
            this.den = (den / divisor);
        
        }        
        
    }
    
    //prints the value of the num and den in rational form
    public String toString()
    {
        return this.num + "/" + this.den;
    }
    
    //simply returns the numerator
    public int getNum()
    {
        return this.num;
    }
    
    //simply returns the denominator
    public int getDen()
    {
        return this.den;
    }
    
    //perform subtraction on calling object and the argument object
	public RationalNumber subtract(RationalNumber r)
	{
		int p, q;
		RationalNumber result;						            //first create new object
		p = this.num * r.getDen() - this.den * r.getNum();		//perform subtraction on num 
		q = this.den * r.getDen();		                        //perform subtraction on den
		result  = new RationalNumber(p, q);								

		
		return result;
	}
	
	//performs addition and returns a new object
	public RationalNumber add(RationalNumber r)
	{
	    int p, q;//
		RationalNumber result; 						             //create new object
		p = (this.num * r.getDen()) + (this.den * r.getNum());	 //make a common factor by cross-multiplying	
		q = this.den * r.getDen();								 //multiply both denominators		
		
		result = new RationalNumber(p, q);                        //pass those values as a new object
		return result;
	}
	
	//performs multiplication and returns a new object
	public RationalNumber multiply(RationalNumber r)
	{
	    int p, q;
		RationalNumber result;
		p = this.num * r.getNum();                             //simply multiplies the two numerators
		q = this.den * r.getDen();                             //simply multiplies the two denominators
		result = new RationalNumber(p, q);                       
		return result;

	}
	
	//performs division on the two rational numbers and returns a new object
	public RationalNumber divide(RationalNumber r)
	{
	    int p, q;
		RationalNumber result;                                
		q = this.den * r.getNum();                                //dividing is the same as multiplying by reciprocal
		p = this.num * r.getDen();                                //multiply by the reciprocal
		result  = new RationalNumber(p, q);
		return result;

	}
	
	//performs an operation that returns the additive inverse of the calling object
	public RationalNumber addInverse()
	{
	    int p, q;
		RationalNumber result;
		p = this.getNum() * (-1);                                 //make the additive inverse
		q = this.getDen();
		result = new RationalNumber(p, q);                        //return the new object
		return result;
	}
	
	//performs the multiplicative inverse of the calling object (a new object is returned)
	public RationalNumber multInverse()
	{
	    int p, q;
		RationalNumber result;
		p = this.getDen();                                //set the numerator as the denominator
		q = this.getNum();                                //set the denominator as the numerator
		result = new RationalNumber(p, q);
		return result;

	}
	
	//cast the numerator and denominator values as double and return it as a decimal
	public double asDecimal()
	{
		double num = this.getNum();
		double den = this.getDen();
		return num / den;
	}
}
