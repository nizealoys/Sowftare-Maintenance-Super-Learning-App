package src.core.view.States;

import src.core.view.ViewState;
import core.view.Viewer;
import javax.swing.*;
import java.util.ArrayList;

public class ProfileState implements ViewState {
    core.view.Viewer viewer;
    JFrame frame;
    JPanel panel;
    ArrayList<JButton> buttonTemps;

    public ProfileState(Viewer viewer){
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
