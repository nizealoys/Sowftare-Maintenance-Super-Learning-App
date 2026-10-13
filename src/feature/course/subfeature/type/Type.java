package feature.course.subfeature.type;

import core.model.Feature;
import core.utils.ChildrenType;

public class Type  extends Feature {
    public Type() {
        super("Type", true, true, ChildrenType.OR);
    }
}
