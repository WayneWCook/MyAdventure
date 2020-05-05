/* FileIO.java : Defines the entry point for the console application.
 * Author: Wayne Cook
 * This module takes care of all reading and writing to file duties. It is best to just reuse a module when you have
 * ir working correctly.
 *
 * Creation date: 6 March 2018 - Initial design implemented.
 * Modified: 7 March 2018 - added check for already requested seat.
 * Modified: 7 March 2018 - allowed user to choose the number of rows.
 *                          Corrected row/index mismatch on seat selection.
 * Modified: 8 March 2018 - Added matrix multiplication
 * Modified: 15 November 2018 - Update for CSC160 (Java) class. Divided into appropriate Classes.
 * Modified: 21 April 2020 for current Java class to add appending to file.
 */

/* Old SUN imports.
import com.sun.istack.internal.localization.NullLocalizable;
import org.omg.CORBA.SystemException;
*/

//import com.sun.xml.internal.ws.policy.privateutil.PolicyUtils;

import java.io.*;
import java.util.Scanner;
import java.util.ArrayList;

public class FileIO {
    private String fileName = "adventure.status";
    private File file;
    private Scanner inputFile;
    private FileWriter outputFile;
    private boolean fileExists = false;                     // Flag for whether file exists or not.

    // Constructor - must include the fileName or an empty string to use the default name.
    protected FileIO(String fileName) {
        if (fileName.length() > 0) {
            this.fileName = fileName;                    // Initialize the file name.
        }
        file = new File(this.fileName);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch(IOException ex) {
                System.out.println("file: " + file + " cannot be created.");
            }
        }
        // The file exists, so open it for writing
        try {
            outputFile = new FileWriter(file, true );  // Incase we want to add to the file later.
        }
        catch (FileNotFoundException ex) {
            System.out.println("file: " + file + " dies not exist.");
        }
        catch (IOException ex) {
            System.out.println("file: " + file + " cannot be opened.");
        }
        // Set up file for reading, done in readFile()
    }

    // Write the file from scratch.
    protected boolean writeFile(ArrayList<String> lines, boolean appendFlag) {
        boolean retVal = true;
        // First set up the file to write to it. Erases file at first.
        try {
            outputFile.close(); // Close it before restarting it.
            outputFile = new FileWriter(file, appendFlag );  // Incase we want to add to the file later.
        }
        catch (FileNotFoundException ex) {
            System.out.println("file: " + file + " dies not exist.");
        }
        catch (IOException ex) {
            System.out.println("file: " + file + " cannot be opened.");
        }
        // Now do the writing of the file.
        int numLines = lines.size();
        //outputFile.println( numLines );
        for (int i = 0; i < numLines; i++) {
            try {
                outputFile.write(lines.get(i));
            } catch (IOException ex) {
                System.out.println("Failure to write to the file");
            }
        }
        try {
            outputFile.flush();   // Force the actula write
        }catch (IOException ex) {
            System.out.println("Failure to write to the file");
        }
        return retVal;
    }

    // Append to an existing file
    public boolean appendLine(String line) {
        boolean retVal = true;
        try {
            outputFile.append("\n" + line);
            outputFile.flush();         // Flush the burffer.
        } catch (IOException e) {
            retVal = false;
        }
        return retVal;
    }

    /* ArrayList is the only subject in this file that I have not covered in the CSC160 Java class.
     * Borrowing and modifying the C++ official site for vectors (http://www.cplusplus.com/reference/vector/vector/)
     * ArrayLists are sequence containers representing arrays that can change in size.
     *
     * Just like arrays,  ArrayLists use contiguous storage locations for their elements, which means that
     * their elements can also be accessed using offsets on regular pointers to its elements, and just
     * as efficiently as in arrays. But unlike arrays, their size can change dynamically, with their
     * storage being handled automatically by the container.
     +
     + The reason I am using a vector here is to allow a variable number of Strings to be returned.
     * A line may have different number of Strings seperated by spaces or other delimeters.
     */
    public void splitLine(ArrayList<String> splitArray, String inString, char delim) {
        int len = inString.length();
        int j = 0, k = 0;
        splitArray.clear();
        for (int i = 0; i < len; i++) {
            if (inString.charAt(i) == ',') {
                String elem = inString.substring(j, i);
                //System.out.println("Element " + k++ + " is " + elem);
                splitArray.add(elem);
                j = i + 1;
            }
            if (i == len - 1) {
                String elem = inString.substring(j);
                //System.out.println("Element " + k++ + " is " + elem);
                splitArray.add(elem);
            }
        }
     }

    protected ArrayList<String> readFile() {
        String tempLine;

        ArrayList<String> params = new ArrayList<>();
        // Set up the file.
        try {
            inputFile = new Scanner(file);
            fileExists = true;
        }
        catch (FileNotFoundException ex) {
            System.out.println("file: " + file + " cannot be opened.");
            fileExists = false;
        }
        String inputLine;
        if (fileExists) {
            while (inputFile.hasNextLine()) {
                inputLine = inputFile.nextLine();
                params.add(inputLine);
            }
        }
        // Now return what you have, if no file then return array shoulc be NULL length.
        return(params);                            // Return the array list of Strings, Null if could not open file.
    }
}