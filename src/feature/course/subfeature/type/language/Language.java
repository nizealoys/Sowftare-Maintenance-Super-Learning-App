package src.feature.course.subfeature.type.language;

import src.core.model.Feature;
import src.core.utils.ChildrenType;

public class Language extends Feature {

    public static final Language instance = new Language();

    private Language() {
        super("Language", false, false, ChildrenType.OR);
    }

    public static Language getInstance() {
        return instance;
    }

}
