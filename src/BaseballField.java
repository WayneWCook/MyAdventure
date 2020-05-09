/* Ship.java : Is this the start of another great adventure.
 * Author: Wayne & Matthew Cook
 * There will be a ship great adventure attached to this eventually.
 * It is required to hav the directional methods that all other rooms have, but they do nothing and
 * should never be called.
 *
 * Creation date: 26 April: The adventure just might start at sea.
 */
import java.util.Random;

class BaseballField extends ModelRoom {
    //Atributes
    final static private String name = "Baseball Field";
    final static private int shipDocked = 4;
    static private int shipIn = 0;
    private Random random = new Random();
    // Baseball game statistics
    private int[] bases = {0,0,0};
    private int runs = 0, balls = 0, strikes = 0, outs = 0, atBat;
    private boolean baserunners = false;

    // Methods
    String getName() {
        return name;
    }
    void enter() {
        boolean loop = true;
        do {
            System.out.println("Welcome to the " + this.getName() + ". Be aware of what is around you");
            switch (super.chooseMenuItem("Do you want to play a baseball game?")) {
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
        System.out.println("You bumped into the ceiling.");
    }
    void goDown() {
        System.out.println("Nothing but floor!");
    }
    void goNorth() {
        System.out.println("What a beautiful Office Building with an interesting slide.");
        super.getRoom(10).enter();
    }
    void goSouth() {
        System.out.println("You just entered a brier patch");
        super.rollDice(7);
        super.getRoom(0).enter();
    }
    void goEast() {
        System.out.println("You came across a cute squirrel in the park");
        try{
            int answer = Integer.parseInt(kbio.requestInput("Want to:\n1. Kick him\n2. Pet him\n3. Feed him" +
                    "\n4. Hold him.\nChoose"));
            switch (answer) {
                case 1:
                    System.out.println("Squirrels do not like to be kicked, you lose a life and go back to the street.");
                    removeLife();
                    getRoom(0).enter();
                    break;
                case 2:
                    System.out.println("the squirrel got nervous and peed on you. Not very healthy.");
                    removeLife();
                    break;
                case 3:
                    System.out.println("The squirrel is hungry, what do you want to feed him?");
                    String food = super.grabAnItem();
                    if (food.equals(Items.candyBar[15])) {
                        System.out.println("You are my friend forever");
                        addFriend(Items.friends[1]);
                    }
                    if (food.equals(Items.candyBar[14])) {
                        System.out.println(Items.friends[2] + " loves " + Items.candyBar[14]);
                        addFriend(Items.friends[2]);
                    }
                     break;
                case 4:
                    System.out.println("the squirrel has rabies, he bit you, you died, good by");
                    removeLife();
                    getRoom(0).enter();
                    break;
                default:
                    System.out.println("Choose wisely.");
                    removeHealth();
                    break;
            }

        } catch (Exception ex)  {
            System.out.println("You lost your chance and some health.");
            removeHealth();
        }
    }
    void goWest() { System.out.println("Nothing but wall."); }

    // the baseball game.
    void advanceRunners() {
        int carry = 0;
        carry = bases[0];
        bases[0] = 1;
        if (carry == 1) {
            carry = bases[1];
            bases[1] = 1;
        }
        if ( carry == 1) {
            carry = bases[2];
            bases[2] = 1;
        }
        if (carry == 1) runs++;
    }

    // Clear balls and strikes.
    void clearBallsStrikes() {
        balls = 0;
        strikes = 0;
    }
    void doAction() {
        if (kbio.YNRequestInput("Do you want to play a game of baseball?")) {
            System.out.println("You get to be our designated hitter. Try to get a run home to win a trophy.");
           do {
               kbio.requestInput("You get to bat again");
                switch (super.rollDie()) {
                    case 2:
                        System.out.println("You just hit an home run and won the game");
                        runs++;
                        clearBallsStrikes();
                        break;
                    case 3:
                        System.out.print("You just hit a triple.");
                        if (baserunners) {
                            for (int base : bases) {
                                if (base == 1) runs++;
                            }
                            bases[0] = 0;
                            bases[1] = 0;
                            bases[2] = 1;
                        }
                        clearBallsStrikes();
                        baserunners = true;
                    case 4:
                        System.out.print("You just hit a dobule.");
                        if (baserunners) {
                            for (int base : bases) {
                                if (base == 1) runs++;
                            }
                            bases[0] = 0;
                            bases[1] = 1;
                            bases[2] = 0;
                        }
                        clearBallsStrikes();
                        baserunners = true;
                        break;
                    case 5:
                        System.out.print("You just hit a single.");
                        advanceRunners();
                        clearBallsStrikes();
                        baserunners = true;
                        break;
                    case 6:
                        System.out.println("You just hit a fall ball.");
                        if (strikes < 3) strikes++;
                        break;
                    case 7:
                        System.out.println("Called ball");
                        balls++;
                        if (balls == 4) {
                            System.out.println("You walked.");
                            advanceRunners();
                            clearBallsStrikes();
                        }
                        break;
                    case 8:
                        System.out.println("You just received a called strike.");
                        strikes++;
                        if (strikes == 3) {
                            outs++;
                            clearBallsStrikes();
                        }
                        break;
                    case 9:
                        System.out.println("You flied out to the outfield");
                        if (bases[2] == 1) runs++;
                        outs++;
                        clearBallsStrikes();
                        break;
                    case 10:
                        System.out.println("You grounded out, any runners advance one base.");
                        if (bases[2] == 1) runs++;
                        else if (bases[1] == 1) {
                            bases[1] = 0;
                            bases[2] = 1;
                        } else if (bases[0] == 1) {
                            bases[0] = 0;
                            bases[1] = 1;
                        }
                        outs++;
                        clearBallsStrikes();
                        break;
                    case 11:
                        System.out.println("You were hit by a pitch, lose a health and take a base.");
                        removeHealth();
                        advanceRunners();
                        baserunners = true;
                        break;
                    case 12:
                        System.out.println("You hit a home run and won the game!");
                        runs++;
                        clearBallsStrikes();
                        break;
                    default:
                        break;
                }
                System.out.println("You have " + outs + " outs, " + balls + " balls, and " + strikes + " strikes.");
                if (baserunners) {
                    System.out.print("You have base runners on");
                    if (bases[0] == 1) System.out.print(" first");
                    if (bases[1] == 1) System.out.print(" second");
                    if (bases[2] == 1) System.out.print(" third");
                    System.out.println(".");
                }
           } while ((outs < 3) && (runs == 0));
        }
        if (runs > 0) {
            System.out.println("You won the game and earned a " + Items.treasures[4]);
            addTreasure(Items.treasures[4]);
        } else System.out.println("Thank you for playing the baseball game.");
    }
}