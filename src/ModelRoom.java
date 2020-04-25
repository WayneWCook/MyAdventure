/* ModelRoom.java : The base room of the great adventure
 * Author: Wayne Cook
 * This is an abstract class. It cannot be used to create an instance. It is what all other rooms extend so
 * that each room has common methods.
 *
 * Creation date: 23 April: The adventure has to start somewhere and this is it.
 */
import java.util.ArrayList;
import java.util.Random;

public abstract class ModelRoom {
    // Attributes - we want one copy for these attributes and not one copy for every room created.
    private static int lives = 3;
    private static int health = 3;
    private static ArrayList<String> treasures = new ArrayList<>();
    private static ArrayList<String> weapons= new ArrayList<>();
    private static ArrayList<String> friends = new ArrayList<>();
    private static ArrayList<ModelRoom> allRooms = new ArrayList<ModelRoom>();
    final static private String rubberChecken = "Rubber Chicken";
    final static private String nothing = "Nothing";

    KBIO kbio = new KBIO();

    // Methods

    void startOver() {
        int i;
        lives =3;
        int tresSize = treasures.size();
        int wepSize = weapons.size();
        int freSize = friends.size();
        // Check first to see if there are any elements, then go through backwards to remove.
        if (tresSize > 0) {
            for (i = tresSize - 1; i >= 0; i-- ) {
                treasures.remove(i);
            }
        }
        // Check first to see if there are any elements, then go through backwards to remove.
        if (wepSize > 0) {
            for (i = wepSize - 1; i >= 0; i-- ) {
                weapons.remove(i);
            }
        }
        // Check first to see if there are any elements, then go through backwards to remove.
        if (freSize > 0) {
            for (i = freSize - 1; i >= 0; i-- ) {
                friends.remove(i);
            }
        }
        this.addRubberChecken();
    }

    // To be consistent, provide a method to print the Rubber Chicken name.
    String rubChickName() {
        return this.rubberChecken;
    }
    // Rubbeer chickens will become an important part of this game, so be able to add and remover them
    void addRubberChecken() {
        treasures.add(rubberChecken);
    }
    boolean removeRubberChecken() {
        boolean retVal = false;
        if (treasures.contains(this.rubChickName())) {
            treasures.remove(rubberChecken);
            retVal = true;
        }
        return retVal;
    }

    // Constructor is now just using the default method. We only want to do the initialization once.
    public void firstCall() {
        this.startOver();
        this.createRooms();
    }

    // Have a common menu

    // Go through needed directions
    abstract String getName();
    abstract void enter();
    abstract void goUp();
    abstract void goDown();
    abstract void goNorth();
    abstract void goSouth();
    abstract void goEast();
    abstract void goWest();
    abstract void doAction();

    // Tale care pf accessomg attrobites
    void addTreasure(String item) {
        treasures.add(item);
    }
    void addWeapon(String item) {
        weapons.add(item);
    }
    void addFriend(String item) {
        friends.add(item);
    }
    void addLife() {
        lives++;
    }
    void addHealth() {
        health++;
    }

    // Tale care pf renicvubg attrobites
    void removeTreasure(String item) {
        treasures.remove(item);
    }
    void removeWeapon(String item) {
        weapons.remove(item);
    }
    void removeFriend(String item) {
        friends.remove(item);
    }
    void removeLife() {
        lives--;
        if (lives < 0) System.out.println("You just died. Too bad!");
    }

    void removeHealth() {
        health--;
    }


    /* Now create the rooms
    * createRooms is called once on initialization of this class.  The purpose is to create each room once.
    * All other references to the instance of each room class will be to the same instance, so all the information
    * about each room is stored in only one location.
    * ---------------------------------------------------
    * First create an instance of your room and then store that reference in the allRooms ArrayList.
    */
    protected void createRooms() {
        Street street = new Street();
        allRooms.add(street);                                   // Room 0-Street
        MainHall mainHall = new MainHall();
        allRooms.add(mainHall);                                 // Room 1-Main Hall
        Hotel hotel = new Hotel();
        allRooms.add(hotel);                                    // Room 2-Hotel
        Dragon dragon = new Dragon();
        allRooms.add(dragon);                                   // Room 3-Dragon's Lare
        System.out.println("You have the following rooms");
        for (int i = 0; i < allRooms.size(); i++) {
            System.out.println("Room " + i + " is " + allRooms.get(i).getName());
        }
    }

    // Get the pointer to a created room
    ModelRoom getRoom(int index) {
        return allRooms.get(index);
    }

    /* chooseMenuItem(String) - Give a standard Menu
     * This makes all choices in each room look the same. Consistency is important in a good game.
     * I could implement this in one of two ways.
     * 1. Pass in a "this" from the calling room, so that I could use the standard entries (goUp,
     *    goDown,... and call those directly from this method.
     *    Advantage: Less code, easier for the room developers to implement.
     *    Disadvantage - Every call adds at least a return address to the stack. The stack would
     *       continue to grow with each room visited. This would cause the use of more memory and
     *       if a person played long enough could cause the system to slow down.
     * 2. Just print the menu here and return to the calling room to implement the calls.
     *    Advantage  - Cleans up the stack for each use of the menu.
     *    Disadvantage - Every room change will still add to the stack, but every menu call will
     *       not add to the stack. There will still be stack growth, but not as much.
     * Tradeoff - more coding and slower stack growth vs rapid stack growth
     * Decision - I went with option 2 to slow the growth of the stack.
     */
    int chooseMenuItem(String doWhat) {
        int retVal = 0;
        boolean endCheck = true;
        do {
                String input = "Choose from the following" +
                        "\n1: Go Up" +
                        "\n2. Go down" +
                        "\n3: Go North" +
                        "\n4. Go South" +
                        "\n5: Go East" +
                        "\n6. Go West" +
                        "\n7. " + doWhat +
                        "\n8. Status" +
                        "\nChoose";
            try {
                retVal = Integer.parseInt(kbio.requestInput(input));
                if ((retVal < 1) || (retVal > 8) ) throw  new Exception("Not Integer");
                else if ( retVal == 8) printStatus();
                else endCheck = false;
            } catch (Exception e) {
                System.out.println("Please enter an integer between 1 and 8");
            }
        } while (endCheck);
        return  retVal;
    }

    // Try to randomize events
    void rollDice(int results) {
        int die1, die2, total;
        Random random = new Random();
        boolean contLoop = true;
        do {
            kbio.requestInput("Press enter to roll the dice and see if you can roll a total of " + results);
            die1 = random.nextInt(6)+ 1;
            die2 = random.nextInt(6)+ 1;
            total = die1 + die2;
            System.out.println("You rolled a " + die1 + " and a " + die2 +" for a total of " + total);
            if (total == results) contLoop = false;
        } while (contLoop);
    }

    // Print an ArrayLis<String>
    void printList(String identifier, ArrayList<String> list) {
        if (list.size() > 0) {
            int count = 0;
            for (String elem : list) {
                System.out.println(identifier + " " + count++ + " is " + elem + ".");
            }
        } else System.out.println("You have no " + identifier + ".");
    }

    // Let the user know what they have
    void printStatus() {
        System.out.println("You have " + lives + " lives.");
        printList("Treasures", treasures);
        printList("Weapons", weapons);
        printList("Friends", friends);
    }

    // Now for grabbing an item.
    private String grabSelectedItem(String identifier, ArrayList<String> list) {
        String retVal = nothing;
        int index = 0, size = list.size();
        if  (size > 0) {
            if (kbio.YNRequestInput("Do you want to choose one of your " + identifier + "?")) {
                System.out.println("Select from the following or type " + size + " for none:");
                printList("Treasures", treasures);
                boolean loop = true;
                do {
                    try {
                        index = Integer.parseInt(kbio.requestInput("Type the number of the one you want."));
                        loop = false;
                    } catch (Exception ex) {
                        System.out.println("You must enter an integer");
                        loop = true;
                    }
                    if ((index > -0) && (index < list.size())) {
                        retVal = list.get(index);
                        list.remove(index);
                    }
                } while (loop);
            }
        }
        return retVal;
    }

    // Now for the actual chose
    String grabAnItem() {
        System.out.println("Welcome to selecting an item from your stash");
        String retVal = nothing;
        retVal = grabSelectedItem("Treasures", treasures);
        retVal = grabSelectedItem("Weapons", weapons);
        retVal = grabSelectedItem("Friends", friends);
        return retVal;
    }
}
