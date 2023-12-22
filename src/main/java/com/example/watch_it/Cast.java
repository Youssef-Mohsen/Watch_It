package com.example.watch_it;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
public class Cast extends Artist{
    static ArrayList<Cast> allCast = new ArrayList<Cast>();

    @Override
    public void add(String first_name,String second_name,int age,String gender,String Nationality,ArrayList<String> movie){
        Artist c=new Cast();
        c.setFirst_Name(first_name);
        c.setSecond_Name(second_name);
        c.setAge(age);
        c.setGender(gender);
        c.setNationality(Nationality);
        c.setMovies(movie);
        allCast.add((Cast) c);
    }
    public static void Read_file(ArrayList<String> s){
        for (String i:s) {
            String[]row=i.split(",");
            Artist c=new Cast();
            c.setFirst_Name(row[0]);
            c.setSecond_Name(row[1]);
            c.setAge(Integer.parseInt(row[2]));
            c.setGender(row[3]);
            c.setNationality(row[4]);
            for(int j=5;j<row.length;j++){
                c.Movies.add(row[j]);
            }
//          Admin.cast.add(c);
        }

    }
    @Override
    public void update( int index, String movie){

        allCast.get(index).Movies.add(movie);
    }
    public static Cast search(String first_name,String second_name) {
        for (Cast m : allCast) {

            if (m.getFirst_Name().equals(first_name) && m.getSecond_Name().equals(second_name)) {
                return m;
            }
        }
        return null;
    }

    public void Delete(String first_name,String second_name)
    {
        for (Cast c:allCast) {
            if(c.getFirst_Name().equals(first_name)&&c.getSecond_Name().equals(second_name))
                allCast.remove(c);
            break;
        }
    }
    public void change_arraycast(ArrayList<String>movies,ArrayList<String>arrayList)
    {
        String line=null;
        for(Cast c:allCast)
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
            System.out.println(line);
        }
    }
    @Override
    public String toString(){
        String data = "";
        data = data.concat("cast");
                data = data.concat(",");
        data = data.concat(getFirst_Name());
        data = data.concat(",");
        data = data.concat(getSecond_Name());
                data = data.concat(",").concat(Integer.toString(getAge()));
                        data = data.concat(",").concat(getGender());
        data = data.concat(",").concat(getNationality());
        for(String movie: getMovies()){
            data = data.concat(",").concat(movie);
        }
        return data;
    }
}