package watch_it;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
public class Cast extends Artist{

    public void Add_Cast( ArrayList<Cast>cast,String first_name,String second_name,int age,String gender,String Nationality,int num,ArrayList<Movie>movies){
        Cast c=new Cast();
        c.setFirst_Name(first_name);
        c.setSecond_Name(second_name);
        c.setAge(age);
        c.setGender(gender);
        c.setNationality(Nationality);
        c.setMovies(movies);
        cast.add(c);
    }
    public void Update_cast(int index, ArrayList<Cast>cast,int num,Movie movie){
        for(int i=0;i<num;i++)
        {
            cast.get(index).Movies.add(movie);
        }
    }

}