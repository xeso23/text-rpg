package dandd;

public class NPC {
  private String name;

  public NPC(String n){
      name = n;
  }
  
  //NPC speech method
  public String talkTo(){
      String dialogue = "My name is Knight Solaire. I will act as your guide."
              + "\nBeyond lies a room with 3 more paths to choose from:"
                  + "\n\nThe Middle door is guarded by a Cerberus, \nhe is immortal, but you may be able to get him to move."
                  + "\n\nThe Right door is large and frozen over, but you may be able to melt your way through."
                  + "\n\nThe Left door is open and leads to a cave containing a large pool of sea water.";
      
      return dialogue;
  }
  //More speech
  public String talkToContinued(){
      String response = "Praise the Sun!" +
                  "\nSolaire strikes an awkard pose with his hands reaching towards the sky.";
      return response;
  }
}
