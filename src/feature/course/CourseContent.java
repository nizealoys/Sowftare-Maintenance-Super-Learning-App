package src.feature.course;

import java.util.ArrayList;
import java.util.HashMap;
import src.core.model.Feature;
import src.core.utils.ChildrenType;

public abstract class CourseContent extends Feature {

    private final ArrayList<QA> qaList;

    public CourseContent(String name, boolean isMandatory, boolean isActivate) {
        super(name, isMandatory, isActivate, ChildrenType.NONE);
        this.qaList = new ArrayList<>();
    }

    public CourseContent(ArrayList<HashMap<String, String>> dataList, String name, boolean isMandatory, boolean isActivate) {
        super(name, isMandatory, isActivate, ChildrenType.NONE);
        this.qaList = new ArrayList<>();
        for (HashMap<String, String> data : dataList) {
            if (data.get("course").equals(name)) {
                QA qa = new QA(data);
                this.qaList.add(qa);
            }
        }
    }

    @Override
    public boolean activation() {
        return super.activation();
    }

    public ArrayList<QA> getQAList() {
        return qaList;
    }
}
