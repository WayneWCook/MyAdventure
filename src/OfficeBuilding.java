/* OfficeBuilding.java : Using a tall building for fun.
 * Author: Wayne Cook
 * You have found a place just off of your street to find food and a place to sleep.
 *
 * Creation date: 24 April: The adventure has to start somewhere and this is it.
 */
import java.nio.channels.UnsupportedAddressTypeException;
import java.util.ArrayList;
import java.util.Random;

class OfficeBuilding extends ModelRoom {

    // Create instances of needed classes.
    private ArrayList<String> localWeapons = new ArrayList<>();
    private Random random = new Random();
    final static private int maxFloors = 8;
    static private int floor  = 1;

    // Attributes
    final static private String name = "The Office";

    // Constructor
    OfficeBuilding() {

    }

    // Methods
    String getName() {
        return name;
    }

    void enter() {
        boolean loop = true;
        do {
            System.out.println("You are now in " + this.getName() + ". You are on floor " + floor);

            switch (super.chooseMenuItem("A vending Machine!!")) {
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

    // Choose elevator or stairs
    void takeElevator() {
        if (kbio.YNRequestInput("Do you want to take the elveator?")) removeHealth();
        else {
            System.out.println("good, you are taking the stairs. That is healthy.");
            addHealth();
        }
    }

    void goUp() {
        if (floor == maxFloors) System.out.print("You are already on the roof, you cannot go any higher.");
        else {
            takeElevator();
            floor++;
        }
    }

    void goDown() {
        if (floor == 0) System.out.print("You are already,in the basement, you cannot go any lower.");
        else {
            takeElevator();
            floor--;
        }
    }

    void goNorth() {
        System.out.println("Welcome to the kitchen.");
        if (kbio.YNRequestInput("The chef loves " + super.rubChickName() + "s. Do you want to trade one for a meal?")) {
            if (super.removeRubberChecken()) {
                super.addLife();                        // Food gives you another life
                System.out.println("Food gives you another life.");
                boolean results = kbio.requestInput("Did you enjoy your meal?").toLowerCase().contains("thank you");
                if (results) {
                    super.addHealth();
                    System.out.println("Being polite, gives you another health");
                } else {
                    super.removeHealth();
                    System.out.println("I like hearing a polite \"Thank you!\" You just lost a health.");
                }
            }
        }
    }
    void goSouth() {
        if (floor == 1) {
            System.out.println("thank you for visiting the building.");
            getRoom(8).enter();
        } else if (floor == maxFloors) {
            System.out.println("You get to take the slide back to the baseball filed. Have Joy!");
            super.addFriend(Items.friends[3]);
            do {
                System.out.print("Wheeee! ");
            } while (super.rollDie() != 5);
            getRoom(8).enter();
        } else if (floor == 0) {
            System.out.println("You are looking at a solid cement wall.");
        } else System.out.println("You are looking over the baseball field.");
    }

    void goEast() {
        if (floor == 0) System.out.println("You are looking at a wall");
        else System.out.println("You are looking out a window.");
    }

    void goWest() {
        if (floor == 0) System.out.println("You are looking at a wall");
        else System.out.println("You are looking out a window.");
    }

    // Now take care of the doAction() command.
    void doAction() {
        System.out.println("You are at the candy counter.");
        int index = random.nextInt(Items.candyBar.length);
        if (kbio.YNRequestInput("Do you want to buy a " + Items.candyBar[index])) {
            super.addCandyBar(Items.candyBar[index]);
        }
    }

}

