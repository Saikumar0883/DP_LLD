package Tomato.Factories;

import java.util.List;

import Tomato.Models.Cart;
import Tomato.Models.MenuItem;
import Tomato.Models.Order;
import Tomato.Models.Restaurant;
import Tomato.Models.User;
import Tomato.Strategies.PaymentStrategy;

public interface OrderFactory {

    Order createOrder(User user, Cart cart, Restaurant restaurant, List<MenuItem> menuItems,
            PaymentStrategy paymentStrategy, double totalCost, String orderType);
}