
/**
 * Write a description of class Comparable here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */


public interface Comparable
{
    //-1 if cost of calling computer is less than the called computer
    //0 if cost of calling computer is equal to called computer
    //1 if cost of calling computer is greater than the called computer
    int compareTo(Comparable c);
}
