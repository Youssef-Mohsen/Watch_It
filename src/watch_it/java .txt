package watch_it;

import java.time.LocalDateTime;

public class userwatchrecord {
 private int user_id;
  Movi movie;
 private LocalDateTime date_of_watching;
  private int rating;

  public userwatchrecord(int user_id, Movi movie , LocalDateTime date_of_watching, int rating)
  {
   this.user_id=user_id;
   this.movie=movie;
   this.date_of_watching=date_of_watching;

    this.rating = rating;
  }

// ********Get methods***********

 public int getRating()
 {
  return rating;
 }
 public Movi getMovie()
 {
  return movie;
 }
 public int getUser_id()
 {
  return user_id;
 }

//  ******* Set methods ******** 

  public int setRating(int rating)
  {
   this.rating=rating;
   return rating;
  }
    public int setuserid(int user_id)
    {
        this.user_id=user_id;
        return user_id;
    }
    public movi setMovie(movi movie)
    {
        this.movie=movie;
        return movie;
    }
  

}
