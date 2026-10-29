package src.core.view.states;

import java.util.ArrayList;
import javax.swing.*;
import src.core.view.ViewState;
import src.core.view.Viewer;

public class MainState implements ViewState {

    Viewer viewer;
    JFrame frame;
    JPanel panel;
    ArrayList<JButton> buttonTemps;

    public MainState(Viewer viewer) {
        /**
         * methode qui init l'etat. JFrame frame = Frame du Viewer, le même pour
         * chaque state JPanel panel = Panel du Viewer, le même pour chaque
         * state
         */

        //ajouter frame.validate & frame.repaint
        this.viewer = viewer;
        this.frame = viewer.frame;
        this.panel = viewer.panel;
        this.buttonTemps = new ArrayList<>();
        build();
    }

    private void build() {
        /**
         * Fonction qui dessine l'etat
         */

        JButton coinButton = new JButton("Coins");
        coinButton.addActionListener((actionEvent) -> {
            //viewer.goTo(Viewer.Page.COIN);
            viewer.goTo(Viewer.Page.WIP);
        });
        this.buttonTemps.add(coinButton);

        JButton shopButton = new JButton("Shop");
        shopButton.addActionListener((actionEvent) -> {
            viewer.goTo(Viewer.Page.SHOP);
        });
        this.buttonTemps.add(shopButton);

        JButton courseButton = new JButton("Course");
        courseButton.addActionListener((actionEvent) -> {
            viewer.getState(Viewer.Page.COURSES, CourseState.class).getCourseAvailable();
            viewer.goTo(Viewer.Page.COURSES);
        });
        this.buttonTemps.add(courseButton);

        JButton profilbutton = new JButton("User Profile");
        profilbutton.addActionListener((actionEvent) -> {
            viewer.getState(Viewer.Page.PROFILE, src.core.view.states.ProfileState.class).getData();
            viewer.getState(Viewer.Page.PROFILE, src.core.view.states.ProfileState.class).premiumPanel();
            viewer.goTo(Viewer.Page.PROFILE);
        });
        this.buttonTemps.add(profilbutton);
    }

    @Override
    public void handleRequest() {
        for (JButton b : this.buttonTemps) {
            this.panel.add(b);
        }
        this.frame.repaint();
        this.frame.revalidate();
    }
}
