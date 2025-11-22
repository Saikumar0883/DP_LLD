package ObserverDP;

import java.util.ArrayList;
import java.util.List;

interface ISubscriber {
    void update(Ichannel channel);
}

interface Ichannel {
    void subscribe(ISubscriber subscriber);

    void unsubscribe(ISubscriber subscriber);

    void notifySubscribers();

    String getChannelName();

    String getVideoData();
}

class Channel implements Ichannel {
    private List<ISubscriber> subscribers;
    private String name;
    private String latestVideo;

    public Channel(String name) {
        this.name = name;
        this.subscribers = new ArrayList<>();
    }

    @Override
    public void subscribe(ISubscriber subscriber) {
        if (!subscribers.contains(subscriber))
            subscribers.add(subscriber);
    }

    @Override
    public void unsubscribe(ISubscriber subscriber) {
        subscribers.remove(subscriber);
    }

    @Override
    public void notifySubscribers() {
        for (ISubscriber subscriber : subscribers) {
            subscriber.update(this);
        }
    }

    public void uploadVideo(String title) {
        latestVideo = title;
        System.out.println("\n[" + name + " uploaded \"" + title + "\"]");
        notifySubscribers();
    }

    @Override
    public String getVideoData() {
        return "Checkout our new video: " + latestVideo;
    }

    @Override
    public String getChannelName() {
        return name;
    }
}

class Subscriber implements ISubscriber {
    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    @Override
    public void update(Ichannel channel) {
        System.out.println("Hey " + name + ", " + channel.getVideoData() +
                " (Channel: " + channel.getChannelName() + ")");
    }
}

public class ObserverDesignPattern {
    public static void main(String[] args) {
        Channel saiArmy = new Channel("SaiArmy");
        Channel gateSmasher = new Channel("GateSmasher");

        Subscriber s1 = new Subscriber("Saikumar");
        Subscriber s2 = new Subscriber("Anil");

        saiArmy.subscribe(s1);
        saiArmy.subscribe(s2);
        saiArmy.uploadVideo("System Design Playlist");

        saiArmy.unsubscribe(s2);
        gateSmasher.subscribe(s1);
        gateSmasher.uploadVideo("Compiler Design New Playlist");

        saiArmy.uploadVideo("System Design Playlist Updated Content");
    }
}
