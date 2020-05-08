/*
Cole Ballagh
pd.2
5-3-20
 */
import java.util.ArrayList;
import java.util.Random;
class Island extends ModelRoom {

    // Attributes
    Random rand = new Random();
    private ArrayList<String> localWeapons = new ArrayList<>();
    private ArrayList<String> localTreasures = new ArrayList<>();
    final static private String name = "Island";
    // Constructor
    Island() {
        localWeapons.add("Pokeball");
        localTreasures.add("Jewel");
    }

    // Methods
    private void pickUpItem() {                                 // function for picking up the item in this room
        if (localWeapons.size() > 0) {
            super.addWeapon(localWeapons.get(0));
            localWeapons.remove(0);
        }

    }
    private void grabJewel(){                                     // allows for user to pick up the jewel
    if (localTreasures.size() > 0) {
        super.addTreasure(localTreasures.get(0));
        localTreasures.remove(0);
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
                default:
                    this.doAction();
                    break;
            }
            if (exitAdventure) loop = false;
        } while (loop);
    }
    void goUp() {
        int fall = rand.nextInt(5);
        System.out.println("You jumped");
        if (fall==3){
            System.out.println("Your knees are not like they used to be...\nYou just lost one health");
            removeHealth();
        }
    }
    void goDown() {
        int curse = rand.nextInt(3);
        String onFloor = "nother";
        System.out.println("There is nothing but sand here.");
        if (kbio.YNRequestInput("Do you want to dig?")){
            System.out.println("You found a chest!");
        if (kbio.YNRequestInput("Do you want to open it?")){
            System.out.println("You found a Jewel!");

            if (localTreasures.size() > 0) onFloor = "a " + localTreasures.get(0);
            System.out.println("There is " + onFloor + " here.");
            if (!onFloor.equals("nothing")) {
                char r = kbio.requestInput(("Do you want to pick up " + onFloor + "? (Y/N)")).toUpperCase().toCharArray()[0];
                if (r =='Y'){
                    if (curse==0){
                        System.out.println("It was cursed!\nYou lost a life");
                        removeLife();
                    }
                    else {
                        this.grabJewel();
                    }
                }
        }
        }
        }
    }
    void goNorth() {
        if (kbio.YNRequestInput("Would you like to return to the Ship?")) super.getRoom(5).enter();
    }
    void goSouth() {
    super.getRoom(9).enter();
    }
    void goEast() {
        System.out.println("It appears as if something has washed up on the shore");
        if (kbio.YNRequestInput("Do you want to go take a look?")){
            if (kbio.YNRequestInput("It looks like a little pink blob, do you want to pick it up?")){
                System.out.println("It's a jellyfish!\nYou lost one health");
                removeHealth();
            }
        }
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
