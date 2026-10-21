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



        //ajouter frame.validate & frame.repaint

        this.viewer = viewer;
        this.frame = viewer.frame;
        this.panel = viewer.panel;
        this.buttonTemps = new ArrayList<>();
        build();
    }

    public void build(){
        /**
         * Fonction qui dessine l'etat
         */

        JButton coinButton = new JButton("Coins");
        coinButton.addActionListener((actionEvent) -> {
            viewer.goTo(Viewer.Page.COIN);
        });
        this.buttonTemps.add(coinButton);


        JButton shopButton = new JButton("Shop");
        shopButton.addActionListener((actionEvent) -> {
            viewer.goTo(Viewer.Page.SHOP);
        });
        this.buttonTemps.add(shopButton);

        JButton courseButton = new JButton("Course");
        courseButton.addActionListener((actionEvent) -> {
            viewer.goTo(Viewer.Page.COURSES);
        });
        this.buttonTemps.add(courseButton);

        JButton profilbutton = new JButton("User Profile");
        profilbutton.addActionListener((actionEvent) -> {
            viewer.goTo(Viewer.Page.PROFILE);
        });
        this.buttonTemps.add(profilbutton);

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
        for (JButton b : this.buttonTemps){
            this.panel.add(b);
        }
        this.frame.repaint();
        this.frame.revalidate();
    }
}
