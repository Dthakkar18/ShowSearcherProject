// --== CS400 Project One File Header ==--
// Name: Nicha Vang
// CSL Username: nicha
// Email: nvang35@wisc.edu
// Lecture #: 002
// Notes to Grader: none

public class ShowPlaceHolder implements IShow {
  protected String title = "";
  protected int year;
  protected int rating;
  protected String providers;
  
  // constructor args (String title, int year, int rating, String providers)
  // where the providers string includes the names of every streaming source
  public ShowPlaceHolder(String title, int year, int rating, String providers) {
    this.title = title;
    this.year = year;
    this.rating = rating;
    this.providers = providers;
    
  }
  @Override
  public int compareTo(IShow o) {
    // TODO Auto-generated method stub
    return 0;
  }

  @Override
  public String getTitle() {

    return title;
  }

  @Override
  public int getYear() {
    return this.year;
  }

  @Override
  public int getRating() {
    // TODO Auto-generated method stub
    return this.rating;
  }

//checks show availability
  @Override
  public boolean isAvailableOn(String provider) {
    if (this.providers.contains(provider)) {
      return true;
    }
    return false;
  }

}
