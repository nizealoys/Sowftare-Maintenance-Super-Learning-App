package core.controller;

import core.log.Logs;
import core.model.Facade;

public class Controller implements ControllerInterface{
    private final Facade facade;
    private final Logs logs = Logs.getInstance();

    public Controller(Facade facade) {
        this.facade = facade;
    }

    @Override
    public int activate(String[] deactivations, String[] activations){
        logs.addLog("Activating: " + String.join(", ", activations) + " and deactivating: " + String.join(", ", deactivations));
        facade.activate(deactivations, activations);
        return 0;
    }

    @Override
    public boolean enableUIView() {
        return true;
    }
    
    @Override
    public boolean disableUIView()
    {
        return true;
    }
    
    @Override
    public String[] getStateAsLog() {
        return logs.getLogs();   
    }
}
