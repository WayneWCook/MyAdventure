/* Main.java : The start of the great adventure
 * Author: Wayne Cook
 * This starts the whole adventure. It needs very little code and just calls the starting sequence.
 *
 * Creation date: 23 April: The adventure has to start somewhere and this is it.
 */
public class Main {
    public static void main(String[] args) {
        KBIO kbio = new KBIO();
        StartAdventure startAdventure = new StartAdventure();
        boolean entry = kbio.YNRequestInput("You are standing on a street corner. Do you want a great adventure?");
        if (entry)  startAdventure.enter();
        System.out.println("We hope you enjoyed your adventure. Come back soon!");
    }
}
