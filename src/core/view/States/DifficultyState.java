package src.core.view.States;

import src.core.view.ViewState;
import src.core.view.Viewer;
import src.feature.course.subfeature.method.Methode;

import javax.swing.*;
import java.util.ArrayList;

/**
 * LES SETS DIFFICULTY BUG QUAND ON L'UTILISE SUR PLUSIEURS VIEWERS EN MÊME TEMPS
 */
public class DifficultyState implements ViewState {
    Viewer viewer;
    JFrame frame;
    JPanel panel;
    ArrayList<JButton> buttonTemps;
    Methode current;
    String courseName;
    JLabel titlePanel= new JLabel("SET DIFFICULTY");


    public DifficultyState(Viewer viewer){
        this.viewer = viewer;
        this.frame = viewer.frame;
        this.panel = viewer.panel;
        this.buttonTemps = new ArrayList<>();
        build();
    }

    public void choose (int i){
        viewer.getState(Viewer.Page.METHOD, MethodState.class).setDifficulty(i);
        viewer.getState(Viewer.Page.LESSONS, LessonState.class).setDifficulty(i);
        viewer.goTo(Viewer.Page.LESSONS);
    }

    @Override
    public void build() {

        JButton easy = new JButton("Easy");
        easy.addActionListener(e ->choose (1));
        this.buttonTemps.add(easy);

        JButton medium = new JButton("Medium");
        medium.addActionListener(e ->choose (2));

        this.buttonTemps.add(medium);

        JButton hard = new JButton("Hard");
        hard.addActionListener(e ->choose (3));

        this.buttonTemps.add(hard);
    }

    @Override
    public void handleRequest() {
        this.panel.add(this.titlePanel);
        for (JButton b : this.buttonTemps){
            this.panel.add(b);
        }
        this.frame.repaint();
        this.frame.revalidate();
    }
}
