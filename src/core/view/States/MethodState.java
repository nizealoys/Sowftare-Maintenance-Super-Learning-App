package src.core.view.States;

import src.core.view.ViewState;
import core.view.Viewer;
import javax.swing.*;
import java.util.ArrayList;

public class MethodState implements ViewState {
    core.view.Viewer viewer;
    JFrame frame;
    JPanel panel;
    ArrayList<JButton> buttonTemps;

    public MethodState(Viewer viewer){
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
            viewer.goTo(core.view.Viewer.Page.MAIN);
        });

        this.buttonTemps.add(main);

        this.buttonTemps.add(new JButton("Workshop"));
        this.buttonTemps.add(new JButton("Exercices"));
        this.buttonTemps.add(new JButton("Lessons"));

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
