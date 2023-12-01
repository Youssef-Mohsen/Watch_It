package com.example.watch_it;
import java.util.ArrayList;

public class Director extends Artist {

    public void Add_Director( ArrayList<Director>directors,String first_name,String second_name,int age,String gender,String Nationality,int num,ArrayList<Movie>movies){
        Director d=new Director();
        d.setFirst_Name(first_name);
        d.setSecond_Name(second_name);
        d.setAge(age);
        d.setGender(gender);
        d.setNationality(Nationality);
        d.setMovies(movies);
        directors.add(d);
    }
    public void Update_Director(int index, ArrayList<Director>directors,int num,Movie movie){
        for(int i=0;i<num;i++)
        {
            directors.get(index).Movies.add(movie);
        }
    }

}