package src.core.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import src.core.log.Logs;
import src.core.model.Facade;
import src.core.observer.ObserverController;
import src.core.observer.ObserverView;
import src.core.observer.SubjectController;
import src.core.view.Viewer;

public class Controller implements ControllerInterface, ObserverController, SubjectController {

    private Boolean isEnable = false;
    private final Logs logs = Logs.getInstance();
    private List<Viewer> observers = new ArrayList<>();
    private int currentObserver;
    private List<String> userInputs = new ArrayList<>();

    public Controller() {
    }

    @Override
    public int activate(String[] deactivations, String[] activations) {
        logs.addLog("Activating: " + String.join(", ", activations) + " and deactivating: " + String.join(", ", deactivations));
        return Facade.getInstance().activate(deactivations, activations);
        // return this.notifyObserver(deactivations, activations);
    }

    @Override
    public boolean enableUIView() {
        if (observers.isEmpty()) {
            addViewer();
        }
        for (ObserverView observer : observers) {
            observer.show();
            logs.addLog("User" + observers.indexOf(observer) + "is Connected" + '\n');
        }
        this.isEnable = true;
        return this.isEnable;
    }

    @Override
    public boolean disableUIView() {
        for (ObserverView observer : observers) {
            observer.hide();
            logs.addLog("User is/ users are Disconnected");
        }
        this.isEnable = false;
        return !this.isEnable;
    }

    @Override
    public String[] getStateAsLog() {
        return logs.getLogs();
    }

    public void addViewer() {
        this.currentObserver = observers.size() + 1;
        Viewer v = new Viewer(this, currentObserver);
        this.addObserver(v);
        if (this.isEnable) {
            enableUIView();
        }
        System.out.print("The current user is: " + currentObserver + '\n');
    }

    public void removeViewer() {
        this.observers.get(currentObserver - 1).close();
        this.removeObserver(this.observers.get(currentObserver - 1));
        System.out.print("The user " + this.currentObserver + " got removed" + '\n');
        this.currentObserver = observers.size();
    }

    @Override
    public void addObserver(ObserverView observer) {
        observers.add((Viewer) observer);
    }

    @Override
    public void removeObserver(ObserverView observer) {
        observers.remove(observer);
    }

    @Override
    public int notifyObserver(String[] deactivations, String[] activations) {
        return observers.get(currentObserver).update(deactivations, activations);
    }

    public boolean logsContains(String[] logs, String string) {
        for (String log : logs) {
            if (log.contains(string)) {
                return true;
            }
        }
        return false;
    }

    public void changeCurrentUser(int i) {
        if (0 <= i && i < observers.size()) {
            this.currentObserver = i;
            System.out.print("THE NEW USER CURRENT IS USER " + currentObserver + '\n');
        } else {
            System.out.print("THE INPUT IS NOT CORRECT");
        }

    }

    @Override
    public void update(String input) {
        this.userInputs.add(input);
        System.out.print(userInputs);
    }

    public String instructions(int i) {
        ArrayList<String> instructions = new ArrayList<>();

        System.out.print("\n List of Instructions: \n");
        if (!this.observers.isEmpty()) {
            System.out.print("The current user is: " + currentObserver + '\n');
        } else {
            System.out.print("There is not CURRENT USER \n");
        }

        String instruction1 = "";
        instruction1 += "ENABLE: SHOWS ALL UI VIEW OF THE USERS (IF THERE IS NOT USER, ENABLE WILL CREATE ONE) \n";
        instruction1 += "DISABLE: HIDE ALL UI VIEW OF THE USERS \n";
        instruction1 += "ADD: ADD A NEW USER \n";
        instruction1 += "REMOVE: REMOVE THE CURRENT USER \n";
        instruction1 += "CHANGE: CHANGE THE CURRENT USER \n";
        instruction1 += "MODEL: SHOW THE CURRENT USER MODEL \n";
        instruction1 += "STOP: SHUT DOWN THE CONSOLE \n";
        instruction1 += "LOG: PRINT THE LOG \n";
        instructions.add(instruction1);

        String instruction2 = "";
        instruction2 += "CHANGING CURRENT USER\n";
        instruction2 += "ENTER AN INPUT BETWEEN 1-" + this.observers.size() + "\n";
        instructions.add(instruction2);

        return instructions.get(i);
    }

    public void command() {
        Scanner in = new Scanner(System.in);

        while (true) {
            System.out.print(instructions(0));
            String line = in.nextLine();
            switch (line) {
                case "ENABLE":
                    enableUIView();
                    System.out.print("New View enable \n");
                    System.out.print(this.observers.isEmpty() + "\n");
                    break;
                case "DISABLE":
                    disableUIView();
                    System.out.print("View disable \n");
                    System.out.print(this.observers.isEmpty() + "\n");
                    break;
                case "ADD":
                    addViewer();
                    System.out.print(this.observers.size() + "\n");
                    break;

                case "REMOVE":
                    removeViewer();
                    System.out.print(this.observers.size() + "\n");
                    break;

                case "CHANGE":
                    if (this.observers.isEmpty()) {
                        System.out.print("THERE IS NO USER");
                        break;
                    } else if (this.observers.size() == 1) {
                        System.out.print("THERE IS ONLY ONE USER");
                        break;
                    } else {
                        System.out.print(instructions(1));
                        String numberLine = in.nextLine().trim();
                        try {
                            int current = Integer.parseInt(numberLine);
                            changeCurrentUser(current);
                        } catch (NumberFormatException e) {
                            System.out.println("Not a number: " + numberLine);
                        }
                        break;
                    }

                case "MODEL":
                    if (!observers.isEmpty()) {
                        System.out.print(this.observers.get(this.currentObserver - 1).model.toString() + '\n');
                    } else {
                        System.out.print("THERE IS NO USER");
                    }
                    break;

                case "STOP":
                    in.close();
                    System.exit(0);
                    break;

                case "LOG":
                    System.out.print(Arrays.toString(this.getStateAsLog()));
                    break;

                default:
                    if (!observers.isEmpty()) {
                        System.out.println("Unknown command " + line);
                    }

            }
        }
    }
}
