package com.example.watch_it;

import java.util.ArrayList;

public abstract class Artist {
    private String First_Name;
    private String Second_Name;
    private String Gender;
    private String Nationality;
    private int Age;
    ArrayList<String> Movies = new ArrayList<String>();

    public String getFirst_Name() {
        return First_Name;
    }

    public String getSecond_Name() {
        return Second_Name;
    }

    public String getNationality() {
        return Nationality;
    }

    public String getGender() {
        return Gender;
    }

    public int getAge() {
        return Age;
    }

    public ArrayList<String> getMovies() {
        return Movies;
    }


    public void setFirst_Name(String first_Name) {
        First_Name = first_Name;
    }

    public void setSecond_Name(String second_Name) {
        Second_Name = second_Name;
    }

    public void setNationality(String nationality) {
        Nationality = nationality;
    }

    public void setGender(String gender) {
        Gender = gender;
    }

    public void setAge(int age) {
        Age = age;
    }

    public void setMovies(ArrayList<String> movies) {
        Movies = movies;
    }
}