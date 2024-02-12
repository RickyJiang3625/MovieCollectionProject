import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Collections;


public class MovieCollection
{
    private ArrayList<Movie> movies;
    private Scanner scanner;
    public static ArrayList<String> GENRES=new ArrayList<String>();
    private static ArrayList<Movie> ACTION_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> ADVENTURE_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> ANIMATION_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> COMEDY_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> CRIME_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> DOCUMENTARY_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> DRAMA_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> FAMILY_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> FANTASY_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> FOREIGN_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> HISTORY_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> HORROR_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> MUSIC_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> MYSTERY_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> ROMANCE_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> SCIFI_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> TV_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> THRILLER_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> WAR_MOVIES = new ArrayList<Movie>();
    private static ArrayList<Movie> WESTERN_MOVIES = new ArrayList<Movie>();

    public MovieCollection(String fileName)
    {
        importMovieList(fileName);
        scanner = new Scanner(System.in);
    }

    public ArrayList<Movie> getMovies()
    {
        return movies;
    }

    public void menu()
    {
        String menuOption = "";

        System.out.println("Welcome to the movie collection!");
        System.out.println("Total: " + movies.size() + " movies");

        while (!menuOption.equals("q"))
        {
            System.out.println("------------ Main Menu ----------");
            System.out.println("- search (t)itles");
            System.out.println("- search (k)eywords");
            System.out.println("- search (c)ast");
            System.out.println("- see all movies of a (g)enre");
            System.out.println("- list top 50 (r)ated movies");
            System.out.println("- list top 50 (h)igest revenue movies");
            System.out.println("- (q)uit");
            System.out.print("Enter choice: ");
            menuOption = scanner.nextLine();

            if (!menuOption.equals("q"))
            {
                processOption(menuOption);
            }
        }
    }

    private void processOption(String option)
    {
        if (option.equals("t"))
        {
            searchTitles();
        }
        else if (option.equals("c"))
        {
            searchCast();
        }
        else if (option.equals("k"))
        {
            searchKeywords();
        }
        else if (option.equals("g"))
        {
            listGenres();
        }
        else if (option.equals("r"))
        {
            listHighestRated();
        }
        else if (option.equals("h"))
        {
            listHighestRevenue();
        }
        else
        {
            System.out.println("Invalid choice!");
        }
    }

    private void searchTitles()
    {
        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();
        while(results.isEmpty()){
        System.out.print("Enter a tital search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();



        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String movieTitle = movies.get(i).getTitle();
            movieTitle = movieTitle.toLowerCase();

            if (movieTitle.indexOf(searchTerm) != -1)
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        if(!results.isEmpty()){
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }}}

    private void sortResults(ArrayList<Movie> listToSort)
    {
        for (int j = 1; j < listToSort.size(); j++)
        {
            Movie temp = listToSort.get(j);
            String tempTitle = temp.getTitle();

            int possibleIndex = j;
            while (possibleIndex > 0 && tempTitle.compareTo(listToSort.get(possibleIndex - 1).getTitle()) < 0)
            {
                listToSort.set(possibleIndex, listToSort.get(possibleIndex - 1));
                possibleIndex--;
            }
            listToSort.set(possibleIndex, temp);
        }
    }

    private void displayMovieInfo(Movie movie)
    {
        System.out.println();
        System.out.println("Title: " + movie.getTitle());
        System.out.println("Tagline: " + movie.getTagline());
        System.out.println("Runtime: " + movie.getRuntime() + " minutes");
        System.out.println("Year: " + movie.getYear());
        System.out.println("Directed by: " + movie.getDirector());
        System.out.println("Cast: " + movie.getCast());
        System.out.println("Overview: " + movie.getOverview());
        System.out.println("User rating: " + movie.getUserRating());
        System.out.println("Box office revenue: " + movie.getRevenue());
    }

    private void searchCast()
    {

        ArrayList<String> results = new ArrayList<String>();
        while(results.isEmpty()){
        System.out.print("Enter a cast member search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        for (Movie movie : movies) {
            String[] temp = movie.getCast().split("\\|");
            for (String actor : temp) {
                if (actor.toLowerCase().contains(searchTerm))
                    if (!results.contains(actor))
                        results.add(actor);
            }
        }
        Collections.sort(results);
        if(!results.isEmpty()){
        for(int j=0;j<results.size();j++){
            String castMember = results.get(j);

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = j + 1;

            System.out.println("" + choiceNum + ". " + castMember);
        }
        System.out.println("Which cast member would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();
        String chosen=results.get(choice-1);
        ArrayList <Movie> movieResults= new ArrayList<Movie>();
        for(Movie m:movies){
            if(m.getCast().toLowerCase().contains(chosen.toLowerCase())){
                movieResults.add(m);
            }
        }
        sortResults(movieResults);
        for(int k=0;k<movieResults.size();k++){
            String title = movieResults.get(k).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = k + 1;

            System.out.println("" + choiceNum + ". " + title);
        }
        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice1 = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = movieResults.get(choice1 - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }}}


    private void searchKeywords()
    {
        System.out.print("Enter a keyword search term: ");
        String searchTerm = scanner.nextLine();

        // prevent case sensitivity
        searchTerm = searchTerm.toLowerCase();

        // arraylist to hold search results
        ArrayList<Movie> results = new ArrayList<Movie>();

        // search through ALL movies in collection
        for (int i = 0; i < movies.size(); i++)
        {
            String keywords = movies.get(i).getKeywords();
            keywords = keywords.toLowerCase();

            if (keywords.indexOf(searchTerm) != -1)
            {
                //add the Movie objest to the results list
                results.add(movies.get(i));
            }
        }

        // sort the results by title
        sortResults(results);

        // now, display them all to the user
        for (int i = 0; i < results.size(); i++)
        {
            String title = results.get(i).getTitle();

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }

        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();

        Movie selectedMovie = results.get(choice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }

    private void listGenres()
    {

    if(GENRES.isEmpty()){
        String genres="";
        for(Movie movie:movies){
            genres+=movie.getGenres()+"|";
        }
        String[] genreList=genres.split("\\|");
        for(String i:genreList){
            if(!GENRES.contains(i)){
                GENRES.add(i);
            }

        }
        Collections.sort(GENRES);
        for (int i = 0; i < GENRES.size(); i++)
        {
            String title = GENRES.get(i);

            // this will print index 0 as choice 1 in the results list; better for user!
            int choiceNum = i + 1;

            System.out.println("" + choiceNum + ". " + title);
        }
        System.out.println("Which genre would you like to learn more about?");
        System.out.print("Enter number: ");

        int choice = scanner.nextInt();
        scanner.nextLine();
        ArrayList<Movie> moviesFitGenre=new ArrayList<Movie>();
        if (ACTION_MOVIES.isEmpty()) {
            genreSortMovies();
        }
        if (choice == 1) {
            moviesFitGenre = ACTION_MOVIES;
        } else if (choice == 2) {
            moviesFitGenre = ADVENTURE_MOVIES;
        } else if (choice == 3) {
            moviesFitGenre = ANIMATION_MOVIES;
        } else if (choice == 4) {
            moviesFitGenre = COMEDY_MOVIES;
        } else if (choice == 5) {
            moviesFitGenre = CRIME_MOVIES;
        } else if (choice == 6) {
            moviesFitGenre = DOCUMENTARY_MOVIES;
        } else if (choice == 7) {
            moviesFitGenre = DRAMA_MOVIES;
        } else if (choice == 8) {
            moviesFitGenre = FAMILY_MOVIES;
        } else if (choice == 9) {
            moviesFitGenre = FANTASY_MOVIES;
        } else if (choice == 10) {
            moviesFitGenre = FOREIGN_MOVIES;
        } else if (choice == 11) {
            moviesFitGenre = HISTORY_MOVIES;
        } else if (choice == 12) {
            moviesFitGenre = HORROR_MOVIES;
        } else if (choice == 13) {
            moviesFitGenre = MUSIC_MOVIES;
        } else if (choice == 14) {
            moviesFitGenre = MYSTERY_MOVIES;
        } else if (choice == 15) {
            moviesFitGenre = ROMANCE_MOVIES;
        } else if (choice == 16) {
            moviesFitGenre = SCIFI_MOVIES;
        } else if (choice == 17) {
            moviesFitGenre = TV_MOVIES;
        } else if (choice == 18) {
            moviesFitGenre = THRILLER_MOVIES;
        } else if (choice == 19) {
            moviesFitGenre = WAR_MOVIES;
        } else if (choice == 20) {
            moviesFitGenre = WESTERN_MOVIES;
        }
        sortResults(moviesFitGenre);
        for(int i=0;i<moviesFitGenre.size();i++){
            String title=moviesFitGenre.get(i).getTitle();
            int choiceNum=i+1;
            System.out.println(""+choiceNum+". "+title);
        }
        System.out.println("Which movie would you like to learn more about?");
        System.out.print("Enter number: ");
        int movieChoice = scanner.nextInt();
        scanner.nextLine();
        Movie selectedMovie = moviesFitGenre.get(movieChoice - 1);

        displayMovieInfo(selectedMovie);

        System.out.println("\n ** Press Enter to Return to Main Menu **");
        scanner.nextLine();
    }
    }

    private void listHighestRated()
    {

    }

    private void listHighestRevenue()
    {

    }
    private void genreSortMovies(){
        for(Movie movie:movies){
            if(movie.getGenres().contains("Action")){
                ACTION_MOVIES.add(movie);
            }
            if(movie.getGenres().contains("Adventure")){
                ADVENTURE_MOVIES.add(movie);
            }
            if(movie.getGenres().contains("Fantasy")){
                FANTASY_MOVIES.add(movie);
            }
            if(movie.getGenres().contains("Science Fiction")){
                SCIFI_MOVIES.add(movie);
            }
            if(movie.getGenres().contains("Drama")){
                DRAMA_MOVIES.add(movie);
            }
            if(movie.getGenres().contains("Romance")){
                ROMANCE_MOVIES.add(movie);
            }
            if(movie.getGenres().contains("Thriller")){
                THRILLER_MOVIES.add(movie);
            }
            if (movie.getGenres().contains("Crime")){
                CRIME_MOVIES.add(movie);
            }
            if(movie.getGenres().contains("Family")){
                FAMILY_MOVIES.add(movie);
            }
            if(movie.getGenres().contains("Animation")){
                ANIMATION_MOVIES.add(movie);
            }
            if(movie.getGenres().contains("Comedy")){
                COMEDY_MOVIES.add(movie);
            }
            if(movie.getGenres().contains("Mystery")){
                MYSTERY_MOVIES.add(movie);
            }
            if(movie.getGenres().contains("War")){
                WAR_MOVIES.add(movie);
            }
            if(movie.getGenres().contains("Horror")){
                HORROR_MOVIES.add(movie);
            }
            if(movie.getGenres().contains("Western")){
                WESTERN_MOVIES.add(movie);
            }
            if(movie.getGenres().contains("History")){
                HISTORY_MOVIES.add(movie);
            }
            if(movie.getGenres().contains("Music")){
                MUSIC_MOVIES.add(movie);
            }
            if(movie.getGenres().contains("Documentary")){
                DOCUMENTARY_MOVIES.add(movie);
            }
            if(movie.getGenres().contains("Foreign")){
                FOREIGN_MOVIES.add(movie);
            }
            if(movie.getGenres().contains("TV Movie")){
                TV_MOVIES.add(movie);
            }
        }
    }
    private void importMovieList(String fileName)
    {
        try
        {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line = bufferedReader.readLine();

            movies = new ArrayList<Movie>();

            while ((line = bufferedReader.readLine()) != null)
            {
                String[] movieFromCSV = line.split(",");

                String title = movieFromCSV[0];
                String cast = movieFromCSV[1];
                String director = movieFromCSV[2];
                String tagline = movieFromCSV[3];
                String keywords = movieFromCSV[4];
                String overview = movieFromCSV[5];
                int runtime = Integer.parseInt(movieFromCSV[6]);
                String genres = movieFromCSV[7];
                double userRating = Double.parseDouble(movieFromCSV[8]);
                int year = Integer.parseInt(movieFromCSV[9]);
                int revenue = Integer.parseInt(movieFromCSV[10]);

                Movie nextMovie = new Movie(title, cast, director, tagline, keywords, overview, runtime, genres, userRating, year, revenue);
                movies.add(nextMovie);
            }
            bufferedReader.close();
        }
        catch(IOException exception)
        {
            // Print out the exception that occurred
            System.out.println("Unable to access " + exception.getMessage());
        }
    }
}