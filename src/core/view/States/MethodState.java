package src.core.view.States;

import src.core.model.Feature;
import src.core.model.FeatureInterface;
import src.core.model.FeatureIterator;
import src.core.view.ViewState;
import src.core.view.Viewer;
import src.feature.course.subfeature.method.Methode;
import src.core.model.Iterator;
import src.core.model.Feature;
import src.feature.course.subfeature.type.language.English;

import javax.swing.*;
import java.util.ArrayList;
import java.util.HashMap;


public class MethodState implements ViewState {
    Viewer viewer;
    JFrame frame;
    JPanel panel;
    ArrayList<JButton> buttonTemps;
    Methode current;
    String courseName;
    JLabel titlePanel= new JLabel();
    HashMap<String, Integer> difficulties = new HashMap<>();   // nom du cours -> difficulté


    public MethodState(Viewer viewer){
        this.viewer = viewer;
        this.frame = viewer.frame;
        this.panel = viewer.panel;
        this.buttonTemps = new ArrayList<>();
        build();
    }

    public void setData(Methode current){
        this.current = current;
    }

    public void setName(String name){
        this.courseName = name;
        this.titlePanel.setText(name);
    }

    public void setDifficulty(int d){
        this.difficulties.put(this.courseName, d);
    }

    @Override
    public void build() {

        JButton main = new JButton("Main Page");
        main.addActionListener((actionEvent) -> {
            viewer.goTo(Viewer.Page.MAIN);
        });

        this.buttonTemps.add(main);

        this.buttonTemps.add(new JButton("Workshop"));

        /**
        JButton exercices = new JButton("Exercices");
        exercices.addActionListener((actionEvent) -> {
            viewer.goTo(Viewer.Page.EXERCICES);
        });
        this.buttonTemps.add(exercices);
         **/

        JButton lessons = new JButton("Lessons");
        lessons.addActionListener((actionEvent) -> {
            LessonState ls = viewer.getState(Viewer.Page.LESSONS, LessonState.class);
            ls.setData(this.current);
            ls.setName(this.courseName);
            if(this.difficulties.get(this.courseName) != null){
                ls.setDifficulty(this.difficulties.get(this.courseName));
                viewer.goTo(Viewer.Page.LESSONS);
            }else{
                viewer.goTo(Viewer.Page.DIFFICULTY);
            }
        });
        this.buttonTemps.add(lessons);

        JButton profilbutton = new JButton("User Profile");
        profilbutton.addActionListener((actionEvent) -> {
            viewer.goTo(Viewer.Page.PROFILE);
        });
        this.buttonTemps.add(profilbutton);
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
