package tarik;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;


public class Main {

  private static int factorial(int x) {
    if (x == 1) return 1;
    return x * factorial(x - 1);
  }

  public static void main(String[] args) {


    //Creates arraylist for storing groups
    ArrayList<Group> list = new ArrayList<>();

    //Creates file on this particular path
    File f = new File("world_cup.txt");

    //Checking if the file with the same name exists
    if (f.exists()) {
      try {
        //If exists it reads the content from the file
        FileInputStream fis = new FileInputStream(f);
        ObjectInputStream ois = new ObjectInputStream(fis);
        try {
          while (true) {
            Group g = (Group) ois.readObject();
            list.add(g);
          }
        } catch (EOFException e) {}
        ois.close();
        fis.close();
      } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
      }
    }

    while (true) {
      // Groups input
      System.out.println("If you want to enter groups by your choice, enter letter Y, "
              + "to read group info enter I, to delete enter D, to insert results enter W, "
              + "to see all possible matches enter A, to read results enter R and to end program enter X");
      Scanner scn = new Scanner(System.in);
      char choice = scn.next().charAt(0);


      if (choice == 'Y' || choice == 'y') {
        System.out.println("How many groups you want to create?");
        int num = scn.nextInt();
        int i = 0;
        // Name and nations of the current group input
        do {
          Scanner in = new Scanner(System.in);
          System.out.println("How many nations does group contain?");
          int nationsNum = in.nextInt();
          System.out.println("Enter group name and nations:");
          String[] input = new String[nationsNum + 1];
          String[] temp = new String[nationsNum];

          for (int j = 0; j < nationsNum + 1; j++) {
            input[j] = scn.next();
            if (j >= 1) {
              temp[j - 1] = input[j];
            }
          }
          list.add(new Group(input[0], temp));
          i++;
        } while (i < num);
      } else if (choice == 'D' || choice == 'd') {
        System.out.println("Which group you want to delete?");
        Scanner in = new Scanner(System.in);
        String input = in.next();
        for (int i = 0; i < list.size(); i++) {
          if (list.get(i).getName().equals(input)) {
            list.remove(i);
          }
        }
      } else if (choice == 'W' || choice == 'w') {
        System.out.println("Enter group name");
        Scanner in = new Scanner(System.in);
        String groupName = in.next();
        for (Group g : list) {
  // n! / k! (n - k)!
          if (g.getName().equals(groupName)) {
            System.out.println("Enter teams and result");
            String home = scn.next();
            String away = scn.next();
            int homeGoals = scn.nextInt();
            int awayGoals = scn.nextInt();
            g.addMatch(home, away, homeGoals, awayGoals);
          }
        }
      } else if (choice == 'R' || choice == 'r') {
        System.out.println("Enter group name");
        Scanner in = new Scanner(System.in);
        String groupName = in.next();
        for (Group g : list) {
          if (g.getName().equals(groupName)) {
            g.printPlayedMatches();
          }
        }
      } else if (choice == 'I' || choice == 'i') {
        System.out.println("Enter group name");
        Scanner in = new Scanner(System.in);
        String input = in.next();
        for (Group g : list) {
          if (g.getName().equals(input)) {
            System.out.println("Group nation are");
            g.printNations();
            System.out.println("All possible mathes are");
            g.printPossibleMatches();
            System.out.println("If you want to see results for played matches enter R");
          }
        }
      } else if (choice == 'A' || choice == 'a') {
        int combinations = 0;
        for (Group g : list) {
          g.printPossibleMatches();
          combinations += g.matches();
        }
        System.out.println("There are " + combinations + " possible matches");
      } else if (choice == 'X' || choice == 'x') {
        File file = new File("world_cup.txt");
        if (file.exists()) file.delete();
        try {
          FileOutputStream fos = new FileOutputStream(file);
          ObjectOutputStream oos = new ObjectOutputStream(fos);
          for (Group g : list) {
            oos.writeObject(g);
          }
          oos.close();
          fos.close();
        } catch (IOException e) {
          e.printStackTrace();
        }
        System.exit(0);
      }
    }
  }
}