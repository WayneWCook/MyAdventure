/* Street.java : The starting street of the great adventure
 * Author: Wayne Cook
 * This is the very beginning of the great adventure. You can meet friends, visit a hotel, or open a grate
 * and enter your adventure.
 *
 * Creation date: 23 April: The adventure has to start somewhere and this is it.
 */
import java.util.ArrayList;

public class Street extends ModelRoom {
    // Attributes
    private ArrayList<String> localTreasures = new ArrayList<>();
    private ArrayList<String> localWeapons = new ArrayList<>();
    private ArrayList<String> localFriends = new ArrayList<>();
    private String possition = "missing";
    private KBIO kbio = new KBIO();
    final static private String name = "The Street";

    // Constructor
    protected Street() {
        this.localTreasures.add("Grate");
    }

    // Methods
    private void pickUpItem() {
        if (localTreasures.size() > 0) {
            super.addTreasure(localTreasures.get(0));
            localTreasures.remove(0);
        }
    }

    // Returns true is grate is down and false if not.

    private boolean checkGrate() {
        boolean retVal = false;
        possition = "missing";
        if (localTreasures.size() > 0) {
            possition = "down";
            retVal = true;
        }
        System.out.println("The grate is " + possition);
        return retVal;
    }

    // Methods
    String getName() {
        return name;
    }

    void enter() {
        boolean loop = true;
        do {
            System.out.println("You are now on your street, standing on a street next to a grate.");
            switch (super.chooseMenuItem("Do Something")) {
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
                    if (kbio.YNRequestInput("Do you want to exit the street?")) exitAdventure = true;
                    break;
            }
            if (exitAdventure) loop = false;
        } while (loop);

    }
    void goUp() {
        if (checkGrate()) {
            System.out.println("You landed on the grate.");
            this.enter();
        } else {
            System.out.println("You fell through an open grate. You just lost a life.");
            super.removeLife();
            System.out.println("Try entering " + super.getRoom(1).getName());
            super.getRoom(1).enter();   // Enter the main hall.
        }
    }
    void goDown() {
        if (checkGrate()) {
            System.out.println("You are standing on the grate.");
            this.enter();
        } else {
            System.out.println("You jumped through an open grate. Enjoy your adventures.");
            super.getRoom(1).enter();   // Enter the main hall.
        }
    }
    void goNorth() {
        System.out.println("You just entered a brier patch");
        super.rollDice(7);
        super.getRoom(7).enter();
    }
    void goSouth() {
        System.out.println("Yuo bumped into a good friend, you gained a life");
        super.addLife();
    }
    void goEast() {
        System.out.println("You are entering a great hotel");
        super.getRoom(2).enter();        // Enter the Hotel.
    }
    void goWest() {
        System.out.println("You reached the end of the street and found a friend selling rubber chickens.");
        if (kbio.YNRequestInput("Do you want to buy a rubber chicken?")) {
            System.out.println("You must roll a 9 to buy the checken");
            super.rollDice(9);
            super.addRubberChecken();
        }
    }
    // Allow for some action to occur. And control it.
    void doAction() {
        if (this.checkGrate()) {
            if (kbio.YNRequestInput("Do you want to remove the grate")) this.pickUpItem();
        }

    }
}
