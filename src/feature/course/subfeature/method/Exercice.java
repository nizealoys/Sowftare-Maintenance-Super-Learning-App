package src.feature.course.subfeature.method;

import java.util.ArrayList;
import src.feature.course.CourseContent;
import src.feature.course.QA;

public class Exercice {

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
