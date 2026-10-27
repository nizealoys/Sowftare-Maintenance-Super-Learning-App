package src.feature.course.subfeature.type.language;

import src.feature.course.CourseContent;
import src.feature.course.QA;
import java.util.ArrayList;

public class French extends CourseContent {
    public French() {
        super("French", false, false);
        testQA();
    }

    public French(ArrayList<QA> qaList) {
        super(qaList, "French", false, false);
    }

    public void testQA(){
        QA a = new QA("Poire", "POIRE", 1,1, "J'aime les poires");
        QA b = new QA("P2", "P2", 1,2, "J'aime les poires");
        this.qaList.add(a);
        this.qaList.add(b);
    }
    
}