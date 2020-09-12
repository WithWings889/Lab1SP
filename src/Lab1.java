import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Lab1 {
    private static int counter = 0;
    private static boolean isWithoutRepetitions = true;
    private static ArrayList<String> selectedWords = new ArrayList<>();
    private static boolean isInList = false;
    private static char[] word = new char[30];

    public static void clearWord(char[] word) {
        for(int i = 0; i < counter; ++i)
            word[i] = '\0';
    }

    public static void addInSelectedWords(){
        String string = new String(word);
        for(String w: selectedWords)
            if( w.equals(string)) {
                isInList = true;
                break;
            }
        if (!isInList)
            selectedWords.add(string);
        isInList = false;
    }

    public static void readFile() {
        File file = new File("src\\text");
        try {
            Scanner scanner = new Scanner(file);
            while(scanner.hasNextLine()){
                String line = scanner.nextLine() + " ";
                for(int i = 0; i < line.length(); ++i){
                    boolean isNotLetter = ((int)line.charAt(i) < 97 && (int)line.charAt(i) > 90) || (int)line.charAt(i) < 65 || (int)line.charAt(i) > 122 ;

                    if (counter >= 30 && !isNotLetter)
                        continue;
                    else{
                        if (isNotLetter && counter != 0){
                            if(isWithoutRepetitions) {
                                addInSelectedWords();
                            }
                            clearWord(word);
                            counter = 0;
                            isWithoutRepetitions = true;
                        }
                        else if(isNotLetter && counter == 0)
                            continue;
                        else {
                            if (isWithoutRepetitions)
                                for(int j = 0; j < counter; ++j)
                                    if(word[j] == line.charAt(i)) {
                                        isWithoutRepetitions = false;
                                        break;
                                    }
                            word[counter] = line.charAt(i);
                            ++counter;

                        }
                    }

                }
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File is not found");
        }
    }

    public static void main(String[] args){
        readFile();

        for(String word: selectedWords){
            System.out.println(word);
        }
    }
}
