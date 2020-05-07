import java.util.ArrayList;

class Island extends ModelRoom {

    // Attributes
    private ArrayList<String> localWeapons = new ArrayList<>();
    final static private String name = "Island";
    // Constructor
    Island() {
        localWeapons.add("Pokeball");
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
                default:
                    this.doAction();
                    break;
            }
            if (exitAdventure) loop = false;
        } while (loop);
    }
    void goUp() {
        System.out.println("You jumped");
    }
    void goDown() {
        System.out.println("There is nothing but sand here.");
        if (kbio.YNRequestInput("Do you want to dig?")){
            System.out.println("You found a chest!");
        }
    }
    void goNorth() {
        if (kbio.YNRequestInput("Would you like to return to the Ship?")) super.getRoom(5).enter();
    }
    void goSouth() {

    }
    void goEast() {
        System.out.println("There is nothing here.");
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
