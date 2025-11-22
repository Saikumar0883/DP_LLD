package SingletonDP;

public class EagerInitialization {
    private static EagerInitialization instance = new EagerInitialization();

    private EagerInitialization() {
        System.out.println("Singleton constructor called");
    }

    public static EagerInitialization getInstance() {
        return instance;
    }

    public static void main(String[] args) {
        EagerInitialization s1 = EagerInitialization.getInstance();
        EagerInitialization s2 = EagerInitialization.getInstance();

        System.out.println(s1 == s2);
    }
}

// in this right without having any locks and anything we can get functionality
// of singleton instance when there is not multithreading but by this we have a
// problem is when ever we declare this class before the main class itself the
// instance get created. what if the instance is very expensive and we never
// used the instance so at that cases this is not useful. that leads to wastage
// of resources