package src.feature.course.subfeature.type.language;

import java.util.ArrayList;
import java.util.HashMap;
import src.data.CourseData;
import src.feature.course.CourseContent;
import src.feature.course.QA;

public class English extends CourseContent {

    public static final English instance = new English();
    ArrayList<QA> qaList;

    private English() {
        ArrayList<HashMap<String, String>> data = CourseData.getInstance().getCourseData();
        super(data, "English", false, false);
        // testQA();
    }

    public static English getInstance() {
        return instance;
    }

    public void testQA() {
        QA a = new QA(1, "POMME", 1, "Pomme", "J'aime les pommes");
        QA b = new QA(2, "POMME2", 1, "Pomme2", "J'aime les pommes");
        QA c = new QA(1, "FOOTBALL", 2, "Foot", "surtout le football");
        QA d = new QA(3, "BARCA", 2, "VISCA", "surtout le football");
        this.qaList.add(a);
        this.qaList.add(b);
        this.qaList.add(c);
        this.qaList.add(d);
    }

}
