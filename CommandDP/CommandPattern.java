package CommandDP;

// Command interface
interface Command {
    void execute();

    void undo();
}

// Receivers
class Light {
    public void on() {
        System.out.println("Light is on");
    }

    public void off() {
        System.out.println("Light is Off");
    }
}

class Fan {
    public void on() {
        System.out.println("Fan is on");
    }

    public void off() {
        System.out.println("Fan is Off");
    }
}

// Concrete Command for Light
class LightCommand implements Command {

    public Light light;

    public LightCommand(Light l) {
        light = l;
    }

    @Override
    public void execute() {
        light.on();
    }

    @Override
    public void undo() {
        light.off();
    }

}

// Concrete Command for Fan
class FanCommand implements Command {

    public Fan fan;

    public FanCommand(Fan f) {
        fan = f;
    }

    @Override
    public void execute() {
        fan.on();
    }

    @Override
    public void undo() {
        fan.off();
    }
}

class RemoteController {
    private static final int numButtons = 4;
    private Command[] buttons;
    private boolean[] buttonsPressed;

    public RemoteController() {
        buttons = new Command[numButtons];
        buttonsPressed = new boolean[numButtons];
        for (int i = 0; i < numButtons; i++) {
            buttons[i] = null;
            buttonsPressed[i] = false;
        }
    }

    public void setCommand(int idx, Command cmd) {
        if (idx >= 0 && idx < numButtons) {
            buttons[idx] = cmd;
            buttonsPressed[idx] = false;
        }
    }

    public void pressButton(int idx) {
        if (idx >= 0 && idx < numButtons && buttons[idx] != null) {
            if (!buttonsPressed[idx]) {
                buttons[idx].execute();
            } else {
                buttons[idx].undo();
            }
            buttonsPressed[idx] = !buttonsPressed[idx];
        } else {
            System.out.println("No command assigned at button " + idx);
        }
    }
}

public class CommandPattern {

    public static void main(String[] args) {
        Light livingRoomLight = new Light();
        Fan ceilingFan = new Fan();

        RemoteController remoteController = new RemoteController();

        remoteController.setCommand(0, new LightCommand(livingRoomLight));
        remoteController.setCommand(1, new FanCommand(ceilingFan));

        System.out.println("Toggeling Light button");

        remoteController.pressButton(0);
        remoteController.pressButton(0);

        System.out.println("Toggeling Fan button");

        remoteController.pressButton(1);
        remoteController.pressButton(1);

        // Press unassigned button to show default message
        System.out.println("--- Pressing Unassigned Button 2 ---");
        remoteController.pressButton(2);
    }
}