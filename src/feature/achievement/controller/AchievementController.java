package feature.achievement.controller;

import core.controller.Controller;
import core.model.Facade;
import feature.achievement.model.Achievement;

public class AchievementController extends Controller {
    private final Achievement achievement;
    
    public AchievementController(Facade facade, Achievement achievement) {
        super(facade);
        this.achievement = achievement;
    }

    public Achievement getAchievement() {
        return achievement;
    }

    public boolean setAchievementElementAchieved(String achievementName, boolean achieved) {
        return achievement.setAchievementElementAchieved(achievementName, achieved);
    }

    @Override
    public boolean enableUIView() {
        return true;
    }

    @Override
    public boolean disableUIView() {
        return true;
    }
}