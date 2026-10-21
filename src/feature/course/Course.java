package src.feature.course;

import java.util.ArrayList;
import src.core.model.Feature;
import src.core.utils.ChildrenType;

public class Course extends Feature {

    private ArrayList<Feature> courseList;
    private int availableCourseSlot;

    public Course() {
        super("Course", true, true, ChildrenType.NONE);
        availableCourseSlot = 1;
        courseList = new ArrayList<>();
    }

    public int GetavailableCourseSlot() {
        return availableCourseSlot;
    }

    public void increaseCourseSlot() {
        if (this.availableCourseSlot < 3) {
            this.availableCourseSlot++;
        }
    }

    public void setCourseSlot(Feature course) {
        this.courseList = courseList;
    }
}
