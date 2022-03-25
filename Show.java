// --== CS400 Project One File Header ==--
// Name: Suyash Tewari
// CSL Username: suyash
// Email: stewari@wisc.edu
// Lecture #: 001
// Notes to Grader: N/A

/**
 * This class represent a single show
 *  
 * @author suyash tewari
 *
 */
public class Show implements IShow {

  protected String title; // title for show
  protected int year; // year show was first produced
  protected int rating; // Rotten Tomatoes Rating (out of 100)
  protected String providers; // providers string includes the names of every streaming source

  /**
   * This is the constructor for the Show class
   * @param title
   * @param year
   * @param rating
   * @param providers
   */
  public Show(String title, int year, int rating, String providers) {
    this.title = title;
    this.year = year;
    this.rating = rating;
    this.providers = providers;
  }
  

  /**
   * This is the compareTo method that compares two shows
   * 
   * @param o - Show object that we are comparing to
   */
  @Override
  public int compareTo(IShow o) {
    
    // compares with title
    if(!this.title.equals(o.getTitle())) {
      return this.title.compareTo(o.getTitle());
    }
    
    // compares with year
    else if(this.year != o.getYear()) {
      if(this.year > o.getYear()) {
        return 1;
      }
      else {
        return -1;
      }
    }
    
    // compares with rating
    else if(this.rating != o.getRating()) {
      if(this.rating > o.getRating()) {
        return 1;
      }
      else {
        return -1;
      }
    }
    
    return 0;
  }

  /**
   * This method get the title of the Show
   * 
   */
  @Override
  public String getTitle() {
    return this.title;
  }

  /**
   * This method gets the year the Show was produced
   * 
   */
  @Override
  public int getYear() {
    return this.year;
  }

  /**
   * This method gets the rating of the Show
   */
  @Override
  public int getRating() {
    return this.rating;
  }

  /**
   * This method checks if the Show is available on the provider parameter
   * 
   * @param provider - Show provider we are checking if show is available on
   */
  @Override
  public boolean isAvailableOn(String provider) {
    return this.providers.contains(provider);
  }

}

