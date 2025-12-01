package Spotify.strategies;

import Spotify.models.Playlist;
import Spotify.models.Song;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Stack;

public class RandomPlayStrategy implements PlayStrategy {

    private Playlist currentPlaylist;
    private List<Song> remainingSongs;
    private Stack<Song> history;
    private final Random random;

    public RandomPlayStrategy() {
        currentPlaylist = null;
        random = new Random();
    }

    @Override
    public void setPlaylist(Playlist playlist) {
        currentPlaylist = playlist;
        remainingSongs = new ArrayList<>(currentPlaylist.getSongs());
        history = new Stack<>();
        System.out.println(remainingSongs.isEmpty());
    }

    @Override
    public boolean hasNext() {
        return currentPlaylist != null && !remainingSongs.isEmpty();
    }

    @Override
    public Song next() {
        if (currentPlaylist == null || currentPlaylist.getSize() == 0) {

            throw new RuntimeException("No playlist loaded or playlist is empty.");
        }
        if (remainingSongs.isEmpty()) {
            System.out.println("no songs left");
            throw new RuntimeException("No songs left to play");
        }

        int idx = random.nextInt(remainingSongs.size());
        Song selectedSong = remainingSongs.get(idx);

        // Remove the selectedSong from the remaining songs list(swap with last one and pop to remove in O(1));
        int lastIndex = remainingSongs.size() - 1;
        remainingSongs.set(idx, remainingSongs.get(lastIndex));
        remainingSongs.remove(lastIndex);
        return selectedSong;
    }

    @Override
    public boolean hasPrevious() {
        return !history.isEmpty();
    }

    @Override
    public Song previous() {
        if (currentPlaylist == null || currentPlaylist.getSize() == 0) {
            throw new RuntimeException("No playlist loaded or playlist is empty.");
        }
        if (history.isEmpty()) {
            throw new RuntimeException("No songs left to play");
        }

        return history.pop();
    }
}
