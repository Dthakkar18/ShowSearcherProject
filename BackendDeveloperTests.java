
public class BackendDeveloperTests {
{
    /**
     * Testing addShow() method to ensure if it works correctly
     * 
     * @return true is no errors, else false
     */
    public static boolean test1() {
        ShowSearcherBackend be = new ShowSearcherBackend();
        be.addShow(new Show("how i met your mother", 2001, 5, "Hulu"));
        be.addShow(new Show("transformers", 2001, 4, "Netflix"));
        be.addShow(new Show("i want to be home alone", 2002, 3, "Netflix"));
        if (be.getNumberOfShows()!=3) {
            return false;
        }
        if (be.yearToTitle.size()!=3) {
            return false;
        }
        if (be.wordToTitle.size()!=12) {
            return false;
        }
        return true;
    }
  
    /**
     * Testing searchByTitleWord() method to ensure if it works correctly
     * 
     * @return true is no errors, else false
     */
    public static boolean test2() {
        ShowSearcherBackend be = new ShowSearcherBackend();
        be.addShow(new Show("how i met your mother", 2001, 5, "Hulu"));
        be.addShow(new Show("transformers", 2001, 4, "Netflix"));
        be.addShow(new Show("i am home alone", 2002, 3, "Netflix"));
        // first if statement should only return one show called "transformers"
        if (!be.searchByTitleWord("transformers").get(0).getTitle().equals("transformers")) {
            return false;
        }
        // should return 2 shows, as they include the word "i"
        if (!be.searchByTitleWord("i").get(0).getTitle().equals("how i met your mother") || 
            be.searchByTitleWord("i").size()!=2) {
            return false;
        }
        if (!be.searchByTitleWord("i").get(1).getTitle().equals("i am home alone")) {
            return false;
        }
        be.addShow(new Show("i love pancakes",2008, 2, "Hulu"));
        if (be.searchByTitleWord("i").size()!=3) {
            return false;
        }
        return true;
    }
  
    /**
     * Testing searchByYear() method to ensure if it works correctly
     * 
     * @return true is no errors, else false
     */
    public static boolean test3() {
        ShowSearcherBackend be = new ShowSearcherBackend();
        be.addShow(new Show("how i met your mother", 2001, 5, "Hulu"));
        be.addShow(new Show("transformers", 2001, 4, "Netflix"));
        be.addShow(new Show("i am home alone", 2002, 3, "Netflix"));
        be.addShow(new Show("i love pancakes", 2008, 2, "Hulu"));
        // when searching by year 2008, should only return one show called "i love pancakes"
        if (be.searchByYear(2008).size()!=1 || !be.searchByYear(2008).get(0).getTitle().equals("i love pancakes")) {
            return false;
        }
        // when searching by year 2001, should turn 2 shows "how i met your mother" and "transformers"
        if (be.searchByYear(2001).size()!=2 || !be.searchByYear(2001).get(0).getTitle().equals("how i met your mother") 
                || !be.searchByYear(2001).get(1).getTitle().equals("transformers")) {
                return false; 
            }
        return true;
    } 
  
    /**
     * Testing filter works in methods
     * 
     * @return true is no errors, else false
     */
    public static boolean test4() {
        ShowSearcherBackend be = new ShowSearcherBackend();
        be.addShow(new Show("how i met your mother", 2001, 5, "Hulu"));
        be.addShow(new Show("transformers", 2001, 4, "Netflix"));
        be.addShow(new Show("i am home alone", 2002, 3, "Netflix"));
        be.addShow(new Show("i love pancakes", 2008, 2, "Hulu"));
        be.setProviderFilter("Disney+", true);
  
        // should return empty list as no shows in added database is shown on Disney+
        if (be.searchByTitleWord("i").size()!=0) {
            return false;
        }
        
        be.setProviderFilter("Hulu", true);
        // return Hulu shows with the word "i", should give us two results in list
        if (be.searchByTitleWord("i").size()!=2 || !be.searchByTitleWord("i").get(0).getTitle().equals("how i met your mother")) {
            return false;
        }
        be.setProviderFilter("Netflix", true);
        // should give us both Hulu AND Netflix shows with the word "i", should yield 3 results in list
        if (be.searchByTitleWord("i").size() != 3 || !be.searchByTitleWord("i").get(2).getTitle()
                .equals("i love pancakes")) {
            return false;            
        }
  
        return true;
    }
  
    /**
     * Testing filter methods and ensuring that they work
     * 
     * @return true is no errors, else false
     */
    public static boolean test5() {
        ShowSearcherBackend be = new ShowSearcherBackend();
        be.addShow(new Show("how i met your mother", 2001, 5, "Hulu"));
        be.addShow(new Show("transformers", 2001, 4, "Netflix"));
        be.addShow(new Show("i am home alone", 2002, 3, "Netflix"));
        be.addShow(new Show("i love pancakes", 2008, 2, "Hulu"));
  
        be.setProviderFilter("Hulu", true);
        be.setProviderFilter("Netflix", true);
        // same as testing method above, should yield us 3 shows in list
        if (be.searchByTitleWord("i").size() != 3
                || !be.searchByTitleWord("i").get(2).getTitle().equals("i love pancakes")) {
            return false;
        }
  
        be.toggleProviderFilter("Netflix"); // should turn Netflix filter from on to off
        // should now provide us with a list of only Hulu shows as that filter is still on and Netflix is off
        if (be.searchByTitleWord("i").size() != 2
                || !be.searchByTitleWord("i").get(0).getTitle().equals("how i met your mother")) {
            return false;
        }
  
        // Testing if Netflix filter is now off
        if (be.getProviderFilter("Netflix")) {
            return false;
        }
  
        // Testing if Netflix filter is still on
        if (!be.getProviderFilter("Hulu")) {
            return false;
        }
  
        // turns Hulu filter off
        be.toggleProviderFilter("Hulu");
        // no filters applied, so all shows containing letter "i" should be in list
        if (be.searchByTitleWord("i").size() != 3) {
            return false;
        }
  
        // tests that Hulu is now off
        if (be.getProviderFilter("Hulu")) {
            return false;
        }
        return true;
    }
  
    public static void main(String[] args) {
	//System.out.println("Test1: " + test1());
	//System.out.println("Test2: " + test2());
        //System.out.println("Test3: " + test3());
	//System.out.println("Test4: " + test4());
	//System.out.println("Test5: " + test5());
	//System.out.println(test5() && test4() && test3() && test2() && test1());
    }
}

public static boolean test1() {
		try {

		      // creating keys and values
		      String keyOne = "Shrek";
		      Integer valueOne = 1;

		      String keyTwo = "Shrek";
		      Integer valueTwo = 2;

		      String keyThree = "Ted";
		      Integer valueThree = 4;


		      // create map
		      HashTableSortedSets<String, Integer> map = new HashTableSortedSets<>();

		      //adding values in map
		      map.add(keyOne, valueOne);
		      map.add(keyTwo, valueTwo);
		      map.add(keyThree, valueThree);

		      //checking size of map
		      if(map.size() != 3) {
		        return false;
		      }


		    }catch(Exception e) {
		      return false; // no exception should be thrown
		    }

		    return true;
		  }
	/**
	   * Testing remove method
	   * @return true if test pass and false otherwise
	   */
	public static boolean test2() {
		try {

		      // creating keys and values
		      String keyOne = "Shrek";
		      Integer valueOne = 1;

		      String keyTwo = "Tony";
		      Integer valueTwo = 5;

		      String keyThree = "Ted";
		      Integer valueThree = 4;


		      // create map
		      HashTableSortedSets<String, Integer> map = new HashTableSortedSets<>();

		      //adding key value paires and making sure they are in the map
		      map.add(keyOne, valueOne);
		      map.add(keyTwo, valueTwo);
		      map.add(keyThree, valueThree);
		      if(map.containsKey(keyOne) == false) {
		        return false;
		      }
		      if(map.containsKey(keyTwo) == false) {
		        return false;
		      }
		      if(map.containsKey(keyThree) == false) {
		        return false;
		      }

		      // removing each node and making sure it is not in map
		      map.remove(keyOne);
		      map.remove(keyTwo);
		      map.remove(keyThree);
		      if(map.containsKey(keyOne)) {
		        return false;
		      }
		      if(map.containsKey(keyTwo)) {
		        return false;
		      }
		      if(map.containsKey(keyThree)) {
		        return false;
		      }



		    }catch(Exception e) {
		      return false; // no exception should be thrown
		    }


		    return true;

		  }
	/*
	Testing search by year
	public static boolean test3() {

	}
	public static boolean test4() {

	}*/

		public static void main(String[] args) {
	        System.out.println("Test1: " + test1());
	        System.out.println("Test2: " + test2());
	        //System.out.println("Test3: " + test3());
	       // System.out.println("Test4: " + test4());
	        System.out.println(test2() && test1());
	    }


}

