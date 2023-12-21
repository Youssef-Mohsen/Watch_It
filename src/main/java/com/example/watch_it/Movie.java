package com.example.watch_it;
import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.*;

public class Movie {

    public static ArrayList<Movie> allmovies = new ArrayList<>();
    public static ArrayList<Movie> action = new ArrayList<>();
    public static ArrayList<Movie> drama = new ArrayList<>();
    public static ArrayList<Movie> horror = new ArrayList<>();
    public static ArrayList<Movie> comedy = new ArrayList<>();
    private ArrayList<String> castNames;
    private static int counter = 0;
    private int id;
    private int views;
    private String title;
    private LocalDate release_date;
    private String running_time;
    private ArrayList<String> genres;
    private String language;
    private String country;
    private String poster_path;
    private String budget;
    private String revenue;
    private float imdb_score;
    private String description;
    private Double avarage_rating;
    private Double total_rating;
    private int users_rated = 0;
    private Director director;
    private List<Cast> cast;
    private static final DecimalFormat df = new DecimalFormat("0.00");

    public Movie(){
        counter++;
        this.id = counter;
        this.director = new Director();
        this.cast = new ArrayList<>();
    }

    public Movie(String title, LocalDate release_date, String running_time, ArrayList<String> genre, String language, String country, String poster_path, String budget, String revenue, float imdb_score,String description) {
        counter++;
        this.id = counter;
        this.title = title;
        this.release_date = release_date;
        this.running_time = running_time;
        this.genres = genre;
        this.language = language;
        this.country = country;
        this.poster_path = poster_path;
        this.budget = budget;
        this.revenue = revenue;
        this.imdb_score = imdb_score;
        this.description=description;
        this.director = new Director();
        this.cast = new ArrayList<>();
    }
    public int getId() {
        return id;
    }

    public int getViews() {
        return views;
    }

    public String getTitle() {
        return title;
    }
    public String getRevenue(){
        return revenue;
    }

    public LocalDate getRelease_date() {
        return release_date;
    }

    public String getRunning_time() {
        return running_time;
    }

    public ArrayList<String> getGenres() {
        return genres;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public String getPoster_path() {
        return poster_path;
    }

    public String getBudget() {
        return budget;
    }

    public float getImdb_score() {
        return imdb_score;
    }
    public Double getAverage_rating() {
        return avarage_rating;}
    public Director getDirector() {
        return director;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setRelease_date(LocalDate release_date) {
        this.release_date = release_date;
    }

    public void setRunning_time(String running_time) {
        this.running_time = running_time;
    }

    public void setGenre(ArrayList<String> genres) {
        this.genres = genres;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public void setCountry(String country) {
        this.country = country;
    }
    public void setPoster_path(String poster_path) {
        this.poster_path = poster_path;
    }

    public void setBudget(String budget) {
        this.budget = budget;
    }

    public void setRevenue(String revenue) {
        this.revenue = revenue;
    }

    public void setImdb_score(float imdb_score) {
        this.imdb_score = imdb_score;
    }

    public void setViews(int views) {
        this.views = views;
    }

    public void setAvarage_rating(Double avarage_rating){
        this.avarage_rating = avarage_rating;
    }
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public void inc_views(){
        this.views++;
    }

    public void addMovie (Movie movie){
        allmovies.add(movie);
    }
    public List<Cast> getCast() {
        return cast;
    }
    public int getUsers_rated() {
        return users_rated;
    }
    public int getYear(){
        return release_date.getYear();
    }
    public Double getTotalRating(){
        return total_rating;
    }
    public void addActor(Cast actor) {
        cast.add(actor);
    }
    public void setCast(List<Cast> cast) {
        this.cast = cast;
    }
    public void setDirector(Director director) {
        this.director = director;
    }
    public void setTotalRating(Double total_rating){
        this.total_rating = total_rating;
    }
    public void setUsers_rated(int users_rated) {
        this.users_rated = users_rated;
    }
    public void UpdateRating(Double rating){
        total_rating += rating;
        if (rating >= 0) {
            users_rated++;
            avarage_rating = total_rating / users_rated;
        }
        else {
            users_rated --;
            if (users_rated > 0) {
                    avarage_rating = total_rating / users_rated;
            }
            else
                avarage_rating = 0.0;
        }
        avarage_rating = Math.round(avarage_rating * 10.0) / 10.0;
        if(avarage_rating>5){
            avarage_rating=5.0;
        }
    }
    public static Movie getMovie (String movie_title) {
        if (!(allmovies.isEmpty())) {
            for (Movie movie : allmovies) {
                if (movie.title.equals(movie_title))
                    return movie;
            }
        }
        return null;
    }
    public static List<Movie> RecentMovies() {
        List<Movie> recent_movies = new ArrayList<>();
        for (Movie m : allmovies) {
            LocalDate release_date = m.getRelease_date();

            if (release_date.isAfter(LocalDate.now().minusMonths(1))) {
                recent_movies.add(m);
            }
        }
        if (!(recent_movies.isEmpty())) {
            recent_movies.sort(Comparator.comparing(Movie::getRelease_date).reversed());
            return recent_movies;
        }
        return null;
    }
    public static ArrayList<Movie> TopRatedMovies() {
        ArrayList<Movie> check = new ArrayList<>(allmovies);
        check.sort(Comparator.comparingDouble(Movie::getAverage_rating).reversed());
        return check;
    }
    public static PriorityQueue<Movie> MostViewedMovies(List<Movie> movieList) {
        // Create a PriorityQueue with a custom comparator
        PriorityQueue<Movie> priorityQueue = new PriorityQueue<>(Comparator.comparing(Movie::getViews).reversed());

        // Add all movies to the PriorityQueue
        priorityQueue.addAll(movieList);

        return priorityQueue;
    }
    public static ArrayList<Movie> MostViewedMovies() {
        ArrayList<Movie> check = new ArrayList<>(allmovies);
        check.sort(Comparator.comparingDouble(Movie::getViews).reversed());
        return check;
    }
    public static void getDiffGenres(){

        for(Movie eachMovie: allmovies){
            for(String eachGenre:eachMovie.getGenres()){
                if(eachGenre.equals("action"))
                    action.add(eachMovie);
                else if (eachGenre.equals("comedy"))
                    comedy.add(eachMovie);
                else if (eachGenre.equals("drama"))
                    drama.add(eachMovie);
                else if (eachGenre.equals("horror"))
                    horror.add(eachMovie);
            }
        }
    }
    public ArrayList<String> getCastNames() {
        return castNames;
    }
    public void getCastNames(ArrayList<String> cast) {
        this.castNames.addAll(cast);
    }
    public void setCastNames(ArrayList<String> castNames) {
        this.castNames = castNames;
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Movie otherMovie = (Movie) obj;
        return title.equals(otherMovie.title);
    }
    @Override
    public String toString (){
        String data = "";
        data = data.concat(String.valueOf(getId())).concat(",").concat(getTitle()).concat(",").concat(getDescription()).concat(",").concat(getRelease_date().toString()).concat(",");
        data = data.concat(getRunning_time()).concat(",").concat(getDirector().getFirst_Name()+" "+getDirector().getSecond_Name()).concat(",").concat(getLanguage()).concat(",");
        data = data.concat(String.valueOf(getImdb_score())).concat(",").concat(getCountry()).concat(",").concat(getRevenue()).concat(",").concat(getBudget()).concat(",");
        data = data.concat(getPoster_path()).concat(",").concat(String.valueOf(getAverage_rating())).concat(",").concat(String.valueOf(getViews())).concat(",");
        data = data.concat(String.valueOf(getUsers_rated())).concat(",").concat(String.valueOf(getTotalRating()));
        data = data.concat(",").concat("cast");
        for (String cast: castNames)
            data = data.concat(",").concat(cast);
        data = data.concat(",").concat("genres");
        for (String genre: genres)
            data = data.concat(",").concat(genre);
        return data;
    }

}


