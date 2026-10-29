package src.feature.course.subfeature.method;

import java.util.ArrayList;
import src.core.model.Feature;
import src.core.utils.ChildrenType;
import src.feature.course.CourseContent;
import src.feature.course.QA;

public class Methode extends Feature { //modifer le nom

    private static final Methode instance = new Methode();

    public Lessons lessons;
    public Exercice exercices;

    private Methode() {
        super("Method", true, true, ChildrenType.OR);
    }

    public static Methode getInstance() {
        return instance;
    }

    public Feature getCourse(String name) {
        return getFeature(name);
    }

    public void setCourseData(CourseContent course) {
        this.lessons = new Lessons(course);
        this.exercices = new Exercice(this.lessons);
    }

    public ArrayList<QA> getQAList() {
        return this.lessons.getQAList();
    }

}

class Lessons {

    private ArrayList<QA> qaList;
    private ArrayList<String> lessons;
    private int daily;
    private CourseContent currentcourse;

    public Lessons(CourseContent currentcourse) {
        this.qaList = currentcourse.getQAList();
        this.lessons = new ArrayList<>();
        for (int i = 0; i < currentcourse.getQAList().size(); i++) {
            this.lessons.add(currentcourse.getQAList().get(i).getExplanation());
        }
        this.daily = 0;
        this.currentcourse = currentcourse;
    }

    public ArrayList<String> getLessons() {
        return lessons;
    }

    public CourseContent getCurrentcourse() {
        return currentcourse;
    }

    public void nextDaily() {
        this.daily++;
    }

    public int getDaily() {
        return this.daily;
    }

    public String getDailyContent() {
        return lessons.get(daily);
    }

    public ArrayList<QA> getQAList() {
        return qaList;
    }
}

class Exercice {

    final private Lessons lessons;

    public Exercice(Lessons lessons) {
        this.lessons = lessons;
    }

    public ArrayList<QA> getDailyQuestions() {
        ArrayList<QA> questions = new ArrayList<>();
        for (QA question : this.lessons.getQAList()) {
            if (question.getTopic() == this.getDaily()) {
                questions.add(question);
            }
        }
        return questions;
    }

    public CourseContent getCurrentcourse() {
        return lessons.getCurrentcourse();
    }

    public int getDaily() {
        return lessons.getDaily();
    }

    public ArrayList<QA> getQAList() {
        return lessons.getQAList();
    }
}
