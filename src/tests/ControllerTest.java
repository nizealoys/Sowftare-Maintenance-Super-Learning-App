
package src.tests;

import org.junit.Before;
import org.junit.Test;
import src.core.controller.Controller;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import src.core.model.Composite;
import src.core.model.CompositeBuilder;
import src.core.model.Feature;

import java.io.IOException;

public class ControllerTest {

    private Controller controller;

    @Before
    public void setUp() {
        CompositeBuilder cb = CompositeBuilder.getInstance();
        Composite composite = Composite.getInstance();
        System.out.println(composite);
        this.controller = Controller.getInstance();
    }

    @Test
    public void activateOptionalFeatureUnitTest() {

        Feature english = Composite.getInstance().getFeature("English");
        controller.activate(new String[]{}, new String[]{"English"});
        assertTrue(english.isActive());

    }

    @Test
    public void deactivateMandatoryFeatureUnitTest() {
        Feature Course = Composite.getInstance().getFeature("Course");
        controller.activate( new String[]{"Course"}, new String[]{});
        assertTrue(Course.isActive());

    }

    @Test
    public void deactivateOptionalFeatureUnitTest() {
        Feature english = Composite.getInstance().getFeature("English");
        controller.activate(new String[]{}, new String[]{"english"});
        controller.activate(new String[]{"english"} ,new String[]{});
        assertFalse(english.isActive());

    }

    @Test
    public void activateMandatoryFeatureUnitTest() {
        Feature Course = Composite.getInstance().getFeature("Course");
        controller.activate(new String[]{}, new String[]{"Course"});
        assertTrue(Course.isActive());

    }

    @Test
    public void enableUIViewUnitTest() throws IOException {
        boolean enableTest = controller.enableUIView();
        assertTrue(enableTest);

    }

    @Test
    public void multipleEnablesUIViewUnitTest() throws IOException {
        controller.enableUIView();
        boolean enableTest = controller.enableUIView();
        assertFalse(enableTest);

    }

    @Test
    public void disableUIViewUnitTest() throws IOException {
        controller.enableUIView();
        boolean disableTest = controller.disableUIView();
        assertTrue(disableTest);

    }

    @Test
    public void uselessDisableUIViewUnitTest() throws IOException {
        controller.enableUIView();
        controller.disableUIView();
        boolean disableTest = controller.disableUIView();
        assertFalse(disableTest);

    }

    @Test
    public void getStateAsLogTest() {
        //CLI test = new CLI();
    }
}
