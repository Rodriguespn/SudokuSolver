package JavaVersion.Board;

/**
 * The difficulty level of the Sudoku Game
 */
public enum DifficultyLevel {

    EASY(3), MEDIUM(5), HARD(6);

    private final int _blocksRemoved;

    private DifficultyLevel(int blocksRemoved) {
        _blocksRemoved = blocksRemoved;
    }

    /** 
     * The number of block to be removed from the completed board
     *  
     * @return Number of block to be removed from the completed board
     *
     */
    public int getNumberOfBlockToRemove() {
        return _blocksRemoved;
    }
}