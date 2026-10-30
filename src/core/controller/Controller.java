package src.core.controller;

import java.io.IOException;
import src.core.log.Logs;
import src.core.model.Composite;
import src.core.model.CompositeBuilder;
import src.core.model.Facade;
import src.core.view.Viewer;
import src.feature.course.QA;

public class Controller implements ControllerInterface {

    private final static Controller instance = new Controller();
    private final Logs logs = Logs.getInstance();
    private final Facade facade = Facade.getInstance();
    private Viewer observer;
    private final Composite composite;

    private Viewer getViewer() throws IOException {
        if (this.observer == null) {
            this.observer = new Viewer();
        }
        return this.observer;
    }

    private Controller() {
        CompositeBuilder.getInstance();
        this.composite = Composite.getInstance();
    }

    public static Controller getInstance() {
        return instance;
    }

    @Override
    public int activate(String[] deactivations, String[] activations) {
        return facade.activate(deactivations, activations);
    }

    @Override
    public boolean enableUIView() throws IOException {
        return getViewer().show();
    }

    @Override
    public boolean disableUIView() throws IOException {
        return getViewer().hide();
    }

    @Override
    public String[] getStateAsLog() {
        return logs.getLogs();
    }

    public boolean logsContains(String[] logs, String string) {
        for (String log : logs) {
            if (log.contains(string)) {
                return true;
            }
        }
        return false;
    }

    public Composite getComposite() {
        return this.composite;
    }

    public void completeQA(QA qa) {
        facade.completeQA(qa);
    }

    public void setDifficulty(String courseName, int difficulty) {
        facade.setDifficulty(courseName, difficulty);
    }

}
