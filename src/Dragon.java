/* Dragon.java : Finding a dragon's lair
 * Author: Wayne Cook
 * You have found a place just below the Hotel and off of the main hall. Be careful of the dragon.
 *
 * Creation date: 24 April: The adventure has to start somewhere and this is it.
 */
import java.util.ArrayList;

public class Dragon extends ModelRoom {

    // Attributes
    private ArrayList<String> localTreasures = new ArrayList<>();
    private ArrayList<String> localBeings= new ArrayList<>();
    final static private String name = "The Dragon's lair";
    final static private String dragon = "Dragon";
    final static private String flashlight = "Flashlight";
    // Constructor
    public Dragon() {
        localBeings.add(dragon);
        localTreasures.add(flashlight);
    }

    // Methods
    private void pickUpItem() {
            if (localTreasures.size() > 0) {
                super.addTreasure(localBeings.get(0));
                localTreasures.remove(0);
            }
    }
    private void pickUpDragon() {
        if (localBeings.size() > 0) {
            super.addFriend(localBeings.get(0));
            localBeings.remove(0);
        }
    }

    String getName() {
        return this.name;
    }

    void enter() {
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
                    this.doAction();
            }
        } while (true);
    }
    void goUp() {
        System.out.println("You found the stairs.");
        if (kbio.YNRequestInput("Are you sure ou want to go up?")) super.getRoom(2).enter();
    }
    void goDown() {
        System.out.println("There is nothing here.");
    }
    void goNorth() {
        System.out.println("There is nothing here.");
    }
    void goSouth() {
        System.out.println("There is nothing here.");
    }
    void goEast() {
        System.out.println("There is nothing here.");
    }
    void goWest() {
        System.out.println("It sure looks bright out there.");
        super.getRoom(1).enter();
    }
    void doAction() {
        String onFloor = "nothing";
        if (localTreasures.size() > 0) onFloor = "a " + localTreasures.get(0);
        System.out.println("There is " + onFloor + " here.");
        if (onFloor != "nothing") {
            if (kbio.YNRequestInput("Do you want to pick up " + onFloor + "?")) this.pickUpItem();
        }
    }

}

