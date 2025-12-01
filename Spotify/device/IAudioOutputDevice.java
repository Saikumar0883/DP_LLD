package Spotify.device;

import Spotify.models.Song;

public interface IAudioOutputDevice {
    void PlayAudio(Song song);
}