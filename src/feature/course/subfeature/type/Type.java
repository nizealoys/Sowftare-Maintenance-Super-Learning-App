package src.feature.course.subfeature.type;

import src.core.model.Feature;
import src.core.utils.ChildrenType;

public class Type extends Feature {

    public static final Type instance = new Type();

    private Type() {
        super("Type", true, true, ChildrenType.OR);
    }

    public static Type getInstance() {
        return instance;
    }
}
