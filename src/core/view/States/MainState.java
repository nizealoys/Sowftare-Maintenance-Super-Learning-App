package src.core.view.States;

import src.core.view.ViewState;
import core.view.Viewer;
import javax.swing.*;
import java.util.ArrayList;

public class MainState implements ViewState {

    Viewer viewer;
    JFrame frame;
    JPanel panel;
    ArrayList<JButton> buttonTemps;


    public MainState(Viewer viewer){
        /**
         * methode qui init l'etat.
         * JFrame frame = Frame du Viewer, le même pour chaque state
         * JPanel panel = Panel du Viewer, le même pour chaque state
         */

        this.viewer = viewer;
        this.frame = viewer.frame;
        this.panel = viewer.panel;
        this.buttonTemps = new ArrayList<>();
    }

    public void draw(){
        /**
         * Fonction qui dessine l'etat
         */
        this.buttonTemps.add(new JButton("User Profile"));
        this.buttonTemps.add(new JButton("Coins"));
        this.buttonTemps.add(new JButton("Shop"));

        JButton courseButton = new JButton("Course");
        courseButton.addActionListener((actionEvent) -> {
            viewer.goTo(Viewer.Page.COURSES);
        });

        this.buttonTemps.add(courseButton);

        for (JButton b : this.buttonTemps){
            this.panel.add(b);
        }
        // Creation of a button that will be shown dynamically
        /*
        this.buttonTemp = new JButton("Dynamic click me");
        this.buttonTemp.addActionListener((actionEvent) -> {
            //System.out.println("dynamic click");
            userInput = "dynamic click";
            notifyObserver();
        });
         */
    }


    @Override
    public void handleRequest() {
        draw();
    }
}
