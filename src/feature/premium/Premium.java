package src.feature.premium;

import src.core.model.Feature;
import src.core.utils.ChildrenType;
import src.feature.course.Course;

import java.util.ArrayList;

public class Premium extends Feature {

    public static final Premium instance = new Premium();

    private Premium() {
        super("Premium", false, false, ChildrenType.NONE);
    }

    public static Premium getInstance() {
        return instance;
    }

    public boolean isPremium() {
        return this.isActive();
    }

    @Override
    public boolean activation() {
        Course course = Course.getInstance();

        System.out.println("premium activation: ");

        course.setOldCourseSlot(course.getCourseSlot());
        course.setCourseSlot(3);
        return super.activation();
    }

    @Override
    public boolean desactivation() {
        Course course = Course.getInstance();

        System.out.println("premium desactivation: ");

        course.setCourseSlot(course.getOldCourseSlot());
        course.setCourseList(new ArrayList<>());
        return super.desactivation();
    }

}
