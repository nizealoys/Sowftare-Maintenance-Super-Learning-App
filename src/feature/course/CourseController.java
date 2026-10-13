package feature.course;

import core.controller.Controller;

public class CourseController extends Controller {
    private final Course course;

    public CourseController(CourseFacade facade, Course course) {
        super(facade);
        this.course = course;
    }

    public Course getCourse() {
        return course;
    }

    @Override
    public boolean enableUIView() {
        return true;
    }

    @Override
    public boolean disableUIView() {
        return true;
    }
}
