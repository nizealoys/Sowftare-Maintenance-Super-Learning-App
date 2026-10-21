package src.feature.achievement;

import java.util.ArrayList;
import src.core.model.Feature;
import src.core.utils.ChildrenType;

public class Achievement extends Feature {
    public ArrayList<AchievementElement> achievementElements;

    public Achievement() {
        super("Achievement", true, true, ChildrenType.NONE);
        this.achievementElements = new ArrayList<>();
    }

    public Achievement(String name, boolean mandatory, boolean activate, ChildrenType childrenType, ArrayList<AchievementElement> achievementElements) {
        super(name, mandatory, activate, childrenType);
        this.achievementElements = achievementElements;
    }

    public ArrayList<AchievementElement> getAchievementElements() {
        return achievementElements;
    }

    public boolean addAchievementElement(String name, String description) {
        for (AchievementElement element : this.achievementElements) {
            if (element.getName().equals(name)) {
                return false;
            }
        }
        this.achievementElements.add(new AchievementElement(name, description, false));
        return true;
    }

    public boolean addAchievementElement(AchievementElement achievementElement) {
        for (AchievementElement element : this.achievementElements) {
            if (element.getName().equals(achievementElement.getName())) {
                return false;
            }
        }
        this.achievementElements.add(achievementElement);
        return true;
    }

    public boolean removeAchievementElement(String name) {
        return this.achievementElements.removeIf(element -> element.getName().equals(name));
    }

    public AchievementElement getAchievementElement(String name) {
        for (AchievementElement element : this.achievementElements) {
            if (element.getName().equals(name)) {
                return element;
            }
        }
        return null;
    }

    public boolean setAchievementElementAchieved(String name, boolean achieved) {
        for (AchievementElement element : this.achievementElements) {
            if (element.getName().equals(name)) {
                element.setAchieved(achieved);
                return true;
            }
        }
        return false;
    }

    public void setAllAchievementElementsAchieved(boolean achieved) {
        for (AchievementElement element : this.achievementElements) {
            element.setAchieved(achieved);
        }
    }

    @Override
    public String toString() {
        return "Achievement{" +
                "name='" + getName() + '\'' +
                ", mandatory=" + isMandatory() +
                ", activate=" + isActive() +
                ", childrenType='" + getChildrenType() + '\'' +
                ", achievementElements=" + achievementElements +
                '}';
    }
}