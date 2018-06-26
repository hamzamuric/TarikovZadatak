package tarik;

import java.io.Serializable;
import java.util.ArrayList;

class Group implements Serializable {

  //Class for matches results and games
  private class Match implements Serializable {
    String home;
    String away;
    int homeGoals;
    int awayGoals;

    public Match(String home, String away, int homeGoals, int awayGoals) {
      this.home = home;
      this.away = away;
      this.homeGoals = homeGoals;
      this.awayGoals = awayGoals;
    }

    @Override
    public String toString() {
      return String.format("%s - %s\n\t%d - %d", home, away, homeGoals, awayGoals);
    }
  }

  private String[] nations;
  private String name;
  private ArrayList<Match> matches;

  Group(String name, String...input) {
    nations = new String[input.length];
    for (int i = 0; i < input.length; i++) {
      nations[i] = input[i];
    }
    this.name = name;
    matches = new ArrayList<>();
  }

  public String getName() { return name; }

  public void printNations() {
    for (String n : nations) {
      System.out.print(n + " ");
      System.out.println();
    }
  }

  private static int factorial(int x) {
    if (x == 1) return 1;
    return x * factorial(x - 1);
  }

  public int matches() {
    return factorial(nations.length) / (2 * factorial(nations.length - 2));
  }

  public void addMatch(String home, String away, int homeGoals, int awayGoals) {
    boolean isFirstValid = false;
    boolean isSecondValid = false;
    for (String s : nations) {
      if (s.equals(home))
        isFirstValid = true;
      if (s.equals(away))
        isSecondValid = true;
    }
    if (isFirstValid && isSecondValid)
      matches.add(new Match(home, away, homeGoals, awayGoals));
    else
      System.out.println("The group does not contain those nations");
  }

  public void printPlayedMatches() {
    for (Match m : matches) {
      System.out.println(m);
    }
  }

  public void printPossibleMatches() {
    // Prints number of possible combinations
    System.out.println("Number of combinations for group " + name + " is " + matches());
    // Prints all combinations
    for (int i = 0; i < nations.length - 1; i++) {
      for (int j = i + 1; j < nations.length; j++) {
        System.out.println(nations[i] + " - " + nations[j]);
      }
    }
  }

  @Override
  public String toString() {
    String result = "Name of Group: " + name + "\nNations are: ";
    for (int i = 0; i < nations.length; i++) {
      result += nations[i] + " ";
    }
    return result;
  }
}