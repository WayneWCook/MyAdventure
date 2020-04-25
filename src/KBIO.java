/* KBIO.java
 * Wayne Cook
 * 1 November 2018
 * Purpose:
 *   This file contains sample code for reading from and writing to a file. It has one private piece of
 *   information that must be accessed through methods that will set and retrieve (get) the information.
 *   First the java.io.* libraries need to be important.
 *   Modified: 21 April 2020 - Add to sample IO program.
 */
import java.io.*;
import java.util.Scanner;

public class KBIO {
    /* Define needed members as private, so that they can only be changed via defined methods. */
    private Scanner input = new Scanner(System.in);             // Set it up so that it is ready to use.

    /* This is a generic input routine that reads one string of characters, tacks on a (Y/N)
     * It returns a True for any string start with Y and a False for an string starting with
     * any other letter */
    public boolean YNRequestInput(String instructs) {
        boolean retVal = false;
        char results = this.requestInput( instructs+ " (y/n)").toUpperCase().toCharArray()[0];
        if (results == 'Y') retVal = true;
        return retVal;
    }
    /* This is a generic input routine that reads one string of characters */
    public String requestInput(String instructs) {
        String retVal;
        System.out.print(instructs + ": ");                     // Print out the instructions.
        return getLine();
    }

    /* Have common named routines for file I/O and user interface (keyboard and display) I/O */
    public String getLine() {
        String retVal = input.nextLine();
        return retVal;
    }

}