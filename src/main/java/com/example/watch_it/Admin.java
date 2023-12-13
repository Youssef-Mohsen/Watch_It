package com.example.watch_it;
import java.io.*;
import java.time.Month;
import java.time.Period;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.time.LocalDate;
import java.util.Map;

public class Admin {
    private String username;
    private int password;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getPassword() {
        return password;
    }

    public void setPassword(int password) {
        this.password = password;
    }

    //counters gonna be pairs ,key for the enum class value for repentance -- javafx???
    public static int basicPlanCounter;
    public static int standardPlanCounter;
    public static int premiumPlanCounter;
    private  static final int MONTHSNUMBER = 12;
    static final short PLANINDEX = 6;
    static final short USERNAMEINDEX = 1;
    static final short TYPEINDEX = 0;
    static final short PASSWORDINDEX = 2;
    static final short FIRSTNAMEINDEX = 4;
    static final short LASTNAMEINDEX = 5;
    static final short PROFILEPICINDEX = 9;
    static final short EMAILINDEX = 7;
    static final short IDINDEX = 3;
    private static short STARTDATEINDEX = 8;

    public static ArrayList<String> movies = new ArrayList<String>();
    public static ArrayList<String> directors = new ArrayList<String>();
    public static ArrayList<String> casts = new ArrayList<String>();
    public static ArrayList<String> admins = new ArrayList<String>();
    public static ArrayList<String> users = new ArrayList<String>();
    public static ArrayList<String> topRatedMovies;

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
                else if(row[TYPEINDEX].equals("director"))
                    directors.add(line);
                else
                    admins.add(line);
            }
            b.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void readMovies(File file) {
        ArrayList<String> arrayList = new ArrayList<String>();
        try {
            String line = "";
            BufferedReader b = new BufferedReader(new FileReader(file));
            while ((line = b.readLine()) != null) {
                movies.add(line);
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
    public static boolean existsInFile(String username){;
        String[] eachLine;
        for (String index: users){
            eachLine = index.split(",");
            if(eachLine[USERNAMEINDEX].equals(username)){
                return  true;
            }
        }
        return false;
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
    public static String getSpecificCellForUser(String type, String username, int index){
        if(type.equals("user")){
            String wantedRecord = existsInFile(users, username);
            String []row = wantedRecord.split(",");
            return row[index];
        }
        else if(type.equals("admin")){

        }
        else if(type.equals("cast")){

        }
        else if (type.equals("director")) {

        }
        else if (type.equals("movie")) {

        }
        return null;
    }

    //working just fine ^^^
    static Subscription.Plans getMostSubscripedPlan(){
        Subscription.Plans plan = null;
        HashMap<Integer,Subscription.Plans> map = new HashMap<>();

        map.put(basicPlanCounter,Subscription.Plans.BASIC);
        map.put(standardPlanCounter,Subscription.Plans.STANDARD);
        map.put(premiumPlanCounter,Subscription.Plans.PREMIUM);

        int max = Math.max(standardPlanCounter,Math.max(basicPlanCounter,premiumPlanCounter));

        plan = map.get(max);

        return plan;
    }
    static void getUsersInEachPlan(){
        basicPlanCounter = 0;
        standardPlanCounter = 0;
        premiumPlanCounter = 0;
        for (String index: users){
            String []line = index.split(",");
            HashMap<Integer,Subscription.Plans> map = new HashMap<>();
            if(line[PLANINDEX].equals("basic"))
                basicPlanCounter++;
            else if (line[PLANINDEX].equals("standard"))
                standardPlanCounter++;
            else
                premiumPlanCounter++;
        }
    }

    //assuming all dates are valid and users still subscribed.
    public static int getMonth(Month month){
        return switch (month) {
            case JANUARY -> 1;
            case FEBRUARY -> 2;
            case MARCH -> 3;
            case APRIL -> 4;
            case MAY -> 5;
            case JUNE -> 6;
            case JULY -> 7;
            case AUGUST -> 8;
            case SEPTEMBER -> 9;
            case OCTOBER -> 10;
            case NOVEMBER -> 11;
            case DECEMBER -> 12;
        };
    }
    public static Month getMonth(int monthNumber){
        return switch (monthNumber) {
            case 1 -> Month.JANUARY;
            case 2 -> Month.FEBRUARY;
            case 3 -> Month.MARCH;
            case 4 -> Month.APRIL;
            case 5 -> Month.MAY;
            case 6 -> Month.JUNE;
            case 7 -> Month.JULY;
            case 8 -> Month.AUGUST;
            case 9 -> Month.SEPTEMBER;
            case 10 -> Month.OCTOBER;
            case 11 -> Month.NOVEMBER;
            case 12 -> Month.DECEMBER;
            default -> null;
        };
    }
    static Month monthWithMostRevenue(){
        Month month = null;
        int [] monthsRevenue = new int[MONTHSNUMBER];
        for(String user: users){
            String[] data = user.split(",");
            String plan = data[PLANINDEX];
            LocalDate startDate = LocalDate.parse(data[STARTDATEINDEX]);
            LocalDate todaysDate = LocalDate.now();
            int startMonth = getMonth(startDate.getMonth());
            int todaysmonth = getMonth(todaysDate.getMonth());
            int differenceMonths = todaysmonth - startMonth;
            if(startDate.getYear() != todaysDate.getYear())
                differenceMonths = (MONTHSNUMBER - startMonth) + todaysmonth;

            while(differenceMonths >= 0){
                if(plan.equals("basic")){
                    monthsRevenue[(startMonth + differenceMonths)] += 10;
                }
                else if (plan.equals("standard")) {
                    monthsRevenue[(startMonth + differenceMonths)] += 20;
                }
                else{
                    monthsRevenue[(startMonth + differenceMonths)] += 30;
                }
                differenceMonths--;
            }
        }
        int []check = new int[MONTHSNUMBER];
        System.arraycopy(monthsRevenue, 0, check, 0, MONTHSNUMBER);
        Arrays.sort(check);
        int recuiredMonth = 0;
        for(int i=0; i<MONTHSNUMBER; i++){
            if(monthsRevenue[i] == check[1])
            {
                recuiredMonth = i + 1;
                break;
            }
        }
        return getMonth(recuiredMonth);
    }
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
                if(watchedCounter %2 != 0)
                    watched_movies.add(data);
            }
            if(toBeWatched && toBeWatchedCounter == 1)
                toBeWatched_movies.add(data);
            if (toBeWatched && toBeWatchedCounter == 0)
                toBeWatchedCounter = 1;
        }
    }
    //as objects
    static void getUserMovieLists(User user){
        //to be watched elawel b3den elwatched
        String userData = existsInFile(users,user.getUser_Name());
        ArrayList<UserWatchRecord> watched_movies = new ArrayList<UserWatchRecord>();
        ArrayList<Movie> toBeWatched_movies = new ArrayList<Movie>();
        if(userData != null)
        {
            boolean toBeWatched = false;
            boolean watched = false;
            int watchedCounter = -1;
            int toBeWatchedCounter = 0;
            String[] eachLine = userData.split(",");
            for (int i=0; i<eachLine.length; i++) {
                if (eachLine[i].equals("watched")) {
                    watched = true;
                    toBeWatched = false;
                }
                if (eachLine[i].equals("to be watched")) {
                    toBeWatched = true;
                }
                if (watched){
                    watchedCounter++;
                    if(watchedCounter % 2 != 0 )
                    {
                        for (String oneMovie : movies) {
                            String[] arr = oneMovie.split(",");
                            if (arr[1].equals(eachLine[i])) {
                                if(arr[i+1].equals("null"))
                                    watched_movies.add(getWatchedMovie(arr[i], -1));
                                else {
                                    System.out.println(arr[i] + "    " + arr[i+1]);
                                    watched_movies.add(getWatchedMovie(arr[i], Integer.parseInt(arr[i + 1])));
                                }
                            }
                        }
                    }
                }
                if (toBeWatched && toBeWatchedCounter == 1)
                    toBeWatched_movies.add(getOneMovie(eachLine[i]));
                if (toBeWatched && toBeWatchedCounter == 0)
                    toBeWatchedCounter = 1;
            }
        }
        User.Watched_Movies.addAll(watched_movies);
        User.Movies_For_Later.addAll(toBeWatched_movies);
    }
    static UserWatchRecord getWatchedMovie(String title, int rate){
        UserWatchRecord movie = new UserWatchRecord();
        for (String oneMovie: movies){
            String []arr = oneMovie.split(",");
            if(arr[1].equals(title))
            {
                movie.setMovie(getOneMovie_(oneMovie));
                movie.movie.setTitle(arr[1]);
                movie.movie.setId(Integer.parseInt(arr[2]));
                movie.movie.setRelease_date(LocalDate.parse(arr[3]));
                movie.movie.setBudget(arr[11]);
                movie.movie.setCountry(arr[10]);
                movie.setRating(rate);
                //movie.setDirector(getDirector());
            }
        }
        return movie;
    }
    static void getAllMovies(){
        ArrayList<Movie> arrayList = new ArrayList<Movie>();
        for(String oneMovie: movies){
            String[] arr = oneMovie.split(",");
            System.out.println(arr[1]);
            String name = arr[1];
            arrayList.add(getOneMovie(name));
        }
        Movie.allmovies.addAll(arrayList);
        //Movie.getDiffGenres();
    }
    static ArrayList<Movie> getMoviesObjs(){
        ArrayList<Movie> arrayList = new ArrayList<Movie>();
        Movie movie = new Movie();
        for(String s: movies){
            arrayList.add(getOneMovie_(s));
        }
        return arrayList;
    }
    static Movie getOneMovie_(String movieString){
        Movie movie = new Movie();
        String[] arr = movieString.split(",");
        movie.setTitle(arr[1]);
        movie.setId(Integer.parseInt(arr[0]));
        movie.setRelease_date(LocalDate.parse(arr[3]));
        movie.setDescription(arr[2]);
        movie.setRunning_time(arr[4]);
        movie.setBudget(arr[9]);
        movie.setCountry(arr[8]);
        movie.setLanguage(arr[6]);
        movie.setImdb_score(Integer.parseInt(arr[7]));
        movie.setRevenue(arr[10]);
        movie.setPoster_path(arr[11]);
        movie.setDirector(getDirector(arr[5]));
        movie.setDirectorName(arr[5]);
        ArrayList<String> cast = new ArrayList<String>();
        ArrayList<String> genres = new ArrayList<String>();
        castAndGenres(movieString,cast,genres);
        movie.setCastNames(cast);
        movie.setGenre(genres);
        return movie;
    }
    static Movie getOneMovie (String title){
        Movie movie = new Movie();
        for (String oneMovie : movies) {
            String[] arr = oneMovie.split(",");
            if (arr[1].equals(title)) {
                movie.setTitle(arr[1]);
                movie.setId(Integer.parseInt(arr[0]));
                movie.setRelease_date(LocalDate.parse(arr[3]));
                movie.setDescription(arr[2]);
                movie.setRunning_time(arr[4]);
                movie.setBudget(arr[9]);
                movie.setCountry(arr[8]);
                movie.setLanguage(arr[6]);
                movie.setImdb_score(Integer.parseInt(arr[7]));
                movie.setRevenue(arr[10]);
                movie.setPoster_path(arr[11]);
                movie.setDirector(getDirector(arr[5]));
                ArrayList<String> cast = new ArrayList<String>();
                ArrayList<String> genres = new ArrayList<String>();
                movie.setGenre(genres);
                castAndGenres(oneMovie,cast,genres);
                ArrayList<Cast> cast_obj = new ArrayList<Cast>();
                for(String casts: cast){
                    cast_obj.add(getCast(casts));
                }
                movie.setCast(cast_obj);
            }
        }
        return movie;
    }
    public static void castAndGenres(String Movie, ArrayList<String> cast_, ArrayList<String> genres){
        //cast first then genres
        boolean cast = false;
        boolean genre = false;
        int castCounter = -1;
        int genreCounter = -1;
        String[] eachLine = Movie.split(",");
        for (String data:eachLine) {
            if(data.equals("cast"))
                cast = true;
            else if(data.equals("genres")) {
                cast = false;
                genre = true;
            }
            if(cast) {
                castCounter++;
                if(castCounter != 0)
                    cast_.add(data);
            }
            else if(genre) {
                genreCounter ++;
                if(genreCounter != 0)
                    genres.add(data);
            }
        }
    }
    static ArrayList<Director> getAllDirectors(){
        ArrayList<Director> arrayList = new ArrayList<Director>();
        for(String oneDirector: directors){
            String[] arr = oneDirector.split(",");
            String name ="";
            name = name.concat(arr[1]).concat(" ").concat(arr[2]);
            arrayList.add(getDirector(name));
        }
        return arrayList;
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
                ArrayList<String> arrayList = new ArrayList<String>();
                for(int i=data.length - 1; i>5; i--){
                    arrayList.add(data[i]);
                }
                director.setMovies(arrayList);
            }
        }
        return director;
    }
    static ArrayList<Cast> getAllCast(){
        ArrayList<Cast> allcast = new ArrayList<Cast>();
        for(String oneCast: casts){
            String[] arr = oneCast.split(",");
            String name ="";
            name = name.concat(arr[1]).concat(" ").concat(arr[2]);
            allcast.add(getCast(name));
        }
        return allcast;
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
                ArrayList<String> arrayList = new ArrayList<String>();
                for(int i=data.length - 1; i>5; i--){
                    arrayList.add(data[i]);
                }
                eachCast.setMovies(arrayList);
            }
        }
        return eachCast;
    }
    //name on getMovies is full name >> first and last combined.
    static ArrayList<User> getAllUsers(){
        ArrayList<User> userArrayList = new ArrayList<User>();
        for(int i=0; i<users.size(); i++){
            String[] data =users.get(i).split(",");
            User user = new User(data[USERNAMEINDEX], data[LASTNAMEINDEX],data[FIRSTNAMEINDEX],data[EMAILINDEX],data[PASSWORDINDEX],data[PROFILEPICINDEX], data[PLANINDEX]);
            userArrayList.add(user);
        }
        getUsersInEachPlan();
        return userArrayList;
    }
}
