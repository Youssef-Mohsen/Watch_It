package com.example.watch_it;

import java.time.LocalDateTime;

public class UserWatchRecord {
 private int user_id;
    Movie movie;
 private LocalDateTime date_of_watching;
  private int rating;

  public UserWatchRecord(int user_id, Movie movie , LocalDateTime date_of_watching, int rating)
  {
   this.user_id=user_id;
   this.movie=movie;
   this.date_of_watching=date_of_watching;

    this.rating = rating;
  }
    public UserWatchRecord(int user_id, Movie movie)
    {
        this.user_id=user_id;
        this.movie=movie;
    }

// ********Get methods***********

 public int getRating()
 {
  return rating;
 }
 public Movie getMovie()
 {
  return movie;
 }
 public int getUser_id()
 {
  return user_id;
 }
 public LocalDateTime getDate_of_watching(){ return date_of_watching;}

//  ******* Set methods ******** 

  public int setRating(int rating)
  {
   this.rating=rating;
   return rating;
  }
    public int setUserId(int user_id)
    {
        this.user_id=user_id;
        return user_id;
    }
    public Movie setMovie(Movie movie)
    {
        this.movie=movie;
        return movie;
    }
  

}
