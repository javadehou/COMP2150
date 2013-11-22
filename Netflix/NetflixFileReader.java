
/**
 * Write a description of class NetflixFileReader here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.util.ArrayList;
import java.io.*;
import java.util.Scanner;
import java.lang.String;

public class NetflixFileReader
{
    protected ArrayList<Media> masterList;      //holds all of the media objects
    protected ArrayList<Media> currentList;     //holds all of the filtered media objects
    protected ArrayList<Filter> filterList;     //holds all of my filters
    protected boolean siv[];                    //each filter makes the sieve more restrictive
    
    protected File inputFile;
    
    protected Scanner input;
    
    public int lineCount;
                                    //an overly safe list of non-movies
    protected String nonmovies[] = {"Season", "Episode", "Episodes", "Part", "parts", "Series", "Collection", "Seasons",
                                    "Vol", "volume", "Volumes", "volumes", "Chapters", "Chapter", "chapters", "Set", 
                                    "episodes", "special", "Special"};
    
    
    
    protected String lines[];               //an array of every netflix line (String) 
    protected String fileLocation;          //a String representation of the file location using its fully qualified name
    
    
   
   
    //default constructor for the NetFlixFileReader, requires a valid file location
    public NetflixFileReader(String fileLocation) throws IOException
    {
        this.inputFile= new File(fileLocation);

        if(!inputFile.exists())
        {
        
            System.out.println("File not found ");
            System.exit(0);
        
        }
        
        processLines();
        makeMasterList();
        currentList = new ArrayList<Media>();
        filterList = new ArrayList<Filter>();
        
    }
    

    //processes every line in a file and inserts each line into a String array
    public void processLines() throws IOException
    {
        this.lineCount = 0;
        
        this.input = new Scanner(inputFile);
        
        // count the number of lines needed to hold the data array
        while (input.hasNext())
        {
            lineCount++;
            input.nextLine();       //advance the pointer
        }
        this.input.close();
        
        this.lines = new String[this.lineCount];
        this.input = new Scanner(this.inputFile);
        siv = new boolean[getCount()];
        for (int j = 0;input.hasNext();j++)
        {
            this.lines[j] = input.nextLine();
            siv[j] = true;                      //make the intial siv completely unrestricted
        }
        this.input.close();
       
    }
    
    //makes the master list entries that hold the media objects
    public void makeMasterList()
    {
       
        masterList = new ArrayList<Media>();
        for(String line: lines)     //for each loop to iterate through the lines array
        {
            
            if(parseGenre(line).equals("Movie"))
            {
                masterList.add(new Movie(parseTitle(line), parseGenre(line), parseBeginYear(line), parseEndYear(line), parseRating(line), parseRuntime(line)));
            }
            else
            {
                masterList.add(new Series(parseTitle(line), parseGenre(line), parseBeginYear(line), parseEndYear(line), parseRating(line), parseRuntime(line), parseCollection(line)));
            }
          
            
        }
    }
    
    //return a line from the lines[] array at the given index
    public String getLine(int index)
    {
        return this.lines[index];
    }
    
    //return a media object from the masterList at a specified index
    public Media getMedia(int i)
    {
        return this.masterList.get(i);
        
    }
    
    public void addToCurrentList(Media m)
    {
        
        this.currentList.add(m);
    }
    
    //the siv allows each line in the list to be blocked or passed through
    public void setSiv(int i, boolean bool)
    {
        this.siv[i] = bool;
        
    }
    
    //add filter to filterList and apply it
    public void addFilter(Filter filter)
    {
        //filterList.add(new Filter(field, relation, target));
        this.filterList.add(filter);

    }
    
    public void removeFilter(int i)
    {
        this.filterList.remove(i);    
        
    }
    
    public Filter getFilter(int i)
    {
        return filterList.get(i);
        
    }
    
    
    
    //return either series or movie depending on the keywords in the string
    public String parseGenre(String s)
    {
        s = s.substring(s.indexOf("|"), s.length());
         for (String nonM : nonmovies)
         { 
        
            if (s.contains(nonM))
            {
                return "Series";
             
            }
            
        }
        return "Movie";
        
    }
    
    //prints the total number of lines in a file
    public int getCount()
    {
        
        return lineCount;
    }
    
    
    //get the name of the movie or series from the string passed
    public String parseTitle(String s)
    {
        int end = s.indexOf("("); //the first opening parenthese marks the end of a title
        
        return s.substring(0, end);
    }
    
    
    //get the collection name if it's a series
    public String parseCollection(String s)
    {
        //the pipe designates a good time to begin looking at runtimes
        s = s.substring(s.indexOf("|"), s.length());
        //the beginning of the first comma afterwards is a good place to trim the parse down to more specific area
        s = s.substring(s.indexOf(",") + 2, s.length());
        
        
        s.trim();
        return s;
            
        
        
    }
    
    //gets the necessary runtimes
    //if its a series then the runtime is unknown and set to 999
    public int parseRuntime(String s)
    {
        String min, hr;
        int h, m, runtime;
        h = 0;
        m = 0;
        runtime = 0;
        s = s.substring(s.indexOf("|") + 1);
        s = s.substring(s.indexOf(",") + 1);
        
        //empty or no length after comma are treated first
        if (s.length() < 2)
        {
            
            return runtime;
            
        }
        
        //if it's not a movie then it's probably something longer than a movie
        //return with 999
        for (String nonM : nonmovies)
        {
        
            if (s.contains(nonM) )
            {
                return 999;
            }
        }
        
        
        if(s.contains("hr") )
        {
            hr = s.substring(s.indexOf("h")- 2, s.indexOf("h"));
            hr = hr.trim();
            h = Integer.parseInt(hr);            
        }        
        
        if(s.contains("m"))
        {            
            min = s.substring(s.indexOf("m") - 2, s.indexOf("m"));
            min = min.trim();
            m = Integer.parseInt(min);            
        }
        
        
        h = h * 60;
        runtime = h + m;
        
        //System.out.print(" " + runtime);
        return runtime;         //finally return the total runtime (in minutes)
        
    }
    
    
    
    //get the rating from the provided string
    public double parseRating(String s)
    {
        double rating;
        s = s.substring(s.indexOf("|"), s.length());
        if (s.contains("stars"))
        {
            s = s.substring(s.indexOf("s") - 4, s.indexOf("s"));
            s = s.trim();
            rating = Double.parseDouble(s);
            return rating;
        }
        else
        {
            return 0;
        }
    }
    
    //gets the beginning year
    public int parseBeginYear(String s)
    {
        s = s.substring(0, s.indexOf("|") + 1);
        String begin;
              
        try
        {
            if (s.contains("-") && (s.charAt(s.indexOf("|") - 7) == '-'))
            {
                s = s.substring(s.indexOf("|") - 12, s.indexOf("|"));
                begin = s.substring(s.indexOf("(") + 1, s.indexOf("-"));
                begin.trim();
                return Integer.parseInt(begin);
                
            }
            else
            {
                s = s.substring(s.indexOf("|") - 6, s.indexOf("|") - 2);
                s.trim();
                return Integer.parseInt(s);
            }
        }
        catch (Exception e)
        {
          
            return 0;
        }
        
    }
    
    //gets the ending year
    public int parseEndYear(String s)
    {
        s = s.substring(0, s.indexOf("|") + 1);
        String end;
        
        try
        {
            if (s.contains("-") && (s.charAt(s.indexOf("|") - 7) == '-'))
            {
                s = s.substring(s.indexOf("|") - 12, s.indexOf("|"));
                end = s.substring(s.indexOf("-") + 1, s.indexOf(")"));
                end.trim();
            
                return Integer.parseInt(end);
                
            }
            else
            {
                s = s.substring(s.indexOf("|") - 6, s.indexOf("|") - 2);
                s.trim();
                
                return Integer.parseInt(s);
            }
        }
        catch (Exception e)
        {
            
            return 0;
        }
    }
    
        
        
}
