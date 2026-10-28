package src.feature.course.subfeature.type.cooking;

import java.util.ArrayList;
import java.util.HashMap;
import src.data.CourseData;
import src.feature.course.CourseContent;

public class Cooking extends CourseContent {

    public static final Cooking instance = new Cooking();

    private Cooking() {
        ArrayList<HashMap<String, String>> data = CourseData.getInstance().getCourseData();
        super(data, "Cooking", false, false);
    }

    public static Cooking getInstance() {
        return instance;
    }

}
