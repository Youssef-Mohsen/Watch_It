package com.example.watch_it;

import java.time.LocalDate;
import java.util.ArrayList;

// This class represents a user subscription for a streaming service.
public class Subscription {
    // Enum to define the available subscription plans.
    public enum Plans {
        BASIC, STANDARD, PREMIUM
    }

    // Fields to store subscription information.
    private int user_id;
    private Plans plan;
    private double price;
    private LocalDate startDate;
    private int moviesWatched;
    public boolean subscriptionNotValid = false;
    public boolean updatePlan = false;

    // Constructor to initialize a new subscription with a user ID and plan.
    public Subscription(int user_id, Plans plan) {
        this.user_id = user_id;
        this.plan = plan;

        // Set the price based on the selected plan.
        if (plan == Plans.BASIC) {
            this.price = 100;
        } else if (plan == Plans.STANDARD) {
            this.price = 200;
        } else if (plan == Plans.PREMIUM) {
            this.price = 300;
        }

        // Set the start date to the current date and initialize moviesWatched to 0.
        this.startDate = LocalDate.now();
        this.moviesWatched = 0;

    }

    // Setter methods for updating subscription information.
    public void setUserID(int user_id) {
        this.user_id = user_id;
    }

    public void setPlan(Plans plan) {
        this.plan = plan;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setMoviesWatched(int moviesWatched) {
        this.moviesWatched = moviesWatched;
    }

    // Getter methods to retrieve subscription information.
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

    // Method to add a movie to the subscription, considering plan limits.
    public void addMovie() {
        if (isSubscriptionActive()) {
            moviesWatched++;

            if (plan == Plans.BASIC && moviesWatched <= 5) {
                if (moviesWatched == 5)
                    updatePlan = true;
            } else if (plan == Plans.STANDARD && moviesWatched <= 10) {
                if (moviesWatched == 10)
                    updatePlan = true;
            } else if (plan == Plans.PREMIUM && moviesWatched <= 30) {
                if (moviesWatched == 30)
                    updatePlan = true;
            } else {
                subscriptionNotValid = true;
            }

        }
    }

    // Method to update the subscription plan and reset subscription information.
    public void updateSubscription(Plans newPlan) {
        this.plan = newPlan;
        if (newPlan == Plans.BASIC) {
            this.price = 10;
        } else if (newPlan == Plans.STANDARD) {
            this.price = 15;
        } else if (newPlan == Plans.PREMIUM) {
            this.price = 20;
        }
        this.startDate = LocalDate.now();
        this.moviesWatched = 0;
        System.out.println("Subscription updated successfully.");
    }

    // Method to delete the subscription plan.
    public void deleteSubscription() {
        this.plan = null;
    }

    // Private helper method to check if the subscription is still active.
    public boolean isSubscriptionActive() {
        LocalDate currentDate = LocalDate.now();
        LocalDate endDate = startDate.plusDays(30);

        // The subscription is active if the current date is before the end date.
        return currentDate.isBefore(endDate);
    }


}