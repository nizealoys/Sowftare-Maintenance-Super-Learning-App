package src.core.model;

import com.sun.jdi.Method;
import src.feature.achievement.Achievement;
import src.feature.course.Course;
import src.feature.course.subfeature.method.Methode;
import src.feature.course.subfeature.type.Type;
import src.feature.course.subfeature.type.language.English;
import src.feature.course.subfeature.type.language.French;
import src.feature.super_learning_app.SuperLearningApp;

public class CompositeBuilder {
    private static final CompositeBuilder instance = new CompositeBuilder();
    private final Composite composite;

    private CompositeBuilder(){
        Feature feature;
        this.composite = Composite.getInstance();
        this.composite.setRoot(new SuperLearningApp());
        feature = composite.getFeature("SuperLearningApp");
        initialisation(feature);
    }

    private void initialisation(Feature feature) {
        feature.add(new Achievement());
        Feature course = new Course();
        Feature english = new English();
        Feature french = new French();
        Feature methode = new Methode();
        course.add(english);
        course.add(french);
        course.add(methode);
        feature.add(course);
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