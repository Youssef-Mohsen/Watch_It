package com.example.watch_it;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Director extends Artist {
    public void Read_File(ArrayList<String>s,ArrayList<Cast> cast){
        for (String i:s) {
            String[]row=i.split(",");
            Cast c=new Cast();
            c.setFirst_Name(row[0]);
            c.setSecond_Name(row[1]);
            c.setAge(Integer.parseInt(row[2]));
            c.setGender(row[3]);
            c.setNationality(row[4]);
            for(int j=5;j<row.length;j++){
                c.Movies.add(row[j]);
            }
            cast.add(c);
        }
    }

    public void Add_Director( ArrayList<Director>directors,String first_name,String second_name,int age,String gender,String Nationality,int num,ArrayList<String> movie){
        Director d=new Director();
        d.setFirst_Name(first_name);
        d.setSecond_Name(second_name);
        d.setAge(age);
        d.setGender(gender);
        d.setNationality(Nationality);
        d.setMovies(movie);
        directors.add(d);
    }
    public void Update_Director(int index, ArrayList<Director>directors,String movie){

        directors.get(index).Movies.add(movie);
    }
    public static Director  Search_director(String first_name, String second_name, ArrayList<Director> directors) {
        for (Director m : directors) {

            if (m.getFirst_Name().equals(first_name) && m.getSecond_Name().equals(second_name)) {

                return m;
            }

        }
        return null;
    }
    public boolean  Check(String first_name,String second_name,ArrayList<Director> directors) {
        for (Director m : directors) {

            if (m.getFirst_Name().equals(first_name) && m.getSecond_Name().equals(second_name)) {
                return true;
            }
        }
        return false;
    }
    public void Delete(ArrayList<Director> directors,String first_name,String second_name)
    {
        for (Director d:directors) {
            if(d.getFirst_Name().equals(first_name)&&d.getSecond_Name().equals(second_name))
                directors.remove(d);
            break;
        }
    }
    public void change_arraydirectr(ArrayList<Director>directors,ArrayList<String>movies,ArrayList<String>arrayList)
    {
        String line=null;
        for(Director d:directors)
        {
            String []row=new String[5+movies.size()];
            String s=String.valueOf(d.getAge());
            int i=5;
            row[0]=d.getFirst_Name();
            row[1]=d.getSecond_Name();
            row[2]=s;
            row[3]=d.getGender();
            row[4]=d.getNationality();
            for(String m:movies)
            {
                row[i]=m;
                i++;
            }
            line=String.join(",",row);
            arrayList.add(line);
            System.out.println(line);
        }

    }
}