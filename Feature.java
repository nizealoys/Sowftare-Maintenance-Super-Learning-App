import java.util.ArrayList;
import java.util.List;


public class Feature implements Component {
    private final String name;
    private final boolean mandatory;
    private boolean activate;
    private Feature parent;
    private ArrayList<Component> children;
    private final String childrenType;

    public Feature(String name, boolean mandatory, String childrenType) {
        this.name = name;
        this.mandatory = mandatory;
        if (mandatory){
            this.activate = true;
        }
        else activate = false;
        this.children = new ArrayList<Component>();
        this.parent = null;
        this.childrenType = childrenType;
    }

    public String getName() {return this.name;}
    public boolean isMandatory() {return this.mandatory;}
    public boolean isActive() {return this.activate;}
    public Feature getParent() {return this.parent;}
    public List<Component> getChildren() {return this.children;}

    @Override
    public void setParent(Component Parent) {this.parent = (Feature) Parent;}


    public String getChildrenType() {return childrenType;}

    @Override
    public void desactivation(){
        if (this.isMandatory()){
            System.out.println("ne peut pas être désactivé");
            return;
        }else if (this.activate) {
            System.out.println("Déjà désactivé");
            return;
        }
        if (mandatory && parent != null && parent.isActive()) {
            System.out.println(name + " est obligatoire, désactivez " + parent.getName() + " d'abord");
            return;
        }
        for (Component c : children) c.desactivation();
        this.activate = false;
    }

    @Override
    public void activation(){
        if (this.activate) {
            System.out.println("Déjà activé");
            return;
        }
        if (parent != null && !parent.isActive()) {
            System.out.println(name + " : activez d'abord " + parent.getName());
            return;
        }
        if (this.parent != null && this.parent.childrenType.equals("alternative")){
            for (Component c : parent.children){
                if (c.isActive() && c != this ){
                    System.out.println(c.getName() + " est déjà activé");
                    return;
                }
            }
        }

        this.activate = true;
    }

    @Override
    public void add(Component child) {
        if (child == null) {
            throw new IllegalArgumentException("child ne peut pas être null");
        } else if (child == this) {
            throw new IllegalArgumentException("Ne peux pas être son propre parent");
        } else if (child.getParent() != null) {
            throw new IllegalArgumentException("Feature a déja un parent");
        } else{
            this.children.add(child);
            child.setParent(this);
        }
    }

    @Override
    public String toString() {
        StringBuilder model = new StringBuilder();
        model.append(this.getName()).append(" [");
        for (Component c : children) {
            if (!c.getChildren().isEmpty()) {
                model.append(" ").append(c.toString());
            }else{
                model.append(" ").append(c.getName());
            }
        }
        model.append(" ]");
        return model.toString();
    }
}
