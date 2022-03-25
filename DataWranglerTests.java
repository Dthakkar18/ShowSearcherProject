// --== CS400 Project One File Header ==--
// Name: Suyash Tewari
// CSL Username: suyash
// Email: stewari@wisc.edu
// --== CS400 Project One File Header ==--
// Name: Suyash Tewari
// CSL Username: suyash
// Email: stewari@wisc.edu
// Lecture #: 001
// Notes to Grader: N/A

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

public class DataWranglerTests {
  
  /**
   * tests the accessors in Show.java
   * 
   * @return true if all tests pass
   */
  public static boolean test1() {
    try {
      Show show1 = new Show("title", 2022, 100, "Netflix");
      
      // tests getTitle()
      if(!show1.getTitle().equals("title")) {
        return false;
      }
      
      // tests getYear()
      if(show1.getYear() != 2022) {
        return false;
      }
      
      // tests getRating()
      if(show1.getRating() != 100) {
        return false;
      }
      
      // tests isAvailableOn()
      if(!show1.isAvailableOn("Netflix")) {
        return false;
      }
      
      if(show1.isAvailableOn("Hulu")) {
        return false;
      }
      
    } catch (Exception e) {
      return false;
    }
      
    return true;
  }
  
  /**
   * tests the compareTo() method in Show.java
   * 
   * @return true if all tests pass
   */
  public static boolean test2() {
    try {
      //tests if two shows are the same
      Show sameShow1 = new Show("title", 2022, 100, "Prime Video");
      Show sameShow2 = new Show("title", 2022, 100, "Prime Video");
      
      if(sameShow1.compareTo(sameShow2) != 0) {
        return false;
      }
      
      // tests if two titles are different
      Show diffTitle1 = new Show("a", 2022, 100, "Hulu");
      Show diffTitle2 = new Show("b", 2022, 100, "Hulu");
      
      if(diffTitle1.compareTo(diffTitle2) >= 0) {
        return false;
      }
      
      // tests if two years are different
      Show diffYear1 = new Show("a", 2021, 100, "Disney+");
      Show diffYear2 = new Show("a", 2022, 100, "Disney+");
      
      if(diffYear1.compareTo(diffYear2) >= 0) {
        return false;
      }
      
      // tests if two ratings are different
      Show diffRating1 = new Show("a", 2022, 90, "Netflix");
      Show diffRating2 = new Show("a", 2022, 100, "Netflix");
      
      if(diffRating1.compareTo(diffRating2) >= 0) {
        return false;
      }
      
    } catch(Exception e) {
      return false;
    }
    
    return true;
  }
  
  /**
   * Tests special cases of ShowLoader.java
   * 
   * @return true if all tests pass
   */
  public static boolean test3() {
    try {
      ShowLoader sl = new ShowLoader();
      List<IShow> shows = sl.loadShows("tv_shows.csv");
    
      // commas within title
      if(!shows.get(2931).getTitle().equals("Ready, Steady, Wiggle!")) {
        return false;
      }
      
      // double quotes within title
      if(!shows.get(2627).getTitle().equals("Say \"I Love You.\"")) {
        return false;
      }
      
      
    } catch(Exception e) {
      return false;
    }

    return true;
  }
  
  /**
   * Tests other functionalities of ShowLoader.java
   * 
   * @return true if all tests pass
   */
  public static boolean test4() {
    try {
      ShowLoader sl = new ShowLoader();
      List<IShow> shows = sl.loadShows("tv_shows.csv");
      
      // checks if it can get the first show, and if it gets the correct rating of the first show
      if(shows.get(0).getRating() != 100) {
        return false;
      }
      
      // checks if it can get the last show, and if it can get the correct year from the last show
      if(shows.get(5367).getYear() != 2018) {
        return false;
      }
      
    } catch(Exception e) {
      return false;
    }
    
    return true;
  }
  
  /**
   * Tests FileNotFoundException in ShowLoader.java
   * 
   * @return true if all tests pass
   */
  public static boolean test5() {
    try {
      
      // checks if it correctly throws exception
      try {
        ShowLoader sl = new ShowLoader();
        sl.loadShows("fake.csv");
        return false;
        
      } catch(FileNotFoundException e) {
      }
      
    } catch(Exception e) {
      return false;
    }
    
   return true;
  }
  
  /**
   * Tests if ShowSearcherFrontend.java works with Show.java
   * 
   * @return true if all tests pass
   */
  public static boolean test6() {
    IShowSearcherBackend issb = new ShowSearcherBackend();
    ShowSearcherFrontend ssf = new ShowSearcherFrontend(issb);
    
    List<IShow> shows = new ArrayList<IShow>();
    
    Show show1 = new Show("title", 2022, 100, "Netflix, Hulu");
    shows.add(show1);
    issb.addShow(show1);
    
    ssf.displayShows(shows);
    
    if(!ssf.displayList.substring(0, ssf.displayList.length() - 2).equals("1. title\n\t100/100 (2022) on: Netflix, Hulu")) {
      return false;
    }
    
    return true;
  }
  
  
  /**
   * Tests if ShowSearcherBackend's addShow method correctly works with DataWrangler methods
   * 
   * @return true if all tests pass
   */
  public static boolean test7() {
    
    IShowSearcherBackend issb = new ShowSearcherBackend();
    ShowSearcherFrontend ssf = new ShowSearcherFrontend(issb);
    
    Show show1 = new Show("title", 2021, 100, "Netflix, Hulu");
    Show show2 = new Show("title2", 2022, 100, "Prime Video");
    
    List<IShow> shows = new ArrayList<IShow>();
    shows.add(show1);
    //shows.add(show2);
    
    issb.addShow(show1);
    issb.addShow(show2);    
    
    ssf.displayShows(shows);
    
    if(issb.getNumberOfShows() != 2) {
      return false;
    }
    
    if(!ssf.displayList.substring(0, ssf.displayList.length() - 2).equals("1. title\n\t100/100 (2021) on: Netflix, Hulu")) {
      return false;
    }
    
    return true;
  }
  
  /**
   * Testing code from ShowSearcherFrontend.java
   * 
   * @return true if all tests pass
   */
  public static boolean test8() {
    
    try {
      IShowSearcherBackend issb = new ShowSearcherBackend();
      ShowSearcherFrontend ssf = new ShowSearcherFrontend(issb);
      
      List<IShow> shows = new ArrayList<IShow>();
      
      Show show1 = new Show("title", 2022, 100, "Netflix, Hulu");
      Show show2 = new Show("title2", 2022, 100, "Prime Video");
      Show show3 = new Show("title3", 2022, 100, "Disney+");

      shows.add(show1);
      shows.add(show2);
      shows.add(show3);
      
      ssf.displayShows(shows);
      
      System.out.println(ssf.displayList.substring(0, ssf.displayList.length() - 2));
      
      if(!ssf.displayList.substring(0, ssf.displayList.length() - 2).equals("3. title3\n\t100/100 (2022) on: Disney+")) {
        return false;
      }
      
    } catch(Exception e) {
      return false;
    }
    
    return true;
  }
  
  /**
   * Testing code from ShowSearcherFrontend.java
   * 
   * @return true if all tests pass
   */
  public static boolean test9() {
    try {
      String commandMenu = "Command Menu\n\t1) Search by [T]itle word\n\t2) Search by [Y]ear it was produced\n\t"
          + "3) [F]ilter by streaming provider\n\t4) [Q]uit\nChoose a command from the menu above: ";
      
      IShowSearcherBackend issb = new ShowSearcherBackend();
      ShowSearcherFrontend ssf = new ShowSearcherFrontend(issb);
      
      ssf.displayCommandMenu();
      
    } catch(Exception e) {
      return false;
    }
    
    return true;
  }


  public static void main(String[] args) {
    System.out.println("test1(): " + test1());
    System.out.println("test2(): " + test2());
    System.out.println("test3(): " + test3());
    System.out.println("test4(): " + test4());
    System.out.println("test5(): " + test5());
    System.out.println("test6(): " + test6());
    System.out.println("test7(): " + test7());
    System.out.println("test8(): " + test8());
    System.out.println("test9(): " + test9());

  }
}

