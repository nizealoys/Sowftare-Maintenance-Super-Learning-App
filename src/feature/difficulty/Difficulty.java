package src.feature.difficulty;

import java.util.HashMap;
import src.core.model.Feature;
import src.core.utils.ChildrenType;

public class Difficulty extends Feature {

    private static final Difficulty instance = new Difficulty();
    public static final String[] difficulties = {"Easy", "Medium", "Hard"};
    private final HashMap<String, Integer> difficultyMap;

    private Difficulty() {
        super("Difficulty", false, false, ChildrenType.NONE);
        difficultyMap = new HashMap<>();
    }

    public static Difficulty getInstance() {
        return instance;
    }

    public int getDifficulty(String courseName) {
        return difficultyMap.getOrDefault(courseName, 0);
    }

    public int getDifficultyLevel(String courseName) {
        return difficultyMap.getOrDefault(courseName, 0) + 1; // Return 1-based difficulty level
    }

    public void setDifficulty(String courseName, int difficulty) {
        if (difficulty < 0 || difficulty >= difficulties.length) {
            throw new IllegalArgumentException("Invalid difficulty level: " + difficulty);
        }
        difficultyMap.put(courseName, difficulty);
    }

}
