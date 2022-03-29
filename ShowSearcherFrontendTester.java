import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

// --== CS400 Project One File Header ==--
// Name: Nicha Vang
// CSL Username: nicha
// Email: nvang35@wisc.edu
// Lecture #: 002
// Notes to Grader: none


/**
 * This tester class tests the integration of different classes to see if the codes are integrated successfully
 * 
 * @author nicha
 *
 */

public class ShowSearcherFrontendTester {
  // previous tests, scroll below for new tests
  /**
   * checks if output printed matches expectations with inputs to search by title
   * 
   * @return true if successful, false otherwise
   */
  /**
   * public static boolean test1() {
   * 
   * TextUITester tester = new TextUITester("t\nSpider-Man\nq\n"); ShowSearcherBackendPlaceHolder
   * backend = new ShowSearcherBackendPlaceHolder(); ShowSearcherFrontend frontend = new
   * ShowSearcherFrontend("t\nSpider-Man\nq\n", backend); frontend.runCommandLoop(); String output =
   * tester.checkOutput(); System.out.println(output);
   * 
   * if (output.startsWith("Welcome to the Show Searcher App!") && output.contains("1. Spider-Man"))
   * { System.out.println("test 1 passed"); return true; } return false; }
   * 
   * /** checks if output printed to System.out matches expectations with inputs to search by year
   * 
   * @return true if successful, false otherwise
   */
  /**
   * public static boolean test2() { TextUITester tester = new TextUITester("y\n2001\nq\n");
   * ShowSearcherBackendPlaceHolder backend = new ShowSearcherBackendPlaceHolder();
   * ShowSearcherFrontend frontend = new ShowSearcherFrontend("y\n2001\nq\n", backend);
   * frontend.runCommandLoop(); String output = tester.checkOutput(); System.out.println(output);
   * 
   * if (output.startsWith("Welcome to the Show Searcher App!") && output.contains("1. Spy Kids")) {
   * System.out.println("test 2 passed"); return true; } return false; }
   * 
   * /** checks if output printed matches expectations with inputs that filter providers
   * 
   * @return true if successful, false otherwise
   */
  /**
   * public static boolean test3() { TextUITester tester = new
   * TextUITester("f\nh\nq\nt\nSpider-Man\nq\n"); ShowSearcherBackendPlaceHolder backend = new
   * ShowSearcherBackendPlaceHolder(); ShowSearcherFrontend frontend = new
   * ShowSearcherFrontend("f\nh\nq\nSpider-Man\nq\n", backend); frontend.runCommandLoop(); String
   * output = tester.checkOutput(); System.out.println(output);
   * 
   * if (output.startsWith("Welcome to the Show Searcher App!") && output.contains("Spider-Man 2"))
   * { System.out.println("test 3 passed"); return true; } return false; }
   * 
   * /** checks if output printed matches expectations for the quit command
   * 
   * @return true if successful, false otherwise
   */
  /**
   * public static boolean test4() { TextUITester tester = new TextUITester("q\n");
   * ShowSearcherBackendPlaceHolder backend = new ShowSearcherBackendPlaceHolder();
   * ShowSearcherFrontend frontend = new ShowSearcherFrontend("q\n", backend);
   * frontend.runCommandLoop(); String output = tester.checkOutput(); System.out.println(output);
   * 
   * if (output.startsWith("Welcome to the Show Searcher App!") && output.contains("Thank you for
   * using Show Searcher App")) { System.out.println("test 4 passed"); return true; } else {
   * System.out.println("test 4 failed"); } return false; }
   * 
   * /** tests for an invalid input. System.out should contain a string "Invalid request."
   * 
   * @return true if successful, false otherwise
   */
  /**
   * public static boolean test5() { TextUITester tester = new
   * TextUITester("p\nt\nSpider-Man\nq\n"); ShowSearcherBackendPlaceHolder backend = new
   * ShowSearcherBackendPlaceHolder(); ShowSearcherFrontend frontend = new
   * ShowSearcherFrontend("q\n", backend); frontend.runCommandLoop(); String output =
   * tester.checkOutput(); System.out.println(output);
   * 
   * if (output.startsWith("Welcome to the Show Searcher App!") && output.contains("Invalid
   * request.")) { System.out.println("test 5 passed"); return true; } else {
   * System.out.println("test 5 failed"); } return false; }
   * 
   * /** runs all test
   * 
   * @return true if successful, false otherwise
   */
  /**
   * public static boolean runAllTests() { if (test1() == true && test2() == true && test3() == true
   * && test4() == true && test5() == true) { return true; } return false; }
   */
  
  
  
  /**
   * test if isAvailableOn method in Show class returns correctly to frontend displayShows method
   * 
   * @return true if successful, false otherwise
   */
  public static boolean test6() {
    ShowSearcherBackend backend = new ShowSearcherBackend();
    ShowSearcherFrontend frontend = new ShowSearcherFrontend(backend);

    Show show1 = new Show("Spider-Man", 2001, 82, "Hulu, Disney+");
    List<IShow> showList = new ArrayList<IShow>();
    // adds show1 to backend and showList
    backend.addShow(show1);
    showList.add(show1);
    // calls displayshows method to print out contents of showList
    frontend.displayShows(showList);

    String showdisplay = frontend.displayList.substring(0, frontend.displayList.length() - 2);
    // checks that string of shows is as expected
    if (showdisplay.equals("1. Spider-Man\n\t82/100 (2001) on: Hulu, Disney+")) {
      return true;
    }

    return false;
  }

  /**
   * test if searchByTitleWord in ShowSearcherBackend class returns correctly to frontend
   * titleSearch method
   * 
   * @return true if successful, false otherwise
   */
  public static boolean test7() {
    // default user commands are t, Free, q
    TextUITester tester = new TextUITester("t\nFree\nq\n");

    ShowSearcherBackend backend = new ShowSearcherBackend();
    // calls constructor with input string
    ShowSearcherFrontend frontend = new ShowSearcherFrontend("t\nFree\nq\n", backend);

    Show show1 = new Show("Spider-Man", 2001, 82, "Hulu, Disney+");
    Show show2 = new Show("Free Guy", 2004, 84, "Prime Video");

    backend.addShow(show1);
    backend.addShow(show2);

    // runs runCommandLoop method with default commands
    frontend.runCommandLoop();

    String output = tester.checkOutput();
    System.out.println(output);

    // checks if correct title is returned
    if (!output.startsWith("Welcome to the Show Searcher App!")
        && !output.contains("1. Free Guy")) {
      return false;
    }

    return true;
  }

  /**
   * tests methods in show class implemented by Data Wrangler
   * 
   * @return true if successful, false otherwise
   */
  public static boolean test8() {

    Show show1 = new Show("The Flash", 2014, 88, "Netflix");
    Show show2 = new Show("Breaking Bad", 2008, 100, "Netflix");
    // checks if show1's title is as expected
    if (!show1.getTitle().equals("The Flash")) {
      return false;
    }
    // checks if show1's rating is as expected
    if (show1.getRating() != 88) {
      return false;
    }
    // checks if show1's year is as expected
    if (show1.getYear() != 2014) {
      return false;
    }
    // checks if show1 comes before show2
    if (show1.compareTo(show2) <= 0) {
      return false;
    }
    // checks if show1 is available on Hulu
    if (show1.isAvailableOn("Hulu") != false) {
      return false;
    }
    return true;
  }

  /**
   * tests loadShows in ShowLoader class implemented by Data Wrangler
   * 
   * @return true if successful, false otherwise
   */
  public static boolean test9() {
    ShowLoader loader = new ShowLoader();
    try {
      List<IShow> showList = loader.loadShows("tv_shows.csv");
      // tests for the first show in list
      if (!showList.get(0).getTitle().equals("Breaking Bad")) {
        return false;
      }
      // tests for the last show in list
      if (!showList.get(5367).getTitle().equals("Fearless Adventures with Jack Randall")) {
        return false;
      }
    } catch (FileNotFoundException e) {
    }

    return true;
  }

  /**
   * starts the simulation
   * 
   * @param args
   */
  public static void main(String[] args) {
    // previous tester methods using keyboard input
    // ShowSearcherBackend backendObject = new ShowSearcherBackend();
    // ShowSearcherFrontend search = new ShowSearcherFrontend(backendObject);
    // search.runCommandLoop();

    // previous tester methods testing placeholders with TextUITester
    /**
     * boolean runAllTests = runAllTests(); if (runAllTests == true) { System.out.println("All tests
     * passed"); }
     */

    // new tester methods
    if (test6() == true && test7() == true && test8() == true && test9() == true) {
      System.out.println("All tests passed");
    }
  }
}


