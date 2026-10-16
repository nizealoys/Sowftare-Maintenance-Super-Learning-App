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
    }

    @Override
    public void draw() {

        JButton main = new JButton("Main Page");
        main.addActionListener((actionEvent) -> {
            viewer.goTo(Viewer.Page.MAIN);
        });
        this.buttonTemps.add(new JButton("User Profile"));

        this.buttonTemps.add(new JButton("Cooking"));
        this.buttonTemps.add(new JButton("English"));
        this.buttonTemps.add(new JButton("French"));
        for (JButton b : this.buttonTemps){
            this.panel.add(b);
        }
    }

    @Override
    public void handleRequest() {
        draw();
    }
}
