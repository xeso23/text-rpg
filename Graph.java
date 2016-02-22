package dandd;

public class Graph {

    //The Adjancey matrix for this graph.
    //This determines where the player can move and what rooms are connected to one another.
    private int[][] adjacency = 
            /*Room 0*/ {{0, 1, 1, 0, 0, 0, 0, 0, 0, 0},
            /*Room 1*/ {1, 0, 0, 0, 0, 0, 0, 0, 0, 0},
            /*Room 2*/ {1, 0, 0, 1, 0, 0, 0, 0, 0, 0},
            /*Room 3*/ {0, 0, 1, 0, 1, 0, 1, 0, 1, 0},
            /*Room 4*/ {0, 0, 0, 1, 0, 1, 0, 0, 0, 0},
            /*Room 5*/ {0, 0, 0, 0, 1, 0, 0, 0, 0, 0},
            /*Room 6*/ {0, 0, 0, 1, 0, 0, 0, 1, 0, 0},
            /*Room 7*/ {0, 0, 0, 0, 0, 0, 1, 0, 0, 0},
            /*Room 8*/ {0, 0, 0, 0, 0, 0, 0, 0, 0, 1},
            /*Room 9*/ {0, 0, 0, 0, 0, 0, 0, 0, 1, 0}};
    
    //boolean value for being able to move on to the next room.
    private boolean canMove[];
    
    //Graph constructor
    public Graph() {
    canMove = new boolean[10];
    }
    
    //Returns the array of boolean values for where the player can move from input room number.
    public boolean[] checkcanMove(int row){
        return canMove;
    }

    /*Check Room method
    Checks where the player can move by traversing the adjacency matrix checking for 1s.
    Where the value of [row][column] is 1, the canMove[column number] is set to true.
    A temp string is also kept to provide the player with a string list of rooms that are accesible.
    */
    
    public String checkRoom(int row) {
        String temp = "";
        for (int column = 0; column < 10; column++) {
            
            if (adjacency[row][column] == 1) {
                canMove[column] = true;
                temp += column + " ";
            } else {
                canMove[column] = false;
            }
        }
        return temp;
    }
    
}
