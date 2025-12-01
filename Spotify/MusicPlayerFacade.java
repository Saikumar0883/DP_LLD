package Spotify;

import Spotify.core.AudioEngine;
import Spotify.device.IAudioOutputDevice;
import Spotify.enums.DeviceType;
import Spotify.enums.PlayStrategyType;
import Spotify.managers.DeviceManager;
import Spotify.managers.PlaylistManager;
import Spotify.managers.StrategyManager;
import Spotify.models.Playlist;
import Spotify.models.Song;
import Spotify.strategies.PlayStrategy;

public class MusicPlayerFacade {
    private static MusicPlayerFacade instance;
    AudioEngine audioEngine;
    Playlist currentPlaylist;
    PlayStrategy playStrategy;

    private MusicPlayerFacade() {
        audioEngine = new AudioEngine();
        currentPlaylist = null;
        playStrategy = null;
    }

    public static MusicPlayerFacade getInstance() {
        if (instance == null) instance = new MusicPlayerFacade();
        return instance;
    }

    public void connectDevice(DeviceType deviceType) {
        DeviceManager.getInstance().connect(deviceType);
    }

    public void setStrategy(PlayStrategyType strategyType) {
        this.playStrategy = StrategyManager.getInstance().getStrategy(strategyType);
    }

    public void loadPlaylist(String playlistName) {
        currentPlaylist = PlaylistManager.getInstance().getPlaylist(playlistName);
        if (playStrategy == null) {
            throw new RuntimeException("Play strategy not set before loading.");
        }
        System.out.println("Strategy instance class = " + playStrategy.getClass());
        playStrategy.setPlaylist(currentPlaylist);
    }


    public void playSong(Song song) {
        if (!DeviceManager.getInstance().hasOutputDevice()) {
            throw new RuntimeException("No audio device connected.");
        }
        IAudioOutputDevice device = DeviceManager.getInstance().getOutPutDevice();
        audioEngine.play(device, song);
    }

    public void pauseSong(Song song) {
        if (!audioEngine.getCurrentSongTitle().equals(song.getTitle())) {
            throw new RuntimeException("Cannot pause \"" + song.getTitle() + "\"; not currently playing.");
        }
        audioEngine.pause();
    }

    public void playAllTracks() {
        if (currentPlaylist == null || currentPlaylist.getSize() == 0) {
            throw new RuntimeException("Playlist is not loaded or empty");
        }

        while (playStrategy.hasNext()) {
            Song nextSong = playStrategy.next();
            IAudioOutputDevice device = DeviceManager.getInstance().getOutPutDevice();
            audioEngine.play(device, nextSong);
        }
    }

    public void playNextTrack() {
        if (currentPlaylist == null || currentPlaylist.getSize() == 0) {
            throw new RuntimeException("Playlist is not loaded or empty");
        }
        if (playStrategy.hasNext()) {
            Song nextSong = playStrategy.next();
            IAudioOutputDevice device = DeviceManager.getInstance().getOutPutDevice();
            audioEngine.play(device, nextSong);
        } else {
            System.out.printf("Completed Playlist:" + currentPlaylist.getPlaylistName());
        }
    }

    public void playPrevious() {
        if (currentPlaylist == null || currentPlaylist.getSize() == 0) {
            throw new RuntimeException("Playlist is not loaded or empty");
        }
        if (playStrategy.hasPrevious()) {
            Song prevSong = playStrategy.previous();
            IAudioOutputDevice device = DeviceManager.getInstance().getOutPutDevice();
            audioEngine.play(device, prevSong);
        } else {
            System.out.printf("Completed Playlist:" + currentPlaylist.getPlaylistName());
        }
    }

    public void enqueueNext(Song song) {
        playStrategy.addToNext(song);
    }
}
