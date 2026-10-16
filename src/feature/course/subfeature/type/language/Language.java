package src.feature.course.subfeature.type.language;

import src.core.model.Feature;
import core.utils.ChildrenType;

public class Language extends Feature {
    public Language() {
        super("Language", false, false, ChildrenType.OR);
    }
    
}
