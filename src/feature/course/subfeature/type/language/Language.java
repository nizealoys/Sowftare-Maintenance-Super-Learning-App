package feature.course.subfeature.type.language;

import core.model.Feature;
import core.utils.ChildrenType;

public class Language extends Feature {
    public Language() {
        super("Language", false, false, ChildrenType.OR);
    }
    
}
