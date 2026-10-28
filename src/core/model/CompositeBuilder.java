package src.core.model;

import src.feature.achievement.Achievement;
import src.feature.course.Course;
import src.feature.course.subfeature.method.Methode;
import src.feature.course.subfeature.type.Type;
import src.feature.course.subfeature.type.language.English;
import src.feature.course.subfeature.type.language.French;
import src.feature.course.subfeature.type.language.Language;
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

        // feature = course
        feature = Course.getInstance();
        feature.add(Type.getInstance());
        feature.add(Methode.getInstance());

        // feature = type
        feature = Type.getInstance();
        feature.add(Language.getInstance());

        // feature = language
        feature = Language.getInstance();
        feature.add(English.getInstance());
        feature.add(French.getInstance());
    }

    // private void initialisation(Feature feature) {
    //     feature.add(new Achievement());
    //     Feature course = new Course();
    //     Feature english = new English();
    //     //Feature french = new French();
    //     Feature methode = new Methode();
    //     course.add(english);
    //     course.add(methode);
    //     feature.add(course);
    // }
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
