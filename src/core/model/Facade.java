package src.core.model;

import java.util.ArrayList;
import src.core.log.Logs;
import src.feature.achievement.Achievement;
import src.feature.achievement.AchievementElement;
import src.feature.course.Course;
import src.feature.course.CourseContent;
import src.feature.course.QA;
import src.feature.difficulty.Difficulty;
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
            // All activations and deactivations were successful
            return 0;
        } else if (desativationFailedActivations.size() == deactivations.length && activationFailedActivations.size() == activations.length) {
            // All activations and deactivations failed
            return -1;
        } else if (!desativationFailedActivations.isEmpty() && !activationFailedActivations.isEmpty()) {
            // Some activations and deactivations failed
            return 1;
        } else if (!desativationFailedActivations.isEmpty()) {
            // Some deactivations failed
            return 2;
        } else {
            // Some activations failed
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

                if (activationSuccess && feature instanceof Premium) {
                    Achievement achievement = Achievement.getInstance();
                    AchievementElement elem = achievement.getAchievementElement("Premium User");

                    if (elem != null && !elem.isAchieved()) {
                        elem.setAchieved(true);
                        logs.addLog("Achievement unlocked: " + elem.getName());
                    }
                }
            }
        }

        return activationFailedActivations;
    }

    @Override
    public String toString() {
        return this.composite.getFeature("SuperLearningApp").toString();
    }

    public boolean checkQA(QA qa, String answer) {
        if (qa.getAnswer().equals(answer)) {
            completeQA(qa);
            return true;
        } else {
            logs.addLog("QA failed: " + qa.getQuestion() + " - Provided answer: " + answer);
            return false;
        }
    }

    private void completeQA(QA qa) {
        Achievement achievement = Achievement.getInstance();
        AchievementElement elem = achievement.getAchievementElement("First Steps");
        ArrayList<CourseContent> courseContents = new ArrayList<>();

        Iterator<Feature> iterator = composite.createIterator();

        while (iterator.hasNext()) {
            Feature feature = iterator.next();
            if (feature instanceof CourseContent courseContent) {
                courseContents.add(courseContent);
            }
        }

        qa.setIsCompleted(true);
        logs.addLog("QA completed: " + qa.getQuestion());
        if (elem != null && !elem.isAchieved()) {
            elem.setAchieved(true);
            logs.addLog("Achievement unlocked: " + elem.getName());
        }
        if (allCourseCompleted(courseContents)) {
            AchievementElement elem2 = achievement.getAchievementElement("Quiz Master");
            if (elem2 != null && !elem2.isAchieved()) {
                elem2.setAchieved(true);
                logs.addLog("Achievement unlocked: " + elem2.getName());
            }
        }
    }

    private boolean allCourseCompleted(ArrayList<CourseContent> courseContents) {
        for (CourseContent courseContent : courseContents) {
            if (!courseContent.isAllCompleted()) {
                return false;
            }
        }
        return true;
    }

    public boolean setDifficulty(String courseName, int difficult) {
        Difficulty difficulty = Difficulty.getInstance();

        if (composite.getFeature(courseName) == null) {
            logs.addLog("Course not found: " + courseName);
            return false;
        }

        if (difficult < 1 || difficult > 3) {
            logs.addLog("Invalid difficulty: " + difficult + " for course: " + courseName);
            return false;
        }

        difficulty.setDifficulty(courseName, difficult);
        logs.addLog("Difficulty set to: " + difficult + " for course: " + courseName);
        return true;
    }
}
