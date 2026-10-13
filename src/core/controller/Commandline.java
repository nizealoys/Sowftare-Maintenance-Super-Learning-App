package core.controller;

import core.observer.ObserverController;
import core.observer.ObserverView;
import core.observer.SubjectController;
import core.view.Viewer;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public  class Commandline implements SubjectController, ObserverController {
    private List<ObserverView> observers = new ArrayList<>();
    private List<String> userInputs = new ArrayList<>();

    @Override
    public void addObserver(ObserverView observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(ObserverView observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObserver(String input) {
        for (ObserverView observer : observers) {
            observer.update(input);
        }
    }

    public void enableUI() {
        Viewer v = new Viewer( this);
        this.addObserver(v);
    }

    public void disableUIView(){
        for (ObserverView observer : observers){
            observer.update("CLOSE");
        }
        this.observers = new ArrayList<>();

    }

    public void command(){
        Scanner in = new Scanner(System.in);

        while (true) {
            String line = in.nextLine();
            switch (line){
                case "a":
                    enableUI();
                    System.out.print("New View enable \n" );
                    System.out.print(this.observers.isEmpty() + "\n");
                    break;
                case "b":
                    disableUIView();
                    System.out.print("View disable \n");
                    System.out.print(this.observers.isEmpty()  + "\n");
                    break;

                case "stop":
                    in.close();
                    System.exit(0);
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