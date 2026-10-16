package core.view;

import java.awt.Color;
import java.util.EnumMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import core.observer.ObserverController;
import core.observer.ObserverView;
import core.observer.SubjectView;
import src.core.view.States.*;
import src.core.view.ViewState;

public class Viewer implements ObserverView, SubjectView {

    public enum Page {MAIN, COURSES, COIN, SHOP, METHOD, PROFILE}
    private final Map<Page, ViewState> states = new EnumMap<>(Page.class);
    private ViewState currentState;
    public JFrame frame;
    public JPanel panel;
    String userInput;
    //JButton buttonTemp;

    ObserverController observer;

    public Viewer(ObserverController observer){
        // Create window with title "application" that stop the program when closed
        this.frame = new JFrame("Application");

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Creating a panel that will hold the buttons
        this.panel = new JPanel();

        // Setup frame with the panel
        this.frame.add(panel);
        this.frame.setSize(1920, 1080);
        this.frame.setVisible(true); // show the window

        this.InitAllStates();
        this.goTo(Page.MAIN);

        this.observer = observer;
        System.out.print(currentState);
    }
    @Override
    public void update(String input){
        setting(input);
        this.notifyObserver();
         }

    @Override
    public void notifyObserver() {
        if (userInput != null){
            observer.update(this.userInput);
        }
    }

    public void InitAllStates(){
        states.put(Page.MAIN,   new MainState(this));
        states.put(Page.COURSES, new CourseState(this));
    }

    public void goTo(Page page){
        panel.removeAll();
        this.currentState = states.get(page);
        this.currentState.handleRequest();
    }

    public void setting(String input) {

        switch (input) {

            // Make the button appear
            /*
            case "add":
                this.panel.add(this.buttonTemp);
                break;


            // Remove the button
            //Remove ne fonctionne pas
            case "remove":
                this.panel.remove(this.buttonTemp);
                break;
             */

            // Enable dark mode
            case "dark":
                this.panel.setBackground(Color.BLACK);
                break;

            // Disable dark mode
            case "light":
                this.panel.setBackground(Color.WHITE);
                break;

            //utilisé par disableUIView
            case "CLOSE":
                this.frame.dispose();
                return;


            // Unknown command
            default:
                System.out.println("Unknown command " + input);
                break;
        }
        this.frame.repaint();
        this.frame.revalidate();
    }
}