package Spotify.device;

import Spotify.external.BluetoothSpeakerApi;
import Spotify.models.Song;

public class BluetoothSpeakerAdapter implements IAudioOutputDevice {

    private BluetoothSpeakerApi bluetoothSpeakerApi;

    public BluetoothSpeakerAdapter(BluetoothSpeakerApi api) {
        this.bluetoothSpeakerApi = api;
    }

    @Override
    public void PlayAudio(Song song) {
        String payload = song.getTitle() + " by " + song.getArtist();
        bluetoothSpeakerApi.playSoundViaBluetooth(payload);
    }
    
}
