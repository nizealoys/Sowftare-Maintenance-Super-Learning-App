package src.core.view.states;

import java.util.ArrayList;
import javax.swing.*;
import src.core.view.ViewState;
import src.core.view.Viewer;
import src.feature.course.subfeature.method.Methode;
import src.feature.difficulty.Difficulty;

public class MethodState implements ViewState {

    Viewer viewer;
    JFrame frame;
    JPanel panel;
    ArrayList<JButton> buttonTemps;
    Methode current;
    String courseName;
    JLabel titlePanel = new JLabel();

    public MethodState(Viewer viewer) {
        this.viewer = viewer;
        this.frame = viewer.frame;
        this.panel = viewer.panel;
        this.buttonTemps = new ArrayList<>();
        build();
    }

    public void setData(Methode current) {
        this.current = current;
    }

    public void setName(String name) {
        this.courseName = name;
        this.titlePanel.setText(name);
    }

    public String getCourseName() {
        return this.courseName;
    }

    private void build() {

        JButton main = new JButton("Main Page");
        main.addActionListener((actionEvent) -> {
            viewer.goTo(Viewer.Page.MAIN);
        });

        this.buttonTemps.add(main);

        JButton workshopButton = new JButton("Workshop");
        workshopButton.addActionListener((actionEvent) -> {
            viewer.goTo(Viewer.Page.WIP);
        });
        this.buttonTemps.add(workshopButton);

        JButton lessons = new JButton("Lessons");
        lessons.addActionListener((actionEvent) -> {
            //WARNING!!! Ne Pas SUPPRIMER WARNING!!!
            LessonState ls = viewer.getState(Viewer.Page.LESSONS, LessonState.class);
            ls.setData(this.current);
            ls.setName(this.courseName);
            ls.setButtons();
            //WARNING!!! Ne Pas SUPPRIMER WARNING!!!
            //if (this.difficulties.get(this.courseName) != null) {
            viewer.goTo(Viewer.Page.LESSONS);
            //} else {
            //viewer.goTo(Viewer.Page.DIFFICULTY);
            //}
        });
        this.buttonTemps.add(lessons);

        JButton difficultyButton = new JButton("Set Difficulty");
        difficultyButton.addActionListener((actionEvent) -> {
            viewer.goTo(Viewer.Page.DIFFICULTY);
        });
        this.buttonTemps.add(difficultyButton);

        JButton profilbutton = new JButton("User Profile");
        profilbutton.addActionListener((actionEvent) -> {
            viewer.getState(Viewer.Page.PROFILE, ProfileState.class).getData();
            viewer.getState(Viewer.Page.PROFILE, ProfileState.class).infoPanel();
            viewer.goTo(Viewer.Page.PROFILE);
        });
        this.buttonTemps.add(profilbutton);

    }

    @Override
    public void handleRequest() {
        this.panel.add(this.titlePanel);
        for (JButton b : this.buttonTemps) {
            this.panel.add(b);
        }

        panel.add(new JLabel("Difficulty: " + Difficulty.getInstance().getDifficulty(this.courseName)));
        this.frame.repaint();
        this.frame.revalidate();
    }
}
