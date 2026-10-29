package src.core.view.states;

import java.util.ArrayList;
import javax.swing.*;
import src.core.view.ViewState;
import src.core.view.Viewer;

public class DifficultyState implements ViewState {

    Viewer viewer;
    JFrame frame;
    JPanel panel;
    ArrayList<JButton> buttonTemps;
    JLabel titlePanel = new JLabel("SET DIFFICULTY");
    //Boolean difficultyIsSet = false;

    public DifficultyState(Viewer viewer) {
        this.viewer = viewer;
        this.frame = viewer.frame;
        this.panel = viewer.panel;
        this.buttonTemps = new ArrayList<>();
        build();
    }

    public void choose(int i) {
        viewer.getState(Viewer.Page.METHOD, MethodState.class).setDifficulty(i);
        viewer.getState(Viewer.Page.LESSONS, LessonState.class).setDifficulty(i);
        //viewer.goTo(Viewer.Page.LESSONS);
        viewer.goTo(Viewer.Page.METHOD);

    }

    private void build() {

        JButton easy = new JButton("Easy");
        easy.addActionListener(e -> choose(1));
        this.buttonTemps.add(easy);

        JButton medium = new JButton("Medium");
        medium.addActionListener(e -> choose(2));

        this.buttonTemps.add(medium);

        JButton hard = new JButton("Hard");
        hard.addActionListener(e -> choose(3));

        this.buttonTemps.add(hard);
    }

    @Override
    public void handleRequest() {
        this.panel.add(this.titlePanel);
        for (JButton b : this.buttonTemps) {
            this.panel.add(b);
        }
        this.frame.repaint();
        this.frame.revalidate();
    }
}
