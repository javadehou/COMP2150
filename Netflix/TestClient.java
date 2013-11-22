
/**
 * Write a description of class test here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.ArrayList;

public class TestClient
{
    //shows a menu of filters
    public static void showFilters(NetflixFileReader n)
    {
        int filterNum = 1;                //enumerates the filters
        System.out.println("\n\n---------CurrentFilters-----------");
        for(Filter filter: n.filterList)
        {
            System.out.println(filterNum + ") " + filter);
            filterNum++;
        }
        System.out.println();
        
    }
    
    
    //main method for the program
    public static void main(String args[]) throws Exception
    {
        
        NetflixFileReader netflix = new NetflixFileReader("netflix");
        Scanner input = new Scanner(System.in);
        boolean error = false;
        boolean exit = false;
        
        int choice = 0;
        int fieldChoice = 0;
        
        String selection;

        
        do
        {
                System.out.println("-----Commands-----\n\ne(exit the program)\n\nr(remove any filters)\n\na(add a filter)\n\nl(list movies)");
                selection = input.nextLine();
            do
            {
                try
                {
                    //selection can be 1 of 4 letters
                    if(selection.equals("a"))
                    {
                        //if the selection is invalid then the exception is thrown and loops
                        do
                        {
                            System.out.println("\nFilter by ....");
                            System.out.println("0: Title");
                            System.out.println("1: Genre");
                            System.out.println("2: Year");
                            System.out.println("3: Rating");
                            System.out.println("4: Runtime");
                            try
                            {
                                fieldChoice = input.nextInt();
                                error  = false;
                            }   
                            catch(InputMismatchException e)
                            {
                                System.out.println("\nInvalid input, please try again"); 
                                error = true;
                                input.nextLine();
                                
                            }
                        }
                        while(fieldChoice < 0 || fieldChoice > 5 || error == true);
                        
                        
                        //the filter is limited depending on what fieldChoice was chosen
                        switch(fieldChoice)
                        {
                            case 0: //deals with Title
                            {
                                System.out.println("Select a relation: \n 1: Equal to \n 2: Contains"); 
                                choice = input.nextInt();
                                selection = input.nextLine();
                                if (choice == 1)
                                {
                                    System.out.println("select target");
                                    netflix.addFilter(new Filter(0, 2, selection));
                                }
                                if (choice == 2)
                                {
                                    System.out.println("select target");
                                    selection = input.nextLine();
                                    netflix.addFilter(new Filter(0, 3, selection));
                                }
                               
                            }
                            break;        
                            
                            case 1: //deals with the Genre
                            {
                                System.out.println("Select a relation: \n 0: Movie \n 1: Series"); 
                                choice = input.nextInt();
                                
                                if (choice == 1)
                                {
                                    System.out.println("select target");
                                    netflix.addFilter(new Filter(1, 2, "Series"));
                                }
                                if (choice == 0)
                                {
                                    System.out.println("select target");
                                    netflix.addFilter(new Filter(1, 2, "Movie"));
                                }
                                
                            }
                            break;
                            
                            case 2: //deals with the year
                            {
                                System.out.println("Select a relation: \n 0: Less than \n 1: Greater than \n 2: Equal to"); 
                                choice = input.nextInt();
                                System.out.println("select target");
                                netflix.addFilter(new Filter(2, choice, input.nextInt()));
                            }
                            break;
                            
                            case 3: //deals with rating
                            {
                                System.out.println("Select a relation: \n 0: Less than \n 1: Greater than \n 2: Equal to"); 
                                choice = input.nextInt();
                                System.out.println("select target");
                                netflix.addFilter(new Filter(3, choice, input.nextDouble()));
                                
                            
                            }
                            break;
                            
                            case 4: //deals with runtime
                            {
                                System.out.println("Select a relation: \n 0: Less than \n 1: Greater than \n 2: Equal to"); 
                                choice = input.nextInt();
                                System.out.println("select target");
                                netflix.addFilter(new Filter(4, choice, input.nextInt()));                
                            
                            }
                            break;
                        }
                        showFilters(netflix);
                        error = false;
                       
                    }
                    
                    //the selection that allows the user to leave the program
                    if (selection.equals("e"))
                    {
                        exit = true;
                        error = false;
                    }
                    
                    //allows user to remove filter
                    if (selection.equals("r"))
                    {
                        showFilters(netflix);       //show the filter menu
                        
                        System.out.println("Choose filter to remove and press <ENTER>");
                        choice = input.nextInt();
                        
                        int i = 0;
                        for(Media medi: netflix.masterList)
                            {
                            
                                if(!(netflix.getFilter(choice - 1).readAndCompareMedia(medi)))
                                {
                                    
                                    netflix.setSiv(i, true);
                                    //netflix.currentList.remove(i);
                                    
                                }
                                
                                i++;
                            }
                        netflix.removeFilter(choice - 1);
                        error = false;
                        
                    }
                    
                    //this selection allows the user to see the list, the list depends on the filter
                    if (selection.equals("l"))
                    {
                        int i = 0;
                        
                        for(Filter filter: netflix.filterList)
                        {
                            i = 0;
                            for(Media medi: netflix.masterList)
                            {
                            
                                if(!filter.readAndCompareMedia(medi))
                                {
                                    
                                    netflix.setSiv(i, false);
                                    //netflix.currentList.add(medi);
                                    
                                }
                                
                                i++;
                            }
                            
                        }
                        
                        for(int j = 0; j < netflix.siv.length; j++)
                        {
                            if (netflix.siv[j])
                            {
                                System.out.println(netflix.getLine(j));
                            }
                        }
                        showFilters(netflix);
                        
                    }
                    
                }
               
                //error handling for default type error inputs
                catch(InputMismatchException e)
                {
                   System.out.println("\nInvalid input, please press try again"); 
                   input.nextLine();
                   error = true;
                   break;
                  
    
                   
               
                }
                catch(Exception e)//error handling for everything else
                {
                   
                   System.out.println("\nSomething else went wrong, please try again"); 
                   error = true;
                   
                   
                  
             
                }
            }
            while(error == true);
            
            
            
        }
        while(exit == false);
    
        
    }
    
}
