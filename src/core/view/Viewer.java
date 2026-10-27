package src.core.view;

import java.awt.Color;
import java.util.EnumMap;
import java.util.Map;

import javax.swing.*;

import src.core.model.Facade;
import src.core.observer.ObserverController;
import src.core.observer.ObserverView;
import src.core.observer.SubjectView;
import src.core.view.States.*;
import src.core.view.ViewState;

public class Viewer implements ObserverView, SubjectView {

    public Facade model;

    private int userId;

    public enum Page {
        MAIN, COURSES, COIN, SHOP, METHOD, PROFILE, EXERCICES, LESSONS, TOPIC, DIFFICULTY
    }
    private final Map<Page, ViewState> states = new EnumMap<>(Page.class);
    private ViewState currentState;
    public JFrame frame;
    public JPanel panel;
    String userInput;
    //JButton buttonTemp;

    ObserverController observer;

    public Viewer(ObserverController observer, int userId) {
        this.model = Facade.getInstance();
        this.userId = userId;
        // Create window with title "application" that stop the program when closed
        this.frame = new JFrame("SUPER LEARNING APP: COOKING EDITION - USER " + this.userId);

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Creating a panel that will hold the buttons
        this.panel = new JPanel();

        // Setup frame with the panel
        this.frame.add(panel);
        this.frame.setSize(1920, 1080);
        //this.frame.setSize(200, 200);
        //this.frame.setVisible(true); // show the window

        this.InitAllStates();
        this.goTo(Page.MAIN);

        this.observer = observer;
    }

    @Override
    public int update(String[] deactivations, String[] activations) {
        return model.activate(deactivations, activations);
    }

    @Override
    public void notifyObserver() {
        if (userInput != null) {
            observer.update(this.userInput);
        }
    }

    public void InitAllStates() {
        states.put(Page.MAIN, new MainState(this));
        states.put(Page.COURSES, new CourseState(this));
        states.put(Page.SHOP, new ShopState(this));
        states.put(Page.COIN, new CoinState(this));
        states.put(Page.METHOD, new MethodState(this));
        states.put(Page.PROFILE, new ProfileState(this));
        states.put(Page.LESSONS, new LessonState(this));
        states.put(Page.TOPIC, new TopicState(this));
        states.put(Page.EXERCICES, new ExerciceState(this));
        states.put(Page.DIFFICULTY, new DifficultyState(this));
    }

    public <T extends ViewState> T getState(Page page, Class<T> type) {
        return type.cast(states.get(page));
    }

    public void goTo(Page page) {
        panel.removeAll();
        this.currentState = states.get(page);
        this.currentState.handleRequest();
    }

    @Override
    public void show()  { SwingUtilities.invokeLater(() -> frame.setVisible(true)); }

    @Override
    public void hide()  { SwingUtilities.invokeLater(() -> frame.setVisible(false)); }

    @Override
    public void close() { SwingUtilities.invokeLater(frame::dispose); }

    public int getUserId() { return this.userId; }
}
