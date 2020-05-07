/* Ship.java : Is this the start of another great adventure.
 * Author: Wayne & Matthew Cook
 * There will be a ship great adventure attached to this eventually.
 * It is required to hav the directional methods that all other rooms have, but they do nothing and
 * should never be called.
 *
 * Creation date: 26 April: The adventure just might start at sea.
 */
import java.util.Random;

class Ship extends ModelRoom {
    //Atributes
    final static private String name = "Ship";
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
            System.out.println("You are now in " + this.getName() + ".");
            switch (super.chooseMenuItem("Ah the Candy Store")) {
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
        System.out.println("You are now on the ships observation deck.");
        do {
            if (shipIn == shipDocked) {
                System.out.println("the ship has docked.");
                loop = false;
            }
            else {
                System.out.println("The ship is currently out at sea");
                if (kbio.YNRequestInput("Do you want to wait for awhile to see if the ship docks?")) {
                    shipIn = random.nextInt(10);
                    loop = true;
                } else loop = false;
            }
        } while (loop);
    }
    void goDown() {
        System.out.println("Thank you for visiting the ship's engine room.");
    }
    void goNorth() {
        if (shipIn == shipDocked) {
            System.out.println("Thank you for sailing!!");
            super.getRoom(4).enter();
            shipIn = 0;
        } else {
            if (kbio.YNRequestInput("There is no dock here. Are you sure you want to proceed?")) {
                removeHealth();
                System.out.println("You just decided to take a swim, but were rescued." +
                "\nIf you have Life Savers, they just might help you here.");
                String item = grabAnItem();
                if (item.equals("Life Savers")) {
                    System.out.println("You were able to swim back to the dock and you improved your health");
                    addHealth();
                    super.getRoom(4).enter();
                } else {
                    System.out.println("You are rescued, but lose some health.");
                    removeHealth();
                }
            }
        }
    }
    void goSouth() {
        System.out.println("You are in the galley and stuff yourself. You gain a life for food, but lose one health.");
        addLife();
        removeHealth();
    }
    void goEast() {
        System.out.println("It's all ocean as far as you can sea");
    if (kbio.YNRequestInput("Would you like to jump in?")){
        System.out.println("You just woke up on a faraway island");
        removeHealth();
        super.getRoom(7).enter();
        }
    }
    void goWest() {}
    void doAction() {
        System.out.println("You are at the candy counter.");
        int index = random.nextInt(Items.candyBar.length);
        if (kbio.YNRequestInput("Do you want to buy a " + Items.candyBar[index])) {
            super.addCandyBar(Items.candyBar[index]);
        }

    }
}