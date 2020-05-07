/* Ship.java : Is this the start of another great adventure.
 * Author: Wayne & Matthew Cook
 * There will be a ship great adventure attached to this eventually.
 * It is required to hav the directional methods that all other rooms have, but they do nothing and
 * should never be called.
 *
 * Creation date: 26 April: The adventure just might start at sea.
 */
import java.util.Random;

class BaseballField extends ModelRoom {
    //Atributes
    final static private String name = "Baseball Field";
    final static private int shipDocked = 4;
    static private int shipIn = 0;
    private Random random = new Random();
    // Methods
    String getName() {
        return name;
    }
    void enter() {
        boolean loop = true;
        do {
            System.out.println("Welcome to the " + this.getName() + ". Be aware of what is around you");
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
        System.out.println("You bumped into the ceiling.");
    }
    void goDown() {
        System.out.println("Nothing but floor!");
    }
    void goNorth() { System.out.println("Nothing but floor and dragon treasures");     }
    void goSouth() {
        System.out.println("You just entered a brier patch");
        super.rollDice(7);
        super.getRoom(0).enter();
    }
    void goEast() { System.out.println("Nothing but wall."); }
    void goWest() { System.out.println("Nothing but wall."); }
    void doAction() {
        System.out.println("Explore the floor.");
    }
}