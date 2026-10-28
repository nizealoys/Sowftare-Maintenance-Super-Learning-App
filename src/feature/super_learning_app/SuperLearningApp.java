package src.feature.super_learning_app;

import src.core.model.Feature;
import src.core.utils.ChildrenType;

public class SuperLearningApp extends Feature {

    public static final SuperLearningApp instance = new SuperLearningApp();

    private SuperLearningApp() {
        super("SuperLearningApp", true, true, ChildrenType.NONE);
    }

    public static SuperLearningApp getInstance() {
        return instance;
    }
}
