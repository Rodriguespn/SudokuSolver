package JavaVersion.DifficultyLevel;

public enum DifficultyLevel {

    EASY(2), MEDIUM(3), HARD(5);

    private final int _blocksRemoved;

    private DifficultyLevel(int blocksRemoved) {
        _blocksRemoved = blocksRemoved;
    }

    public int getNumberOfBlockToRemove() {
        return _blocksRemoved;
    }
}