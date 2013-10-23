
/**
 * Write a description of class ElementalCuteCreature here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class ElementalCuteCreature extends CuteCreature
{
    
    public String attunement[] = {"earth", "water", "air", "fire"};
    public int attuneSelection;
    
    //the same type of information is passed to this constuctor as in the parent constructor with an added twist of having an attunement
    ElementalCuteCreature(String n, String s, int m, int a, int e, int attune)
    {
        super(n, s, m, a, e);           //all of the instance variables from the parent constructor applied here
        this.attuneSelection = attune;
    }
    
    //if you don't specify any attributes, you just get an elemental rabbit
    ElementalCuteCreature()
    {
        this("Radd", "Rabbit", 100, 13, 10, 0);          //all of the instance variables from the parent constructor applied here
        
    }
    
    //provides the calling objects attunement
    public int getAttunement()
    {
        return this.attuneSelection;
    }
    
     
    
    //this is the take damage method that has special rules for two elemental creatures
    //explanations are below.
    public void takeDamage(int attuneSel, int dmg)
    {
        
        //the type of creature receiving damage
        switch (this.getAttunement())
        {
            /*************** if earth*************/
            case 0:
            {
                
                switch(attuneSel)
                {
                    case 1: // water creatures deal this much damage
                    {
                        super.takeDamage(dmg/2);
                    }
                    break;
                
                    case 2: //air creatures deal this much damage
                    {
                        super.takeDamage(dmg);
                        super.takeDamage(dmg);
                    }
                    break;
                    
                    default://else just deal the default amount
                    super.takeDamage(dmg);
                }
                
            }
            break;
            
            /*************if water***************/
            case 1:
            {
                
                //the type of creature dealing damage
                switch(attuneSel)
                {
                    case 0: // earth creatures deal double
                    {
                        super.takeDamage(dmg);
                        super.takeDamage(dmg);
                    }
                    break;
                
                    case 3: //air creatures deal half
                    {
                        super.takeDamage(dmg/2);
                    }
                    break;
                    
                    default://else just deal the default amount
                    super.takeDamage(dmg);
                
                
                }
                
            }
            break;
            
            case 2:/*************if air***************/
            {
                
                switch(attuneSel)
                {
                    case 0: // earth creatures deal this much damage
                    {
                        super.takeDamage(dmg/2);
                    }
                    break;
                
                    case 3: //fire creatures deal this much damage
                    {
                        super.takeDamage(dmg);
                        super.takeDamage(dmg);
                    }
                    break;
                    
                    default://else just deal the default amount
                    super.takeDamage(dmg);
                }
                
            }
            break;
            
            case 3:/*************if fire*****************/
            {
                switch(attuneSel)
                {
                    case 1: // water creatures deal this much damage
                    {
                        super.takeDamage(dmg);
                        super.takeDamage(dmg);
                    }
                    break;
                
                    case 2: //air creatures deal this much damage
                    {
                        super.takeDamage(dmg/2);                        
                    }
                    break;
                    
                    default://else just deal the default amount
                    super.takeDamage(dmg);
                }
            }
            break;
                       
        }
    }
    
   
    //the attack method is complex and implements a 75 percent chance of attacking by using the psuedo-random number generator
    public void attack(ElementalCuteCreature c)
    {
        
        int a = this.getAttunement();
        int b = c.getAttunement();
        
        //the attack must check that the elemental creatures first are not of the same type
        if (a == b)
            {
                System.out.println("Can't attack");
                return;
            }
        
        //implements a similar fighting algorithm from the parent class except that there are conditions and rules depending on the type of creature
        //the type of creature will be passed to the overloaded and overidden attack method
       
        k = Math.random();
        if (k <= .75)
        {
            
            
            if (a != b)
            {
                c.takeDamage(a, this.attackDamage);  //pass the elemental value and also his attack damage
                
                if (c.currentHP <= 0)
                {
                    gainExp(c.experienceValue);
                }
                System.out.println(this.name + ": attack " + c.name);
            }
            else
            {
                super.attack(c);                    //if it's not an elemental creature fighting an elemental creature, then simply do normal damage
            }
        }
        else
        {
            System.out.println(k + ": miss");
            
        }
                
                
    }
    
    //this acually uses the parent or super class version of toString but with the added information of what "element" a creature is
    public String toString()
    {
        
        return super.toString() + "\n" + this.attunement[attuneSelection]; 
    }
    }
    

