package src.feature.achievement.model;

public class AchievementElement {
    final private String name;
    final private String description;
    private boolean achieved;

    public AchievementElement(String name, String description, boolean achieved) {
        this.name = name;
        this.description = description;
        this.achieved = achieved;
    }

    public AchievementElement(AchievementElement achievementElement) {
        this.name = achievementElement.name;
        this.description = achievementElement.description;
        this.achieved = achievementElement.achieved;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    public void setAchieved(boolean achieved) {
        this.achieved = achieved;
    }

    public boolean isAchieved() {
        return achieved;
    }

    @Override
    public String toString() {
        return "AchievementElement{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", achieved=" + achieved +
                '}';
    }
}