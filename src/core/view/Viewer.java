package core.view;

import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import core.observer.ObserverController;
import core.observer.ObserverView;
import core.observer.SubjectView;

public class Viewer implements ObserverView, SubjectView {
    JFrame frame;
    JPanel panel;
    String userInput;
    JButton buttonTemp;

    ObserverController observer;

    public Viewer(ObserverController observer){
        // Create window with title "application" that stop the program when closed
        this.frame = new JFrame("Application");

        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        // Creating a panel that will hold the buttons
        this.panel = new JPanel();

        // Setup frame with the panel
        this.frame.add(panel);
        this.frame.setSize(200, 200);
        this.frame.setVisible(true); // show the window

        // Creation of a button that will be shown dynamically
        this.buttonTemp = new JButton("Dynamic click me");
        this.buttonTemp.addActionListener((actionEvent) -> {
            //System.out.println("dynamic click");
            userInput = "dynamic click";
            notifyObserver();
        });

        this.observer = observer;
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

    public void setting(String input) {

        switch (input) {

            // Make the button appear
            case "add":
                this.panel.add(this.buttonTemp);
                break;

            // Remove the button
            //Remove ne fonctionne pas
            case "remove":
                this.panel.remove(this.buttonTemp);
                break;

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