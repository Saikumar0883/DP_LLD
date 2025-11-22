package DecoratorDP;

interface Character {
    String getAbilities();
}

class Mario implements Character {
    public String getAbilities() {
        return "Mario";
    }
}

abstract class CharacterDecorator implements Character {
    protected Character character;

    public CharacterDecorator(Character character) {
        this.character = character;
    }
}

class HeightUpDecorator extends CharacterDecorator {

    public HeightUpDecorator(Character c) {
        super(c);
    }

    @Override
    public String getAbilities() {
        return character.getAbilities() + " with Height up";
    }
}

class GunPowerDecorator extends CharacterDecorator {
    public GunPowerDecorator(Character c) {
        super(c);
    }

    @Override
    public String getAbilities() {
        return character.getAbilities() + " with Gun";
    }
}

// Concrete Decorator: Star Power-Up (temporary ability).
class StarPowerUp extends CharacterDecorator {
    public StarPowerUp(Character c) {
        super(c);
    }

    public String getAbilities() {
        return character.getAbilities() + " with Star Power (Limited Time)";
    }
}

public class DecoratorDesignPattern {
    public static void main(String[] args) {
        // Create a basic Mario character.
        Character mario = new Mario();
        System.out.println("Basic Character: " + mario.getAbilities());

        // Decorate Mario with a HeightUp power-up.
        mario = new HeightUpDecorator(mario);
        System.out.println("After HeightUp: " + mario.getAbilities());

        // Decorate Mario further with a GunPowerUp.
        mario = new GunPowerDecorator(mario);
        System.out.println("After GunPowerUp: " + mario.getAbilities());

        // Finally, add a StarPowerUp decoration.
        mario = new StarPowerUp(mario);
        System.out.println("After StarPowerUp: " + mario.getAbilities());
    }
}