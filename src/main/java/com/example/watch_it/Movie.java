package com.example.watch_it;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;
public class Movie {

    public static ArrayList<Movie> allmovies = new ArrayList<>();
    public static ArrayList<Movie> action = new ArrayList<>();
    public static ArrayList<Movie> drama = new ArrayList<>();
    public static ArrayList<Movie> horror = new ArrayList<>();
    public static ArrayList<Movie> comedy = new ArrayList<>();
    private String directorName;
    private ArrayList<String> castNames;
    private static int counter = 0;
    private int id;
    private int views = 0;
    private String title;
    private LocalDate release_date;
    private String running_time;
    private ArrayList<String> genres;
    private String language;
    private String country;
    private String poster_path;
    private String budget;
    private String revenue;
    private int imdb_score;
    private String description;
    private float avarage_rating = 0f;
    private float total_rating = 0f;
    private int users_rated = 0;

    private Director director;
    private List<Cast> cast;
    public int userRate;
    public Movie(){
        counter++;
        this.id = counter;
        this.director = new Director();
        this.cast = new ArrayList<>();
    }

    public Movie(String title, LocalDate release_date, String running_time, ArrayList<String> genre, String language, String country, String poster_path, String budget, String revenue, int imdb_score,String description) {
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

    public int getImdb_score() {
        return imdb_score;
    }
    public float getAverage_rating() {
        return avarage_rating;
    }
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

    public void setImdb_score(int imdb_score) {
        this.imdb_score = imdb_score;
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
    public int getUserRate(){
        return userRate;
    }
    public int getYear(){
        return release_date.getYear();
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
    public void UpdateRating(float rating){

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
                avarage_rating = 0.0f;
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
    public static List<Movie> RecentMovies(List<Movie> movie) {
        List<Movie> recent_movies = new ArrayList<>();
        for (Movie m : movie) {
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
    public static List<Movie> TopRatedMovies(List<Movie> movie) {
        movie.sort(Comparator.comparing(Movie::getAverage_rating).reversed());
        return movie;
    }
    public static PriorityQueue<Movie> MostViewedMovies(List<Movie> movieList) {
        // Create a PriorityQueue with a custom comparator
        PriorityQueue<Movie> priorityQueue = new PriorityQueue<>(Comparator.comparing(Movie::getViews).reversed());

        // Add all movies to the PriorityQueue
        priorityQueue.addAll(movieList);

        return priorityQueue;
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
    public String getDirectorName() {
        return directorName;
    }

    public void setDirectorName(String directorName) {
        this.directorName = directorName;
    }

    public ArrayList<String> getCastNames() {
        return castNames;
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

}


