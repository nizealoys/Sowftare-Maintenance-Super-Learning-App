package src.tests;

import org.junit.Before;
import org.junit.Test;
import src.core.controller.Controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import src.core.model.Composite;
import src.core.utils.ChildrenType;
import src.core.model.Feature;

public class ControllerTest {

    private Controller controller;

    @Before
    public void setUp() {
        Composite composite = Composite.getInstance();
        composite.setRoot(new Feature("Feature1", true, true, ChildrenType.NONE));
        composite.getRoot().add(new Feature("Feature2", false, true, ChildrenType.NONE));
        composite.getRoot().add(new Feature("Feature3", false, true, ChildrenType.NONE));
        composite.getRoot().getChildren().get(0).add(new Feature("Feature4", false, true, ChildrenType.NONE));
        this.controller = new Controller();
    }

    @Test
    public void activateFeaturesUnitTest() {
        Feature feature2 = Composite.getInstance().getFeature("Feature2");
        Feature feature4 = Composite.getInstance().getFeature("Feature4");

        assertTrue(Composite.getInstance().getRoot().isActive());
        assertFalse(feature2.isActive());
        assertFalse(feature4.isActive());

        controller.activate(new String[]{}, new String[]{"Feature4"});
        assertFalse(feature4.isActive());
        controller.activate(new String[]{}, new String[]{"Feature2"});
        assertTrue(feature2.isActive());
        assertFalse(feature4.isActive());

        controller.activate(new String[]{}, new String[]{"Feature4"});
        assertTrue(feature4.isActive());

        controller.activate(new String[]{"Feature2"}, new String[]{});
        assertFalse(feature2.isActive());
        assertFalse(feature4.isActive());
    }

    @Test
    public void enableUIViewUnitTest() {
        Controller test = new Controller();
        test.enableUIView();
        assertTrue(test.logsContains(test.getStateAsLog(), "User is Connected"));
        //System.exit(0);
    }

    @Test
    public void disableUIViewUnitTest() {
        Controller test = new Controller();
        test.enableUIView();
        test.disableUIView();
        assertTrue(test.logsContains(test.getStateAsLog(), "User is Disconnected"));
        //System.exit(0);
    }

    @Test
    public void getStateAsLogTest() {
        Controller test = new Controller();
    }
}
