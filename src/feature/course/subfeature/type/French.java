package src.feature.course.subfeature.type;

import java.util.ArrayList;
import java.util.HashMap;
import src.data.CourseData;
import src.feature.course.CourseContent;

public class French extends CourseContent {

    private static final French instance = new French();

    private French() {
        ArrayList<HashMap<String, String>> data = CourseData.getInstance().getCourseData();
        super(data, "French", false, false);
    }

    public static French getInstance() {
        return instance;
    }

}
