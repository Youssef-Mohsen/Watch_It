package watch_it;

import java.util.ArrayList;

public class User
{
    private int ID;
    private String User_Name;
    private String Last_Name;
    private String First_Name;
    private String Email;
    private String Password;
    private Subscription subscription;
    // This List Access By User
    private ArrayList<Movie> Movies_For_Later = new ArrayList<>();
    // This List Access By UserWatchRecord
    private ArrayList<UserWatchRecord> Watched_Movies = new ArrayList<>();
    //private double User_Wallet;
    //private Cast cast = new Cast();
    //private Director director = new Director();
    private static int Num_Of_User = 0;
    /****use it as ID to be unique(static variable) and display it in message to each user*****/
    public User()
    {
        Num_Of_User++;
        this.ID = Num_Of_User;
    }
    /*****as Sign Up method and the data will take in main(add)******/
    public User(int ID, String user_Name, String last_Name, String first_Name, String email, String password) {
        this.ID = ID;
        User_Name = user_Name;
        Last_Name = last_Name;
        First_Name = first_Name;
        Email = email;
        Password = password;
    }
    /****Note the Id is not allowed to be Updated(because it is given by system)****/
    public void UpDate_Data_User(int Answer , String info)
    {
        if(Answer == 1)
        {
            this.User_Name = info;
        }
        else if(Answer == 2)
        {
            this.First_Name = info;
        }
        else if(Answer == 3)
        {
            this.Last_Name = info;
        }
        else if(Answer == 4)
        {
            this.Email = info;
        }
        else if(Answer == 5)
        {
            this.Password = info;
        }
    }
    // delete User in Class System(Main).

    /*****Setters*****/
    public void setID(int ID) {
        this.ID = ID;
    }

    public void setUser_Name(String user_Name) {
        User_Name = user_Name;
    }

    public void setLast_Name(String last_Name) {
        Last_Name = last_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public void setEmail(String email) {
        Email = email;
    }
    public void Set_Password(String password) {
        Password = password;
    }
    /*******Getters******/
    public int getID() {
        return ID;
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
    /*******take the index pof the movie the User Change it and the new Movie the Want(object of movie)****/
    /****should handle here the date watching of new movie.*****/


    /****can be handle in main also(we Should discuss it)****/
    /******this should be in User Class >>>> I ask T.A******/
    public void Display_Watch_Later_Movie()
    {
        for(Movie movi : Movies_For_Later)
        {
            System.out.println(movi.getId());
            System.out.println(movi.getViews());
            System.out.println(movi.getTitle());
            System.out.println(movi.getRelease_date());
            System.out.println(movi.getRunning_time());
            System.out.println(movi.getGenre());
            System.out.println(movi.getLanguage());
            System.out.println(movi.getCountry());
            System.out.println(movi.getposter_path());
            System.out.println(movi.getBudget());
            System.out.println(movi.getImdb_score());
            // its director.
            // its Casts.
        }
    }
    public void Display_Watched_Movie()
    {
        for(UserWatchRecord movi : Watched_Movies)
        {
            System.out.println(movi.getUser_id());
            Movie mov = new Movie();
            mov = movi.getMovie();
            System.out.println(mov.getId());
            System.out.println(mov.getViews());
            System.out.println(mov.getTitle());
            System.out.println(mov.getRelease_date());
            System.out.println(mov.getRunning_time());
            System.out.println(mov.getGenre());
            System.out.println(mov.getLanguage());
            System.out.println(mov.getCountry());
            System.out.println(mov.getposter_path());
            System.out.println(mov.getBudget());
            System.out.println(mov.getImdb_score());
            System.out.println(movi.getDate_of_watching());
            System.out.println(movi.getRating());
        }
    }
    /****handle in System class*****/
    //search about Cast.(by name)
    //search about director.(by name)
    //search about movie(by name)
}