/* MainHall.java : The first room of the great adventure
 * Author: Wayne Cook
 * You enter here from the street and you can go anywhere that is created by our team.
 *
 * Creation date: 23 April: The adventure has to start somewhere and this is it.
 */import java.util.ArrayList;

public class MainHall extends ModelRoom {

    // Attributes
    private ArrayList<String> localWeapons = new ArrayList<>();
    final static private String name = "The Main Hall";
    // Constructor
    public MainHall() {
        localWeapons.add("Swiss Army Knife");
    }

    // Methods
    private void pickUpItem() {
        if (localWeapons.size() > 0) {
            super.addWeapon(localWeapons.get(0));
            localWeapons.remove(0);
        }
    }

    String getName() {
        return name;
    }

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
                    this.doAction();
                case 8:
                default:
                    loop = false;}
        } while (loop);
    }
    void goUp() {
        super.getRoom(0).enter();
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
         System.out.println("It is dark and noisy in there");
         if (kbio.YNRequestInput("Are you sure you want to go?")) super.getRoom(3).enter();
     }
     void goWest() {
         System.out.println("There is nothing here.");
     }
     void doAction() {
        String onFloor = "nother";
        if (localWeapons.size() > 0) onFloor = "a " + localWeapons.get(0);
         System.out.println("There is " + onFloor + " here.");
        if (!onFloor.equals("nothing")) {
            char r = kbio.requestInput(("Do you want to pick up " + onFloor + "? (Y/N)")).toUpperCase().toCharArray()[0];
            if (r =='Y') this.pickUpItem();
        }
     }

}
