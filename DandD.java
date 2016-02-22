/* Seth Yost - CSC 222 - Fall 2015
 * Program 5
 * This program is a graph based text-based RPG/Dungeon Crawler. The goal is to collect the final 
 item and win the game. The player must traverse a dungeon of 10 rooms to reach the final item, facing many
 perils along the way.
 */
package dandd;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;

public class DandD {

    public static void main(String[] args) throws FileNotFoundException {
        //creating an array of 10 rooms
        Room[] rooms = new Room[10];

        //initializing player stat pool
        int HP = 0;
        int ATK = 0;
        int DEF = 0;
        int MagDef = 0;
        int MagATK = 0;

        //sets start room to zero
        int currRoom = 0;

        //Answer string for menu creation
        String answer;
        //template for a character
        Character choice = new Character(0, 0, 0, 0, 0, "", "", "");

        //arraylist for character creation, it stores characters until the program is terminated. 
        ArrayList<Character> charactersList = new ArrayList<>(10);
        String saveCharName;

        //Graph constructor
        Graph grph = new Graph();

        //Character creation strings
        String classelect;
        String pname = "";
        String race = "";
        JOptionPane.showMessageDialog(null, "Welcome to my Game, a text-based Dungeon Crawler."
                + "\n\nThe goal of the game is to reach the final item at the end of the dungeon."
                + "\n\nThe path is set so some rooms are unreachable until an item is obtained"
                + "\nand there will be enemies to fight along the way."
                + "\n\nIf you die in a battle, you will be placed back in room zero, keep your items, "
                + "\nand be restored to full health.");

        //This do-while loop keeps the main menu and program running until Exit is selected in the main menu.
        do {

            boolean foundtoSave = false;
            //Main menu
            answer = JOptionPane.showInputDialog("Main Menu"
                    + "\nPlease Select an Option."
                    + "\n(N)ew Character."
                    + "\n(L)oad Character."
                    + "\n(S)ave Character."
                    + "\n(P)lay the Game."
                    + "\n(E)xit the Game.");

            if (answer.equalsIgnoreCase("N")) {
                //player name
                pname = JOptionPane.showInputDialog("What is your name?");

                //choice of race
                race = JOptionPane.showInputDialog("Which race does your character belong to?"
                        + "\nHuman - Bonus Hit Points and Physical Attack"
                        + "\nElf - Bonus Magic Attack and Magic Defense"
                        + "\nOrc - Bonus Physical and Magic Defenses"
                        + "\nCatPerson - Bonus Physical attack and magic Attack.\n");

                //input validation loop for race input
                while (!(race.equalsIgnoreCase("Human"))
                        && !(race.equalsIgnoreCase("Elf")) && !(race.equalsIgnoreCase("Orc"))
                        && !(race.equalsIgnoreCase("CatPerson"))) {
                    race = JOptionPane.showInputDialog("Please type the full name of the Race!!!"
                            + "\nHuman - Bonus Hit Points and Physical Attack"
                            + "\nElf - Bonus Magic Attack and Magic Defense"
                            + "\nOrc - Bonus Physical and Magic Defenses"
                            + "\nCatPerson - Bonus Physical attack and magic Attack.\n");
                }
                //If, else if based on race choice, different stats are appended.
                if (race.equalsIgnoreCase("Human")) {
                    HP = 20;
                    ATK = 5;
                    MagATK = 0;
                    DEF = 0;
                    MagDef = 0;
                } else if (race.equalsIgnoreCase("Elf")) {
                    HP = 0;
                    ATK = 0;
                    MagATK = 5;
                    DEF = 0;
                    MagDef = 5;
                } else if (race.equalsIgnoreCase("Orc")) {
                    HP = 0;
                    ATK = 0;
                    MagATK = 0;
                    DEF = 5;
                    MagDef = 5;
                } else if (race.equalsIgnoreCase("CatPerson")) {
                    HP = 0;
                    ATK = 5;
                    MagATK = 5;
                    DEF = 0;
                    MagDef = 0;
                }

                //class selection
                classelect = JOptionPane.showInputDialog("Which class would you like to select? "
                        + "\nWarrior (W)  \nGood: Physical attack, Physical Defense, Magic Defense"
                        + "\nPoor: Magic Attack \nWeapon: Claymore"
                        + "\n\nMage (M) \nExcellent: Magic Attack "
                        + "\nGood: Magic Defense \nPoor: Physical defense"
                        + "\nWeapon: Wand/Catalyst"
                        + "\n\nRogue (R) \nExcellent: Physical Attack \nGood: Magic Attack \nPoor: Physical Defense, "
                        + "Magic Defense \nWeapon: Dagger\n");

                //class selection input validation
                while (!(classelect.equalsIgnoreCase("W")) && !(classelect.equalsIgnoreCase("M")) && !(classelect.equalsIgnoreCase("R"))
                        && !(classelect.equalsIgnoreCase("Warrior")) && !(classelect.equalsIgnoreCase("Mage"))
                        && !(classelect.equalsIgnoreCase("Rogue"))) {
                    classelect = JOptionPane.showInputDialog("PLEASE PICK A VALID CLASS!! "
                            + "\nWarrior (W)  \nGood: Physical attack, Physical Defense, Magic Defense"
                            + "\nPoor: Magic Attack \nWeapon: Claymore"
                            + "\n\nMage (M) \nExcellent: Magic Attack "
                            + "\nGood: Magic Defense \nPoor: Physical defense"
                            + "\nWeapon: Wand/Catalyst"
                            + "\n\nRogue (R) \nExcellent: Physical Attack \nGood: Magic Attack \nPoor: Physical Defense, "
                            + "Magic Defense \nWeapon: Dagger\n");

                }
                //If, else if based on class choice. This is where the character object is actually created.
                if (classelect.equalsIgnoreCase("W") || classelect.equalsIgnoreCase("Warrior")) {
                    //character constructed with stats, class, race, and player name.
                    choice = new Character(200 + HP, 20 + ATK, 10 + MagATK, 25 + DEF, 25 + MagDef, "Warrior", race, pname);
                } else if (classelect.equalsIgnoreCase("M") || classelect.equalsIgnoreCase("Mage")) {
                    choice = new Character(180 + HP, 10 + ATK, 30 + MagATK, 15 + DEF, 25 + MagDef, "Mage", race, pname);
                } else if (classelect.equalsIgnoreCase("R") || classelect.equalsIgnoreCase("Rogue")) {
                    choice = new Character(180 + HP, 35 + ATK, 15 + MagATK, 15 + DEF, 15 + MagDef, "Rogue", race, pname);
                }
                //Displays completed character creation
                JOptionPane.showMessageDialog(null, "Your character's information: \n" + choice.toString());

                //stores character in array for storing until program runtime ends
                charactersList.add(choice);

                //Load Character
            } else if (answer.equalsIgnoreCase("L")) {
                String readIn = "";
                int count2 = 0;
                //Array of strings to pass the text file into
                String[] loadChar = new String[9];
                try {
                    //User input of input file name. In this case, the character name.
                    String filename = JOptionPane.showInputDialog("Please input the name of the character you would like to load in.");
                    //Scanner will read the input file the user requests.
                    Scanner inFile = new Scanner(new File(filename));
                    while (inFile.hasNext()) {
                        //Temp string takes in file value for next entry
                        readIn = inFile.next();
                        //ups the counter
                        count2++;
                        //sets the array of strings value corresponding the the count value to the temp variable value.
                        loadChar[count2] = readIn;
                    }
                    //closes fille input
                    inFile.close();

                    //Parses strings of stat values to ints 
                    HP = Integer.parseInt(loadChar[4]);
                    ATK = Integer.parseInt(loadChar[5]);
                    MagATK = Integer.parseInt(loadChar[6]);
                    DEF = Integer.parseInt(loadChar[7]);
                    MagDef = Integer.parseInt(loadChar[8]);

                    //Constructs the character with the loaded in values.
                    choice = new Character(HP, ATK, MagATK, DEF, MagDef, loadChar[3], loadChar[2], loadChar[1]);

                    //Ouputs to the user what the loaded character's information is.
                    JOptionPane.showMessageDialog(null, choice.toString());

                    //catches file not found and displays and error message.
                } catch (FileNotFoundException fnfe) {
                    JOptionPane.showMessageDialog(null, "Character not found, please try again.");
                }

                //Save a character.
            } else if (answer.equalsIgnoreCase("S")) {
                int count = 0;
                //If there is a character to save, the user will be prompted to type the character name.
                if (!charactersList.isEmpty()) {
                    saveCharName = JOptionPane.showInputDialog("What is the character name you're looking for?");

                    //Searches the character array for the character name
                    for (int i = 0; i < charactersList.size(); i++) {
                        if ((charactersList.get(i).getName()).equalsIgnoreCase(saveCharName)) {
                            JOptionPane.showMessageDialog(null, "Character found! Your Character will be saved to a text File.");
                            foundtoSave = true;
                            count = i;
                        }
                    }

                    //If the Character is found
                    if (foundtoSave == true) {
                        //Output file created, named after the character being saved.
                        FileOutputStream out = new FileOutputStream((charactersList.get(count)).getName(), true);
                        //PrintWriter Constuctor, printwriter will write text/data to the output file craeted above.
                        PrintWriter outFile = new PrintWriter(out);
                        
                        //prints the string from the toStringforSave method into the character named text file
                        outFile.println(charactersList.get(count).toStringforSave());
                        //Closes file writing
                        outFile.close();
                    } else {
                        //if character was not found
                        JOptionPane.showMessageDialog(null, "Character not found!");

                    }
                } else {
                    //IF THE user has not created any characters, the user can't save one.
                    JOptionPane.showMessageDialog(null, "You have yet to create any characters.");
                }

                //Playing the game.
            } else if (answer.equalsIgnoreCase("P")) {
                
                //intializes the dynamic player HP
                int currHealth = choice.getHP();

                //Item instantiation for vaious pick-up options
                Item healthpot = new Item("Health Potion", "Heals 50 HP.");
                Item powerUp = new Item("Power-Up Potion", "Increases one stat by five.");
                Item firekey = new Item("Fire Key", "Key guarded by Kraken that opens the frozen over door.");
                Item bone = new Item("Bone", "Bone that could satisfy the apetitie of even the largest of dogs.");
                Item crown = new Item("Old Crown", "Old, beaten up Crown worn by a great King of the Past.");

                //Room instantiation, inputs a description and the rooms item(if applicable).
                rooms[0] = new Room("You're in a small, damp room. It reminds you a bit of a prison cell.", healthpot);
                rooms[1] = new Room("You're in a dilapidated throne room. You get an ominous feeling.", powerUp);
                rooms[2] = new Room("You're in a long hallway. There is a man in the room by the opposite door.", healthpot);
                NPC solaire = new NPC("Knight Solaire");
                rooms[3] = new Room("You're in a large Cathedral-esque room with four doors, a sleeping "
                        + "cerberus guards the middle door.", healthpot);
                rooms[4] = new Room("You're in a very large cave with a large pool of salt water in it.", null);
                Boss kraken = new Boss(200, 35, 25, 20, 10, "Ice Golem", "Descendent of the monster Perseus defeated in Ancient Greece.");
                rooms[5] = new Room("You're in a small outcove within the cave. It feels very warm.", firekey);
                rooms[6] = new Room("You're in a snowy mountain valley with huge mountains on either"
                        + "\nside creating a natural path.", healthpot);
                Boss iceGolem = new Boss(225, 10, 20, 25, 30, "Ice Golem", "A large formation of ice animated with magic.");
                rooms[7] = new Room("You're an old shed that has stood the test of time.", bone);
                rooms[8] = new Room("You're in a dark room that appears to have no dimensions, like an endless space.", null);
                Boss usurper = new Boss(250, 30, 25, 25, 30, "The Dark King", "Master of the Abyss, the phantom who stole the crown of the previous King.");
                rooms[9] = new Room("You're in a small room in the back of the chamber, \na shining item case sits"
                        + "in the middle of the room.", crown);
                //sets Item array based on what was added to the roosm I instantiated
                for (int r = 0; r < rooms.length; r++) {
                    choice.setItemArray(rooms[r]);
                }
                //sets active HP values for bosses, used during battle.
                int krakenHealth = kraken.getHP();
                int iceGHealth = iceGolem.getHP();
                int usurpHealth = usurper.getHP();

                //temp value storing variables
                int bossDamage = 0;
                int heroDamage = 0;
                String quit = "";
                String option;
                String fightchoice;

                //Random number generation is used to diversify boss attack pattern.
                Random rand = new Random();

                //Do-while for playing the game, aborts if the user decides to quit or if the player wins.
                do {
                    
                    //If the player enters room 4 and the Kraken is not dead, the player will fight the Kraken
                    if (currRoom == 4 && kraken.dead() == false) {
                        
                        JOptionPane.showMessageDialog(null, "A Kraken has appeared from the ocean depths!"
                                + "\nBe prepared to fight!");
                        
                        //Starts a new battle
                        Battle fight = new Battle();
                        
                        //This do-while continues until the kraken or player is defeated.
                        do {
                            //Temp int value holders
                            int tempDamage1 = 0;
                            int tempDamage2 = 0;
                            int holdHealth = 0;
                            
                            //Battle menu, displays Player and Boss HP and the player's options for fighting.
                            fightchoice = JOptionPane.showInputDialog("Kraken's Health: "
                                    + "\nHP: " + krakenHealth + "/" + kraken.getHP() + "\n"
                                    + choice.getName() + "'s stats:"
                                    + "\nHP: " + currHealth + "/" + choice.getHP()
                                    + "\nATK: " + choice.getAtk()
                                    + "\nMagic ATK: " + choice.getMagicAtk()
                                    + "\n\n(A)ttack || (M)agic Attack || (H)eal");
                            
                            //If the Choice is a physical attack
                            if (fightchoice.equalsIgnoreCase("A")) {
                                //Damage calculated in Battle class
                                heroDamage = fight.phyAttackChar(choice.getAtk(), kraken.getDef());
                                //Message displays what type of damage was done and how much.
                                JOptionPane.showMessageDialog(null, "You strike the enemy with your weapon "
                                        + "doing " + heroDamage + " physical damage!");
                                
                                //temp variable for storing kraken's adjusted HP
                                tempDamage1 = krakenHealth - heroDamage;
                                
                                //Setting Kraken's current HP
                                krakenHealth = tempDamage1;
                                
                                //Same process as before, but takes magic values into consideration
                            } else if (fightchoice.equalsIgnoreCase("M")) {
                                
                                heroDamage = fight.magAttackChar(choice.getMagicAtk(), kraken.getMagicDef());
                                
                                JOptionPane.showMessageDialog(null, "You strike the enemy with a spell "
                                        + "doing " + heroDamage + " magic damage!");
                                
                                tempDamage1 = krakenHealth - heroDamage;
                                krakenHealth = tempDamage1;
                                
                                //If the user chooses to heal.
                            } else if (fightchoice.equalsIgnoreCase("H")) {
                                
                                //checks to see if the user has a healthpot in the bag
                               if (choice.useItem(healthpot) == true) {
                                    
                                    currHealth = currHealth + 50;
                                    //restores 50 HP
                                
                                if (currHealth > choice.getHP()) {
                                    int difference = currHealth - choice.getHP();
                                    currHealth = currHealth - difference;
                                }
                                choice.useItem(healthpot);
                                    JOptionPane.showMessageDialog(null, "You've been healed!");
                                    //removes health pot from item array as well
                                    choice.setItemArray(rooms[currRoom]);
                                    
                                } else {
                                    //if the user has no health pots, the user can't heal
                                    JOptionPane.showMessageDialog(null, "You're out of health potions!");
                                }
                            }
                            //input validation for battle menu
                            while (!fightchoice.equalsIgnoreCase("A") && !fightchoice.equalsIgnoreCase("M")
                                    && !fightchoice.equalsIgnoreCase("H")) {
                                fightchoice = JOptionPane.showInputDialog("Kraken's Health: "
                                        + "\nHP: " + krakenHealth + "/" + kraken.getHP()
                                        + choice.getName() + "'s stats:"
                                        + "\nHP: " + currHealth + "/" + choice.getHP()
                                        + "\nATK: " + choice.getAtk()
                                        + "\nMagic ATK: " + choice.getMagicAtk()
                                        + "\n\n(A)ttack || (M)agic Attack || (H)eal");
                            }
                            //Boss attacks, random number generated, if the value is greater than 5, the
                            //boss deploys a physical attack, if the value is below 5, the Boss uses magic
                            if (rand.nextInt(10) > 5) {
                                //Physical attack calculation Boss attacking player
                                bossDamage = fight.phyAttackBoss(choice.getDef(), kraken.getAtk());
                                
                                //attack description and damge shown
                                JOptionPane.showMessageDialog(null, "The Kraken slaps you with one of its "
                                        + "tentacles doing " + bossDamage + " physical damage!");
                                
                                //updating player health
                                tempDamage2 = currHealth - bossDamage;
                                currHealth = tempDamage2;
                            } else {
                                //functions the same as the physical attack calculation for for magic
                                bossDamage = fight.magAttackBoss(choice.getMagicDef(), kraken.getMagicAtk());
                                
                                JOptionPane.showMessageDialog(null, "The Kraken shoots you with a burst of "
                                        + "water doing " + bossDamage + " magic damage!");
                                
                                tempDamage2 = currHealth - bossDamage;
                                currHealth = tempDamage2;
                            }
                        } while (!(krakenHealth <= 0) && !(currHealth <= 0)); //If Boss or Player dies, loop aborts
                        
                        //If the loop aborted because the kraken died
                        if (krakenHealth <= 0) {
                            //Victory message, and character "level-up"
                            JOptionPane.showMessageDialog(null, "The Kraken has been defeated!! "
                                    + "\nYour health has been restored to full. "
                                    + "\nYou've also gained 20 base HP.");
                            
                            //Updates user HP to be 20 health points greater
                            choice.setHP(((choice.getHP()) + 20));
                            //updates dynamic HP to equal new value of static HP
                            currHealth = choice.getHP();
                            
                            //Sets kraken HP to 0 in the kraken object, rendering it dead to the program.
                            kraken.setHP(0);
                            kraken.dead();
                        } else {
                            //if the Battle ended because the player died, the player is warped to start.
                            JOptionPane.showMessageDialog(null, "You died. Returning to the start...");
                            currRoom = 0;
                            currHealth = choice.getHP();
                        }

                        //Ice Golem boss, functionally identical to the Kraken fight
                    } else if (currRoom == 6 && iceGolem.dead() == false) {
                        JOptionPane.showMessageDialog(null, "An Ice Golem has emerged from the snowy mountainside!"
                                + "\nBe prepared to fight!");
                        Battle fight = new Battle();
                        do {
                            int tempDamage1 = 0;
                            int tempDamage2 = 0;
                            int holdHealth = 0;
                            fightchoice = JOptionPane.showInputDialog("Ice Golem's Health: "
                                    + "\nHP: " + iceGHealth + "/" + iceGolem.getHP() + "\n"
                                    + choice.getName() + "'s stats:"
                                    + "\nHP: " + currHealth + "/" + choice.getHP()
                                    + "\nATK: " + choice.getAtk()
                                    + "\nMagic ATK: " + choice.getMagicAtk()
                                    + "\n\n(A)ttack || (M)agic Attack || (H)eal");
                            if (fightchoice.equalsIgnoreCase("A")) {

                                heroDamage = fight.phyAttackChar(choice.getAtk(), iceGolem.getDef());
                                JOptionPane.showMessageDialog(null, "You strike the enemy with your weapon "
                                        + "doing " + heroDamage + " physical damage!");
                                tempDamage1 = iceGHealth - heroDamage;
                                iceGHealth = tempDamage1;
                            } else if (fightchoice.equalsIgnoreCase("M")) {
                                heroDamage = fight.magAttackChar(choice.getMagicAtk(), iceGolem.getMagicDef());
                                JOptionPane.showMessageDialog(null, "You strike the enemy with a spell "
                                        + "doing " + heroDamage + " magic damage!");
                                tempDamage1 = iceGHealth - heroDamage;
                                iceGHealth = tempDamage1;
                            } else if (fightchoice.equalsIgnoreCase("H")) {
                                 if (choice.useItem(healthpot) == true) {
                                    
                                    currHealth = currHealth + 50;
                                    //restores 50 HP
                                
                                if (currHealth > choice.getHP()) {
                                    int difference = currHealth - choice.getHP();
                                    currHealth = currHealth - difference;
                                }
                                choice.useItem(healthpot);
                                    JOptionPane.showMessageDialog(null, "You've been healed!");
                                    //removes health pot from item array as well
                                    choice.setItemArray(rooms[currRoom]);
                                    
                                }  else {
                                    JOptionPane.showMessageDialog(null, "You're out of health potions!");
                                }
                            }
                            while (!fightchoice.equalsIgnoreCase("A") && !fightchoice.equalsIgnoreCase("M")
                                    && !fightchoice.equalsIgnoreCase("H")) {
                                fightchoice = JOptionPane.showInputDialog("Ice Golem's Health: "
                                        + "\nHP: " + iceGHealth + "/" + iceGolem.getHP()
                                        + choice.getName() + "'s stats:"
                                        + "\nHP: " + currHealth + "/" + choice.getHP()
                                        + "\nATK: " + choice.getAtk()
                                        + "\nMagic ATK: " + choice.getMagicAtk()
                                        + "\n\n(A)ttack || (M)agic Attack || (H)eal");
                            }
                            if (rand.nextInt(10) > 5) {
                                bossDamage = fight.phyAttackBoss(choice.getDef(), iceGolem.getAtk());
                                JOptionPane.showMessageDialog(null, "The Ice Golem slams down on you with one of its "
                                        + "massive arms " + bossDamage + " physical damage!");
                                tempDamage2 = currHealth - bossDamage;
                                currHealth = tempDamage2;
                            } else {
                                bossDamage = fight.magAttackBoss(choice.getMagicDef(), iceGolem.getMagicAtk());
                                JOptionPane.showMessageDialog(null, "The Ice Golem fires massive ice crystals "
                                        + "at you " + bossDamage + " magic damage!");
                                tempDamage2 = currHealth - bossDamage;
                                currHealth = tempDamage2;
                            }
                        } while (!(iceGHealth <= 0) && !(currHealth <= 0));
                        if (iceGHealth <= 0) {
                            JOptionPane.showMessageDialog(null, "The Ice Golem has been defeated!! "
                                    + "\nYour health has been restored to full. "
                                    + "\nYou've also gained 20 base HP.");
                            choice.setHP(((choice.getHP()) + 20));
                            currHealth = choice.getHP();
                            iceGolem.setHP(0);
                            iceGolem.dead();
                        } else {
                            JOptionPane.showMessageDialog(null, "You died. Returning to the start...");
                            currRoom = 0;
                            currHealth = choice.getHP();
                        }
                        
                        //Final boss, functionally identical to the Kraken and Ice Golem fights.
                    } else if (currRoom == 8 && usurper.dead() == false) {
                        JOptionPane.showMessageDialog(null, "The man who stole the throne has been consumed "
                                + "\nby darkness and is living in this room devoid of light. "
                                + "\nHe is not pleased you are in his chamber!"
                                + "\nBe prepared to fight!");
                        Battle fight = new Battle();
                        do {
                            int tempDamage1 = 0;
                            int tempDamage2 = 0;
                            int holdHealth = 0;
                            fightchoice = JOptionPane.showInputDialog("Usurper's Health: "
                                    + "\nHP: " + usurpHealth + "/" + usurper.getHP() + "\n"
                                    + choice.getName() + "'s stats:"
                                    + "\nHP: " + currHealth + "/" + choice.getHP()
                                    + "\nATK: " + choice.getAtk()
                                    + "\nMagic ATK: " + choice.getMagicAtk()
                                    + "\n\n(A)ttack || (M)agic Attack || (H)eal");
                            if (fightchoice.equalsIgnoreCase("A")) {

                                heroDamage = fight.phyAttackChar(choice.getAtk(), usurper.getDef());
                                JOptionPane.showMessageDialog(null, "You strike the enemy with your weapon "
                                        + "doing " + heroDamage + " physical damage!");
                                tempDamage1 = usurpHealth - heroDamage;
                                usurpHealth = tempDamage1;
                            } else if (fightchoice.equalsIgnoreCase("M")) {
                                heroDamage = fight.magAttackChar(choice.getMagicAtk(), usurper.getMagicDef());
                                JOptionPane.showMessageDialog(null, "You strike the enemy with a spell "
                                        + "doing " + heroDamage + " magic damage!");
                                tempDamage1 = usurpHealth - heroDamage;
                                usurpHealth = tempDamage1;
                            } else if (fightchoice.equalsIgnoreCase("H")) {
                                 if (choice.useItem(healthpot) == true) {
                                    
                                    currHealth = currHealth + 50;
                                    //restores 50 HP
                                
                                if (currHealth > choice.getHP()) {
                                    int difference = currHealth - choice.getHP();
                                    currHealth = currHealth - difference;
                                }
                                choice.useItem(healthpot);
                                    JOptionPane.showMessageDialog(null, "You've been healed!");
                                    //removes health pot from item array as well
                                    choice.setItemArray(rooms[currRoom]);
                                    
                                }  else {
                                    JOptionPane.showMessageDialog(null, "You're out of health potions!");
                                }
                            }
                            while (!fightchoice.equalsIgnoreCase("A") && !fightchoice.equalsIgnoreCase("M")
                                    && !fightchoice.equalsIgnoreCase("H")) {
                                fightchoice = JOptionPane.showInputDialog("Usurper's Health: "
                                        + "\nHP: " + usurpHealth + "/" + usurper.getHP()
                                        + choice.getName() + "'s stats:"
                                        + "\nHP: " + currHealth + "/" + choice.getHP()
                                        + "\nATK: " + choice.getAtk()
                                        + "\nMagic ATK: " + choice.getMagicAtk()
                                        + "\n\n(A)ttack || (M)agic Attack || (H)eal");
                            }
                            if (rand.nextInt(10) > 5) {
                                bossDamage = fight.phyAttackBoss(choice.getDef(), usurper.getAtk());
                                JOptionPane.showMessageDialog(null, "You've been stabbed but you didn't see it happen. "
                                        + "The stab did " + bossDamage + " physical damage!");
                                tempDamage2 = currHealth - bossDamage;
                                currHealth = tempDamage2;
                            } else {
                                bossDamage = fight.magAttackBoss(choice.getMagicDef(), usurper.getMagicAtk());
                                JOptionPane.showMessageDialog(null, "The Usurper fires a dark energy mass "
                                        + "at you doing " + bossDamage + " magic damage!");
                                tempDamage2 = currHealth - bossDamage;
                                currHealth = tempDamage2;
                            }
                        } while (!(usurpHealth <= 0) && !(currHealth <= 0));
                        if (usurpHealth <= 0) {
                            JOptionPane.showMessageDialog(null, "The Usurper has been defeated!! "
                                    + "\nYour health has been restored to full. "
                                    + "\nYou've also gained 20 base HP.");
                            choice.setHP(((choice.getHP()) + 20));
                            currHealth = choice.getHP();
                            usurper.setHP(0);
                            usurper.dead();
                        } else {
                            JOptionPane.showMessageDialog(null, "You died. Returning to the start...");
                            currRoom = 0;
                            currHealth = choice.getHP();
                        }
                    }
                    
                    //If the current room is room 2, this If statement ensures the NPC interaction option is present.
                    if (currRoom == 2) {
                        option = JOptionPane.showInputDialog("Game Menu:"
                                + "\nWhat would you like to do?"
                                + "\n(L)ook around."
                                + "\n(I)nteract with NPC."
                                + "\n(M)ove to room: "
                                + "\n(P)ick-up item."
                                + "\n(C)heck status."
                                + "\n(S)how Bag Contents."
                                + "\n(Q)uit the game.");
                        if (option.equalsIgnoreCase("I")) {
                            //NPC interaction methods.
                            JOptionPane.showMessageDialog(null, solaire.talkTo());
                            JOptionPane.showMessageDialog(null, solaire.talkToContinued());
                        }
                        
                        //For every other room but two, the options are identical
                    } else {
                          
                        option = JOptionPane.showInputDialog("Game Menu:"
                                + "\nWhat would you like to do?"
                                + "\n(L)ook around."
                                + "\n(M)ove to room: "
                                + "\n(P)ick-up item."
                                + "\n(C)heck status."
                                + "\n(S)how Bag Contents."
                                + "\n(Q)uit the game.");
                    }
                    
                    //Look around, returns room description
                    if (option.equalsIgnoreCase("L")) {
                        JOptionPane.showMessageDialog(null, rooms[currRoom].getAppearance());
                    } 
                    
                    //Move Option
                    else if (option.equalsIgnoreCase("M")) {
                        
                        try {
                            //Checks which room the user would like to move to. Presents user with viable options.
                            int moveRoom = Integer.parseInt(JOptionPane.showInputDialog("Which room would "
                                    + "you like to move to?\n"
                                    + "Enter just the number.\n"
                                    + grph.checkRoom(currRoom)));
                            
                            //Checks if the character can move to the room number entered.
                            boolean[] roomMove = grph.checkcanMove(currRoom);
                            
                            //If the player can move to the desired room.
                            if (roomMove[moveRoom] == true) {
                                
                            //if-else Key catchers, makes sure the user cannot enter locked doors without the proper item.
                            //if the user has the proper item, the player can move there.
                                if (moveRoom == 8 && choice.hasBone() == false) {
                                    JOptionPane.showMessageDialog(null, "Cannot move there, the Cerberus is still blocking the door.");
                                } 
                                
                                else if (moveRoom == 6 && choice.hasfireKey() == false) {
                                    JOptionPane.showMessageDialog(null, "Cannot move there, the door is still frozen over.");
                                } 
                                
                                else if (moveRoom == 8 && choice.hasBone() == true) {
                                    JOptionPane.showMessageDialog(null, "The Cerberus moved after you presented it with the bone."
                                            + "\nYou may proceed.");
                                    currRoom = moveRoom;
                                } 
                                
                                else if (moveRoom == 6 && choice.hasBone() == true) {
                                    JOptionPane.showMessageDialog(null, "The door melted and lock turned with use of the Fire Key."
                                            + "\nYou may proceed.");
                                    currRoom = moveRoom;
                                } else {
                                    currRoom = moveRoom;
                                }

                            } else {
                                //If the choice was bad but still an integer value, this message appears.
                                JOptionPane.showMessageDialog(null, "Can't move to that room! Returning to menu...");
                            }
                            //If the user tries to enter a string, this try/catch will tell the user its a bad input
                        } catch (NumberFormatException nfe) {
                            JOptionPane.showMessageDialog(null, "Bad input! Returning to menu...");
                        }
                        
                     //Pick-Up item option,calls pickUpItem method from Character class, passing in current room num
                    } else if (option.equalsIgnoreCase("P")) {
                        JOptionPane.showMessageDialog(null, choice.pickUpItem(rooms[currRoom]));
                        
                        //Check-Status option, returns string of current player status.
                    } else if (option.equalsIgnoreCase("C")) {
                        JOptionPane.showMessageDialog(null, "Character Name: " + choice.getName()
                                + "\nRace: " + choice.getRace()
                                + "\nClass: " + choice.getCharClass()
                                + "\nHP: " + currHealth + "/" + choice.getHP()
                                + "\nATK: " + choice.getAtk()
                                + "\nMagic ATK: " + choice.getMagicAtk()
                                + "\nDEF: " + choice.getDef()
                                + "\nMagic DEF: " + choice.getMagicDef());
                        
                        //Show bag option shows the user the contents of his/her bag
                    } else if (option.equalsIgnoreCase("S")) {
                        String whichItem = "";
                        //Asks the user if user would like to use an item.
                        String useItem = JOptionPane.showInputDialog(choice.showBag()
                                + "\nUse an item? Yes or No.");
                        
                        //if the user does want to use an item, prompted to enter item name.
                        if (useItem.equalsIgnoreCase("Yes") || useItem.equalsIgnoreCase("Y")) {
                            whichItem = JOptionPane.showInputDialog(choice.showBag()
                                    + "\n\nWhich Item? (Type item name)");
                            
                            //If the item is a health potion
                            if (whichItem.equalsIgnoreCase("Health Potion")) {
                                //player uses the health potion
                                choice.useItem(healthpot);
                                //dynamic health is updated
                                currHealth = currHealth + 50;
                                
                                //makes sure the dnymaic health doesn't break the cap
                                if (currHealth > choice.getHP()) {
                                    int difference = currHealth - choice.getHP();
                                    currHealth = currHealth - difference;
                                }
                                
                                JOptionPane.showMessageDialog(null, "You've been healed!");
                                choice.setItemArray(rooms[currRoom]);
                                
                                //Power-up Potion option
                            } else if (whichItem.equalsIgnoreCase("Power-Up Potion")) {
                                choice.useItem(powerUp);
                                //asks user which stat he/she would like to boost by 5
                                String boost = JOptionPane.showInputDialog("Which stat would you like to boost:"
                                        + "HP, ATK, MagATK, DEF, or MagDef?");
                                //input validation
                                while (!(boost.equalsIgnoreCase("HP")) && !(boost.equalsIgnoreCase("ATK"))
                                        && !(boost.equalsIgnoreCase("MagATK")) && !(boost.equalsIgnoreCase("MagDEF"))
                                        && !(boost.equalsIgnoreCase("DEF"))) {
                                    boost = JOptionPane.showInputDialog("Which stat would you like to boost?");
                                }
                                //method that processes the stat boost
                                choice.boostStat((boost));
                                choice.setItemArray(rooms[currRoom]);
                            } else {
                                //if the user doesn't enter something valid
                                JOptionPane.showMessageDialog(null, "Bad Input... returning to menu.");
                            }
                        }
                    } else if (option.equalsIgnoreCase("Q")) {
                        //If the user entered Q for quit, the user is re-prompted to confirm
                        quit = JOptionPane.showInputDialog("Are you sure you want to quit?"
                                + "\nYes or No?");
                    }
                    
                    //Do-while loop ends if the player chooses to quit or if the player won the game.
                } while (!quit.equalsIgnoreCase("yes") && !quit.equalsIgnoreCase("Y") && choice.hasCrown() == false);
                if (quit.equalsIgnoreCase("yes") || quit.equalsIgnoreCase("y")) {
                    JOptionPane.showMessageDialog(null, "Returning to main menu...");
                } else {
                    JOptionPane.showMessageDialog(null, "You've won the game!!!"
                            + "\nReturning to main menu...");
                }
            }

            //This loop aborts when E for exit is entered into the main menu.
        } while (!(answer.equalsIgnoreCase("E")));
        JOptionPane.showMessageDialog(null, "Thank you for playing!!!");

    }
}
