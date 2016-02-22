package dandd;

import java.util.ArrayList;

public class Character {
    
    //Character Stats, Class Type, Race, and name.
    private int HP;
    private int ATK;
    private int DEF;
    private int MagicDef;
    private int Intelligence;
    private String ClassType;
    private String race;
    private String name;
    
    //ArrayLists for items, the player bag, the room items array, and a copy of it.
    private ArrayList<Item> bag = new ArrayList<>();
    private ArrayList<Item> items = new ArrayList<>();
    private ArrayList<Item> itemcopy = new ArrayList<>();

    //Character object constructor
    public Character(int h, int at, int iq, int df, int mdf, String ct, String r, String nm) {
        HP = h;
        ATK = at;
        Intelligence = iq;
        DEF = df;
        MagicDef = mdf;
        ClassType = ct;
        race = r;
        name = nm;
    }

    //Annotated to string of character Name, Race, Class, and Stats
    public String toString() {
        return "Character Name: " + name + " " 
                + "\nRace: " + race + " "
                + "\nClass: " + ClassType + " "
                + "\nHP: " + HP + " "
                + "\nATK: " + ATK + " "
                + "\nMagic ATK: " + Intelligence + " "
                + "\nDEF: " + DEF + " "
                + "\nMagic DEF: " + MagicDef;

    }
    
    //Vanilla toString of Character name, race, class, and stats. This is used for saving a text file.
     public String toStringforSave() {
        return  name  + "\n" + race + " "
                + "\n" + ClassType + " "
                + "\n" + HP + " "
                + "\n" + ATK + " "
                + "\n" + Intelligence + " "
                + "\n" + DEF + " "
                + "\n" + MagicDef;

    }

    public String getName() {
        return name;
    }
    
    public String getRace(){
        return race;
    }

    public String getCharClass() {
        return ClassType;
    }

    public int getHP() {
        return HP;
    }
    
    public void setHP(int h){
        HP = h;
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

    //Method used by power-up Potion, takes in the stat the player wants to boost and adds 5 to its value
    public void boostStat(String Stat){
        int temp = 0;
        if(Stat.equalsIgnoreCase("HP")){
            temp = HP + 5;
            HP = temp;
        }else if(Stat.equalsIgnoreCase("ATK")){
            temp = ATK + 5;
            ATK = temp;
        }else if(Stat.equalsIgnoreCase("MagATK")){
            temp = Intelligence + 5;
            Intelligence = temp;
        }else if(Stat.equalsIgnoreCase("DEF")){
            temp = DEF + 5;
            DEF = temp;
        }else if(Stat.equalsIgnoreCase("MagDef")){
            temp = MagicDef + 5;
            MagicDef = temp;
        }
    }
    
    /*Pick-Up item method
    passes in the current room from the main program
    If the array is not empty and the item picked up is not null,
    and item is in the array(checked by string comparision of names) it is removed from the room item array.
    Adds the item to the players bag and returns that it has done so with an item description.
    If there's no item like described above, a message saying there's no item appears instead.
    */
    public String pickUpItem(Room r){
        String temp;
        if(!items.isEmpty() && r.checkItem() != null){
            
         for(int k = 0; k < items.size(); k++){
            if((items.get(k)).getName().equalsIgnoreCase(r.checkItem().getName())){
                items.remove(k);
               break;  
            } 
         }
        bag.add(r.checkItem());
        temp = "You've picked up a " + r.checkItem().getIteminfo();
        //sets item to null after being picked up to prevent duplication and stockpiling
        r.setItem();
        return temp;
        }else{
            return "There's nothing here to pick-up.";
        }
        
        
      
    }
 
    //Win Condition, if the crown is in the user's inventory you win the game.
    //The bag arraylist is traversed and looks for the matching item name.
    public boolean hasCrown(){
        boolean hasCrown = false;
   if(!bag.isEmpty()){
        for(int k = 0; k < bag.size(); k++){
            if((bag.get(k)).getName().equalsIgnoreCase("Old Crown")){
                hasCrown = true;
            } }
        }
   return hasCrown;
   }
        
    //Checks if the user can open the middle door by moving the Cerberus
    //The bag arraylist is traversed and looks for the matching item name.
   public boolean hasBone(){
        boolean hasBone = false;
   if(!bag.isEmpty()){
        for(int k = 0; k < bag.size(); k++){
            if((bag.get(k)).getName().equalsIgnoreCase("Bone")){
                hasBone = true;
            } }
        }
   return hasBone;
   }
   
   //Checks if the user has a firey key to melt and unlock the icey door.
    //The bag arraylist is traversed and looks for the matching item name.
   public boolean hasfireKey(){
        boolean hasFireKey = false;
   if(!bag.isEmpty()){
        for(int k = 0; k < bag.size(); k++){
            if((bag.get(k)).getName().equalsIgnoreCase("Fire Key")){
                hasFireKey = true;
            } }
        }
   return hasFireKey;
   }
    
   //returns string of all the items contained in the player's bag currently
    public String showBag(){
        String temp = "";
        for(int k = 0; k < bag.size(); k++){
            temp+= bag.get(k).getIteminfo() + "\n\n";
                    }
        return temp;
    }
    
    /*Use Item method
    Takes in Item object from main program, and gets its name string.
    Sets default ifFound value to false. 
    Searches array for Item object with the same name.
    Returns true if found, false if not.
    */
    public boolean useItem(Item i){
        String name = i.getName();
        boolean itemFound = false;
        for(int k = 0; k < bag.size(); k++){
            if((bag.get(k)).getName().equalsIgnoreCase(name)){
                bag.remove(k);
                itemFound = true;
                break;
                
            } }
        return itemFound;
    }
    
    //Sets item array for the rooms to keep an extra copy.
    public void setItemArray(Room r){
     if(r.checkItem()!= null){
        items.add(r.checkItem());
        }
        //for loop to copy items here
        for(int j = 0; j < items.size(); j++){
        itemcopy.add(items.get(j));
    }
       
}
}
