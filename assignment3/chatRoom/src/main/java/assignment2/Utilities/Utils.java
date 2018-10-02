package assignment2.Utilities;

import java.util.List;
import java.util.Random;

public class Utils {

    private static final String[] namesList = new String[]{
            "Aaron",
            "Bill",
            "Carl",
            "Dennis",
            "Edward",
            "Fred",
            "George",
            "Harry",
            "Igor",
            "John",
            "Klaus",
            "Leonard",
            "Michael",
            "Nicholas",
            "Oliver",
            "Peter",
            "Quentin",
            "Richard",
            "Shawn",
            "Ted"
    };

    public static String getRandomName(){
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();
        for (int i = 0; i < rand.nextInt(5); i++){
            sb.append(namesList[rand.nextInt(namesList.length)]);
        }
        return sb.toString();
    }

    /**
     * Gets a random word from a string of text.
     * @param line A string of text, with words separated by spaces.
     * @return One of the words in that line.
     */
    public static String getRandomWord(String line){
        Random rand = new Random();
        String[] arrayOfWords = line.split(" ");
        return arrayOfWords[rand.nextInt(arrayOfWords.length)];

    }

    /**
     * Generates a random message of words compiled from a list of strings.
     * @param listOfMessages The list of strings to pull words from.
     * @return A string of random words.
     */
    public static String getRandomMessage(List<String> listOfMessages) {
        StringBuilder sb = new StringBuilder();
        Random rand = new Random();

        for (int i = 0; i < rand.nextInt(listOfMessages.size())+1; i++) {

            sb.append(getRandomWord(listOfMessages.get(rand.nextInt(listOfMessages.size()))));
            sb.append(" ");
        }

        return sb.toString();

    }

    /**
     * Chooses a random string from a list.
     * @param strings A list of strings.
     * @return One string from the list.
     */
    public static String chooseRandomString(String[] strings){
        return strings[new Random().nextInt(strings.length)];
    }
}
