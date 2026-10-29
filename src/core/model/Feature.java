package src.core.model;

import java.util.ArrayList;
import src.core.log.Logs;
import src.core.utils.ChildrenType;

public class Feature {

    private final String name;
    private final boolean mandatory;
    private boolean activate;
    private Feature parent;
    private final ArrayList<Feature> children;
    private final ChildrenType childrenType;

    public Feature(String name, boolean mandatory, boolean activate, ChildrenType childrenType) {
        this.name = name;
        this.mandatory = mandatory;
        this.activate = activate;
        this.children = new ArrayList<>();
        this.parent = null;
        this.childrenType = childrenType;
    }

    /*
    public Feature(String name, boolean mandatory) {
        this.name = name;
        this.mandatory = mandatory;
        //this.activate = activate;
        this.children = new ArrayList<>();
        this.parent = null;
        this.childrenType = null;
    }

     */

    public String getName() {
        return this.name;
    }


    public boolean isMandatory() {
        return this.mandatory;
    }


    public boolean isActive() {
        return this.activate;
    }


    public Feature getParent() {
        return this.parent;
    }


    public ArrayList<Feature> getChildren() {
        return this.children;
    }


    public void setParent(Feature Parent) {
        this.parent = Parent;
    }


    public String getChildrenType() {
        return childrenType.name();
    }

    public Feature getFeature(String name) {
        if (name.equals(this.getName())) {
            return this;
        }
        return null;
    }


    public boolean desactivation() {

        if (!activate) {
            System.out.println("Déjà désactivé");
            return false;
        }

        if (mandatory && parent != null && parent.isActive()) {
            System.out.println(
                    name + " ne peut pas être désactivé"
            );
            return false;
        }

        desactivationRecursive();

        return true;
    }

    private void desactivationRecursive() {

        for (Feature c : children) {
            c.desactivationRecursive();
        }

        activate = false;
    }


    public boolean activation() {

        if (activate) {
            System.out.println("Déjà activé");
            return false;
        }

        if (parent != null && !parent.isActive()) {
            System.out.println(
                    name + " : activez d'abord " + parent.getName()
            );
            return false;
        }

        if (parent != null && parent.childrenType.equals(ChildrenType.XOR)) {

            for (Feature c : parent.children) {
                if (c != this && c.isActive()) {
                    System.out.println(
                            c.getName() + " est déjà activé"
                    );
                    return false;
                }
            }
        }

        activate = true;

        for (Feature c : children) {
            if (c.isMandatory()) {
                Logs.getInstance().addLog("Activating mandatory child: " + c.getName());
                c.activation();
            }
        }

        return true;
    }


    public void add(Feature child) {

        if (child == null) {
            throw new IllegalArgumentException("Child ne peut pas être null");
        }

        if (child.getParent() != null) {
            throw new IllegalArgumentException("Feature a déjà un parent");
        }

        Feature current = this;

        while (current != null) {
            if (current == child) {
                throw new IllegalArgumentException("Déjà parent");
            }

            current = current.getParent();
        }

        this.children.add(child);
        child.setParent(this);

        child.desactivationRecursive();

        if (activate && child.isMandatory()) {
            child.activation();
        }
    }


    public String toString() {
        StringBuilder model = new StringBuilder();
        if (this.isActive()) {
            model.append(this.getName()).append(" [");
            for (Feature c : children) {
                if (!c.getChildren().isEmpty()) {
                    model.append(" ").append(c.toString());
                } else if (c.isActive()) {
                    model.append(" ").append(c.getName());
                }
            }
        }
        model.append(" ]");
        return model.toString();
    }
}
