package src.core.model;

public class Composite {

    private final static Composite instance = new Composite();
    private Feature root;

    private Composite() {
        this.root = null;
    }

    public void setRoot(Feature root) {
        this.root = root;
    }

    public static Composite getInstance() {
        return instance;
    }

    public Feature getFeature(String featureName) {
        return getFeatureRec(featureName, root);
    }

    private Feature getFeatureRec(String featureName, Feature current) {

        if (current == null) {
            return null;
        }

        if (featureName.equals(current.getName())) {
            return current;
        }

        for (Feature c : current.getChildren()) {
            Feature ret = getFeatureRec(featureName, c);

            if (ret != null) {
                return ret;
            }
        }

        return null;
    }

    public Feature getRoot() {
        return root;
    }

    @Override 
    public String toString() {
        StringBuilder model = new StringBuilder();
        model.append(root.toString());
        return model.toString();
    } 
}