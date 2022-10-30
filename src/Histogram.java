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

    do {                                        //make sure file exists, otherwise ask user again for filename
      fileName = getFileName();
      read(letter, letterCount, fileName);
    } while (isErrored);

    sort(letter, letterCount);
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
      System.out.println("Could not find the file " + e);
      isErrored = true;
      return;
    }

    while (scan != null && scan.hasNext()) {

      String input = scan.nextLine();
      System.out.println(input);
      letterFound = false;

      for (int i = 0; i < letter.size(); i++) {  //check letters against array, if letter found, add 1 to letterCount
        if (letter.get(i) == input.charAt(0)) {
          letterCount.set(i,letterCount.get(i)+1);
          System.out.println("Letter " + input + " Found at " + i);
          letterFound = true;
          break;
        }
        System.out.println(letter.size());
      }

      if (letterFound == false) {  // if letter not found in our letter Array, add it to our letter Array
        letter.add(input.charAt(0));
        letterCount.add(1);
        System.out.println("New Letter " + input.charAt(0) + " Found!");
      }


    }
  }

  public static void sort(ArrayList<Character> letter, ArrayList<Integer> letterCount) {
    int position;
    int max = 0;
    ArrayList<Character> letterHolder = new ArrayList<Character>();
    ArrayList<Integer> letterCountHolder = new ArrayList<Integer>();

    for (int i = 0; i <= letterCount.size(); i++) {  //Walk through Array

      for (int i2 = 0; i2 <= letterCount.size(); i2++) {
        if (letterCount.get(i2) > max) {
          max = letterCount.get(i2);
          position = i2;
        }
      }

      letterHolder.add(letter.get(position));
      letterCountHolder.add(letterCount.get(position));
      letter.remove(position);
      letterCount.remove(position);
    }


  }

  public static void display(ArrayList<Character> letter, ArrayList<Integer> letterCount) {

  }
}

