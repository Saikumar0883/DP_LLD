package NotificationSystem;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

interface Notification {
    String getContent();
}

class SimpleNotification implements Notification {

    String text;

    public SimpleNotification(String text) {
        this.text = text;
    }

    @Override
    public String getContent() {
        return text;
    }

}

abstract class NotificationDecorator implements Notification {
    protected Notification notification;

    public NotificationDecorator(Notification notification) {
        this.notification = notification;
    }
}

class TimeStampDecorator extends NotificationDecorator {

    public TimeStampDecorator(Notification notification) {
        super(notification);
    }

    public String getContent() {
        return notification.getContent() + LocalDateTime.now();
    }
}

class SignatureDecorator extends NotificationDecorator {
    private String signature;

    public SignatureDecorator(Notification notification, String signature) {
        super(notification);
        this.signature = signature;
    }

    public String getContent() {
        return notification.getContent() + " \n --" + signature + "\n\n";
    }
}

interface Observer {
    void update();
}

interface Observable {

    void addObserver(Observer o);

    void removeObserver(Observer o);

    void notifyObservers();
}

class NotificationObservable implements Observable {
    private List<Observer> observers = new ArrayList<>();
    private Notification notification = null;

    @Override
    public void addObserver(Observer o) {
        if (!observers.contains(o))
            observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        for (Observer o : observers) {
            o.update();
        }
    }

    protected void SetNotification(Notification notification) {
        this.notification = notification;
        notifyObservers();
    }

    String getNotification() {
        return notification.getContent();
    }

}

class Logger implements Observer {

    NotificationObservable notificationObservable;

    public Logger() {
        this.notificationObservable = NotificationService.getInstance().getObservable();
        notificationObservable.addObserver(this);
    }

    public Logger(NotificationObservable notificationObservable) {
        this.notificationObservable = notificationObservable;
        notificationObservable.addObserver(this);
    }

    @Override
    public void update() {
        System.out.println("Logging New Notification : \n" + notificationObservable.getNotification());
    }

}

class NotificationEngine implements Observer {
    NotificationObservable notificationObservable;
    List<NotificationStrategy> notificationStrategies = new ArrayList<>();

    public NotificationEngine() {
        this.notificationObservable = NotificationService.getInstance().getObservable();
        notificationObservable.addObserver(this);
    }

    public NotificationEngine(NotificationObservable notificationObservable) {
        this.notificationObservable = notificationObservable;
        notificationObservable.addObserver(this);
    }

    @Override
    public void update() {
        for (NotificationStrategy strategy : notificationStrategies) {
            strategy.sendNotification(notificationObservable.getNotification());
        }
    }

    public void addNotificationStrategy(NotificationStrategy notificationStrategy) {
        notificationStrategies.add(notificationStrategy);
    }

}

interface NotificationStrategy {
    void sendNotification(String content);
}

class EmailStrategy implements NotificationStrategy {
    private String emailId;

    public EmailStrategy(String emailId) {
        this.emailId = emailId;
    }

    public void sendNotification(String content) {
        System.out.println("Sending email Notification to: " + emailId + "\n" + content);
    }

}

class SmsStrategy implements NotificationStrategy {

    private String mobileNumber;

    public SmsStrategy(String mobileNumber) {
        this.mobileNumber = mobileNumber;
    }

    public void sendNotification(String content) {
        System.out.println("Sending SMS Notification to: " + mobileNumber + "\n" + content);
    }
}

class PopUpStrategy implements NotificationStrategy {
    @Override
    public void sendNotification(String content) {
        System.out.printf("Sending Notification through PopUp \n %s", content);
    }
}

class NotificationService {

    public static NotificationService instance = null;
    NotificationObservable observable = null;
    List<Notification> notifications = new ArrayList<>();

    private NotificationService() {
        observable = new NotificationObservable();
        System.out.println("Singleton class");
    }

    public static NotificationService getInstance() {
        if (instance == null)
            instance = new NotificationService();
        return instance;
    }

    public NotificationObservable getObservable() {
        return observable;
    }

    public void sendNotification(Notification notification) {
        notifications.add(notification);
        observable.SetNotification(notification);
    }
}

public class NotificationSystem {
    public static void main(String[] args) {
        NotificationService notificationService = NotificationService.getInstance();

        Logger _logger = new Logger();

        NotificationEngine notificationEngine = new NotificationEngine();

        notificationEngine.addNotificationStrategy(new EmailStrategy("saikumarailwar9@gmail.com"));
        notificationEngine.addNotificationStrategy(new SmsStrategy("9440888507"));
        notificationEngine.addNotificationStrategy(new PopUpStrategy());

        Notification notification = new SimpleNotification("Your Order has been shipped");
        notification = new TimeStampDecorator(notification);
        notification = new SignatureDecorator(notification, "Customer Care");

        notificationService.sendNotification(notification);
    }
}