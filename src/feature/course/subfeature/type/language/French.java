package feature.course.subfeature.type.language;

import feature.course.CourseContent;
import feature.course.QA;
import java.util.ArrayList;

public class French extends CourseContent {
    public French() {
        super("French", false, false);
    }

    public French(ArrayList<QA> qaList) {
        super(qaList, "French", false, false);
    }
    
}