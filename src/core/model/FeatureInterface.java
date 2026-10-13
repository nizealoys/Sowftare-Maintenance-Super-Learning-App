package core.model;

import java.util.ArrayList;

public interface FeatureInterface {
    String getName();
    boolean isMandatory();
    boolean isActive();
    Feature getParent();
    ArrayList<Feature> getChildren();

    boolean desactivation();
    boolean activation();
    void add(Feature c);

    public String getChildrenType();
    void setParent(Feature c);

    @Override
    String toString();
}