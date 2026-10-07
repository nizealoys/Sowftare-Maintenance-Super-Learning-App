import java.util.ArrayList;

public class Composite {
    //private ArrayList<Component> children;
    private Component root;


    public Composite(Component root) {
        this.root = root;
    }

    public Feature getFeature(String featureName){
        return getFeatureRec(featureName, root);
    }

    private Feature getFeatureRec (String featureName, Component current) {
        Feature ret;
        for (Component c : root.getChildren()) {
            if (featureName.equals(c.getName())) {
                ret = (Feature) c;
                return ret;
            }
            ret = getFeatureRec(featureName, c);
            if (ret != null) return ret;
        }
        //System.out.println("Pas de feature correspondante");
        return null;
    }
}

/**
class composit
    root

            qdd les feqture
            get9string0 -> feqture


co,posite :get9nq,e0:qctivqte - fqlse
 **/
