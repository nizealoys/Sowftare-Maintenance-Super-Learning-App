package src.feature.course;

import java.util.ArrayList;
import src.core.model.Feature;
import src.core.utils.ChildrenType;
import src.feature.difficulty.Difficulty;

public class Course extends Feature {

    private static final Course instance = new Course();
    private ArrayList<Feature> courseList;
    private int CourseSlot;
    private int oldCourseSlot;

    private Course() {
        super("Course", true, true, ChildrenType.NONE);
        CourseSlot = 1;
        oldCourseSlot = 1;
        courseList = new ArrayList<>();
    }

    public static Course getInstance() {
        return instance;
    }

    public int getCourseSlot() {
        return CourseSlot;
    }

    public void increaseCourseSlot() {
        if (this.CourseSlot < 3) {
            this.CourseSlot++;
        }
    }

    public void setCourseSlot(int newSlot) {
        CourseSlot = newSlot;
    }

    public void setCourseList(ArrayList<Feature> course) {
        this.courseList = course;
    }

    public void addCourse(Feature course) {
        this.courseList.add(course);
        Difficulty.getInstance().setDifficulty(course.getName(), 0);
    }

    public void removeCourse(Feature course) {
        this.courseList.remove(course);
    }

    public ArrayList<Feature> getCourseList() {
        return courseList;
    }

    public void setOldCourseSlot(int oldSlot) {
        this.oldCourseSlot = oldSlot;
    }

    public int getOldCourseSlot() {
        return oldCourseSlot;
    }
}
