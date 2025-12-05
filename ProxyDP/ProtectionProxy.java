package ProxyDP;

interface IDocumentReader {
    void unlockPDF(String filePath, String password);
}

class RealDocumentReader implements IDocumentReader {
    @Override
    public void unlockPDF(String filePath, String password) {
        System.out.println("[RealDocumentReader] Unlocking PDF at: " + filePath);
        System.out.println("[RealDocumentReader] PDF unlocked successfully with password: " + password);
        System.out.println("[RealDocumentReader] Displaying PDF content...");
    }
}

class User {
    public String userName;
    public boolean premimumMembership;

    public User(String userName, boolean premimumMembership) {
        this.userName = userName;
        this.premimumMembership = premimumMembership;
    }
}

class ProxyDocumentReader implements IDocumentReader {
    private RealDocumentReader realDocumentReader;
    private User user;

    public ProxyDocumentReader(User user) {
        this.user = user;
        this.realDocumentReader = new RealDocumentReader();
    }

    @Override
    public void unlockPDF(String fielPath, String password) {
        if (user.premimumMembership) {
            realDocumentReader.unlockPDF(fielPath, password);
        } else {
            System.out.println("[DocumentProxy] Access denied. Only premium members can unlock PDFs.");
        }
    }
}

public class ProtectionProxy {
    public static void main(String[] args) {
        User user1 = new User("Saikumar", true);
        User user2 = new User("Sai", false);

        System.out.println("== Sai (Non-Premium) tries to unlock PDF ==");
        IDocumentReader reader = new ProxyDocumentReader(user2);
        reader.unlockPDF("SecretFile", "Sai2ku@");

        System.out.println("== Saikumar (Premium) tries to unlock PDF ==");
        reader = new ProxyDocumentReader(user1);
        reader.unlockPDF("SecretFile", "Saikumar2@");
    }
}
