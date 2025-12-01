package Spotify.factories;

import Spotify.device.BluetoothSpeakerAdapter;
import Spotify.device.HeadPhonesAdapter;
import Spotify.device.IAudioOutputDevice;
import Spotify.device.WiredSpeakerAdapter;
import Spotify.enums.DeviceType;
import Spotify.external.BluetoothSpeakerApi;
import Spotify.external.HeadPhonesApi;
import Spotify.external.WiredSpeakerApi;

public class DeviceFactory {
    public static IAudioOutputDevice createDevice(DeviceType type) {
         switch (type) {
            case BLUETOOTH :
                return new BluetoothSpeakerAdapter(new BluetoothSpeakerApi());
            case WIRED :
                return new WiredSpeakerAdapter(new WiredSpeakerApi());
            case HEADPHONES :
            default:
                return new HeadPhonesAdapter(new HeadPhonesApi());
        }
    }
}
