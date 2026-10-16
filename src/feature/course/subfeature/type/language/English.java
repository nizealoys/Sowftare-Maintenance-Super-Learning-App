package src.feature.course.subfeature.type.language;

import src.feature.course.CourseContent;
import src.feature.course.QA;
import java.util.ArrayList;

public class English extends CourseContent {
    public English() {
        super("English", false, false);
    }

    public English(ArrayList<QA> qaList) {
        super(qaList, "English", false, false);
    }
    
}
