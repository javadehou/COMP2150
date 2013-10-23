
/**
 * Write a description of class CuteCreature here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class CuteCreature
{
    // instance variables, these will be extended by the elemental cute creature
    public String name;
    public String species;
    public int level;
    public int currentHP;
    public int maxHP;
    public int attackDamage;
    public int experiencePoints;
    public int experienceValue;
    public int lvlupNext;
    public double k;             //used to hold the psuedo-random number 
   
    //This is the constructor that takes arguments, the defaults are specified below
    CuteCreature(String name, String species, int maxHP, int attackDamage, int experienceValue)
    {
       //unchangeable defaults
       this.experiencePoints = 0;
       this.level = 1;
       this.currentHP = maxHP;
       this.lvlupNext = 250;                //this is an extra variable to determine how much experience is needed to levelup again, not needed but useful
       
       
       //defaults specified through the argument parameters
       this.name = name;
       this.species = species;
       this.maxHP = maxHP;
       this.attackDamage = attackDamage;
       this.experienceValue = experienceValue;
        
       
    }
   
    //an overloaded constructor used when no arguments are passed.
    CuteCreature()
    {
       this("Radd", "Rabbit", 100, 13, 10); //the default creature you get is a rabbit
    }
   
    //the take damage method (negatives may occur which means the creature is dead)
    public void takeDamage(int dmg)
    {
       if (currentHP > 0)
       {
           currentHP = currentHP - dmg;
       }
    }   
    
    //sets all the values necessary when leveling up
    private void levelUp()
    {
        this.level += 1;
        this.currentHP += 4;
        this.maxHP += 4;
        this.attackDamage += 2;
        this.experienceValue += 10;
    }
    
    //gain experience only happens under certain attack conditions and therefore depends on the attack method
    public void gainExp(int exp)
    {
        this.experiencePoints += exp;
        
        if ((this.experiencePoints / this.lvlupNext) > 0)//if you actually meet the minimum experience level, you levelup
        {
            levelUp();
            this.experiencePoints -= this.lvlupNext;//obviously you can't keep those level points forever, so after levelup you have that amount deducted.
                                                    //it could also have been set to zero, but since the lab didn't specify, I just deducted
        }
    }
    
    //the attack method is complex and implements a 75 percent chance of attacking by using the psuedo-random number generator
    public void attack(CuteCreature c)
    {
        
        
        k = Math.random();
        if (k <= .75)                         //75 percent chance of attacking
        {
            c.takeDamage(this.attackDamage);
    
            if (c.currentHP <= 0)           //if you kill someone, you gain experience with the amount gained from the fallen
            {
                
                gainExp(c.experienceValue);
            }
            System.out.println(this.name + ": attack " + c.name);
        }
        else
        {
            System.out.println(k + ": miss");                   //you might get unlucky and miss though
            
        }
    
        

    }   
    
    //this should be used to print stats
    public String toString()
    {
        String s;
        s = new String(
                        "---------------------------------" + "\n" +
                        "Level: " + this.level + "\n" +
                        "Name: " + this.name + "\n" +
                        "Species: " + this.species  + "\n" +                        
                        "HP: " + this.currentHP + "/" + this.maxHP + "\n" +
                        "Attack Dmg: " + this.attackDamage + "\n" +
                        "XP: " + this.experiencePoints + "/" + this.lvlupNext + "\n" +
                        "XP Value: " + this.experienceValue);                       
        
        return s;
    }
}
