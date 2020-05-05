/* StartAdventure.java : The start of the great adventure
 * Author: Wayne Cook
 * To connect to the other rooms, this is also a ModelRoom subclass.
 * It is required to have the directional methods that all other rooms have, but they do nothing and
 * should never be called.
 *
 * Creation date: 23 April: The adventure has to start somewhere and this is it.
 */
import java.io.*;               // Be able to check for existance of file.
import java.nio.file.Files;
import java.util.Calendar;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.lang.String;

class StartAdventure extends ModelRoom {
    // Useful classes
    private FileIO fileIO;
    private File file;                  // Needed for checking the existance of a file.
    private File backup;                // Used to backup file when needed.
    //private Items items = new Items();

    //Atributes
    final static private String name = "In the Beginning";
    static private ArrayList<String> fileContent;

    // Methods
    String getName() {
        return name;
    }

    void enter() {
        int asset;
        super.setUserName(kbio.requestInput("What is your name?"));
        String fileName = super.getUserName() + ".status";
        file = new File(fileName);  // Create the file to check for existance before setting up the FileIO.
        boolean startOver = true;
        if (file.isFile() && kbio.YNRequestInput("Do you want to start with your current scores?"))  {
            fileIO = new FileIO(fileName);
            fileContent = fileIO.readFile();
            // Find lives and set them
            int intEntry = Integer.parseInt(fileContent.get(1));
            if (intEntry < 3) intEntry = 3;
            setLives(intEntry);
            // Find health and set them
            intEntry = Integer.parseInt(fileContent.get(2));
            if (intEntry < 3) intEntry = 3;
            setHealth(intEntry);
            int listIndex = 0, listNumb = 0, check = 0;             // Which list is to be accessed.
            String value;
            try {                                                   // Catch any bad reads of the file.
                for (int k = 3; k < fileContent.size(); k++) {          // Read the file
                    value = fileContent.get(k);
                    if (Items.assets[listNumb].equals(value)) listIndex = listNumb++;
                    else if (listIndex == 0) addTreasure(value);
                    else if (listIndex == 1) addCandyBar(value);
                    else if (listIndex == 2) addWeapon(value);
                    else if (listIndex == 3) addFriend(value);
                }
                startOver = false;
            } catch (Exception ex) {
                System.out.println("The file could not be properly read, so you will have the default values.");
            }
        }
        System.out.println("To the street again!");
        super.firstCall(startOver);
        ModelRoom start = super.getRoom(0);
        start.enter();          // Street should always be the first item in the index.
        System.out.println("Thank you for joing the adventure. Your final inventory is:");
        super.printStatus();
        fileContent = new ArrayList<>();
        fileContent.add(getUserName() + "\n");
        fileContent.add(Integer.toString(getLives()) + "\n");
        fileContent.add(Integer.toString(getHealth()) + "\n");
        fileContent.add(Items.assets[0] + "\n");
        int size = super.getSizeTreasures(), i;
        if (size > 0) {
            for (i = 0; i < size; i++) {
                fileContent.add(getTrresduresAsset(i) + "\n");
            }
        }
        fileContent.add(Items.assets[1] + "\n");
        size = super.getSizerCandyBars();
        if (size > 0) {
            for (i = 0; i < size; i++) {
                fileContent.add(getCandyBarsAsset(i) + "\n");
            }
        }
        fileContent.add(Items.assets[2] + "\n");
        size = super.getSizeWeapons();
        if (size > 0) {
            for (i = 0; i < size; i++) {
                fileContent.add(getWeaponsAsset(i) + "\n");
            }
        }
        fileContent.add(Items.assets[3] + "\n");
        size = super.getSizerFriends();
        if (size > 0) {
            for (i = 0; i < size; i++) {
                fileContent.add(getFriendsAsset(i) + "\n");
            }
        }
        // check tio see if file exists before writing to it.
        String inActiveDate;
        if (file.isFile()) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.DATE, 1);
            Date date = cal.getTime();
            SimpleDateFormat format1 = new SimpleDateFormat("yyyyMMdd");
            try {
                inActiveDate = format1.format(date);
                System.out.println(inActiveDate );
            } catch (Exception e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
                System.out.println("Date cannot be properly formated");
                inActiveDate = "bak";
            }
            int buNum = 0;
            String backupName;
            do {
                backupName = getUserName() + "." + inActiveDate + "." + buNum++;
                backup = new File(backupName);
            } while (backup.isFile());
            fileIO = null;
            try {
                Files.copy(file.toPath(), backup.toPath());
            } catch (IOException ex) {
                System.out.println("Copy of file failed.");
            }
        }
        fileIO = new FileIO(fileName);
        fileIO.writeFile(fileContent, false);
    }
    // Include required methods that will do absolutely nothing.
    void goUp() {}
    void goDown() {}
    void goNorth() {}
    void goSouth() {}
    void goEast() {}
    void goWest() {}
    void doAction() {}
}


