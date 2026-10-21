package src.feature.course.subfeature.type;

import src.core.model.Feature;
import src.core.utils.ChildrenType;

public class Type  extends Feature {
    public Type() {
        super("Type", true, true, ChildrenType.OR);
    }
}
