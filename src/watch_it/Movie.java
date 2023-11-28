package watch_it;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Movie {
    private static int counter = 0;
    private int id;
    private int views = 0;
    private String title;
    private LocalDate release_date;
    private String running_time;
    private String genre;
    private String language;
    private String country;
    private String poster_path;
    private double budget;
    private double revenue;
    private float imdb_score;

    // private Director director;
    // private List<Cast> cast;

    public Movie(){
        counter++;
        this.id = counter;
        //  this.director = new Director();
        //  this.cast = new ArrayList<>();
    }

    public Movie (String title, LocalDate release_date, String running_time,String genre, String language, String country, String poster_path, double budget, double revenue, float imdb_score) {
        counter++;
        this.id = counter;
        this.title = title;
        this.release_date = release_date;
        this.running_time = running_time;
        this.genre = genre;
        this.language = language;
        this.country = country;
        this.poster_path = poster_path;
        this.budget = budget;
        this.revenue = revenue;
        this.imdb_score = imdb_score;
        // this.director = new Director();
        // this.cast = new ArrayList<>();
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

    public String getGenre() {
        return genre;
    }

    public String getLanguage() {
        return language;
    }

    public String getCountry() {
        return country;
    }

    public String getposter_path() {
        return poster_path;
    }

    public double getBudget() {
        return budget;
    }

    public float getImdb_score() {
        return imdb_score;
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

    public void setGenre(String genre) {
        this.genre = genre;
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

    public void setBudget(double budget) {
        this.budget = budget;
    }

    public void setRevenue(double revenue) {
        this.revenue = revenue;
    }

    public void setImdb_score(float imdb_score) {
        this.imdb_score = imdb_score;
    }

    public void inc_views(){
        this.views++;
    }

    /*
    public List<Cast> getCast() {
        return cast;
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
     */

    public static List<Movie> RecentMovies(List<Movie> movie) {
        List<Movie> recent_movies = new ArrayList<>();
        for (Movie m : movie) {
            LocalDate release_date = m.getRelease_date();

            if (release_date.isAfter(LocalDate.now().minusMonths(1))) {
                recent_movies.add(m);
            }
        }
        recent_movies.sort(Comparator.comparing(Movie::getRelease_date).reversed());
        return recent_movies;
    }
    public static List<Movie> TopRatedMovies(List<Movie> movie) {
        movie.sort(Comparator.comparing(Movie::getImdb_score).reversed());
        return movie;
    }
    public static List<Movie> MostViewedMovies(List<Movie> movie) {
        movie.sort(Comparator.comparing(Movie::getViews).reversed());
        return movie;
    }

}
