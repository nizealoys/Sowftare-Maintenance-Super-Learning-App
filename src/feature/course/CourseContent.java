package src.feature.course;

import src.core.model.Feature;
import src.core.utils.ChildrenType;
import java.util.ArrayList;

public abstract class CourseContent extends Feature {
    private final ArrayList<QA> qaList;

    public CourseContent(String name, boolean isMandatory, boolean isSelectable) {
        super(name, isMandatory, isSelectable, ChildrenType.NONE);
        this.qaList = new ArrayList<>();
    }

    public CourseContent(ArrayList<QA> qaList, String name, boolean isMandatory, boolean isSelectable) {
        super(name, isMandatory, isSelectable, ChildrenType.NONE);
        this.qaList = qaList;
    }

    public ArrayList<QA> getQAList() {
        return qaList;
    }
}