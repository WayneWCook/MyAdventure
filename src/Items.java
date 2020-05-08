/* Items.java : The items that populate the great adventure.
 * Author: Wayne Cook
 * Every story needs items to pick u and use. Here is the place to put them all.
 *
 * Creation date: 30 April: The adventure needs items to use.
 */


import java.util.ArrayList;
class Items {
    final static String nothing = "Nothing";
    final static String[] candyBar = {"Butter Finger", "Snickers", "Dove", "Necco","80% Cocoa Dark Chocolate",
            "Good & Plenty", "Red Licorice", "Black Licorice", "Aussie Bites", "Oreos", "Sweet Tarts", "Life Savers",
            "Black Crows", "Juicy Fruit Gum"};
    final static String[] treasures = {"Flashlight", "Ancient Scroll", "boiling water", "Jewel"};
    final static String[] weapons = {"Swiss Army Knife", "Japanese Samurai Sword", "Spatula", "Hard Spaghetti noodle spears", "Pokeball", "stick"};
    final static String[] friends = {"Dragon", "George", "Sally"};
    final static String[] assets = {"Treasures", "Candy Bars", "Weapons", "Friends"};

    int getLength(String[] array) {
        return array.length;
    }
}
