/* Dock.java : Is this the start of another great adventure.
 * Author: Wayne & Matthew Cook
 * There will be a ship great adventure attached to this eventually.
 * It is required to hav the directional methods that all other rooms have, but they do nothing and
 * should never be called.
 *
 * Creation date: 23 April: The adventure just might start at sea.
 */
import java.util.Random;

class Dock extends ModelRoom {
    //Atributes
    final static private String name = "Dock";
    final static private int shipDocked = 4;
    private Random random = new Random();
    // Methods
    String getName() {
        return name;
    }
    int shipIn = 0;
    void enter() {
        boolean loop = true;
        do {
            System.out.println("You are now in " + this.getName() + ".");
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
                    break;
            }
            if (exitAdventure) loop = false;
        } while (loop);
    }
    void goUp() {
        boolean loop;
        System.out.println("You are on the dock's observation deck;");
        do {
            if (shipIn == shipDocked) {
                System.out.println("the ship is in.");
                loop = false;
            }
            else {
                System.out.println("The ship is currently out at sea");
                if (kbio.YNRequestInput("Do you want to wait for awhile to see if the ship docks?")) {
                    shipIn = random.nextInt(7);
                    loop = true;
                } else loop = false;
            }
        } while (loop);
    }
    void goDown() {}
    void goNorth() {}
    void goSouth() {
        if (shipIn == shipDocked) {
            System.out.println("Bon voyage!");
            super.getRoom(5).enter();
            shipIn = 0;
        } else {
            if (kbio.YNRequestInput("There is no ship here. Are you sure you want to proceed?")) {
                removeHealth();
                System.out.println("You just decided to take a swim, but were rescued. You lose one health.");
            }
        }
    }
    void goEast() {}
    void goWest() {
        super.getRoom(2).enter();
    }
    void doAction() {}
}