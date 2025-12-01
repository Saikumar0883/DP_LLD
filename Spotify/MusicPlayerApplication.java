package Spotify;

import Spotify.enums.DeviceType;
import Spotify.enums.PlayStrategyType;
import Spotify.managers.PlaylistManager;
import Spotify.models.Song;

import java.util.ArrayList;
import java.util.List;

public class MusicPlayerApplication {
    private static MusicPlayerApplication instance;
    List<Song> songsLibrary;

    private MusicPlayerApplication() {
        songsLibrary = new ArrayList<>();
    }

    public static MusicPlayerApplication getInstance() {
        if (instance == null)
            instance = new MusicPlayerApplication();
        return instance;
    }

    public void createSong(String title, String artist, String filePath) {
        Song newSong = new Song(title, artist, filePath);
        songsLibrary.add(newSong);
    }

    public void createPlaylist(String playlistName) {
        PlaylistManager.getInstance().createPlaylist(playlistName);
    }

    public Song findSongByTitle(String title) {
        for (Song s : songsLibrary) {
            if (s.getTitle() == title) {
                return s;
            }
        }
        return null;
    }

    public void addSongToPlaylist(String playlistName, String title) {
        Song song = findSongByTitle(title);
        if (song == null) {
            throw new RuntimeException("Song \"" + title + "\" not found in library.");
        }
        PlaylistManager.getInstance().addSongToPlaylist(playlistName, song);
    }

    public void connectAudioDevice(DeviceType deviceType) {
        MusicPlayerFacade.getInstance().connectDevice(deviceType);
    }

    public void setStrategy(PlayStrategyType playStrategyType) {
        MusicPlayerFacade.getInstance().setStrategy(playStrategyType);
    }

    public void loadPlaylist(String playlistName) {
        MusicPlayerFacade.getInstance().loadPlaylist(playlistName);
    }

    public void playSingleSong(String songTitle) {
        Song song = findSongByTitle(songTitle);
        if (song == null) {
            throw new RuntimeException("Song \"" + songTitle + "\" not found.");
        }
        MusicPlayerFacade.getInstance().playSong(song);
    }

    public void pauseCurrentSong(String songTitle) {
        Song song = findSongByTitle(songTitle);
        if (song == null) {
            throw new RuntimeException("Song \"" + songTitle + "\" not found.");
        }
        MusicPlayerFacade.getInstance().pauseSong(song);
    }

    public void playAllTracksInPlaylist() {
        MusicPlayerFacade.getInstance().playAllTracks();
    }

    public void playPreviousTrackInPlaylist() {
        MusicPlayerFacade.getInstance().playPrevious();
    }

    public void queueSongNext(String songTitle) {
        Song song = findSongByTitle(songTitle);
        if (song == null) {
            throw new RuntimeException("Song \"" + songTitle + "\" not found.");
        }
        MusicPlayerFacade.getInstance().enqueueNext(song);
    }
}
