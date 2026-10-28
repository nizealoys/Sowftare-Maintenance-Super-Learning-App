package src.feature.course.subfeature.type;

import java.util.ArrayList;
import java.util.HashMap;
import src.data.CourseData;
import src.feature.course.CourseContent;

public class English extends CourseContent {

    public static final English instance = new English();

    private English() {
        ArrayList<HashMap<String, String>> data = CourseData.getInstance().getCourseData();
        super(data, "English", false, false);
    }

    public static English getInstance() {
        return instance;
    }

}
