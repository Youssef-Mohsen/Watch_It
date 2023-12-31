package com.example.watch_it;
import java.time.LocalDate;
import java.util.ArrayList;

public class User
{
    private int User_ID;
    private final String User_Name;
    private String Last_Name;
    private String First_Name;
    private String Email;
    private String Password;
    private Subscription subscription;
    private String profilePath;
    private String plan;
    private LocalDate startDate;


    // This List Access By User
    ArrayList<Movie> Movies_For_Later = new ArrayList<Movie>();
    ArrayList<String> toWatchMovies = new ArrayList<String>();
    // This List Access By UserWatchRecord
    ArrayList<UserWatchRecord> Watched_Movies = new ArrayList<UserWatchRecord>();
    ArrayList<String> watchedMovies = new ArrayList<String>();
    public static ArrayList<User> allusers = new ArrayList<User>();

    /**use it as ID to be unique(static variable) and display it in message to each user***/

    /*****as Sign Up method and the data will take in main(add)******/
    public User(String user_name, String last_Name, String first_Name, String email, String password, String profilePath, String plan)
    {
        this.plan = plan;
        this.profilePath = profilePath;
        this.User_ID = Admin.users.size();
        this.User_Name = user_name;
        this.Last_Name = last_Name;
        this.First_Name = first_Name;
        this.Email = email;
        this.Password = password;
    }
    public User(int id, String user_name, String last_Name, String first_Name, String email, String password, String profilePath, String plan)
    {
        this.User_ID =id;
        this.User_Name = user_name;
        this.Last_Name = last_Name;
        this.First_Name = first_Name;
        this.Email = email;
        this.Password = password;
        this.profilePath = profilePath;
        this.plan = plan;
    }
    public User(int id, String user_name, String last_Name, String first_Name, String email, String password, String profilePath, String plan, String stardDate)
    {
        this.User_ID =id;
        this.User_Name = user_name;
        this.Last_Name = last_Name;
        this.First_Name = first_Name;
        this.Email = email;
        this.Password = password;
        this.startDate = LocalDate.parse(stardDate);
        this.profilePath = profilePath;
        this.plan = plan;
    }
    public User(int id, String user_name, String last_Name, String first_Name, String email, String password, String profilePath, Subscription plan, String stardDate)
    {
        this.User_ID =id;
        this.User_Name = user_name;
        this.Last_Name = last_Name;
        this.First_Name = first_Name;
        this.Email = email;
        this.Password = password;
        this.startDate = LocalDate.parse(stardDate);
        this.subscription = plan;
        this.profilePath = profilePath;

        if (plan.getPlan().equals(Subscription.Plans.BASIC))
            setPlan("basic");
        if (plan.getPlan().equals(Subscription.Plans.STANDARD))
            setPlan("standard");
        if (plan.getPlan().equals(Subscription.Plans.PREMIUM))
            setPlan("premium");

    }
    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public static boolean Userexist(String user_name) {
        for (User user : allusers) {
            if (user.User_Name.equals(user_name)) {
                return true;
            }
        }
        return false;
    }
    /***Setters***/
    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }
    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }
    public void setPlan(String plan){
        this.plan = plan;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public void setUser_ID(int user_ID){
        this.User_ID = user_ID;
    }
    public void setProfilePath(String profilePath){
        this.profilePath = profilePath;
    }
    public void Set_Password(String password) {
        Password = password;
    }

    /*****Getters****/
    public int getUser_ID() {
        return User_ID;
    }
    public String getUser_Name() {
        return User_Name;
    }
    public String getLast_Name() {
        return Last_Name;
    }
    public String getFirst_Name() {
        return First_Name;
    }
    public String getEmail() {
        return Email;
    }
    public String getPassword() {
        return Password;
    }
    public String getProfilePath(){
        return profilePath;
    }
    public String getPlan(){
        return plan;
    }
    /**Set the data of Subscription**/
    public void Subscribe(int id , Subscription.Plans plan)
    {
        subscription = new Subscription(id , plan);
    }
    /****define set method for plan in class Subscription****/
    public void Updata_Subscription_Plan(Subscription.Plans plan)
    {
        subscription.setPlan(plan);
    }

    /*********delete will handle in Class System(Main).
     // How to handle the date of watching
     // take movie from method Choose movie Should be in main(return object of movie)
     //the Constraints of this part the amount of movies that allowed to user
     // add ********/

    /*******take the index of the movie the User Change it and the new Movie the Want(object of movie)****/

    /****delete movie from the list of watch later should handle in class system.*****/

    public static User GetUser(String user_name) {
        for (User user : allusers) {
            if (user.User_Name.equals(user_name))
                return user;
        }
        return null;
    }

    public UserWatchRecord getWatchedMovie(Movie movie){
        for (UserWatchRecord u: Watched_Movies)
            if(u.getMovie().equals(movie))
                return u;
        return null;
    }
    @Override
    public String toString(){
        String data ="";
        data = data.concat("user").concat(",").concat(getUser_Name()).concat(",").concat(getPassword()).concat(",").concat(Integer.toString(getUser_ID()));
        data = data.concat(",").concat(getFirst_Name()).concat(",");
        data = data.concat(getLast_Name()).concat(",").concat(getPlan()).concat(",");
        data = data.concat(getEmail()).concat(",").concat(getSubscription().getStartDate().toString());
        data = data.concat(",").concat(getProfilePath()).concat(",").concat(String.valueOf(getSubscription().getUpdatePlan())).concat(",");

        data = data.concat("to be watched").concat(",");
        for (Movie toWatchMovie : Movies_For_Later) {
            data = data.concat(toWatchMovie.getTitle()).concat(",");
        }
        data = data.concat("watched").concat(",");
        for (UserWatchRecord watchedMovie : Watched_Movies) {
            data = data.concat(watchedMovie.getMovie().getTitle()).concat(",").concat(String.valueOf(watchedMovie.getRating())).concat(",");
        }
        data = data.concat("done").concat(",");
        return data;
    }
}