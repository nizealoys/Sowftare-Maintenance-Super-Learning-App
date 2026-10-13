package feature.course.subfeature.type.language;

import feature.course.CourseContent;
import feature.course.QA;
import java.util.ArrayList;

public class English extends CourseContent {
    public English() {
        super("English", false, false);
    }

    public English(ArrayList<QA> qaList) {
        super(qaList, "English", false, false);
    }
    
}
