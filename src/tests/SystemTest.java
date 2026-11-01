package src.tests;

import org.junit.Before;
import org.junit.Test;
import src.core.controller.Controller;
import src.core.model.Composite;
import src.core.model.CompositeBuilder;
import src.core.model.Feature;
import src.feature.achievement.Achievement;
import src.feature.course.Course;
import src.feature.course.QA;
import src.feature.difficulty.Difficulty;
import src.feature.course.subfeature.type.Cooking;
import src.feature.course.subfeature.type.English;
import src.feature.course.subfeature.type.French;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class SystemTest {

    private Controller controller;
    private CompositeBuilder compositeBuilder;

    @Before
    public void setUp() {
        compositeBuilder = CompositeBuilder.getInstance();
        controller = Controller.getInstance();
        compositeBuilder.reset();
    }

    @Test
    public void completeLearningSessionWithPremiumTest() {
        compositeBuilder.reset();

        Feature premium = Composite.getInstance().getFeature("Premium");
        Feature english = Composite.getInstance().getFeature("English");
        Feature french = Composite.getInstance().getFeature("French");

        assertNotNull(controller.getComposite().getRoot());

        int activationResult = controller.activate(
                new String[]{},
                new String[]{"Premium", "English", "French"});

        assertEquals(0, activationResult);
        assertTrue(premium.isActive());
        assertTrue(english.isActive());
        assertTrue(french.isActive());

        controller.setDifficulty("English", 3);
        assertEquals(3, Difficulty.getInstance().getDifficulty("English"));

        QA question = English.getInstance().getQAList().get(0);

        assertFalse(controller.checkQA(question, "false answer"));
        assertFalse(question.getIsCompleted());
        assertTrue(controller.checkQA(question, question.getAnswer()));
        assertTrue(question.getIsCompleted());
        assertTrue(controller.logsContains(
                controller.getStateAsLog(),
                "QA completed: " + question.getQuestion()));

        QA frenchQuestion = French.getInstance().getQAList().get(0);
        assertFalse(controller.checkQA(frenchQuestion, "false answer"));
        assertFalse(frenchQuestion.getIsCompleted());
        assertTrue(controller.checkQA(frenchQuestion, frenchQuestion.getAnswer()));
        assertTrue(frenchQuestion.getIsCompleted());
        assertTrue(controller.logsContains(
                controller.getStateAsLog(),
                "QA completed: " + frenchQuestion.getQuestion()));

        QA cookingQuestion = Cooking.getInstance().getQAList().get(0);
        assertFalse(controller.checkQA(cookingQuestion, "false answer"));
        assertFalse(cookingQuestion.getIsCompleted());
        assertTrue(controller.checkQA(cookingQuestion, cookingQuestion.getAnswer()));
        assertTrue(cookingQuestion.getIsCompleted());
        assertTrue(controller.logsContains(
                controller.getStateAsLog(),
                "QA completed: " + cookingQuestion.getQuestion()));
    }

    @Test
    public void completeLearningSessionWithoutPremiumTest() {
        compositeBuilder.reset();

        Feature premium = Composite.getInstance().getFeature("Premium");
        Feature english = Composite.getInstance().getFeature("English");
        Feature french = Composite.getInstance().getFeature("French");

        int activationResult = controller.activate(
                new String[]{},
                new String[]{"English"});

        assertEquals(0, activationResult);
        assertFalse(premium.isActive());
        assertTrue(english.isActive());
        assertFalse(french.isActive());

        controller.setDifficulty("English", 2);
        assertEquals(2, Difficulty.getInstance().getDifficulty("English"));

        QA question = English.getInstance().getQAList().get(0);

        assertFalse(controller.checkQA(question, "false answer"));
        assertFalse(question.getIsCompleted());
        assertTrue(controller.checkQA(question, question.getAnswer()));
        assertTrue(question.getIsCompleted());
        assertTrue(controller.logsContains(
                controller.getStateAsLog(),
                "QA completed: " + question.getQuestion()));
    }

    @Test
    public void achievementsAreUnlockedDuringLearningSessionTest() {
        compositeBuilder.reset();
        Feature premium = Composite.getInstance().getFeature("Premium");

        assertFalse(Achievement.getInstance()
                .getAchievementElement("Premium User").isAchieved());
        assertFalse(Achievement.getInstance()
                .getAchievementElement("First Steps").isAchieved());
        assertFalse(Achievement.getInstance()
                .getAchievementElement("Quiz Master").isAchieved());

        assertEquals(0, controller.activate(
                new String[]{},
                new String[]{"Premium"}));
        assertTrue(premium.isActive());
        assertTrue(Achievement.getInstance()
                .getAchievementElement("Premium User").isAchieved());

        QA question = English.getInstance().getQAList().get(0);
        assertFalse(controller.checkQA(question, "false answer"));
        assertFalse(question.getIsCompleted());
        assertTrue(controller.checkQA(question, question.getAnswer()));
        assertTrue(question.getIsCompleted());
        assertTrue(controller.logsContains(
                controller.getStateAsLog(),
                "QA completed: " + question.getQuestion()));

        assertTrue(Achievement.getInstance()
                .getAchievementElement("First Steps").isAchieved());

        English.getInstance().getQAList().forEach(qa -> controller.checkQA(qa, qa.getAnswer()));
        French.getInstance().getQAList().forEach(qa -> controller.checkQA(qa, qa.getAnswer()));
        Cooking.getInstance().getQAList().forEach(qa -> controller.checkQA(qa, qa.getAnswer()));

        assertTrue(Achievement.getInstance()
                .getAchievementElement("Quiz Master").isAchieved());
    }

}
