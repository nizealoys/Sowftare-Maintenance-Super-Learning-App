package src.core.view.states;

import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.*;
import src.core.view.ViewState;
import src.core.view.Viewer;
import src.feature.course.subfeature.method.Methode;

public class MethodState implements ViewState {

    Viewer viewer;
    JFrame frame;
    JPanel panel;
    ArrayList<JButton> buttonTemps;
    Methode current;
    String courseName;
    JLabel titlePanel = new JLabel();
    HashMap<String, Integer> difficulties = new HashMap<>();

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

    public void setDifficulty(int d) {
        this.difficulties.put(this.courseName, d);
    }

    private void build() {

        JButton main = new JButton("Main Page");
        main.addActionListener((actionEvent) -> {
            viewer.goTo(Viewer.Page.MAIN);
        });

        this.buttonTemps.add(main);

        JButton workshopButton = new JButton("Workshop");
        workshopButton.addActionListener((actionEvent) ->{
            //viewer.goTo(Viewer.Page.WIP);
        });
        this.buttonTemps.add(workshopButton);


        JButton lessons = new JButton("Lessons");
        lessons.addActionListener((actionEvent) -> {
            //WARNING!!! Ne Pas SUPPRIMER WARNING!!!
            LessonState ls = viewer.getState(Viewer.Page.LESSONS, LessonState.class);
            ls.setData(this.current);
            ls.setName(this.courseName);
            //WARNING!!! Ne Pas SUPPRIMER WARNING!!!
            //if (this.difficulties.get(this.courseName) != null) {
                ls.setDifficulty(this.difficulties.get(this.courseName));
                viewer.goTo(Viewer.Page.LESSONS);
            //} else {
                //viewer.goTo(Viewer.Page.DIFFICULTY);
            //}
        });
        this.buttonTemps.add(lessons);

        JButton profilbutton = new JButton("User Profile");
        profilbutton.addActionListener((actionEvent) -> {
            viewer.getState(Viewer.Page.PROFILE, src.core.view.states.ProfileState.class).getData();
            viewer.getState(Viewer.Page.PROFILE, src.core.view.states.ProfileState.class).premiumPanel();
            viewer.goTo(Viewer.Page.PROFILE);
        });
        this.buttonTemps.add(profilbutton);

        JButton difficultyButton = new JButton("Set Difficulty");
        profilbutton.addActionListener((actionEvent) ->{
            viewer.goTo(Viewer.Page.DIFFICULTY);
        });

        this.buttonTemps.add(difficultyButton);
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
