/* Trogdor.java : The base room of the great adventure
 * Author: Wayne Cook
 * Every story nneds a true and constant villain. This is that villain. Beware. If it ever shows
 * up in your room, you had better defeat it quickly.
 *
 * Creation date: 23 April: The adventure has to start somewhere and this is it.
 */

public class Trogdor {
    // attributes
    private int health = 5;
    private int room = 4;
    // Included classes
    KBIO kbio = new KBIO();
    Items items = new Items();
    // methods
    public int addHealth() {
        return ++health;
    }

    public int removeHealth() {
        if ( --health == 0) {
            System.out.println("It is with tremendous grief that I announce the passing of Trogdor");
        }
        return health;
    }

    // Returns 0 if no health lost, -1 for Trogdor gaining health from you, and +1 for Trogdor losing health.
    public int checkSameRoom(int userRoom) {
        int retVal = 0;
        if (room == userRoom) {
            System.out.print("Hi, I am Tragdor, your worst nightmare." +
                    "\nWe are in the same room. Who do you have with you? ");
            if (kbio.requestInput("Who is your friend? ").contains(items.friends[0])) {
                System.out.println("You are making it tough today! It appears that I cannot destroy you");
                retVal = 1;
            } else {
                System.out.println("I love taking peoples health, so I think I will. Thank you, it was nice meeting with you.");
                retVal = -1;
            }
        }
        return retVal;
    }

    // Tragdor changes rooms and is always ready for a good fight.
    public int changeRooms(int room, int userRoom) {
        int retVal = checkSameRoom(userRoom);
        if (retVal != 0) {
            this.room = room;
            retVal = checkSameRoom(userRoom);
        }
        return retVal;
    }
}
