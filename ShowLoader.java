// --== CS400 Project One File Header ==--
// Name: Suyash Tewari
// CSL Username: suyash
// Email: stewari@wisc.edu
// Lecture #: 001
// Notes to Grader: N/A

import java.io.FileNotFoundException;
import java.util.List;
import java.util.Scanner;
import java.io.File;
import java.util.ArrayList;

/**
 * This class can be used to load a list of shows from a specified csv source file. The following
 * csv columns are used to load these show attributes: 
 * - Title: the complete title for a show 
 * - Year: the year that the show was first produced 
 * - Rotten Tomatoes: the review score (out of 100) for this show 
 * - Netflix: 1 = available on this service, other wise 0 
 * - Hulu: 1 = available on this service, other wise 0 
 * - Prime Video: 1 = available on this service, other wise 0 
 * - Disney+: 1 = available on this service, other wise 0
 */

public class ShowLoader implements IShowLoader {
  
  private int tracker; // keeps track of index of row elements when row is spliced

  @Override
  public List<IShow> loadShows(String filepath) throws FileNotFoundException {
    
    List<IShow> shows = new ArrayList<IShow>(); // instantiate an ArrayList that will contain Shows
    File f = new File(filepath);
    Scanner sc = new Scanner(f, "UTF-8"); // instantiate scanner to read csv file
    
    sc.nextLine(); // skip over the headers of the csv file
    
    while (sc.hasNextLine()) {
      
      String indivShow = sc.nextLine();
      String[] showElements = indivShow.split(","); // split row elements into an array
      
      // get correct title
      String title = titleFormatter(showElements);

      // get correct year, make sure its the correct index
      int indexOfElement = tracker + 1;
      int year = Integer.parseInt(showElements[indexOfElement]);
      
      // get correct rating, make sure its the correct index
      int rating = 
          Integer.parseInt(showElements[indexOfElement + 3].substring(0, showElements[indexOfElement + 3].indexOf("/")));
      
      // get correct providers, make sure its the correct index
      String providers = providerConcatenator(showElements[indexOfElement + 4], showElements[indexOfElement + 5],
          showElements[indexOfElement + 6], showElements[indexOfElement + 7]);

      // create show and add to list
      Show newShow = new Show(title, year, rating, providers);
      shows.add(newShow);
    }
    
    sc.close();
    return shows;
  }

  /**
   * Helper method that stores the title of the Show into a variable. This helper class helps take
   * care of special cases, such as a comma inside the title.
   * 
   * @param showInfo
   * @return
   */
  private String titleFormatter(String[] showInfo) {
    
    String title = showInfo[2]; // title is the third element in the row
    tracker = 2;
    
    // special case of having a comma in the title
    if (showInfo[2].contains("\"") && !showInfo[2].contains("\"\"")) {
      
      // go through the title that is split up
      while (!showInfo[tracker + 1].contains("\"")) {
        tracker++;
        
        // this adds the other parts of the spliced title to the
        // beginning of the spliced title
        title += "," + showInfo[tracker]; 
      }
      
      // this adds the last part of the spliced title, with the end quote
      if (showInfo[tracker + 1].contains("\"")) {
        tracker++;
        title += "," + showInfo[tracker];
      }

      // this removes the quotes around the whole title
      title = title.substring(1, title.length() - 1);
    }
    
    // special case of having double quotes in the title
    if (title.contains("\"\"")) {
      
      // remove the start and end quotes of the title
      title = title.substring(1, title.length() - 1);
      
      // split the title according to the double quotes
      String[] splitTitle = title.split("\"\"");
      String newTitle = "";
      for (int i = 0; i < splitTitle.length; i++) {
        newTitle += splitTitle[i];
        newTitle += "\"";
      }
      title = newTitle;
    }
    return title;
  }
  
  /**
   * This method converts the 1s and 0s to the actual providers
   * 
   * @param netflix
   * @param hulu
   * @param pm
   * @param dp
   * @return
   */
  private String providerConcatenator(String netflix, String hulu, String pm, String dp) {
    String providers = "";
    if (netflix.equals("1")) {
      providers += " Netflix,";
    }
    if (hulu.equals("1")) {
      providers += " Hulu,";
    }
    if (pm.equals("1")) {
      providers += " Prime Video,";
    }
    if (dp.equals("1")) {
      providers += " Disney+,";
    }
    
    // this makes it so that there are spaces after commas and 
    // no spaces at the end of the string or beginning
    return providers.substring(0, providers.length() - 1).trim();
  }
}

