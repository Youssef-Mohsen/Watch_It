package com.example.watch_it;
import java.io.*;
import java.time.Month;
import java.util.*;
import java.time.LocalDate;

public class Admin {
    private String username;
    private String password;
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
    private static final short STARTDATEINDEX = 8;
    public static ArrayList<Admin> allAdmins = new ArrayList<Admin>();

    public static ArrayList<String> movies = new ArrayList<String>();
    public static ArrayList<String> directors = new ArrayList<String>();
    public static ArrayList<String> casts = new ArrayList<String>();
    public static ArrayList<String> admins = new ArrayList<String>();
    public static ArrayList<String> users = new ArrayList<String>();
    public Admin(String username, String password ){
        this.password = password;
        this.username = username;
    }
    public Admin(){
        this.username = null;
        this.password = null;
    }
    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public static void readFile(File file) {
        try {
            String line = "";
            BufferedReader b = new BufferedReader(new FileReader(file));
            while ((line = b.readLine()) != null) {
                String [] row = line.split(",");
                switch (row[TYPEINDEX]) {
                    case "user" -> users.add(line);
                    case "cast" -> casts.add(line);
                    case "director" -> directors.add(line);
                    default -> admins.add(line);
                }
            }
            b.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void readMovies(File file) {
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
            for (User user : User.allusers){
                b.write(user.toString());
                b.newLine();
            }
            for (Artist cast: Cast.allCast){
                b.write(cast.toString());
                b.newLine();
            }
            for (Director director: Director.allDirectors){
                b.write(director.toString());
                b.newLine();
            }
            for (Admin admin: allAdmins){
                b.write(admin.toString());
                b.newLine();
            }
            b.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void writeMovies(File file){
        try {
            BufferedWriter b = new BufferedWriter(new FileWriter(file));
            for (Movie movie : Movie.allmovies){
                b.write(movie.toString());
                b.newLine();
              //  System.out.println(movie.getDirector().getFirst_Name()+" "+movie.getDirector().getSecond_Name());
            }
            b.close();
        }
        catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    public static void setData(){
        User.allusers.addAll(Admin.getAllUsers());
        Director.allDirectors.addAll(Admin.getAllDirectors());
        Cast.allCast.addAll(Admin.getAllCast());
        Admin.allAdmins.addAll(Admin.getAllAdmins());
        Movie.allmovies.addAll(Admin.getMoviesObjs());
        Movie.getDiffGenres();
    }
    //returned string isn't splitted
    public static String existsInFile(String username){
        String[] eachLine;
        for (String index: users){
            eachLine = index.split(",");
            if(eachLine[USERNAMEINDEX].equals(username)){
                return index;
            }
        }
        return null;
    }

    static void getUsersInEachPlan(){
        basicPlanCounter = 0;
        standardPlanCounter = 0;
        premiumPlanCounter = 0;
        for (String index: users){
            String []line = index.split(",");
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
                    monthsRevenue[(startMonth + differenceMonths) -1] += 10;
                }
                else if (plan.equals("standard")) {
                    monthsRevenue[(startMonth + differenceMonths) - 1] += 20;
                }
                else{
                    monthsRevenue[(startMonth + differenceMonths) - 1] += 30;
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
    //******************************************************************
    static void getUserMovieLists(User user){

        boolean toBeWatched = false;
        boolean watched = false;
        int watchedCounter = 0;
        int toBeWatchedCounter = 0;
        String allData = existsInFile(user.getUser_Name());
        try{
            String[] eachLine = allData.split(",");
            for (String data:eachLine) {
                if(data.equals("done"))
                    break;
                if(data.equals("watched")) {
                    watched = true;
                    toBeWatched = false;
                }
                if(data.equals("to be watched")) {
                    toBeWatched = true;
                    watched = false;
                }
                if(watched) {
                    if(watchedCounter != 0) {
                        user.watchedMovies.add(data);
                    }
                    watchedCounter++;
                }
                if(toBeWatched && toBeWatchedCounter == 1) {
                    user.toWatchMovies.add(data);
                }
                if (toBeWatched && toBeWatchedCounter == 0)
                    toBeWatchedCounter = 1;
            }
        }catch (NullPointerException e){
            System.out.println("Error "+e);
        }

    }
    //as objects-------------------
    static void getUserMovieLists_obj(User user){
        getUserMovieLists(user);
        for(String movie: user.toWatchMovies){
            user.Movies_For_Later.add(getUnkownMovie(movie));
        }
        for(int i=0; i<user.watchedMovies.size(); i++){
            if(i%2 == 0){
                Movie movie;
                movie = getUnkownMovie(user.watchedMovies.get(i));
                Double rate = Double.parseDouble(user.watchedMovies.get(i+1));
                UserWatchRecord u = new UserWatchRecord(movie,rate);
                user.Watched_Movies.add(u);
            }
        }
    }
    //returning every movie as obj in the DB.
    static ArrayList<Movie> getMoviesObjs(){
        ArrayList<Movie> arrayList = new ArrayList<Movie>();
        for(String s: movies){
            arrayList.add(getOneMovie(s));
        }
        return arrayList;
    }
    //returning one movie obj based on a string containing all data.
    static Movie getOneMovie(String movieString){
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
        movie.setImdb_score(Float.parseFloat(arr[7]));
        movie.setRevenue(arr[10]);
        movie.setPoster_path(arr[11]);
        movie.setDirector(getDirector(arr[5]));
        movie.setAvarage_rating(Double.parseDouble(arr[12]));
        movie.setViews(Integer.parseInt(arr[13]));
        movie.setUsers_rated(Integer.parseInt(arr[14]));
        movie.setTotalRating(Double.parseDouble(arr[15]));
        ArrayList<String> cast = new ArrayList<String>();
        ArrayList<String> genres = new ArrayList<String>();
        castAndGenres(movieString,cast,genres);
        movie.setCastNames(cast);
        movie.setGenre(genres);
        ArrayList<Cast> movieCast = new ArrayList<Cast>();
        for (String castString: cast){
            movieCast.add(getCast(castString));
        }
        movie.setCast(movieCast);
        return movie;
    }
    //returning one move obj based on its title.
    static Movie getUnkownMovie(String title){
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
                movie.setImdb_score(Float.parseFloat(arr[7]));
                movie.setRevenue(arr[10]);
                movie.setPoster_path(arr[11]);
                movie.setDirector(getDirector(arr[5]));
                movie.setAvarage_rating(Double.parseDouble(arr[12]));
                movie.setViews(Integer.parseInt(arr[13]));
                movie.setUsers_rated(Integer.parseInt(arr[14]));
                movie.setTotalRating(Double.parseDouble(arr[15]));
                ArrayList<String> cast = new ArrayList<String>();
                ArrayList<String> genres = new ArrayList<String>();
                castAndGenres(oneMovie,cast,genres);
                movie.setGenre(genres);
                movie.setCastNames(cast);
                ArrayList<Cast> cast_obj = new ArrayList<Cast>();
                for(String casts: cast){
                    cast_obj.add(getCast(casts));
                }
                movie.setCast(cast_obj);
            }
        }
        return movie;
    }
    //returning strings with the names of the cast and genres in the movie.
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
                ArrayList<String> arrayList = new ArrayList<>();
                for(int i=data.length - 1; i>5; i--){
                    arrayList.add(data[i]);
                }
                director.setMovies(arrayList);
            }
        }
        return director;
    }
    static ArrayList<Cast> getAllCast(){
        ArrayList<Cast> allcast = new ArrayList<>();
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
                ArrayList<String> arrayList = new ArrayList<>();
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
        ArrayList<User> userArrayList = new ArrayList<>();
        for (String s : users) {
            String[] data = s.split(",");
            Subscription.Plans plan = null;
            double price = 0;
            if (data[PLANINDEX].equals("basic")) {
                plan = Subscription.Plans.BASIC;
                price = 100;
            }
            if (data[PLANINDEX].equals("standard")) {
                plan = Subscription.Plans.STANDARD;
                price = 200;
            }
            if (data[PLANINDEX].equals("premium")) {
                plan = Subscription.Plans.PREMIUM;
                price = 300;
            }
            Subscription subscription = new Subscription(User.allusers.size() + 1, plan);
            User user = new User(User.allusers.size() + 1, data[USERNAMEINDEX], data[LASTNAMEINDEX], data[FIRSTNAMEINDEX], data[EMAILINDEX], data[PASSWORDINDEX], data[PROFILEPICINDEX], subscription, data[STARTDATEINDEX]);
            getUserMovieLists_obj(user);
            user.getSubscription().setPlan(plan);
            user.setPlan(data[PLANINDEX]);
            user.getSubscription().setPrice(price);
            user.getSubscription().setStartDate(LocalDate.parse(data[STARTDATEINDEX]));
            user.getSubscription().setUpdatePlan(Boolean.parseBoolean(data[10]));
            int moviesCounter = 0;
            for (UserWatchRecord ignored : user.Watched_Movies) {
                moviesCounter++;
            }
            user.getSubscription().setMoviesWatched(moviesCounter);
            userArrayList.add(user);
        }
        getUsersInEachPlan();
        return userArrayList;
    }
    static ArrayList<Admin> getAllAdmins(){
        ArrayList<Admin> adminArrayList = new ArrayList<>();
        for(String oneAdmin : admins){
            String []data = oneAdmin.split(",");
            Admin admin = new Admin(data[USERNAMEINDEX], data[PASSWORDINDEX]);
            adminArrayList.add(admin);
        }
        return adminArrayList;
    }
    @Override
    public String toString(){
        String data ="";
        data = data.concat("admin").concat(",").concat(getUsername()).concat(",").concat(getPassword());
        return data;
    }
}
