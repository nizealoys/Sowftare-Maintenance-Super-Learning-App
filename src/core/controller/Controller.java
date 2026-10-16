package src.core.controller;

import src.core.log.LogElement;
import src.core.log.Logs;
import src.core.model.Facade;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import core.observer.ObserverController;
import core.observer.SubjectController;
import core.controller.ControllerInterface;

public class Controller implements ControllerInterface, ObserverController, SubjectController {
    private Facade facade;
    private final Logs logs = Logs.getInstance();
    private List<core.observer.ObserverView> observers = new ArrayList<>();
    private List<String> userInputs = new ArrayList<>();

    public Controller() {}

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
        core.view.Viewer v = new core.view.Viewer( this);
        this.addObserver(v);
        logs.addLog("User is Connected");

        return true;
    }

    @Override
    public boolean disableUIView(){
        for (core.observer.ObserverView observer : observers){
            observer.update("CLOSE");
            logs.addLog("User is Disconnected");
        }
        this.observers = new ArrayList<>();

        return true;
    }

    @Override
    public String[] getStateAsLog() {return logs.getLogs();}

    @Override
    public void addObserver(core.observer.ObserverView observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(core.observer.ObserverView observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(String input) {
        for (core.observer.ObserverView observer : observers) {
            observer.update(input);
        }
    }

    public boolean logsContains(String[] logs, String string) {
        for (String log : logs) {
            if (log.contains(string)) {
                return true;
            }
        }
        return false;
    }

    public void command(){
        Scanner in = new Scanner(System.in);

        while (true) {
            String line = in.nextLine();
            switch (line){
                case "enable":
                    enableUIView();
                    System.out.print("New View enable \n" );
                    System.out.print(this.observers.isEmpty() + "\n");
                    break;
                case "disable":
                    disableUIView();
                    System.out.print("View disable \n");
                    System.out.print(this.observers.isEmpty()  + "\n");
                    break;

                case "stop":
                    in.close();
                    System.exit(0);
                    break;

                case "log":
                    System.out.print(Arrays.toString(this.getStateAsLog()));
                    break;

                default:
                    notifyObserver(line);
            }
        }
    }

    @Override
    public void update(String input) {
        this.userInputs.add(input);
        System.out.print(userInputs);
    }
}
