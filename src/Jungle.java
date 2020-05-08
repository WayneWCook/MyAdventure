/*
Cole Ballagh
pd.2
5-8-20
 */
import java.util.ArrayList;
import java.util.Random;

class Jungle extends ModelRoom {
    //Attributes
    final static private String name = "Jungle";
    private ArrayList<String> localWeapons = new ArrayList<>();
    private ArrayList<String> localTreasures = new ArrayList<>();
    private Random random = new Random();
    // Methods
    String getName() {
        return name;
    }
    private void pickUpItem() {
        if (localWeapons.size() > 0) {
            super.addWeapon(localWeapons.get(0));
            localWeapons.remove(0);
        }
    }
    Jungle() {
        localWeapons.add("stick");

    }
    void enter() {
        boolean loop = true;
        do {
            System.out.println("Welcome to the " + this.getName() + ".");
            switch (super.chooseMenuItem("List what is in the room")) {
                case 1:
                    this.goUp();
                    break;
                case 2:
                    this.goDown();
                    break;
                case 3:
                    this.goNorth();
                    break;
                case 4:
                    this.goSouth();
                    break;
                case 5:
                    this.goEast();
                    break;
                case 6:
                    this.goWest();
                    break;
                case 7:
                default:
                    this.doAction();
                    break;
            }
            if (exitAdventure) loop = false;
        } while (loop);
    }
    void goUp() {
        System.out.println("There is nothing but trees overhead.");
    }
    void goDown() {
        System.out.println("You see a couple of snakes, as long as you move slowly they shouldn't hurt you");
    }
    void goNorth() { super.getRoom(7).enter();}
    void goSouth() { //has a portal going to the street so it doesn't take hours to leave the game
        System.out.println("Whoa, there's a portal here!");
    if (kbio.YNRequestInput("Would you like to go through?")){
        System.out.println("You fell all the way down to the Street\nYou lost a life");
        removeLife();
        super.getRoom(0).enter();//enters the room defined in the superclass, ModelRoom
        }
    else {
        System.out.println("Yeah, jumping into a random portal, probably not the best idea");
    }
    }
    void goEast() { System.out.println("Nothing but a thick wall of vines."); }
    void goWest() {
        System.out.println("You just got tangled up in some vines\nPress enter to see if you can roll a 6 to get back out");
        super.rollDice(6);
    }
    void doAction() {
        String onFloor = "nother";
        if (localWeapons.size() > 0) onFloor = "a " + localWeapons.get(0);    //gives the user a stick if they do something
        System.out.println("You found a " + onFloor + "!");
        if (!onFloor.equals("nothing")) {
            char r = kbio.requestInput(("Do you want to pick up " + onFloor + "? (Y/N)")).toUpperCase().toCharArray()[0];
            if (r =='Y') this.pickUpItem();                             // gets the pickUpItem() function from this class
    }
}
}