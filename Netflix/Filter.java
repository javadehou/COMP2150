
/**
 * Write a description of class RunningTime here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.InputMismatchException;

public class Filter 
{
    protected String relations[] = {"less than", "greater than", "equal to", "contains"};
    protected String fields[] = {"Title", "Genre", "Year", "Rating", "Runtime"};
    
    protected int field;
    protected int relation;
    
    protected int intTarget;
    protected double doubleTarget;
    protected String stringTarget;
    protected char charTarget;
    
    protected String title;
    protected String genre;
    protected int byear;
    protected int eyear;
    protected double rating;
    protected int runtime;
    
   
    //overloaded constructors to handle various targets that may be passed
    Filter(int field, int relation, int target) 
    {
        this.field = field;
        this.relation = relation;
        this.intTarget = target;
    }
    
    Filter(int field, int relation, double target)
    {
        this.field = field;
        this.relation = relation;
        this.doubleTarget = target;
    }
    
    Filter(int field, int relation, String target)
    { 
        this.field = field;
        this.relation = relation;
        this.stringTarget = target;       
    }
    
    Filter(int field, int relation, char target)
    {      
        this.field = field;
        this.relation = relation;
        this.charTarget = target;      
    }
    
     //deals with various fields and makes use of relation operators
    public boolean readAndCompareMedia(Media media)
    {
        this.title = media.getTitle().trim();
        this.genre = media.getGenre().trim();
        this.byear = media.getBeginYear();
        this.eyear = media.getEndYear();
        this.rating = media.getRating();
        this.runtime = media.getRuntime();
        
        switch(this.field)
        {
            case 0:  //deal with title (String) 
            {
                
                switch(this.relation)
                {
                                       
                    //return all titles that equals target
                    case 2:{return title.equals(this.stringTarget);}
                    
                    //return all titles containing the target
                    case 3:{return title.contains(this.stringTarget);}
                    
                }
               
            }

            case 1:  //deal with genre (String)
            {
                switch(this.relation)
                {
                    case 2:{return genre.equals(this.stringTarget);}
                }
            }

            case 2: //deal with year (int)
            {
                switch(this.relation)
                {
                    //return all years less than target
                    case 0:{return (byear < this.intTarget || eyear < this.intTarget);}
                    
                    //return all 
                    case 1:{return (byear > this.intTarget || eyear > this.intTarget);}
                    
                    //return all 
                    case 2:{return (byear == this.intTarget || eyear == this.intTarget);}
                    
                }
               
            }
            
            case 3: //deal with rating (double)
            {
                switch(this.relation)
                {
                    //return all ratings less than target
                    case 0:{return rating < this.doubleTarget;}
                    
                    //return all ratings greater than target
                    case 1:{return rating > this.doubleTarget;}
                    
                    //return all ratings that equals target
                    case 2:{return rating == this.doubleTarget;}
                    
                }
                
            }
                
            case 4: //deal with runtime (int)
            {
                switch(this.relation)
                {
                    //return all runtimes less than target
                    case 0:{return runtime < this.intTarget;}
                    
                    //return all runtimes greater than target
                    case 1:{return runtime > this.intTarget;}
                    
                    //return all runtimes that equals target
                    case 2:{return runtime == this.intTarget;}
                    
                }
            }

            default: return false;
        
        }

    }
    
    
    //basic printed output for the filters
    public String toString()
    {
        if(this.field == 0 || this.field == 1)
        { 
            return "Filter: " + this.fields[field] + "|" + this.relations[relation] + "|" +  stringTarget;
        }
        
        else if(this.field == 4 || this.field == 2)
        { 
            return "Filter: " + this.fields[field] + "|" + this.relations[relation] + "|" + intTarget;
        }
            
        else if(this.field == 3)
        { 
            return "Filter: " + this.fields[field] + "|" + this.relations[relation] + "|" + doubleTarget;
        }
       
        else return null;
        
        
    }
    

}