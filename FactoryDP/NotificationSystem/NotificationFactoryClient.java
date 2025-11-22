package FactoryDP.NotificationSystem;

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

interface Factory {
    Notification createNotification(String type);
}

class NotificationFactory implements Factory {
    public Notification createNotification(String type) {
        switch (type) {
            case "email":
                return new EmailNotification();
            case "push":
                return new PushNotification();
            case "sms":
                return new SmsNotification();
            default:
                System.out.println("Invalid notification type");
                return null;
        }
    }
}

public class NotificationFactoryClient {
    public static void main(String[] args) {
        NotificationFactory factory = new NotificationFactory();
        Notification notification = factory.createNotification("email");
        notification.send("Hello sending an email");

    }
}
