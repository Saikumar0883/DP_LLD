package ProxyDP;

interface IImage {
    void display();
}

class RealImage implements IImage {
    private String fileName;

    public RealImage(String fileName) {
        this.fileName = fileName;
        System.out.println("Loading image from disk " + fileName);
    }

    public void display() {
        System.out.println("[Real Image] displaying " + fileName);
    }
}

class ProxyImage implements IImage {
    private RealImage realImage;
    private String fileName;

    public ProxyImage(String fileName) {
        this.fileName = fileName;
        realImage = null;
    }

    @Override
    public void display() {
        if (realImage == null)
            realImage = new RealImage(fileName);
        realImage.display();
    }
}

public class VirtualProxy {
    public static void main(String[] args) {
        IImage image = new ProxyImage("mypic.jpeg");
        image.display();
    }
}
