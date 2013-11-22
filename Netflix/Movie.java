
/**
 * Write a description of class Movie here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Movie extends Media
{
    Movie(String title, String genre, int byear, int eyear, double rating, int runtime)
    {
        super.title = title;
        super.genre = genre;
        super.byear = byear;
        super.eyear = eyear;
        super.rating = rating;
        super.runtime = runtime;
    }
    
    public String toString()
    {
        return (title + "\n" + genre + "\n" + byear + "\n" + rating + "\n" + runtime + "\n\n");
        
    }
    
    public String getTitle()
    {
        return this.title;
    }
    
    public String getGenre()
    {
        return this.genre;
    }
    
    public int getBeginYear()
    {
        return this.byear;
    }
    
    public int getEndYear()
    {
        return this.eyear;
    }
    
    public double getRating()
    {
        return this.rating;
    }
    
    public int getRuntime()
    {
        return this.runtime;
    }
 
}
