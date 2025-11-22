package FactoryDP.BurgerFactory;

interface Burger {
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

interface BurgerFactory {
    Burger createBurger(String type);
}

class SinghBurger implements BurgerFactory {
    public Burger createBurger(String type) {
        switch (type) {
            case "basic":
                return new BasicBurger();
            case "standard":
                return new StandardBurger();
            case "premimum":
                return new PremiumBurger();
            default:
                System.out.println("Invalid burger type!");
                return null;
        }
    }
}

class KingBurger implements BurgerFactory {
    public Burger createBurger(String type) {
        switch (type) {
            case "basic":
                return new BasicWheatBurger();
            case "standard":
                return new StandardWheatBurger();
            case "premimum":
                return new PremiumWheatBurger();
            default:
                System.out.println("Invalid burger type!");
                return null;
        }
    }
}

public class FactoryMethod {
    public static void main(String[] args) {
        String type = "basic";

        BurgerFactory burgerFactory = new KingBurger();
        Burger burger = burgerFactory.createBurger(type);
        if (burger != null)
            burger.prepare();
    }
}
