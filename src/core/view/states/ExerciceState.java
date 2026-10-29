package src.core.view.states;

import java.util.ArrayList;
import javax.swing.*;
import src.core.view.ViewState;
import src.core.view.Viewer;
import src.feature.course.QA;

public class ExerciceState implements ViewState {

    Viewer viewer;
    JFrame frame;
    JPanel panel;
    ArrayList<JButton> buttonTemps;
    String topicName;
    JLabel titlePanel = new JLabel();
    ArrayList<QA> exercice = new ArrayList<>();
    ArrayList<JTextField> fields = new ArrayList<>();
    ArrayList<JLabel> labels = new ArrayList<>();
    ArrayList<JLabel> correction = new ArrayList<>();

    public ExerciceState(Viewer viewer) {
        this.viewer = viewer;
        this.frame = viewer.frame;
        this.panel = viewer.panel;
        this.buttonTemps = new ArrayList<>();
        build();
    }

    public void setData(ArrayList<QA> QAlist) {
        this.exercice = QAlist;
        this.labels.clear();
        this.fields.clear();
        this.correction.clear();
        for (QA question : QAlist) {
            this.labels.add(new JLabel(question.getQuestion()));
            this.fields.add(new JTextField(16));
        }
    }

    public void setName(String name) {
        this.topicName = name;
        this.titlePanel.setText(name);
    }

    public boolean correction(JTextField answer, QA question) {
        return answer.getText().trim().equalsIgnoreCase(question.getAnswer().trim());
    }

    private void build() {
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

        JButton submit = new JButton("Submit");
        submit.addActionListener((actionEvent) -> {
            this.correction.clear();
            for (int i = 0; i < this.exercice.size(); i++) {
                if (!correction(this.fields.get(i), this.exercice.get(i))) {
                    this.correction.add(new JLabel("Correction " + (i + 1) + ": " + this.exercice.get(i).getAnswer()));
                }
            }
            if (this.correction.isEmpty()) {
                this.correction.add(new JLabel("ALL YOUR ANSWERS ARE CORRECT"));
            } else {
                this.correction.add(new JLabel("YOU GOT AT LEAST ONE WRONG ANSWER"));
            }
            handleRequest();
        });
        this.buttonTemps.add(submit);
    }

    @Override
    public void handleRequest() {
        this.panel.removeAll();
        this.panel.add(this.titlePanel);
        for (int i = 0; i < this.labels.size(); i++) {
            this.panel.add(this.labels.get(i));
            this.panel.add(this.fields.get(i));
        }
        for (JButton b : this.buttonTemps) {
            this.panel.add(b);
        }
        for (JLabel c : this.correction) {
            this.panel.add(c);
        }
        this.panel.revalidate();
        this.panel.repaint();
    }
}
