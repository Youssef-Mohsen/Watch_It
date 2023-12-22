package com.example.watch_it;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Director extends Artist {
    static ArrayList<Director> allDirectors = new ArrayList<Director>();
    public void Read_File(ArrayList<String>s,ArrayList<Director> directors){
        for (String i:s) {
            String[]row=i.split(",");
            Artist d=new Director();
            d.setFirst_Name(row[0]);
            d.setSecond_Name(row[1]);
            d.setAge(Integer.parseInt(row[2]));
            d.setGender(row[3]);
            d.setNationality(row[4]);
            for(int j=5;j<row.length;j++){
                d.Movies.add(row[j]);
            }
            directors.add((Director) d);
        }
    }

    @Override
    public void add( String first_name,String second_name,int age,String gender,String Nationality,ArrayList<String> movie){
        Artist d=new Director();
        d.setFirst_Name(first_name);
        d.setSecond_Name(second_name);
        d.setAge(age);
        d.setGender(gender);
        d.setNationality(Nationality);
        d.setMovies(movie);
        allDirectors.add((Director) d);
    }
    @Override
    public void update(int index,String movie){

        allDirectors.get(index).Movies.add(movie);
    }

    public static Director search(String first_name,String second_name) {
        for (Director m : allDirectors) {

            if (m.getFirst_Name().equals(first_name) && m.getSecond_Name().equals(second_name)) {

                return m;
            }
        }
        return null;
    }

    public void Delete(String first_name,String second_name)
    {
        for (Director d:allDirectors) {
            if(d.getFirst_Name().equals(first_name)&&d.getSecond_Name().equals(second_name))
                allDirectors.remove(d);
            break;
        }
    }
    public void change_arraydirectr(ArrayList<String>movies,ArrayList<String>arrayList)
    {
        String line=null;
        for(Director d:allDirectors)
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
    @Override
    public String toString(){
        String data = "";
        data = data.concat("director").concat(",").concat(getFirst_Name()).concat(",").concat(getSecond_Name()).concat(",").concat(Integer.toString(getAge())).concat(",").concat(getGender()).concat(",").concat(getNationality());
        for(String movie: getMovies()){
            data = data.concat(",").concat(movie);
        }
        return data;
    }
}