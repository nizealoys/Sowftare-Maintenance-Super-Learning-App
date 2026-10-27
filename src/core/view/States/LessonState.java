package src.core.view.States;

import src.core.view.ViewState;
import src.core.view.Viewer;
import src.feature.course.QA;
import src.feature.course.subfeature.method.Methode;

import javax.swing.*;
import java.util.ArrayList;

public class LessonState implements ViewState{

    Viewer viewer;
    JFrame frame;
    JPanel panel;
    ArrayList<JButton> buttonTemps;
    Methode current;
    String courseName;
    JLabel titlePanel= new JLabel();
    ArrayList<Integer> topic_verificator = new ArrayList<Integer>();
    ArrayList<JButton> topicButtons = new ArrayList<>();
    int difficulty;


    public LessonState(Viewer viewer){
        this.viewer = viewer;
        this.frame = viewer.frame;
        this.panel = viewer.panel;
        this.buttonTemps = new ArrayList<>();
        build();
    }

    public void setData(Methode current){
        this.current = current;
        this.topicButtons = new ArrayList<>();
        this.topic_verificator.clear();
    }

    public void setName(String name){
        this.courseName = name;
        this.titlePanel.setText(name + " lessons");
    }

    public void setDifficulty(int i){
        this.difficulty = i;
        this.topicButtons = new ArrayList<>();
        this.topic_verificator.clear();
        if (this.current != null) {
            setButtons();
        }
    }

    public ArrayList<QA> getQA(int i, ArrayList<QA> all){
        ArrayList<QA> result = new ArrayList<>();
        for (QA question : all){
            if (question.getTopic() == i && question.getDifficulty() == this.difficulty){
                result.add(question);
            }
        }
        return result;
    }

    public void setButtons(){
        for (QA topic: current.getQAList()){
            if (!topic_verificator.contains(topic.getTopic()) && topic.getDifficulty() == this.difficulty){
                JButton topicbutton = new JButton("Topic" + topic.getTopic());
                ArrayList<QA> QA = getQA(topic.getTopic(),  current.getQAList());
                topicbutton.addActionListener((actionEvent) -> {
                    viewer.getState(Viewer.Page.TOPIC, TopicState.class).setData(topic.getExplanation(), QA);
                    viewer.getState(Viewer.Page.TOPIC, TopicState.class).setName("Topic" + topic.getTopic());
                    viewer.goTo(Viewer.Page.TOPIC);
                });
                topic_verificator.add(topic.getTopic());
                this.topicButtons.add(topicbutton);

            }
        }
    }

    @Override
    public void build() {

        JButton main = new JButton("Main Page");
        main.addActionListener((actionEvent) -> {
            viewer.goTo(Viewer.Page.MAIN);
        });

        this.buttonTemps.add(main);

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
        for (JButton b : this.topicButtons){
            this.panel.add(b);
        }
        this.frame.repaint();
        this.frame.revalidate();
    }
}
