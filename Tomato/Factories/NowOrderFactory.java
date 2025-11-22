package Tomato.Factories;

import java.util.List;

import Tomato.Models.Cart;
import Tomato.Models.DeliveryOrder;
import Tomato.Models.MenuItem;
import Tomato.Models.Order;
import Tomato.Models.PickupOrder;
import Tomato.Models.Restaurant;
import Tomato.Models.User;
import Tomato.Strategies.PaymentStrategy;
import Tomato.Utils.TimeUtils;

public class NowOrderFactory implements OrderFactory {

    @Override
    public Order createOrder(User user, Cart cart, Restaurant restaurant, List<MenuItem> menuItems,
            PaymentStrategy paymentStrategy, double totalCost, String orderType) {
        Order order = null;

        if (orderType.equals("Delivery")) {
            DeliveryOrder deliveryOrder = new DeliveryOrder();
            deliveryOrder.setUserAddress(user.getAddress());
            order = deliveryOrder;
        } else {
            PickupOrder pickupOrder = new PickupOrder();
            pickupOrder.setRestaurantAddress(restaurant.getLocation());
            order = pickupOrder;
        }
        order.setUser(user);
        order.setRestaurant(restaurant);
        order.setItems(menuItems);
        order.setPaymentStrategy(paymentStrategy);
        order.setScheduled(TimeUtils.getCurrentTime());
        order.setTotal(totalCost);
        return order;
    }

}