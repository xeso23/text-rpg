package dandd;

public class Item {
    
    //The item private variables
    private String name;
    private String description;

      //Item object constructor
      public Item(String n, String d){
         name = n;
        description = d;
         
      }
      
      //returns object name
      public String getName(){
          return name;
      }
    
      //returns item information: name and description
    public String getIteminfo(){
        return name + "\n" + description;
    }
            
}
