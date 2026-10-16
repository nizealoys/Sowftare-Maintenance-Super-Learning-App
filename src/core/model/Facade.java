package src.core.model;

import src.core.log.Logs;
import java.util.ArrayList;

public abstract class Facade {
    private final Composite composite;
    private final Logs logs = Logs.getInstance();

    public Facade(Composite composite) {
        this.composite = composite;
    }
    

    // 0 good
    // -1 all failed
    // 1 some failed
    // 2 some deactivation failed
    // 3 some activation failed
    public int activate(String[] deactivations, String[] activations) {
        ArrayList<String> desativationFailedActivations;
        ArrayList<String> activationFailedActivations;

        desativationFailedActivations = desactivation(deactivations);
        activationFailedActivations = activation(activations);

        if (desativationFailedActivations.isEmpty() && activationFailedActivations.isEmpty()) {
            return 0;
        } else if (desativationFailedActivations.size() == deactivations.length && activationFailedActivations.size() == activations.length) {
            return -1;
        } else if (!desativationFailedActivations.isEmpty() && !activationFailedActivations.isEmpty()) {
            return 1;
        } else if (!desativationFailedActivations.isEmpty() ) {
            return 2;
        } else {
            return 3;
        }
    }

    private ArrayList<String> desactivation(String[] deactivations) {
        boolean activationSuccess;
        Feature feature;
        ArrayList<String> desativationFailedActivations = new ArrayList<>();

        for (String featureName : deactivations) {
            feature = composite.getFeature(featureName);
            
            if (feature != null) {
                activationSuccess = feature.desactivation();

                logs.addLog("Deactivating: " + featureName + " - Success: " + activationSuccess);

                if (!activationSuccess) {
                    desativationFailedActivations.add(featureName);
                }
            }
        }

        return desativationFailedActivations;
    }

    private ArrayList<String> activation(String[] activations) {
        boolean activationSuccess;
        Feature feature;
        ArrayList<String> activationFailedActivations = new ArrayList<>();

        for (String featureName : activations) {
            feature = composite.getFeature(featureName);

            if (feature != null) {
                activationSuccess = feature.activation();

                logs.addLog("Activating: " + featureName + " - Success: " + activationSuccess);
                
                if (!activationSuccess) {
                    activationFailedActivations.add(featureName);
                }
            }
        }

        return activationFailedActivations;
    }
}