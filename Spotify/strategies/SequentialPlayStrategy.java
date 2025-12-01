package Spotify.strategies;

import Spotify.models.Playlist;
import Spotify.models.Song;

public class SequentialPlayStrategy implements PlayStrategy {
    private Playlist currentPlaylist;
    private int currentIndex;

    public SequentialPlayStrategy() {
        currentIndex = -1;
        currentPlaylist = null;
    }

    @Override
    public void setPlaylist(Playlist playlist) {
        currentPlaylist = playlist;
    }

    @Override
    public boolean hasNext() {
        return ((currentIndex + 1) < currentPlaylist.getSize());
    }

    @Override
    public Song next() {
        if (currentPlaylist == null)
            throw new RuntimeException("No playlist is loaded");
        if (currentPlaylist.getSize() == 0)
            throw new RuntimeException("Playlist is empty");
        currentIndex++;
        return currentPlaylist.getSongs().get(currentIndex);
    }

    @Override
    public boolean hasPrevious() {
        return ((currentIndex - 1) > 0);
    }

    @Override
    public Song previous() {
        if (currentPlaylist == null)
            throw new RuntimeException("No playlist is loaded");
        if (currentPlaylist.getSize() == 0)
            throw new RuntimeException("Playlist is empty");
        currentIndex--;
        return currentPlaylist.getSongs().get(currentIndex);
    }
}
