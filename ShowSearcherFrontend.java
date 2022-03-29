// --== CS400 Project One File Header ==--
// Name: Nicha Vang
// CSL Username: nicha
// Email: nvang35@wisc.edu
// Lecture #: 002
// Notes to Grader: none

import java.util.List;
import java.util.Scanner;

/**
 * This class is used to drive a console-based text user interface for the ShowSearcher App.
 * 
 * @author nicha
 *
 */
public class ShowSearcherFrontend implements IShowSearcherFrontend {
  IShowSearcherBackend searchBackend;
  Scanner input;
  String command = "";
  String inputTester = "";
  String displayList = ""; // used in displayShows, placed here for tester to access

  // reads input from System.in
  public ShowSearcherFrontend(IShowSearcherBackend object) {
    this.searchBackend = object;
  }

  // reads input from string
  public ShowSearcherFrontend(String input, IShowSearcherBackend object) {
    inputTester = input;
    this.searchBackend = object;

  }

  /**
   * This method drives the entire read, eval, print loop (repl) for the Show Search App. This loop
   * will continue to run until the user explicitly enters the quit command.
   */
  @Override
  public void runCommandLoop() {
    input = new Scanner(System.in);

    System.out.println("Welcome to the Show Searcher App!");
    System.out.println("=================================");
    // prints command menu to console
    displayCommandMenu();

    command = input.next();
    input.nextLine();
    System.out.println(command);

    // while command is not quit, execute
    while (!command.toUpperCase().equals("Q") || command.equals("4")) {

      if (command.toUpperCase().equals("T") || command.equals("1")) {
        // search shows by title
        titleSearch();
        // go back to display command menu
        displayCommandMenu();
        command = input.next();
        input.nextLine();
        System.out.println(command);

        // if user inputs the same command, continue
        if (command.toUpperCase().equals("T") || command.equals("1")) {
          continue;
        }
      }

      if (command.toUpperCase().equals("Y") || command.equals("2")) {
        // search shows by year
        yearSearch();
        displayCommandMenu();
        command = input.next();
        input.nextLine();
        System.out.println(command);

        // if user inputs the same command, continue
        if (command.toUpperCase().equals("T") || command.equals("1")) {
          continue;
        }
      }

      if (command.toUpperCase().equals("F") || command.equals("3")) {
        // filter shows by provider
        filterProvider();
        displayCommandMenu();
        command = input.next();
        input.nextLine();
        System.out.println(command);

        // if user inputs the same command, continue
        if (command.toUpperCase().equals("T") || command.equals("1")) {
          continue;
        }
      }

      // if command does not equal t/f/q, prompt for user input
      else if (!command.toUpperCase().equals("Q")) {
        // invalid input
        System.out.print("Invalid request. Search again using T/Y/F/Q or 1/2/3/4: ");
        command = input.next();
        input.nextLine();
        System.out.println(command);
      }
    }
    // runs when user inputs quit
    System.out.println("Thank you for using Show Searcher App");
    input.close();

  }

  /**
   * prints command options to System.out
   */
  @Override
  public void displayCommandMenu() {
    System.out.println("Command Menu:");
    System.out.println("\t1) Search by [T]itle word\n\t2) Search by [Y]ear it was produced\n\t3) "
        + "[F]ilter by streaming provider\n\t4) [Q]uit");
    System.out.print("Choose a command from the menu above: ");
  }

  /**
   * displays a list of shows
   */
  @Override
  public void displayShows(List<IShow> shows) {
    boolean foundProvider = false;
    String showProvider = "";
    System.out
        .println("Found " + shows.size() + "/" + searchBackend.getNumberOfShows() + " matches.");

    // display shows in a numbered list
    for (int i = 0; i < shows.size(); i++) {
      displayList = (i + 1) + ". " + shows.get(i).getTitle() + "\n\t"
          + shows.get(i).getRating() + "/100 (" + shows.get(i).getYear() + ") " + "on: ";
      // finds which provider(s) it is available on
      while (foundProvider == false) {
        if (shows.get(i).isAvailableOn("Netflix") == true) {
          showProvider = "Netflix, ";
          displayList += showProvider;
          foundProvider = true;
        }
        if (shows.get(i).isAvailableOn("Hulu") == true) {
          showProvider = "Hulu, ";
          displayList += showProvider;
          foundProvider = true;
        }
        if (shows.get(i).isAvailableOn("Prime Video") == true) {
          showProvider = "Prime Video, ";
          displayList += showProvider;
          foundProvider = true;
        }
        if (shows.get(i).isAvailableOn("Disney+") == true) {
          showProvider = "Disney+, ";
          displayList += showProvider;
          foundProvider = true;
        }
      }
      // reset foundProvider to false for next iteration
      foundProvider = false;

      // print the list of numbered shows to screen
      System.out.println(displayList.substring(0, displayList.length() - 2));
    }
  }

  /**
   * reads word from System.in, displays results
   */
  // @Override
  public void titleSearch() {
    System.out.print("Choose a word that you would like to search for: ");
    command = input.next();
    input.nextLine();
    System.out.println(command);
    // uses searcher backend to search shows by title word
    List<IShow> showsByTitle = searchBackend.searchByTitleWord(command);
    // display results
    displayShows(showsByTitle);
    // clears list for next use
    showsByTitle.clear();

  }

  /**
   * reads year from System.in, displays results
   */
  @Override
  public void yearSearch() {
    System.out.print("Choose a year that you would like to search for: ");
    command = input.next();
    input.nextLine();
    System.out.println(command);
    // uses searcher backend to search shows by year
    List<IShow> showByYear = searchBackend.searchByYear(Integer.parseInt(command));
    // display results
    displayShows(showByYear);
    // clears list for next use
    showByYear.clear();
  }

  /**
   * helper method that displays provider filter options
   */
  public void filterProvider() {
    // sets strings of the default providers available for search
    String netflix = "1) _x_ [N]etflix";
    String hulu = "2) _x_ [H]ulu";
    String primeVideo = "3) _x_ [P]rime Video";
    String disneyPlus = "4) _x_ [D]isney+";
    String quitFilters = "[Q]uit toggling provider filters";

    System.out.println("Providers that shows are being searched for:");
    System.out.println("\t" + netflix + "\n\t" + hulu + "\n\t" + primeVideo + "\n\t" + disneyPlus
        + "\n\t" + quitFilters);
    System.out.print("Choose the provider that you'd like to toggle the filter for: ");

    command = input.next();
    input.nextLine();
    System.out.println(command);

    // replaces "x" for "_" to filter out that provider
    while (!command.toUpperCase().equals("Q") || !command.equals("5")) {
      if (command.toUpperCase().equals("N") || command.equals("1")) {
        netflix = netflix.replaceFirst("x", "_");
        System.out.println("\t" + netflix + "\n\t" + hulu + "\n\t" + primeVideo + "\n\t"
            + disneyPlus + "\n\t" + quitFilters);
        // filters out Netflix
        searchBackend.setProviderFilter("Netflix", true);

        System.out.print("Choose the provider that you'd like to toggle the filter for: ");
        command = input.next();
        input.nextLine();
        System.out.println(command);
      } else if (command.toUpperCase().equals("H") || command.equals("2")) {
        hulu = hulu.replaceFirst("x", "_");
        System.out.println("\t" + netflix + "\n\t" + hulu + "\n\t" + primeVideo + "\n\t"
            + disneyPlus + "\n\t" + quitFilters);

        // filters out Hulu
        searchBackend.setProviderFilter("Hulu", true);

        System.out.print("Choose the provider that you'd like to toggle the filter for: ");
        command = input.next();
        input.nextLine();
        System.out.println(command);
      } else if (command.toUpperCase().equals("P") || command.equals("3")) {
        primeVideo = primeVideo.replaceFirst("x", "_");
        System.out.println("\t" + netflix + "\n\t" + hulu + "\n\t" + primeVideo + "\n\t"
            + disneyPlus + "\n\t" + quitFilters);

        // filters out Prime Video
        searchBackend.setProviderFilter("Prime Video", true);

        System.out.print("Choose the provider that you'd like to toggle the filter for: ");
        command = input.next();
        input.nextLine();
        System.out.println(command);

      } else if (command.toUpperCase().equals("D") || command.equals("4")) {
        disneyPlus = disneyPlus.replaceFirst("x", "_");
        System.out.println("\t" + netflix + "\n\t" + hulu + "\n\t" + primeVideo + "\n\t"
            + disneyPlus + "\n\t" + quitFilters);

        // filters out Disney+
        searchBackend.setProviderFilter("Disney+", true);

        System.out.print("Choose the provider that you'd like to toggle the filter for: ");
        command = input.next();
        input.nextLine();
        System.out.println(command);
      }
      // quits filtering out providers
      else if (command.toUpperCase().equals("Q") || command.equals("5")) {
        return;
      } else {
        // user input invalid string
        System.out.print("Invalid request. Search again using N/H/P/D or 1/2/3/4: ");
        command = input.next();
        input.nextLine();
        System.out.println(command);
      }
    }
  }
}
