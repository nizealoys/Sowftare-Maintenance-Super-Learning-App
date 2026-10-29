package src.core.model;

import src.feature.achievement.Achievement;
import src.feature.course.Course;
import src.feature.course.subfeature.method.Methode;
import src.feature.course.subfeature.type.Cooking;
import src.feature.course.subfeature.type.English;
import src.feature.course.subfeature.type.French;
import src.feature.course.subfeature.type.Type;
import src.feature.difficulty.Difficulty;
import src.feature.premium.Premium;
import src.feature.super_learning_app.SuperLearningApp;

public class CompositeBuilder {

    private static final CompositeBuilder instance = new CompositeBuilder();
    private final Composite composite;

    private CompositeBuilder() {
        Feature feature = SuperLearningApp.getInstance();
        this.composite = Composite.getInstance();
        this.composite.setRoot(feature);
        feature.add(Achievement.getInstance());
        feature.add(Course.getInstance());
        feature.add(Premium.getInstance());
        feature.add(Difficulty.getInstance());

        // feature = course
        feature = Course.getInstance();
        feature.add(Type.getInstance());
        feature.add(Methode.getInstance());

        // feature = type
        feature = Type.getInstance();
        feature.add(English.getInstance());
        feature.add(French.getInstance());
        feature.add(Cooking.getInstance());
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
