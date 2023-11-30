package watch_it;
import java.io.*;
import java.time.Month;
import java.util.ArrayList;
import java.util.HashMap;
import java.time.LocalDate;
import java.util.Map;

public class Admin {
    //gonna fix the try/catch/finally later --------
    //there's more work after the User class ------- maybe ?

    private String username;
    private int password;
    //counters gonna be pairs ,key for the enum class value for repentance -- javafx???
    public static int basicPlanCounter;
    public static int standardPlanCounter;
    public static int premiumPlanCounter;
    private static short PLANINDEX = 7;
    private static final short USERNAMEINDEX = 1;
    private static final short TYPEINDEX = 0;
    private static short STARTDATEINDEX;
    public static ArrayList<String> movies;
    public static ArrayList<String> directors;
    public static ArrayList<String> casts;
    public static ArrayList<String> admins;
    public static ArrayList<String> users;


//    public static ArrayList<String> readFile(File file) {
//        ArrayList<String> arrayList = new ArrayList<String>();
//        try {
//            String line = "";
//            BufferedReader b = new BufferedReader(new FileReader(file));
//            while ((line = b.readLine()) != null) {
//                arrayList.add(line);
//            }
//            b.close();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//        return arrayList;
//    }
    public static void readFile(File file) {
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            String line = "";
            BufferedReader b = new BufferedReader(new FileReader(file));
            while ((line = b.readLine()) != null) {
                String [] row = line.split(",");
                if(row[TYPEINDEX].equals("user"))
                    users.add(line);
                else if (row[TYPEINDEX].equals("cast"))
                    casts.add(line);
                else if (row[TYPEINDEX].equals("movie"))
                    movies.add(line);
                else if(row[TYPEINDEX].equals("director"))
                    directors.add(line);
            }
            b.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void writeOnFile(File file){
        try {
            BufferedWriter b = new BufferedWriter(new FileWriter(file));
            for(String index:users){
                b.write(index);
                b.newLine();
            }
            for(String index:movies){
                b.write(index);
                b.newLine();
            }
            for(String index:casts){
                b.write(index);
                b.newLine();
            }
            for(String index:directors){
                b.write(index);
                b.newLine();
            }
            for(String index:admins){
                b.write(index);
                b.newLine();
            }
            b.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    //if the record exists in database >> gonna return the whole record[don't forget to split it]
    //otherwise gonna return null [make sure to check before dealing with return val].

    //returned string isn't splitted
    public static String existsInFile(ArrayList<String> arrayList, String username){
        boolean exist = false;
        String[] eachLine;
        for (String index: arrayList){
            eachLine = index.split(",");
            if(eachLine[USERNAMEINDEX].equals(username)){
                return index;
            }
        }
        return null;
    }

    //record to be deleted is not splitted
    public static void deleteOneRecord(ArrayList<String> arrayList, String record){
        arrayList.remove(record);
    }
    public static void changeCell(ArrayList<String> arrayList, String username, String oldCell, String newCell){
        String recuiredLine = existsInFile(arrayList, username);
        if(recuiredLine != null) {
            String[] row = recuiredLine.split(",");
            try {
                for (int index = 0; index < row.length; index++) {
                    if (row[index].equals(username))
                        row[index] = newCell;
                }
            }catch (ArrayIndexOutOfBoundsException e){
            }
            arrayList.set(arrayList.indexOf(recuiredLine), String.join(",", row));
        }
    }
    //enum ----------------
    public static String getSpecificCellForUser(ArrayList<String> arrayList, String username, int index){
        String wantedRecord = existsInFile(arrayList, username);
        String []row = wantedRecord.split(",");
        return row[index];
    }
    //working just fine ^^^
    public static Subscription.Plans getMostSubscripedPlan(ArrayList<String> arrayList){
        Subscription.Plans plan = null;
        for (String index:arrayList){
            String []line = index.split(",");
            HashMap<Integer,Subscription.Plans> map = new HashMap<>();
            if(line[PLANINDEX].equals("basic"))
                basicPlanCounter++;
            else if (line[PLANINDEX].equals("standard"))
                standardPlanCounter++;
            else
                premiumPlanCounter++;
            int max = Math.max(standardPlanCounter,Math.max(basicPlanCounter,premiumPlanCounter));

            map.put(basicPlanCounter,Subscription.Plans.BASIC);
            map.put(standardPlanCounter,Subscription.Plans.STANDARD);
            map.put(premiumPlanCounter,Subscription.Plans.PREMIUM);

            plan = map.get(max);
        }
        return plan;
    }
//    public static Month getMonthWithMostRevenue(ArrayList<String> arrayList){
//        Month month = null;
//        int[] months = new int [12];
//        for (String index: arrayList){
//            String []line = index.split(",");
//
//            if(line[PLANINDEX].equals("basic"))
//        }
//        return month;
//    }
    //all as strings
    static void getUserMovieLists(String user, ArrayList<String> watched_movies, ArrayList<String> toBeWatched_movies){
        boolean toBeWatched = false;
        boolean watched = false;
        int watchedCounter = -1;
        int toBeWatchedCounter = 0;
        String[] eachLine = user.split(",");
        for (String data:eachLine) {
            if(data.equals("watched"))
                watched = true;
            if(data.equals("to be watched")) {
                watched = false;
                toBeWatched = true;
            }
            if(watched) {
                watchedCounter++;
                if(watchedCounter != 0 && watchedCounter%2!=0)
                    watched_movies.add(data);
            }
            if(toBeWatched && toBeWatchedCounter == 1)
                toBeWatched_movies.add(data);
            if (toBeWatched && toBeWatchedCounter == 0)
                toBeWatchedCounter = 1;
        }
    }
    //as objects
    static void getUserMovieLists(String userData,User user,ArrayList<UserWatchRecord> watched_movies, ArrayList<Movie> toBeWatched_movies){
        if(userData != null)
        {
            boolean toBeWatched = false;
            boolean watched = false;
            int watchedCounter = -1;
            int toBeWatchedCounter = 0;
            Movie movie_to_watch = new Movie();
            String[] eachLine = userData.split(",");
            for (int i=0; i<eachLine.length; i++) {
                if (eachLine[i].equals("watched"))
                    watched = true;
                if (eachLine[i].equals("to be watched")) {
                    watched = false;
                    toBeWatched = true;
                }
                if (watched){
                    watchedCounter++;
                    if(watchedCounter != 0 && watchedCounter % 2 != 0 )
                    {
                        for (String oneMovie : movies) {
                            String[] arr = oneMovie.split(",");
                            if (arr[1].equals(eachLine[i]))
                                watched_movies.add(getWatchedMovie(arr[i], Integer.parseInt(arr[i+1])));
                        }
                    }
                }
                if (toBeWatched && toBeWatchedCounter == 1)
                    toBeWatched_movies.add(getMovie(eachLine[i]));
                if (toBeWatched && toBeWatchedCounter == 0)
                    toBeWatchedCounter = 1;
            }
        }
        user.Watched_Movies.addAll(watched_movies);
        user.Movies_For_Later.addAll(toBeWatched_movies);
    }
    static UserWatchRecord getWatchedMovie(String title, int rate){
        UserWatchRecord movie = new UserWatchRecord();
        for (String oneMovie: movies){
            String []arr = oneMovie.split(",");
            if(arr[1].equals(title))
            {
                movie.movie.setTitle(arr[1]);
                movie.movie.setId(Integer.parseInt(arr[2]));
                movie.movie.setRelease_date(LocalDate.parse(arr[3]));
                // movie.setDuration(Integer.parseInt(arr[4]));
                //director,case lazm hykono strings msh obj ,wel class bta3hom hykon el movie string msh obj.
                //movie.setDirector(arr[7]);
                movie.movie.setBudget(Double.parseDouble(arr[11]));
                movie.movie.setCountry(arr[10]);
                movie.setRating(rate);
                //movie.setDirector(getDirector());
            }
        }
        return movie;
    }
    static Movie getMovie (String title){
        Movie movie = new Movie();
        for (String oneMovie: movies){
            String []arr = oneMovie.split(",");
            if(arr[1].equals(title))
            {
                movie.setTitle(arr[1]);
                movie.setId(Integer.parseInt(arr[2]));
                movie.setRelease_date(LocalDate.parse(arr[3]));
                // movie.setDuration(Integer.parseInt(arr[4]));
                //director,cast ykono strings msh obj ,wel class bta3hom hykon el movie string msh obj.
                //movie.setDirector(arr[7]);
                movie.setBudget(Double.parseDouble(arr[11]));
                movie.setCountry(arr[10]);
                movie.setLanguage(arr[8]);
                movie.setImdb_score(Float.parseFloat(arr[9]));
                movie.setRevenue(Double.parseDouble(arr[12]));
                movie.setPoster_path(arr[13]);
                //movie.setDirector(getDirector());
            }
        }
        return movie;
    }
    static Director getDirector(String directorName){
        Director director = new Director();
        String[] name = directorName.split(" ");
        for (String oneDirector:directors){
            String[] data = oneDirector.split(",");
            if(data[1].equals(name[0]) && data[2].equals(name[1])){
                director.setFirst_Name(data[1]);
                director.setSecond_Name(data[2]);
                director.setAge(Integer.parseInt(data[3]));
                director.setGender(data[4]);
                director.setNationality(data[5]);
                //movies
            }
        }
        return director;
    }
    static Cast getCast(String name){
        //ArrayList<Cast> cast = new ArrayList<Cast>();
        Cast eachCast = new Cast();
        String[] castName = name.split(" ");
        for (String oneCast: casts){
            String[] data = oneCast.split(",");
            if(data[1].equals(castName[0]) && data[2].equals(castName[1])){
                eachCast.setFirst_Name(data[1]);
                eachCast.setSecond_Name(data[2]);
                eachCast.setGender(data[4]);
                eachCast.setAge(Integer.parseInt(data[3]));
                eachCast.setNationality(data[5]);
                //movies
                //cast.add(eachCast);
            }
        }
        return eachCast;
    }
    //name on getMovies is full name >> first and last combined.
    static ArrayList<String> getMovies(String name, ArrayList<String> allMovies){
        ArrayList<String> requiredMovies = new ArrayList<String>();
        for (String data: allMovies){
            String []arr = data.split(",");
            for(String index: arr)
                //if cast/director is on this specific movie, then i'm gonna add name of the movie to the list.
                if(index.equals(name))
                    requiredMovies.add(arr[1]);
        }
        return requiredMovies;
    }
}
