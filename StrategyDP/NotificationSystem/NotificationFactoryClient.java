package StrategyDP.NotificationSystem;

interface Notification {
    void send(String message);
}

class EmailNotification implements Notification {
    public void send(String message) {
        System.out.printf("Sending email notification: {}", message);
    }
}

class SmsNotification implements Notification {
    public void send(String message) {
        System.out.printf("Sending sms notification :{}", message);
    }
}

class PushNotification implements Notification {
    public void send(String message) {
        System.out.printf("Sending push notification :{}", message);
    }
}

class NotificationService {
    private Notification notificationstrategy;

    public NotificationService(Notification notification) {
        this.notificationstrategy = notification;
    }

    public void setNotificationStrategy(Notification notificationStrategy) {
        this.notificationstrategy = notificationStrategy;
    }

    public void sendNotification(String message) {
        if (notificationstrategy == null) {
            System.out.println("No notification strategy set!");
        } else {
            notificationstrategy.send(message);
        }
    }
}

public class NotificationFactoryClient {
    public static void main(String[] args) {
        // Use Email Notification
        NotificationService service = new NotificationService(new EmailNotification());
        service.sendNotification("Welcome to our platform!");

        // Change to SMS Notification at runtime
        service.setNotificationStrategy(new SmsNotification());
        service.sendNotification("Your OTP code is 12345");

        // Change to Push Notification
        service.setNotificationStrategy(new PushNotification());
        service.sendNotification("You have a new message!");
    }
}
