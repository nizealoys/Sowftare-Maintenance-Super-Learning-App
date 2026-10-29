package src.core.model;

import java.util.ArrayList;
import src.core.log.Logs;
import src.feature.course.Course;
import src.feature.course.CourseContent;
import src.feature.premium.Premium;

public class Facade {

    private final static Facade facade = new Facade();
    private final Logs logs = Logs.getInstance();
    //private final Composite composite = Composite.getInstance();
    private final Composite composite = CompositeBuilder.getInstance().getComposite();

    public static Facade getInstance() {
        return facade;
    }

    public Composite getComposite() {
        return this.composite;
    }

    // 0 good
    // -1 all failed
    // 1 some failed
    // 2 some deactivation failed
    // 3 some activation failed
    public int activate(String[] deactivations, String[] activations) {
        ArrayList<String> desativationFailedActivations;
        ArrayList<String> activationFailedActivations;

        logs.addLog("Activating: " + String.join(", ", activations) + " and deactivating: " + String.join(", ", deactivations));

        desativationFailedActivations = desactivation(deactivations);
        activationFailedActivations = activation(activations);

        if (desativationFailedActivations.isEmpty() && activationFailedActivations.isEmpty()) {
            return 0;
        } else if (desativationFailedActivations.size() == deactivations.length && activationFailedActivations.size() == activations.length) {
            return -1;
        } else if (!desativationFailedActivations.isEmpty() && !activationFailedActivations.isEmpty()) {
            return 1;
        } else if (!desativationFailedActivations.isEmpty()) {
            return 2;
        } else {
            return 3;
        }
    }

    private ArrayList<String> desactivation(String[] deactivations) {
        boolean activationSuccess;
        Feature feature;
        ArrayList<String> desativationFailedActivations = new ArrayList<>();

        for (String featureName : deactivations) {
            feature = composite.getFeature(featureName);

            if (feature != null) {

                if (feature instanceof CourseContent courseContent) {
                    Course course = Course.getInstance();
                    activationSuccess = feature.desactivation();
                    if (activationSuccess) {
                        course.removeCourse(courseContent);
                    }

                } else if (feature instanceof Premium) {
                    Course course = Course.getInstance();
                    activationSuccess = feature.desactivation();
                    for (Feature courseContent : course.getCourseList()) {
                        courseContent.desactivation();
                    }

                } else {
                    activationSuccess = feature.desactivation();
                }

                logs.addLog("Deactivating: " + featureName + " - Success: " + activationSuccess);

                if (!activationSuccess) {
                    desativationFailedActivations.add(featureName);
                }
            }
        }

        return desativationFailedActivations;
    }

    private ArrayList<String> activation(String[] activations) {
        boolean activationSuccess;
        Feature feature;
        ArrayList<String> activationFailedActivations = new ArrayList<>();

        for (String featureName : activations) {
            feature = composite.getFeature(featureName);

            if (feature != null) {
                if (feature instanceof CourseContent courseContent) {
                    Course course = Course.getInstance();

                    if (course.getCourseSlot() == course.getCourseList().size()) {
                        activationSuccess = false;
                    } else {
                        activationSuccess = feature.activation();
                        System.out.println(activationSuccess);
                    }
                    if (activationSuccess) {
                        course.addCourse(courseContent);
                    }

                } else {
                    activationSuccess = feature.activation();
                }

                logs.addLog("Activating: " + featureName + " - Success: " + activationSuccess);

                if (!activationSuccess) {
                    activationFailedActivations.add(featureName);
                }
            }
        }

        return activationFailedActivations;
    }

    @Override
    public String toString() {
        return this.composite.getFeature("SuperLearningApp").toString();
    }
}
