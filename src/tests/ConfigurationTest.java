package src.tests;

import java.awt.GraphicsEnvironment;
import org.junit.Before;
import org.junit.Assume;
import org.junit.Test;
import src.core.controller.Controller;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import src.core.model.Composite;
import src.core.model.CompositeBuilder;
import src.core.model.Feature;
import src.feature.course.QA;
import src.feature.difficulty.Difficulty;

public class ConfigurationTest {

    private Controller controller;
    private CompositeBuilder compositeBuilder;

    @Before
    public void setUp() {
        compositeBuilder = CompositeBuilder.getInstance();
        controller = Controller.getInstance();
        compositeBuilder.reset();
    }

    @Test
    public void activateSeveralCourseWithoutPremiumTest() {
        compositeBuilder.reset();
        Feature english = Composite.getInstance().getFeature("English");
        Feature french = Composite.getInstance().getFeature("French");
        assertEquals(3, controller.activate(new String[]{}, new String[]{"English", "French"}));
        assertTrue(english.isActive());
        assertFalse(french.isActive());
    }

    @Test
    public void activateSeveralCourseWithPremiumTest() {
        compositeBuilder.reset();
        Feature english = Composite.getInstance().getFeature("English");
        Feature french = Composite.getInstance().getFeature("French");
        Feature premium = Composite.getInstance().getFeature("Premium");
        assertEquals(0, controller.activate(new String[]{}, new String[]{"Premium", "English", "French"}));
        assertTrue(english.isActive());
        assertTrue(french.isActive());
        assertTrue(premium.isActive());
    }
}
