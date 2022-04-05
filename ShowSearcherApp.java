import java.util.List;

public class ShowSearcherApp {
    public static void main(String[] args) throws Exception {
//        IShowLoader loader = new ShowLoader(); //new ShowLoader();
//       List<IShow> shows = loader.loadShows("tv_shows.csv");
//        IShowSearcherBackend backend = new ShowSearcherBackend(); //new ShowSearcherBackend();
//	for(IShow show : shows) backend.addShow(show);
//	IShowSearcherFrontend frontend = new ShowSearcherFrontend(backend); //new ShowSearcherFrontend(backend);
//        frontend.runCommandLoop();

        IShowLoader loader = new ShowLoader();
	List<IShow> shows = loader.loadShows("tv_shows.csv");
	IShowSearcherBackend backend = new ShowSearcherBackend();
	
	/*This was used to test it with 20 shows not whole csv
	 *
	System.out.println("Adding only 20 shows to database\n");
        for(int i = 0; i < 20; i++){
		System.out.println(shows.get(i).getTitle());
		backend.addShow(shows.get(i));
	}
	System.out.println("\n");
	*/

	//This for loop adds all the shows form csv to database
	for(IShow show : shows){
		//System.out.println(show.getTitle());
	       	backend.addShow(show);
	}
	
        IShowSearcherFrontend frontend = new ShowSearcherFrontend(backend);
	frontend.runCommandLoop();
    }
}
