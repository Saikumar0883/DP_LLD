package Spotify.managers;

import Spotify.enums.PlayStrategyType;
import Spotify.strategies.CustomQueuePlayStrategy;
import Spotify.strategies.PlayStrategy;
import Spotify.strategies.RandomPlayStrategy;
import Spotify.strategies.SequentialPlayStrategy;

public class StrategyManager {
    private static StrategyManager instance = null;
    private SequentialPlayStrategy sequentialPlayStrategy;
    private RandomPlayStrategy randomPlayStrategy;
    private CustomQueuePlayStrategy customQueuePlayStrategy;

    private StrategyManager() {
        sequentialPlayStrategy = new SequentialPlayStrategy();
        randomPlayStrategy = new RandomPlayStrategy();
        customQueuePlayStrategy = new CustomQueuePlayStrategy();
    }

    public static StrategyManager getInstance() {
        if (instance == null)
            instance = new StrategyManager();
        return instance;
    }

    public PlayStrategy getStrategy(PlayStrategyType strategy) {
        if (strategy == PlayStrategyType.SEQUENTIAL) {
            return sequentialPlayStrategy;
        } else if (strategy == PlayStrategyType.RANDOM) {
            return randomPlayStrategy;
        } else {
            return customQueuePlayStrategy;
        }
    }
}
