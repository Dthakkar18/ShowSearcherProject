// --== CS400 Project One File Header ==--
// Name: Nicha Vang
// CSL Username: nicha
// Email: nvang35@wisc.edu
// Lecture #: 002
// Notes to Grader: none

import java.util.ArrayList;
import java.util.List;

public class ShowSearcherBackendPlaceHolder implements IShowSearcherBackend {
  List<IShow> listShow = new ArrayList<IShow>();
  boolean filterOn = false;
  String filterProvider;
  boolean filterNetflix = false;;
  boolean filterHulu = false;
  boolean filterPrimeVideo = false;
  boolean filterDisneyPlus = false;

  /**
   * adds show to backend database
   */
  @Override
  public void addShow(IShow show) {
    // TODO Auto-generated method stub

  }

  /**
   * retrieve number of shows in database
   */
  @Override
  public int getNumberOfShows() {
    return 100;
  }

  /**
   * set the desired provider filters before calling either search method: (all providers are
   * included in search results by default)
   */
  @Override
  public void setProviderFilter(String provider, boolean filter) {
    this.filterOn = filter;
    filterProvider = provider;
    if (provider.equals("Netflix")) {
      filterNetflix = true;
    } else if (provider.equals("Hulu")) {
      filterHulu = true;
    } else if (provider.equals("Prime Video")) {
      filterPrimeVideo = true;
    } else if (provider.equals("DisneyPlus")) {
      filterDisneyPlus = true;
    }
  }

  @Override
  public boolean getProviderFilter(String provider) {
    return true;
  }

  @Override
  public void toggleProviderFilter(String provider) {
    this.filterOn = false;

  }

  /**
   * create shows with specific titles
   */
  @Override
  public List<IShow> searchByTitleWord(String word) {
    ShowPlaceHolder showName1 = new ShowPlaceHolder("Spider-Man", 2001, 82, "Hulu, Disney+");
    ShowPlaceHolder showName2 = new ShowPlaceHolder("Spider-Man 2", 2004, 84, "Prime Video");
    ShowPlaceHolder showName3 = new ShowPlaceHolder("Spider-Man 3", 2007, 84, "Disney+");
    ShowPlaceHolder showName4 = new ShowPlaceHolder("The Amazing Spider-Man", 2012, 83, "Hulu");
    ShowPlaceHolder showName5 =
        new ShowPlaceHolder("The Amazing Spider-Man 2", 2014, 85, "Prime Video");

    listShow.add(showName1);
    listShow.add(showName2);
    listShow.add(showName3);
    listShow.add(showName4);
    listShow.add(showName5);

    // filters out Hulu
    if (this.filterOn == true && this.filterProvider.equals("Hulu")) {
      listShow.remove(showName4);
      return listShow;
    }
    // filters out Prime Video
    if (this.filterOn == true && this.filterProvider.equals("Prime Video")) {
      listShow.remove(showName2);
      listShow.remove(showName5);
      return listShow;
    }
    // filters out Disney+
    if (this.filterOn == true && this.filterProvider.equals("Disney+")) {
      listShow.remove(showName3);
      return listShow;
    }
    // filters out Netflix
    if (this.filterOn == true && this.filterProvider.equals("Netflix")) {
      return listShow;
    }

    return listShow;
  }

  /**
   * create shows with specific year
   */
  @Override
  public List<IShow> searchByYear(int year) {
    ShowPlaceHolder showName1 = new ShowPlaceHolder("Spy Kids", 2001, 80, "Hulu");
    ShowPlaceHolder showName2 =
        new ShowPlaceHolder("The Fast and The Furious", 2001, 82, "Prime Video");
    ShowPlaceHolder showName3 = new ShowPlaceHolder("The One", 2001, 75, "Hulu");
    ShowPlaceHolder showName4 =
        new ShowPlaceHolder("Scooby-Doo and The Cyber Chase", 2001, 78, "Disney+");

    if (this.filterOn == true && this.filterProvider.equals("Hulu")) {
      listShow.add(showName2);
      listShow.add(showName4);
      return listShow;
    }
    if (this.filterOn == true && this.filterProvider.equals("Prime Video")) {
      listShow.add(showName1);
      listShow.add(showName3);
      listShow.add(showName4);
      return listShow;
    }
    if (this.filterOn == true && this.filterProvider.equals("Disney+")) {
      listShow.add(showName1);
      listShow.add(showName2);
      listShow.add(showName3);
      return listShow;
    }
    listShow.add(showName1);
    listShow.add(showName2);
    listShow.add(showName3);
    listShow.add(showName4);
    return listShow;
  }
}


