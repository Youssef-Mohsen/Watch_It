package com.example.watch_it;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public abstract class Artist {
    private String First_Name;
    private String Second_Name;
    private String Gender;
    private String Nationality;
    private int Age;
    ArrayList<String>Movies=new ArrayList<String>();

    public String getFirst_Name() {
        return First_Name;
    }

    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public void setSecond_Name(String second_Name) {
        Second_Name = second_Name;
    }

    public String getSecond_Name() {
        return Second_Name;
    }

    public String getNationality() {
        return Nationality;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public String getGender() {
        return Gender;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public int getAge() {
        return Age;
    }

    public void setAge(int age) {
        Age = age;
    }
    public ArrayList<String> getMovies() {
        return Movies;
    }

    public void setMovies(String movies) {
        Movies.add( movies);
    }
}