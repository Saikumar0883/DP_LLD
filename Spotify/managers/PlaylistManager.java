package Spotify.managers;

import Spotify.models.Playlist;
import Spotify.models.Song;

import java.util.HashMap;

public class PlaylistManager {
    private static PlaylistManager instance = null;
    HashMap<String, Playlist> playlists;

    private PlaylistManager() {
        playlists = new HashMap<>();
    }

    public static PlaylistManager getInstance() {
        if (instance == null)
            instance = new PlaylistManager();
        return instance;
    }

    public void createPlaylist(String playlist) {
        if (playlists.containsKey(playlist))
            throw new RuntimeException("Playlist already exists");

        playlists.put(playlist, new Playlist(playlist));
    }

    public void addSongToPlaylist(String playlist, Song song) {
        if (!playlists.containsKey(playlist)) {
            throw new RuntimeException("Playlist doesn't exists");
        }
        Playlist pl = playlists.get(playlist);
        pl.addSongToPlaylist(song);
    }

    public Playlist getPlaylist(String playlistname) {
        if (!playlists.containsKey(playlistname))
            throw new RuntimeException("Playlist doesn't exists");
        return playlists.get(playlistname);
    }
}
