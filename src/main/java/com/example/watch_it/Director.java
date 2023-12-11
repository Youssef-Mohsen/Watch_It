package com.example.watch_it;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Director extends Artist {
    public void Read_File(ArrayList<String>s,ArrayList<Director> directors){
        for (String i:s) {
            String[]row=i.split(",");
            Director d=new Director();
            d.setFirst_Name(row[0]);
            d.setSecond_Name(row[1]);
            d.setAge(Integer.parseInt(row[2]));
            d.setGender(row[3]);
            d.setNationality(row[4]);
            for(int j=5;j<row.length;j++){
                d.Movies.add(row[j]);
            }
            directors.add(d);
        }
    }

    public void Add_Director( String first_name,String second_name,int age,String gender,String Nationality,int num,ArrayList<String> movie){
        Director d=new Director();
        d.setFirst_Name(first_name);
        d.setSecond_Name(second_name);
        d.setAge(age);
        d.setGender(gender);
        d.setNationality(Nationality);
        d.setMovies(movie);
       // Admin.directors.add(d);
    }
    public void Update_Director(int index,String movie){

        Admin.directors_obj.get(index).Movies.add(movie);
    }

    public static Director  Search_director(String first_name,String second_name) {
        for (Director m : Admin.directors_obj) {

            if (m.getFirst_Name().equals(first_name) && m.getSecond_Name().equals(second_name)) {

                return m;
            }

        }
        return null;
    }
    public int  Check(String first_name,String second_name) {
        for (int i=0;i<Admin.directors_obj.size();i++) {

            if (Admin.directors_obj.get(i).getFirst_Name().equals(first_name) && Admin.directors_obj.get(i).getSecond_Name().equals(second_name)) {
                return i;
            }
        }
        return -1;
    }
    public void Delete(String first_name,String second_name)
    {
        for (Director d:Admin.directors_obj) {
            if(d.getFirst_Name().equals(first_name)&&d.getSecond_Name().equals(second_name))
                Admin.directors_obj.remove(d);
            break;
        }
    }
    public void change_arraydirectr(ArrayList<String>movies,ArrayList<String>arrayList)
    {
        String line=null;
        for(Director d:Admin.directors_obj)
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

        }

    }
}