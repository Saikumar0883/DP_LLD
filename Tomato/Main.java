package Tomato;

import java.util.List;

import Tomato.Models.Order;
import Tomato.Models.Restaurant;
import Tomato.Models.User;
import Tomato.Strategies.UpiPaymentStrategy;

public class Main {
    public static void main(String[] args) {

        Tomato tomato = new Tomato();

        User user = new User(2688, "Saikumar", "Delhi");
        System.out.println("User: " + user.getName() + " is active.");

        List<Restaurant> restaurantList = tomato.searchRestaurants(user.getAddress());
        if (restaurantList.isEmpty()) {
            System.out.println("No restaurants found");
            return;
        }

        System.out.println("Found Restaurants:");
        for (Restaurant restaurant : restaurantList) {
            System.out.println(" - " + restaurant.getName());
        }

        // User selects a restaurant
        tomato.selectRestaurant(user, restaurantList.get(0));
        System.out.println("Selected restaurant: " + restaurantList.get(0).getName());

        // User adds items to the cart
        tomato.addToCart(user, "P1");
        tomato.addToCart(user, "P2");

        tomato.printUserCart(user);

        // User checkout the cart
        Order order = tomato.checkoutNow(user, "Delivery", new UpiPaymentStrategy("1234567890"));

        // User pays for the cart. If payment is successful, notification is sent.
        tomato.payForOrder(user, order);
    }
}
