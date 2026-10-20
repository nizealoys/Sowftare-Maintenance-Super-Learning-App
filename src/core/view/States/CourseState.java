package src.core.view.States;

import src.core.view.ViewState;
import core.view.Viewer;
import javax.swing.*;
import java.util.ArrayList;

public class CourseState implements ViewState {

    Viewer viewer;
    JFrame frame;
    JPanel panel;
    ArrayList<JButton> buttonTemps;

    public CourseState ( Viewer viewer){
        this.viewer = viewer;
        this.frame = viewer.frame;
        this.panel = viewer.panel;
        this.buttonTemps = new ArrayList<>();
        build();
    }

    @Override
    public void build() {

        JButton main = new JButton("Main Page");
        main.addActionListener((actionEvent) -> {
            viewer.goTo(Viewer.Page.MAIN);
        });

        this.buttonTemps.add(main);

        JButton cookingbutton = new JButton("Cooking");
        cookingbutton.addActionListener((actionEvent) -> {
            viewer.goTo(Viewer.Page.METHOD);
        });
        this.buttonTemps.add(cookingbutton);

        JButton englishbutton = new JButton("English");
        englishbutton.addActionListener((actionEvent) -> {
            viewer.goTo(Viewer.Page.METHOD);
        });
        this.buttonTemps.add(englishbutton);

        JButton frenchbutton = new JButton("French");
        frenchbutton.addActionListener((actionEvent) -> {
            viewer.goTo(Viewer.Page.METHOD);
        });
        this.buttonTemps.add(frenchbutton);

        JButton profilbutton = new JButton("User Profile");
        profilbutton.addActionListener((actionEvent) -> {
            viewer.goTo(Viewer.Page.PROFILE);
        });
        this.buttonTemps.add(profilbutton);
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
