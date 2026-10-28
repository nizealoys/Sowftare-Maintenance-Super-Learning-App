package src.feature.difficulty;

import src.core.model.Feature;
import src.core.utils.ChildrenType;

public class Difficulty extends Feature {

    public static final Difficulty instance = new Difficulty();
    public static final String[] difficulties = {"Easy", "Medium", "Hard"};
    private int currentDifficultyIndex;

    private Difficulty() {
        super("Difficulty", false, false, ChildrenType.NONE);
        this.currentDifficultyIndex = 0;
    }

    public static Difficulty getInstance() {
        return instance;
    }

    public String getCurrentDifficulty() {
        return difficulties[currentDifficultyIndex];
    }

    // Get the current difficulty level as an integer (1 for Easy, 2 for Medium, 3 for Hard)
    public int getCurrentDifficultyLevel() {
        return currentDifficultyIndex + 1;
    }

    public int getCurrentDifficultyIndex() {
        return currentDifficultyIndex;
    }

    public void setCurrentDifficultyLevel(int level) {
        if (level >= 1 && level <= difficulties.length) {
            this.currentDifficultyIndex = level - 1;
        } else {
            throw new IllegalArgumentException("Invalid difficulty level: " + level);
        }
    }

    public void setCurrentDifficultyIndex(int index) {
        if (index >= 0 && index < difficulties.length) {
            this.currentDifficultyIndex = index;
        } else {
            throw new IllegalArgumentException("Invalid difficulty index: " + index);
        }
    }
}
