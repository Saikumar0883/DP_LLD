package AbstractFactory;

interface Burger {
    void prepare();
}

interface GarlicBread {
    void prepare();
}

class BasicBurger implements Burger {
    public void prepare() {
        System.out.println("Preparing Basic burger");
    }
}

class StandardBurger implements Burger {
    public void prepare() {
        System.out.println("Preparing Standard burger");
    }
}

class PremiumBurger implements Burger {
    public void prepare() {
        System.out.println("Preparing Premium burger");
    }
}

class BasicWheatBurger implements Burger {
    public void prepare() {
        System.out.println("Preparing Basic wheat burger");
    }
}

class StandardWheatBurger implements Burger {
    public void prepare() {
        System.out.println("Preparing Standard wheat burger");
    }
}

class PremiumWheatBurger implements Burger {
    public void prepare() {
        System.out.println("Preparing Premium wheat burger");
    }
}

class BasicGarlicBread implements GarlicBread {
    public void prepare() {
        System.out.println("Preparing Basic GarlicBread");
    }
}

class StandardGarlicBread implements GarlicBread {
    public void prepare() {
        System.out.println("Preparing Standard GarlicBread");
    }
}

class PremiumGarlicBread implements GarlicBread {
    public void prepare() {
        System.out.println("Preparing Premium GarlicBread");
    }
}

class BasicWheatGarlicBread implements GarlicBread {
    public void prepare() {
        System.out.println("Preparing Basic wheat GarlicBread");
    }
}

class StandardWheatGarlicBread implements GarlicBread {
    public void prepare() {
        System.out.println("Preparing Standard wheat GarlicBread");
    }
}

class PremiumWheatGarlicBread implements GarlicBread {
    public void prepare() {
        System.out.println("Preparing Premium wheat GarlicBread");
    }
}

interface AbstractFactory {
    Burger createBurger(String type);

    GarlicBread creatGarlicBread(String type);
}

class SinghFactory implements AbstractFactory {
    public Burger createBurger(String type) {
        switch (type) {
            case "basic":
                return new BasicBurger();
            case "standard":
                return new StandardBurger();
            case "premimum":
                return new PremiumBurger();
            default:
                System.out.println("Invalid burger type");
                return null;
        }
    }

    public GarlicBread creatGarlicBread(String type) {
        switch (type) {
            case "basic":
                return new BasicGarlicBread();
            case "standard":
                return new StandardGarlicBread();
            case "premimum":
                return new PremiumGarlicBread();
            default:
                System.out.println("Invalid garlic bread type");
                return null;
        }
    }
}

class KingFactory implements AbstractFactory {
    public Burger createBurger(String type) {
        switch (type) {
            case "basic":
                return new BasicWheatBurger();
            case "standard":
                return new StandardWheatBurger();
            case "premimum":
                return new PremiumWheatBurger();
            default:
                System.out.println("Invalid wheat burger type");
                return null;
        }
    }

    public GarlicBread creatGarlicBread(String type) {
        switch (type) {
            case "basic":
                return new BasicWheatGarlicBread();
            case "standard":
                return new StandardWheatGarlicBread();
            case "premimum":
                return new PremiumWheatGarlicBread();
            default:
                System.out.println("Invalid wheat garlic bread type");
                return null;
        }
    }
}

public class AbstractFactoryMethod {
    public static void main(String[] args) {
        String type = "premimum";
        AbstractFactory factory = new KingFactory();
        GarlicBread bread = factory.creatGarlicBread(type);
        Burger burger = factory.createBurger(type);
        bread.prepare();
        burger.prepare();
    }
}
