package src.feature.course.subfeature.type.language;

import src.feature.course.CourseContent;
import src.feature.course.QA;
import java.util.ArrayList;

public class French extends CourseContent {
    public French() {
        super("French", false, false);
    }

    public French(ArrayList<QA> qaList) {
        super(qaList, "French", false, false);
    }
    
}