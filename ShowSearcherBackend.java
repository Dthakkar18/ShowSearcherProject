
import java.util.ArrayList;
import java.util.List;

public class ShowSearcherBackend implements IShowSearcherBackend {

    protected HashTableSortedSets<String, IShow> wordToTitle = new HashTableSortedSets<String, IShow>();
    protected HashTableSortedSets<Integer, IShow> yearToTitle = new HashTableSortedSets<Integer, IShow>();
    List<String> provider = new ArrayList<>(); // array of the providers that we are using for filtering
    List<Boolean> filter = new ArrayList<>(); // whether the filters for the providers are on or off

    /**
     * adds show to backend database
     * 
     * @param show show that should be added to database
     */
    @Override
    public void addShow(IShow show) {
        yearToTitle.add(show.getYear(), show);
        String [] split = show.getTitle().split(" ",0); // splits words up from string
        for (String str: split) {
            wordToTitle.add(str, show);
        }
    }

    /**
     * gets total number of shows in database
     * 
     * @return total number of shows in database
     */
    @Override
    public int getNumberOfShows() {
        return yearToTitle.size();
    }

    /**
     * sets a filter to search
     */
    @Override
    public void setProviderFilter(String provider, boolean filter) {
        this.provider.add(provider);
        this.filter.add(filter);
    }

    /**
     * get whether a filter for a provider is on
     * 
     * @param provider to check for is filter is on or off
     * @return true if filter for given provider is on, and false is not
     */
    @Override
    public boolean getProviderFilter(String provider) {
        return filter.get(this.provider.indexOf(provider));
    }

    /**
     * turns filter on or off for given provider
     * 
     * @param provider the filter of the provider that you want to toggle on or off
     */
    @Override
    public void toggleProviderFilter(String provider) {
        // if provider isn't set yet
        if (this.provider.contains(provider)==false) {
            return; // nothing is done
        }
        // if filter for provider is on
        if (filter.get(this.provider.indexOf(provider))) {
            filter.set(this.provider.indexOf(provider), false);
        } else { // if filter for provider is off
            filter.set(this.provider.indexOf(provider), true);
        }
    }

    /**
     * look-up shows by title word
     * the results are filtered according to the provider filters set above
     * 
     * @param word word in title of the movie that we want to search for
     * @return List of shows which fit the requirements
     */
    @Override
    public List<IShow> searchByTitleWord(String word) {
        List<String>activeFilters= new ArrayList<>(); // array to collect all the active filters
        for (int i=0;i<filter.size();i++) { // iterate through filters to check for active filters
            if (filter.get(i)) {
                activeFilters.add(provider.get(i));
            }
        }
        List<IShow> shows = new ArrayList<>();
        if (wordToTitle.get(word)!=null) { // if there are shows with the word in it
            for (IShow show: (List<IShow>) wordToTitle.get(word)) {
                if (activeFilters.size()!=0) { // if there is a filter on
                    for (String provider: activeFilters) {
                        if (show.isAvailableOn(provider)) { // ensure show is on provider
                            shows.add(show);
                        }
                    } 
                } else {
                    shows.add(show);
                }
            }
        }
        return shows;
    }

    /**
     * look-up shows by the year the movie is created the results are filtered according to the
     * provider filters set above
     * 
     * @param year year in which movie is made that we want to search for
     * @return List of shows which fit the requirements
     */
    @Override
    public List<IShow> searchByYear(int year) {
        List<String> activeFilters = new ArrayList<>(); // array to collect all the active filters
        for (int i = 0; i < filter.size(); i++) { // iterate through filters to check for active filters
            if (filter.get(i)) {
                activeFilters.add(provider.get(i));
            }
        }
        List <IShow> shows = new ArrayList<>();
        if (yearToTitle.get(year)!=null) { // if there are movies in that year
            for (IShow show : (List<IShow>) yearToTitle.get(year)) {
                if (activeFilters.size() != 0) { // if a filter is on
                    for (String provider : activeFilters) {
                        if (show.isAvailableOn(provider)) {
                            shows.add(show);
                        }
                    }
                } else {
                    shows.add(show);
                }
            }
        }
        return shows;   
    }
}
