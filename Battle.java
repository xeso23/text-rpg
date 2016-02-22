package dandd;
import java.util.Random;

public class Battle {

    private double damage = 0;
    private Random rand = new Random();

    public Battle() {

    }

    //used when a player is doing physical damage to a boss
    //arbitrary damage calculation, doing %s based on 100 gave too small of numbers to be fun.
    public int phyAttackChar(int c, int b) {
        int crit = rand.nextInt(10);
         damage = ((double) c * ((double) b / 50));
         
        if(crit == 9){
        return (int) (damage * 2);
        }else{
            return (int) damage;
        }
    }

    //used when a boss is doing physical damage to a player
    public int phyAttackBoss(int c, int b) {

        damage = ((double) b * ((double) c / 50));

        return (int) damage;
    }

    //used when a player is doing magic damage to a boss
    public int magAttackChar(int c, int b) {
        int crit = rand.nextInt(10);
         damage = ((double) c * ((double) b / 50));
         
        if(crit == 9){
        return (int) (damage * 2);
        }else{
            return (int) damage;
        }
    
    }

    //used when a boss is doing magic damage to a player
    public int magAttackBoss(int c, int b) {
         damage =  ((double) b * ((double)c/50));
         
        return (int) damage;
    }

}
