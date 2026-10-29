package src.core.view.states;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import src.core.view.ViewState;
import src.core.view.Viewer;

public class WIPState implements ViewState {

    Viewer viewer;
    JFrame frame;
    JPanel panel;
    ArrayList<JButton> buttonTemps;

    public WIPState(Viewer viewer) throws IOException {
        this.viewer = viewer;
        this.frame = viewer.frame;
        this.panel = viewer.panel;
        this.buttonTemps = new ArrayList<>();
        build();
    }

    private void build() throws IOException {

        JButton main = new JButton("Main Page");
        main.addActionListener((actionEvent) -> {
            viewer.goTo(Viewer.Page.MAIN);
        });
        this.buttonTemps.add(main);

        JButton profilbutton = new JButton("User Profile");
        profilbutton.addActionListener((actionEvent) -> {
            viewer.getState(Viewer.Page.PROFILE, src.core.view.states.ProfileState.class).getData();
            viewer.getState(Viewer.Page.PROFILE, src.core.view.states.ProfileState.class).infoPanel();
            viewer.goTo(Viewer.Page.PROFILE);
        });
        this.buttonTemps.add(profilbutton);
    }

    @Override
    public void handleRequest() {
        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.Y_AXIS));

        JPanel buttons = new JPanel();
        for (JButton b : this.buttonTemps) {
            buttons.add(b);
        }

        JLabel pic = viewer.loadPicture("src/data/work-in-progress.jpg");
        buttons.setAlignmentX(Component.CENTER_ALIGNMENT);
        pic.setAlignmentX(Component.CENTER_ALIGNMENT);
        content.add(buttons);
        content.add(pic);

        this.panel.add(content);
        this.frame.repaint();
        this.frame.revalidate();
    }
}
