package com.example.watch_it;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
public class Cast extends Artist{

    public void Add_Cast( ArrayList<Cast>cast,String first_name,String second_name,int age,String gender,String Nationality,String movie){
        Cast c=new Cast();
        c.setFirst_Name(first_name);
        c.setSecond_Name(second_name);
        c.setAge(age);
        c.setGender(gender);
        c.setNationality(Nationality);
        c.setMovies(movie);
        cast.add(c);
    }
    public static void Read_file(ArrayList<String> s,ArrayList<Cast> cast){
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
    public void Update_cast(int index,int num,String movie,ArrayList<Cast> cast){

        cast.get(index).Movies.add(movie);
    }
    public Cast  Search_Cast(String first_name,String second_name,ArrayList<Cast> cast) {
        for (Cast m : cast) {

            if (m.getFirst_Name().equals(first_name) && m.getSecond_Name().equals(second_name)) {
                return m;
            }
        }
        return null;
    }
    public boolean  Check(String first_name,String second_name,ArrayList<Cast> cast) {
        for (Cast m : cast) {

            if (m.getFirst_Name().equals(first_name) && m.getSecond_Name().equals(second_name)) {
                return true;
            }
        }
        return false;
    }
    public void Delete(ArrayList<Cast> cast,String first_name,String second_name)
    {
        for (Cast c:cast) {
            if(c.getFirst_Name().equals(first_name)&&c.getSecond_Name().equals(second_name)) {
                cast.remove(c);
                break;
            }
        }
    }
    public void change_arraycast(ArrayList<Cast>cast,ArrayList<String>movies,ArrayList<String>arrayList)
    {
        String line=null;
        for(Cast c:cast)
        {
            String []row=new String[5+movies.size()];
            String s=String.valueOf(c.getAge());
            int i=5;
            row[0]=c.getFirst_Name();
            row[1]=c.getSecond_Name();
            row[2]=s;
            row[3]=c.getGender();
            row[4]=c.getNationality();
            for(String m:movies)
            {
                row[i]=m;
                i++;
            }
            line=String.join(",",row);
            arrayList.add(line);
           // System.out.println(line);
        }

    }
}