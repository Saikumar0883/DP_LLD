package Spotify.strategies;

import Spotify.models.Playlist;
import Spotify.models.Song;

public interface PlayStrategy {
    void setPlaylist(Playlist playlist);

    boolean hasNext();

    Song next();

    boolean hasPrevious();

    Song previous();

    default public void addToNext(Song song) {

    }
}
