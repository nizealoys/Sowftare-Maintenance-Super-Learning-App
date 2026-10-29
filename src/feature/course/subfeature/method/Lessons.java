package src.feature.course.subfeature.method;

import src.feature.course.CourseContent;
import src.feature.course.QA;
import java.util.ArrayList;

public class Lessons {

    private final ArrayList<QA> qaList;
    private final ArrayList<String> lessons;
    private int daily;
    private final CourseContent currentcourse;

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
