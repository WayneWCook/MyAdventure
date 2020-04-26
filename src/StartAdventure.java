/* StartAdventure.java : The start of the great adventure
 * Author: Wayne Cook
 * To connect to the other rooms, this is also a ModelRoom subclass.
 * It is required to have the directional methods that all other rooms have, but they do nothing and
 * should never be called.
 *
 * Creation date: 23 April: The adventure has to start somewhere and this is it.
 */public class StartAdventure extends ModelRoom {
    //Atributes
    final static private String name = "In the Beginning";
    // Methods
    String getName() {
        return name;
    }
    void enter() {
        System.out.println("On the street again!");
        super.firstCall();
        ModelRoom start = super.getRoom(0);
        start.enter();          // Street should always be the first item in the index.
        System.out.println("Thank you for joing the adventure. Your final inventory is:");
        super.printStatus();
    }
    void goUp() {}
    void goDown() {}
    void goNorth() {}
    void goSouth() {}
    void goEast() {}
    void goWest() {}
    void doAction() {}
}
