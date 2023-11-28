package watch_it;

import java.time.LocalDate;


public class Subscription {
    public enum Plans {
        BASIC, STANDARD, PREMIUM
    }
    private int user_id;
    private Plans plan;
    private double price;
    private LocalDate startDate;
    private int moviesWatched;

    public Subscription(int user_id,Plans plan) {
        this.user_id=user_id;
        this.plan = plan;
        if(plan==Plans.BASIC) {
            this.price = 10;
        } else if (plan==Plans.STANDARD) {
            this.price = 15;
        }
        else if (plan==Plans.PREMIUM) {
            this.price = 20;
        }
        this.startDate = LocalDate.now();
        this.moviesWatched = 0;
    }

    public void setUserID(int user_id) {
        this.user_id=user_id;
    }
    public void setPlan(Plans plan) {this.plan=plan;
    }

    public void setPrice(double price) {
        this.price=price;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate=startDate;
    }

    public void setMoviesWatched(int moviesWatched) {
         this.moviesWatched =moviesWatched;
    }
    public int getUserID() {
        return user_id;
    }
    public Plans getPlan() {
        return plan;
    }

    public double getPrice() {
        return price;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public int getMoviesWatched() {
        return moviesWatched;
    }

    public void addMovie() {
        if (isSubscriptionActive()) {
            moviesWatched++;
            if(plan==Plans.BASIC && moviesWatched<=5)
            {
                System.out.println("Movie added. Total movies watched: " + moviesWatched);
            }
            else if(plan==Plans.STANDARD && moviesWatched<=10)
            {
                System.out.println("Movie added. Total movies watched: " + moviesWatched);

            }
            else if(plan==Plans.PREMIUM && moviesWatched<=30)
            {
                System.out.println("Movie added. Total movies watched: " + moviesWatched);

            }
            else {
                System.out.println("You reached the limit of the movies. Please renew to watch more movies.");
            }

        } else {
            System.out.println("Subscription expired. Please renew to watch more movies.");
        }
    }

    public void updateSubscription(Plans newPlan) {
        this.plan = newPlan;
        if(newPlan==Plans.BASIC) {
            this.price = 10;
        } else if (newPlan==Plans.STANDARD) {
            this.price = 15;
        }
        else if (newPlan==Plans.PREMIUM) {
            this.price = 20;
        }
        this.startDate =LocalDate.now();
        this.moviesWatched = 0;
        System.out.println("Subscription updated successfully.");
    }

    public void deleteSubscription() {
        this.plan=null;

    }

    private boolean isSubscriptionActive() {
        LocalDate currentDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(30);

        return currentDate.isBefore(endDate);
    }
}