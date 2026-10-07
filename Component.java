import java.util.List;

public interface Component{
    String getName();
    boolean isMandatory();
    boolean isActive();
    Component getParent();
    List<Component> getChildren();

    boolean desactivation();
    boolean activation();
    void add(Component c);

    void setParent(Component c);

    String toString();
}