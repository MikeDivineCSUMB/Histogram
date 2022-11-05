/**
 * Title: Histogram
 * Abstract: Purpose of this program is to read files and create a Histogram of the characters
 * Author: Mike Divine
 * Date: 10/27/2022

 */
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Histogram {
  static String fileName;
  static ArrayList<Character> letter = new ArrayList<Character>();
  static ArrayList<Integer> letterCount = new ArrayList<Integer>();
  static boolean isErrored;
  static Boolean letterFound = false;

  public static void main(String[] args) {

    do {  //make sure file exists, otherwise ask user again for filename
      System.out.println("Enter \"exit\" to Quit.");
      fileName = getFileName();
      if (fileName.equals("exit")) {
        break;
      }
      read(letter, letterCount, fileName);
    } while (isErrored);

    if (!fileName.equals("exit")) {
      sort(letter, letterCount);
      display(letter, letterCount);
    }

  }

  public static String getFileName() {
    Scanner myObj = new Scanner(System.in);
    System.out.println("Enter filename: ");

    fileName = myObj.nextLine();
    return fileName;
  }

  public static void read(ArrayList<Character> letter, ArrayList<Integer> letterCount, String fileName) {
    File f = new File(fileName);
    Scanner scan = null;

    try {
      isErrored = false;
      scan = new Scanner(f);
    } catch (FileNotFoundException e) {
      System.out.println("Could not find the file " + fileName.toString());
      isErrored = true;
      return;
    }

    while (scan != null && scan.hasNext()) {

      String input = scan.nextLine();
      letterFound = false;
      char[] line = new char[input.length()];

      for (int i = 0; i < input.length(); i++) {
        line[i] = input.charAt(i);
      }

      for (int loopThruLine = 0; loopThruLine < line.length; loopThruLine++) {

        for (int i = 0; i < letter.size(); i++) {  //check letters against array, if letter found, add 1 to letterCount

          if (letter.get(i) == line[loopThruLine]) {
            letterCount.set(i,letterCount.get(i)+1);
            letterFound = true;
            break;
          }
        }

        if (letterFound == false) {  // if letter not found in our letter Array, add it to our letter Array
          letter.add(line[loopThruLine]);
          letterCount.add(1);
        }
      }
    }
  }

  public static void sort(ArrayList<Character> letter, ArrayList<Integer> letterCount) {
    int position = 1;
    int max = 0;
    ArrayList<Character> letterHolder = new ArrayList<>();
    ArrayList<Integer> letterCountHolder = new ArrayList<>();

    /**
     * Run through letterCount Array and sort by most letters to least letters
     * */
    int walkthru = letterCount.size();

    for (int i = 0; i < walkthru; i++) {

      max = 0;

      for (int i2 = 0; i2 < letterCount.size(); i2++) {
        if (letterCount.get(i2) >= max) {
          max = letterCount.get(i2);
          position = i2;
        }
      }

      letterHolder.add(letter.get(position));
      letterCountHolder.add(letterCount.get(position));
      letter.remove(position);
      letterCount.remove(position);
    }

    for (int i = 0; i < letterCountHolder.size(); i++) {
      letter.add(letterHolder.get(i));
      letterCount.add(letterCountHolder.get(i));
    }
  }

  public static void display(ArrayList<Character> letter, ArrayList<Integer> letterCount) {

    for (int i = letterCount.size()-1; i >= 0; i--) {
      System.out.println(letter.get(i) + " " + letterCount.get(i));
    }
    System.out.println("");
    System.out.println("==============================");

    /**
     * Run through count
     * */
    int max = 2147483647;
    int amountInLine = 0;
    String begOfLine = "";
    String endOfLine = "";
    boolean addLine = true;

    for (int i = 0; i < letterCount.size(); i++) {

      if (letterCount.get(i) < max) {
        begOfLine = "|    " + letterCount.get(i) + "|";
        endOfLine = letter.get(i) + " " + endOfLine;
        amountInLine++;
        max = letterCount.get(i);
      } else if (letterCount.get(i) == max) {
        endOfLine = letter.get(i) + " " + endOfLine;
        amountInLine++;
      } else {
        System.out.println("Error");
      }

      if (letterCount.size()-1 != i) {
        addLine = true;

        if (letterCount.get(i+1) == max) {
          addLine = false;
        }
      } else {
        addLine = true;
      }

      if (addLine == true) {
        System.out.printf(begOfLine);

        for (int i2 = 0; i2 < (22 - amountInLine*2); i2++) {
          System.out.printf(" ");
        }

        System.out.printf(endOfLine + "\n");
        begOfLine = "";
      }

    }

    System.out.printf("------------------------------\n");
    ArrayList<Character> letterHolder = new ArrayList<>();
    Character[] AllLetters = {'A','B','C','D','E','F','G','H','I','J','K'};
    boolean found = false;

    for (int i = 0; i < AllLetters.length; i++) {
      found = false;

      for (int i2 = 0; i2 < letter.size(); i2++) {

        if (letter.get(i2) == AllLetters[i]) {
          found = true;
        }
      }

      if (found == false) {
        letterHolder.add(AllLetters[i]);
      }
    }
    String finalLetterOrder = "";

    for (int i = 0; i < letterHolder.size(); i++) {
      finalLetterOrder = finalLetterOrder + " " + letterHolder.get(i);
    }

    System.out.printf("%29s",finalLetterOrder + " " + endOfLine);

  }
}

