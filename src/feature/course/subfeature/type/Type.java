package src.feature.course.subfeature.type;

import src.core.model.Feature;
import core.utils.ChildrenType;

public class Type  extends Feature {
    public Type() {
        super("Type", true, true, ChildrenType.OR);
    }
}
