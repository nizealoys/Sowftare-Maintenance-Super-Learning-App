package src.core.view.States;

import src.core.view.ViewState;
import src.core.view.Viewer;
import src.feature.course.QA;
import src.feature.course.subfeature.method.Methode;

import javax.swing.*;
import java.util.ArrayList;

public class TopicState implements ViewState {
    Viewer viewer;
    JFrame frame;
    JPanel panel;
    ArrayList<QA> Exercice;
    ArrayList<JButton> buttonTemps;
    String currentTopic;
    String topicName;
    JLabel titlePanel= new JLabel();
    JLabel TopicExplanation = new JLabel();
    ArrayList<QA> exercice;

    public TopicState(Viewer viewer){
        this.viewer = viewer;
        this.frame = viewer.frame;
        this.panel = viewer.panel;
        this.buttonTemps = new ArrayList<>();
        build();
    }

    public void setData(String current, ArrayList<QA> QAlist){
        this.currentTopic = current;
        this.exercice = QAlist;
    }

    public void setName(String name){
        this.topicName = name;
        this.titlePanel.setText(name);
        this.TopicExplanation.setText(this.currentTopic);
    }

    @Override
    public void build() {

        JButton main = new JButton("Main Page");
        main.addActionListener((actionEvent) -> {
            viewer.goTo(Viewer.Page.MAIN);
        });
        this.buttonTemps.add(main);

        JButton exercices = new JButton("Exercices");
        exercices.addActionListener((actionEvent) -> {
            viewer.getState(Viewer.Page.EXERCICES, ExerciceState.class).setData(this.exercice);
            viewer.getState(Viewer.Page.LESSONS, LessonState.class).setName(this.topicName);
            viewer.goTo(Viewer.Page.EXERCICES);
        });
        this.buttonTemps.add(exercices);

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
        this.panel.add(this.TopicExplanation);
        this.frame.repaint();
        this.frame.revalidate();
    }
}
