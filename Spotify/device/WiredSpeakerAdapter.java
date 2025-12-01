package Spotify.device;

import Spotify.external.WiredSpeakerApi;
import Spotify.models.Song;

public class WiredSpeakerAdapter implements IAudioOutputDevice {

    private WiredSpeakerApi wiredSpeakerApi;

    public WiredSpeakerAdapter(WiredSpeakerApi api) {
        wiredSpeakerApi = api;
    }

    @Override
    public void PlayAudio(Song song) {
        String payload = song.getTitle() + " by " + song.getArtist();
        wiredSpeakerApi.playSoundViaCable(payload);
    }

}
