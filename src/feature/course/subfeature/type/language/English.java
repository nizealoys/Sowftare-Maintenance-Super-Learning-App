package src.feature.course.subfeature.type.language;

import src.feature.course.CourseContent;
import src.feature.course.QA;
import java.util.ArrayList;

public class English extends CourseContent {
    ArrayList<QA> qaList;

    public English() {
        super("English", false, false);
        this.qaList = new ArrayList<>();
        testQA();
    }

    public English(ArrayList<QA> qaList) {
        super(qaList, "English", false, false);
    }

    public void testQA(){
        QA a = new QA("Pomme", "POMME", 1,1, "J'aime les pommes");
        QA b = new QA("Pomme2", "POMME2", 1,2, "J'aime les pommes");
        QA c = new QA("Foot", "FOOTBALL", 2,1, "surtout le football");
        QA d = new QA("VISCA", "BARCA", 2,3, "surtout le football");
        this.qaList.add(a);
        this.qaList.add(b);
        this.qaList.add(c);
        this.qaList.add(d);
    }
    
}
