package src.feature.difficulty;

import java.util.HashMap;
import src.core.model.Feature;
import src.core.utils.ChildrenType;

public class Difficulty extends Feature {

    private static final Difficulty instance = new Difficulty();
    public static HashMap<Integer, String> difficulties;
    private final HashMap<String, Integer> difficultyMap;

    private Difficulty() {
        super("Difficulty", false, false, ChildrenType.NONE);
        difficultyMap = new HashMap<>();
        difficulties = new HashMap<>();
        difficulties.put(1, "Easy");
        difficulties.put(2, "Medium");
        difficulties.put(3, "Hard");
    }

    public static Difficulty getInstance() {
        return instance;
    }

    public int getDifficulty(String courseName) {
        return difficultyMap.getOrDefault(courseName, 1);
    }

    public void setDifficulty(String courseName, int difficulty) {
        if (difficulty < 1 || difficulty > difficulties.size()) {
            throw new IllegalArgumentException("Invalid difficulty level: " + difficulty);
        }
        difficultyMap.put(courseName, difficulty);
    }

    public HashMap<String, Integer> getDifficulties() {
        return difficultyMap;
    }
}
