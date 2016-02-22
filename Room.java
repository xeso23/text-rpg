package dandd;

import java.util.ArrayList;

public class Room {
  
    //Room's item and appearance variables
    private Item roomItem;
    private String appearance;
    
    //Room constructor, takes in item and the room's description.
    public Room(String a, Item i){
        appearance = a;
        roomItem = i;
        
    }
    
    //Returns room's description
    public String getAppearance(){
        return appearance;
    }
    
    //Checks Item object, returns it as an Item object.
    public Item checkItem(){
        
        return roomItem;
        
    }
    
    //voids the room item if its been picked up(determined in main program through Character class)
    public void setItem(){
        
        roomItem = null;
        
    }
   
}
