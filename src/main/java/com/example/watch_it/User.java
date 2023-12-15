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
    String AllData;
    
    // This List Access By User
    static ArrayList<Movie> Movies_For_Later = new ArrayList<>();
    static ArrayList<String> toWatchMovies = new ArrayList<>();
    // This List Access By UserWatchRecord
    static ArrayList<UserWatchRecord> Watched_Movies = new ArrayList<>();
    static ArrayList<String> watchedMovies = new ArrayList<>();
    public static ArrayList<User> allusers = new ArrayList<User>();

    /****use it as ID to be unique(static variable) and display it in message to each user*****/

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
    }

    // handle by GUI Answer the button that user click on it.
    public void UpDate_Data_User(String Answer , String info)
    {
        if(Answer.equals("First Name"))
        {
            this.First_Name = info;
        }
        else if(Answer.equals("Last Name"))
        {
            this.Last_Name = info;
        }
        else if(Answer.equals("Email"))
        {
            this.Email = info;
        }
        else if(Answer.equals("Password"))
        {
            this.Password = info;
        }
    }
    // return the index of the user who deleted to handle the Id of the reminder users.
    public static void Delete_User(String user_name)
    {
        int index = 0 ;
        for(User user : allusers)
        {
            if(user.User_Name.equals(user_name))
            {
                index = allusers.indexOf(user);
                allusers.remove(user);
            }
        }
        for (int i=index; i<allusers.size(); i++){
            allusers.get(i).setUser_ID( allusers.get(i).getUser_ID() - 1);
        }
    }
    public static boolean Userexist(String user_name) {
        for (User user : allusers) {
            if (user.User_Name.equals(user_name)) {
                return true;
            }
        }
        return false;
    }
    /*****Setters*****/
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
    /*******Getters******/
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
    /****Set the data of Subscription****/
    public void Subscribe(int id , Subscription.Plans plan)
    {
        subscription = new Subscription(id , plan);
    }
    /******define set method for plan in class Subscription******/
    public void Updata_Subscription_Plan(Subscription.Plans plan)
    {
        subscription.setPlan(plan);
    }
    /*********delete will handle in Class System(Main).
     // How to handle the date of watching
     // take movie from method Choose movie Should be in main(return object of movie)
     //the Constraints of this part the amount of movies that allowed to user
     // add ********/
    public void Choose_Movies_For_Later(Movie mov)
    {
        Movies_For_Later.add(mov);
    }
    /*******take the index of the movie the User Change it and the new Movie the Want(object of movie)****/
    public void Update_Choosen_Movie_Change(Movie mov , int index)
    {
        // make sure the exception handling is applied in the index.
        Movies_For_Later.set(index , mov);
    }
    /****delete movie from the list of watch later should handle in class system.*****/
    public void Set_record(int id , Movie movi)
    {
        // Handle the part of Date and rate
        // the movi that user watch it.
        Watched_Movies.add(new UserWatchRecord(id , movi));
    }
    /***to set rate and Update it****/
    public void Set_Rate_Racord(int index ,int rate)
    {
        // make sure the exception handling is applied in the index.
        Watched_Movies.get(index).setRating(rate);
    }
    public static User GetUser(String user_name) {
        for (User user : allusers) {
            if (user.User_Name.equals(user_name))
                return user;
        }
        return null;
    }
    @Override
    public String toString(){
        String data ="";
        data = data.concat("user").concat(",").concat(this.User_Name).concat(",").concat(String.valueOf(getPassword())).concat(",").concat(String.valueOf(allusers.size()).concat(",").concat(getFirst_Name()).concat(",").concat(getLast_Name()).concat(",").concat(plan).concat(",").concat(getEmail()).concat(",").concat(LocalDate.now().toString()).concat(",").concat(profilePath));
        String watched="";
        String toWatch="";
        for(int i=0; i<watchedMovies.size(); i++){
            watched=watched.concat("watched").concat(",");
            if(i<watchedMovies.size()-1){
                watched = watched.concat(watchedMovies.get(i).concat(","));
            }
            else
                watched = watched.concat(watchedMovies.get(i));
        }
        for(int i=0; i<toWatchMovies.size(); i++){
            watched=watched.concat("to be watched").concat(",");
            if(i<toWatchMovies.size()-1){
                toWatch = toWatch.concat(toWatchMovies.get(i).concat(","));
            }
            else
                toWatch = toWatch.concat(toWatchMovies.get(i));
        }
        if(watchedMovies.isEmpty() && toWatchMovies.isEmpty())
            return data;
        if((watchedMovies.isEmpty()))
          data = data.concat(",").concat(watched);
        else if((toWatchMovies.isEmpty()))
           data = data.concat(",").concat(toWatch);
        return data;
    }
}
