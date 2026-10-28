package src.core.controller;

import src.core.log.Logs;
import src.core.model.Composite;
import src.core.model.Facade;
import src.core.view.Viewer;

public class Controller implements ControllerInterface {

    public static Controller instance = new Controller();
    private Boolean isEnable = false;
    private final Logs logs = Logs.getInstance();
    private Viewer observer;
    private final Composite composite;


    private Viewer getViewer() {
        if (this.observer == null) {
            this.observer = new Viewer();
        }
        return this.observer;
    }



    private Controller() {
        this.composite = Composite.getInstance();
    }

    public static Controller getInstance() {
        return instance;
    }

    @Override
    public int activate(String[] deactivations, String[] activations) {
        logs.addLog("Activating: " + String.join(", ", activations) + " and deactivating: " + String.join(", ", deactivations));
        return Facade.getInstance().activate(deactivations, activations);
    }

    @Override
    public boolean enableUIView() {
        getViewer().show();
        logs.addLog("User is Connected" + '\n');
        this.isEnable = true;
        return this.isEnable;
    }

    @Override
    public boolean disableUIView() {
        getViewer().hide();
        logs.addLog("User is users are Disconnected");
        this.isEnable = false;
        return !this.isEnable;
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

}
