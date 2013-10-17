
/**
 * Write a description of class TestClient here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.*;

public class TestClient
{
    //in order to see all results you may need to comment-out some of the battles.
    //{"earth", "water", "air", "fire"};
    public static void main(String[] args)
    {
        /**************************First Battle test*******************************/
        
        //1st Elemental creature "air"
        ElementalCuteCreature falco = new ElementalCuteCreature("Falco", "Falcon", 200, 10, 0, 2);
        
        //2nd Elemental Creature, "earth" by default if no constructor arguments are passed
        ElementalCuteCreature rabbit = new ElementalCuteCreature();
       
        
        //Prints their stats before the battle
        System.out.println(falco);
        System.out.println(rabbit);
        System.out.println();
        
        //battle
        //elemental creature vs elemental creature
        System.out.println("Battle!");
        rabbit.attack(falco);
        
        //Prints their stats after the battle
        System.out.println(falco);//the rabbit should have done approximately 1/2 damage on the air creature (using integer division)
        System.out.println(rabbit);
        System.out.println();
        
        
        /**************************Second Battle test*******************************/
        
        //3rd creature (non-elemental)
        CuteCreature dog = new CuteCreature("Fido", "dog", 10, 10, 250);
        
        //4th creature (elemental)
        ElementalCuteCreature firefox = new ElementalCuteCreature("Flame", "fox", 300, 30, 0, 3);
        
        //Prints their stats before the battle
        System.out.println(dog);
        System.out.println(firefox);
        System.out.println();
        
        //battle
        //elemental creature vs non-elemental creature
        System.out.println("Battle!");
        firefox.attack(dog);
        
        //Prints their stats after the battle
        System.out.println(dog);//dog should have died
        System.out.println(firefox);//killing the dog gave firefox 259 xp and leveled him up
        System.out.println();
        
        /**************************Third Battle test*******************************/
        
        //5th elemenal creature
        ElementalCuteCreature f1 = new ElementalCuteCreature("fire", "snake", 743, 29, 4, 3);
        
        //6th elemental creature
        ElementalCuteCreature e1 = new ElementalCuteCreature("earth", "badger", 330, 10, 8, 0);
        
        //print stats prior to battle
        System.out.println(f1);
        System.out.println(e1);
        
        //print battle and attack
        System.out.println("Battle!");
        e1.attack(f1);
        
        //print stats after battle
        System.out.println(f1);
        System.out.println(e1);//an elemental fire creature has no bonus damage on an elemental earth creature, as expected
        
        /**************************Fourth Battle test*******************************/
        //7th creature
        ElementalCuteCreature w1 = new ElementalCuteCreature("water", "whale", 200, 10, 30, 1);
        
        System.out.println(w1);
        //fire attacking air
        firefox.attack(falco);
        
        //fire attacking water
        firefox.attack(w1);
        
        
        System.out.println(falco); //should inflict double
        System.out.println(w1);     //should inflict half

        /**************************Fifth Battle test*******************************/
        //8th creature
        ElementalCuteCreature w2 = new ElementalCuteCreature("whaley", "water", 200, 10, 20, 1);
        
        //9th creature
        ElementalCuteCreature w3 = new ElementalCuteCreature("whaley's brother", "water", 200, 10, 20 , 1);
        
        System.out.println(w2);
        System.out.println(w3);
        
        w2.attack(w3);//two elemental creatures of the same type shouldn't be able to battle each other
            
            


        
        
        
        
       
        
        
       

    }
}
