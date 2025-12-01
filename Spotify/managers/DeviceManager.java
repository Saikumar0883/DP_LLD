package Spotify.managers;

import Spotify.device.IAudioOutputDevice;
import Spotify.enums.DeviceType;
import Spotify.factories.DeviceFactory;

public class DeviceManager {
    private static DeviceManager instance = null;
    private IAudioOutputDevice currentOutputDevice;

    private DeviceManager() {
        currentOutputDevice = null;
    }

    public static DeviceManager getInstance() {
        if (instance == null)
            instance = new DeviceManager();
        return instance;
    }

    public void connect(DeviceType deviceType) {
        currentOutputDevice = DeviceFactory.createDevice(deviceType);

        switch (deviceType) {
            case BLUETOOTH:
                System.out.println("Bluetooth device connected ");
                break;
            case WIRED:
                System.out.println("Wired device connected ");
                break;
            case HEADPHONES:
                System.out.println("Headphones connected ");
                break;
        }
    }

    public IAudioOutputDevice getOutPutDevice() {
        if (currentOutputDevice == null)
            throw new RuntimeException("No output device is connected");
        return currentOutputDevice;
    }

    public boolean hasOutputDevice() {
        return currentOutputDevice != null;
    }

}
