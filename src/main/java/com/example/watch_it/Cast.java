package com.example.watch_it;
import java.time.LocalDate;
import java.util.Locale;
import java.util.Scanner;
import java.util.ArrayList;
public class Cast extends Artist{

    public void Add_Cast(String first_name,String second_name,int age,String gender,String Nationality,ArrayList<String> movie){
        Cast c=new Cast();
        c.setFirst_Name(first_name);
        c.setSecond_Name(second_name);
        c.setAge(age);
        c.setGender(gender);
        c.setNationality(Nationality);
        c.setMovies(movie);
//      Admin.cast.add(c); static array from admin class
    }
    public static void Read_file(ArrayList<String> s){
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
//          Admin.cast.add(c);
        }

    }
    public void Update_cast( int index,int num,String movie){

//        Admin.cast.get(index).Movies.add(movie);
    }
    public static Cast  Search_Cast(String first_name,String second_name) {
        for (Cast m : Admin.casts_obj) {

            if (m.getFirst_Name().equals(first_name) && m.getSecond_Name().equals(second_name)) {
                return m;
            }
        }
        return null;
    }
    public int  Check(String first_name,String second_name) {
        for (int i=0;i<Admin.casts_obj.size();i++) {

            if (Admin.casts_obj.get(i).getFirst_Name().equals(first_name) && Admin.casts_obj.get(i).getSecond_Name().equals(second_name)) {
                return i;
            }
        }
        return -1;
    }
    public void Delete(String first_name,String second_name)
    {
        for (Cast c:Admin.casts_obj) {
            if(c.getFirst_Name().equals(first_name)&&c.getSecond_Name().equals(second_name))
                Admin.casts_obj.remove(c);
            break;
        }
    }
    public void change_arraycast(ArrayList<String>movies,ArrayList<String>arrayList)
    {
        String line=null;
        for(Cast c:Admin.casts_obj)
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
}