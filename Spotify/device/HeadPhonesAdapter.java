package Spotify.device;

import Spotify.external.HeadPhonesApi;
import Spotify.models.Song;

public class HeadPhonesAdapter implements IAudioOutputDevice {

    private HeadPhonesApi api;

    public HeadPhonesAdapter(HeadPhonesApi api) {
        this.api = api;
    }

    @Override
    public void PlayAudio(Song song) {
        String payload = song.getTitle() + " by " + song.getArtist();
        api.playSoundViaHeadPhones(payload);
    }

}
