import java.util.List;

public interface Component{
    String getName();
    boolean isMandatory();
    boolean isActive();
    Component getParent();
    List<Component> getChildren();

    void desactivation();
    void activation();
    void add(Component c);

    void setParent(Component c);
}
