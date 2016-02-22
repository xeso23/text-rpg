package dandd;

public class Boss {
    //Boss stat and descriptive variables
    private int HP;
    private int ATK;
    private int DEF;
    private int MagicDef;
    private int Intelligence;
    private String Name;
    private String Description;
    
    
    //Boss constructor takes in values set in main program for stats, name, description
   public Boss(int h, int at, int df, int mdf, int iq, String nm, String des){
    HP = h;
    ATK = at;
    DEF = df;
    MagicDef = mdf;
    Intelligence = iq;
    Name = nm;
    Description = des;
}
    //returns boss name
   public String getName() {
        return Name;
    }

    
    //adjusts boss HP to passed in value
    public void setHP(int h){
        HP = h;
    }
    
   
    //Accessor methods for boss stats.
    
    public int getHP() {
        return HP;
    }
    

    public int getAtk() {
        return ATK;
    }

    public int getDef() {
        return DEF;
    }

    public int getMagicDef() {
        return MagicDef;
    }

    public int getMagicAtk() {
        return Intelligence;
    }
   
    //If the HP of the boss object is zero, the boss is dead.
   public boolean dead(){
       if(HP == 0){
           return true;
       }else{
           return false;
       }
       
   }
   
}
