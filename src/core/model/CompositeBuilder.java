package src.core.model;

import src.feature.achievement.Achievement;
import src.feature.super_learning_app.SuperLearningApp;

public class CompositeBuilder {
    private static final CompositeBuilder instance = new CompositeBuilder();
    private final Composite composite;

    private CompositeBuilder() {
        Feature feature;
        this.composite = Composite.getInstance();
        this.composite.setRoot(new SuperLearningApp());
        feature = composite.getFeature("SuperLearningApp");
        feature.add(new Achievement());
    }

    public static CompositeBuilder getInstance() {
        return instance;
    }

    public Composite getComposite() {
        return composite;
    }

    public Feature getFeature(String featureName) {
        return composite.getFeature(featureName);
    }

    @Override
    public String toString() {
        return composite.toString();
    }
}