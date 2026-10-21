package src.feature.course;

import src.core.model.Feature;
import src.core.utils.ChildrenType;

public class Course extends Feature{
    
    private int courseSlot;

    public Course() {
        super("Course", true, true, ChildrenType.NONE);
        courseSlot = 1;
    }

    public int getCourseSlot() {
        return courseSlot;
    }

    public void increaseCourseSlot() {
        if (this.courseSlot < 3) {
            this.courseSlot++;
        }
    }

    public void setCourseSlot(int courseSlot) {
        this.courseSlot = courseSlot;
    }
}
