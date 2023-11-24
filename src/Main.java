import watch_it.Subscription;
public class Main {
    public static void main(String[] args) {
        Subscription basicSubscription = new Subscription(1, Subscription.Plans.BASIC);

        basicSubscription.addMovie();

        System.out.println( basicSubscription.getPlan());
        System.out.println(basicSubscription.getPrice());
        System.out.println(basicSubscription.getMoviesWatched());
        System.out.println(basicSubscription.getStartDate());
        }
    }
