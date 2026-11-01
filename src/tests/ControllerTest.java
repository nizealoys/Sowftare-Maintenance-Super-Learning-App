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
import src.core.model.Feature;
import src.feature.course.QA;
import src.feature.difficulty.Difficulty;

import java.io.IOException;

public class ControllerTest {

    private Controller controller;

    @Before
    public void setUp() {
        controller = Controller.getInstance();
    }

    @Test
    public void activateOptionalFeatureUnitTest() {

        Feature english = Composite.getInstance().getFeature("English");
        assertEquals(0, controller.activate(new String[]{}, new String[]{"English"}));
        assertTrue(english.isActive());

    }

    @Test
    public void deactivateMandatoryFeatureUnitTest() {
        Feature Course = Composite.getInstance().getFeature("Course");
        int result = controller.activate(new String[]{"Course"}, new String[]{});
        assertEquals(-1, result);
        assertTrue(Course.isActive());

    }

    @Test
    public void deactivateOptionalFeatureUnitTest() {
        Feature english = Composite.getInstance().getFeature("English");
        controller.activate(new String[]{}, new String[]{"english"});
        controller.activate(new String[]{"english"}, new String[]{});
        assertFalse(english.isActive());

    }

    @Test
    public void activateMandatoryFeatureUnitTest() {
        Feature Course = Composite.getInstance().getFeature("Course");
        controller.activate(new String[]{"Course"}, new String[]{});
        assertEquals(-1, controller.activate(new String[]{}, new String[]{"Course"}));
        assertTrue(Course.isActive());

    }

    @Test
    public void enableUIViewUnitTest() throws IOException {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        boolean enableTest = controller.enableUIView();
        assertTrue(enableTest);

    }

    @Test
    public void multipleEnablesUIViewUnitTest() throws IOException {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        controller.enableUIView();
        boolean enableTest = controller.enableUIView();
        assertFalse(enableTest);

    }

    @Test
    public void disableUIViewUnitTest() throws IOException {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        controller.enableUIView();
        boolean disableTest = controller.disableUIView();
        assertTrue(disableTest);

    }

    @Test
    public void uselessDisableUIViewUnitTest() throws IOException {
        Assume.assumeFalse(GraphicsEnvironment.isHeadless());
        controller.enableUIView();
        controller.disableUIView();
        boolean disableTest = controller.disableUIView();
        assertFalse(disableTest);

    }

    @Test
    public void getStateAsLogTest() {
        int logCount = controller.getStateAsLog().length;
        controller.activate(new String[]{}, new String[]{"English"});

        String[] logs = controller.getStateAsLog();

        assertTrue(logs.length > logCount);
        assertTrue(controller.logsContains(logs, "Activating:"));
    }

    @Test
    public void logsContainsReturnsFalseWhenTextIsAbsent() {
        assertFalse(controller.logsContains(new String[]{"first log", "second log"}, "missing"));
    }

    @Test
    public void getCompositeReturnsApplicationComposite() {
        assertNotNull(controller.getComposite());
        assertEquals("SuperLearningApp",
                controller.getComposite().getRoot().getName());
    }

    @Test
    public void completeQACompletesQuestionAndWritesLog() {
        QA qa = new QA(1, "Explanation", 1, "Question", "Answer");

        controller.completeQA(qa);

        assertTrue(qa.getIsCompleted());
        assertTrue(controller.logsContains(controller.getStateAsLog(), "QA completed: Question"));
    }

    @Test
    public void setDifficultyUpdatesCourseDifficultyAndWritesLog() {
        controller.setDifficulty("English", 3);

        assertEquals(3, Difficulty.getInstance().getDifficulty("English"));
        assertTrue(controller.logsContains(controller.getStateAsLog(),
                "Difficulty set to: 3 for course: English"));
    }

    @Test
    public void setDifficultyForUnknownCourseWritesNotFoundLog() {
        controller.setDifficulty("Unknown course", 2);

        assertTrue(controller.logsContains(controller.getStateAsLog(),
                "Course not found: Unknown course"));
    }
}
