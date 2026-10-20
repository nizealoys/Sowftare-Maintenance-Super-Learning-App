package src.core.view.States;

import src.core.view.ViewState;
import core.view.Viewer;
import javax.swing.*;
import java.util.ArrayList;


public class ShopState implements ViewState {
    core.view.Viewer viewer;
    JFrame frame;
    JPanel panel;
    ArrayList<JButton> buttonTemps;

    public ShopState ( Viewer viewer){
        this.viewer = viewer;
        this.frame = viewer.frame;
        this.panel = viewer.panel;
        this.buttonTemps = new ArrayList<>();
        build();
    }

    @Override
    public void build() {

        JButton mainbutton = new JButton("Main Page");
        mainbutton.addActionListener((actionEvent) -> {
            viewer.goTo(core.view.Viewer.Page.MAIN);
        });
        this.buttonTemps.add(mainbutton);

        this.buttonTemps.add(new JButton("Course slot"));
        this.buttonTemps.add(new JButton("Cosmetics"));
        this.buttonTemps.add(new JButton("Early access"));
        this.buttonTemps.add(new JButton("Ad free"));
        this.buttonTemps.add(new JButton("Premium"));

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
