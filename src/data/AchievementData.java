package src.data;

import java.util.ArrayList;
import java.util.HashMap;

public class AchievementData {

    private final static AchievementData instance = new AchievementData();
    private final ArrayList<HashMap<String, String>> achievementData;
    private final String[] keys = {"name", "description", "achieved"};

    private AchievementData() {
        achievementData = new ArrayList<>();
        achivementsData();
    }

    public static AchievementData getInstance() {
        return instance;
    }

    private void achivementsData() {
        ArrayList<String> values = new ArrayList<>();
        values.add("First Steps");
        values.add("Complete your first course.");
        values.add("false");

        this.addAchievementData(values);

        values = new ArrayList<>();
        values.add("Quiz Master");
        values.add("Clear all excercises");
        values.add("false");

        this.addAchievementData(values);

        values = new ArrayList<>();
        values.add("Premium User");
        values.add("Upgrade to premium.");
        values.add("false");

        this.addAchievementData(values);
    }

    private void addAchievementData(ArrayList<String> values) {
        if (values.size() != keys.length) {
            throw new IllegalArgumentException("Values size does not match keys size.");
        }

        HashMap<String, String> data = new HashMap<>();
        for (int i = 0; i < keys.length; i++) {
            data.put(keys[i], values.get(i));
        }
        achievementData.add(data);
    }

    public void addAchievement(HashMap<String, String> achievement) {
        if (achievement.size() != keys.length) {
            throw new IllegalArgumentException("Achievement size does not match keys size.");
        }
        achievementData.add(achievement);
    }

    public ArrayList<HashMap<String, String>> getAchievementData() {
        return achievementData;
    }

    public ArrayList<String> getAchievementDataByKey(String key) {
        ArrayList<String> values = new ArrayList<>();

        for (HashMap<String, String> data : achievementData) {
            if (data.containsKey(key)) {
                values.add(data.get(key));
            }
        }
        return values;
    }

    public int getAchievementDataSize() {
        return achievementData.size();
    }
}
