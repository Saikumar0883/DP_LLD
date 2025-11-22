package SingletonDP;

public class ThreadSafeLockingSIngleton {

    private static ThreadSafeLockingSIngleton instance = null;

    private ThreadSafeLockingSIngleton() {
        System.out.println("Singleton constructor called ");
    }

    public static ThreadSafeLockingSIngleton getinstance() {
        synchronized (ThreadSafeLockingSIngleton.class) {
            if (instance == null) {
                instance = new ThreadSafeLockingSIngleton();
            }
            return instance;
        }
    }

    public static void main(String[] args) {
        ThreadSafeLockingSIngleton s1 = ThreadSafeLockingSIngleton.getinstance();
        ThreadSafeLockingSIngleton s2 = ThreadSafeLockingSIngleton.getinstance();

        System.out.println(s1 == s2);
    }
}

// in this method everytime we call we lock the instance variable for
// unnecessary checks and get of instances
// like if program calls 20 times for the first time the lock happens and it
// returns the new instance but for next 19 times unnecessary locks are happened
// so we will go for double check and aquire lock we move to
// threadsafedoublelocking method