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
