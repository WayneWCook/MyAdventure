/* Hotel.java : Finding a place to stay and eat.
 * Author: Wayne Cook
 * You have found a place just off of your street to find food and a place to sleep.
 *
 * Creation date: 24 April: The adventure has to start somewhere and this is it.
 */
import java.util.ArrayList;
import java.util.Random;

class Hotel extends ModelRoom {

    // Create instanes of needed classes.
    private ArrayList<String> localWeapons = new ArrayList<>();
    private Random random = new Random();

    // Attributes
    final static private String name = "The Hotel";
    final static private String sword = "Japanese Samurai Sword";

    // Constructor
    Hotel() {
        localWeapons.add(sword);
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
            switch (super.chooseMenuItem("Look! Candy!!")) {
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
        System.out.println("It is dark at the top of the stairs, but you think you see someone at the top.");
        if (kbio.YNRequestInput("Do you want to go up and see who is there?")) {
            System.out.println("The man says: \"There is nothing to see up here.\"");
            if (kbio.requestInput("Do you have anything to say?").contains("you")) {
                String results = kbio.requestInput("I just moved in from overseas and I am meeting new people.");
                if (results.contains("Japan")) {
                    if (kbio.YNRequestInput("Thank you for being so nice. do ou like Samurai swords?")) {
                        if (kbio.YNRequestInput("Would you like one?")) this.acceptSword();
                    }
                }
            }
        }
    }
    void goDown() {
        System.out.println("Man, is it dark down there.");
        if (kbio.YNRequestInput("Are you sure you want to go down?")) super.getRoom(3).enter();  // Enter the lair.
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
        System.out.println("You are at the front desk.");
        if (kbio.YNRequestInput("Do you want a room for the night?")) {
            super.rollDice(11);
            System.out.println("Your reset has increase your health.");
            super.addHealth();
        }
    }
    void goEast() {
        System.out.println("I smell the ocean.");
        if (kbio.YNRequestInput("Do you want to go outside?")) super.getRoom(4).enter();
    }
    void goWest() {
        System.out.println("Ah Sunshine");
        super.getRoom(0).enter();        // Enter the Hotel.
    }
    private void acceptSword() {
        if (localWeapons.size() > 0) {
            super.addWeapon(localWeapons.get(0));
            localWeapons.remove(0);
            if (kbio.requestInput("This has been in my family for several generation. Take good care of it").contains("Thank you")) {
                addHealth();
            } else {
                removeHealth();
            }
        }
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
