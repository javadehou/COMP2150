
/**
 * Write a description of class Media here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public abstract class Media<E>
{
    protected String title;
    protected String genre;
    protected int byear;
    protected int eyear;
    protected double rating;
    protected int runtime;
    
    Media(){}
    
    public abstract String getTitle();
    
    public abstract String getGenre();
    
    public abstract int getBeginYear();
    
    public abstract int getEndYear();
    
    public abstract double getRating();
    
    public abstract int getRuntime();
    
   
    
    
    
    
    
}
